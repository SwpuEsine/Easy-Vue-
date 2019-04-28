package com.ev.service.impl;

import com.ev.dao.SysResourceDao;
import com.ev.entity.SysResource;
import com.ev.service.SysResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysResourceServiceImpl implements SysResourceService{

    @Resource
    private SysResourceDao sysResourceDao;

    public int insert(SysResource pojo){
        return sysResourceDao.insert(pojo);
    }

    public int insertSelective(SysResource pojo){
        return sysResourceDao.insertSelective(pojo);
    }

    @Override
    public List<SysResource> findByRoleId(Long roleId) {
        return sysResourceDao.findByRoleId(roleId);
    }

    public int insertList(List<SysResource> pojos){
        return sysResourceDao.insertList(pojos);
    }

    public int update(SysResource pojo){
        return sysResourceDao.update(pojo);
    }

    public List<String> findPathByResourceName(String roleName){
        return sysResourceDao.findPathByResourceName(roleName);
    }

    @Override
    public List<SysResource> findAllByRoleIdsIn(List<Long> roleId) {
        return sysResourceDao.findAllByRoleIdsIn(roleId);
    }

    @Override
    public List<SysResource> findAll() {
        return sysResourceDao.findAll();
    }

    @Override
    public SysResource findByResourceId(Long resourceId) {
        return sysResourceDao.findByResourceId(resourceId);
    }

    @Override
    public List<SysResource> findByParentId(Long parentId) {
        return sysResourceDao.findByParentId(parentId);
    }

    @Override
    public List<SysResource> findByIsMenu(Boolean isMenu) {
        return sysResourceDao.findByIsMenu(isMenu);
    }

    @Override
    public int deleteBYResourceIdOrParentId(Long resouceId) {
        return sysResourceDao.deleteBYResourceIdOrParentId(resouceId,resouceId);
    }

}
