package com.ev.service.impl;

import com.ev.common.PageList;
import com.ev.dao.SysUserDao;
import com.ev.dao.SysUserRoleDao;
import com.ev.entity.SysUser;
import com.ev.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends PageBaseServiceImpl<SysUser> implements SysUserService{

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

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

    @Override
    public int deleteByUserIdIn(List<Long> userIdList) {
        return sysUserDao.deleteByUserIdIn(userIdList);
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

    @Override
    public List<Long> getUserRoleIds(Long userId) {
        return sysUserRoleDao.findRoleIdByUserId(userId);
    }

    public PageList<SysUser> findByUsernameAndPhone(int pageCode,int pageSize,String userName,String phone){
        return super.findAllByPageParams(pageCode,pageSize,() -> sysUserDao.findByUsernameAndPhone(userName,phone));
    }

    @Override
    public int deleteByUserId(Long userId) {
        return sysUserDao.deleteByUserId(userId);
    }

    @Override
    public SysUser findbyUserId(Long userId) {
        return sysUserDao.findbyUserId(userId);
    }


}
