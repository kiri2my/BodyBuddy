<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
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
	left: 240px;
	top: 25px;
	width: 105%;
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
	<!-- partial -->
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
	function memberList() {
		$.ajax({
			type : "GET",
			url : "memberlist",
			dataType : "html",
			error : function() {
				alert('통신실패!!');
			},
			success : function(data) {
				/* $('#main').hide(); */
				$('#main').html(data).trigger('create');
			}
		});
	}
	
	function trainerList() {
		$.ajax({
			type : "GET",
			url : "trainerlist",
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
	
	function dailyCheck() {
		$.ajax({
			type : "GET",
			url : "dailycheck",
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