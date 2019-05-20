package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description
 * @Date :14:52 2019/5/18
 * @Modefied By:
 */
@Data
public class NewsVO {

    /*校友姓名*/
    private String  characterName;

    /*校友介绍*/
    private String characterIntroduction;

    /*新闻标题*/
    private String artTitle;

    /*新闻图片*/
    private String artImage;

    /*新闻id*/
    private String artId;
}
