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
					<p class="card-title">
						<br>
					</p>
					<input type="text" class="span2" id="memsearch"
						placeholder="일반회원 검색">
					<button type="button" onclick="memberSearch()" style="position: absolute;" class="btn"
						id="membtn">검색</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">

							<c:set var="member" value="${mList }" />
							<c:if test="${empty member }">
									회원이 없습니다.
								</c:if>
							<c:if test="${!empty member }">
								<thead>
									<tr>
										<th style="width: 15%">이름</th>
										<th style="width: 6%">생년월일</th>
										<th style="width: 7%">연락처</th>
										<th style="width: 7%">이용기간</th>
										<th style="width: 6%">남은기간</th>
										<th style="width: 7%">이용상태</th>
										<th style="width: 5%">출석</th>
										<th style="width: 5%">현황</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="member" items="${mList }">
										<tr>
											<td><a href="#">${member.M_NAME }(${member.M_ID })</a></td>
											<td>${member.M_BIRTH }</td>
											<td>${member.M_PHONE }</td>
											<td>${member.PS_DATE }~${member.PS_DATE1 }</td>
											<td>${member.OP_PERIOD }</td>
											<td><button class="btn btn-danger" onclick="statusChange('${member.PS_CODE}','${member.DA_STATUS }')" id="${member.PS_CODE }" value="${member.DA_STATUS }">${member.DA_STATUS }</button></td>
											<th><button class="btn btn-danger"
													onclick="normalCheck('${member.M_ID}','${member.PS_CODE}')">출석</button></th>
											<th><button class="btn btn-danger"
													onclick="Attended('${member.M_ID}','${member.PS_CODE}','${member.M_NAME }')">보기</button></th>
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
		style="width: 30%; height: inherit; left: 50%; top: 20%;">
		<div class="modal-header"
			style="text-align: center; align-content: center;">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h3 id="modal_title"></h3>
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
	function normalCheck(id, code) {
		var id = id;
		var code = code;
		var cCode = $('#'+code).val();
		//alert('cCode = '+cCode+', code = '+code);
		if(cCode == '일시정지') {
			alert('일시정지 상태에서는 출석이 불가능 합니다.')
			return;
		}
		$.ajax({
			type : "POST",
			url : "normalcheck",
			data : {
				code : code
			},
			dataType : 'text',
			success : function(data) {
				alert('출석 성공');
			},
			error : function() {
				alert('일반회원 입력 실패');
			}
		});

	}

	function Attended(id, code, name) {
		var id = id;
		var code = code;
		var name = name;
		$
				.ajax({
					type : "post",
					url : "attended",
					data : {
						id : id,
						code : code
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						var str = "";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>날짜</th><th style='width: 10%'>출결</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].dn_date + "</td><td>"
									+ "출석" + "</td></tr>";
						}
						str += "</tbody></table>";
						$('#modal_title').html(name+'('+id+') 출석 현황');
						$('#modalBody').html(str);
						$('#modal').modal('toggle');
					},
					error : function(error) {
						alert('현황 목록 로드 실패');
						console.log(error);
					}
				});
	}
	
	/* function changeState(mid) {
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
	} */
	
	function statusChange(code1,status1) {
		var code = code1;
		var status = status1;
		//alert(code+","+status);
		var start = '이용중으로 변경 하시겠습니까?';
		var stop = '일시 정지로 변경 하시겠습니까?';
		var str = '';
		
		if(status =='이용중'){
			str = stop;
			status = '일시정지';
		}else if(status == '일시정지') {
			str = start;
			status = '이용중';
		}
		
		if (confirm(str) == true) {
			$.ajax({
				type : 'post',
				url : 'changestate',
				data : {
					code : code,
					status : status
				},
				dateType : 'json',
				success : function(data) {

					$('#'+code).html(status);
					$('#'+code).val(status);
				},
				error : function(error) {
					alert("이용상태 변경 실패");
				}
			});
		} else {
			return;
		}
	}
</script>
</html>