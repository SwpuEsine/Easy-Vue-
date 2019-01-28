package com.ev.service.impl;

import com.ev.entity.SysRole;
import com.ev.dao.SysRoleDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl{

    @Resource
    private SysRoleDao sysRoleDao;

    public int insert(SysRole pojo){
        return sysRoleDao.insert(pojo);
    }

    public int insertSelective(SysRole pojo){
        return sysRoleDao.insertSelective(pojo);
    }

    public int insertList(List<SysRole> pojos){
        return sysRoleDao.insertList(pojos);
    }

    public int update(SysRole pojo){
        return sysRoleDao.update(pojo);
    }


}
