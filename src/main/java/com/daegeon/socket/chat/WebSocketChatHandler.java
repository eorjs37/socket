package com.daegeon.socket.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws  Exception {
          log.info("payload {}",message.getPayload());


          for(WebSocketSession sess: sessions){
            sess.sendMessage(message);
          }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info(session + " 클라이언트 접속 해제");
        sessions.remove(session);
    }


}
