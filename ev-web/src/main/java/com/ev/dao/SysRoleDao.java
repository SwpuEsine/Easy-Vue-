package com.ev.dao;

import com.ev.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleDao {
    int insert(@Param("pojo") SysRole pojo);

    int insertSelective(@Param("pojo") SysRole pojo);

    int insertList(@Param("pojos") List<SysRole> pojo);

    int update(@Param("pojo") SysRole pojo);

    List<SysRole> findbyRoleIdAndRoleNameIn(@Param("roleId") Long roleId, @Param("roleNameList") List<String> roleNameList);

    List<String> findRoleName();

    List<SysRole>  findRoleIdAndRemark();

    List<SysRole> find();

    int deleteByRoleId(@Param("roleId")Long roleId);

    SysRole findByRoleId(@Param("roleId")Long roleId);


}
