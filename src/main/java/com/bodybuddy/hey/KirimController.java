package com.bodybuddy.hey;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bodybuddy.hey.bean.Member;
import com.bodybuddy.hey.bean.Payment;
import com.bodybuddy.hey.bean.Qna;
import com.bodybuddy.hey.service.KirimService;
import com.google.gson.JsonObject;


//로그아웃시 (SessionStatus status){status.setComplete();}사용

@Controller
public class KirimController {
	@Autowired
	KirimService ks;
	ModelAndView mav;
	String text;
	String html;
	String json;
	
	
	//cron 표현식 보기 (시간조건) : https://aljjabaegi.tistory.com/400
	//자정이 되면 실행:"0 0 0 * * *"       
	//5분마다 실행:"0 0/5 * * * *"
	
	@Scheduled(cron="0 0/5 * * * * ")   
	public void adExpirePeriod() {
		System.out.println("스케쥴러 : adExpirePeriod ");
		ks.adExpirePeriod();
	}
	
	
	
	
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb) {
		System.out.println("url:/access");
		mav = ks.access(mb);
		return mav;
	}
	
	@RequestMapping(value = "/profilepage",produces ="application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String profilePage(String m_id) {
		System.out.println("url:/profilepage");
		html = ks.profilePage(m_id);
		return html;
	}
	
	@RequestMapping(value = "/detailpage", method = RequestMethod.GET)
	public ModelAndView detailPage(String ad_code) {
		System.out.println("url:/detailpage");
		mav = ks.detailPage(ad_code);
		return mav;
	}
	
	@RequestMapping(value = "/purchsingle", method = RequestMethod.POST) 
	@ResponseBody
	public String purchSingle(Payment ph) { 
		System.out.println("URL : /purchsingle");
		html = ks.purchSingle(ph); 
		return html; 
	}
	
	@RequestMapping(value = "/dibsadd", method = RequestMethod.GET)
	@ResponseBody
	public String dibsAdd(@RequestParam("d_adcode") String d_adcode) {
		System.out.println("url:/dibsadd");
		html = ks.dibsAdd(d_adcode);
		return html;
	}
	
	@RequestMapping(value = "/dibsdelete", method = RequestMethod.GET)
	@ResponseBody
	public String dibsDelete(String d_adcode) {
		System.out.println("url:/dibsdelete");
		html = ks.dibsDelete(d_adcode);
		return html;
	}
	
	@RequestMapping(value = "/detailqawriteinsert",produces ="application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String detailQaWriteInsert(Qna qna) {
		System.out.println("url:/detailqawriteinsert");
		html = ks.detailQaWriteInsert(qna);
		return html;
	}
	
	@RequestMapping(value = "/alarmconfirm" , method = RequestMethod.POST)
	@ResponseBody
	public String alarmConfirm(String al_code) {
		System.out.println("url:/alarmconfirm");
		text = ks.alarmConfirm(al_code);
		return text;
	}
	
	@RequestMapping(value="adinsertdetail", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletResponse resp, 
                 MultipartHttpServletRequest multi) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multi.getFile("upload");
		if(file != null){
			if(file.getSize() > 0 && !StringUtils.isEmpty(file.getName())){
				if(file.getContentType().toLowerCase().startsWith("image/")){
					try{
						
						String oriFileName = file.getOriginalFilename();
						String sysFileName = System.currentTimeMillis() + "."
								+ oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
						byte[] bytes = file.getBytes();
						
						String root = multi.getSession().getServletContext().getRealPath("/");
						System.out.println("root=" + root);
						String path = root + "resources/upload/editor";
						
						File uploadFile = new File(path);
						if(!uploadFile.exists()){
							uploadFile.mkdirs();
						}
						//fileName = UUID.randomUUID().toString();
						path = path + "/" + sysFileName;
						out = new FileOutputStream(new File(path));
                        out.write(bytes);
                        
                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        String fileUrl = "resources/upload/editor/"+sysFileName; // path +
                        //String callback = multi.getParameter("CKEditorFuncNum");
                        // json 데이터로 등록
                        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
                        // 이런 형태로 리턴이 나가야함.
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", sysFileName);
                        json.addProperty("url", fileUrl);
                        
                        printWriter.println(json);
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
					}
				}
			}
		}
		return null;
	}

	

	
	
	
	

	
	
	/*
	 * @RequestMapping(value = "/purchsingle", produces =
	 * "application/json; charset=utf-8")
	 * 
	 * public String purchSingle(@RequestParam PaymentHistory ph) { String json =
	 * ks.purchSingle(ph); return json; }
	 */

	/*
	 * @RequestMapping(value = "/purchsingle",produces =
	 * "application/json; charset=utf-8", method = RequestMethod.POST)
	 * public @ResponseBody String purchSingle(@RequestBody String op_code) { ajax통신
	 * System.out.println("url:/purchsingle");
	 * 
	 * 
	 * String json = ks.purchSingle(op_code); return json; }
	 */

}
