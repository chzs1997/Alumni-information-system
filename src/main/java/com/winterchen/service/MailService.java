package com.winterchen.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :11:59 2019/4/11
 * @Modefied By:
 */
@Service
public interface MailService {

    //发送简单邮件
    void sendSimpleMail(String to, String tile, String content);

    //发送带附件的邮件
    void sendAttachmentsMail(String to, String title, String content, List<File> fileList);
}
