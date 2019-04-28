package com.ev.controller;

import com.ev.annoation.Action;
import com.ev.common.PageList;
import com.ev.eunm.SysLogModelType;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @create 2019-03-05 上午10:53
 **/
@RestController
@RequestMapping("/log")
@Action(modelName = SysLogModelType.LOG,description = "日志管理")
public class LogController {

  /*  @Autowired
    SysLogService sysLogService;*/

    @RequestMapping(value = "/ligt",method = RequestMethod.GET)
    @Action(description = "分页获取全部")
    public PageList<Object> getAllByPage(){

       /* if(esRecord){
            Page<EsLog> es = esLogService.findByConfition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new PageList<Object>();
        }else{
            Page<Log> log = logService.findByConfition(type, key, searchVo, PageUtil.initPage(pageVo));
            return new PageList<Object>();
        }*/
       return null;
    }
}
