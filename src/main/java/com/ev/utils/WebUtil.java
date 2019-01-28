package com.ev.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @create 2019-01-22 下午4:37
 **/
public class WebUtil {
    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
