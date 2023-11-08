package com.satori.service.message.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Maps;
import com.satori.service.config.WebsocketApplicationContextAware;
import com.satori.service.enums.SendTypeEnum;
import com.satori.service.message.entity.UserMessage;
import com.satori.service.message.service.UserMessageService;
import jakarta.annotation.Resource;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @auth YanFuYou
 * @date 16/09/23 下午 11:32
 */
@Component
@ServerEndpoint("/websocket/message/{userId}")
@Slf4j
public class WebSocketServer {

    public static Integer onlineCount = 0;


    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<>();

    private Session session;

    private Long userId;


    @Resource
    @Lazy
    private UserMessageService userMessageService;

    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        onlineCount++;
        log.info("新建用户连接,会话id：{},连接者id：{}", session.getId(), userId);
        this.userId = userId;
        this.session = session;
        log.info("当前在线人数：{}", onlineCount);
        try {
            //1-上线 2-下线 3-在线列表 4-普通消息
            HashMap<Object, Object> map1 = Maps.newHashMap();
            map1.put("messageType", 1);
            map1.put("userId", userId);
            sendMessageAll(JSONObject.toJSONString(map1),userId);
            clients.put(userId.toString(),this);

            HashMap<Object, Object> map3 = Maps.newHashMap();
            map3.put("messageType", 3);
            Set<String> keySet = clients.keySet();
            //需要这换为用户昵称
            map3.put("onlineUsers", keySet);
            sendMessageTo(JSONObject.toJSONString(map3),userId);
            userMessageService.updateOnlineUsers(userId.toString());
        } catch (Exception e) {
            log.error("执行上线通知时发生错误",e);
        }

    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("服务器错误", throwable);
    }

    @OnClose
    public void onClose() {
        onlineCount --;
        try {
            WebSocketServer webSocketServer = clients.get(this.userId.toString());
            webSocketServer.session.close();
            clients.remove(this.userId.toString());
            HashMap<String, Object> map2 = Maps.newHashMap();
            map2.put("messageType",2);
            Set<String> keySet = clients.keySet();
            map2.put("onlineUsers",keySet);
            map2.put("userId",userId);
            sendMessageAll(JSONObject.toJSONString(map2),userId);

        }catch (Exception e){
            log.error("执行下线通知时发生错误",e);
        }
        log.info("用户下线：{},在线人数：{}",userId,onlineCount);
    }

    @OnMessage
    public void onMessage(String message,Session session){
        try {
            log.info("收到客户端消息：{},sessionId：{}",message,session.getId());
            JSONObject jsonObject = JSONObject.parseObject(message);
            String content = jsonObject.getString("content");
            Long userId = jsonObject.getLong("userId");
            Long receiverId = jsonObject.getLong("receiverId");
            Byte sendType = jsonObject.getByte("sendType");
            Long parentId = Optional.ofNullable(jsonObject.getLong("parentId")).orElse(0L);
            HashMap<String, Object> map4 = Maps.newHashMap();
            map4.put("messageType",4);
            map4.put("content",content);
            map4.put("senderId",userId);
            map4.put("receiverId",receiverId);
            map4.put("sendType",sendType);
            UserMessage userMessage = new UserMessage();
            userMessage.setSenderId(userId);
            userMessage.setReceiverId(receiverId);
            userMessage.setReceiverType(sendType);
            userMessage.setParentMessageId(parentId);
            userMessage.setMessageContent(content);
            userMessageService.save(userMessage);
            map4.put("id",userMessage.getId());
            if (SendTypeEnum.TO_USER.getValue().equals(sendType)){
                sendMessageTo(JSONObject.toJSONString(map4),receiverId);
            }else if (SendTypeEnum.TO_GROUP.getValue().equals(sendType)){
                sendMessageAll(JSONObject.toJSONString(map4),receiverId);
            }

        }catch (Exception e){
            log.error("消息发送失败",e);
        }
    }

    public void sendMessageTo(String content, Long receiverId) {
        int num = 0;
        for (WebSocketServer socket : clients.values()) {
            if (socket.userId.equals(receiverId) || socket.userId.equals(userId)){
                socket.session.getAsyncRemote().sendText(content);
                num ++;
                if (num >= 2){
                    break;
                }
            }
        }
    }

    public void sendMessageAll(String content, Long userId) {
        for (WebSocketServer socket : clients.values()) {
            socket.session.getAsyncRemote().sendText(content);
        }

    }

    public static synchronized int getonlineCount() {
        return onlineCount >= 0 ? onlineCount : 0;
    }
}
