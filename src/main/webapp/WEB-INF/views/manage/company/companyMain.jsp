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
	function company() {
		$.ajax({
			type : "get",
			url : "company",
			dataType : "html",
			success : function(data) {
				$('#main').append(data);
			},
			error : function() {
				alert('일반회원 출석 로드 실패');
			}

		});
	}

	function normalDailyCheck() {
		$.ajax({
			type : "get",
			url : "normaldailycheck",
			data : {
				id : "company1"
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
				id : "company1"
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
				id : "company1"
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
				id : "3333"
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
				id : 'company1'
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
				id : 'company1'
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
			url : "question",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('문의 관리 로드 실패');
			}

		});
	}
	
	
</script>

</html>
