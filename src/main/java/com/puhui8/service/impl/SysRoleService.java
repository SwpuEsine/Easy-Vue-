package com.puhui8.service.impl;

import com.puhui8.dao.SysRoleDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.puhui8.entity.SysRole;

@Service
public class SysRoleService{

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
