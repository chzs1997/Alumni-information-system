package com.winterchen.service;

import com.winterchen.model.Stroke;
import com.winterchen.modelVO.UserStrokeVO;
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

    /**
     *
     * 查询所有用户行程
     * */

    List<UserStrokeVO> selectAllUserStroke();

    /**
     *
     * 插入一条新的行程
     * */
    int addUserStrokeByManager(String userMail, String schTime, String schPlace, String schState);

    /**
     *
     * 查询插入的最后一条行程
     * */
    List<UserStrokeVO> selectUserStrokeByUserMail(String userMail);

    /**
     *
     * 更新一条指定行程
     * */
    int editUserStrokeByschId(String schTime, String schPlace, String schState, int schId);


    /**
     *
     * 删除一条指定行程
     * */
    int deleteUserStrokeBySchId(int schId);
}
