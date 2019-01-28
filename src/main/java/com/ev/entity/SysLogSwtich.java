package com.ev.entity;

import lombok.Data;

/**
 * @author
 * @create 2019-01-27 上午11:25
 **/
@Data
public class SysLogSwtich {
    private Long  switchId;
    private String modelName;
    private String className;
    private String methodName;
    private String Descrption;
    private Boolean isEnable;
}
