package com.ev.dao;

import com.ev.entity.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysResourceDao {
    int insert(@Param("pojo") SysResource pojo);

    int insertSelective(@Param("pojo") SysResource pojo);

    int insertList(@Param("pojos") List<SysResource> pojo);

    int update(@Param("pojo") SysResource pojo);

    List<String> findPathByResourceName(@Param("roleName") String roleName);

    List<SysResource> findAllByRoleIdsIn(@Param("roleIds")List<Long> roleIds);

    List<SysResource> findByResourceIdOrderByOrderAsc(@Param("resourceId")Long resourceId);

    List<SysResource> findAll();

    SysResource findByResourceId(@Param("resourceId")Long resourceId);

    List<SysResource> findByParentId(@Param("parentId")Long parentId);

    List<SysResource> findByIsMenu(@Param("isMenu")Boolean isMenu);


    int deleteBYResourceIdOrParentId(@Param("resourceId")Long resourceId,@Param("parentId")Long parentId);


    List<SysResource> findByRoleId(@Param("roleId")Long roleId);



}
