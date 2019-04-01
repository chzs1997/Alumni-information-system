package com.winterchen.util;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuzipan
 * @date 1/23/2019 7:10 PM
 */
public class WebSocketUtils {
    /*
    *
    * 模拟存储websocket session使用
    * */
    public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();

    public static void sendMessageAll(String message){
        LIVING_SESSIONS_CACHE.forEach((sessionId, session)->sendMessage(session, message));
    }

    /*
    * 发送给指定用户信息
    * @Param session用户session
    * @Param message 发送内容
    * */
    public static void sendMessage(Session session, String message){
        if(session==null){
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if(basic == null){
            return;
        }
        try{
           basic.sendText(message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
