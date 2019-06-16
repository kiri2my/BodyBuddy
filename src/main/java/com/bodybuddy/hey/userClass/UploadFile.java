package com.bodybuddy.hey.userClass;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bodybuddy.hey.dao.YoonDao;



@Component
public class UploadFile {
	//파일 업로드 메소드	
	//String fullPath="D:/Work/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVC-Board/resources/upload";
	@Autowired
	private YoonDao yDao;
	
	public boolean fileUp(MultipartHttpServletRequest multi, String m_id){
		System.out.println("fileUp");
		//1.이클립스의 물리적 저장경로 찾기 (이클립스가 가상으로 경로지정해줌) 
		String root=multi.getSession().getServletContext().getRealPath("/");
		System.out.println("root="+root);
		String path=root+"resources/upload/";
		//2.폴더 생성을 꼭 할것...
		File dir=new File(path);
		
		
		  if(!dir.isDirectory()){ //upload폴더 없다면 dir.mkdir(); //upload폴더 생성
		  dir.mkdirs(); //s 안ㅇ붙이면 딱 그폴더만 생성되고 s붙ㄹ이면 경로 상 상위폴더까지 같이만들어줌
		  }
		 
		
		//3.파일을 가져오기-파일태그 이름들 반환
		Iterator<String> files=multi.getFileNames(); //파일업로드 2개이상일때
		
		Map<String,String> fMap=new HashMap<String, String>();
		fMap.put("m_id", m_id); //글번호 저장
		boolean f=false;
		while(files.hasNext()){
			String fileTagName=files.next();
			System.out.println("fileTag="+fileTagName);  
			//파일 메모리에 저장
			MultipartFile mf=multi.getFile(fileTagName); //실제파일
			String oriFileName=mf.getOriginalFilename();  //a.txt 오리지날파일명
			fMap.put("pf_image", oriFileName); //DB에 저장
			//4.시스템파일이름 생성  a.txt  ==>112323242424.txt
			String sysFileName=System.currentTimeMillis()+"." //현재시간
			+oriFileName.substring(oriFileName.lastIndexOf(".")+1); //.확장자를 찾아서 확장자앞에다가currentTimeMillis붙임
			fMap.put("pf_image", sysFileName);
			
			//5.메모리->실제 파일 업로드
			
			try {
				mf.transferTo(new File(path+sysFileName)); //실제 파일 업로드부분
				f=yDao.fileInsert(fMap); //업로드한 파일 이름들 DB에 넣기
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //while End
		if(f)
			return true;
		return false;
	}
	
	public boolean fileUp2(MultipartHttpServletRequest multi, String m_id){
		System.out.println("fileUp");
		//1.이클립스의 물리적 저장경로 찾기 (이클립스가 가상으로 경로지정해줌) 
		String root=multi.getSession().getServletContext().getRealPath("/");
		System.out.println("root="+root);
		String path=root+"resources/upload/";
		//2.폴더 생성을 꼭 할것...
		File dir=new File(path);
		 if(!dir.isDirectory()){ //upload폴더 없다면 dir.mkdir(); //upload폴더 생성
		  dir.mkdirs(); }
		 
		//3.파일을 가져오기-파일태그 이름들 반환
		Iterator<String> files=multi.getFileNames(); //파일업로드 2개이상일때
		
		Map<String,String> fMap=new HashMap<String, String>();
		fMap.put("m_id", m_id); //글번호 저장
		boolean f=false;
		while(files.hasNext()){
			String fileTagName=files.next();
			System.out.println("fileTag="+fileTagName);  
			//파일 메모리에 저장
			MultipartFile mf=multi.getFile(fileTagName); //실제파일
			String oriFileName=mf.getOriginalFilename();  //a.txt 오리지날파일명
			fMap.put("pf_image", oriFileName); //DB에 저장
			//4.시스템파일이름 생성  a.txt  ==>112323242424.txt
			String sysFileName=System.currentTimeMillis()+"." //현재시간
			+oriFileName.substring(oriFileName.lastIndexOf(".")+1); //.확장자를 찾아서 확장자앞에다가currentTimeMillis붙임
			fMap.put("pf_image", sysFileName);
			
			//5.메모리->실제 파일 업로드
			
			try {
				mf.transferTo(new File(path+sysFileName)); //실제 파일 업로드부분
				f=yDao.fileUpdate(fMap); //업로드한 파일 이름들 DB에 넣기
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //while End
		if(f)
			return true;
		return false;
	}
	//파일 다운로드
		public void download(String fullPath, 
				String oriFileName, HttpServletResponse resp) throws Exception {
			
			//한글파일 깨짐 방지
			String downFile = URLEncoder.encode(oriFileName, "UTF-8");
			System.out.println("downFile="+downFile);
			//파일이름공백처리 (파일명 뒤에 이상한 문자 붙는 경우 아래 코드로 해결)
			//downFile = downFile.replaceAll("\\+", ""); // \\다음을 문자로 인식해라
			//파일 객체 생성
			File file = new File(fullPath);
			//다운로드 경로 파일을 읽어 들임
			InputStream is = new FileInputStream(file);
			//반환객체설정
			resp.setContentType("application/octet-stream");
			resp.setHeader("content-Disposition", 
					"attachment; filename=\""+downFile+"\"");
			
			//반환객체에 스트림 연결
			OutputStream os = resp.getOutputStream();
			
			//파일쓰기
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer)) != -1){
				os.write(buffer,0,length);
			}
			
			os.flush();
			os.close();
			is.close();
		}
	
	
	
}
	//@Transactional
	//서비스 클래스의 컨트롤 메소드
	//public ModelAndView execute(int cmd,MultipartHttpServletRequest multi) {
	//	switch(cmd) {
	//	case 1:
	//		boardWrite(multi);
	//		fct1();
		
	//	}
		
	//	return mav;
	//	}
	//private void fct1() {
	//	mav.addObject("obj","추가");
	//}