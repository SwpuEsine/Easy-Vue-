package com.puhui8.entity;

import lombok.Data;

/**
 * @author
 * @create 2019-01-21 下午3:12
 **/
@Data
public class SysRole {
    private Long roleId;
    //角色名称
    private String roleName;
    //备注
    private String remark;

}
