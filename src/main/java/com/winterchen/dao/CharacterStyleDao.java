package com.winterchen.dao;

import com.winterchen.model.CharacterStyle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :17:55 2019/3/9
 * @Modefied By:
 */
public interface CharacterStyleDao {
    /*查询最新人物风采数据*/
    List<CharacterStyle> findCharacterData(@Param("characterType") int characterType);

    /*根据人物ID查询人物信息*/
    CharacterStyle findCharacterById(@Param("characterId") int characterId);

    /*根据人物Id查找相关老师*/
    List<CharacterStyle> selectSimilar(@Param("characterId") int characterId);
}
