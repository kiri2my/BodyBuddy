package com.bodybuddy.hey.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.OpCategory;
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
		List<OpCategory> opCateListAll=null;
		
		mainList = yDao.mainList();
		opCateListAll  = yDao.opCateListAll();
		
		String html = makeHTMLMainList(mainList, opCateListAll);
		mav.addObject("mainListHTML",html);
		view="main";
		
		mav.setViewName(view);
		return mav;
	}

	private String makeHTMLMainList(List<Map<String,String>> mainList, List<OpCategory> opCateListAll) {
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
								//개인 트레인경우 : 자기주소
								if(mainList.get(i).get("T_CID")==null) {
									sb.append("                                                    <p>"+mainList.get(i).get("M_ADDR")+"</p>\r\n");
								}
								//소속 트레이너인경우 : 소속업체주소
								if(mainList.get(i).get("T_CID")!=null) {
									sb.append("                                                    <p>"+mainList.get(i).get("T_CID_ADDR")+"</p>\r\n");
								}
							}
							 
				  sb.append("                                                    <p>");
				  for(int j=0;j<opCateListAll.size();j++) {//옵션 모두 반복문 돌려서 프로그램 옵션 카테고리 찍어주기
					  System.out.println("opCateListAll.size()==========="+opCateListAll.size());
					  System.out.println("mainList.get(i).get(\"AD_CODE\")==========="+mainList.get(i).get("AD_CODE"));
					  
					  if(opCateListAll.get(j).getOp_adcode().equals(mainList.get(i).get("AD_CODE"))) {
						  System.out.println("j==========="+j);
						  System.out.println("opCateListAll.get(j).getOp_category()==========="+opCateListAll.get(j).getOp_category());
						  sb.append(opCateListAll.get(j).getOp_category()+"/");
					  }
				  }
				  
				  sb.append("</p>\r\n" + 
							"                                                    <p><a href='"+"detail/page/"+mainList.get(i).get("AD_CODE")+"' class=\"btn btn-primary\" role=\"button\">상세보기</a> "
									+ "<a href='"+"/dibsadd?ad_code="+mainList.get(i).get("AD_CODE")+"' class=\"btn btn-default\" role=\"button\"><button type=\"button\" class=\"btn btn-outline-secondary btn-rounded btn-icon\">\r\n" + 
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
