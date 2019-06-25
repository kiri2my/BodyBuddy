<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<p class="card-title"></p>
					<br>
					<input type="text" class="span2" id="memsearch" placeholder="회원 검색">
					<button type="button" onclick="memberSearch()"
						style="position: absolute;" class="btn" id="membtn">검색</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">

							<c:set var="member" value="${mList }" />
							<c:if test="${empty member }">
									회원이 없습니다.
								</c:if>
							<c:if test="${!empty member }">
								<thead>
									<tr>
										<th>이름</th>
										<th>아이디</th>
										<th>생년월일</th>
										<th>연락처</th>
										<th>이용상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="member" items="${mList }">
										<tr>
											<td><a href="#" onclick="profileN('${member.m_id }')">${member.m_name }</a></td>
											<td><a href="#" onclick="profileN('${member.m_id }')">${member.m_id }</a></td>
											<td>${member.m_birth }</td>
											<td>${member.m_phone }</td>
											<td><a href="#" style="text-decoration: none;"
												onclick="changeState('${member.m_id }')">이용중</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</c:if>
						</table>
					</div> <!-- table end -->
				</div>
			</div>
		</div>
	</div>


	<div class="modal" id="modal"
		style="width: 800px; height: 800px; left: 50%; top: 20%;">
		<div class="modal-header"
			style="text-align: center; align-content: center;">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3 id="modal-head"></h3>
		</div>
		<!-- <div id="modalBody" style="width: 300px;"></div> -->
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

<script type="text/javascript">
	function memberSearch() {
		var name = $('#memsearch').val();
		//alert(name+","+sessionId);
		$.ajax({
			type : "get",
			url : "membersearch",
			data : {
				name : name,
				id : sessionId
			},
			dataType : "html",
			success : function(data) {
				//alert(data);
				$('#main').html(data);
			},
			error : function() {
				alert('회원 검색 실패');
			}
		});
	}

	function changeState(mid) {
		var state = state;
		var mid = mid;

		alert();
		$.ajax({
			type : "post",
			url : "changestate",
			data : {
				mid : mid,
				cid : sessionId
			},
			dataType : "html",
			success : function(data) {
				alert(data);
				//$('#main').html(data);
			},
			error : function() {
				alert('이용 상태 전환 실패');
			}
		});
	}

	function profileN(m_id) {
		var m_id = m_id;
		//alert(m_id);
		//var w = window.open("about:blank","_blank","width=600, height=700, top=0,left=0,scrollbars=no");

		$.ajax({
			type : "get",
			url : "profilepage",
			data : {
				m_id : m_id
			},
			dataType : "html",
			success : function(data) {
				//alert(data);
				$('#modal-head').html(m_id + ' 프로필');
				$('#modal').html(data);
				$('#modal').modal('toggle');

				//$('#aaaaa2').html(data);
				//$('#aaaaa1').modal('toggle');
				//$('#'+m_phone).html(data);
				//$('#'+m_phone).prop("style", "display: inline");
			},
			error : function() {
				alert('회원 프로필 로드 실패');
			}

		});
	}
</script>
</html>