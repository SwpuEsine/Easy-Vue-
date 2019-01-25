package com.puhui8.service.impl;

import com.puhui8.entity.SysUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.puhui8.dao.SysUserDao;

@Service
public class SysUserService{

    @Resource
    private SysUserDao sysUserDao;

    public int insert(SysUser pojo){
        return sysUserDao.insert(pojo);
    }

    public int insertSelective(SysUser pojo){
        return sysUserDao.insertSelective(pojo);
    }

    public int insertList(List<SysUser> pojos){
        return sysUserDao.insertList(pojos);
    }

    public int update(SysUser pojo){
        return sysUserDao.update(pojo);
    }

    public SysUser findByUserName(String userName){
        List<SysUser> sysusers = sysUserDao.findByUserName(userName);
        return sysusers.isEmpty()?null:sysusers.get(0);
    }

    /**
     * 逗号分隔角色列表
     * @return
     */
    public String  getUserRoleNameWithDelimiter(Long userId){
       return  sysUserDao.findUserRoleNameWithDelimiter(userId);
    }

}
