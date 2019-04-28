package com.ev.controller;

import com.ev.annoation.Action;
import com.ev.common.JsonResult;
import com.ev.common.PageList;
import com.ev.entity.*;
import com.ev.eunm.SysLogModelType;
import com.ev.service.*;
import com.ev.vo.RoleRelUserVo;
import com.ev.vo.RoleResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @create 2019-01-26 下午12:11
 **/
@RestController
@RequestMapping("/role")
@Action(modelName = SysLogModelType.ROLE,description = "角色管理")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    SysResourceService sysResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    RedisService redisService;

    @GetMapping(value = "/selectList")
    public JsonResult<List> getSelectRoles(){
       return JsonResult.ok(sysRoleService.findRoleIdAndRemark());
    }

    @GetMapping(value = "/list")
    @Action(description = "查看角色")
    public PageList<RoleRelUserVo> getResources(){
        List<RoleRelUserVo> relUserVoList=new ArrayList<>();
        List<SysRole> roles = sysRoleService.find();
        for (SysRole role:roles) {
            relUserVoList.add(getRoleRevUser(role));
        }
        PageList<RoleRelUserVo> pageList=new PageList<>(relUserVoList.size(),relUserVoList);
        return pageList;
    }

    @PostMapping(value = "/edit")
    @Action(description = "修改角色")
    public JsonResult edit(@RequestBody SysRole sysRole){
        sysRoleService.update(sysRole);
        return JsonResult.ok("修改成功");
    }

    @PostMapping(value = "/add")
    @Action(description = "添加角色")
    public JsonResult add(@RequestBody SysRole sysRole){
        sysRoleService.insertSelective(sysRole);
        return JsonResult.ok("添加成功");
    }

    @PostMapping(value = "/delete")
    @Action(description = "删除角色")
    public JsonResult delete(Long id){
        //删除橘色
        sysRoleService.deleteByRoleId(id);
        //删除角色拥有的资源
        sysRoleResourceService.deleteBYRoleId(id);
        //删除角色用户对应表
        sysUserRoleService.deleteByRoleId(id);
        return JsonResult.ok("删除成功!");
    }

    @GetMapping(value = "/info")
    @Action(description = "获取角色详细信息")
    public JsonResult<SysRole> info(Long roleId){
        SysRole sysRole = sysRoleService.findByRoleId(roleId);
        return JsonResult.ok(sysRole);
    }

    public RoleRelUserVo getRoleRevUser(SysRole role) {
        RoleRelUserVo relUserVo=new RoleRelUserVo();
        List<SysUser> userList = sysUserRoleService.findUserListByRoleId(role.getRoleId());
        relUserVo.setUserList(userList);
        relUserVo.setRole(role);
        return relUserVo;
    }
    @GetMapping(value = "/resIds")
    @Action(description = "获取角色拥有的资源id")
    public JsonResult<List> getResourcesIds(Long roleId){
        //半节点
        List<Long> resids = sysRoleResourceService.findChildrenResIdByRoleId(roleId);
        //如果没有子节点 要返回
        return JsonResult.ok(resids);
    }

    @PostMapping(value = "/resIds")
    @Action(description = "更新角色权限")
    public JsonResult<String> setRoleAuthorities(@RequestBody RoleResVo vo){
        Long roleId = vo.getRoleId();
        sysRoleResourceService.deleteBYRoleId(roleId);
        List<Long> resIds = vo.getResIds();
        List<SysRoleResource> sysRoleResources=new ArrayList<>();
        for (Long resId : resIds) {
            SysRoleResource sysRoleResource=new SysRoleResource();
            sysRoleResource.setRoleId(roleId);
            sysRoleResource.setResId(resId);
            sysRoleResources.add(sysRoleResource);
        }
        sysRoleResourceService.insertList(sysRoleResources);
        //更新redis缓存数据

        String s =sysRoleService.findByRoleId(roleId).getRoleName();
        List<String> rolePaths = sysResourceService.findPathByResourceName(s);
        if (rolePaths!=null && !rolePaths.isEmpty()){
            redisService.setRoleToPaths(s,rolePaths);
        }
        return JsonResult.ok("权限设置成功");
    }

    @GetMapping(value = "/menu/list")
    @Action(description = "获取角色拥有的菜单")
    public JsonResult<List> getRgetResourcesesources(Long roleId){
        List<SysResource> sysResources=new ArrayList<>();
        if (roleId!=null){
            sysResources=sysResourceService.findByRoleId(roleId);
        }else {
            sysResources = sysResourceService.findAll();
        }
        ;
        List<Map<String, Object>> menuList = getMenuList(sysResources, -1L);
        return JsonResult.ok(menuList);
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
                map.put("children",getMenuList(menus,sysResource.getResourceId()));
                menuList.add(map);
            }
        }
        return menuList;
    }

}
