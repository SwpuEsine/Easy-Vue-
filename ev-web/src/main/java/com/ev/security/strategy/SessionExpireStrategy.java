package com.ev.security.strategy;

import com.ev.common.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2019-02-15 上午9:59
 **/
public class SessionExpireStrategy implements SessionInformationExpiredStrategy{

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
            JsonResult result=JsonResult.unAuthorized("并发登录");
            ObjectMapper objectMapper=new ObjectMapper();
            String responseMessage=objectMapper.writeValueAsString(result);
            HttpServletResponse response = event.getResponse();
            response.setContentType("application/json;chartset=UTF-8");
            response.getWriter().write(responseMessage);
    }
}
