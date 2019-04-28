package com.ev.controller;

import com.ev.annoation.Action;
import com.ev.common.JsonResult;
import com.ev.eunm.SysLogModelType;
import com.ev.entity.SysResource;
import com.ev.service.*;
import com.ev.vo.MenuParentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author
 * @create 2019-03-02 上午10:29
 **/
@RestController
@RequestMapping("/menu")
@Action(modelName = SysLogModelType.MENU,description = "菜单管理")
public class MenuController extends BaseController{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysResourceService sysResourceService;

    @Autowired
    SysUserRoleService sysUserRoleService;


    @GetMapping(value = "/list")
    @Action(description = "查看菜单列表")
    public JsonResult<List> getRgetResourcesesources(Long roleId){
        List<SysResource> sysResources=new ArrayList<>();
        if (roleId!=null){
            sysResources=sysResourceService.findByRoleId(roleId);
        }else {
            sysResources = sysResourceService.findAll();
        }
        ;
        List<Map<String, Object>> menuList = getMenuList(sysResources, -1L);
        menuList.sort(Comparator.comparingLong(m -> (Long) m.get("order")));
        return JsonResult.ok(menuList);
    }

    @GetMapping(value = "/info")
    @Action(description = "查询菜单详情")
    public JsonResult<MenuParentVo> getResourceInfo(Long id){
        SysResource menuInfo = sysResourceService.findByResourceId(id);
        //一级菜单
        List<SysResource> leve1Menus = sysResourceService.findByIsMenu(true);
        MenuParentVo menuParentVo=new MenuParentVo();
        menuParentVo.setMenu(menuInfo);
        menuParentVo.setMenuList(leve1Menus);
        return JsonResult.ok(menuParentVo);
    }

    @GetMapping(value = "/selectList")
    @Action(description = "查看所有菜单",isEnable = false)
    public JsonResult<List> getSelectRoles(){
        return JsonResult.ok(sysResourceService.findByIsMenu(true));
    }

    @PostMapping(value = "/edit")
    @Action(description = "编辑菜单")
    public JsonResult edit(@RequestBody SysResource sysResource){
        if (sysResource.getParentId()==null ||sysResource.getParentId()==0){
            sysResource.setParentId(-1L);
        }
        sysResourceService.update(sysResource);
        return JsonResult.ok("修改成功");
    }


    @PostMapping(value = "/add")
    @Action(description = "添加菜单")
    public JsonResult add(@RequestBody SysResource sysResource){
        sysResourceService.insertSelective(sysResource);
        return JsonResult.ok("添加成功");
    }


    @PostMapping(value = "/delete")
    @Action(description = "删除菜单")
    public JsonResult delete(Long id){
        sysResourceService.deleteBYResourceIdOrParentId(id);
        return JsonResult.ok("删除成功!");
    }

    public List<Map<String,Object>>  getMenuList(List<SysResource> menus,Long parentId){
        List<Map<String,Object>>  menuList=new ArrayList<>();
        for (SysResource sysResource:menus) {
            if (sysResource.getParentId()==parentId){
                Map<String, Object> map = new HashMap<>();
                map.put("id",sysResource.getResourceId());
                map.put("name", sysResource.getResourceName());
                map.put("path", sysResource.getPath());
                map.put("order", sysResource.getOrder());
                map.put("icon",sysResource.getIconName());
                map.put("parentId",sysResource.getParentId());
                map.put("isMenu",sysResource.getIsMenu());
                List<Map<String, Object>> childrenList = getMenuList(menus, sysResource.getResourceId());
                childrenList.sort(Comparator.comparingLong(m -> (Long) m.get("order")));
                map.put("children",childrenList);
                menuList.add(map);
            }
        }
        return menuList;
    }
}
