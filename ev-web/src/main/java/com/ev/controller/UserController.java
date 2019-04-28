package com.ev.controller;



/*
*
 * @author
 * @create 2018-11-29 上午10:23
 *
*/

import com.ev.annoation.Action;
import com.ev.common.JsonResult;
import com.ev.common.PageList;
import com.ev.entity.*;
import com.ev.eunm.SysLogModelType;
import com.ev.service.SysRoleService;
import com.ev.service.SysUserRoleService;
import com.ev.service.SysUserService;
import com.ev.service.impl.SysResourceServiceImpl;
import com.ev.vo.RequestParam;
import com.ev.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/user")
@Action(modelName = SysLogModelType.USER)
public class UserController extends BaseController{

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysResourceServiceImpl sysResourceService;

    @Autowired
    SysUserRoleService sysUserRoleService;


    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping(value = "/userdetail")
    public Object getUser(@AuthenticationPrincipal UserDetails userDetails){
        //里面包含了用户信息
        return userDetails;
    }

    @GetMapping(value = "info")
    @Action(description = "获取用户详细信息")
    public HashMap<String,Object> getUserRoleInfo(Long userId){
        HashMap<String,Object> m=new HashMap<String,Object>();
        UserRoleVo vo=new UserRoleVo();
        SysUser sysUser = sysUserService.findbyUserId(userId);
        sysUser.setPassWord(null);
        List<Long> roleids = sysUserService.getUserRoleIds(sysUser.getUserId());
        vo.setUser(sysUser);
        vo.setRolesList(roleids);
        m.put("form",vo);
        List<SysRole> roleList = sysRoleService.findRoleIdAndRemark();
        m.put("roles",roleList);
        m.put("code",200);
        return m;
    }

    @RequestMapping("/assets/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {

    }

    @PostMapping("/regist")
    @Action(description = "注册用户")
    public JsonResult register(@Valid SysUser sysUser, BindingResult errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            //自定义的返回，并将错误信息返回
            return JsonResult.error(message);
        }
        String password=passwordEncoder.encode(sysUser.getPassWord());
        sysUser.setPassWord(password);
        sysUser.setIsLock((short)0);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setIsExpire((short)0);
        sysUserService.insert(sysUser);
        return JsonResult.ok("注册成功");
    }

    @GetMapping(value = "/list")
    @Action(description = "获取用户列表")
    public PageList<SysUser> findListByAll(
            RequestParam requestParam,
            String userName,String phone){

        return sysUserService.findByUsernameAndPhone(requestParam.getPage(),requestParam.getLimit(),userName,phone);
    }

    /*@PostMapping(value = "/add")
    public JsonResult add(SysUser user, String[] rolesList){
         System.out.println(user);
         System.out.println(rolesList);
         sysUserService.insert(user);
         return JsonResult.ok("添加成功");
    }*/

    @PostMapping(value = "/add")
    @Action(description = "添加用户")
    public JsonResult add(@RequestBody UserRoleVo vo){
        SysUser user = vo.getUser();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        sysUserService.insertSelective(user);
        List<Long> roleIds = vo.getRolesList();
        List<SysUserRole> roleList=new ArrayList<>();
        for (Long roleId:roleIds) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(user.getUserId());
            sysUserRole.setRoleId(roleId);
            roleList.add(sysUserRole);
        }
        sysUserRoleService.insertList(roleList);
        return JsonResult.ok("添加成功");
    }


    @PostMapping(value = "/edit")
    @Action(description = "修改用户")
    public JsonResult edit(@RequestBody UserRoleVo vo){
        SysUser user = vo.getUser();
        user.setUpdateTime(new Date());
        sysUserService.update(user);
        List<Long> roleIds = vo.getRolesList();
        List<SysUserRole> roleList=new ArrayList<>();
        for (Long roleId:roleIds) {
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(user.getUserId());
            sysUserRole.setRoleId(roleId);
            roleList.add(sysUserRole);
        }
        sysUserRoleService.deleteByUserId(user.getUserId());
        sysUserRoleService.insertList(roleList);
        return JsonResult.ok("修改成功");
    }

    @PostMapping(value = "/delete")
    @Action(description = "删除用户")
    public JsonResult delete(String userIds){
        String[] split = userIds.split(",");
        List userIdList=Arrays.asList(split);
        //删除用户
        sysUserService.deleteByUserIdIn(userIdList);
        //角色
        sysUserRoleService.deleteByuserIdIn(userIdList);
        //
        return JsonResult.ok("删除成功!");
    }

    @GetMapping(value = "/menuList")
    @Action(description = "获取用户菜单列表")
    public Map<String,Object> getUserResources(HttpSession session){
        SysUser sysUser=getCurrentUser(session);
        List<Long> roleids = sysUserService.getUserRoleIds(sysUser.getUserId());
        List<SysResource> sysResources = sysResourceService.findAllByRoleIdsIn(roleids);
        Map<String,Object> result=new HashMap<>();
        List<Map<String, Object>> menuList = getMenuList(sysResources, -1L);
        result.put("code",200);
        result.put("data",menuList);
        return result;
    }

    public List<Map<String,Object>>  getMenuList(List<SysResource> menus,Long parentId){
        List<Map<String,Object>>  menuList=new ArrayList<>();
        for (SysResource sysResource:menus) {
            if (sysResource.getParentId()==parentId && sysResource.getIsMenu()){
                Map<String, Object> map = new HashMap<>();
                map.put("name", sysResource.getResourceName());
                map.put("url", sysResource.getPath());
                map.put("index", sysResource.getOrder());
                map.put("icon",sysResource.getIconName());
                map.put("hidden",sysResource.getHidden());
                map.put("subMenus",getMenuList(menus,sysResource.getResourceId()));
                menuList.add(map);
            }
        }
        return menuList;
    }
}
