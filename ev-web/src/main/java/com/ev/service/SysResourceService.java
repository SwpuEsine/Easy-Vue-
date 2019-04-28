package com.ev.service;

import com.ev.entity.SysResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @create 2019-02-18 上午9:32
 **/
public interface SysResourceService {
    public List<String> findPathByResourceName(String roleName);

    public List<SysResource> findAllByRoleIdsIn(List<Long> roleId);

    List<SysResource> findAll();

    SysResource findByResourceId(Long resourceId);

    List<SysResource> findByParentId(Long parentId);

    List<SysResource> findByIsMenu(Boolean isMenu);
    //删除资源以及子资源
    int deleteBYResourceIdOrParentId(Long resouceId);

    int update(SysResource pojo);

    int insertSelective(SysResource pojo);

    List<SysResource> findByRoleId(Long roleId);
}
