package com.winterchen.service.impl;

import com.winterchen.dao.StrokeDao;
import com.winterchen.model.Stroke;
import com.winterchen.service.StrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :18:14 2019/2/18
 * @Modefied By:
 */
@Service(value = "StrokeService")
public class StrokeServiceImpl implements StrokeService {
    @Autowired
    private StrokeDao strokeDao;

    @Override
    public int strokeKeep(int userId, Date schTime, String schPlace) {
        return strokeDao.strokeKeep(userId, schTime, schPlace);
    }

    @Override
    public Stroke strokeSelectByuserId(int userId) {
        return strokeDao.strokeSelectByuserId(userId);
    }

    @Override
    public int strokeUpdateByuserId(int userId, Date schTime, String schPlace) {
        return strokeDao.strokeUpdateByuserId(userId, schTime, schPlace);
    }
}
