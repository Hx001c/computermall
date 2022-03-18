package com.athx.computermall.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/13 20:18
 * @description：省市区的实体类
 * @modified By：
 * @version: $
 */
@Data
@TableName("t_dict_district")
public class District implements Serializable {
    private Integer id;
    //父区域的代号
    private String parent;
    //本区域的代号
    private String code;
    private String name;

}
