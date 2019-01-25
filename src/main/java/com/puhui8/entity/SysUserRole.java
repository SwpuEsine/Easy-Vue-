package com.puhui8.entity;

import lombok.Data;

/**
 * 用户角色表
 *
 * @author
 * @create 2019-01-21 下午3:18
 **/
@Data
public class SysUserRole {
    private Long userRoleId;
    private Long roleId;
    private Long userId;

}
