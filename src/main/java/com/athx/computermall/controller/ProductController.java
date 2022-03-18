package com.athx.computermall.controller;

import com.athx.computermall.bean.Product;
import com.athx.computermall.service.IProductService;
import com.athx.computermall.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.athx.computermall.controller.BaseController.OK;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/15 19:18
 * @description：商品数据的控制层
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @RequestMapping("/hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product> list = productService.findHotList();
        return new JsonResult<>(OK,list);
    }

    @RequestMapping("get_detail/{id}")
    public JsonResult<Product> getDetail(@PathVariable("id") Integer id){
        return new JsonResult<>(OK,productService.findById(id));
    }
}
