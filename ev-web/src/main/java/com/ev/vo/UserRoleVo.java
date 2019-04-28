package com.ev.vo;

import com.ev.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @create 2019-02-25 下午3:35
 **/
@Data
public class UserRoleVo {
    private SysUser user;
    private List<Long> rolesList;
}
