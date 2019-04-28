package com.ev.service.impl;

import com.ev.common.SecurityPrefix;
import com.ev.service.RedisService;
import com.ev.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author
 * @create 2019-02-15 下午4:45
 **/
@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Long setRoleToPaths(String roleName, List<String> paths) {
        redisUtil.lTrim(SecurityPrefix.AUTHORTITY_PREFIX+roleName,0,0);
        return redisUtil.lRightPushAll(SecurityPrefix.AUTHORTITY_PREFIX+roleName,paths);
    }

    @Override
    public boolean flushAll() {
        Set<String> keys = redisUtil.keys("*");
        return redisUtil.delete(keys)==0?false:true;
    }
}
