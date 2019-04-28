package com.ev.vo;

import com.ev.entity.SysRole;
import com.ev.entity.SysUser;
import com.ev.entity.SysUserRole;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @create 2019-03-03 上午10:33
 **/
@Data
public class RoleRelUserVo {
    private List<SysUser> userList;
    private SysRole role;
}
