package com.ev.service.impl;

import com.ev.common.PageList;
import com.ev.dao.QuartzJobDao;
import com.ev.entity.QuartzJob;
import com.ev.entity.SysUser;
import com.ev.service.QuartzJobService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("quartzJobService")
public class QuartzJobServiceImpl extends PageBaseServiceImpl<QuartzJob>  implements QuartzJobService{

    @Resource
    private QuartzJobDao quartzJobDao;

    public int insert(QuartzJob pojo){
        return quartzJobDao.insert(pojo);
    }

    public int insertSelective(QuartzJob pojo){
        return quartzJobDao.insertSelective(pojo);
    }

    public int insertList(List<QuartzJob> pojos){
        return quartzJobDao.insertList(pojos);
    }

    public int update(QuartzJob pojo){
        return quartzJobDao.update(pojo);
    }

    @Override
    public PageList<QuartzJob> findByJobStatusNot(int page, int limit, String notJobStatus) {
        return super.findAllByPageParams(page,limit,()->quartzJobDao.findByJobStatusNot(notJobStatus));
    }

    @Override
    public QuartzJob findByJobId(Long jobId) {
        return quartzJobDao.findByJobId(jobId);
    }


}
