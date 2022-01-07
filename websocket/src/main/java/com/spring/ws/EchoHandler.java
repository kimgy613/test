package com.spring.ws;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
public class EchoHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
     
      // 클라이언트와 연결 이후에 실행되는 메서드
      @Override
      public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        System.out.println("{} 연결됨"+ session.getId());
      }
     
      // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
      @Override
      protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(("{}로 부터 {} 받음"+ session.getId()+ message.getPayload()));
        for (WebSocketSession sess : sessionList) {
          sess.sendMessage(new TextMessage(session.getId() + " : " + message.getPayload()));
        }
      }
     
      // 클라이언트와 연결을 끊었을 때 실행되는 메소드
      @Override
      public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        System.out.println(("{} 연결 끊김"+ session.getId()));
      }
      
      /*
       * sessionList에는 들어오는 사람들의 세션들을 관리할 객체이고, 
       * 처음 /echo 매핑값으로 들어오면 처음 메소드가 실행되어 세션이 생성되고
       * List에 그걸 넣어줌 그 이후에 생성된 객체로 send 메소드를 호출하게되면
       * 2번째 객체가 실행된다. 여기서 원하는 방향으로 메시지를 뿌려줄 수 있고
       * 마지막 close 메소드를 호출하면 연결이 끊어진다.
       * */
}
