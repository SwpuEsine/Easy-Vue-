package com.ev.service;

import com.ev.entity.SysJobLog;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.ev.dao.SysJobLogDao;


public interface SysJobLogService {

    public int insert(SysJobLog pojo);

    public int insertSelective(SysJobLog pojo);

    public int insertList(List<SysJobLog> pojos);

    public int update(SysJobLog pojo);
}
