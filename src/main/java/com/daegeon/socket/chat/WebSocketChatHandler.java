package com.daegeon.socket.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private  final ObjectMapper mapper;

    //현재 연결된 세션들
    private final Set<WebSocketSession> sessions = new HashSet<>();


    //소켓 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session)  {
        log.info("{} 연결됨",session.getId());
        sessions.add(session);
    }

    // 소켓 통신 시 메세지의 전송을 다루는 부분
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)  {
          log.info("payload {}",message.getPayload());

    }

    private void sendMessageToChatRoom(ChatMessageDto chatMessageDto, Set<WebSocketSession> chatRoomSession){
        chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess,chatMessageDto));
    }

    public  <T> void sendMessage(WebSocketSession session,T message){
        try {
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
