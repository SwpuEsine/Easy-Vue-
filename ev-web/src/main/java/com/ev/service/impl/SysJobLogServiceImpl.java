package com.ev.service.impl;

import com.ev.entity.SysJobLog;
import com.ev.service.SysJobLogService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ev.dao.SysJobLogDao;

@Service("sysJobLogService")
public class SysJobLogServiceImpl implements SysJobLogService{

    @Resource
    private SysJobLogDao sysJobLogDao;

    public int insert(SysJobLog pojo){
        return sysJobLogDao.insert(pojo);
    }

    public int insertSelective(SysJobLog pojo){
        return sysJobLogDao.insertSelective(pojo);
    }

    public int insertList(List<SysJobLog> pojos){
        return sysJobLogDao.insertList(pojos);
    }

    public int update(SysJobLog pojo){
        return sysJobLogDao.update(pojo);
    }
}
