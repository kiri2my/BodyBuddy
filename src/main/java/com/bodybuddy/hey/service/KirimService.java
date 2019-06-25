package com.bodybuddy.hey.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.bodybuddy.hey.bean.Alarm;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.bean.Qna;
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
		String checkId =  kDao.deleteRealIdCheck(mb.getM_id());
		System.out.println("aa" + checkId);
		if (checkId != null)  {
			mav.setViewName("loginJoinFrm/loginFrm");
			mav.addObject("loginCheck", "이미 탈퇴 처리된 계정입니다");
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
				view = "loginJoinFrm/loginFrm";
				mav.addObject("loginCheck", "다시 입력해주세요(Password Error)");
			}
		}else {// 아이디오류
			view = "loginJoinFrm/loginFrm";
			mav.addObject("loginCheck", "다시 입력해주세요(Email Error)");
		}
		mav.setViewName(view);
		return mav;
	}
	
	public ModelAndView detailPage(String ad_code) {
		mav = new ModelAndView();
		String view = null;
		Member sessionMb=null;
		List<Map<String, String>> dibsList=null;
		
		System.out.println("aaaad_code=" + ad_code);
		Map<String, String> dp = kDao.detailPage(ad_code);
		List<OpCategory> opCateList = kDao.opCateList(ad_code);
		List<Map<String,String>> apList =kDao.adPhotoList(ad_code);
		List<Qna> qaList = kDao.detailQa(ad_code);
		List<Map<String,String>> rvList = kDao.detailReview(ad_code);
		List<Map<String,String>> psList = kDao.detailPsCount(ad_code);
		
		sessionMb = (Member) session.getAttribute("mb");
		if(sessionMb!=null) {
			String d_id = sessionMb.getM_id();
			dibsList = yDao.dibsN(d_id);
		}
		String html = makeHTMLDetailPage(dp, opCateList, dibsList, sessionMb, qaList, rvList, psList, mav, apList);
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
			text = opExpirePersonnel(ph);
			if(text!=null && text.equals("full")) {
				return text;
			}
			ph.setPs_mid(sessionMb.getM_id());
			kDao.purchSingle(ph);
			String da_opperiod=ph.getPs_opcode();
			Payment ph1=kDao.selectPscode(cs);
			String ph2=kDao.selectPeriod(da_opperiod);
			Map<String,String> cs2 = new HashMap<>();
			String ps_code=ph1.getPs_code();
			cs2.put("da_period", ph2);
			cs2.put("ps_code", ps_code);
		 boolean insertDaily=kDao.insertDaliy(cs2);
		 	if(insertDaily==true) {
		 		
		 		text = "success";
			 	return text;
		 	}
		}else if(sessionMb.getM_kind().equals("c") || sessionMb.getM_kind().equals("t")){
				text ="notn";
				return text;
		}else if(i!=0) {
				text ="overlap";
				return text;
		}
		return text;
	}
	
	private String opExpirePersonnel(Payment ph) {
		String text=null;
		Map<String,String> purByPer=null;
		purByPer = kDao.personnelCalc(ph.getPs_opcode());
		if(purByPer!=null) {
			
			int a = Integer.valueOf(String.valueOf(purByPer.get("PURCHCOUNT"))).intValue();
			int b = Integer.valueOf(String.valueOf(purByPer.get("OP_PERSONNEL"))).intValue();
			if(b!=-100) {
				if(a >= b){
					text="full";
					adExpire();
				}
			}
			
			
		}
		return text;
		
	}

	

	public String dibsAdd(String d_adcode) {
		String del = "<button id='" + "dibsDelete" + d_adcode
				+ "' type='button' class='btn btn-outline-danger btn-rounded btn-icon'>"
				+ "<i class='mdi mdi-heart'></i></button>";
		String add = "<button id='" + "dibsAdd" + d_adcode
				+ "'type='button' class='btn btn-outline-secondary btn-rounded btn-icon'>"
				+ "<i class='mdi mdi-heart-outline text-danger'></i></button>";
		String html = null;

		System.out.println("dibsAdd:d_adcode=============" + d_adcode);
		System.out.println(session.getId());
		Member sessionMb = (Member) session.getAttribute("mb");
		if (sessionMb == null) {// 비회원 찜 추가
			session.setAttribute("tempDibs" + d_adcode, "dibs");
			html = del;// "일시적으로 찜 목록에 추가되었습니다.";

		} else if (sessionMb != null) {// 회원 찜 추가
			String sessionId = sessionMb.getM_id();
			Map<String, String> dibs = new HashMap<>();
			dibs.put("d_adcode", d_adcode);
			dibs.put("d_id", sessionId);
			if (kDao.dibsAdd(dibs)) {
				html = del; // sessionId+"님의 찜 목록에 추가되었습니다";
				return html;
			}
			html = add; // sessionId+"님의 찜 목록에 추가실패";
		}
		System.out.println("비회원장바구니 세션등록키===" + session.getAttribute("tempDibs" + d_adcode));
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println("찜추가완료NAMES=" + names.nextElement());
		}
		return html;
	}

	public String dibsDelete(String d_adcode) {
		String del = "<button id='" + "dibsDelete" + d_adcode
				+ "' type='button' class='btn btn-outline-danger btn-rounded btn-icon'>"
				+ "<i class='mdi mdi-heart'></i></button>";
		String add = "<button id='" + "dibsAdd" + d_adcode
				+ "'type='button' class='btn btn-outline-secondary btn-rounded btn-icon'>"
				+ "<i class='mdi mdi-heart-outline text-danger'></i></button>";
		String html = null;
		System.out.println("dibsDelete:d_adcode=============" + d_adcode);
		System.out.println(session.getId());
		Member sessionMb = (Member) session.getAttribute("mb");
		if (sessionMb == null) {// 비회원 찜 제거
			session.setAttribute("tempDibs" + d_adcode, null);
			html = add;// "해당 광고가 찜 목록에서 제거되었습니다.";
		} else if (sessionMb != null) {// 회원 찜 제거
			String sessionId = sessionMb.getM_id();
			Map<String, String> dibs = new HashMap<>();
			dibs.put("d_adcode", d_adcode);
			dibs.put("d_id", sessionId);
			if (kDao.dibsDelete(dibs)) {
				html = add;// "님의 찜 목록에서 제거되었습니다";
				return html;
			}
			html = del;// "님의 찜 목록에서 제거실패";
		}
		System.out.println("222비회원장바구니 세션등록키===" + session.getAttribute("tempDibs" + d_adcode));
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			System.out.println("찜취소완료NAMES=" + names.nextElement());
		}
		return html;
	}
	public String profilePage(String m_id) {
		Member mb=null;
		String html = null;
		Map<String,String> tp=null;
		List<Map<String, String>> tpo=null;
		List<Map<String, String>> tpc=null;
		Map<String, String> cp=null;
		List<Map<String, String>> cpo=null;
		
		
		String kind = kDao.getMemberKind(m_id);
		System.out.println("kind============="+kind);
		System.out.println("m_id============="+m_id);
		
		if(kind.equals("n")) {
			mb=kDao.getNormalInfo(m_id);
			html = makeHTMLNormalProfile(mb);
		}else if(kind.equals("t")) {
			tp=kDao.getTrainerProfile(m_id);
			tpo=kDao.getTrainerPOption(m_id);
			tpc=kDao.getTrainerPCategory(m_id);
			html = makeHTMLTrainerProfile(tp, tpo, tpc);
		}else if(kind.equals("c")) {
			cp=kDao.getCompanyProfile(m_id);
			cpo=kDao.getCompanyProfileOption(m_id);
			html = makeHTMLCompanyProfile(cp,cpo);
		}
		return html;
	}
	
	
	
	public void adExpirePeriod() {
		//매일 자정 지날때 실행할 내용:
		String period=null;
		String periodStartStr=null;
		LocalDate periodStart=null;
		String op_code;
		int result=0;
		//DAO에서 옵션 기간(OP_PERIOD) 얻어오기
		List<Map<String,String>> opl = kDao.getOpPeriodList();
		//현재 날짜 얻어오기
		
		LocalDate today = LocalDate.now();
		//혹은 today.toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		/*Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //출력 형태 지정
		String todayStr = sdf.format(cal.getTime()); //출력형태의 문자열*/
		
		//해당 옵션레코드의 시작날짜가 오늘보다 많다면(after라면)
		for(int i=0;i<opl.size();i++) {
			if(opl.get(i).get("OP_PERIOD").toString().contains("~")) {
				period = opl.get(i).get("OP_PERIOD").toString();
				periodStartStr =period.split("~")[0];//시작날짜 문자열
				//기본 날짜포맷 사용 : LocalDate periodStart = LocalDate.parse(periodStartStr, DateTimeFormatter.ISO_DATE); 
				periodStart = LocalDate.parse(periodStartStr, formatter);//내가 지정한 날짜포맷 사용
		        //periodStart.format(formatter);
				if(periodStart.isBefore(today)) { //isBefore:a<b   isAfter:a>b //시작날짜>현재 날짜라면  
					//해당 옵션 만료
					op_code = opl.get(i).get("OP_CODE").toString();
					result = kDao.expireOption(op_code);
				}
			}
		}
		
		adExpire();
		//해당 광고의 모든 옵션이 만료되었다면 광고를 만료
		
	}
	private void adExpire() {
		int result=0;
		String ad_code;
		String ad_code0;
		String ad_code1;
		List<Map<String,String>> aeList =kDao.getAdExpireList();
		Set<String> ex1CodeSet = new HashSet<>();
		Set<String> ex0CodeSet = new HashSet<>();
		for(int i=0;i<aeList.size();i++) {
			if(String.valueOf(aeList.get(i).get("OP_EXPIRE")).equals("1")) {
				ex1CodeSet.add(aeList.get(i).get("AD_CODE"));
			}else if(String.valueOf(aeList.get(i).get("OP_EXPIRE")).equals("0")) {
				ex0CodeSet.add((String) aeList.get(i).get("AD_CODE"));
			}
		}
		
		
		//1셋에는 있고 0셋에는 없으면 만료시킴
		Iterator<String> itr1 = ex1CodeSet.iterator();
		Iterator<String> itr0 = ex0CodeSet.iterator();
		List<String> delCode = new ArrayList<>();
		System.out.println("ex0CodeSet======="+ex0CodeSet);
		System.out.println("ex1CodeSet======="+ex1CodeSet);
		while(itr0.hasNext()) {
			ad_code0 = itr0.next();
			System.out.println("0코드======="+ad_code0);
			while(itr1.hasNext()) {
				ad_code1 = itr1.next();
				System.out.println("1코드======="+ad_code1);
				 if(ad_code0.equals(ad_code1)) {
					 System.out.println("교집합!!!!!"+ad_code1);
					 delCode.add(ad_code1);
				 }
			}
		}
		for(int i=0;i<delCode.size();i++) {
			ex1CodeSet.remove(delCode.get(i).toString());
		}
		Iterator<String> comp = ex1CodeSet.iterator();
		while(comp.hasNext()) {
			ad_code = comp.next();
			result = kDao.expireAd(ad_code);
			System.out.println("RESULT2"+result);
			if(result!=0) {
				System.out.println("만료된광고"+result+"건");
			}else {
				System.out.println("만료시킬 광고 없음");
			}
		}
		System.out.println("정상완료");
		
	}

	//adExpirePeriod 끝 http://www.urbanui.com/majestic/template/pages/ui-features/typography.html

	public String detailQaWriteInsert(Qna qna) {
		String html=null;
		Member sessionMb = (Member) session.getAttribute("mb");
		qna.setQa_writer(sessionMb.getM_id());
		if(kDao.detailQaWriteInsert(qna)) {//성공시
			List<Qna> qaList = kDao.detailQa(qna.getQa_adcode());
			html=makeHtmlQna(qaList);
			//json = new Gson().toJson(html);
		}		
		return html;
	}
	
	
	
	
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	//MAKEHTML
	private String makeHTMLCompanyProfile(Map<String, String> pc, List<Map<String, String>> pco) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='main-panel'>" + 
				"                <div class='content-wrapper' style='width:57.7em' >" + 
				"                    <div class=\"row\"  style='width:78.6em'>" + 
				"                        <div class=\"col-md-4 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 400px;\">" + 
				"                                        <a href=\"#\" class=\"thumbnail\">" + 
				"                                            <img src='resources/upload/"+pc.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />" + 
				"                                        </a>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <div class=\"caption\">" + 
				"                                        <h2 class=\"text-primary\" style=\"text-align: center\">"+pc.get("C_BNAME")+"</h2>" + 
				"                                        <h3><small class=\"text-muted\" >"+pc.get("M_ADDR")+"</small></h3>" + 
				"                                        <br>" + 
				"                                        <h4 class=\"display-4\">Phone : "+pc.get("C_BPHONE")+"</h4>" + 
				"                                        <h4 class=\"display-4\">Representative : "+pc.get("M_NAME")+"</h4>" + 
				"                                        <h4 class=\"display-4\">Business Number : "+pc.get("C_NUM")+"</h4>" + 
				"                                        <br>" + 
				"                                        <address class=\"text-primary\">" + 
				"                                            <p class=\"font-weight-bold\">E-mail</p><p class=\"mb-2\">"+pc.get("M_ID")+"</p>"+ 
				"                                            <p class=\"font-weight-bold\">Personal-phone</p><p class=\"mb-2\">"+pc.get("M_PHONE")+"</p>"+ 
				"                                        </address>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"                    <div class=\"row\"  style='width:78.6em' >" + 
				"                        <div class=\"col-md-9 stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <p class=\"card-title\">업체 등록 광고 보기</p>" + 
				"                                    <div class=\"table-responsive\">" + 
				"                                        <div id=\"company-advertise-listing_wrapper\" class=\"dataTables_wrapper container-fluid dt-bootstrap4 no-footer\">" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>" + 
				"                                            </div>" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12\">" + 
				"                                                    <table id=\"company-advertise-listing\" class=\"table dataTable no-footer\" role=\"grid\">" + 
				"                                                        <thead>" + 
				"                                                            <tr role=\"row\">" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Gross amount: activate to sort column ascending\" style=\"width: 101.364px;\">광고명</th>" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date: activate to sort column ascending\" style=\"width: 67.7273px;\">등록 날짜</th>" + 
				"                                                                <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"company-advertise-listing\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Name: activate to sort column descending\" style=\"width: 105px;\">진행 상태</th>" + 
				"                                                            </tr>" + 
				"                                                        </thead>" + 
				"                                                        <tbody>");
		
				for(int i=0;i<pco.size();i++) {
					DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
					String Date = sdFormat.format(pco.get(i).get("AD_DATE"));
					sb.append("                                                     <tr role=\"row\" class=\"odd\">" + 
							"                                                         <td class=\"sorting_1\">"+pco.get(i).get("AD_TITLE")+"</td>" + 
							"                                                         <td>"+Date+"</td>");
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
							sb.append(pco.get(i).get("AD_STATUS")+"</label></td>"+ 
							"                                                       </tr>" );
				}
				
	  sb.append("                                                        </tbody>" + 
				"                                                    </table>" + 
				"                                                </div>" + 
				"                                            </div>" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12 col-md-5\"></div>" + 
				"                                                <div class=\"col-sm-12 col-md-7\"></div>" + 
				"                                            </div>" + 
				"                                        </div>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"                </div>" + 
				
				"            </div>");		
		return sb.toString();
	}

	private String makeHTMLTrainerProfile(Map<String, String> tp, List<Map<String, String>> tpo, List<Map<String, String>> tpc) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"main-panel\">" + 
				"                <div class=\"content-wrapper\" style='width:57.7em' >" + 
				"                    <div class=\"row\" style=\"width:78.6em\">" + 
				"                        <div class=\"col-md-4 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 400px;\">" + 
				"                                        <a href=\"#\" class=\"thumbnail\">" + 
				"                                            <img src='resources/upload/"+tp.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />" + 
				"                                        </a>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <div class=\"caption\">" + 
				"                                        <h3 class=\"text-primary\" style=\"text-align: center\">"+tp.get("M_NAME")+" 트레이너 프로필<br><br>" + 
				"                                            <small class=\"text-muted\" style=\"text-align: right\">소속업체 : ");
		if(tp.get("C_BNAME")!="" ||tp.get("C_BNAME")!=null) {
			sb.append(tp.get("C_BNAME"));
		}else {
			sb.append("없음");
		}
		
		sb.append("</small>" + 
				"                                        </h3>" + 
				"                                        <br>" + 
				"                                        <h4>");
		
		for(int i=0;i<tpc.size();i++) {
				sb.append(tpc.get(i).get("AD_CATEGORY")+"/");
		}
	  sb.append("										 </h4><br>" + 
				"                                        <h4>주요 이력: <p class=\"display-4\">");
	  if(!tp.get("T_CAREER").equals("none")) {
		  String[] career = tp.get("T_CAREER").split(" ");//,쉼표로 자를것
		  	for(int i=0;i<career.length;i++) {
		  		sb.append(career[i]+"<br>");
		  	}
	  }
	  
	  
	  sb.append("										 </h4>" + 
				"                                        <br>" + 
				"                                        <address class=\"text-primary\">" + 
				"                                            <p class=\"font-weight-bold\">E-mail</p><p class=\"mb-2\">" +tp.get("M_ID")+"</p>" + 
				"                                        </address>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"                    <div class=\"row\" style='width:78.6em'>"+ 
				"                        <div class=\"col-md-9 stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <p class=\"card-title\">이 트레이너의 프로그램 히스토리</p>" + 
				"                                    <div class=\"table-responsive\">" + 
				"                                        <div id=\"trainer-program-listing_wrapper\" class=\"dataTables_wrapper container-fluid dt-bootstrap4 no-footer\">" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>" + 
				"                                                <div class=\"col-sm-12 col-md-6\"></div>" + 
				"                                            </div>" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12\">" + 
				"                                                    <table id=\"trainer-program-listing\" class=\"table dataTable no-footer\" role=\"grid\">" + 
				"                                                        <thead>" + 
				"                                                            <tr role=\"row\">" + 
				"                                                                <th class=\"sorting_asc\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-sort=\"ascending\" aria-label=\"Name: activate to sort column descending\" style=\"width: 105px;\">프로그램 상태</th>" +
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Gross amount: activate to sort column ascending\" style=\"width: 101.364px;\">프로그램명(옵션)</th>" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Status report: activate to sort column ascending\" style=\"width: 144.091px;\">진행 장소</th>" + 
				"                                                                <th class=\"sorting\" tabindex=\"0\" aria-controls=\"trainer-program-listing\" rowspan=\"1\" colspan=\"1\" aria-label=\"Date: activate to sort column ascending\" style=\"width: 67.7273px;\">진행 기간</th>" + 
				"                                                                " + 
				"                                                            </tr>" + 
				"                                                        </thead>" + 
				"                                                        <tbody>");
		for(int i=0;i<tpo.size();i++) {
			sb.append("                                                            <tr role=\"row\"  class=\"odd\">");
					//success-초록색 : 완료	info-보라색 : 모집중	danger-빨간색 : 만료됨		
					if(tpo.get(i).get("AD_STATUS").equals("진행중")) {
						sb.append("<td><label class=\"badge badge-warning\">");
					}else if(tpo.get(i).get("AD_STATUS").equals("완료")) {
						sb.append("<td><label class=\"badge badge-success\">");
					}else if(tpo.get(i).get("AD_STATUS").equals("모집중")) {
						sb.append("<td><label class=\"badge badge-info\">");
					}else if(tpo.get(i).get("AD_STATUS").equals("만료됨")) {
						sb.append("<td><label class=\"badge badge-danger\">");
					} 
					sb.append(tpo.get(i).get("AD_STATUS")+"</label></td>"+ 
					
					"                                                                <td class=\"sorting_1\">"+tpo.get(i).get("AD_TITLE")+"("+tpo.get(i).get("OP_CONTENT")+"-");
			
			if(tpo.get(i).get("OP_TIMES")!=null || String.valueOf(tpo.get(i).get("OP_TIMES"))!="") sb.append("횟수:"+String.valueOf(tpo.get(i).get("OP_TIMES"))+"/"); else sb.append("");
			if(tpo.get(i).get("OP_DAY")!=null || tpo.get(i).get("OP_DAY")!="") sb.append(tpo.get(i).get("OP_DAY")+"/"); else sb.append("");
			if(tpo.get(i).get("OP_CLOCK")!=null || tpo.get(i).get("OP_CLOCK")!="") sb.append(tpo.get(i).get("OP_CLOCK")+"/"); else sb.append("");
			if(tpo.get(i).get("OP_PERSONNEL")!=null || String.valueOf(tpo.get(i).get("OP_PERSONNEL"))!="") sb.append("인원"+String.valueOf(tpo.get(i).get("OP_PERSONNEL"))); else sb.append("");
			sb.append(")</td><td>"+tpo.get(i).get("AD_ADDR")+" ");
			if(tp.get("C_BNAME")!=null || tp.get("C_BNAME")!="") sb.append(tp.get("C_BNAME")); else sb.append("");
			sb.append("</td><td>");
			if(tpo.get(i).get("OP_PERIOD")!=null || tpo.get(i).get("OP_PERIOD")!="") sb.append(tpo.get(i).get("OP_PERIOD")); else sb.append("");
			sb.append("</td>");
			
					sb.append("</tr>");
		}//for END
	  sb.append(
				"                                                        </tbody>" + 
				"                                                    </table>" + 
				"                                                </div>" + 
				"                                            </div>" + 
				"                                            <div class=\"row\">" + 
				"                                                <div class=\"col-sm-12 col-md-5\"></div>" + 
				"                                                <div class=\"col-sm-12 col-md-7\"></div>" + 
				"                                            </div>" + 
				"                                        </div>" + 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"                </div>"+
  			
			  	"			</div>");
		return sb.toString();
	}

	private String makeHTMLNormalProfile(Member mb) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"row w-100 mx-0\">" + 
				"          <div class=\"col-lg-4 mx-auto\">" + 
				"            <div class=\"auth-form-light text-left py-5 px-4 px-sm-5\">" + 
				"              <div class=\"brand-logo\">" + 
				"                <a class=\"navbar-brand brand-logo\" href=\"/\" style=\"color: #71c016;\">일반회원 프로필</a>" + 
				"              </div><br>" + 
				"    <div>" + 
				"        <div class=\"img_wrap\">" + 
				"            <img id=\"img\" src='resources/upload/"+mb.getPf_image()+"' />" + 
				"        </div>" + 
				"    </div>" + 
				"              <form class=\"pt-3\">" + 
				"                <div class=\"form-group\">" + 
				"                  <div class=\"input-group\">" + 
				"                    <div class=\"input-group-prepend bg-transparent\">" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">" + 
				"                        <i class=\"mdi mdi-email-outline text-primary\"></i>" + 
				"                      </span>" + 
				"                    </div>" + 
				"                    <input type=\"email\" name=\"m_id\" class=\"form-control form-control-lg border-left-0\" value='"+mb.getM_id()+"' disabled>" +
				"                  </div>" + 
				"                </div>" + 
				"                <div class=\"form-group\">" + 
				"                </div>" + 
				"                <div class=\"form-group\">" + 
				"                    <div class=\"input-group\">" + 
				"                    <div class=\"input-group-prepend bg-transparent\">" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">" + 
				"                        <i class=\"mdi mdi-account-outline text-primary\"></i>" + 
				"                      </span>" + 
				"                    </div>" + 
				"                    <input type=\"text\"  name=\"m_name\" class=\"form-control form-control-lg border-left-0\"value='"+mb.getM_name()+"' disabled>" + 
				"                  </div>" + 
				"                </div>" + 
				"                <div class=\"form-group\">" + 
				"                  <div class=\"input-group\">" + 
				"                    <div class=\"input-group-prepend bg-transparent\">" + 
				"                      <span class=\"input-group-text bg-transparent border-right-0\">" + 
				"                        <i class=\"mdi mdi-cellphone text-primary\"></i>" + 
				"                      </span>" + 
				"                    </div>" + 
				"                    <input type=\"tel\"  name=\"m_phone\" class=\"form-control form-control-lg border-left-0\" value=\"전화번호: "+mb.getM_phone()+"\">" + 
				"                  </div>" + 
				"                </div> " + 
				"                <div class=\"form-group\">" + 
				"                  <div class=\"input-group\">" + 
				"                    <input type=\"text\"  name=\"m_birth\" class=\"form-control form-control-lg border-left-0\" value=\"생년월일 :"+mb.getM_birth()+"\"  disabled>" + 
				"                  </div>" + 
				"                </div> " + 
				"                <div class=\"mb-4\">" + 
				"                  <div class=\"form-check\">" + 
				"                    <i class=\"input-helper\"></i>" + 
				"                  </div>" + 
				"                </div>" + 
				"                <div class=\"mt-3\">" + 
				"                  <a class=\"btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn\" href=\"main.jsp\">닫기</a>" + 
				"                </div>" + 
				"              </form>" + 
				"            </div>" + 
				"          </div>" + 
				"        </div>");
		return sb.toString();
	}

	private String makeHTMLDetailPage(Map<String, String> dp, List<OpCategory> opCateList,List<Map<String, String>> dibsList, 
			Member sessionMb, List<Qna> qaList, List<Map<String, String>> rvList, List<Map<String, String>> psList, ModelAndView mav, List<Map<String, String>> apList) {
		StringBuilder sb = new StringBuilder();
		String ad_code = dp.get("AD_CODE").toString();
		System.out.println("HTMLad_code===="+ad_code);
		sb.append("<div class=\"content-wrapper\">" + 
				"<br><br><br><br><br><br><br><br><br>                    <div class=\"row\">" + 
				
				"                        <div class=\"col-md-4 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"                                    <div class=\"col-md-12\" style=\"overflow: hidden; height: 400px;\">" + 
//광고사진 캐러셀 시작
			"<div id=\"carousel-adPhoto-generic\" class=\"carousel slide\" data-ride=\"carousel\" data-interval=\"false\"  >" + 
			"  <!-- Indicators -->" + 
			"  <ol class=\"carousel-indicators\">");
			for(int i=0;i<apList.size();i++) {
				sb.append("    <li data-target=\"#carousel-adPhoto-generic\" data-slide-to='"+i+"'");
				if(i==0) {
					sb.append("class='active'");
				}
				sb.append("></li>");
				
			}
  sb.append( 
			"  </ol>" + 
			"  <!-- Wrapper for slides -->" + 
			"  <div class=\"carousel-inner\" role=\"listbox\">");
			for(int i=0;i<apList.size();i++) {
				sb.append("    <div class='item");
				if(i==0) {
					sb.append(" active");
				}
				sb.append("')>" + 
						"      <img src='resources/upload/"+apList.get(i).get("AP_IMAGE")+"' alt='advertisePhoto'>" + //width='400px'
						"      <div class=\"carousel-caption\">" + 
						i+"번째사진" + 
						"      </div>" + 
						"    </div>");
			}
	sb.append("  </div>" + 
			"  <!-- Controls -->" + 
			"  <a class=\"left carousel-control\" href=\"#carousel-adPhoto-generic\" role=\"button\" data-slide=\"prev\">" + 
			"    <span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>" + 
			"    <span class=\"sr-only\">Previous</span>" + 
			"  </a>" + 
			"  <a class=\"right carousel-control\" href=\"#carousel-adPhoto-generic\" role=\"button\" data-slide=\"next\">" + 
			"    <span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>" + 
			"    <span class=\"sr-only\">Next</span>" + 
			"  </a>" + 
			"</div>"+	
				
//프로필 캐러셀 끝				
				
				/*"                                        <a href=\"#\" class=\"thumbnail\">" + 
				"                                            <img src='resources/upload/"+dp.get("PF_IMAGE")+"' alt=\"detailImage\" class=\"img-rounded\" />" + 
				"                                        </a>" +*/ 
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"" + 
				"                        <div class=\"col-md-5 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body\">" + 
				"" + 
				//"<form name='detailPageInfo'>"+
				"<input type=\"hidden\" id=\"ad_code\" name=\"ad_code\" value='"+ad_code+"'>"+
				"<input type=\"hidden\" id=\"ad_name\" name=\"ad_name\" value='"+dp.get("AD_NAME")+"'>"+
				"                                    <div class=\"caption\">" + 
				"                                        <h3 class=\"display-4\" style=\"text-align: center\">"+dp.get("AD_TITLE")+"<br><br>" + 
				"                                            <small class=\"text-muted\">");
		//주소 시작위치
		//트레이너던 업체던 소속업체가 없는경우
		if(dp.get("T_CID")==null) {
			sb.append(dp.get("M_ADDR"));
		}else {//소속업체 있는경우
			sb.append(dp.get("T_CID_ADDR"));
		}
		//주소 끝위치
		//블루비
		sb.append("											 </small></h3><br>" + 
				"                                        <h5>"+dp.get("AD_CATEGORY")+"</h5><br>" + 
				//옵션출력 시작
				"                                            <p><select id='optionSelect' class=\"form-control\">" + 
				"	                                            <option id='opSelPlz'>상세 옵션을 선택해주세요</option>");
		for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 옵션명 찍어주기
			sb.append("<option id='op"+opCateList.get(i).getOp_code()+"'>"+opCateList.get(i).getOp_content()+" - ");
					if(opCateList.get(i).getOp_period()!=null)
						sb.append("기간 : "+opCateList.get(i).getOp_period()+"-");
					if(opCateList.get(i).getOp_times()!=null)
						sb.append("횟수 : "+opCateList.get(i).getOp_times()+"-");
					if(opCateList.get(i).getOp_day()!=null)
						sb.append("요일 : "+opCateList.get(i).getOp_day()+"-");
					if(opCateList.get(i).getOp_clock()!=null)
						sb.append("시간 : "+opCateList.get(i).getOp_clock()+"-");
					
					
					
					if(opCateList.get(i).getOp_personnel()!=-100) {//정원이 무제한이 아닌경우
						sb.append("남은 인원/정원 : ");
						//남은 자리/정원 뽑아내기
						int cnt=0;
						int leftCnt=0;
						if(psList.size()!=0) {
						for(int j=0; j<psList.size();j++) {
							if(opCateList.get(i).getOp_code().equals(psList.get(j).get("PS_OPCODE"))) {
								cnt++;
							}
							leftCnt = opCateList.get(i).getOp_personnel()-cnt;
						}
						}else if(psList.size()==0){
							leftCnt=opCateList.get(i).getOp_personnel();
						}
						sb.append(leftCnt+"/"+opCateList.get(i).getOp_personnel()+"</option>");
					}else if(opCateList.get(i).getOp_personnel()==-100){//정원이 무제한일경우
						sb.append("인원 제한 없음</option>");
					}
		}
		sb.append("                                            </select></p>");
				//옵션출력 끝 
				
				//옵션코드 리스트, 옵션가격 리스트 뽑아내기
				for(int i=0;i<opCateList.size();i++) {
					sb.append("<input type=\"hidden\" id='"+opCateList.get(i).getOp_code()+"' value='"+opCateList.get(i).getOp_price()+"'/>");
				}
				
		sb.append("                                        <div class=\"row\">" + 
				"                                            <div class=\"col-md-7\">" + 
				"                                                <blockquote class=\"blockquote blockquote-primary\">" + 
				"                                                    <footer class=\"blockquote-footer\">프로모션 및 가격 할인행사</footer>" + 
				"                                                    <p>PT100회 한정수량 골라잡기 이벤트</p>" + 
				"                                                </blockquote>" + 
				"                                            </div>" + 
				"                                            <div class=\"col-md-4\">" + 
				"                                                <blockquote  id='priceShow' class=\"blockquote blockquote-primary\">" + 
				"                                                    <h3><small class=\"text-muted\">가격: </small>0원</h3>" +
				"                                                </blockquote>" + 
				"                                            </div>" + 
				"                                        </div>"+
				"                          				<div style=\"text-align: center\">");//버튼단DIV 시작
		
				//옵션 담당 트레이너 보기버튼 시작				
				if(!dp.get("AD_CATEGORY").toString().equals("일반")) {
					sb.append("                        	 	<button type=\"button\" class=\"btn btn-outline-secondary dropdown-toggle\" data-toggle=\"dropdown\">담당 트레이너 프로필 보기</button>" + 
							  "                         		<div class=\"dropdown-menu\">");
					//프로그램 광고 작성자가 트레이너  //+소속업체가 없으면 개인트레이너 (혹은 업체)
					if(dp.get("M_KIND").toString().equals("t") ) {//&& dp.get("T_CID")==null
						sb.append("<a href='#' id='profilePage"+dp.get("AD_NAME")+"'class='dropdown-item profilePage' data-toggle=\"modal\" data-target=\"#myModal\">"+dp.get("M_NAME")+"</a>");
					//프로그램 광고 작성자가 업체	
					}else if(dp.get("M_KIND").toString().equals("c")) {
						for(int i=0;i<opCateList.size();i++) {//반복문 돌려서 트레이너 + 담당 옵션 찍어주기
							//if(!opCateList.get(i).getOp_trainer().equals(opCateList.get(i+1).getOp_trainer())) {
								sb.append("<a href='#' id='profilePage"+opCateList.get(i).getOp_trainer()+"'class='dropdown-item profilePage' data-toggle=\"modal\" data-target=\"#myModal\">"+opCateList.get(i).getM_name()+"</a>");
							//}
						}
					}
				}
				sb.append("</div>");
				//옵션 담당 트레이너 보기버튼 끝
	  
		//찜버튼 위치
		Set<String> delBtnSet = new HashSet<>();		
		String addBtn=null;
		String delBtn=null;
	
	  	addBtn = "<button id='" + "dibsAdd" + ad_code
				+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart-outline text-danger\"></i></button>";
		
	  	delBtn = "<button id='" + "dibsDelete" + ad_code
				+ "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
				+ "<i class=\"mdi mdi-heart\"></i></button>";
		
	  	if (sessionMb != null && (dibsList != null && dibsList.size() != 0)) { // (회원:찜하지 않은 상품은 찜하기버튼)
			for (int j = 0; j < dibsList.size(); j++) {
				if(dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
					delBtnSet.add(ad_code);
				}
			}
	  	} else if (sessionMb == null && (dibsList == null || dibsList.size() == 0)) {// (dibsList==null) 비회원
			if (session.getAttribute("tempDibs" + ad_code) == null
					|| session.getAttribute("tempDibs" + ad_code) != "dibs") {
				sb.append(addBtn);
			} else if (session.getAttribute("tempDibs" + ad_code) == "dibs") {
				sb.append(delBtn);
			}
		}  // 찜버튼 끝
	  	String delBtnSetJson = new Gson().toJson(delBtnSet);
		mav.addObject("delBtnSet",delBtnSetJson);
	  	
				
				sb.append("<button id='purchase' class=\"btn btn-primary\" role=\"button\" disabled='true'>구매</button> </div>" + 
				//"</form>"+
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"                    <div class=\"row\">" + 
				"                        <div class=\"col-md-9 grid-margin stretch-card\">" + 
				"                            <div class=\"card\">" + 
				"                                <div class=\"card-body dashboard-tabs p-0\">" + 
				"									<ul class=\"nav nav-tabs px-4 nav-justified\" role=\"tablist\">" + 
				"                                        <li class=\"nav-item\">" + 
				"                                            <a class=\"nav-link active\" id=\"detailInfo-tab\" data-toggle=\"tab\" href=\"#detailInfo\" role=\"tab\" aria-controls=\"detailInfo\" aria-selected=\"true\" style=\"border-bottom-color : #71c016; color: #71c016\">상세 정보 보기</a>" + 
				"                                        </li>" + 
				"                                        <li class=\"nav-item\">" + 
				"                                            <a class=\"nav-link\" id=\"review-tab\" data-toggle=\"tab\" href=\"#review\" role=\"tab\" aria-controls=\"review\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">별점 및 후기 보기</a>" + 
				"                                        </li>" + 
				"                                        <li class=\"nav-item\">" + 
				"                                            <a class=\"nav-link\" id=\"question-tab\" data-toggle=\"tab\" href=\"#question\" role=\"tab\" aria-controls=\"question\" aria-selected=\"false\" style=\"border-bottom-color : #71c016; color: #71c016\">문의 보기</a>" + 
				"                                        </li>" + 
				"" + 
				"                                    </ul>"+
				"                                    <div class=\"tab-content py-0 px-0\">" + 
				
				//상세정보 시작
				
				"                                        <div class=\"tab-pane fade active in show\" id=\"detailInfo\" role=\"tabpanel\" aria-labelledby=\"detailInfo-tab\">" + 
				"                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">" + 
				"" + 
				"                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">" + 
				"                                                    <li class=\"nav-item d-none d-lg-block w-100\">" + 
				dp.get("AD_CONTENT")+
				//"광고 작성일 : " + dp.get("AD_DATE").toString()+
			
				"                                                        " + 
				"" + 
				"                                                    </li>" + 
				"                                                </div>" + 
				"                                            </div>" + 
				"                                        </div>" + 
				//상세정보끝, 후기시작
				
				"											<div class=\"tab-pane fade\" id=\"review\" role=\"tabpanel\" aria-labelledby=\"review-tab\">"
				+ "                                            <div class=\"d-flex flex-wrap justify-content-xl-between\">"
				+ "                                                <div class=\"d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item\">"
				+ "                                                    <li class=\"nav-item d-none d-lg-block w-100\">"
				+ "                                                        <p>총 "+rvList.size()+"건의 후기가 있습니다.</p> 평점:");
				//평점 계산
			if(rvList.size()!=0) {
				double pSum=0;
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
				double pAvg = Math.round((pSum/(double)rvList.size()*10)/10.0);
				System.out.println("평점="+pAvg);
				System.out.println("3점 몇명?"+threeStarCnt);
				sb.append(pAvg+". 1점인원:"+oneStarCnt+"명. 2점인원:"+twoStarCnt+"명. 3점인원:"+threeStarCnt+"명. 4점인원:"+fourStarCnt+"명. 5점인원:"+fiveStarCnt);
			}				
			//평점 끝
		sb.append("                                                        <div class=\"table-responsive\">"
				+ "                                                            <table class=\"table table-hover table-condensed\">"
				+ "                                                                <thead>"
				+ "                                                                    <tr>"
				+ "                                                                        <th>별점</th>"
				+ "                                                                        <th>구매옵션</th>"
				+ "                                                                        <th>구매일</th>"
				+ "                                                                        <th>내용</th>"
				+ "                                                                        <th>작성자</th>"
				+ "                                                                        <th>작성일</th>"
				+ "                                                                    </tr>"
				+ "                                                                </thead>"
				+ "                                                                <tbody>");
				for(int i=0; i<rvList.size();i++) {
					sb.append("                                                       <tr>"
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("RV_STPOINT"))+"</td>"
							
							+ "                                                           <td>");
							if(rvList.get(i).get("OP_CONTENT")!=null)
								sb.append(rvList.get(i).get("OP_CONTENT")+"/"); else sb.append("선택 옵션 없음");
							if(rvList.get(i).get("OP_PERIOD")!=null)
								sb.append("기간:"+rvList.get(i).get("OP_PERIOD")+"/"); else sb.append("");
							if(rvList.get(i).get("OP_TIMES")!=null)
								sb.append(""+String.valueOf(rvList.get(i).get("OP_TIMES"))+"회/"); else sb.append("");
							if(rvList.get(i).get("OP_DAY")!=null)
								sb.append(""+rvList.get(i).get("OP_DAY")+"/"); else sb.append("");
							if(rvList.get(i).get("OP_CLOCK")!=null)
								sb.append("시간:"+rvList.get(i).get("OP_CLOCK")); else sb.append("");
							sb.append("</td>"
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("PS_DATE"))+"</td>"
							+ "														      <td>");
							//후기내용이 길다면 더보기 버튼 넣어주기
							if(rvList.get(i).get("RV_CONTENT").length()>20) {
								sb.append("<p>"+rvList.get(i).get("RV_CONTENT").substring(0, 20)+"<p class='text-success showHiddenReview'> 더보기<i class=\"mdi mdi-arrow-down\"></i></p>"
										+"<p class='hiddenReview' hidden>"+rvList.get(i).get("RV_CONTENT").substring(20)+"</p>"
										+"<p class='text-success foldHiddenReview' hidden> 접기<i class=\"mdi mdi-arrow-up\"></i></p>"
										);
							}else {//20글자보다 짧다면 그냥 넣어주기
								sb.append("<p>"+rvList.get(i).get("RV_CONTENT")+"</p>");
							}
							sb.append("                                                   </td>"
							+ "															  <td>"+rvList.get(i).get("RV_NAME")+"</td>"
							+ "                                                           <td>"+String.valueOf(rvList.get(i).get("RV_DATE"))+"</td>"
							+ "                                                       </tr>");
				}
				
		sb.append("                                                                </tbody>"
				+ "                                                            </table>"
				+ "                                                        </div>"+
"                                                    </li>"
				+ "                                                </div>"
				+ "                                            </div>"
				+ "                                        </div>"+
				//후기 끝
				//문의시작
				"<div class=\"tab-pane fade\" id=\"question\" role=\"tabpanel\" aria-labelledby=\"question-tab\">"+
				makeHtmlQna(qaList)+
				"                                        </div>"+//id='question'
				//문의 끝
				"                                    </div>" + 
				"                                </div>" + 
				"                            </div>" + 
				"                        </div>" + 
				"                    </div>" + 
				"" +
				"                </div>");
		return sb.toString();
	}

	private String makeHtmlQna(List<Qna> qaList) {
		StringBuilder sb = new StringBuilder();
		sb.append("                                            <div class='d-flex flex-wrap justify-content-xl-between'>" +
				"                                                <div class='d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item'>" + 
				"                                                    <li class='nav-item d-none d-lg-block w-100'>" + 
				"                                                        <p>총 "+qaList.size()+"건의 문의가 있습니다."+
				"<button type='button' class='btn btn-primary btn-sm qFrm' data-toggle='modal' data-target='#myModal' hidden>문의하기</button></p>"+
				
				"                                                        <div class=\"table-responsive\">" + 
				"                                                            <table class='table table-hover table-condensed'>" + 
				"                                                                <thead>" + 
				"                                                                    <tr>" + 
				"                                                                        <th>번호</th>" + 
				"                                                                        <th>문의 상태</th>" + 
				"                                                                        <th>문의 내용</th>" + 
				"                                                                        <th>작성자</th>" + 
				"                                                                        <th>작성일</th>" + 
				"                                                                    </tr>" + 
				"                                                                </thead>" + 
				"                                                                <tbody>");
				for(int i=0; i<qaList.size();i++) {
					sb.append("<tr><td>"+qaList.get(i).getQa_num()+"</td>"); 
					if(qaList.get(i).getQa_aContent()!=null) {
						sb.append("<td>답변 완료</td>");
					}else {sb.append("<td>답변 대기중</td>");}
					sb.append("<td>");
					//문의내용이 길다면 더보기 버튼 넣어주기
					if(qaList.get(i).getQa_wContent().length()>20) {
						sb.append("<p>"+qaList.get(i).getQa_wContent().substring(0, 20)+"<p class='text-success showHiddenQna'> 더보기<i class='mdi mdi-arrow-down'></i></p>"
								+"<p class='hiddenQna' hidden>"+qaList.get(i).getQa_wContent().substring(20)+"</p>"
								+"<p class='text-success foldHiddenQna' hidden> 접기<i class='mdi mdi-arrow-up'></i></p>"
								);
					}else {//20글자보다 짧다면 그냥 넣어주기
						sb.append("<p>"+qaList.get(i).getQa_wContent()+"</p>");
					}
					sb.append("                                                   </td>"+ 
							  "<td>"+qaList.get(i).getQa_writer()+"</td>" + 
							  "<td>"+qaList.get(i).getQa_wdate()+"</td>" + 
							  "</tr>");
				  	if(qaList.get(i).getQa_aContent()!=null) {
				  		sb.append("<tr><td></td><td><i class='mdi mdi-arrow-right-bold'></i></td>" + 
				  				  "														      <td>");
						//답변내용이 길다면 더보기 버튼 넣어주기
						if(qaList.get(i).getQa_aContent().length()>20) {
							sb.append("<p>"+qaList.get(i).getQa_aContent().substring(0, 20)+"<p class='text-success showHiddenQna'> 더보기<i class='mdi mdi-arrow-down'></i></p>"
									+"<p class='hiddenQna' hidden>"+qaList.get(i).getQa_aContent().substring(20)+"</p>"
									+"<p class='text-success foldHiddenQna' hidden> 접기<i class='mdi mdi-arrow-up'></i></p>"
									);
						}else {//20글자보다 짧다면 그냥 넣어주기
							sb.append("<p>"+qaList.get(i).getQa_aContent()+"</p>");
						}
						sb.append("                                                   </td>"+ 
				  					"<td>"+qaList.get(i).getQa_answer()+"</td>" + 
				  					"<td>"+qaList.get(i).getQa_adate()+"</td>" + 
				  					"</tr>");
				  	}
				}
	  sb.append("                                                                </tbody>" + 
				"                                                            </table>" + 
				"                                                        </div>" + 
				"                                                    </li>" + 
				"                                                </div>" + //d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item
				"                                            </div>"); //d-flex flex-wrap justify-content-xl-between
				
		return sb.toString();
	}

	

	
	public void alarmSelfPurch(Map<String, String> m) {
		System.out.println("AD_CODE:::::::::::"+m.get("ad_code"));
		m.put("al_msid", m.get("m_id"));
		m.put("al_mrid", m.get("m_id"));
		if(kDao.alarmSelfPurch(m)) {
			System.out.println("PS02:구매알림 보내기 성공");
		}
		System.out.println("PS02:구매알림 보내기 실패");
	}
	
	public Map<String,String> alarmSendPurch(Map<String, String> m) {
		int al_code=0;
		System.out.println("AD_CODE:::::::::::"+m.get("ad_code"));
		String ad_name=kDao.getAdName(m.get("ad_code"));
		m.put("al_msid", m.get("m_id"));
		m.put("al_mrid", ad_name);
		al_code = kDao.alarmSendPurch(m);
		if(al_code!=0) {
			m.put("al_code", String.valueOf(al_code));
			System.out.println("PS01:구매알림 보내기 성공");
			return m;
		}
		System.out.println("PS01:구매알림 보내기 실패");
		return null;
	}
	public Map<String, String> alarmSendQuestion(Map<String, String> m) {
		int al_code=0;
		System.out.println("AD_CODE:::::::::::"+m.get("ad_code"));
		String ad_name=kDao.getAdName(m.get("ad_code"));
		m.put("al_msid", m.get("m_id"));
		m.put("al_mrid", ad_name);
		al_code = kDao.alarmSendQuestion(m);
		if(al_code!=0) {
			m.put("al_code", String.valueOf(al_code));
			System.out.println("QA01:문의알림 보내기 성공");
			return m;
		}
		System.out.println("QA01:문의알림 보내기 실패");
		return null;
	}
	public Map<String, String> alarmSendAnswer(Map<String, String> m) {
		int al_code=0;
		System.out.println("QA_NUM:::::::::::"+m.get("qa_num"));
		String qa_writer=kDao.getQuestionId(m.get("qa_num"));
		m.put("al_msid", m.get("m_id"));
		m.put("al_mrid", qa_writer);
		al_code = kDao.alarmSendAnswer(m);
		if(al_code!=0) {
			m.put("al_code", String.valueOf(al_code));
			System.out.println("AQ01:문의답변알림 보내기 성공");
			return m;
		}
		System.out.println("AQ01:문의답변알림 보내기 실패");
		return null;
	}
	public Map<String, String> alarmSendSskReq(Map<String, String> m) {//SS01
		int al_code=0;
		m.put("al_msid", m.get("m_id"));
		m.put("al_mrid", m.get("c_id"));
		al_code = kDao.alarmSendSskReq(m);
		if(al_code!=0) {
			m.put("al_code", String.valueOf(al_code));
			System.out.println("SS01:소속요청 보내기 성공");
			return m;
		}
		System.out.println("SS01:소속요청 보내기 실패");
		return null;
	}
	
	public String alarmReceiveAll(String m_id) {
		String json=null;
		List<Alarm> msgList=null;
		msgList = kDao.alarmReceiveAll(m_id);
		if(msgList!=null) {
			String html = makeHTMLAllMsg(msgList);
			List<String> list = new ArrayList<>();
			list.add(html);
			list.add(String.valueOf(msgList.size()));
			json = new Gson().toJson(list);
		}
		
		return json;
	}
	public String alarmConfirm(String al_code) {
		String text="failed";
		System.out.println("AL_CODE:::"+al_code);
		if(kDao.alarmConfirm(al_code)) {
			text="success";
		}
		return text;
	}

	private String makeHTMLAllMsg(List<Alarm> msgList) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<msgList.size(); i++) {
			String time = strTimeCalc(msgList.get(i).getAl_date());
			if(msgList.get(i).getAl_kind().equals("PS01")) {
				sb.append("        <a class='dropdown-item alarm-confirm'>"
						+ "			   <input type='hidden' class='al_code' value='"+msgList.get(i).getAl_code()+"'/>"
						+ "            <div class='item-content'>"
						+ "                <h6 class='font-weight-normal'>"+msgList.get(i).getAl_mSId()+"님이 당신의 상품을 구매하였습니다.</h6>"
						+ "                <p class='font-weight-light small-text mb-0 text-muted'>"
											+time
						+ "                </p>"
						+ "            </div>"
						+ "        </a>");
			}//PS01 END
			if(msgList.get(i).getAl_kind().equals("PS02")) {
				sb.append("        <a class='dropdown-item alarm-confirm'>"
						+ "			   <input type='hidden' class='al_code' value='"+msgList.get(i).getAl_code()+"'/>"
						+ "            <div class='item-content'>"
						+ "                <h6 class='font-weight-normal'>상품을 성공적으로 구매하였습니다.</h6>"
						+ "                <p class='font-weight-light small-text mb-0 text-muted'>"
											+time
						+ "                </p>"
						+ "            </div>"
						+ "        </a>");
			}//PS02 END
			if(msgList.get(i).getAl_kind().equals("QA01")) {
				sb.append("        <a class='dropdown-item alarm-confirm'>"
						+ "			   <input type='hidden' class='al_code' value='"+msgList.get(i).getAl_code()+"'/>"
						+ "            <div class='item-content'>"
						+ "                <h6 class='font-weight-normal'>답변을 기다리는 문의를 받았습니다.</h6>"
						+ "                <p class='font-weight-light small-text mb-0 text-muted'>"
											+time
						+ "                </p>"
						+ "            </div>"
						+ "        </a>");
			}//QA01 END
			if(msgList.get(i).getAl_kind().equals("AQ01")) {
				sb.append("        <a class='dropdown-item alarm-confirm'>"
						+ "			   <input type='hidden' class='al_code' value='"+msgList.get(i).getAl_code()+"'/>"
						+ "            <div class='item-content'>"
						+ "                <h6 class='font-weight-normal'>문의에 대한 답변이 도착하였습니다.</h6>"
						+ "                <p class='font-weight-light small-text mb-0 text-muted'>"
											+time
						+ "                </p>"
						+ "            </div>"
						+ "        </a>");
			}//AQ01 END
			if(msgList.get(i).getAl_kind().equals("SS01")) {
				sb.append("        <a class='dropdown-item alarm-confirm'>"
						+ "			   <input type='hidden' class='al_code' value='"+msgList.get(i).getAl_code()+"'/>"
						+ "            <div class='item-content'>"
						+ "                <h6 class='font-weight-normal'>트레이너의 소속계약 요청이 들어왔습니다.</h6>"
						+ "                <p class='font-weight-light small-text mb-0 text-muted'>"
											+time
						+ "                </p>"
						+ "            </div>"
						+ "        </a>");
			}//SS01 END
			
		}
		return sb.toString();
	}

	private String strTimeCalc(LocalDateTime sendTime) {
		String str=null;
		LocalDateTime today = LocalDateTime.now();
		long sec = ChronoUnit.SECONDS.between(sendTime, today);
		long min = ChronoUnit.MINUTES.between(sendTime, today);
		long hour = ChronoUnit.HOURS.between(sendTime, today);
		long days = ChronoUnit.DAYS.between(sendTime, today); 
		long weeks = ChronoUnit.WEEKS.between(sendTime, today); 
		long months = ChronoUnit.MONTHS.between(sendTime, today); 
		long years = ChronoUnit.YEARS.between(sendTime, today);
		if(sec<60) str = "방금 전";
		else if(min<60) str = min+" 분 전"; 
		else if(hour<24) str = hour+" 시간 전";
		else if(days<7) str = days+" 일 전";
		else if(weeks<5) str = weeks+" 주 전";
		else if(months<12) str = months+" 개월 전";
		else if(years<1000) str = years+" 년 전";
		return str;
	}

	

	

	

	

	
}