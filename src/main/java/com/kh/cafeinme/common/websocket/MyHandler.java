package com.kh.cafeinme.common.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandler extends TextWebSocketHandler {
	   
  private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      sessions.add(session);
      super.afterConnectionEstablished(session);
      for(WebSocketSession s : sessions) {
	      s.getAttributes().keySet().stream().forEach(ele->{
	      	log.info("키:{}-속성:{}",ele,s.getAttributes().get(ele));
	      });
      }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
 			sessions.remove(session);
 			super.afterConnectionClosed(session, status);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
      super.handleTextMessage(session, message);
      sessions.forEach(webSocketSession -> {
          try {
              webSocketSession.sendMessage(message);
          } catch (IOException e) {
              log.error("Error occurred.", e);
          }
      });
  }
  public List<WebSocketSession> getWebSocketSession(){
  	return this.sessions;
  }
}