package com.athx.computermall.service.Impl;

import com.athx.computermall.Vo.CartVo;
import com.athx.computermall.bean.Cart;
import com.athx.computermall.bean.Product;
import com.athx.computermall.mapper.CartMapper;
import com.athx.computermall.service.ICartService;
import com.athx.computermall.service.IProductService;
import com.athx.computermall.service.exception.*;
import javafx.scene.web.PromptData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:24
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;
    @Override
    public void addCartItem(Integer uid, Integer pid, Integer amount, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart item = cartMapper.selectcartByUidandPid(uid, pid);
        if (item==null){
            // 判断查询结果是否为null
            // 是：表示该用户并未将该商品添加到购物车
            // -- 创建Cart对象
            Cart cart = new Cart();
            // -- 封装数据：uid,pid,amount
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setCreatedTime(new Date());
            cart.setCreatedUser(username);
            cart.setModifiedTime(new Date());
            cart.setModifiedUser(username);
            cart.setNum(amount);
            // -- 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = productService.findById(pid);
            // -- 封装数据：price
            cart.setPrice(product.getPrice());
            // -- 封装数据：4个日志
            // -- 调用insert(cart)执行将数据插入到数据表中
            Integer row = cartMapper.insertOne(cart);
            if (row<1){
                throw new InsertException("添加购物车时出现异常");
            }
            return;
        }else {

            // 否：表示该用户的购物车中已有该商品
            // -- 从查询结果中获取购物车数据的id
            // -- 从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer cid = item.getCid();
            Integer num = item.getNum();
            // -- 执行更新数量
            Integer integer = cartMapper.updateNumByCid(cid, num + amount, username, new Date());
            if (integer<1){
                throw new UpdateException("更新购物车数据异常");
            }
        }

    }

    @Override
    public List<CartVo> getListCartVo(Integer uid) {
        List<CartVo> list = cartMapper.selectListByuid(uid);
        return list;
    }

    @Override
    public Integer addNum( Integer cid, String username,Integer uid) {
        //1.查询该条数据是否存在
        CartVo cartVo = cartMapper.selectByCid(cid);
        if (cartVo==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        //2.数据存在 判断uid 和参数中的uid 是否一样
        if(cartVo.getUid()!=uid){
            throw new AccessDeniedException("非法访问异常");
        }
        //获取新的数量
        int num = cartVo.getNum() + 1;
        cartMapper.updateNumByCid(cid,num,username,new Date());
        return num;
    }
    @Override
    public Integer reduceNum(Integer cid, String username,Integer uid) {
        //1.查询该条数据是否存在
        CartVo cartVo = cartMapper.selectByCid(cid);
        if (cartVo==null){
            throw new CartNotFoundException("购物车数据不存在");
        }
        //2.数据存在 判断uid 和参数中的uid 是否一样
        if(cartVo.getUid()!=uid){
            throw new AccessDeniedException("非法访问异常");
        }
        //判断数量是否等于1 是的不能再减少了
        if (cartVo.getNum()==1){
            throw new CartNumException("该条数据不能再少了");
        }
        int num = cartVo.getNum() - 1;

        cartMapper.updateNumByCid(cid,cartVo.getNum()-1,username,new Date());
        return num;
    }

    @Override
    public void deleteCartItem(Integer cid, Integer uid) {
        CartVo cartVo = cartMapper.selectByCid(cid);
        if (cartVo==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (cartVo.getUid()!=uid){
            throw new  AccessDeniedException("非法访问异常");
        }
        Integer integer = cartMapper.deleteCartItem(cid);
        if (integer<1){
            throw new DeleteException("删除时出现异常");
        }
    }
}
