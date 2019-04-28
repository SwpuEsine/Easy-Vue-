package com.ev.dao;

import com.ev.entity.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuartzJobDao {
    int insert(@Param("pojo") QuartzJob pojo);

    int insertSelective(@Param("pojo") QuartzJob pojo);

    int insertList(@Param("pojos") List<QuartzJob> pojo);

    int update(@Param("pojo") QuartzJob pojo);

    List<QuartzJob> findByJobStatusNot(@Param("notJobStatus")String notJobStatus);

    QuartzJob findByJobId(@Param("jobId")Long jobId);

}
