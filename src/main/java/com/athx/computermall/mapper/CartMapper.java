package com.athx.computermall.mapper;

import com.athx.computermall.Vo.CartVo;
import com.athx.computermall.bean.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:03
 * @description：购物车数据持久层数据
 * @modified By：
 * @version: $
 */
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insertOne(Cart cart);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart selectcartByUidandPid(Integer uid,Integer pid);


    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(@Param("cid") Integer cid, @Param("num") Integer num,@Param("modifiedUser") String modifiedUser, @Param("modifiedTime") Date modifiedTime);

    /**
     * 展示购物车数据
     * @param uid
     * @return
     */
    List<CartVo> selectListByuid(Integer uid);
    /**
     * 根据id查询是否存在数据
     * @param cid
     * @return
     */
    CartVo selectByCid(Integer cid);

    /**
     * 删除商品
     * @param cid
     * @return
     */
    Integer deleteCartItem(Integer cid);

}
