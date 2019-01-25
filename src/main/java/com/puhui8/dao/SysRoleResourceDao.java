package com.puhui8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.puhui8.entity.SysRoleResource;

@Mapper
public interface SysRoleResourceDao {
    int insert(@Param("pojo") SysRoleResource pojo);

    int insertSelective(@Param("pojo") SysRoleResource pojo);

    int insertList(@Param("pojos") List<SysRoleResource> pojo);

    int update(@Param("pojo") SysRoleResource pojo);
}
