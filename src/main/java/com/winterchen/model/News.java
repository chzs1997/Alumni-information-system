package com.winterchen.model;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:19 2019/3/19
 * @Modefied By:
 */
@Data
public class News {

    /*新闻id*/
    private Integer artId;

    /*管理员id*/
    private Integer userId;

    /*新闻类型*/
    private Integer artType;

    /*新闻发布时间*/
    private Date artTime;

    /*新闻标题*/
    private String artTitle;

    /*新闻摘要*/
    private String artSummary;

    /*新闻内容*/
    private String artContent;

    /*新闻图片*/
    private String artImage;

    /*新闻标签1*/
    private String artLabel1;

    /*新闻标签2*/
    private String artLabel2;

    /*新闻标签3*/
    private String artLabel3;

    /*新闻标签4*/
    private String artLabel4;

}
