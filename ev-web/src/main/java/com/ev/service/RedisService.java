package com.ev.service;

import com.ev.common.SecurityPrefix;

import java.util.List;

/**
 * @author
 * @create 2019-02-15 下午4:42
 **/
public interface RedisService {
    //设置角色 权限路径映射
    Long setRoleToPaths(String roleName, List<String>paths);

    boolean flushAll();
}
