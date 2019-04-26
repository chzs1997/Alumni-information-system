package com.winterchen.controller;

import com.winterchen.dao.NewsDao;
import com.winterchen.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:32 2019/3/19
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController extends HttpServlet {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsDao newsDao;

    /**
     * 新闻上传具体实现方法;
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam("new_title") String artTitle,
                             @RequestParam("stage") String stage[],
                             @RequestParam("new_type") String type,
                             @RequestParam("new_content") String artContent
    ) {
        if (!file.isEmpty()) {
            String img_name = "";
            int artType = 0;
            //测算目前图片到哪个id
            int id = newsService.findArtId();
            System.out.println(id);
            switch (type) {
                case "学院新闻":
                    img_name = "new_1_"+(id+1)+".jpg";
                    artType  = 1;
                    break;
                case "校友新闻":
                    img_name = "new_2_"+(id+1)+".jpg";
                    artType  = 2;
                    break;
                case "通知公告":
                    img_name = "new_3_"+(id+1)+".jpg";
                    artType  = 3;
                    break;
                case "校友卡":
                    img_name = "new_4_"+(id+1)+".jpg";
                    artType  = 4;
                    break;
                case "校友返校活动":
                    img_name = "new_5_"+(id+1)+".jpg";
                    artType  = 5;
                    break;
            }
            try {
                String new_img_name = "F:\\springboot2-mybatis-demo2.0\\src\\main\\resources\\static\\static\\img\\news\\"+img_name;
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new_img_name));
                System.out.println(file.getName());
                out.write(file.getBytes());
                out.flush();
                out.close();
                String artImage = "static/static/img/news/"+img_name;
                int i = newsService.save(artType, artTitle, artContent, artImage, stage);
                System.out.println(i);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "success";

        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /*
    * 新闻加载初始化
    *
    * */
    @ResponseBody
    @PostMapping("/newsSeralize")
    public Object newsSeralize(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize,
            @RequestParam(name = "newsType") int newsType
    ){
        return newsService.findAllNews(pageNum, pageSize,newsType);
    }

    /*
     * 通知公告加载
     *
     * */
    @ResponseBody
    @PostMapping("/announcement")
    public Object announcement(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "4")
                    int pageSize
    ){
        return newsService.findAnnouncement(pageNum, pageSize);
    }

    /*
     * 指定内容加载
     *
     * */
    @ResponseBody
    @PostMapping("/newsDetailSeralize")
    public Object newsDetailSeralize(
            @RequestParam(name = "artId", required = false, defaultValue = "1")
                    int artId
    ){
        newsDao.updateViewCounts(artId);
        return newsService.findNewsByartId(artId);
    }

    /*
    *
    * 附加新闻加载
    * */
    @ResponseBody
    @PostMapping("/newsExtra")
    public Object newsExtra(
            @RequestParam(name = "artId", required = false, defaultValue = "1")
                       int artId
    ){
        return newsService.findNewsExtra(artId);
    }

    /*
     *
     * 相关新闻加载
     * */
    @ResponseBody
    @PostMapping("/newsRelation")
    public Object newsRelation(
            @RequestParam(name = "artId", required = false, defaultValue = "1")
                    int artId
    ){
        return newsService.findNewsRelation(artId);
    }

    /*
     *
     * 添加评论
     * */
    @ResponseBody
    @PostMapping("/addComments")
    public Object addComments(
            @RequestParam(name = "commentName") String commentName,
            @RequestParam(name = "commentMail") String commentMail,
            @RequestParam(name = "commentContent") String commentContent,
            @RequestParam(name = "artId") int artId
    ){
        return newsService.addComments(commentName, commentMail, commentContent, artId);
    }

    /*
     *
     * 初始化评论
     * */
    @ResponseBody
    @PostMapping("/commentsSeralize")
    public Object commentsSeralize(
            @RequestParam(name = "artId") int artId
    ){
        return newsService.commentsSeralize(artId);
    }

    /*
    *
    * 对评论点赞或者点踩
    *
    * */
    @ResponseBody
    @PostMapping("/addCounts")
    public Object addCounts(
            @RequestParam(name = "commentId") int commentId,
            @RequestParam(name = "commentPraise", required = false, defaultValue = "0") int  commentPraise,
            @RequestParam(name = "commentTread", required = false, defaultValue = "0") int commentTread
    ){
        newsService.addCounts(commentId,commentPraise,commentTread);
        return 1;
    }

    /*
     *
     * 对新闻点赞
     *
     * */
    @ResponseBody
    @PostMapping("/praiseAdd")
    public Object addCounts(
            @RequestParam(name = "artId") int artId,
            @RequestParam(name = "newsPraise", required = false, defaultValue = "0") int  newsPraise
    ){
        newsService.praiseAdd(artId,newsPraise);
        return newsService.selectCounts(artId);
    }

    /*
     *
     * 初始化评论点赞数量
     *
     * */
    @ResponseBody
    @PostMapping("/coutsSeralize")
    public Object addCounts(
            @RequestParam(name = "artId") int artId
    ){
        return newsService.selectCounts(artId);
    }



    /*
     *
     * 初始化评论
     * */
    @ResponseBody
    @PostMapping("/serviceListSeralize")
    public Object serviceListSeralize(@RequestParam(name = "artType") int artType){
        return newsService.findNewsByartType(artType);
    }

    /*
    *
    * 近一个月新闻浏览量统计
    * */
    @ResponseBody
    @RequestMapping(value = "/findNewsViewCountsLastMonth")
    public Object findNewsViewCountsLastMonth(){
        Map<Integer,Integer> hashMap =  newsService.findNewsViewCountsLastMonth();
        return hashMap;
    }

    /*
     *
     * 近一个月新闻浏览量统计
     * */
    @ResponseBody
    @RequestMapping(value = "/findNewsCommentCountsLastMonth")
    public Object findNewsCommentCountsLastMonth(){
        Map<Integer,Integer> hashMap =  newsService.findNewsCommentCountsLastMonth();
        return hashMap;
    }

}
