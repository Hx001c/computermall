package com.athx.computermall.controller;

import com.athx.computermall.Vo.CartVo;
import com.athx.computermall.bean.Cart;
import com.athx.computermall.mapper.CartMapper;
import com.athx.computermall.service.ICartService;
import com.athx.computermall.utils.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/16 19:54
 * @description：购物车的控制层
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;
    @RequestMapping("/add_cart")
    public JsonResult<Void> addToCart(HttpSession session,Integer pid,Integer amount){
        System.out.println(amount);
        cartService.addCartItem(getUidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping({"/",""})
    public JsonResult<List<CartVo>> getCartList(HttpSession session){
        List<CartVo> listCartVo = cartService.getListCartVo(getUidFromSession(session));
        return new JsonResult<>(OK,listCartVo);
    }

    @RequestMapping("/reduce_num/{cid}")
    public JsonResult<Integer> reduceNum(HttpSession session,@PathVariable("cid")Integer cid){
        Integer reduceNum = cartService.reduceNum(cid, getUsernameFromSession(session), getUidFromSession(session));
        return new JsonResult<>(OK,reduceNum);
    }

    @RequestMapping("/add_num/{cid}")
    public JsonResult<Integer> addNum(HttpSession session,@PathVariable("cid") Integer cid){
        Integer addNum = cartService.addNum(cid, getUsernameFromSession(session), getUidFromSession(session));
        return new JsonResult<>(OK,addNum);
    }


    @RequestMapping("/delete_item")
    public JsonResult<Void> deleteItem(HttpSession session,@Param("cid") Integer cid){
        System.out.println(cid);
        cartService.deleteCartItem(cid,getUidFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping("/delete_check_list")
    public JsonResult<Void> deleteItem( Integer[] cids){
        cartService.deleteCheckedCartVO(cids);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/list")
    public JsonResult<List<CartVo>> getCartList(Integer[] cids,HttpSession session){
        List<CartVo> cartVOByCids = cartService.getCartVOByCids(getUidFromSession(session), Arrays.asList(cids));
        return new JsonResult<>(OK,cartVOByCids);
    }
}
