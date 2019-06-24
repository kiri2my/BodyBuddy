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
					cate="�씪諛�";
					break;
				case "cateFitness":
					cate="�뵾�듃�땲�뒪";
					break;
				case "cateHomeTraining":
					cate="�솃�듃�젅�씠�떇";
					break;
				case "catePilates":
					cate="�븘�씪�뀒�뒪";
					break;
				case "cateYoga":
					cate="�슂媛�";
					break;
			}
		}
		
		if(sido!=null) {//二쇱냼吏�媛� �엳�떎硫�
			Map<String,String> local = new HashMap<>();
			local.put("sido", sido);
			local.put("sigungu", sigungu);
			System.out.println("cateGORY====================="+cate);
			if(cate!=null) { //二쇱냼吏� �엳�뒗寃쎌슦 cate�엳�뒗寃쎌슦
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
		}else {//二쇱냼吏�媛� �뾾�떎硫�
			System.out.println("cateGORY====================="+cate);
			if(cate!=null) {//二쇱냼吏� �뾾�뒗寃쎌슦 cate�엳�뒗寃쎌슦
				mainList = yDao.mainListCate(cate);
			}else {
				mainList = yDao.mainList();
			}
		}
		
		// 濡쒓렇�씤 �릺�뼱�엳�쓣 �떆
		Member sessionMb = (Member) session.getAttribute("mb");
		System.out.println("�씠寃� �꼸�씠 �븘�땲�씪怨�? sessionMb=" + sessionMb);
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
				"                            <!--md-12硫� �솕硫댁뿉 苑� 李④퀬 md-7由ъ뒪�듃, md-5吏��룄�걹-->\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">珥�"+mainList.size()+"嫄댁쓽 寃곌낵媛� �엳�뒿�땲�떎.</p>\r\n" + 
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
							
							//二쇱냼 �떆�옉�쐞移�
							//�듃�젅�씠�꼫�뜕 �뾽泥대뜕 �냼�냽�뾽泥닿� �뾾�뒗寃쎌슦
							if(mainList.get(i).get("T_CID")==null) {
								sb.append("<p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
							}else {//�냼�냽�뾽泥� �엳�뒗寃쎌슦
								sb.append("<p>"+mainList.get(i).get("T_CID_ADDR")+"</p>\r\n");
							}
							//二쇱냼 �걹�쐞移�
							 
				  sb.append("<p>");
				  /*for(int j=0;j<opCateListAll.size();j++) {//�샃�뀡 紐⑤몢 諛섎났臾� �룎�젮�꽌 �봽濡쒓렇�옩 �샃�뀡 移댄뀒怨좊━ 李띿뼱二쇨린
					  if(opCateListAll.get(j).getOp_adcode().equals(ad_code)) {
						  sb.append(opCateListAll.get(j).getOp_category()+"/");
					  }
				  }*/ //'AD'_CATEGORY濡� �삷寃⑥삤硫댁꽌 痍⑥냼
				  if(ad_category.equals("�씪諛�")) {
					  sb.append("�씪諛� �젙湲� �쉶�썝 紐⑥쭛");
				  }else {
					  sb.append(ad_category);
				  }
				  
				  
				  
				  sb.append("</p>\r\n" + //"detailpage?ad_code="+ad_code //"detail/page/"+ad_code+"
							"<p><a href='detailpage?ad_code="+ad_code+"' id='showdetail"+ad_code+"' class='btn btn-primary' role=\"button\">상세보기</a> ");
				// 李쒕쾭�듉 �쐞移� �떆�옉
				  delBtn = "<button id='" + "dibsDelete" + ad_code
							+ "' type=\"button\" class=\"btn btn-outline-danger btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart\"></i></button>";
				  addBtn = "<button id='" + "dibsAdd" + ad_code
							+ "'type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">"
							+ "<i class=\"mdi mdi-heart-outline text-danger\"></i></button>";
				  
				  
					
					
					if (sessionMb != null && (dibsList != null && dibsList.size() != 0)) { // (�쉶�썝:李쒗븯吏� �븡�� �긽�뭹�� 李쒗븯湲곕쾭�듉)
						// StringBuilder sb2=null;
						for (int j = 0; j < dibsList.size(); j++) {
							if(dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								delBtnSet.add(ad_code);
							}
								
							// �쉶�썝 : 李쒗븳�긽�뭹 李� 痍⑥냼踰꾪듉
							//else if (!dibsList.get(j).get("D_ADCODE").equals(ad_code)) {
								//addBtnSet.add(ad_code);
							//}
						}
						// �쉶�썝�씤�뜲 李� �븯�굹�룄 �뾾�쓣�븣�룄 dibsList null�씪�닔�엳�쓬 : 李쒗븯湲곕쾭�듉
					//} else if (sessionMb != null && (dibsList == null || dibsList.size() == 0)) {
						//addBtnMap.put(ad_code,addBtn);
					} else if (sessionMb == null && (dibsList == null || dibsList.size() == 0)) {// (dibsList==null) 鍮꾪쉶�썝
						// 鍮꾪쉶�썝 �꽭�뀡�뿉 李쒗븳�긽�뭹 �븘�땲硫� 李쒗븯湲곕쾭�듉
						Enumeration<String> names = session.getAttributeNames();
						while (names.hasMoreElements()) {
							System.out.println("李쏯AMES=" + names.nextElement());
						}
						if (session.getAttribute("tempDibs" + ad_code) != null) {
							System.out.println("李쒖컻李�!" + session.getAttribute("tempDibs" + ad_code));
						}
						if (session.getAttribute("tempDibs" + ad_code) == null
								|| session.getAttribute("tempDibs" + ad_code) != "dibs") {
							sb.append(addBtn);
							// 鍮꾪쉶�썝 �꽭�뀡�뿉 李쒗븳 �긽�뭹 李쒖랬�냼踰꾪듉 :
							// session.setAttribute("tempDibs"+d_adcode,"dibs")/session.getAttribute("tempDibs"+d_adcode)
						} else if (session.getAttribute("tempDibs" + ad_code) == "dibs") {
							sb.append(delBtn);
						}
					} 
					
					// 李쒕쾭�듉 �걹
				  
								
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
					+ "													<td>�븯�뒗�슂�씪:" + getprogramListN.get(i).get("OP_DAY")+",�떆媛�:"+ getprogramListN.get(i).get("OP_CLOCK")+",�슏�닔:"+optimes
					+ "</td>\r\n" + "													<td>"
					+ getprogramListN.get(i).get("DA_OPPERIOD") + "</td>\r\n"
					+ "													<td>"
					+ getprogramListN.get(i).get("AD_CATEGORY") + "</td>\r\n"
					+ "													<td>" + getprogramListN.get(i).get("DA_STATUS")
					+ "</td>\r\n" + "<td><button class='btn btn-dark btn-lg btn-block'>�긽�떞�궡�뿭蹂닿린</button>"
							+ "<input type='hidden' id='op_code' name='testInput' value='"+getprogramListN.get(i).get("OP_CODE")+"'/>"
							+"<input type='hidden' value='"+getprogramListN.get(i).get("PS_MID")+"'/></td>"
					+ "		<td><button class='btn btn-dark' onclick='yyyyyy("+getprogramListN.get(i).get("PS_CODE")+")'>異쒖꽍泥댄겕�솗�씤</button>"
					+"</td>"
					+ "													<td><a href='" + "reviewwritefrm?ps_code="
					+ getprogramListN.get(i).get("PS_CODE") + "&m_id=" + getprogramListN.get(i).get("PS_MID")
					+ "'><button class='btn btn-dark'>�썑湲곗벐湲�</button></a></td>\r\n" + "												</tr>");
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
					+ "</td>\r\n" + "													<td><button class='btn btn-dark' onclick='yyyyyy("+getnormalListN.get(i).get("PS_CODE")+")'>異쒖꽍泥댄겕�솗�씤</button></td>\r\n"
					+ "													<td><a href='"+"reviewwritefrm?ps_code="+getnormalListN.get(i).get("PS_CODE")+"&m_id="+getnormalListN.get(i).get("PS_MID")+"'><button class='btn btn-dark'>�썑湲곗벐湲�</button></a></td>\r\n"
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
					+ "													<td><button class='btn btn-dark'>�궘�젣</button>"
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
			String alert = "alert('�씠誘� �빐�떦 愿묎퀬湲��뿉 �썑湲곕벑濡앹쓣 �븯�뀲�뒿�땲�떎');";
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
				"													<td><a href='"+"counseln?cs_opcode="+getCounselListN.get(i).get("CS_OPCODE")+"&cs_date="+Date+"' target='_blank'><button class='btn btn-dark btn-lg btn-block'>�옄�꽭�엳蹂닿린</button></a></td>\r\n" + 
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