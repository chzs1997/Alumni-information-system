package com.winterchen.dao;

import com.winterchen.model.LoginLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:03 2019/4/2
 * @Modefied By:
 */
@Repository
public interface LoginLogDao {

    //登陆日志存储
    int save(@Param("userId") Integer userId,@Param("userName") String userName);

    //最近登陆情况查询
    List<LoginLog> selectUsers();
}
