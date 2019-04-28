package com.ev.common;

import lombok.Data;

import java.util.List;

/**
 * 分页结果对象,这里以layui框架的table为标准
 *
 * @author wangfan
 * @date 2017-7-24 下午4:28:59
 */
@Data
public class PageList<T> {

    private int code; //状态码, 0表示成功

    private String msg;  //提示信息

    private long total; // 总数量, bootstrapTable是total

    private List<T> rows; // 当前数据, bootstrapTable是rows

    public PageList() {
    }

    public PageList(List<T> rows) {
        this.rows = rows;
        this.total = rows.size();
        this.code = 200;
        this.msg = "success";
    }

    public PageList(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
        this.code = 200;
        this.msg = "success";
    }
}
