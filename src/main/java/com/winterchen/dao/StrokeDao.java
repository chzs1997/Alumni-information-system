package com.winterchen.dao;

import com.winterchen.model.Stroke;
import com.winterchen.modelVO.UserStrokeVO;
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

    /**
     *
     * 查询所有用户行程
     * */

    List<UserStrokeVO> selectAllUserStroke();

    /**
     *
     * 插入一条新的行程
     * */
    int addUserStrokeByManager(@Param("userMail") String userMail
                              ,@Param("schTime") String schTime
                              ,@Param("schPlace") String schPlace
                              ,@Param("schState") int schState);

    /**
     *
     * 查询插入的最后一条行程
     * */
    List<UserStrokeVO> selectUserStrokeByUserMail(@Param("userMail") String userMail);

    /**
     *
     * 更新一条指定行程
     * */
    int editUserStrokeByschId(@Param("schTime") String schTime
                             ,@Param("schPlace") String schPlace
                             ,@Param("schState") int schState
                             ,@Param("schId") int schId);


    /**
     *
     * 删除一条指定行程
     * */
    int deleteUserStrokeBySchId(@Param("schId") int schId);
}
