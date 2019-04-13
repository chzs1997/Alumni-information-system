package com.winterchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:54 2019/4/12
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "/chat")
public class WsController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleChat(Principal principal, String msg){
        String destUser = msg.substring(msg.lastIndexOf(";")+1,msg.length());
        String message = msg.substring(0, msg.lastIndexOf(";"));
//        messagingTemplate.convertAndSendToUser(destUser,"/queue/chat",new ChatResp(message, principal.getName()));
    }
}
