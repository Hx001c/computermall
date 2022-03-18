package com.athx.computermall.service.Impl;

import com.athx.computermall.bean.Address;
import com.athx.computermall.mapper.AddressMapper;
import com.athx.computermall.mapper.UserMapper;
import com.athx.computermall.service.IAddressService;
import com.athx.computermall.service.IDistrictService;
import com.athx.computermall.service.exception.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 19:19
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper,Address> implements IAddressService {
    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        // 统计当前用户的收货地址数据的数量
        Integer count = addressMapper.selectCountById(uid);
        // 判断数量是否达到上限值
        // 是：抛出AddressCountLimitException
        if (count>=maxCount){
            throw new AddressCountLimitException("用户收获地址数量超过上限");
        }
        // 补全数据：将参数uid封装到参数address中
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        // 补全数据：4项日志
        address.setUid(uid);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
        address.setCreatedUser(username);
        Integer isDefault = count==0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //设置省份名
        address.setProvinceName(districtService.getNameById(address.getProvinceCode()));
        //设置城市名
        address.setCityName(districtService.getNameById(address.getCityCode()));
        //设置地区名
        address.setAreaName(districtService.getNameById(address.getAreaCode()));
        // 调用addressMapper的insert()方法插入收货地址数据，并获取返回的受影响行数
        int rows = addressMapper.insert(address);
        // 判断受影响行数是否不为1
        // 是：抛出InsertException
        if(rows!=1){
            throw new InsertException("添加新的地址时出现异常");
        }
    }

    @Override
    public List<Address> getAddressListByUid(Integer uid) {

        List<Address> addresses = addressMapper.selectAddressListByUid(uid);
        for (Address address : addresses) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return addresses;
    }

    @Override
    public void changeDefaultAddress(Integer aid, Integer uid,String username) {
        //1.查询该地址是否存在
        Address address = addressMapper.selectByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("地址不存在");
        }
        // 2.判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        // 是：抛出AccessDeniedException：非法访问
        if(address.getUid()!=uid){
            throw new AccessDeniedException("非法访问异常");
        }

        //3.将所有的地址设置为非默认
        Integer integer = addressMapper.updateNoneDefaultByUid(uid);
        if (integer==null){
            throw new UpdateException("更新默认地址时异常");
        }

        // 调用addressMapepr的updateDefaultByAid()将指定aid的收货地址设置为默认，并获取返回的受影响的行数
        // 判断受影响的行数是否不为1
        // 是：抛出UpdateException
        Integer rows = addressMapper.updateDefaultsById(aid, username, new Date());
        if(rows<=0){
            throw new UpdateException("设置默认地址时出现异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        //1.根据参数aid 查询是否存在地址数据
        Address address = addressMapper.selectByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("地址不存在");
        }
        //2.判断查询结果中的uid和登录用户的uid是否一致
        if(address.getUid()!=uid){
            throw new AccessDeniedException("非法访问异常");
        }
        //根据参数aid 删除对应的地址数据
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows<=0){
            throw new DeleteException("删除时出现异常");
        }
        //判断要删除的数据是否时默认地址
        if (address.getIsDefault()==0){
            //不是 则直接返回
            return;
        }
        //是默认地址则需要将最近一次修改的地址设置为默认地址
        //查询当前用户是否还有其他的地址
        Integer count = addressMapper.selectCountById(uid);
        if(count==0){
            //没有其他的地址可以直接返回
            return;
        }
        //找出最近一次用户修改的数据
        Address lastModify = addressMapper.selectLastModify(uid);
        //将其设置为默认地址
        Integer rows2 = addressMapper.updateDefaultsById(lastModify.getAid(), username, new Date());
        if(rows<1){
            throw new UpdateException("设置默认地址时出现异常");
        }
    }


}
