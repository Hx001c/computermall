package com.athx.computermall.mapper;

import com.athx.computermall.bean.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 18:46
 * @description：收获地址数据的持久层接口
 * @modified By：
 * @version: $
 */
public interface AddressMapper extends BaseMapper<Address> {
    /**\
     * 根据id来查询当前用户的总收获地址数量
     * @param uid
     * @return
     */

    public Integer selectCountById(Integer uid);

    @Override
    int insert(Address entity);


    /**
     * 根据uid来查询列表收货地址
     * @param uid
     * @return
     */
    List<Address> selectAddressListByUid(Integer uid);

    /**
     * 把所有的地址设置为非默认地址
     * @param uid
     * @return
     */
    Integer updateNoneDefaultByUid(Integer uid);

    /**
     * 根据id指定设置为默认地址
     * @param aid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateDefaultsById(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据aid 查询该收获地址是否存在
     * @param aid
     * @return
     */
    Address selectByAid(Integer aid);

    /**
     * 根据id删除对应的地址数据
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询某用户最后修改的收货地址
     * @param uid 归属的用户id
     * @return 该用户最后修改的收货地址，如果该用户没有收货地址数据则返回null
     */
    Address selectLastModify(Integer uid);
}
