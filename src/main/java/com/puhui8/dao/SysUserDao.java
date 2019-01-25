package com.puhui8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.puhui8.entity.SysUser;

@Mapper
public interface SysUserDao {
    int insert(@Param("pojo") SysUser pojo);

    int insertSelective(@Param("pojo") SysUser pojo);

    int insertList(@Param("pojos") List<SysUser> pojo);

    int update(@Param("pojo") SysUser pojo);

    List<SysUser> findByUserName(@Param("userName")String userName);

    String findUserRoleNameWithDelimiter(@Param("userId")Long userId);

}
