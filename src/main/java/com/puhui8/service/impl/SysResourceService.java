package com.puhui8.service.impl;

import com.puhui8.entity.SysResource;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.puhui8.dao.SysResourceDao;

@Service
public class SysResourceService{

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
}
