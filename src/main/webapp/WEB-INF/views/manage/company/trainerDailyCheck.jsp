<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

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
					<p class="card-title">
						<br>
					</p>
					<input type="text" class="span2" id="memsearch" placeholder="회원 검색">
					<button type="button" onclick="memberSearch()"
						style="position: absolute;" class="btn" id="membtn">검색</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">

							<c:set var="trainer" value="${tList }" />
							<c:if test="${empty trainer }">
									회원이 없습니다.
								</c:if>
							<c:if test="${!empty trainer }">
								<thead>
									<tr>
										<th style="width: 10%">이름</th>
										<th style="width: 10%">연락처</th>
										<th style="width: 10%">근태 입력</th>
										<th style="width: 10%">근태 현황</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="trainer" items="${tList }">
										<tr>
											<td><a href="#">${trainer.m_name }(${trainer.m_id })</a></td>
											<td>${trainer.m_phone }</td>
											<td><button class="btn btn-danger"
													onclick="dailyCheck('출근','${trainer.m_id }','${trainer.t_cid }')">출근</button>
												<button class="btn btn-danger"
													onclick="dailyCheck('결근','${trainer.m_id }','${trainer.t_cid }')">결근</button>
												<button class="btn btn-danger"
													onclick="dailyCheck('휴가','${trainer.m_id }','${trainer.t_cid }')">휴가</button></td>
											<td><button class="btn btn-danger"
													onclick="workingAttitude('${trainer.m_id }','${trainer.t_cid }')">근태보기</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal" id="modal"
		style="width: 35%; height: inherit; left: 50%; top: 20%;">
		<div class="modal-header"
			style="text-align: center; align-content: center;">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3>트레이너 근태</h3>
		</div>
		<div class="modal-body" id="modalBody"></div>
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
	var ym = '';

	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	if (month < 10) {
		month = "0" + month;
	}

	ym = year + month;
	var cidd = '';
	var tidd = '';

	function dailyCheck(status, id, cid) {
		var status = status;
		var tid = id;
		var cid = cid
		$.ajax({
			type : "POST",
			url : "dailycheckinsert",
			data : {
				tid : tid,
				status : status,
				cid : cid
			},
			dataType : 'html',
			success : function(data) {
				alert("근태 입력 성공");
			},
			error : function() {
				alert('트레이너 근태 입력 실패');
			}
		});
	}

	function workingAttitude(id, cid) {
		tidd = id;
		cidd = cid
		$
				.ajax({
					type : "post",
					url : "workingattitude",
					data : {
						tid : tidd,
						cid : cidd,
						ym : ym
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						var str = "<h3>"
							+ year
							+ "년"
							+ month
							+ "월 근태</h3>"
							+ "<br><select name='year' id='year1'> <option value='2019' selected='selected'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option></select>"
							+ "<select name='month' id='month1'> <option value='01'>1</option><option value='02'>2</option><option value='03'>3</option><option value='04'>4</option><option value='05'>5</option><option value='06'>6</option><option value='07'>7</option><option value='08'>8</option><option value='09'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>"
							+ "<button class='btn btn-danger' onclick='workingAttiudeSelect()'>검색</button>";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>날짜</th><th style='width: 10%'>근태</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].dt_date + "</td><td>"
									+ data[i].dt_status + "</td></tr>";
						}
						str += "</tbody></table>";
						$('#modalBody').html(str);
						$('#modal').modal('toggle');
						/* $('#modalBody').append(data); */
					},
					error : function(error) {
						alert('근태 목록 로드 실패');
						console.log(error);
					}
				});
	}
	
	function workingAttiudeSelect() {
		var month1 = $('#month1 option:selected').val();
		var year1 = $('#year1 option:selected').val();
		var ym1 = year1 + month1;
		var tidd1 = tidd;
		var cidd1 = cidd;
		$
				.ajax({
					type : "post",
					url : "workingattitude",
					data : {
						tid : tidd,
						cid : cidd,
						ym : ym1
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						var str = "<h3>"
							+ year1
							+ "년"
							+ month1
							+ "월 근태</h3>"
							+ "<br><select name='year' id='year1'> <option value='2019' selected='selected'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option></select>"
							+ "<select name='month' id='month1'> <option value='01'>1</option><option value='02'>2</option><option value='03'>3</option><option value='04'>4</option><option value='05'>5</option><option value='06'>6</option><option value='07'>7</option><option value='08'>8</option><option value='09'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>"
							+ "<button class='btn btn-danger' onclick='workingAttiudeSelect()'>검색</button>";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>날짜</th><th style='width: 10%'>근태</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].dt_date + "</td><td>"
									+ data[i].dt_status + "</td></tr>";
						}
						str += "</tbody></table>";
						$('#modalBody').html(str);
						//$('#modal').modal('toggle');
						/* $('#modalBody').append(data); */
					},
					error : function(error) {
						alert('근태 목록 로드 실패');
						console.log(error);
					}
				});
	}
</script>
</html>