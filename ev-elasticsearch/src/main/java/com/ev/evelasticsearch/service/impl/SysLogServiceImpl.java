package com.ev.evelasticsearch.service.impl;

import com.ev.evelasticsearch.entity.RequestParam;
import com.ev.evelasticsearch.entity.SysLog;
import com.ev.evelasticsearch.repository.SysLogRepository;
import com.ev.evelasticsearch.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author
 * @create 2019-02-09 下午7:43
 **/
@Service
public class SysLogServiceImpl implements SysLogService{

    @Autowired
    private SysLogRepository repository;
    @Override
    public SysLog save(SysLog sysLog) {
        return repository.save(sysLog);
    }

    @Override
    public Page<SysLog> findByAllConditions(RequestParam requestParam,String userName, Date createTime, Boolean isException, String methodName, Pageable pageable) {

        if(StringUtils.isEmpty(userName)&&StringUtils.isEmpty(methodName)&&null!=createTime&&null!=isException){
            // 无过滤条件获取全部
            return repository.findAll(pageable);
        }/*else if(){
            // 仅有type
            return repository.findAllById();
        }*/
        return null;
    }


}
