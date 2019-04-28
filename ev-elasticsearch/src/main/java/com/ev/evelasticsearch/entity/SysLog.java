package com.ev.evelasticsearch.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "syslog", type = "opr_log")
@Data
public class SysLog {
    @Id
    private String sysLogId;
    @Field(type = FieldType.Keyword)
    private String userName;
    @Field(type = FieldType.Keyword,index = false)
    private String className;
    @Field(type = FieldType.Keyword)
    private String methodName;
    @Field(type = FieldType.Keyword,index = false)
    private String  parameters;
    @Field(type = FieldType.Text)
    private String url;
    @Field(type = FieldType.Date)
    private Date createTime;
    @Field(type = FieldType.Long)
    private Long  duration;//请求耗时  毫秒
    @Field(type = FieldType.Boolean)
    private Boolean isException;//是否异常
    @Field(type = FieldType.Boolean)
    private Boolean isProcess;//异常是否处理
    @Field(type = FieldType.Keyword,index = false)
    private String ipAddress;//请求ip

}
