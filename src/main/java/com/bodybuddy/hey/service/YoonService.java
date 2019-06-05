package com.bodybuddy.hey.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.dao.YoonDao;
import com.google.gson.Gson;


@Service
public class YoonService {
	@Autowired
	private YoonDao yDao;
	@Autowired
	HttpSession session;
	
	ModelAndView mav;

	

	public ModelAndView mainList() {
		mav=new ModelAndView();
		String view=null;
		List<Map<String, String>> mainList=null;
		
		mainList=yDao.mainList();
		String html = makeHTMLMainList(mainList);
		mav.addObject("mainListHTML",html);
		view="main";
		
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLMainList(List<Map<String,String>> mainList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"col-md-12 card scroll \">\r\n" + 
				"                            <!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도끝-->\r\n" + 
				"                            <div class=\"card\">\r\n" + 
				"                                <div class=\"card-body\">\r\n" + 
				"                                    <p class=\"card-title\">총"+mainList.size()+"건의 결과가 있습니다.</p>\r\n" + 
				"                                    <div class=\"row\">\r\n");
		
				for(int i=0; i<mainList.size();i++) {
				  sb.append("                                        <div class=\"col-sm-6 col-md-3\">\r\n" + 
							"                                            <div class=\"thumbnail\">\r\n" + 
							"                                                <img alt=\"100%x200\" src='"+mainList.get(i).get("PF_IMAGE")+"' data-holder-rendered=\"true\" style=\"height: 200px; width: 100%; display: block;\">\r\n" + 
							"                                                <div class=\"caption\">\r\n" + 
							"                                                    <br>\r\n" + 
							"                                                    <h3 id=\"thumbnail-label\">");
							if(mainList.get(i).get("AD_KIND")=="mo") {
								sb.append("일반 회원 모집 : ");
							}
							if(mainList.get(i).get("AD_KIND")=="p") {
								sb.append("프로그램 : ");
							}
							sb.append(mainList.get(i).get("AD_TITLE")+"<a class=\"anchorjs-link\" href=\"#thumbnail-label\"><span class=\"anchorjs-icon\"></span></a></h3>\r\n"); 
							
							//일반 회원 모집일 경우 :자기주소
							if(mainList.get(i).get("AD_KIND")=="mo") {
								sb.append("                                                    <p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
							}
							//프로그램일 경우 
							if(mainList.get(i).get("AD_KIND")=="p") {
								//개인 트레이거나, 소속트레이너이면서 홈트레이닝일경우 : 자기주소
								if(mainList.get(i).get("T_CID")==null || mainList.get(i).get("T_CID")!=null && mainList.get(i).get("AD_CATEGORY").contains("홈트레이닝")) {
									sb.append("                                                    <p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
								}
								//소속 트레이너이면서 홈트레이닝 아닌 경우 : 소속업체주소
								if(mainList.get(i).get("T_CID")!=null && mainList.get(i).get("AD_CATEGORY").contains("홈트레이닝")==false) {
									sb.append("                                                    <p>"+mainList.get(i).get("T_CID_ADDR")+"</p>\r\n");
								}
							}
							 
				  sb.append("                                                    <p>"+mainList.get(i).get("AD_CATEGORY")+"</p>\r\n" + 
							"                                                    <p><a href='"+"detailpage/"+mainList.get(i).get("AD_CODE")+"' class=\"btn btn-primary\" role=\"button\">상세보기</a> "
									+ "<a href='"+"/dibsadd"+"' class=\"btn btn-default\" role=\"button\"><button type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">\r\n" + 
							"                                                                <i class=\"mdi mdi-heart-outline text-danger\"></i>\r\n" + 
							"                                                            </button></a></p>\r\n" + 
							"                                                </div>\r\n" + 
							"                                            </div>\r\n" + 
							"                                        </div>\r\n"
							);
				}
	  sb.append("            	                    </div>\r\n" + 
				"                                </div>\r\n" + 
				"                            </div>\r\n" + 
				"                        </div>");
		
		
		return sb.toString();
	}


	
	
}
