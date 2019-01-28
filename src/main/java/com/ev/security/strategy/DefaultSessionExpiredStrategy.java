//package com.ev.security.strategy;
//
//import org.springframework.security.web.session.SessionInformationExpiredEvent;
//import org.springframework.security.web.session.SessionInformationExpiredStrategy;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
///**
// * @author
// * @create 2019-01-24 下午12:02
// **/
//public class DefaultSessionExpiredStrategy implements SessionInformationExpiredStrategy{
//    /**
//     * 被剔除记录信息
//     * @param event
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
//
//        String ip=event.getRequest().getRemoteAddr();//远程地址
//        System.out.println("被IP地址为"+ip+"的用户挤退");
//    }
//}
