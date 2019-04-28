package com.ev.service.impl;

import com.ev.common.PageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author
 * @create 2019-02-15 下午6:24
 **/
public class PageBaseServiceImpl<T> {


    protected PageList<T> findAllByPageParams(int pageCode, int pageSize, Supplier<?> s) {
        PageHelper.startPage(pageCode, pageSize);
        PageList<T> pageInfo = new PageList<T>((List) s.get());
        PageHelper.clearPage();
        return pageInfo;
    }
}
