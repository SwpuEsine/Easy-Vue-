package com.ev.service.impl;

import com.ev.dao.SysRoleDao;
import com.ev.entity.SysRole;
import com.ev.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

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

    @Override
    public List<String> findRoleName() {
        return sysRoleDao.findRoleName();
    }

    @Override
    public List<SysRole> findRoleIdAndRemark(){
        return sysRoleDao.findRoleIdAndRemark();
    }

    @Override
    public List<SysRole> find() {
        return sysRoleDao.find();
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        return sysRoleDao.deleteByRoleId(roleId);
    }

    @Override
    public SysRole findByRoleId(Long roleId) {
        return sysRoleDao.findByRoleId(roleId);
    }

}
