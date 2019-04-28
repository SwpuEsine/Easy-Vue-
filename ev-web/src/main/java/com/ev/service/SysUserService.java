package com.ev.service;

import com.ev.common.PageList;
import com.ev.entity.SysUser;
import com.ev.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserService {
    public SysUser findByUserName(String userName);
    public String  getUserRoleNameWithDelimiter(Long userId);
    public List<Long> getUserRoleIds(Long userId);
    public int insert(SysUser pojo);
    public int insertSelective(SysUser pojo);
    public PageList<SysUser> findByUsernameAndPhone(int pageCode,int pageSize,String userName, String phone);
    int deleteByUserId(Long userId);
    SysUser findbyUserId(Long userId);
    int update(SysUser pojo);
    int deleteByUserIdIn(List<Long> userIdList);
}
