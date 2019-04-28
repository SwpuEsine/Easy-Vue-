package com.ev.service.impl;

import com.ev.dao.SysUserRoleDao;
import com.ev.entity.SysUser;
import com.ev.entity.SysUserRole;
import com.ev.service.SysUserRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService{

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    public int insert(SysUserRole pojo){
        return sysUserRoleDao.insert(pojo);
    }

    public int insertSelective(SysUserRole pojo){
        return sysUserRoleDao.insertSelective(pojo);
    }

    public int insertList(List<SysUserRole> pojos){
        return sysUserRoleDao.insertList(pojos);
    }

    @Override
    public List<SysUserRole> findByUserId(Long userId) {
        return sysUserRoleDao.findByUserId(userId);
    }

    @Override
    public int deleteByUserId(Long userId) {
        return sysUserRoleDao.deleteByUserId(userId);
    }

    @Override
    public int deleteByuserIdIn(List<Long> userIdList) {
        return sysUserRoleDao.deleteByuserIdIn(userIdList);
    }

    @Override
    public List<SysUser> findUserListByRoleId(Long roleId) {
        return sysUserRoleDao.findUserListByRoleId(roleId);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        return sysUserRoleDao.deleteByRoleId(roleId);
    }

    public int update(SysUserRole pojo){
        return sysUserRoleDao.update(pojo);
    }


}
