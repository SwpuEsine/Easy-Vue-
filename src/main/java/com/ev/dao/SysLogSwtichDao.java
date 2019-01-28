package com.ev.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.ev.entity.SysLogSwtich;

@Mapper
public interface SysLogSwtichDao {
    int insert(@Param("pojo") SysLogSwtich pojo);

    int insertSelective(@Param("pojo") SysLogSwtich pojo);

    int insertList(@Param("pojos") List<SysLogSwtich> pojo);

    int update(@Param("pojo") SysLogSwtich pojo);

    int delete();

}
