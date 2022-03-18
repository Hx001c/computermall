package com.athx.computermall.controller;

import com.athx.computermall.bean.Address;
import com.athx.computermall.service.IAddressService;
import com.athx.computermall.service.IDistrictService;
import com.athx.computermall.utils.JsonResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import javafx.scene.AmbientLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 19:47
 * @description：地址信息的控制层
 * @modified By：
 * @version: $
 */
@RestController()
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IDistrictService districtService;
    @Autowired
    private IAddressService addressService;
    @RequestMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session){
        //调用业务层来添加地址
        addressService.addNewAddress(address,getUidFromSession(session),getUsernameFromSession(session));
        //相应成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getAddressListByuid(HttpSession session){
        List<Address> list = addressService.getAddressListByUid(getUidFromSession(session));
        return new JsonResult<>(OK,list);
    }
    @RequestMapping("/change_default_address/{aid}")
    public JsonResult<Void> changeDefauktAddress(HttpSession session,@PathVariable("aid") Integer aid){
        addressService.changeDefaultAddress(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete_address/{aid}")
    public JsonResult<Void> deltedAddress(HttpSession session,@PathVariable("aid") Integer aid){
        addressService.deleteAddress(aid,getUidFromSession(session),getUsernameFromSession(session));

        return new JsonResult<>(OK);
    }
}
