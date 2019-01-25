package com.puhui8.entity;

import lombok.Data;

/**
 * 系统资源表
 *
 * @author
 * @create 2019-01-21 下午3:25
 **/
@Data
public class SysResource {
    private Long resourceId;
    private String resourceName;
    private Long order;//同级顺序
    private String iconName;
    private Boolean hidden;//是否隐藏
    private String path;
    private Long parentId;
    private Boolean isMenu;

}
