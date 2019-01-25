package com.puhui8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.puhui8.entity.SysRole;

@Mapper
public interface SysRoleDao {
    int insert(@Param("pojo") SysRole pojo);

    int insertSelective(@Param("pojo") SysRole pojo);

    int insertList(@Param("pojos") List<SysRole> pojo);

    int update(@Param("pojo") SysRole pojo);
}
