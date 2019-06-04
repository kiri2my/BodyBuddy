<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY(지도있음,로그인안함)</title>
<!-- plugins:css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="/resources/images/favicon.png" />
</head>

<body>
	<div class="container-scroller">
		<jsp:include page="headerNCT.jsp" flush="false" />
		<%-- <jsp:include page="headerNonLogin.jsp" flush="false"/> --%>
		<jsp:include page="body.jsp" flush="false" />
		<jsp:include page="footer.jsp" flush="false" />

	</div>


	<!-- plugins:js -->
	<script src="<c:url value="/resources/vendors/base/vendor.bundle.base.js"/>"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script src="<c:url value="/resources/vendors/chart.js/Chart.min.js" />"></script>
	<script src="<c:url value="/resources/vendors/datatables.net/jquery.dataTables.js" />"></script>
	<script src="<c:url value="/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js" />"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="<c:url value="/resources/js/off-canvas.js" />"></script>
	<script src="<c:url value="/resources/js/hoverable-collapse.js" />"></script>
	<script src="<c:url value="/resources/js/template.js" />"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="<c:url value="/resources/js/dashboard.js" />"></script>
	<script src="<c:url value="/resources/js/data-table.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
	<script src="<c:url value="/resources/js/dataTables.bootstrap4.js" />"></script>
	<!-- End custom js for this page-->
</body>
</html>