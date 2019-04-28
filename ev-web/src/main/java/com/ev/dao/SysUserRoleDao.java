package com.ev.dao;

import com.ev.entity.SysUser;
import com.ev.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao {
    int insert(@Param("pojo") SysUserRole pojo);

    int insertSelective(@Param("pojo") SysUserRole pojo);

    int insertList(@Param("pojos") List<SysUserRole> pojo);

    int update(@Param("pojo") SysUserRole pojo);

    List<Long> findRoleIdByUserId(@Param("userId")Long userId);

    List<SysUserRole> findByUserId(@Param("userId")Long userId);

    int deleteByUserId(@Param("userId")Long userId);

    int deleteByuserIdIn(@Param("userIdList")List<Long> userIdList);

    List<SysUser> findUserListByRoleId(@Param("roleId")Long roleId);

    int deleteByRoleId(@Param("roleId")Long roleId);

}
