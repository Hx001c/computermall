package com.athx.computermall.mapper;

import com.athx.computermall.bean.District;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 20:21
 * @description：处理省/市/区数据的持久层接口
 * @modified By：
 * @version: $
 */
public interface DistrictMapper extends BaseMapper<District> {
    /**
     * 根据父代号查询区信息
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    /**
     * 获取省市区的名字
     * @param code
     * @return
     */
    String findNameByCode(String code);
}
