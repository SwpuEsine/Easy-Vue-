package com.puhui8.security;

import com.puhui8.system.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Base64;

/**
 * @author
 * @create 2018-12-19 上午11:42
 **/
@Slf4j
public class Md5PasswordEncoder implements PasswordEncoder {
    /**
     * 注册时使用该方法加密
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        log.info("原始密码是:"+rawPassword);
        return MD5Util.MD5(rawPassword.toString());
    }

    /**
     *
     * @param rawPassword  表单提交
     * @param encodedPassword  数据库读取的密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.info("登录");
        log.info("原始密码是:"+rawPassword);
        log.info("获取的加密密码是:"+encodedPassword);
        log.info("自己加密的密码是："+encode(rawPassword));
        return encode(rawPassword).equals(encodedPassword);
    }
}
