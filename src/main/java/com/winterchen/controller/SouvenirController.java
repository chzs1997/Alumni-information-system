package com.winterchen.controller;

import com.winterchen.service.SouvenirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: liuzipan
 * @Description 纪念品问题
 * @Date :19:00 2019/4/19
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "souvenir")
public class SouvenirController {
    @Autowired
    private SouvenirService souvenirService;

    /**
     * 纪念品加载初始化
     *
     * */
    @ResponseBody
    @PostMapping("/souvenirSerialize")
    public Object newsSeralize(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize
    ){
        return souvenirService.findAllSouvenir(pageNum, pageSize);
    }

    /**
     *
     * 对新闻点赞
     *
     * */
    @ResponseBody
    @PostMapping("/praiseAdd")
    public Object addCounts(
            @RequestParam(name = "souvenirId") int souvenirId,
            @RequestParam(name = "souvenirPraise", required = false, defaultValue = "0") int  souvenirPraise
    ){
        souvenirService.praiseAdd(souvenirId,souvenirPraise);
        return souvenirService.selectCounts(souvenirId);
    }

}
