package com.ev.service;

import com.ev.entity.SysRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleResourceService {
    int deleteBYRoleId(Long roleId);
    List<Long> findChildrenResIdByRoleId(Long roleId);
    int insert( SysRoleResource pojo);
    public int insertList(List<SysRoleResource> pojos);
}
