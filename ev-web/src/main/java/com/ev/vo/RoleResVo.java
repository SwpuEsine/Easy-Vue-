package com.ev.vo;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @create 2019-03-04 上午10:42
 **/
@Data
public class RoleResVo {
    private Long roleId;
    //角色拥有的资源集合
    private List<Long> resIds;
}
