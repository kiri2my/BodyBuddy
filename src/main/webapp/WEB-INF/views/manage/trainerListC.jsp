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
						트레이너 요청 <span class="badge badge-important" style="font: bold;">${num }</span>
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
											<td><button class="btn btn-danger"
													onclick="trainerSales('${trainer.m_id}','${trainer.t_cid}')">실적보기</button></td>
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
		style="width: 30%; height: inherit; margin-left: 35%; top: 20%; ">
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
	var cidd = '';
	var tidd = '';
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
				//////////소속수락 웹소켓 알림
				if(state==1){
					var jsonSendSosokAcptAlarm = JSON.stringify({c_id:sessionId,t_id:tid});
					$("#msg").val("SS02"+jsonSendSosokAcptAlarm);
					console.log("WebSock7", sock);
					console.log($("#msg").val());
					sock.send($("#msg").val());
				}else if(state==0){//////////소속거절 웹소켓 알림
					var jsonSendSosokRjctAlarm = JSON.stringify({c_id:sessionId,t_id:tid});
					$("#msg").val("SS03"+jsonSendSosokRjctAlarm);
					console.log("WebSock8", sock);
					console.log($("#msg").val());
					sock.send($("#msg").val());
				}
				/////////////
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
				//////////////소속끊기 웹소켓 알림
				var jsonSendSosokCutAlarm = JSON.stringify({c_id:sessionId,t_id:tid});
				$("#msg").val("SS04"+jsonSendSosokCutAlarm);
				console.log("WebSock9", sock);
				console.log($("#msg").val());
				sock.send($("#msg").val());
				///////////////////
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
				$('#modal_title').html(m_id + ' 프로필');
				$('#modalBody').html(data);
				$('#modal').modal('toggle');
			},
			error : function() {
				alert('회원 프로필 로드 실패');
			}

		});
	}

	function trainerSales(tid, cid) {
		var tid = tid;
		var cid = cid;
		//console.log(tid + "," + cid);
		$
				.ajax({
					type : "post",
					url : "trainersales",
					data : {
						tid : tid,
						cid : cid
					},
					dataType : "json",
					success : function(data) {
						var msg = '';
						if (data == '0') {
							cidd = cid;
							tidd = tid;
							msg ="<select name='year' id='year' > <option value='2019' selected='selected'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option></select>"
							+ "<select name='year' id='month'> <option value='01' selected='selected'>1</option><option value='02'>2</option><option value='03'>3</option><option value='04'>4</option><option value='05'>5</option><option value='06'>6</option><option value='07'>7</option><option value='08'>8</option><option value='09'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>"
							+ "<button class='btn btn-danger' onclick='trainerSalesSelect()'>검색</button>";
							$('#modalBody').html(msg);
							$('#modal').modal('toggle');
							alert("실적이 없습니다.");
							return;
						}
						cidd = data[0].AD_NAME;
						tidd = data[0].OP_TRAINER;
						console.log(cidd + "," + tidd);
						//alert(data[0].AD_NAME+","+data[0].OP_TRAINER);
						var str = "<h3>"+ data[0].YM+" 실적</h3>" + "<select name='year' id='year' > <option value='2019' selected='selected'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option></select>"
								+ "<select name='year' id='month'> <option value='01' selected='selected'>1</option><option value='02'>2</option><option value='03'>3</option><option value='04'>4</option><option value='05'>5</option><option value='06'>6</option><option value='07'>7</option><option value='08'>8</option><option value='09'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>"
								+ "<button class='btn btn-danger' onclick='trainerSalesSelect()'>검색</button>";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>프로그램명</th><th style='width: 10%'>실적수</th><th style='width: 10%'>단가</th><th style='width: 10%'>금액</th></tr></thead><tbody>";

						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].AD_TITLE + "</td><td>"
									+ data[i].COUNT + "</td><td>"
									+ data[i].OP_PRICE + "</td><td>"
									+ data[i].PRICE_SUM + "</td></tr>";
						}

						str += "</tbody></table>";
						$('#modalBody').html(str);
						$('#modal').modal('toggle');
					},
					error : function() {
						alert('트레이너 실적 로드 실패');
					}

				});
	}

	function trainerSalesSelect() {
		//$('#modal').modal('toggle');
		var tid = tidd;
		var cid = cidd;
		var month = $('#month option:selected').val();
		var year = $('#year option:selected').val();
		var ym = year + month;
		//alert(tid + "," + cid + "," + month + "," + year + "," + ym);
		$
				.ajax({
					type : "post",
					url : "trainersalesselect",
					data : {
						tid : tid,
						cid : cid,
						ym : ym
					},
					dataType : "json",
					success : function(data) {
						if (data == '0') {
							alert("실적이 없습니다.");
							return;
						}
						cidd = data[0].AD_NAME;
						tidd = data[0].OP_TRAINER;
						console.log(cidd + "," + tidd);
						var str = "<h3>"
								+ year
								+ "년"
								+ month
								+ "월 실적</h3>"
								+ "<br><select name='year' id='year'> <option value='2019' selected='selected'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option></select>"
								+ "<select name='year' id='month'> <option value='01'>1</option><option value='02'>2</option><option value='03'>3</option><option value='04'>4</option><option value='05'>5</option><option value='06'>6</option><option value='07'>7</option><option value='08'>8</option><option value='09'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select>"
								+ "<button class='btn btn-danger' onclick='trainerSalesSelect()'>검색</button>";
						str += "<table class='table table-striped table-hover'><thead><tr><th style='width: 10%'>프로그램명</th><th style='width: 10%'>실적수</th><th style='width: 10%'>단가</th><th style='width: 10%'>금액</th></tr></thead><tbody>";
						for (var i = 0; i < data.length; i++) {
							str += "<tr><td>" + data[i].AD_TITLE + "</td><td>"
									+ data[i].COUNT + "</td><td>"
									+ data[i].OP_PRICE + "</td><td>"
									+ data[i].PRICE_SUM + "</td></tr>";
						}
						str += "</tbody></table>";
						$('#modalBody').html(str);
						//$('#modal').modal('toggle');
					},
					error : function() {
						alert('트레이너 실적 로드 실패');
					}

				});
	}
</script>
</html>