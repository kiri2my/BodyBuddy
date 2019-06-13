package com.bodybuddy.hey.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Counsel;
import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.OpCategory;
import com.bodybuddy.hey.bean.Review;
import com.bodybuddy.hey.dao.YoonDao;
import com.google.gson.Gson;

@Service
public class YoonService {
	@Autowired
	private YoonDao yDao;

	ModelAndView mav;
	@Autowired
	HttpSession session;

	public ModelAndView mainList(String sido, String sigungu, String extra) {
		mav=new ModelAndView();
		String view=null;
		List<Map<String, String>> mainList=null;
		List<OpCategory> opCateListAll=null;
		List<Map<String, String>> dibsList=null;
		mav.addObject("showMap",false);
		if(sido!=null) {
			Map<String,String> local = new HashMap<>();
			local.put("sido", sido);
			local.put("sigungu", sigungu);
			if(extra.contains("/")){
		    	String[] extraSplit = extra.split("/");
				local.put("extra1", extraSplit[0]);
				local.put("extra2", extraSplit[1]);
				mainList = yDao.mainMapList2(local);
			}else {
				local.put("extra", extra);
				mainList = yDao.mainMapList1(local);
			}
			mav.addObject("showMap",true);
			mav.addAllObjects(local);
		}else {
			mainList = yDao.mainList();
		}
		opCateListAll  = yDao.opCateListAll();
		
		// 로그인 되어있을 시
		Member sessionMb = (Member) session.getAttribute("mb");
		System.out.println("이게 널이 아니라고? sessionMb=" + sessionMb);
		if (sessionMb != null) {
			String d_id = sessionMb.getM_id();
			System.out.println("sessionMb.getM_id();sessionMb.getM_id();" + sessionMb.getM_id());
			dibsList = yDao.dibsN(d_id);
		}	
		String html = makeHTMLMainList(mainList, opCateListAll, dibsList, sessionMb);
		mav.addObject("mainListHTML",html);
		String jsonMainList = new Gson().toJson(mainList);
		mav.addObject("jsonMainList", jsonMainList);
		
		view="main";
		mav.setViewName(view);
		return mav;
	}
	private String makeHTMLMainList(List<Map<String, String>> mainList, List<OpCategory> opCateListAll,
			List<Map<String, String>> dibsList, Member sessionMb) {
StringBuilder sb = new StringBuilder();
		
		sb.append("<div id='listCard' class=\"col-md-12 card scroll\">\r\n" + 
				"                            <!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도끝-->\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">총"+mainList.size()+"건의 결과가 있습니다.</p>\r\n" + 
				"                                    <div class=\"row\">\r\n");
		
				for(int i=0; i<mainList.size();i++) {
					String ad_code = mainList.get(i).get("AD_CODE").toString();
				  sb.append("                                    <div class=\"col-sm-6 col-md-3\">\r\n" + 
							"                                        <div class=\"thumbnail\">\r\n" + 
							"                                            <img alt=\"100%x200\" src='"+mainList.get(i).get("PF_IMAGE")+"'"+
																				"data-holder-rendered=\"true\" style=\"height: 200px; width: 100%; display: block;\">"+ 
							"                                            <div class=\"caption\">\r\n" + 
							"                                                <br>\r\n" + 
							"                                                <h3 id=\"thumbnail-label\">");
							if(mainList.get(i).get("AD_KIND")=="mo") {
								sb.append("일반 회원 모집 : ");
							}
							if(mainList.get(i).get("AD_KIND")=="p") {
								sb.append("프로그램 : ");
							}
							sb.append(mainList.get(i).get("AD_TITLE")+"<a class=\"anchorjs-link\" href=\"#thumbnail-label\">"+
									 "<span class=\"anchorjs-icon\"></span></a></h3>"); 
							
							//일반 회원 모집일 경우 :자기주소
							if(mainList.get(i).get("AD_KIND")=="mo") {
								sb.append("<p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
							}
							//프로그램일 경우 
							if(mainList.get(i).get("AD_KIND")=="p") {
								//개인 트레인경우 : 자기주소
								if(mainList.get(i).get("T_CID")==null) {
									sb.append("<p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
								}
								//소속 트레이너인경우 : 소속업체주소
								if(mainList.get(i).get("T_CID")!=null) {
									sb.append("<p>"+mainList.get(i).get("T_CID_ADDR")+"</p>\r\n");
								}
							}
							 
				  sb.append("<p>");
				  for(int j=0;j<opCateListAll.size();j++) {//옵션 모두 반복문 돌려서 프로그램 옵션 카테고리 찍어주기
					  if(opCateListAll.get(j).getOp_adcode().equals(ad_code)) {
						  sb.append(opCateListAll.get(j).getOp_category()+"/");
					  }
				  }
				  
				  
				  sb.append("</p>\r\n" + //"detailpage?ad_code="+ad_code //"detail/page/"+ad_code+"
							"<p><a href='"+"detailpage?ad_code="+ad_code+"' class=\"btn btn-primary\" role=\"button\">상세보기</a> ");
							
				// 찜버튼 위치

					if (sessionMb == null) {
						System.out.println("sessionMb는 null" + i);
					} else if (sessionMb != null) {
						System.out.println("sessionMb는 있음" + i);
					}
					if (dibsList == null) {
						System.out.println("dibsList는 null" + i);
					} else if (dibsList != null) {
						System.out.println("dibsList는 있음" + i);
						System.out.println("dibsList 사이즈" + dibsList.size());
					}

					String addBtn = "<a class=\"btn btn-default\" role=\"button\">" + "<button id='" + "dibsAdd" + ad_code
							+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart-outline text-danger\"></i>\r\n" + "</button></a>";

					String delBtn = "<a class=\"btn btn-default\" role=\"button\">" + "<button id='" + "dibsDelete"
							+ ad_code + "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart\"></i>\r\n" + "</button></a>";

					if (sessionMb != null && (dibsList != null && dibsList.size() != 0)) { // (회원:찜하지 않은 상품은 찜하기버튼)
						// StringBuilder sb2=null;
						for (int j = 0; j < dibsList.size(); j++) {
							if(!dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								sb.append(addBtn);
							// 회원 : 찜한상품 찜 취소버튼
							}else if (dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								sb.append(delBtn);
							}
							
						}
						//중복버튼 제거
						StringBuilder sb2 = null;
						if (sb.toString().contains("dibsDelete")) {//찜 취소버튼이 한개라도 있다면 
							sb2 = new StringBuilder(sb.toString().replace(addBtn, ""));//찜하기 버튼을 모두 제거
						}else if (!sb.toString().contains("dibsDelete")) {//찜 취소버튼이 한개도 없다면
							sb2 = new StringBuilder(sb.toString().replace(addBtn, ""));//찜하기 버튼을 한개 빼고 모두 제거
							sb.append(addBtn);
						}
						sb=sb2;
						
						
						// 회원인데 찜 하나도 없을때도 dibsList null일수있음 : 찜하기버튼
					} else if (sessionMb != null && (dibsList == null || dibsList.size() == 0)) {
						sb.append(addBtn);
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
					} // 찜버튼 끝
				  
								
						sb.append("</p>\r\n" + 
								  "                                                </div>\r\n" + 
								  "                                            </div>\r\n" + 
								  "                                        </div>\r\n"
						);
				}//end For
				
	  sb.append("            	                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>");
		
		
		return sb.toString();
	}

	public ModelAndView programListN(String m_id) {
		System.out.println("idididididi=" + m_id);
		session.setAttribute("id", m_id);
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
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getprogramListN.get(i).get("AD_TITLE")
					+ "</td>\r\n" + "													<td>"
					+ getprogramListN.get(i).get("OP_TRAINER") + "</td>\r\n"
					+ "													<td>" + getprogramListN.get(i).get("OP_CONTENT")
					+ "</td>\r\n" + "													<td>"
					+ getprogramListN.get(i).get("OP_PERIOD") + "</td>\r\n"
					+ "													<td>"
					+ getprogramListN.get(i).get("AD_CATEGORY") + "</td>\r\n"
					+ "													<td>" + getprogramListN.get(i).get("DA_STATUS")
					+ "</td>\r\n" + "<td><button class='btn btn-dark btn-lg btn-block'>상담내역보기</button>"
							+ "<input type='hidden' value='"+getprogramListN.get(i).get("OP_CODE")+"'/>"
							+"<input type='hidden' value='"+getprogramListN.get(i).get("PS_MID")+"'/></td>"
					+ "		<td>출결현황보기</td>\r\n"
					+ "													<td><a href='" + "reviewwritefrm?ps_code="
					+ getprogramListN.get(i).get("PS_CODE") + "&m_id=" + getprogramListN.get(i).get("PS_MID")
					+ "'>후기쓰기</a></td>\r\n" + "												</tr>");
		}
		return sb.toString();
	}

	private String makeHTMLnorPage(List<Map<String, String>> getnormalListN) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getnormalListN.size(); i++) {
			sb.append("												<tr role=\"row\" class=\"odd\">\r\n"
					+ "													<td>" + getnormalListN.get(i).get("AD_TITLE")
					+ "</td>\r\n" + "													<td>"
					+ getnormalListN.get(i).get("C_BNAME") + "</td>\r\n"
					+ "													<td>" + getnormalListN.get(i).get("OP_PERIOD")
					+ "</td>\r\n" + "													<td>출결현황보기</td>\r\n"
					+ "													<td>" + getnormalListN.get(i).get("DA_STATUS")
					+ "</td>\r\n" + "													<td><a href='"+"reviewwritefrm?ps_code="+getnormalListN.get(i).get("PS_CODE")+"&m_id="+getnormalListN.get(i).get("PS_MID")+"'>후기쓰기</a></td>\r\n"
					+ "												</tr>");
		}
		return sb.toString();
	}

	public ModelAndView payListN(String m_id) {
		session.setAttribute("id", m_id);
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
					+ "                            <td>" + getPayListN.get(i).get("OP_PERIOD") + "</td>\r\n"
					+ "                            <td>" + price + "</td>\r\n" + "                            <td>"
					+ Date + "</td>\r\n" + "                       </tr>");

		}

		return sb.toString();
	}

	public ModelAndView modifyN(String m_id) {
		session.setAttribute("id", m_id);
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
		session.setAttribute("id", m_id);
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
					+ "													<td><button class='deliteButton'>삭제</button>"
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
		boolean insertRv = yDao.reviewInsert(rv);
		view = "manage/normal/normalMain";
		mav.setViewName(view);
		return mav;
	}
	
	public ModelAndView counselListn(String cs_opcode, String cs_mid) {
		List<Map<String, String>> getCounselListN = null;
		String view=null;
		Map<String,String> cs = new HashMap<>();
		cs.put("CS_ADCODE", cs_opcode);
		cs.put("CS_MID", cs_mid);
		getCounselListN=yDao.getCounsel(cs_opcode,cs_mid);
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
				"													<td><a href='"+"counseln?cs_opcode="+getCounselListN.get(i).get("CS_OPCODE")+"&cs_date="+Date+"'><button class='btn btn-dark btn-lg btn-block'>자세히보기</button></a></td>\r\n" + 
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
	
	

	
}