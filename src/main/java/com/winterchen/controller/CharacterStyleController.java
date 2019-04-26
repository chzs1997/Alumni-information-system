package com.winterchen.controller;

import com.winterchen.model.CharacterStyle;
import com.winterchen.service.CharacterStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Author: liuzipan
 * @Description
 * @Date :10:36 2019/3/3
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "/CharacterStyle")
public class CharacterStyleController {

    @Autowired
    private CharacterStyleService characterStyleService;

    //抽取人物操作
    @ResponseBody
    @RequestMapping(value = "/withdraw")
    public Object withdraw(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                        int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                        int pageSize,
                           @RequestParam(name = "charcterType", defaultValue = "1")
                                        int characterType){
       return characterStyleService.findCharacterData(pageNum, pageSize, characterType);
    }

    //抽取热门人选
    @ResponseBody
    @RequestMapping(value = "/hotPerson")
    public Object hotPerson(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                   int pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "15")
                                   int pageSize,
                           @RequestParam(name = "charcterType", defaultValue = "1")
                                   int characterType){
        return characterStyleService.findHotCharacter(pageNum, pageSize, characterType);
    }

    //根据人物id跳转操作
    @ResponseBody
    @RequestMapping(value = "/jump")
    public Object jump(
            @RequestParam(name = "characterId") int characterId
    ){
        CharacterStyle characterStyle = characterStyleService.findCharacterById(characterId);
        HashMap<Object,Object> objectMap = new HashMap<>();
        objectMap.put("characterId",characterStyle.getCharacterId());
        objectMap.put("characterTitle",characterStyle.getCharacterTitle());
        objectMap.put("characterContent",characterStyle.getCharacterContent());
        objectMap.put("characterImg", characterStyle.getCharacterPicture());
        return objectMap;
    }

    //根据人物id查找相关老师
    @ResponseBody
    @RequestMapping(value = "/selectSimilar")
    public Object selectSimilar(
            @RequestParam(name = "characterId") int characterId
    ){
        return characterStyleService.selectSimilar(1,4,characterId);
    }
}
