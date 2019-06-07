package com.bodybuddy.hey.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.dao.KirimDao;
import com.bodybuddy.hey.dao.MemberDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class KirimService {

	@Autowired 
	KirimDao kDao;

	@Autowired
	private HttpSession session;
	ModelAndView mav;

	public ModelAndView access(Member mb) {
		mav=new ModelAndView();
		String view=null;
	
		BCryptPasswordEncoder pwdEncoder=new BCryptPasswordEncoder();
		//해당 아이디의 암호화된 비번을 가져옴
		String pwdEncode=kDao.getSecurityPwd(mb.getM_id());
		
			System.out.println("2="+pwdEncode);
		if(pwdEncode!=null) { //암호화된 비번이 존재한다면:아이디가 존재
			if(pwdEncoder.matches(mb.getM_pw(), pwdEncode)) {
				session.setAttribute("id", mb.getM_id());
				System.out.println("getmid="+mb.getM_id());
				//로그인 후 회원정보를 3종류로 나눠 화면에 출력하기 위해
				String kind = kDao.getMemberKind(mb.getM_id());
				System.out.println("kind="+kind);
				System.out.println("getid="+mb.getM_id());
				switch(kind) {
				case "n":
					mb = kDao.getNormalInfo(mb.getM_id());
					mav.addObject("kindSignal", "n");
					break;
				case "t":
					mb = kDao.getTrainerInfo(mb.getM_id());
					mav.addObject("kindSignal", "t");
					break;
				case "c":
					mb = kDao.getCompanyInfo(mb.getM_id());
					mav.addObject("kindSignal", "c");
					break;	
				}				
							
				mav.addObject("mb", mb);
				session.setAttribute("mb", mb);
				System.out.println("mb="+mb);
				//forward:url, POST-POST, GET-GET끼리만 가능
				//view="forward:/board";
				//redirect:url, POST-GET 둘다 GET방식만 가능
				view="forward:/";
			}else {//비번오류
				view="loginJoinFrm/loginFrm";
				mav.addObject("loginCheck", "비번오류");
			}
		}else {//아이디오류
			view="loginJoinFrm/loginFrm";
			mav.addObject("loginCheck", "아이디오류");
		}
		mav.setViewName(view);
		return mav;

	}

	public ModelAndView detailPage(String ad_code) {
		
		mav=new ModelAndView();
		String view=null;
		System.out.println("ad_code="+ad_code);
		Map<String,String> dp = kDao.detailPage(ad_code);
		List<OpCategory> opCateList=kDao.opCateList(ad_code);
		
		String html = makeHTMLDetailPage(dp,opCateList);
		view = "detailPage";
		mav.addObject("detailPageHTML", html);
		mav.setViewName(view);
		return mav;
	}
	
	public ModelAndView detailReview(String ad_code) {
		
		
		return null;
	}

	public ModelAndView detailQa(String ad_code) {
		
		
		return null;
	}
	
	
	private String makeHTMLReview() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"tab-pane fade show\" id=\"review\" role=\"tabpanel\" aria-labelledby=\"review-tab\">\r\n" + 
				"                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">\r\n" + 
				"\r\n" + 
				"                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">\r\n" + 
				"                                                    <li class=\"nav-item d-none d-lg-block w-100\">\r\n" + 
				"                                                        <p>총 _건의 후기가 있습니다.</p> 평점:\r\n" + 
				"                                                        <div class=\"table-responsive\">\r\n" + 
				"                                                            <table class=\"table table-hover table-condensed\">\r\n" + 
				"                                                                <thead>\r\n" + 
				"                                                                    <tr>\r\n" + 
				"                                                                        <th>별점</th>\r\n" + 
				"                                                                        <th>옵션</th>\r\n" + 
				"                                                                        <th>내용</th>\r\n" + 
				"                                                                        <th>작성일</th>\r\n" + 
				"                                                                    </tr>\r\n" + 
				"                                                                </thead>\r\n" + 
				"                                                                <tbody>\r\n" + 
				"                                                                    <tr>\r\n" + 
				"                                                                        <td>1.0</td>\r\n" + 
				"                                                                        <td>기간+시간+횟수+요일</td>\r\n" + 
				"                                                                        <td>인생 모두 부질없는것이다 <a href=\"#\"> 더보기<i class=\"mdi mdi-arrow-down\"></i></a></td>\r\n" + 
				"                                                                        <td>2018/04/23</td>\r\n" + 
				"                                                                    </tr>\r\n" + 
				"\r\n" + 
				"                                                                </tbody>\r\n" + 
				"                                                            </table>\r\n" + 
				"                                                        </div>\r\n" + 
				"                                                        <nav>\r\n" + 
				"                                                            <ul class=\"pagination pagination-lg\">\r\n" + 
				"                                                                <div class=\"btn-toolbar\" role=\"toolbar\" aria-label=\"Toolbar with button groups\">\r\n" + 
				"                                                                    <div class=\"btn-group\" role=\"group\" aria-label=\"First group\">\r\n" + 
				"                                                                        <li>\r\n" + 
				"                                                                            <a href=\"#\" aria-label=\"Previous\">\r\n" + 
				"                                                                                <span aria-hidden=\"true\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">«</button></span>\r\n" + 
				"                                                                            </a>\r\n" + 
				"                                                                        </li>\r\n" + 
				"                                                                        <li><a href=\"#\" class=\"active\"><button type=\"button\" class=\"btn btn-outline-secondary\">1</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">2</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">3</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">4</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">5</button></a></li>\r\n" + 
				"                                                                        <li>\r\n" + 
				"                                                                            <a href=\"#\" aria-label=\"Next\">\r\n" + 
				"                                                                                <span aria-hidden=\"true\"><button type=\"button\" class=\"btn btn-outline-secondary\">»</button></span>\r\n" + 
				"                                                                            </a>\r\n" + 
				"                                                                        </li>\r\n" + 
				"                                                                    </div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                            </ul>\r\n" + 
				"                                                        </nav>\r\n" + 
				"                                                    </li>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>");
		
		return sb.toString();
	}

	private String makeHTMLDetailPage(Map<String, String> dp, List<OpCategory> opCateList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"content-wrapper\">\r\n" + 
				"<br><br><br><br><br><br><br><br><br>                    <div class=\"row\">\r\n" + 
				
				"                        <div class=\"col-md-4 grid-margin stretch-card\">\r\n" + 
				"\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 600px;\">\r\n" + 
				"                                        <a href=\"#\" class=\"thumbnail\">\r\n" + 
				"                                            <img src='"+dp.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />\r\n" + 
				"                                        </a>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<form method='post' name='detailPageInfo'>"+
				"<input type=\"hidden\" id=\"ad_code\" name=\"ad_code\" value='"+dp.get("AD_CODE")+"'>"+
				"                                    <div class=\"caption\">\r\n" + 
				"                                        <h3 class=\"display-4\" style=\"text-align: center\">"+dp.get("AD_TITLE")+"<br><br>\r\n" + 
				"                                            <small class=\"text-muted\">");
		//일반 회원 모집일 경우 :자기주소
		if(dp.get("AD_KIND")=="mo") {
			sb.append(dp.get("M_ADDR"));
		}
		//프로그램일 경우 
		if(dp.get("AD_KIND")=="p") {
			//개인 트레일경우 : 자기주소
			if(dp.get("T_CID")==null) {
				sb.append(dp.get("M_ADDR"));
			}
			//소속 트레이너일 경우 : 소속업체주소
			if(dp.get("T_CID")!=null) {
				sb.append(dp.get("T_CID_ADDR"));
			}
			
		}
		
		
		
		sb.append("</small>\r\n" + 
				"                                        </h3>\r\n" + 
				"                                        <br>\r\n" + 
				"                                        <h5>");
		for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 프로그램 옵션 카테고리 찍어주기
			sb.append(opCateList.get(i).getOp_category()+"/");
		 }
		
		
		sb.append("</h5>\r\n" + 
				"                                        <br>\r\n" + 
				"\r\n" + 
				"                                        <p>\r\n" + 
				"                                            <select id='optionSelect' class=\"form-control\">\r\n" + 
				"                                                <option>상세 옵션을 선택해주세요</option>\r\n");
		for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 옵션명 찍어주기
			sb.append("<option id='op"+opCateList.get(i).getOp_code()+"'>"+opCateList.get(i).getOp_name()+"</option>\r\n");	
		}
		
		for(int i=0;i<opCateList.size();i++) {//옵션코드 리스트, 옵션가격 리스트 뽑아내기
			sb.append("<input type=\"hidden\" id='"+opCateList.get(i).getOp_code()+"' value='"+opCateList.get(i).getOp_price()+"'/>");	
		}
		 
				
		sb.append(
				
				
				"\r\n" + 
				"                                            </select>\r\n" + 
				"                                        </p>\r\n" + 
				"                                        <div class=\"row\">\r\n" + 
				"                                            <div class=\"col-md-7\">\r\n" + 
				"                                                <blockquote class=\"blockquote blockquote-primary\">\r\n" + 
				"                                                    <footer class=\"blockquote-footer\">프로모션 및 가격 할인행사</footer>\r\n" + 
				"                                                    <p>PT100회 한정수량 골라잡기 이벤트</p>\r\n" + 
				"                                                </blockquote>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <div class=\"col-md-4\">\r\n" + 
				"                                                <blockquote  id='priceShow' class=\"blockquote blockquote-primary\">" + 
				"                                                    <h3><small class=\"text-muted\">가격: </small>0원</h3>" +
				"                                                </blockquote>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                        \r\n" + 
				"                                        \r\n" + 
				"                                        \r\n" + 
				"                                        \r\n" + 
				"\r\n" + 
				"                                        <div style=\"text-align: center\">\r\n" + 
				"                                        <button type=\"button\" class=\"btn btn-outline-secondary dropdown-toggle\" data-toggle=\"dropdown\">(담당 트레이너 프로필 보기)</button>\r\n" + 
				"                                        <div class=\"dropdown-menu\">\r\n");
				for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 트레이너 + 담당 카테고리 찍어주기
					//if(!opCateList.get(i).getOp_trainer().equals(opCateList.get(i+1).getOp_trainer())) {
						sb.append("<a class='dropdown-item'>"+opCateList.get(i).getM_name()+"  "+opCateList.get(i).getOp_category()+"</a>\r\n");
					//}
				}
	  sb.append("                            </div>\r\n" + 
				"                                                   <a href='"+"/dibsadd?ad_code="+dp.get("AD_CODE")+"' class=\"btn btn-default\" role=\"button\"><button type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">\r\n" + 
				"                                                    <i class=\"mdi mdi-heart-outline text-danger\"></i>\r\n" + 
				"                                                </button></a><button id='purchase' class=\"btn btn-primary\" role=\"button\">구매</button> </div>\r\n" + 
				"</form>"+
				"                                    </div>\r\n" + 
				"\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                        </div>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-9 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body dashboard-tabs p-0\">\r\n" + 
				"                                    <ul class=\"nav nav-tabs px-4 nav-justified\" role=\"tablist\">\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link active\" id=\"detailInfo-tab\" data-toggle=\"tab\" href=\"#detailInfo\" role=\"tab\" aria-controls=\"detailInfo\" aria-selected=\"true\" style=\"border-bottom-color : #71c016; color: #71c016\">상세 정보 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link\" id=\"review-tab\" data-toggle=\"tab\" role=\"tab\" aria-controls=\"review\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">별점 및 후기 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link\" id=\"question-tab\" data-toggle=\"tab\" role=\"tab\" aria-controls=\"question\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">문의 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                                    </ul>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                                    <div class=\"tab-content py-0 px-0\">\r\n" + 
				
				//상세정보 시작
				
				"                                        <div class=\"tab-pane fade show active\" id=\"detailInfo\" role=\"tabpanel\" aria-labelledby=\"detailInfo-tab\">\r\n" + 
				"                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">\r\n" + 
				"\r\n" + 
				"                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">\r\n" + 
				"                                                    <li class=\"nav-item d-none d-lg-block w-100\">\r\n" + 
				dp.get("AD_CONTENT")+
				"                                                        \r\n" + 
				"\r\n" + 
				"                                                    </li>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				//상세정보끝
				
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"                </div>");
		return sb.toString();
	}


	
	

	/*
	 * public String purchSingle(PaymentHistory ph) { String str="";
	 * ph.setPs_mid((String)session.getAttribute("id"));
	 * 
	 * if(kDao.payInsert(ph)) { Gson gson = new Gson(); String
	 * jsonStr=gson.toJson(str); str="결제성공"; System.out.println("payinsert 성공");
	 * 
	 * }else { System.out.println("payinsert 실패"); str="결제실패"; }
	 * 
	 * 
	 * return str;
	 * 
	 * }
	 */

}
