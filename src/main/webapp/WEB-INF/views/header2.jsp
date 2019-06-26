<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>


<!-- Web socket CDN -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<head>
<!-- plugins:js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<!-- End plugin js for this page-->
<!-- inject:js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/template.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>


	
<!-- End custom js for this page-->
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY</title>
<!-- plugins:css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link type="text/css" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
</head>

<body>

	<!-- partial:partials/_navbar.html -->
	<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
		<div class="navbar-brand-wrapper d-flex justify-content-center">
			<div
				class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
				<a class="navbar-brand brand-logo"
					href="${pageContext.request.contextPath}/" style="color: #71c016;"><img alt ="logo" src="resources/img/logo.jpg"></a> <a class="navbar-brand brand-logo-mini"
					href="${pageContext.request.contextPath}/" style="color: #71c016;">BODY BUDDY</a>
				<button class="navbar-toggler navbar-toggler align-self-center"
					type="button" data-toggle="minimize">
					<span class="mdi mdi-sort-variant"></span>
				</button>
			</div>
		</div>
		<div
			class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
			<ul class="navbar-nav mr-lg-8 w-100">
				
			</ul>
			<input type="hidden" id="msg"/>






			<ul id="headerAlarm" class="nav navbar-nav">
				
			</ul>
			<ul id="headerKind" class='navbar-nav navbar-nav-right'>

			</ul>

		</div>
	</nav>
	<!-- partial -->

	<!-- page-body-wrapper ends -->

	<!-- container-scroller -->


</body>


<script src="${pageContext.request.contextPath}/resources/js/kirimWebSocket.js"></script>
<script>
var kind = "${mb.m_kind}";
var m_id = "${mb.m_id}";
console.log("headerConsoleKind", kind);
console.log("sessionId", m_id);

//////////////////////////////////////////////////////////////////////////웹소켓

var sock=null;
connect();

//sock.onclose = onClose;

//연결할때
function connect(){
	var connect = new SockJS("/alarm");
	sock=connect;
	$("#msg").val("CON1"+m_id);
}

//서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var data = msg.data;
	console.log("받은메세지...",data);
	var parseData = JSON.parse(data);
	console.log("::::",parseData[0]);
	console.log("::::",parseData[1]);
	
	$(".beforeAlarm").after(parseData[0]);
	if(parseData[1]>0){
		$(".count").html("<p class='text-white' style='font-size: 5px;line-height: 15px;'>"+parseData[1]+"</p>");
		$(".count").prop("hidden", false);
	}
	
}
//보낼때
function sendMessage() {
	
}
//서버와 연결을 끊었을 때
function onClose(evt) {
	$(".beforeAlarm").append("연결 끊김");
}

window.onload=function(){
	if(kind == 'n' || kind == 't' || kind == 'c'){
		console.log("WebSock", sock);
		
		// 메시지 전송
		console.log("보내는메세지...",$("#msg").val());
		sock.send($("#msg").val());
		sock.onmessage = onMessage;
		sendMessage();
		
		
		//////////알림 받은거 클릭하면 이미 본걸로 업데이트 시켜주기
		$("#notificationDropdown").click(function(){
			//console.log($(this).next().children().filter($(".alarm-confirm")) );
			var ac = $(this).next().children().filter($(".alarm-confirm")); 
			ac.click(function(){
				var al_code = $(this).children().filter($(".al_code")).val();
				$.ajax({
					url:'alarmconfirm',
					type:'post',
					data:{al_code:al_code},
					dataType:'text',
					success:function(suc){
						console.log(suc);
						ac.hide();
					},
					error:function(err){
						console.log(err);
					}
				});//$.ajax({
			});//ac.click(function(){
		});//$("#notificationDropdown").click(function(){
	}//if(kind == 'n' || kind == 't' || kind == 'c'){
}//window.onload=function(){

	
	
	
$(document).ready(function(){
});

/////////////웹소켓 끝








	if (kind == 'n' || kind == 't' || kind == 'c') {
		var strKind = "<li class='nav-item nav-profile dropdown'>\n"
				+ "    <a class='nav-link dropdown-toggle' href='#' data-toggle='dropdown' id='profileDropdown'>\n"
				+ "        <img src='resources/images/faces/mydefault.jpg' alt='profile' />\n"
				+ "        <span class='nav-profile-name'>${mb.m_name}</span>\n"
				+ "    </a>\n"
				+ "    <div class='dropdown-menu dropdown-menu-right navbar-dropdown' aria-labelledby='profileDropdown'>\n"
				+ "        <a href=";

		if (kind == 'n') {
			strKind += "infoprogramn?m_id=${mb.m_id}";
		} else if (kind == 't') {
			strKind += "trainer?m_id=${mb.m_id}";
		} else if (kind == 'c') {
			strKind += "company?m_id=${mb.m_id}";
		}
		strKind += "' class='dropdown-item'>\n"
				+ "            <i class='mdi mdi-settings text-primary'></i>\n"
				+ "            마이페이지\n" + "        </a>\n"
				+ "        <a href='logout' class='dropdown-item'>\n"
				+ "            <i class='mdi mdi-logout text-primary'></i>\n"
				+ "            로그아웃\n" + "        </a>\n" + "    </div>\n"
				+ "</li>\n";

		$('#headerKind').html(strKind);
	}
</script>
</html>