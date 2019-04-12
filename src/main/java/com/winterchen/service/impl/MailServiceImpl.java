package com.winterchen.service.impl;

import com.winterchen.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :12:00 2019/4/11
 * @Modefied By:
 */
@Service(value = "MailService")
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendSimpleMail(String to, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        logger.info("邮件发送成功");
    }

    @Override
    public void sendAttachmentsMail(String to, String title, String content, List<File> fileList) {
       MimeMessage message = mailSender.createMimeMessage();
       try{
           MimeMessageHelper helper = new MimeMessageHelper(message,true);
           helper.setFrom(from);
           helper.setTo(to);
           helper.setSubject(title);
           helper.setText(content);
           String fileName = null;
           for(File file:fileList){
               fileName = MimeUtility.encodeText(file.getName(),"GB2313","B");
               helper.addAttachment(fileName,file);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       mailSender.send(message);
       logger.info("邮件发送成功");
    }
}
