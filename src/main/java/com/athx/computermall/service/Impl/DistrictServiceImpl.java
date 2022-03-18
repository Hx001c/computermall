package com.athx.computermall.service.Impl;

import com.athx.computermall.bean.District;
import com.athx.computermall.mapper.DistrictMapper;
import com.athx.computermall.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 20:39
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> districtList = districtMapper.findByParent(parent);
        for (District district : districtList) {
            district.setParent(null);
            district.setId(null);
        }
        return districtList;
    }

    @Override
    public String getNameById(String code) {
        String name = districtMapper.findNameByCode(code);
        return name;
    }
}
