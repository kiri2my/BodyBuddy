package com.bodybuddy.hey.userClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.dao.KirimDao;
import com.bodybuddy.hey.service.KirimService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class AlarmHandler extends TextWebSocketHandler {//텍스트알림은 text ,데이터 주고받을땐 BinaryWebSocketHandler
	
	@Autowired
	KirimService ks;
	@Autowired
	KirimDao kDao;
	
	
	
	
	private static Logger logger = LoggerFactory.getLogger(AlarmHandler.class);
	
	//접속자 관리
	private static List<WebSocketSession> wSessionList = new ArrayList<WebSocketSession>();
	//private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<String,WebSocketSession> idAndSession = new HashMap<>();
	
	// 클라이언트와 연결 이후에 실행되는 메서드 //@OnOpen역할
	@Override
	public void afterConnectionEstablished(WebSocketSession wSession) throws Exception {
		logger.info("서버접속 : {} 연결됨", wSession.getId());
		logger.info("접속자 수 : "+wSessionList.size());
		wSessionList.add(wSession);
		
	}
		
	
	//클라<->서버간 상호작용 메소드 //@OnMessage 역할
	@Override
	protected void handleTextMessage(WebSocketSession wSession, TextMessage message) throws Exception {
		String jsonObj = new Gson().toJson(message);
		
		logger.info("jsonObj : "+jsonObj);
		logger.info("{}로 부터 {} 받음", wSession.getId(), message.getPayload());
		String htmlMsg=null;
		Map<String,String> ws=null;
		String rcvSessionId=null;
		String m_id=null;
		
		String conMsg = "       <a class='dropdown-item'>"
					  + "            <div class='item-content'>"
					  + "                <h6 class='font-weight-normal'>웹소켓에 접속되셨습니다 세션아이디 : "+wSession.getId()+"</h6>"
					  + "                <p class='font-weight-light small-text mb-0 text-muted'>"
					  + "                    방금 전"
					  + "                </p>"
					  + "            </div>"
					  + "        </a>";
		
		
		
		String sort = message.getPayload().substring(0, 4);
		System.out.println("SORT::::::::::::::::::"+sort);
		switch(sort) {
		case "CON1":
			System.out.println("::::::CON1::::::");
			m_id = message.getPayload().substring(4);
			System.out.println(m_id);
			idAndSession.put(m_id, wSession);
			wSession.sendMessage(new TextMessage(conMsg));
			
			//내가 받은 알람 모두 찍어내기
			//판매자건 누구건 접속했을때 알림내용 가져오기 //현재 수신자에게 몇개의 메세지가 와있는지 디비에서 검색함.
			htmlMsg = ks.alarmReceiveAll(m_id);
			wSession.sendMessage(new TextMessage(htmlMsg));
			break;
			
		case "PS01":
			System.out.println("::::::PS01::::::");
			String json = message.getPayload().substring(4);
			
			Map<String,String> m = new Gson().fromJson(json, 
					new TypeToken<Map<String,String>>(){}.getType());
			System.out.println("adcode::"+m.get("ad_code"));
			System.out.println("m_id::"+m.get("m_id"));
			
			htmlMsg = ks.alarmSelfPurch(m.get("ad_code"));//구매자가 자기구매알림 받기
			wSession.sendMessage(new TextMessage(htmlMsg));
					
			ws = ks.alarmSendPurch(m);//구매자가 판매자에게 구매알림보내기
			//접속중이라면 알림 보내고
			//비접속중이라면 테이블에 있는거 가져오게하기
			break;
		}
		
		
	
		
		
		
				//rcvSessionId = ks.getWSession(ws);//판매자 웹세션받기
				//htmlMsg = ks.alarmReceivePurch(ws);//판매자가 구매알림받기
				/*synchronized(wSessionList){
					for (WebSocketSession singleWSession : wSessionList) {
						if(singleWSession.getId().equals(rcvSessionId)){
							wSession=singleWSession; //판매자 웹세션의 아이디가 세션리스트에 있는놈중에서 걸린다면
							wSession.sendMessage(new TextMessage(htmlMsg));
						}
					}
				}*/
		
		/*synchronized(wSessionList){
			for (WebSocketSession singleWSession : wSessionList) {//모두한테 메세지 보내기
				singleWSession.sendMessage(new TextMessage(wSession.getId() + " : " + message.getPayload()));
				
				if(!singleWSession.equals(wSession)){//나 이외에 다른 놈들한테 처리할 내용
					//singleWSession.getBasicRemote().sendText(text);
				}
			}
		}*/
	}
	
	// 클라이언트와 연결을 끊었을 때 실행되는 메소드 //@OnClose역할
	@Override
	public void afterConnectionClosed(WebSocketSession wSession, CloseStatus status) throws Exception {
		wSessionList.remove(wSession);
		logger.info("{} 연결 끊김", wSession.getId());
		logger.info("접속자 수 : "+wSessionList.size());
		
	}	
		
	
		
}