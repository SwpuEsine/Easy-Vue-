package com.ev.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.ev.entity.SysUserRole;

@Mapper
public interface SysUserRoleDao {
    int insert(@Param("pojo") SysUserRole pojo);

    int insertSelective(@Param("pojo") SysUserRole pojo);

    int insertList(@Param("pojos") List<SysUserRole> pojo);

    int update(@Param("pojo") SysUserRole pojo);
}
