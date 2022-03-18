package com.athx.computermall.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Hexin
 * @date ：Created in 2022/3/12 10:31
 * @description：实体类的基类
 * @modified By：
 * @version: $
 */
@Data
public class BaseEntity implements Serializable {
   private String createdUser;// '日志-创建人',
    private Date createdTime;//'日志-创建时间',
    private String modifiedUser; //'日志-最后修改执行人',
    private Date modifiedTime;// '日志-最后修改时间',

}
