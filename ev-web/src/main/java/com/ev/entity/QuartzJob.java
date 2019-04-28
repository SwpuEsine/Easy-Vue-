package com.ev.entity;

import com.ev.scheduler.JobParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2019-03-06 下午5:00
 **/
@Data
@Slf4j
public class QuartzJob implements Serializable{

    public static final String STATUS_RUNNING = "1";
    public static final String STATUS_NOT_RUNNING = "0";
    public static final String STATUS_DELETED = "2";
    public static final String CONCURRENT_IS = "1";
    public static final String CONCURRENT_NOT = "0";


    private Long jobId;
    /** 任务名称 */
    private String jobName;

    /** 任务分组，任务名称+组名称应该是唯一的 */
    private String jobGroup;

    /** 任务初始状态 0禁用 1启用 2删除 */
    private String jobStatus;

    /** 任务是否有状态（并发与否） */
    private String isConcurrent = "1";

    /** 任务运行时间表达式 */
    private String cronExpression;

    /** 任务描述 */
    private String description;

    /** 任务调用类名，包名+类名，通过类反射调用  */
    private String jobClass;

    /** 启动时间 */
    private Date startTime;

    /** 前一次运行时间 */
    private Date previousTime;

    /** 下次运行时间 */
    private Date nextTime;

    //参数字符串
    private String parameter;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + jobName.hashCode();
        hash = 31 * hash + jobGroup.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
        QuartzJob oBean = (QuartzJob) obj;
        if(this.jobName.equals(oBean.jobName) && this.jobGroup.equals(oBean.jobGroup)) {
            return true;
        }
        return false;
    }

    public List<JobParameter> getParameter() {
        List<JobParameter> jobParameterList=null;
        if (StringUtils.isNotEmpty(this.parameter)){
            ObjectMapper mapper=new ObjectMapper();
            try {
                jobParameterList= mapper.readValue(this.parameter, new TypeReference<List<JobParameter>>(){});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jobParameterList;
    }

  /*  public void setParameter(List<JobParameter> parameter) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            this.parameter = mapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            log.info("转化对象到json异常:"+e.getMessage());
            e.printStackTrace();
        }
    }*/
}
