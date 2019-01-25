package com.puhui8.controller;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件
 *
 * @author
 * @create 2018-11-28 上午10:18
 **/
@RestController
@RequestMapping("/file")
public class UploadController {

    public FileInfo upload(MultipartFile file){
        System.out.println(file.getName());
        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());
        //file.transferTo();//这个可以传送

        //fastDFS服务     学习了解一下 记住
        return  null;
    }

    public void  downLoad(String id){


    }
}
