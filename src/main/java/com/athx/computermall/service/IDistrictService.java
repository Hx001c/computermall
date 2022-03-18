package com.athx.computermall.service;

import com.athx.computermall.bean.District;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 20:38
 * @description：处理省/市/区数据的业务层接口
 * @modified By：
 * @version: $
 */
public interface IDistrictService{
    /**
     * 获取全国所有省/某省所有市/某市所有区
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);

    /**
     * 获取城市省份区县的名字
     * @param code
     * @return
     */
    String getNameById(String code);
}
