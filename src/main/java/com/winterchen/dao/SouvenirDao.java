package com.winterchen.dao;

import com.winterchen.model.Souvenir;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :18:42 2019/4/19
 * @Modefied By:
 */
@Repository
public interface SouvenirDao {

    List<Souvenir> selectAllSouvenir();

    /**
     *
     * 对纪念品点赞/
     */
    int praiseAdd(@Param("souvenirId") int souvenirId
                 ,@Param("souvenirPraise") int souvenirPraise);

    /***
     *
     *  查找纪念品点赞评论次数
     */
    Souvenir selectCounts(int souvenirId);

    /**
     * 查找最后一个纪念品ID
     * *
     */
    int findSouvenirId();

    /**
     *
     * 插入一条新的纪念品信息
     * */
    int insSouvenir(@Param("souvenirName") String souvenirName
                    ,@Param("souvenirImage") String souvenirImage
                    ,@Param("souvenirTime") String souvenirTime
                    , @Param("souvenirMeaning") String souvenirMeaning);
}
