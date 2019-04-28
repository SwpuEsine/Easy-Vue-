package com.ev.service;

import com.ev.entity.SysResource;
import com.ev.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleService {
    public int insert(SysRole pojo);

    public int insertSelective(SysRole pojo);

    public int insertList(List<SysRole> pojos);

    public int update(SysRole pojo);

    List<String> findRoleName();

    List<SysRole> findRoleIdAndRemark();

    List<SysRole> find();

    int deleteByRoleId(Long roleId);

    SysRole findByRoleId(Long roleId);
}
