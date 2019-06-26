<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
<script type="text/javascript"

	src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/template.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />


<style>
#header {
	position: relative;
}

#nav {
	position: fixed;
	top: 50px;
	height: 90%;
}

#main {
	position: absolute;
	left: 255px;
	top: 50px;
	width: 90%;
}

#memberList {
	position: absolute;
	left: 240px;
	top: 25px;
	width: 110%;
}

#footer {
	position: relative;
	left: 240px;
	width: 70%;
	padding: 15px 0;
	text-align: center;
	color: white;
	background: gray;
}

</style>
</head>

<body>
	<div id="wrap">
		<div id="header">
			<%-- <jsp:include page="headerNCT.jsp" /> --%>
			<jsp:include page="companyHeader.jsp" />
		</div>
		<div id="main">
			<jsp:include page="companyBody.jsp" />
		</div>
		<div id="footer">
			<jsp:include page="companyFooter.jsp" />
		</div>
		<div id="nav">
			<jsp:include page="companyNav.jsp" />
		</div>
	</div>

</body>

<script type="text/javascript">
    var sessionId = '';
    window.onload = function() {
    	sessionId = '${mb.m_id}';
    }
    
    
	function company() {
		$.ajax({
			type : "post",
			url : "company",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
				
			},
			error : function() {
				alert('업체 관리 페이지 로드 실패');
			}

		});
	}

	function normalDailyCheck() {
		$.ajax({
			type : "post",
			url : "normaldailycheck",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('일반회원 출석 로드 실패');
			}

		});
	}

	function programDailyCheck() {
		$.ajax({
			type : "get",
			url : "programdailycheck",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('프로그램  로드 실패');
			}

		});
	}
	
	function trainerDailyCheck() {
		$.ajax({
			type : "get",
			url : "trainerdailycheck",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('트레이너 출석 로드 실패');
			}

		});
	}

	function normalMemList() {
		$.ajax({
			type : "get",
			url : "memberlistc",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('회원목록 로드 실패');
			}

		});
	}

	function trainerMemList() {
		$.ajax({
			type : "get",
			url : "trainerlistc",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('트레이너 목록 로드 실패');
			}

		});
	}
	
	
	function salesHistory() {
		$.ajax({
			type : "get",
			url : "saleshistory",
			data : {
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('판매 목록 로드 실패');
			}

		});
	}
	
	function question() {
		$.ajax({
			type : "get",
			url : "questionlist",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('문의 관리 로드 실패');
			}

		});
	}
	
	function infoModify() {
		var id = '${m_id}';
		$.ajax({
			type : "post",
			url : "infomodifyc",
			data : {
				id : sessionId
				},
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('정보 수정 로드 실패');
			}

		});
		
	}
	
	function salesPage() {
		$.ajax({
			type : "post",
			url : "sales",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('판매/실적 로드 실패');
			}

		});
		
	}
	
	function advertiseManage(){
		$.ajax({
			type : "get",
			url : "advertisemanage",
			dataType : "html",
			success : function(data) {
				
				$('#main').html(data);
				$('#adWriteFrm').prop('style','display: none');
			},
			error : function() {
				alert('광고 관리 페이지 로드 실패');
			}

		});
		
	}
	
	
	
	function advertiseWriteFrm(){
		$.ajax({
			type : "get",
			url : "advertisewritefrm",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('광고 등록 페이지 로드 실패');
			}

		});
		
	}
	
	function advertiseModifyFrm(){
		$.ajax({
			type : "get",
			url : "advertisemodifyfrm",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('광고 등록 페이지 로드 실패');
			}

		});
		
	}
	
	function memberdelete() {
		$.ajax({
			type : "GET",
			url : "memberdeltet",
			dataType : "html",
			error : function() {
				alert('통신실패!!');
			},
			success : function(data) {
				/* $('#main').hide(); */
				$('#main').html(data);
			}
		});
	}
</script>

</html>
