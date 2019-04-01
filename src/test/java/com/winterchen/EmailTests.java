package com.winterchen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: liuzipan
 * @Description
 * @Date :14:40 2019/2/18
 * @Modefied By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTests {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    //读取配置文件中的参数
    @Value("${spring.mail.username}")
    private String sender;
    private static final String recipient = "936696076@qq.com";

    /*
    * 发送简单文本邮件
    *
    * */
    @Test
    public void sendSimpleEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者
        message.setFrom(sender);

        //接收者
        message.setTo(recipient);

        //邮件主题
        message.setSubject("主题:文本邮件");

        //邮件内容
        message.setText("骚扰邮件勿回");
        javaMailSender.send(message);
    }

    /*
    * 新增发送html邮件测试方法
    * */
    @Test
    public void sendHtmlEmail(){
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject("主题:HTML邮件");
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style = 'color:#F00'>红色字</p>")
                    .append("<p style = 'text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
