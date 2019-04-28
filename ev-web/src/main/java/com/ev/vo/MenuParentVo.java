package com.ev.vo;

import com.ev.entity.SysResource;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @create 2019-03-02 下午3:06
 **/
@Data
public class MenuParentVo {
    private SysResource menu;
    private List<SysResource> menuList;
}
