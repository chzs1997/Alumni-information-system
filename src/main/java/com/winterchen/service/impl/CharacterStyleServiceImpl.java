package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.CharacterStyleDao;
import com.winterchen.model.CharacterStyle;
import com.winterchen.service.CharacterStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :17:54 2019/3/9
 * @Modefied By:
 */
@Service(value = "CharacterStyleService")
public class CharacterStyleServiceImpl implements CharacterStyleService {

    @Autowired
    private CharacterStyleDao characterStyleDao;

    @Override
    public PageInfo<CharacterStyle> findCharacterData(int pageNum, int pageSize, int characterType) {
        //创建分页工具类
        PageHelper.startPage(pageNum, pageSize);
        List<CharacterStyle> docs = characterStyleDao.findCharacterData(characterType);
        PageInfo<CharacterStyle> pageInfo = new PageInfo<>(docs);
        return pageInfo;
    }

    @Override
    public CharacterStyle findCharacterById(int characterId) {
        return characterStyleDao.findCharacterById(characterId);
    }

    @Override
    public PageInfo<CharacterStyle> selectSimilar(int pageNum, int pageSize, int characterId) {
        //创建分页工具类
        PageHelper.startPage(pageNum, pageSize);
        List<CharacterStyle> docs = characterStyleDao.selectSimilar(characterId);
        PageInfo<CharacterStyle> pageInfo = new PageInfo<>(docs);
        return pageInfo;
    }

}
