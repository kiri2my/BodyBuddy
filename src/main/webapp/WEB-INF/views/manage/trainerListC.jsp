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
#trainersearch {
	height: 40px;
	width: 200px;
}

#tainerbtn {
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
					<input type="text" class="span2" id="trainersearch"
						placeholder="트레이너 검색">
					<button type="button" style="position: absolute;"
						onclick="trainerSearch()" class="btn" id="tainerbtn">검색</button>
					<button style="position: absolute; left: 80%;"
						class="btn btn-danger" onclick="trainerJoinList()">
						트레이너 요청<span class="badge badge-important">6</span>
					</button>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">

							<c:set var="trainer" value="${tList }" />
							<c:if test="${empty trainer }">
									트레이너가 없습니다.
								</c:if>
							<c:if test="${!empty trainer }">

								<thead>
									<tr>
										<th>이름</th>
										<th>입사일자</th>
										<th>연락처</th>
										<th>근태현황</th>
										<th>실적</th>
										<th>상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="trainer" items="${tList }">
										<tr>
											<td><a href="#" onclick="profileC('${trainer.m_id }')">${trainer.m_name }(${trainer.m_id })</a></td>
											<td>${trainer.yn_date }</td>
											<td>${trainer.m_phone }</td>
											<td><button class="btn btn-danger"
													onclick="workingAttitude('${trainer.m_id }','${trainer.t_cid }')">근태보기</button>
											</td>
											<td><button class="btn btn-danger" onclick="">실적보기</button></td>
											<td><button class="btn btn-danger"
													onclick="delete_event('${trainer.m_id }')">소속끊기</button></td>
											<!-- onclick="trainerDiscon('${trainer.m_id }')" -->
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
		style="width: 30%; height: 50%; left: 50%; top: 20%;">
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
	function delete_event(tid) {
		if (confirm("트레이너 소속을 끊으시겠습니까?") == true) {
			var tid = tid;
			alert(tid);
			$.ajax({
				type : 'post',
				url : 'trainerdiscon',
				data : {
					cid : sessionId,
					tid : tid
				},
				dateType : 'json',
				success : function(data) {
					alert(data);

					$('#modal').modal('hide');
				},
				error : function(error) {
					alert("트레이너 소속끊기 실패")
				}
			});
		} else {
			return;
		}
	}
	function trainerSearch() {
		var name = $('#trainersearch').val();
		$.ajax({
			type : "get",
			url : "trainersearch",
			data : {
				name : name,
				id : 'company1'
			},
			dataType : "html",
			success : function(data) {
				$('#modal').html(data);
			},
			error : function() {
				alert('트레이너 검색 실패');
			}
		});
	}

	function trainerJoinList() {
		$
				.ajax({
					type : "post",
					url : "trainerjoinlist",
					data : {
						id : sessionId
					},
					dataType : "json",
					success : function(data) {
						console.log(data);
						var str = "";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>이름</th><th style='width: 10%'>생년월일</th><th style='width: 10%'>연락처</th><th style='width: 20%'>선택</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr id=\'"+data[i].YN_TRAINER+"\'><td>"
									+ data[i].M_NAME
									+ "</td><td>"
									+ data[i].M_BIRTH
									+ "</td><td>"
									+ data[i].M_PHONE
									+ "</td><td><button class='btn btn-danger' onclick='trainerJoin(1,\""
									+ data[i].YN_TRAINER
									+ "\")'>수락</button><button class='btn btn-danger' onclick='trainerJoin(0,\""
									+ data[i].YN_TRAINER
									+ "\")'>거절</button></td></tr>";
						}
						str += "</tbody></table>";
						$('#modalBody').html(str);
						$('#modal').modal('toggle');
					},
					error : function() {
						alert('트레이너 요청 목록 로드 실패');
					}
				});
	}

	function workingAttitude(id, cid) {
		var tid = id;
		var cid = cid
		$
				.ajax({
					type : "post",
					url : "workingattitude",
					data : {
						tid : tid,
						cid : cid
					},
					dataType : 'json',
					success : function(data) {
						console.log(data);
						var str = "";
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

	function trainerJoin(state, tid) {
		var state = state;
		var tid = tid;
		var ttid = "#" + tid;
		alert(state + "," + tid);
		$.ajax({
			type : 'post',
			url : 'trainerjoin',
			data : {
				state : state,
				cid : sessionId,
				tid : tid
			},
			dateType : 'json',
			success : function(data) {
				alert(data);
				console.log(data);
				/* var str = "";
				str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>이름</th><th style='width: 10%'>생년월일</th><th style='width: 10%'>연락처</th><th style='width: 20%'>선택</th></tr></thead><tbody>";
				for (var i = 0; i < data.length; i++) {
					str += "<tr><td>"
							+ data[i].M_NAME
							+ "</td><td>"
							+ data[i].M_BIRTH
							+ "</td><td>"
							+ data[i].M_PHONE
							+ "</td><td><button class='btn btn-danger' onclick='trainerJoin(1,\""+ data[i].YN_TRAINER + "\")'>수락</button><button class='btn btn-danger' onclick='trainerJoin(0,\""	+ data[i].YN_TRAINER + "\")'>거절</button></td></tr>";
				}
				str += "</tbody></table>"; */

				//$(ttid).prop("style","display: none");
				//$('#modalBody').html(str);
				$('#modal').modal('hide');
			},
			error : function(error) {
				alert("트레이너 수락 거절 실패")
			}
		});
	}

	function trainerDiscon(tid) {
		var tid = tid;
		alert(tid);
		$.ajax({
			type : 'post',
			url : 'trainerdiscon',
			data : {
				cid : sessionId,
				tid : tid
			},
			dateType : 'json',
			success : function(data) {
				alert(data);

				$('#modal').modal('hide');
			},
			error : function(error) {
				alert("트레이너 소속끊기 실패")
			}
		});
	}
	
	function profileC(m_id) {
		var m_id = m_id;
		alert(m_id);
		//var w = window.open("about:blank","_blank","width=600, height=700, top=0,left=0,scrollbars=no");

		$.ajax({
			type : "get",
			url : "profilepage",
			data : {
				m_id : m_id
			},
			dataType : "html",
			success : function(data) {
				alert(data);
				$('#modal_title').html(m_id+' 프로필');
				$('#modalBody').html(data);
				$('#modal').modal('toggle');
			},
			error : function() {
				alert('회원 프로필 로드 실패');
			}

		});
	}
</script>
</html>