package com.athx.computermall.controller;

import com.athx.computermall.bean.District;
import com.athx.computermall.service.IDistrictService;
import com.athx.computermall.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 20:42
 * @description：获取省市区的控制层
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

    @RequestMapping({"","/"})
    public JsonResult<List<District>> getByParent(String parent){
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<List<District>> (200,list);
    }


}
