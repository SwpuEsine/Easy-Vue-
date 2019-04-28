package com.ev.dao;

import com.ev.entity.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleResourceDao {
    int insert(@Param("pojo") SysRoleResource pojo);

    int insertSelective(@Param("pojo") SysRoleResource pojo);

    int insertList(@Param("pojos") List<SysRoleResource> pojo);

    int update(@Param("pojo") SysRoleResource pojo);

    int deleteBYRoleId(@Param("roleId")Long roleId);

    //获取
    List<Long> findChildrenResIdByRoleId(@Param("roleId")Long roleId);


}
