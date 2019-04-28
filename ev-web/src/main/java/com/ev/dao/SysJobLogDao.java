package com.ev.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.ev.entity.SysJobLog;

@Mapper
public interface SysJobLogDao {
    int insert(@Param("pojo") SysJobLog pojo);

    int insertSelective(@Param("pojo") SysJobLog pojo);

    int insertList(@Param("pojos") List<SysJobLog> pojo);

    int update(@Param("pojo") SysJobLog pojo);
}
