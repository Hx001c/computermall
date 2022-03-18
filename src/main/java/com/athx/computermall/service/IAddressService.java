package com.athx.computermall.service;

import com.athx.computermall.bean.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 19:10
 * @description：处理收获地址数据的业务层接口
 * @modified By：
 * @version: $
 */
public interface IAddressService extends IService<Address> {
    /**
     * 创建新的收获地址
     * @param address 收货地址
     * @param uid 当前登陆的用户id
     * @param username 当前登录的用户名
     */
    public void addNewAddress(Address address,Integer uid,String username);

    /**
     * 查询某用户的收货地址列表数据
     * @param uid 收货地址归属的用户id
     * @return 该用户的收货地址列表数据
     */
    List<Address> getAddressListByUid(Integer uid);

    /**
     * 更改默认地址
     * @param aid
     * @param uid
     * @return
     */
    void changeDefaultAddress(Integer aid,Integer uid,String username);


    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void deleteAddress(Integer aid,Integer uid,String username);
}
