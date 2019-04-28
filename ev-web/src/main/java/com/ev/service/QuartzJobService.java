package com.ev.service;

import com.ev.common.PageList;
import com.ev.entity.QuartzJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuartzJobService {
    public int insert(QuartzJob pojo);

    public int insertSelective(QuartzJob pojo);

    public int insertList(List<QuartzJob> pojos);

    public int update(QuartzJob pojo);

    PageList<QuartzJob> findByJobStatusNot(int page, int limit, String notJobStatus);

    QuartzJob findByJobId(Long jobId);
}
