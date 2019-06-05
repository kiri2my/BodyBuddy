<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

<style type="text/css">
#memsearch {
	height: 40px;
	width: 200px;
}
#membtn {
	vertical-align: bottom;
}
</style>
</head>
<body>
	<div class="row" style="height: 100%">
		<div class="col-md-12 stretch-card">
			<div class="card">
				<div class="card-body">
					<p class="card-title"><br></p>
					<form class="navbar-form pull-left">
						<input type="text" class="span2" id="memsearch"
							placeholder="회원 검색" >
						<button type="submit" class="btn" id="membtn">검색</button>
					</form>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							<thead>
								<tr>
									<th>회원번호</th>
									<th>이름</th>
									<th>이용기간</th>
									<th>남은기간</th>
									<th>트레이너</th>
									<th>연락처</th>
									<th>이용상태</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>2</td>
									<td>고소영</td>
									<td>2019.4.1~2019.6.3</td>
									<td>2</td>
									<td>없음</td>
									<td>010-1234-5678</td>
									<td>이용중</td>
								</tr>
								<tr>
									<td>1</td>
									<td>송혜교</td>
									<td>2019.5.1~2019.7.1</td>
									<td>28</td>
									<td>김현석</td>
									<td>010-2234-5678</td>
									<td>이용중</td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- plugins:js -->
	<script
		src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/template.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

</body>
</html>