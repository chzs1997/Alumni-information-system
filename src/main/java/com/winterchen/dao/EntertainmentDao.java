package com.winterchen.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: liuzipan
 * @Description
 * @Date :10:26 2019/5/1
 * @Modefied By:
 */
@Repository
public interface EntertainmentDao {

    /**
     * 查找最后一条娱乐ID
     * */
    int findEntertainmentId();

    /**
     *
     * 插入一条周边食宿信息
     * */
    int insEntertainment(@Param("entertainmentType") int entertainmentType
                        ,@Param("entertainmentName") String entertainmentName
                        ,@Param("entertainmentAddress") String entertainmentAddress
                        ,@Param("entertainmentContact") String entertainmentContact
                        ,@Param("entertainmentImg") String entertainmentImg
                        ,@Param("entertainmentIntroduction") String entertainmentIntroduction);
}
