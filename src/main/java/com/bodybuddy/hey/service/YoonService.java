package com.bodybuddy.hey.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Counsel;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Review;
import com.bodybuddy.hey.dao.YoonDao;
import com.bodybuddy.hey.userClass.UploadFile;
import com.google.gson.Gson;

@Service
public class YoonService {
	@Autowired
	private YoonDao yDao;

	ModelAndView mav;
	@Autowired
	HttpSession session;
	@Autowired
	private UploadFile upload;
	
	
	
	public ModelAndView mainList(String sido, String sigungu, String extra, String cate) {
		mav=new ModelAndView();
		String view=null;
		List<Map<String, String>> mainList=null;
		List<Map<String, String>> dibsList=null;
		mav.addObject("showMap",false);
		if(cate!=null) {
			switch(cate) {
				case "cateAll":
					cate=null;
					break;
				case "cateNormal":
					cate="일반";
					break;
				case "cateFitness":
					cate="피트니스";
					break;
				case "cateHomeTraining":
					cate="홈트레이닝";
					break;
				case "catePilates":
					cate="필라테스";
					break;
				case "cateYoga":
					cate="요가";
					break;
			}
		}
		
		if(sido!=null) {//주소지가 있다면
			Map<String,String> local = new HashMap<>();
			local.put("sido", sido);
			local.put("sigungu", sigungu);
			System.out.println("cateGORY====================="+cate);
			if(cate!=null) { //주소지 있는경우 cate있는경우
				local.put("cate", cate);
			}
			if(extra.contains("/")){
		    	String[] extraSplit = extra.split("/");
				local.put("extra1", extraSplit[0]);
				local.put("extra2", extraSplit[1]);
				mainList = yDao.mainListMap(local);
			}else {
				local.put("extra", extra);
				mainList = yDao.mainListMap(local);
			}
			mav.addObject("showMap",true);
			mav.addAllObjects(local);
		}else {//주소지가 없다면
			System.out.println("cateGORY====================="+cate);
			if(cate!=null) {//주소지 없는경우 cate있는경우
				mainList = yDao.mainListCate(cate);
			}else {
				mainList = yDao.mainList();
			}
		}
		
		// 로그인 되어있을 시
		Member sessionMb = (Member) session.getAttribute("mb");
		System.out.println("이게 널이 아니라고? sessionMb=" + sessionMb);
		if (sessionMb != null) {
			String d_id = sessionMb.getM_id();
			System.out.println("sessionMb.getM_id();sessionMb.getM_id();" + sessionMb.getM_id());
			dibsList = yDao.dibsN(d_id);
		}	
		String html = makeHTMLMainList(mainList, dibsList, sessionMb, mav);
		mav.addObject("mainListHTML",html);
		String jsonMainList = new Gson().toJson(mainList);
		mav.addObject("jsonMainList", jsonMainList);
		
		view="main";
		mav.setViewName(view);
		return mav;
	}
	private String makeHTMLMainList(List<Map<String, String>> mainList, List<Map<String, String>> dibsList, 
			Member sessionMb, ModelAndView mav) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div id='listCard' class=\"col-md-12 card scroll\">\r\n" + 
				"                            <!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도끝-->\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">총"+mainList.size()+"건의 결과가 있습니다.</p>\r\n" + 
				"                                    <div class=\"row\">\r\n");
		Set<String> delBtnSet = new HashSet<>();		
		String addBtn=null;
		String delBtn=null;
		for(int i=0; i<mainList.size();i++) {
					String ad_code = mainList.get(i).get("AD_CODE").toString();
					String ad_category = mainList.get(i).get("AD_CATEGORY").toString();
					
				  sb.append("                                    <div class=\"col-sm-6 col-md-3\">\r\n" + 
							"                                        <div class=\"thumbnail\">\r\n" + 
							"                                            <img alt=\"100%x200\" src='resources/upload/"+mainList.get(i).get("PF_IMAGE")+"'"+
																				"data-holder-rendered=\"true\" style=\"height: 200px; width: 100%; display: block;\">"+ 
							"                                            <div class=\"caption\">\r\n" + 
							"                                                <br>\r\n" + 
							"                                                <h3 id=\"thumbnail-label\">");
							sb.append(mainList.get(i).get("AD_TITLE")+"<a class=\"anchorjs-link\" href=\"#thumbnail-label\">"+
									 "<span class=\"anchorjs-icon\"></span></a></h3>"); 
							
							//주소 시작위치
							//트레이너던 업체던 소속업체가 없는경우
							if(mainList.get(i).get("T_CID")==null) {
								sb.append("<p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
							}else {//소속업체 있는경우
								sb.append("<p>"+mainList.get(i).get("T_CID_ADDR")+"</p>\r\n");
							}
							//주소 끝위치
							 
				  sb.append("<p>");
				  /*for(int j=0;j<opCateListAll.size();j++) {//옵션 모두 반복문 돌려서 프로그램 옵션 카테고리 찍어주기
					  if(opCateListAll.get(j).getOp_adcode().equals(ad_code)) {
						  sb.append(opCateListAll.get(j).getOp_category()+"/");
					  }
				  }*/ //'AD'_CATEGORY로 옮겨오면서 취소
				  if(ad_category.equals("일반")) {
					  sb.append("일반 정기 회원 모집");
				  }else {
					  sb.append(ad_category);
				  }
				  
				  
				  
				  sb.append("</p>\r\n" + //"detailpage?ad_code="+ad_code //"detail/page/"+ad_code+"
							"<p><a href='detailpage?ad_code="+ad_code+"' id='showdetail"+ad_code+"' class='btn btn-primary' role=\"button\">상세보기</a> ");

				// 찜버튼 위치 시작
				  
		  delBtn = "<button id='" + "dibsDelete" + ad_code
							+ "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart\"></i></button>";
				  addBtn = "<button id='" + "dibsAdd" + ad_code
							+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart-outline text-danger\"></i></button>";
				  
				  
					
					
					if (sessionMb != null && (dibsList != null && dibsList.size() != 0)) { // (회원:찜하지 않은 상품은 찜하기버튼)
						// StringBuilder sb2=null;
						for (int j = 0; j < dibsList.size(); j++) {
							if(dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								delBtnSet.add(ad_code);
							}
								
							// 회원 : 찜한상품 찜 취소버튼
							//else if (!dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								//addBtnSet.add(ad_code);
							//}
						}
						// 회원인데 찜 하나도 없을때도 dibsList null일수있음 : 찜하기버튼
					//} else if (sessionMb != null && (dibsList == null || dibsList.size() == 0)) {
						//addBtnMap.put(ad_code,addBtn);
					} else if (sessionMb == null && (dibsList == null || dibsList.size() == 0)) {// (dibsList==null) 비회원
						// 비회원 세션에 찜한상품 아니면 찜하기버튼
						Enumeration<String> names = session.getAttributeNames();
						while (names.hasMoreElements()) {
							System.out.println("찜NAMES=" + names.nextElement());
						}
						if (session.getAttribute("tempDibs" + ad_code) != null) {
							System.out.println("찜찜찜!" + session.getAttribute("tempDibs" + ad_code));
						}
						if (session.getAttribute("tempDibs" + ad_code) == null
								|| session.getAttribute("tempDibs" + ad_code) != "dibs") {
							sb.append(addBtn);
							// 비회원 세션에 찜한 상품 찜취소버튼 :
							// session.setAttribute("tempDibs"+d_adcode,"dibs")/session.getAttribute("tempDibs"+d_adcode)
						} else if (session.getAttribute("tempDibs" + ad_code) == "dibs") {
							sb.append(delBtn);
						}
					} 
					
					// 찜버튼 끝
				  
								
						sb.append("</p>\r\n" + 
								  "                                                </div>\r\n" + 
								  "                                            </div>\r\n" + 
								  "                                        </div>\r\n"
						);
				}//end For
		String delBtnSetJson = new Gson().toJson(delBtnSet);
		mav.addObject("delBtnSet",delBtnSetJson);
				
	  sb.append("            	                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>");
		
		
		return sb.toString();
	}

	public ModelAndView programListN(String m_id) {
		System.out.println("idididididi=" + m_id);
		String view = null;
		List<Map<String, String>> getProgramListN = null;
		List<Map<String, String>> getNormalListN = null;
		getProgramListN = yDao.getproListN(m_id);
		getNormalListN = yDao.getnormalListN(m_id);
		String html = makeHTMLproPage(getProgramListN);
		String html2 = makeHTMLnorPage(getNormalListN);

		mav.addObject("programListN", html);
		mav.addObject("getnormalListN", html2);

		view = "manage/normal/normalMain";
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLproPage(List<Map<String, String>> getprogramListN) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getprogramListN.size(); i++) {
			String optimes=String.valueOf(getprogramListN.get(i).get("OP_TIMES"));
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getprogramListN.get(i).get("AD_TITLE")
					+ "</td>\r\n" 
					+ "<td><a href='#' id='profilePage"+getprogramListN.get(i).get("OP_TRAINER")+"'class='dropdown-item profilePage' data-toggle=\"modal\" data-target=\"#myModal\"><button class='btn btn-primary'>"+getprogramListN.get(i).get("OP_TRAINER")+"</button></a></td>\r\n"
					+ "													<td>하는요일:" + getprogramListN.get(i).get("OP_DAY")+",시간:"+ getprogramListN.get(i).get("OP_CLOCK")+",횟수:"+optimes
					+ "</td>\r\n" + "													<td>"
					+ getprogramListN.get(i).get("DA_OPPERIOD") + "</td>\r\n"
					+ "													<td>"
					+ getprogramListN.get(i).get("AD_CATEGORY") + "</td>\r\n"
					+ "													<td>" + getprogramListN.get(i).get("DA_STATUS")
					+ "</td>\r\n" + "<td><button class='btn btn-dark btn-lg btn-block'>상담내역보기</button>"
							+ "<input type='hidden' id='op_code' name='testInput' value='"+getprogramListN.get(i).get("OP_CODE")+"'/>"
							+"<input type='hidden' value='"+getprogramListN.get(i).get("PS_MID")+"'/></td>"
					+ "		<td><button class='btn btn-dark' onclick='yyyyyy("+getprogramListN.get(i).get("PS_CODE")+")'>출석체크확인</button>"
					+"</td>"
					+ "													<td><a href='" + "reviewwritefrm?ps_code="
					+ getprogramListN.get(i).get("PS_CODE") + "&m_id=" + getprogramListN.get(i).get("PS_MID")
					+ "'><button class='btn btn-dark'>후기쓰기</button></a></td>\r\n" + "												</tr>");
		}
		return sb.toString();
	}

	private String makeHTMLnorPage(List<Map<String, String>> getnormalListN) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getnormalListN.size(); i++) {
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getnormalListN.get(i).get("AD_TITLE")
					+ "</td>\r\n"
					+ "<td><a href='#' id='profilePage"+getnormalListN.get(i).get("M_ID")+"'class='dropdown-item profilePage' data-toggle=\"modal\" data-target=\"#myModal\"><button class='btn btn-primary'>"+getnormalListN.get(i).get("C_BNAME")+"</button></a></td>\r\n"
					+ "													<td>" + getnormalListN.get(i).get("DA_OPPERIOD")
					+ "													<td>" + getnormalListN.get(i).get("DA_STATUS")+"</td>\r\n" 
					+ "</td>\r\n" + "													<td><button class='btn btn-dark' onclick='yyyyyy("+getnormalListN.get(i).get("PS_CODE")+")'>출석체크확인</button></td>\r\n"
					+ "													<td><a href='"+"reviewwritefrm?ps_code="+getnormalListN.get(i).get("PS_CODE")+"&m_id="+getnormalListN.get(i).get("PS_MID")+"'><button class='btn btn-dark'>후기쓰기</button></a></td>\r\n"
					+ "												</tr>");
		}
		return sb.toString();
	}

	public ModelAndView payListN(String m_id) {
		String view = null;
		List<Map<String, String>> getPayListN = null;
		getPayListN = yDao.getpayListN(m_id);
		String html = makeHTMLpayPage(getPayListN);
		mav.addObject("payListN", html);
		view = "manage/payHistoryN";
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLpayPage(List<Map<String, String>> getPayListN) {
		StringBuilder sb = new StringBuilder();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < getPayListN.size(); i++) {
			String price = String.valueOf(getPayListN.get(i).get("PS_PRICE"));
			String Date = sdFormat.format(getPayListN.get(i).get("PS_DATE"));
			System.out.println("price========" + price);
			sb.append("                      <tr role=\"row\" class=\"odd\">\r\n"
					+ "                            <td class=\"sorting_1\">" + getPayListN.get(i).get("AD_TITLE")
					+ "</td>\r\n" + "                            <td>" + getPayListN.get(i).get("AD_CATEGORY")
					+ "</td>\r\n" + "                            <td>" + getPayListN.get(i).get("OP_CONTENT") + "</td>\r\n"
					+ "                            <td>" + getPayListN.get(i).get("DA_OPPERIOD") + "</td>\r\n"
					+ "                            <td>" + price + "</td>\r\n" + "                            <td>"
					+ Date + "</td>\r\n" + "                       </tr>");

		}
		return sb.toString();
	}

	public ModelAndView modifyN(String m_id){
		String view = null;
		Member mb = yDao.getModifyN(m_id);
		Member mbPhoto = yDao.getPhotoModifyN(m_id);
		mav.addObject("mb", mb);
		mav.addObject("mbPhoto", mbPhoto);
		view = "manage/infoModifyN";
		mav.setViewName(view);
		return mav;
	}


	public ModelAndView dibsList(String m_id) {
		String view = null;
		List<Map<String, String>> dibs = null;
		dibs = yDao.getdibs(m_id);
		String html = makeHTMLdibsPage(dibs);
		mav.addObject("dibs", html);
		view = "manage/dibsListN";
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLdibsPage(List<Map<String, String>> dibs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dibs.size(); i++) {
			System.out.println("-----------------------==" + dibs.get(i).get("AD_TITLE"));
			sb.append("<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td class=\"sorting_1\">"
					+ dibs.get(i).get("AD_TITLE") + "</td>\r\n"
					+ "													<td><button class='btn btn-dark'>삭제</button>"
					+ "<input type='hidden' id='ad_code' name='ad_code' value='"+dibs.get(i).get("AD_CODE")+"'></td>\r\n"
					+ "												</tr>");
		}
		return sb.toString();
	}
	
	public ModelAndView reviewWriteFrm(String m_id, String ps_code) {
		ModelAndView mav = new ModelAndView();
		Review rv = new Review();
		rv.setRv_name(m_id);
		rv.setRv_pscode(ps_code);
		if (yDao.reviewOverlap(rv)) {// true
			String alert = "alert('이미 해당 광고글에 후기등록을 하셨습니다');";
			mav.addObject("alert", alert);

			mav.setViewName("forward:/infoprogramn");
		} else {// false
			mav.addObject("m_id", m_id);
			mav.addObject("ps_code", ps_code);
			mav.setViewName("manage/review");
		}
		return mav;
	}

	public ModelAndView insertReview(Review rv) {
		String view = null;
		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id=sessionMb.getM_id();
		boolean insertRv = yDao.reviewInsert(rv);
		view = "forward:questionlistn?m_id="+m_id+"";
		mav.setViewName(view);
		return mav;
	}
	
	public ModelAndView counselListn(String cs_opcode, String cs_mid) {
		List<Map<String, String>> getCounselListN = null;
		String view=null;
		Map<String,String> cs = new HashMap<>();
		cs.put("cs_opcode", cs_opcode);
		cs.put("cs_mid", cs_mid);
		getCounselListN=yDao.getCounsel(cs);
		String html = makeHTMLCounselPage(getCounselListN);
		mav.addObject("counselList", html);
		view="manage/counselNList";
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLCounselPage(List<Map<String, String>> getCounselListN) {
		StringBuilder sb=new StringBuilder();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i <  getCounselListN.size(); i++) { 
			String Date = sdFormat.format(getCounselListN.get(i).get("CS_DATE"));
		sb.append("												<tr role=\"row\" class=\"odd\">\r\n" + 
				"													<td><a href='"+"counseln?cs_opcode="+getCounselListN.get(i).get("CS_OPCODE")+"&cs_date="+Date+"' target='_blank'><button class='btn btn-dark btn-lg btn-block'>자세히보기</button></a></td>\r\n" + 
				"													<td>" + Date + "</td>\r\n" + 
				"												</tr>");
		}
		return sb.toString();
	}
	
	public ModelAndView counseln(String cs_opcode, String cs_date) throws ParseException {
		Member sessionMb = (Member) session.getAttribute("mb");
		String view=null;
		String cs_mid=sessionMb.getM_id();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date to = transFormat.parse(cs_date);
		
		
		Counsel cs=new Counsel();
		cs.setCs_opcode(cs_opcode);
		cs.setCs_mid(cs_mid);
		cs.setCs_date(to);
		Counsel cs1=yDao.getcounselN(cs);
		mav.addObject("cs", cs1);
		view = "manage/counselN";
		mav.setViewName(view);
		return mav;
	}
	public ModelAndView memberdelten() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manage/memberDeleteN");
		return mav;
	}
	

	
	public ModelAndView infomodifyn(MultipartHttpServletRequest multi) {
		String view=null;
		Member mb=new Member();

		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id=sessionMb.getM_id();
		String m_birth=sessionMb.getM_birth();
		String m_name=sessionMb.getM_name();
		String m_pw=multi.getParameter("m_pw");
		String m_phone=multi.getParameter("m_phone");
		String m_addr=multi.getParameter("m_addr");
		String m_exaddr=multi.getParameter("m_exaddr");
		String pf_image=multi.getParameter("pf_image");

		
		 int i=yDao.imgOverlap(m_id);
		 if(i==0) {
			upload.fileUp(multi, m_id);
			BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder(); 
			mb.setM_id(m_id);
			mb.setM_pw(pwdEncoder.encode(m_pw));
			mb.setM_phone(m_phone);
			mb.setM_addr(m_addr);
			mb.setM_exaddr(m_exaddr);
			mb.setM_birth(m_birth);
			mb.setM_name(m_name);
			mb.setPf_image(pf_image);
			yDao.updateNorMb(mb);
			Member mb1=yDao.getModifyN(m_id);
			Member mbPhoto = yDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
			return mav;
		 }else if(i>=1) {
			upload.fileUp2(multi, m_id);
			BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder(); 
			mb.setM_id(m_id);
			mb.setM_pw(pwdEncoder.encode(m_pw));
			mb.setM_phone(m_phone);
			mb.setM_addr(m_addr);
			mb.setM_exaddr(m_exaddr);
			mb.setM_birth(m_birth);
			mb.setM_name(m_name);
			mb.setPf_image(pf_image);
			yDao.updateNorMb(mb);
			Member mb1=yDao.getModifyN(m_id);
			Member mbPhoto = yDao.getPhotoModifyN(m_id);
			mav.addObject("mb", mb1);
			mav.addObject("mbPhoto", mbPhoto);
			return mav;
		 }
		 
		return mav;
	}
	public ModelAndView calender(String ps_code) {
		List<Map<String, String>> getDailyListN = null;
		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id=sessionMb.getM_id();
		System.out.println();
		String view=null;
		Map<String,String> ck = new HashMap<>();
		ck.put("ps_code", ps_code);
		ck.put("m_id", m_id);
		getDailyListN=yDao.getDailyList(ck);
		String html = makeHTMLDailyPage(getDailyListN);
		mav.addObject("getDailyList", html);
		view="manage/calenderN";
		mav.setViewName(view);
		return mav;
	}
	
	private String makeHTMLDailyPage(List<Map<String, String>> getDailyListN) {
		StringBuilder sb=new StringBuilder();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i <  getDailyListN.size(); i++) { 
			String Date = sdFormat.format(getDailyListN.get(i).get("DN_DATE"));
		sb.append("												<tr role=\"row\" class=\"odd\">\r\n" + 
				"													<td>" + Date + "</td>\r\n" + 
				"												</tr>");
		}
		return sb.toString();
	}
	
	
	
	public ModelAndView sales() {
		List<Map<String, String>> getSalesList = null;
		List<Map<String, String>> getSalesAllList = null;
		List<Map<String, String>> getSalescList = null;
		List<Map<String, String>> getSalesAllc = null;
		Member sessionMb = (Member) session.getAttribute("mb");
		String m_id=sessionMb.getM_id();
		//Member mb=yDao.getCbname(m_id);
		//String cbname=mb.getC_bname();
		getSalesList = yDao.getsales(m_id);
		getSalesAllList = yDao.getsalesAll(m_id);
		getSalescList = yDao.getSalescList(m_id);
		getSalesAllc = yDao.getSalesAllcList(m_id);
		//getSalescList = yDao.getsalesAlla(m_id);
		String html = makeHTMLsalesList(getSalesList);
		String html2 = makeHTMLsalesAllList(getSalesAllList);
		String html3 = makeHTMLsalescList(getSalescList);
		String html4 = makeHTMLsalesAllcList(getSalesAllc);
		mav.addObject("getSalesList", html);
		mav.addObject("getSalesAllList", html2);
		mav.addObject("getSalescList", html3);
		mav.addObject("getSalesAllcList", html4);
		mav.setViewName("manage/sales");
		
		return mav;
		
	}
	
	
	private String makeHTMLsalesAllcList(List<Map<String, String>> getSalesAllc) {
		StringBuilder sb=new StringBuilder();
		String ps_price=String.valueOf(getSalesAllc.get(0).get("PS_PRICE"));
		sb.append("<td>총 합계</td>\r\n" + 
				"								<td>" + ps_price + "</td>");
		
		return sb.toString();
	}
	private String makeHTMLsalescList(List<Map<String, String>> getSalescList) {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i <  getSalescList.size(); i++) {
			String op_price=String.valueOf(getSalescList.get(i).get("OP_PRICE"));
			String ps_price=String.valueOf(getSalescList.get(i).get("PS_PRICE"));
			String ps_opcode=String.valueOf(getSalescList.get(i).get("PS_OPCODE"));
		sb.append("<tr>\r\n" + 
				"								<th>" + getSalescList.get(i).get("AD_CATEGORY") + "</th>\r\n" + 
				"								<th>" + getSalescList.get(i).get("OP_CONTENT") + "</th>\r\n" + 
				"								<th>" + ps_opcode + "</th>\r\n" + 
				"								<th>" + op_price + "</th>\r\n" + 
				"								<th>" + ps_price + "</th>\r\n" + 
				"							</tr>");
		}
		return sb.toString();
	}
	
	
	private String makeHTMLsalesAllList(List<Map<String, String>> getSalesAllList) {
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i <  getSalesAllList.size(); i++) { 
			String ps_opcode=String.valueOf(getSalesAllList.get(i).get("PS_OPCODE"));
			String op_price=String.valueOf(getSalesAllList.get(i).get("PS_PRICE"));
		sb.append("							<tr>\r\n" + 
				"								<th>" + getSalesAllList.get(i).get("M_NAME") + "</th>\r\n" +  
				"								<th>" + ps_opcode + "</th>\r\n" + 
				"								<th>" + op_price + "</th>\r\n" + 
				"							</tr>");
		}
		return sb.toString();
	}
	
	
	private String makeHTMLsalesList(List<Map<String, String>> getSalesList) {
		StringBuilder sb=new StringBuilder();
		
		for (int i = 0; i <  getSalesList.size(); i++) { 
			String ps_opcode=String.valueOf(getSalesList.get(i).get("PS_OPCODE"));
			String op_price=String.valueOf(getSalesList.get(i).get("OP_PRICE"));
			String ps_price=String.valueOf(getSalesList.get(i).get("PS_PRICE"));
		sb.append("							<tr>\r\n" + 
				"								<th>" + getSalesList.get(i).get("M_NAME") + "</th>\r\n" + 
				"								<th>" + getSalesList.get(i).get("OP_CONTENT") + "</th>\r\n" + 
				"								<th>" + ps_opcode + "</th>\r\n" + 
				"								<th>" + op_price + "</th>\r\n" + 
				"								<th>" + ps_price + "</th>\r\n" + 
				"							</tr>");
		sb.append("<input type='hidden' id='testInput' name='testInput' value='"+getSalesList.get(0).get("T_CID")+"'/>");
		}
		return sb.toString();
	}
	public String chart(String t_cid) {
		String json="";
		List<Map<String, String>> getChartList = null;
		getChartList=yDao.getchart(t_cid);
		json = new Gson().toJson(getChartList);
		return json;
	}
	public String chart2(String t_cid) {
		String json="";
		List<Map<String, String>> getChartList2 = null;
		getChartList2=yDao.getchart2(t_cid);
		json = new Gson().toJson(getChartList2);
		return json;
	}
	
	
	public ModelAndView questionlistN(String m_id) {
		//System.out.println("idididididdsaasd=" + m_id);
		String view = null;
		
		 List<Map<String, String>> getReviewListN = null; 
		 List<Map<String, String>> getQuestionListN = null; 
		 getReviewListN = yDao.getRvListN(m_id);
		 getQuestionListN = yDao.getQuestListN(m_id); 
		 String html =makeHTMLRVPage(getReviewListN); 
		 String html2 = makeHTMLQTPage(getQuestionListN);
		 
		 mav.addObject("reviewListN", html);
		 mav.addObject("questionListN", html2);
		 

		view = "manage/infoRVQAN";
		mav.setViewName(view);
		return mav;
	}
	private String makeHTMLQTPage(List<Map<String, String>> getQuestionListN) {
		StringBuilder sb = new StringBuilder();
		DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (int i = 0; i < getQuestionListN.size(); i++) {
			String Date = sdFormat.format(getQuestionListN.get(i).get("QA_WDATE"));
			String Date2 = sdFormat.format(getQuestionListN.get(i).get("QA_ADATE"));
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getQuestionListN.get(i).get("AD_TITLE")
					+ "</td>\r\n"
					+ "													<td><a style='display: inline;' href='#myModal' role='button' class='cbtn'\r\n" + 
					"													data-toggle='modal' id='cbtn'>내용보기</a>\r\n"
					+ "<input type='hidden' class='mm' name='ad_code' value='"+getQuestionListN.get(i).get("QA_NUM")+"'></td>\r\n"
					
					+ "													<td>" + Date+"</td>\r\n" 
					+ "													<td>" + Date2+"</td>\r\n" 
					+ "													<td></td>\r\n"
					+ "												</tr>");
		}
		
		return sb.toString();
		
	
	}
	private String makeHTMLRVPage(List<Map<String, String>> getReviewListN) {
		StringBuilder sb = new StringBuilder();
				DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < getReviewListN.size(); i++) {
			String Date = sdFormat.format(getReviewListN.get(i).get("RV_DATE"));
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getReviewListN.get(i).get("AD_TITLE")
					+ "</td>\r\n"
					+ "													<td>" + getReviewListN.get(i).get("OP_CONTENT")+"</td>\r\n"
					+ "													<td>" + getReviewListN.get(i).get("RV_CONTENT")+"</td>\r\n" 
					+ "													<td>" + Date+"</td>\r\n" 
					+ "													<td></td>\r\n"
					+ "												</tr>");
		}
		return sb.toString();
	}
	
	
	

	
	/*
	 * public String dailyCheck(String ps_code, String m_id) throws ParseException {
	 * String json=""; List<Map<String, String>> dailyCheck1 = null;
	 * Map<String,String> cs = new HashMap<>(); cs.put("ps_code", ps_code);
	 * cs.put("ps_mid", m_id); dailyCheck1=yDao.getDailyCheck(cs); SimpleDateFormat
	 * transFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * for(int i=0;i<dailyCheck1.size();i++) { String
	 * daily=dailyCheck1.get(i).toString(); Date to = transFormat.parse(daily);
	 * json=new Gson().toJson(to); System.out.println(to); }
	 * 
	 * return json; }
	 */
	


}