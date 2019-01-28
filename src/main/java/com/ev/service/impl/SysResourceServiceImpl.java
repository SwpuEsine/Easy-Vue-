package com.ev.service.impl;

import com.ev.entity.SysResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ev.dao.SysResourceDao;

@Service
public class SysResourceServiceImpl{

    @Resource
    private SysResourceDao sysResourceDao;

    public int insert(SysResource pojo){
        return sysResourceDao.insert(pojo);
    }

    public int insertSelective(SysResource pojo){
        return sysResourceDao.insertSelective(pojo);
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
}
