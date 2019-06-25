<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Majestic Admin</title>
<!-- plugins:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/images/favicon.png">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/template.js"></script>
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
	left: 300px;
	top: 80px;
	width: 80%;
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
			<jsp:include page="../header.jsp" />
		</div>
		<div id="main">
	<div class="container-scroller">
		<div class="col-md-12 grid-margin grid-margin-md-0 stretch-card">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title">회원탈퇴시 주의사항</h4>

					<ul class="list-arrow">
						1.Lorem ipsum dolor sit amet
						<li>Consectetur adipiscing elit</li>
						<li>Integer molestie lorem at massa</li>
						<li>Facilisis in pretium nisl aliquet</li>
						<li>Nulla volutpat aliquam velit</li>
						<br> 2.Lorem ipsum dolor sit amet
						<li>Consectetur adipiscing elit</li>
						<li>Integer molestie lorem at massa</li>
						<li>Facilisis in pretium nisl aliquet</li>
						<li>Nulla volutpat aliquam velit</li>
						<br> 3.Lorem ipsum dolor sit amet
						<li>Consectetur adipiscing elit</li>
						<li>Integer molestie lorem at massa</li>
						<li>Facilisis in pretium nisl aliquet</li>
						<li>Nulla volutpat aliquam velit</li>
					</ul>
				</div>
				<a href="memberdeletereal"><button class="btn btn-dark btn-lg btn-block">회원탈퇴</button></a>
			</div>
		</div>

	</div>
		</div>
		<div id="nav">
			<jsp:include page="sidebarNormal.jsp" />
		</div>
	</div>



</body>
</html>