package com.puhui8.service.impl;

import com.puhui8.entity.SysRoleResource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.puhui8.dao.SysRoleResourceDao;

@Service
public class SysRoleResourceService{

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
}
