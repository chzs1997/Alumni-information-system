package com.winterchen.dao;

import com.winterchen.model.Stroke;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description 行程
 * @Date :17:49 2019/2/18
 * @Modefied By:
 */
@Repository
public interface StrokeDao {
    /**
     *
     * 行程信息存储
     * */
    int strokeKeep(int userId, Date schTime, String schPlace);

    /**
     *
     * 根据用户ID对行程信息查询
     * */

    List<Stroke> findStrokeByUserId(@Param("userId") int userId);

    /**
     *
     * 根据用户Id修改行程信息
     * */
    int strokeUpdateByuserId(int userId, Date schTime, String schPlace);

    /**
     * 根据用户ID+行程状态查找行程信息
     * */
    List<Stroke> findStrokeByUserIdAndState(@Param("userId") int userId,
                                            @Param("strokeState") int strokeState);
}
