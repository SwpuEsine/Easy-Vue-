package com.ev.service.impl;

import com.ev.dao.SysRoleResourceDao;
import com.ev.entity.SysRoleResource;
import com.ev.service.SysRoleResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Resource
    private SysRoleResourceDao sysRoleResourceDao;

    public int insert(SysRoleResource pojo){
        return sysRoleResourceDao.insert(pojo);
    }

    public int insertSelective(SysRoleResource pojo){
        return sysRoleResourceDao.insertSelective(pojo);
    }

    public int insertList(List<SysRoleResource> pojos){
        return sysRoleResourceDao.insertList(pojos);
    }

    public int update(SysRoleResource pojo){
        return sysRoleResourceDao.update(pojo);
    }

    @Override
    public int deleteBYRoleId(Long roleId) {
        return sysRoleResourceDao.deleteBYRoleId(roleId);
    }

    @Override
    public List<Long> findChildrenResIdByRoleId(Long roleId) {
        return sysRoleResourceDao.findChildrenResIdByRoleId(roleId);
    }
}
