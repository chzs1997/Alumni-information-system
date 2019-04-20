package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.Souvenir;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :21:40 2019/4/19
 * @Modefied By:
 */
@Service
public interface SouvenirService {

    //初始化加载新闻
    PageInfo<Souvenir> findAllSouvenir(int pageNum, int pageSize);

    //对新闻点赞
    int praiseAdd(int souvenirId, int souvenirPraise);

    //查找新闻点赞评论次数
    Object selectCounts(int souvenirId);
}
