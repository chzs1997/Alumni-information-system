package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.LoginLog;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:33 2019/4/2
 * @Modefied By:
 */
@Service
public interface LoginLogService {


    //登陆日志存储
    int save(Integer userId,String userName);

    //最近登陆情况查询
    PageInfo<LoginLog> selectLog(int pageNum,int pageSize);

    //错误次数加1
    LoginLog addFailure(String userName);

    //登陆日志存储
    int insert(Integer userId,String userName);

    //登录日志中记录新用户
    int addNewUser(String userMail);
}
