package com.ev.service.impl;

import com.ev.dao.SysLogSwtichDao;
import com.ev.entity.SysLogSwtich;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogSwtichService{

    @Resource
    private SysLogSwtichDao sysLogSwtichDao;

    public int insert(SysLogSwtich pojo){
        return sysLogSwtichDao.insert(pojo);
    }

    public int insertSelective(SysLogSwtich pojo){
        return sysLogSwtichDao.insertSelective(pojo);
    }

    public int insertList(List<SysLogSwtich> pojos){
        return sysLogSwtichDao.insertList(pojos);
    }

    public int update(SysLogSwtich pojo){
        return sysLogSwtichDao.update(pojo);
    }

    public int delete(){
        return sysLogSwtichDao.delete();
    }

}
