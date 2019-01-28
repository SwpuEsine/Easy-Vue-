package com.ev.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.ev.entity.SysResource;

@Mapper
public interface SysResourceDao {
    int insert(@Param("pojo") SysResource pojo);

    int insertSelective(@Param("pojo") SysResource pojo);

    int insertList(@Param("pojos") List<SysResource> pojo);

    int update(@Param("pojo") SysResource pojo);

    List<String> findPathByResourceName(@Param("roleName")String roleName);


}
