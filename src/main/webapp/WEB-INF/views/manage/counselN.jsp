<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상담내역 상세페이지입니다</h2>
<h3>상담코드</h3><br> ${cs.cs_opcode}<br>
<h3>상담받는아이디</h3><br>  ${cs.cs_mid}<br>
<h3>날짜 </h3><br>${cs.cs_date}<br>
<h3>상담글내용</h3><br> ${cs.cs_content}<br>
<h3>상담이미지</h3><br> ${cs.cs_image}<br>
</body>
</html>