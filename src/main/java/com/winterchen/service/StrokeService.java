package com.winterchen.service;

import com.winterchen.model.Stroke;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :18:10 2019/2/18
 * @Modefied By:
 */
@Service
public interface StrokeService {

    /**
     *
    * 行程信息存储
    * */
    int strokeKeep(int userId, Date schTime, String schPlace);

    /**
     *
    * 根据用户ID对行程信息查询
    * */
    List<Stroke> findStrokeByUserId(int userId);

    /**
     *
    * 根据用户Id修改行程信息
    * */
    int strokeUpdateByuserId(int userId, Date schTime, String schPlace);

    /**
     * 根据用户ID+行程状态查找行程信息
     * */
    List<Stroke> findStrokeByUserIdAndState(int userId, int strokeState);
}
