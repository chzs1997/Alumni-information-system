package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.CharacterStyle;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :10:44 2019/3/3
 * @Modefied By:
 */
@Service
public interface CharacterStyleService {

    /*查询最新人物风采数据*/
    PageInfo<CharacterStyle> findCharacterData(int pageNum, int pageSize, int characterType);

    /*查询最新人物风采数据*/
    PageInfo<CharacterStyle> findHotCharacter(int pageNum, int pageSize, int characterType);

    /*根据用户ID查找信息*/
    CharacterStyle findCharacterById(int characterId);

    /*根据人物ID寻找类似老师*/
    PageInfo<CharacterStyle> selectSimilar(int pageNum, int pageSize, int characterType);
}
