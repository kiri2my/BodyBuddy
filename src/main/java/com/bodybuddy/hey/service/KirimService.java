package com.bodybuddy.hey.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.bean.Qna;
import com.bodybuddy.hey.bean.Review;
import com.bodybuddy.hey.dao.KirimDao;
import com.bodybuddy.hey.dao.MemberDao;
import com.bodybuddy.hey.dao.YoonDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
@SessionAttributes({ "mb" }) // mb라는 키로 저장된 attribute는 세션객체에 저장 됨
public class KirimService {

	@Autowired
	KirimDao kDao;
	@Autowired
	YoonDao yDao;
	@Autowired
	MemberDao mDao;
	@Autowired
	HttpSession session;

	ModelAndView mav;

	public ModelAndView access(Member mb) {
		mav = new ModelAndView();
		String view = null;
		System.out.println("사부작");
		String checkId =  kDao.deleteRealIdCheck(mb.getM_id());
		System.out.println("aa" + checkId);
		if (checkId != null)  {
			mav.setViewName("loginJoinFrm/loginFrm");
			mav.addObject("loginCheck", "이미탈퇴한아이디입니다");
			return mav;
		}

		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		// 해당 아이디의 암호화된 비번을 가져옴
		String pwdEncode = kDao.getSecurityPwd(mb.getM_id());
		if (pwdEncode != null) { // 암호화된 비번이 존재한다면:아이디가 존재
			if (pwdEncoder.matches(mb.getM_pw(), pwdEncode)) {
				// 로그인 후 회원정보를 3종류로 나눠 화면에 출력하기 위해
				String kind = kDao.getMemberKind(mb.getM_id());
				System.out.println("kind=" + kind);
				System.out.println("getid=" + mb.getM_id());
				switch (kind) {
				case "n":
					System.out.println("N 타입 회원");
					mb = kDao.getNormalInfo(mb.getM_id());
					break;
				case "t":
					System.out.println("T 타입 회원");
					mb = kDao.getTrainerInfo(mb.getM_id());
					break;
				case "c":
					System.out.println("C 타입 회원");
					mb = kDao.getCompanyInfo(mb.getM_id());
					break;
				}
				session.setAttribute("mb", mb);
				//mav.addObject("mb", mb);// @SessionAttributes때문에 세션영역에 mb저장됨
				// forward:url, POST-POST, GET-GET끼리만 가능
				// view="forward:/board";
				// redirect:url, POST-GET 둘다 GET방식만 가능
				view = "forward:/";
			}else {// 비번오류
				System.out.println("5252 비번이 틀렸다고");
				view = "loginJoinFrm/loginFrm";
				mav.addObject("loginCheck", "비번오류");
			}
		}else {// 아이디오류
			System.out.println("5252 아이디가 틀렸다고");
			view = "loginJoinFrm/loginFrm";
			mav.addObject("loginCheck", "아이디오류");
		}
		mav.setViewName(view);
		return mav;

	}

	public String dibsAdd(String d_adcode) {
		String suc = "<button id='" + "dibsDelete" + d_adcode
				+ "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart\"></i></button>";
		String err = "<button id='" + "dibsAdd" + d_adcode
				+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart-outline text-danger\"></i></button>";
		String html = null;

		System.out.println("d_adcode=============" + d_adcode);
		System.out.println(session.getId());
		Member sessionMb = (Member) session.getAttribute("mb");
		if (sessionMb == null) {// 비회원 찜 추가
			session.setAttribute("tempDibs" + d_adcode, "dibs");
			html = suc;// "일시적으로 찜 목록에 추가되었습니다.";

		} else if (sessionMb != null) {// 회원 찜 추가
			String sessionId = sessionMb.getM_id();
			Map<String, String> dibs = new HashMap<>();
			dibs.put("d_adcode", d_adcode);
			dibs.put("d_id", sessionId);
			if (kDao.dibsAdd(dibs)) {
				html = suc; // sessionId+"님의 찜 목록에 추가되었습니다";
			}
			html = err; // sessionId+"님의 찜 목록에 추가실패";
		}
		System.out.println("비회원장바구니 세션등록키===" + session.getAttribute("tempDibs" + d_adcode));
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println("찜추가완료NAMES=" + names.nextElement());
		}
		return html;
	}

	public String dibsDelete(String d_adcode) {
		String err = "<button id='" + "dibsDelete" + d_adcode
				+ "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart\"></i></button>";
		String suc = "<button id='" + "dibsAdd" + d_adcode
				+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart-outline text-danger\"></i></button>";
		String html = null;

		
		System.out.println("2d_adcode=============" + d_adcode);
		System.out.println(session.getId());
		Member sessionMb = (Member) session.getAttribute("mb");
		if (sessionMb == null) {// 비회원 찜 제거
			session.setAttribute("tempDibs" + d_adcode, null);
			html = suc;// "해당 광고가 찜 목록에서 제거되었습니다.";
		} else if (sessionMb != null) {// 회원 찜 제거
			String sessionId = sessionMb.getM_id();
			Map<String, String> dibs = new HashMap<>();
			dibs.put("d_adcode", d_adcode);
			dibs.put("d_id", sessionId);
			if (kDao.dibsDelete(dibs)) {
				html="success";
				//html = suc;// "님의 찜 목록에서 제거되었습니다";
			}
			//html = err;// "님의 찜 목록에서 제거실패";
		}
		System.out.println("222비회원장바구니 세션등록키===" + session.getAttribute("tempDibs" + d_adcode));
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println("찜취소완료NAMES=" + names.nextElement());
		}
		return html;
	}
	public ModelAndView profilePage(String m_id) {
		mav = new ModelAndView();
		Member mb=null;
		String html = null;
		String view = null;
		String kind = kDao.getMemberKind(m_id);
		if(kind.equals("n")) {
			mb=kDao.getNormalInfo(m_id);
			html = makeHTMLNormalProfile(mb);
		}else if(kind.equals("t")) {
			HashMap<String,String> pt=kDao.getTrainerProfile(m_id);
			List<HashMap<String, String>> pto=kDao.getTrainerProfileOption(m_id);
			html = makeHTMLTrainerProfile(pt, pto);
		}else if(kind.equals("c")) {
			HashMap<String,String> pc=kDao.getCompanyProfile(m_id);
			List<HashMap<String, String>> pco=kDao.getCompanyProfileOption(m_id);
			html = makeHTMLCompanyProfile(pc,pco);
		}
		mav.addObject("profileHtml", html);
		mav.setViewName(view);
		return mav;
	}

	
	
	
	
	
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	private String makeHTMLCompanyProfile(HashMap<String, String> pc, List<HashMap<String, String>> pco) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"main-panel\" style=\"width: 100%\">\r\n" + 
				"                <div class=\"content-wrapper\">\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-4 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 600px;\">\r\n" + 
				"                                        <a href=\"#\" class=\"thumbnail\">\r\n" + 
				"                                            <img src='"+pc.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />\r\n" + 
				"                                        </a>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <div class=\"caption\">\r\n" + 
				"                                        <h2 class=\"text-primary\" style=\"text-align: center\">"+pc.get("C_BNAME")+"</h2>\r\n" + 
				"                                        <h3><small class=\"text-muted\" >"+pc.get("M_ADDR")+"</small></h3>\r\n" + 
				"                                        <br>\r\n" + 
				"                                        <h4 class=\"display-4\">Phone : "+pc.get("C_BPHONE")+"</h4>\r\n" + 
				"                                        <h4 class=\"display-4\">Representative : "+pc.get("M_NAME")+"</h4>\r\n" + 
				"                                        <h4 class=\"display-4\">Business Number : "+pc.get("C_NUM")+"</h4>\r\n" + 
				"                                        <br>\r\n" + 
				"                                        <address class=\"text-primary\">\r\n" + 
				"                                            <p class=\"font-weight-bold\">E-mail</p><p class=\"mb-2\">"+pc.get("M_ID")+"</p>"+ 
				"                                            <p class=\"font-weight-bold\">Personal-phone</p><p class=\"mb-2\">"+pc.get("M_PHONE")+"</p>"+ 
				"                                        </address>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-9 stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">업체 등록 광고 보기</p>\r\n" + 
				"                                    <div class=\"table-responsive\">\r\n" + 
				"                                        <div id=\"company-advertise-listing_wrapper\" class=\"dataTables_wrapper container-fluid dt-bootstrap4 no-footer\">\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12\">\r\n" + 
				"                                                    <table id=\"company-advertise-listing\" class=\"table dataTable no-footer\" role=\"grid\">\r\n" + 
				"                                                        <thead>\r\n" + 
				"                                                            <tr role=\"row\">\r\n" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Gross amount: activate to sort column ascending\" style=\"width: 101.364px;\">광고명</th>\r\n" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date: activate to sort column ascending\" style=\"width: 67.7273px;\">등록 날짜</th>\r\n" + 
				"                                                                <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Name: activate to sort column descending\" style=\"width: 105px;\">진행 상태</th>\r\n" + 
				"                                                            </tr>\r\n" + 
				"                                                        </thead>\r\n" + 
				"                                                        <tbody>\r\n");
		
				for(int i=0;i<pco.size();i++) {
					sb.append("                                                     <tr role=\"row\" class=\"odd\">\r\n" + 
							"                                                         <td class=\"sorting_1\">"+pco.get(i).get("AD_TITLE")+"</td>\r\n" + 
							"                                                         <td>"+pco.get(i).get("AD_DATE")+"</td>");
							//success-초록색 : 완료	info-보라색 : 모집중	danger-빨간색 : 만료됨		
							if(pco.get(i).get("AD_STATUS").equals("진행중")) {
								sb.append("<td><label class=\"badge badge-warning\">");
							}else if(pco.get(i).get("AD_STATUS").equals("완료")) {
								sb.append("<td><label class=\"badge badge-success\">");
							}else if(pco.get(i).get("AD_STATUS").equals("모집중")) {
								sb.append("<td><label class=\"badge badge-info\">");
							}else if(pco.get(i).get("AD_STATUS").equals("만료됨")) {
								sb.append("<td><label class=\"badge badge-danger\">");
							} 
							sb.append(pco.get(i).get("AD_STATUS")+"</label></td>\r\n"+ 
							"                                                       </tr>" );
				}
				
	  sb.append("                                                        </tbody>\r\n" + 
				"                                                    </table>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-5\"></div>\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-7\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n" + 
				
				"            </div>");		
		return sb.toString();
	}

	private String makeHTMLTrainerProfile(HashMap<String,String> pt, List<HashMap<String, String>> pto) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"main-panel\" style=\"width: 100%\">\r\n" + 
				"                <div class=\"content-wrapper\">\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-4 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 600px;\">\r\n" + 
				"                                        <a href=\"#\" class=\"thumbnail\">\r\n" + 
				"                                            <img src='"+pt.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />\r\n" + 
				"                                        </a>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <div class=\"caption\">\r\n" + 
				"                                        <h3 class=\"text-primary\" style=\"text-align: center\">"+pt.get("M_NAME")+" 트레이너 프로필<br><br>\r\n" + 
				"                                            <small class=\"text-muted\" style=\"text-align: right\">소속업체 : "+pt.get("C_BNAME")+"</small>\r\n" + 
				"                                        </h3>\r\n" + 
				"                                        <br>\r\n" + 
				"                                        <h4>");
		for(int i=0;i<pto.size();i++) {
			sb.append(pto.get(i).get("OP_CATEGORY")+"/");
		}
	  sb.append("										 </h4><br>\r\n" + 
				"                                        <h4>주요 이력: <p class=\"display-4\">");
	  	String[] career = pt.get("T_CAREER").split(" ");//,쉼표로 자를것
	  	for(int i=0;i<career.length;i++) {
	  		sb.append(career[i]+"<br>");
	  	}
	  sb.append("										 </h4>\r\n" + 
				"                                        <br>\r\n" + 
				"                                        <address class=\"text-primary\">" + 
				"                                            <p class=\"font-weight-bold\">E-mail</p><p class=\"mb-2\">" +pt.get("M_ID")+"</p>" + 
				"                                        </address>" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-9 stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">이 트레이너의 프로그램 히스토리</p>\r\n" + 
				"                                    <div class=\"table-responsive\">\r\n" + 
				"                                        <div id=\"trainer-program-listing_wrapper\" class=\"dataTables_wrapper container-fluid dt-bootstrap4 no-footer\">\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12\">\r\n" + 
				"                                                    <table id=\"trainer-program-listing\" class=\"table dataTable no-footer\" role=\"grid\">\r\n" + 
				"                                                        <thead>\r\n" + 
				"                                                            <tr role=\"row\">\r\n" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Gross amount: activate to sort column ascending\" style=\"width: 101.364px;\">프로그램명(옵션)</th>\r\n" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Status report: activate to sort column ascending\" style=\"width: 144.091px;\">진행 장소</th>\r\n" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date: activate to sort column ascending\" style=\"width: 67.7273px;\">진행 기간</th>\r\n" + 
				"                                                                <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Name: activate to sort column descending\" style=\"width: 105px;\">프로그램 상태</th>\r\n" + 
				"                                                                \r\n" + 
				"                                                            </tr>\r\n" + 
				"                                                        </thead>\r\n" + 
				"                                                        <tbody>\r\n");
		for(int i=0;i<pto.size();i++) {
			sb.append("                                                            <tr role=\"row\"  class=\"odd\">" + 
					"                                                                <td class=\"sorting_1\">"+pto.get(i).get("AD_TITLE")+"("+pto.get(i).get("OP_NAME")+")"+"</td>"+
					"																 <td>"+pto.get(i).get("AD_ADDR")+" "+pto.get(i).get("C_BNAME")+"</td>"+	
					"                                                                <td>"+pto.get(i).get("OP_PERIOD")+"</td>");
			//success-초록색 : 완료	info-보라색 : 모집중	danger-빨간색 : 만료됨		
			if(pto.get(i).get("AD_STATUS").equals("진행중")) {
				sb.append("<td><label class=\"badge badge-warning\">");
			}else if(pto.get(i).get("AD_STATUS").equals("완료")) {
				sb.append("<td><label class=\"badge badge-success\">");
			}else if(pto.get(i).get("AD_STATUS").equals("모집중")) {
				sb.append("<td><label class=\"badge badge-info\">");
			}else if(pto.get(i).get("AD_STATUS").equals("만료됨")) {
				sb.append("<td><label class=\"badge badge-danger\">");
			} 
			sb.append(pto.get(i).get("AD_STATUS")+"</label></td>\r\n" + 
					"                                                            </tr>");
		}//for END
	  sb.append(
				"                                                        </tbody>\r\n" + 
				"                                                    </table>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                            <div class=\"row\">\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-5\"></div>\r\n" + 
				"                                                <div class=\"col-sm-12 col-md-7\"></div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                </div>\r\n"+
  			
			  	"			</div>\r\n");
		return sb.toString();
	}

	private String makeHTMLNormalProfile(Member mb) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"row w-100 mx-0\">\r\n" + 
				"          <div class=\"col-lg-4 mx-auto\">\r\n" + 
				"            <div class=\"auth-form-light text-left py-5 px-4 px-sm-5\">\r\n" + 
				"              <div class=\"brand-logo\">\r\n" + 
				"                <a class=\"navbar-brand brand-logo\" href=\"/\" style=\"color: #71c016;\">일반회원 프로필</a>\r\n" + 
				"              </div><br>" + 
				"    <div>\r\n" + 
				"        <div class=\"img_wrap\">\r\n" + 
				"            <img id=\"img\" src='"+mb.getPf_image()+"' />\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" + 
				"              <form class=\"pt-3\">\r\n" + 
				"                <div class=\"form-group\">\r\n" + 
				"                  <div class=\"input-group\">\r\n" + 
				"                    <div class=\"input-group-prepend bg-transparent\">\r\n" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">\r\n" + 
				"                        <i class=\"mdi mdi-email-outline text-primary\"></i>\r\n" + 
				"                      </span>\r\n" + 
				"                    </div>\r\n" + 
				"                    <input type=\"email\" name=\"m_id\" class=\"form-control form-control-lg border-left-0\" value='"+mb.getM_id()+"' disabled>\r\n" +
				"                  </div>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"form-group\">\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"form-group\">\r\n" + 
				"                    <div class=\"input-group\">\r\n" + 
				"                    <div class=\"input-group-prepend bg-transparent\">\r\n" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">\r\n" + 
				"                        <i class=\"mdi mdi-account-outline text-primary\"></i>\r\n" + 
				"                      </span>\r\n" + 
				"                    </div>\r\n" + 
				"                    <input type=\"text\"  name=\"m_name\" class=\"form-control form-control-lg border-left-0\"value='"+mb.getM_name()+"' disabled>\r\n" + 
				"                  </div>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"form-group\">\r\n" + 
				"                  <div class=\"input-group\">\r\n" + 
				"                    <div class=\"input-group-prepend bg-transparent\">\r\n" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">\r\n" + 
				"                        <i class=\"mdi mdi-cellphone text-primary\"></i>\r\n" + 
				"                      </span>\r\n" + 
				"                    </div>\r\n" + 
				"                    <input type=\"tel\"  name=\"m_phone\" class=\"form-control form-control-lg border-left-0\" value=\"전화번호: "+mb.getM_phone()+"\">\r\n" + 
				"                  </div>\r\n" + 
				"                </div> \r\n" + 
				"                <div class=\"form-group\">\r\n" + 
				"                  <div class=\"input-group\">\r\n" + 
				"                    <input type=\"text\"  name=\"m_birth\" class=\"form-control form-control-lg border-left-0\" value=\"생년월일 :"+mb.getM_birth()+"\"  disabled>\r\n" + 
				"                  </div>\r\n" + 
				"                </div> \r\n" + 
				"                <div class=\"mb-4\">\r\n" + 
				"                  <div class=\"form-check\">\r\n" + 
				"                    <i class=\"input-helper\"></i>\r\n" + 
				"                  </div>\r\n" + 
				"                </div>\r\n" + 
				"                <div class=\"mt-3\">\r\n" + 
				"                  <a class=\"btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn\" href=\"main.jsp\">닫기</a>\r\n" + 
				"                </div>\r\n" + 
				"              </form>\r\n" + 
				"            </div>\r\n" + 
				"          </div>\r\n" + 
				"        </div>");
		return sb.toString();
	}

	public ModelAndView detailPage(String ad_code) {
		mav = new ModelAndView();
		String view = null;
		Member sessionMb=null;
		List<Map<String, String>> dibsList=null;
		List<Qna> qaList = null;
		List<Map<String,String>> rvList = null;
		System.out.println("aaaad_code=" + ad_code);
		Map<String, String> dp = kDao.detailPage(ad_code);
		
		List<OpCategory> opCateList = kDao.opCateList(ad_code);
		
		qaList = kDao.detailQa(ad_code);
		rvList = kDao.detailReview(ad_code);
		sessionMb = (Member) session.getAttribute("mb");
		if(sessionMb!=null) {
			String d_id = sessionMb.getM_id();
			dibsList = yDao.dibsN(d_id);
		}
		String html = makeHTMLDetailPage(dp, opCateList, dibsList, sessionMb, qaList, rvList);
		
		view = "detailPage";
		mav.addObject("detailPageHTML", html);
		mav.setViewName(view);
		return mav;
	}

	public String purchSingle(Payment ph) {
		String text = null;
		Member sessionMb = (Member) session.getAttribute("mb");
		if(sessionMb == null) {
			 text = "login";
			 return text;
		}
		Map<String,String> cs = new HashMap<>();
		String ps_mid=sessionMb.getM_id();
		String ps_adcode=ph.getPs_adcode();
		cs.put("ps_mid", ps_mid);
		cs.put("ps_adcode", ps_adcode);
		
		int i=kDao.selectOverlap(cs);
		if (sessionMb != null && sessionMb.getM_kind().equals("n") && i==0) {  //
			ph.setPs_mid(sessionMb.getM_id());
			kDao.purchSingle(ph);
			Payment ph1=kDao.selectPscode(cs);
		 boolean insertDaliy=kDao.insertDaliy(ph1.getPs_code());
		 text = "success";
		 return text;
			}else if(sessionMb.getM_kind().equals("c") || sessionMb.getM_kind().equals("t")){
		 text ="notn";
		 return text;
		 }else if(i!=0) {
		 text ="overlap";
		 return text;
		 }
		return text;
	}

	private String makeHTMLDetailPage(Map<String, String> dp, List<OpCategory> opCateList, 
			List<Map<String, String>> dibsList, Member sessionMb, List<Qna> qaList, List<Map<String, String>> rvList) {
		StringBuilder sb = new StringBuilder();
		String ad_code = dp.get("AD_CODE").toString();
		System.out.println("HTMLad_code===="+ad_code);
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
				//"<form name='detailPageInfo'>"+
				"<input type=\"hidden\" id=\"ad_code\" name=\"ad_code\" value='"+ad_code+"'>"+
				"                                    <div class=\"caption\">\r\n" + 
				"                                        <h3 class=\"display-4\" style=\"text-align: center\">"+dp.get("AD_TITLE")+"<br><br>\r\n" + 
				"                                            <small class=\"text-muted\">");
		//주소 시작위치
		//트레이너던 업체던 소속업체가 없는경우
		if(dp.get("T_CID")==null) {
			sb.append(dp.get("M_ADDR"));
		}else {//소속업체 있는경우
			sb.append(dp.get("T_CID_ADDR"));
		}
		//주소 끝위치
		
		sb.append("											 </small></h3><br>" + 
				"                                        <h5>"+dp.get("AD_CATEGORY")+"</h5><br>" + 
				//옵션출력 시작
				"                                            <p><select id='optionSelect' class=\"form-control\">\r\n" + 
				"                                                <option id='opSelPlz'>상세 옵션을 선택해주세요</option>\r\n");
		for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 옵션명 찍어주기
			sb.append("<option id='op"+opCateList.get(i).getOp_code()+"'>"+opCateList.get(i).getOp_content()+" - "+
					"기간 : "+opCateList.get(i).getOp_period()+
					"횟수 : "+opCateList.get(i).getOp_times()+
					"요일 : "+opCateList.get(i).getOp_day()+
					"시간 : "+opCateList.get(i).getOp_clock()+
					"정원 : "+opCateList.get(i).getOp_personnel()+"</option>"+
					//옵션코드 리스트, 옵션가격 리스트 뽑아내기
					"<input type=\"hidden\" id='"+opCateList.get(i).getOp_code()+"' value='"+opCateList.get(i).getOp_price()+"'/>");
		}
		sb.append("                                            </select></p>" +
				//옵션출력 끝 
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
				"                                        </div>\r\n"+
				"                          				<div style=\"text-align: center\">");//버튼단DIV 시작
		
				//옵션 담당 트레이너 보기버튼 시작				
				if(!dp.get("AD_CATEGORY").toString().equals("일반")) {
					sb.append("                        	 	<button type=\"button\" class=\"btn btn-outline-secondary dropdown-toggle\" data-toggle=\"dropdown\">담당 트레이너 프로필 보기</button>" + 
							  "                         		<div class=\"dropdown-menu\">");
					//프로그램 광고 작성자가 트레이너  //+소속업체가 없으면 개인트레이너 (혹은 업체)
					if(dp.get("M_KIND").toString().equals("t") && dp.get("T_CID")==null) {
						sb.append("<a href='profilepage?m_id="+dp.get("AD_NAME")+"'class='dropdown-item'>"+dp.get("M_NAME")+"</a>");
					//프로그램 광고 작성자가 업체	
					}else if(dp.get("M_KIND").toString().equals("c")) {
						for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 트레이너 + 담당 옵션 찍어주기
							//if(!opCateList.get(i).getOp_trainer().equals(opCateList.get(i+1).getOp_trainer())) {
								sb.append("<a href='profilepage?m_id="+opCateList.get(i).getOp_trainer()+"'class='dropdown-item'>"+opCateList.get(i).getM_name()+"</a>");
							//}
						}
					}
				}
				sb.append("</div>");
				//옵션 담당 트레이너 보기버튼 끝
	  
		//찜버튼 위치
	  	String addBtn = "<a class=\"btn btn-default\" role=\"button\">" + "<button id='" + "dibsAdd" + ad_code
				+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart-outline text-danger\"></i>\r\n" + "</button></a>";
		
	  	String delBtn = "<a class=\"btn btn-default\" role=\"button\">" + "<button id='" + "dibsDelete"
				+ ad_code + "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart\"></i>\r\n" + "</button></a>";
		
	  	if (sessionMb != null && (dibsList != null && dibsList.size() != 0)) { 
			for (int j = 0; j < dibsList.size(); j++) {
				if(!dibsList.get(j).get("D_ADCODE").equals(ad_code)) {// 회원:찜하지 않은 상품은 찜하기버튼
					sb.append(addBtn);
				}else if (dibsList.get(j).get("D_ADCODE").equals(ad_code)) {// 회원:찜한상품 찜 취소버튼
					sb.append(delBtn);
				}
			}
			//중복버튼 제거
			StringBuilder sb2 = null;
			if (sb.toString().contains("dibsDelete")) {//찜 취소버튼이 한개라도 있다면 
				sb2 = new StringBuilder(sb.toString().replace(addBtn, ""));//찜하기 버튼을 모두 제거
			}else if (!sb.toString().contains("dibsDelete")) {//찜 취소버튼이 한개도 없다면
				sb2 = new StringBuilder(sb.toString().replace(addBtn, ""));//찜하기 버튼을 한개 빼고 모두 제거
				sb2.append(addBtn);
			}
			sb=sb2;
			// 회원인데 찜 하나도 없을때도 dibsList null일수있음 : 찜하기버튼
		} else if (sessionMb != null && (dibsList == null || dibsList.size() == 0)) {
			sb.append(addBtn);
		} else if (sessionMb == null && (dibsList == null || dibsList.size() == 0)) {// (dibsList==null) 비회원
			// 비회원 세션에 찜한상품 아니면 찜하기버튼
			if (session.getAttribute("tempDibs" + ad_code) == null
					|| session.getAttribute("tempDibs" + ad_code) != "dibs") {
				sb.append(addBtn);
				// 비회원 세션에 찜한 상품 찜취소버튼 :
			} else if (session.getAttribute("tempDibs" + ad_code) == "dibs") {
				sb.append(delBtn);
			}
		} // 찜버튼 끝
				
				sb.append("<button id='purchase' class=\"btn btn-primary\" role=\"button\" disabled='true'>구매</button> </div>\r\n" + 
				//"</form>"+
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"                    <div class=\"row\">\r\n" + 
				"                        <div class=\"col-md-9 grid-margin stretch-card\">\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body dashboard-tabs p-0\">\r\n" + 
				"									<ul class=\"nav nav-tabs px-4 nav-justified\" role=\"tablist\">\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link active\" id=\"detailInfo-tab\" data-toggle=\"tab\" href=\"#detailInfo\" role=\"tab\" aria-controls=\"detailInfo\" aria-selected=\"true\" style=\"border-bottom-color : #71c016; color: #71c016\">상세 정보 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link\" id=\"review-tab\" data-toggle=\"tab\" href=\"#review\" role=\"tab\" aria-controls=\"review\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">별점 및 후기 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"                                        <li class=\"nav-item\">\r\n" + 
				"                                            <a class=\"nav-link\" id=\"question-tab\" data-toggle=\"tab\" href=\"#question\" role=\"tab\" aria-controls=\"question\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">문의 보기</a>\r\n" + 
				"                                        </li>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"                                    </ul>"+
				"                                    <div class=\"tab-content py-0 px-0\">\r\n" + 
				
				//상세정보 시작
				
				"                                        <div class=\"tab-pane fade\" id=\"detailInfo\" role=\"tabpanel\" aria-labelledby=\"detailInfo-tab\">\r\n" + 
				"                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">\r\n" + 
				"\r\n" + 
				"                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">\r\n" + 
				"                                                    <li class=\"nav-item d-none d-lg-block w-100\">\r\n" + 
				dp.get("AD_CONTENT")+
				//"광고 작성일 : " + dp.get("AD_DATE").toString()+
			
				"                                                        \r\n" + 
				"\r\n" + 
				"                                                    </li>\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>\r\n" + 
				//상세정보끝, 후기시작
				
				"											<div class=\"tab-pane fade\" id=\"review\" role=\"tabpanel\" aria-labelledby=\"review-tab\">\r\n"
				+ "                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">\r\n"
				+ "                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">\r\n"
				+ "                                                    <li class=\"nav-item d-none d-lg-block w-100\">\r\n"
				+ "                                                        <p>총 "+rvList.size()+"건의 후기가 있습니다.</p> 평점:");
				//평점 계산
			if(rvList.size()!=0) {
				int pSum=0;
				int oneStarCnt=0;
				int twoStarCnt=0;
				int threeStarCnt=0;
				int fourStarCnt=0;
				int fiveStarCnt=0;
				for(int i=0;i<rvList.size();i++) {
					String ptStr= String.valueOf(rvList.get(i).get("RV_STPOINT")); 
					int pt = Integer.valueOf(ptStr);
					System.out.println("별점!!!!!!!!!!!!!!!!!!!!!!"+pt);
					pSum += pt;
					if(pt==1) oneStarCnt++;
					else if(pt==2) twoStarCnt++;
					else if(pt==3) threeStarCnt++;
					else if(pt==4) fourStarCnt++;
					else if(pt==5) fiveStarCnt++;
				}
				System.out.println("pSum="+pSum);
				System.out.println("rvList.size()="+rvList.size());
				double pAvg = Math.round((pSum/(float)rvList.size()*100)/100.00);
				System.out.println("평점="+pAvg);
				System.out.println("3점 몇명?"+threeStarCnt);
				sb.append(pAvg+". 1점인원:"+oneStarCnt+"명. 2점인원:"+twoStarCnt+"명. 3점인원:"+threeStarCnt+"명. 4점인원:"+fourStarCnt+"명. 5점인원:"+fiveStarCnt);
			}				
			//평점 끝
		sb.append("                                                        <div class=\"table-responsive\">\r\n"
				+ "                                                            <table class=\"table table-hover table-condensed\">\r\n"
				+ "                                                                <thead>\r\n"
				+ "                                                                    <tr>\r\n"
				+ "                                                                        <th>별점</th>\r\n"
				+ "                                                                        <th>구매옵션</th>\r\n"
				+ "                                                                        <th>구매일</th>\r\n"
				+ "                                                                        <th>내용</th>\r\n"
				+ "                                                                        <th>작성자</th>\r\n"
				+ "                                                                        <th>작성일</th>\r\n"
				+ "                                                                    </tr>\r\n"
				+ "                                                                </thead>\r\n"
				+ "                                                                <tbody>\r\n");
				for(int i=0; i<rvList.size();i++) {
					sb.append("                                                       <tr>"
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("RV_STPOINT"))+"</td>"
							
							+ "                                                           <td>"+rvList.get(i).get("OP_CONTENT")+"/"
							+ "                                                           기간:"+rvList.get(i).get("OP_PERIOD")+"/"
							+ "                                                           "+String.valueOf(rvList.get(i).get("OP_TIMES"))+"회/"
							+ "                                                           "+rvList.get(i).get("OP_DAY")+"/"
							+ "                                                           시간:"+rvList.get(i).get("OP_CLOCK")+"</td>"
							
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("PS_DATE"))+"</td>"
							+ "                                                           <td>"+rvList.get(i).get("RV_CONTENT")+"<a href=\"#\"> 더보기<i class=\"mdi mdi-arrow-down\"></i></a></td>"
							+ "                                                           <td>"+rvList.get(i).get("RV_NAME")+"</td>"
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("RV_DATE"))+"</td>"
							+ "                                                       </tr>");
				}
				
		sb.append("                                                                </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                        </div>\r\n"+
				//페이징시작
				"                                                        <ul class=\"pagination pagination-sm\" style=\"padding-left:  \">\r\n" + 
				"                                                            <li>\r\n" + 
				"                                                                <div class=\"btn-toolbar\" role=\"toolbar\" aria-label=\"Toolbar with button groups\">\r\n" + 
				"                                                                    <div class=\"btn-group\" role=\"group\">\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">◀◀</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\" aria-label=\"Previous\">◀</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">1</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">2</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">3</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">4</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">5</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\" aria-label=\"Next\">▶</button>\r\n" + 
				"                                                                        <button type=\"button\" class=\"btn btn-sm btn-outline-secondary\">▶▶</button>\r\n" + 
				"                                                                    </div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                            </li>\r\n" + 
				"                                                        </ul>"
				//페이징 끝
				+ "                                                    </li>\r\n"
				+ "                                                </div>\r\n"
				+ "                                            </div>\r\n"
				+ "                                        </div>"+
				//후기 끝
				//문의시작
				"<div class=\"tab-pane fade\" id=\"question\" role=\"tabpanel\" aria-labelledby=\"question-tab\">\r\n" + 
				"                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">\r\n" + 
				"                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">\r\n" + 
				"                                                    <li class=\"nav-item d-none d-lg-block w-100\">\r\n" + 
				"                                                        <p>총 "+qaList.size()+"건의 문의가 있습니다.<button type=\"button\" class=\"btn btn-primary btn-sm\">문의하기</button></p>\r\n" + 
				"                                                        <div class=\"table-responsive\">\r\n" + 
				"                                                            <table class=\"table table-hover table-condensed\">\r\n" + 
				"                                                                <thead>\r\n" + 
				"                                                                    <tr>\r\n" + 
				"                                                                        <th>번호</th>\r\n" + 
				"                                                                        <th>문의 상태</th>\r\n" + 
				"                                                                        <th>문의 내용</th>\r\n" + 
				"                                                                        <th>작성자</th>\r\n" + 
				"                                                                        <th>작성일</th>\r\n" + 
				"                                                                    </tr>\r\n" + 
				"                                                                </thead>\r\n" + 
				"                                                                <tbody>\r\n");
				for(int i=0; i<qaList.size();i++) {
					sb.append("<tr><td>"+qaList.get(i).getQa_num()+"</td>"); 
					if(qaList.get(i).getQa_answer()!=null) {
						sb.append("<td>답변 완료</td>");
					}else {sb.append("<td>답변 대기중</td>");}
					sb.append("<td>"+qaList.get(i).getQa_wContent()+"</td>" + 
							  "<td>"+qaList.get(i).getQa_writer()+"</td>" + 
							  "<td>"+qaList.get(i).getQa_wdate()+"</td>" + 
							  "</tr>");
				  	if(qaList.get(i).getQa_answer()!=null) {
				  		sb.append("<tr><td></td><td></td><td><i class=\"mdi mdi-arrow-right-bold\"></i></td>" + 
				  					"<td>"+qaList.get(i).getQa_aContent()+"<a href=\"#\"> 접기<i class=\"mdi mdi-arrow-up\"></i></a></td>" + 
				  					"<td>"+qaList.get(i).getQa_answer()+"</td>" + 
				  					"<td>"+qaList.get(i).getQa_adate()+"</td>" + 
				  					"</tr>");
				  	}
				}
	  sb.append("                                                                </tbody>\r\n" + 
				"                                                            </table>\r\n" + 
			  
				"                                                        </div>\r\n" + 
				"                                                            <ul class=\"pagination pagination-lg\">\r\n" + 
				"                                                                <div class=\"btn-toolbar\" role=\"toolbar\" aria-label=\"Toolbar with button groups\">\r\n" + 
				"                                                                    <div class=\"btn-group\" role=\"group\" aria-label=\"First group\">\r\n" + 
				"                                                                        <li>\r\n" + 
				"                                                                            <a href=\"#\" aria-label=\"Previous\">\r\n" + 
				"                                                                                <span aria-hidden=\"true\" class=\"disabled\"><button type=\"button\" class=\"btn btn-outline-secondary\">«</button></span>\r\n" + 
				"                                                                            </a>\r\n" + 
				"                                                                        </li>\r\n" + 
				"                                                                        <li><a href=\"#\" ><button type=\"button\" class=\"btn btn-outline-secondary\">1</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" ><button type=\"button\" class=\"btn btn-outline-secondary\">2</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" ><button type=\"button\" class=\"btn btn-outline-secondary\">3</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" ><button type=\"button\" class=\"btn btn-outline-secondary\">4</button></a></li>\r\n" + 
				"                                                                        <li><a href=\"#\" ><button type=\"button\" class=\"btn btn-outline-secondary\">5</button></a></li>\r\n" + 
				"                                                                        <li>\r\n" + 
				"                                                                            <a href=\"#\" aria-label=\"Next\">\r\n" + 
				"                                                                                <span aria-hidden=\"true\"><button type=\"button\" class=\"btn btn-outline-secondary\">»</button></span>\r\n" + 
				"                                                                            </a>\r\n" + 
				"                                                                        </li>\r\n" + 
				"                                                                    </div>\r\n" + 
				"                                                                </div>\r\n" + 
				"                                                            </ul>\r\n" + 
				"\r\n" + 
				"                                                    </li>\r\n" + 
				"\r\n" + 
				"                                                </div>\r\n" + 
				"                                            </div>\r\n" + 
				"                                        </div>"+
				//문의 끝
				
				"                                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>\r\n" + 
				"                    </div>\r\n" + 
				"\r\n" + 
				"                </div>");
		return sb.toString();
	}
}