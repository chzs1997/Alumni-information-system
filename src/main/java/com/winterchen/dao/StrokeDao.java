package com.winterchen.dao;

import com.winterchen.model.Stroke;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description 行程
 * @Date :17:49 2019/2/18
 * @Modefied By:
 */
public interface StrokeDao {
    /*
     * 行程信息存储
     * */
    int strokeKeep(int userId, Date schTime, String schPlace);

    /*
     * 根据用户ID对行程信息查询
     * */
    Stroke strokeSelectByuserId(int userId);

    /*
     * 根据用户Id修改行程信息
     * */
    int strokeUpdateByuserId(int userId, Date schTime, String schPlace);
}
