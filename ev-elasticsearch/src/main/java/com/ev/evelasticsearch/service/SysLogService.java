package com.ev.evelasticsearch.service;

import com.ev.evelasticsearch.entity.RequestParam;
import com.ev.evelasticsearch.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2019-02-09 下午7:41
 **/
public interface SysLogService {
    SysLog save(SysLog sysLog);
    Page<SysLog> findByAllConditions(RequestParam requestParam, String userName, Date createTime, Boolean isException, String methodName, Pageable pageable);
}
