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

    //对新闻点赞
    int praiseAdd(@Param("souvenirId") int souvenirId
                 ,@Param("souvenirPraise") int souvenirPraise);

    //查找新闻点赞评论次数
    Souvenir selectCounts(int souvenirId);
}
