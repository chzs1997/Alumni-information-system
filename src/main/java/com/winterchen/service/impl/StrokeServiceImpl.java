package com.winterchen.service.impl;

import com.winterchen.dao.StrokeDao;
import com.winterchen.model.Stroke;
import com.winterchen.modelVO.UserStrokeVO;
import com.winterchen.service.StrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<Stroke> findStrokeByUserId(int userId) {
        return strokeDao.findStrokeByUserId(userId);
    }

    @Override
    public int strokeUpdateByuserId(int userId, Date schTime, String schPlace) {
        return strokeDao.strokeUpdateByuserId(userId, schTime, schPlace);
    }

    @Override
    public List<Stroke> findStrokeByUserIdAndState(int userId, int strokeState) {
        return strokeDao.findStrokeByUserIdAndState(userId,strokeState);
    }

    @Override
    public List<UserStrokeVO> selectAllUserStroke() {
        return strokeDao.selectAllUserStroke();
    }

    @Override
    public int addUserStrokeByManager(String userMail, String schTime, String schPlace, String schState) {
        int state;
        if(schState == "已处理"){
            state = 1;
        }
        else{
            state = 0;
        }
        return strokeDao.addUserStrokeByManager(userMail, schTime, schPlace, state);
    }

    @Override
    public List<UserStrokeVO> selectUserStrokeByUserMail(String userMail) {
        return strokeDao.selectUserStrokeByUserMail(userMail);
    }

    @Override
    public int editUserStrokeByschId(String schTime, String schPlace, String schState, int schId) {
        int state = 0;
        if(schState.equals("已处理")){
            state = 1;
        }
        else{
            state = 0;
        }
        return strokeDao.editUserStrokeByschId(schTime, schPlace, state, schId);
    }

    @Override
    public int deleteUserStrokeBySchId(int schId) {
        return strokeDao.deleteUserStrokeBySchId(schId);
    }
}
