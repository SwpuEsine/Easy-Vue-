package com.ev.service;

import com.ev.entity.SysUser;
import com.ev.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleService {
    public int insertList(List<SysUserRole> pojos);
    List<SysUserRole> findByUserId(Long userId);
    int deleteByUserId(Long userId);
    int deleteByuserIdIn(List<Long> userIdList);
    List<SysUser> findUserListByRoleId(Long roleId);
    int deleteByRoleId(Long roleId);
}
