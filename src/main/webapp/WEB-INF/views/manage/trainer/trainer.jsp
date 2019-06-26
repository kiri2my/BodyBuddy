
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:900px">


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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/js/jquery-3.4.1.min.js"></script>
<!-- End custom js for this page-->
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY(지도없음,로그인함)</title>
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
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>

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
	position: relative;
	left: 257px;
	top: 60px;
	width: 95%;
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

<body style="height:900px">
	<!-- partial -->
	<div id="wrap">
		<div id="header">
			<jsp:include page="../../header2.jsp" />
		</div>

		<div id="main">
			<jsp:include page="trainerMain.jsp" />
		</div>

		<div id="nav">
			<jsp:include page="trainerSidebar.jsp" />
		</div>
		<%--</div>
		 <div id="footer">
			<jsp:include page="trainerFooter.jsp" />
		</div>   --%>
	</div>

</body>
<script type="text/javascript">
	var sessionId = '';
	window.onload = function() {
		sessionId = '${mb.m_id}';
	}
	function advertisemanage() {
		$.ajax({
			type : "GET",
			url : "advertisemanage",
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

	function advertisemodifyfrm() {
		$.ajax({
			type : "GET",
			url : "advertisemodifyfrm",
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
	function normalMemList() {
		$.ajax({
			type : "get",
			url : "memberlistc",
			dataType : "html",
			success : function(data) {
				$('#main').html(data);
			},
			error : function() {
				alert('회원목록 로드 실패');
			}
		});
	}

	function profileModifyT() {
		$.ajax({
			type : "GET",
			url : "profileModifyT",
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

	function trainerModifyT() {
		$.ajax({
			type : "GET",
			url : "trainerModifyT",
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

	function questionList() {
		$.ajax({
			type : "GET",
			url : "questionlist",
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
	
	function salesList() {
		$.ajax({
			type : "GET",
			url : "saleshistory",
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
	
	function memberdeleteT() {
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
</script>
</html>
