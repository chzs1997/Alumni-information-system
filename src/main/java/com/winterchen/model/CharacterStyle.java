package com.winterchen.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :17:47 2019/3/9
 * @Modefied By:
 */
@Data
public class CharacterStyle {

    /*人物id*/
    private Integer characterId;

    /*人物类型*/
    private Integer characterType;

    /*人物姓名*/
    private String characterName;

    /*人物发布时间*/
    private Date characterTime;

    /*人物图片*/
    private String characterPicture;

    /*人物简介*/
    private String characterIntroduction;

    /*人物介绍标题*/
    private String characterTitle;

    /*人物内容*/
    private String characterContent;

}
