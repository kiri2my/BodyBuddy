<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
</head>

<body>
	<div id="bodybody" style="left: 0px; top: 15px; position: relative; display: block;">
		<div
			style="top: 0px; left: 0px; position: absolute; width: 550px; height: 300px;">

			<h3 style="text-align: center;margin-top:20px;">최근 신규 가입</h3>

			<table class="table table-hover" style="table-layout: fixed; border-right:1px solid #eaeaea; border-top:1px solid #eaeaea; height:335px">
				<thead>
					<tr>
						<th>이름</th>
						<th>아이디</th>
						<th>가입날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="member" items="${mList }">
						<tr>
							<td><a href="#" onclick="profileN('${member.m_id }')"><nobr>${member.m_name }</nobr></a></td>
							<td><a href="#" onclick="profileN('${member.m_id }')"><nobr>${member.m_id }</nobr></a></td>
							<td>${member.ps_date }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
		<div
			style="left: 550px; position: absolute; width: 550px; height: 300px; display: block;">


			<h3 style="text-align: center;margin-top:20px;">최근 판매 내역</h3>

			<table class="table table-hover" style="table-layout: fixed;border-right:1px solid #eaeaea; border-top:1px solid #eaeaea; height:335px">
				<thead>
					<tr>
						<!-- <th>판매번호</th> -->
						<th>광고명</th>
						<th>결제자</th>
						<th style="width: 20%;">금액</th>
						<th>판매일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sales" items="${sList }">
						<tr>
							<%-- <td><a href="#" onclick="advertisedetail(${sales.ps_code })">${sales.ps_code }</a></td> --%>
							<td style="text-align: center; text-overflow:ellipsis; overflow:hidden;"><nobr><a href="#"
								onclick="advertisedetail(${sales.ps_adcode})">${sales.ad_title}</a></nobr></td>
							<td style=" text-overflow:ellipsis; overflow:hidden;"><nobr><a href="#"
								onclick="advertisedetail(${sales.m_id})">${sales.m_name }(${sales.m_id})</a></nobr></td>
							<td style="text-align: right; text-overflow:ellipsis; overflow:hidden;"><nobr>${sales.ps_price }</nobr></td>
							<td style=" text-overflow:ellipsis; overflow:hidden;"><nobr>${sales.ps_date }</nobr></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div
			style="left: 1100px; position: absolute; width: 550px; height: 300px; display: block;">
			<h3 style="text-align: center;margin-top:20px;">등록중인 광고</h3>
			<table class="table table-hover"  style="table-layout: fixed;border-right:1px solid #eaeaea; border-top:1px solid #eaeaea;">
				<thead>
					<tr>
						<th>광고번호</th>
						<th>광고명</th>
						<th>등록일자</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach var="ad" items="${aList }">
							<tr>
								<td style="text-overflow:ellipsis; overflow:hidden;"><nobr>${ad.ad_code }</nobr></td>
								<td style="text-overflow:ellipsis; overflow:hidden;"><nobr><a href="#" onclick="profileN('${ad.ad_title }')">${ad.ad_title }</a></nobr></td>
								<td style="text-overflow:ellipsis; overflow:hidden;"><nobr>${ad.ad_date}</nobr></td>
								<td style="text-overflow:ellipsis; overflow:hidden;"><nobr>${ad.ad_status}</nobr></td>
							</tr>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="card" style="top: 400px; width: 1665px; height: 800px;">
		<div class="card-body">
			<div class="chartjs-size-monitor"
				style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
				<div class="chartjs-size-monitor-expand"
					style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div
						style="position: absolute; width: 1000px; height: 1000px; left: 0; top: 0"></div>
				</div>
				<div class="chartjs-size-monitor-shrink"
					style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div
						style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
				</div>
			</div>
			<h2>매출</h2>
			<%-- <canvas id="barChart" width="380" height="189"
				class="chartjs-render-monitor"
				style="display: block; height: 211px; width: 423px;"></canvas> --%>
			<div class="tabbable">
				<!-- 왼쪽과 오른쪽 탭에만 필요 -->
				
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
						<div class="table-responsive">
							<table class="table">
								<thead class="thead-dark">
									<tr>
										<th>분류</th>
										<th>옵션명</th>
										<th>총실적수</th>
										<th>단가</th>
										<th>총 금액</th>
									</tr>
								</thead>
								<tbody>${getSalescList}
								</tbody>
								<footer>
									<tr>${getSalesAllcList}
									</tr>
								</footer>

							</table>
							<!-- table  -->

							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/template.js"></script>
	<script type="text/javascript" src=" /resources/js/dashboard.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
<script type="text/javascript">
function advertisewritefrm() {
	$.ajax({
		type : "GET",
		url : "advertisewritefrm",
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
function salesPage() {
	$.ajax({
		type : "post",
		url : "sales",
		dataType : "html",
		success : function(data) {
			$('#main').html(data);
		},
		error : function() {
			alert('판매/실적 로드 실패');
		}

	});
	
}



var omg = "";
console.log("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","${aList }");
console.log("${getSalesAllcList}");
console.log("${getSalescList}");
console.log("${getSalesList}");
console.log("${getSalesAllList}");
$(document)
		.ready(
				function() {
					
					console.log("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","${aList }");
					console.log("${getSalesAllcList}");
					console.log("${getSalescList}");
					console.log("${getSalesList}");
					console.log("${getSalesAllList}");

					var t_cid = '${mb.m_id}'; /* $("#testInput").val(); */
					console.log(t_cid)

					$
							.ajax({
								type : "post",
								url : "chart",
								data : {
									t_cid : t_cid
								},
								dataType : "json",
								success : function(result) {
									console.log("result:", result);
									var str = "";
									var name = "";
									var ctx = document.getElementById(
											"myChart").getContext('2d');
									var arr = {};
									for (var i = 0; i < result.length; i++) {
										str += String(result[i].PS_PRICE)
												.split(9)
												+ ",";
										name += String(result[i].M_NAME)
												.split(7)
												+ ",";
									}
									var arr2 = (name.substring(0,
											name.length - 1)).split(",");
									var arr = (str.substring(0,
											str.length - 1)).split(",");

									var myChart = new Chart(
											ctx,
											{
												type : 'bar',
												data : {
													labels : arr2,
													datasets : [ {
														label : '# of Votes',
														data : arr,
														backgroundColor : [
																'rgba(255, 99, 132, 0.2)',
																'rgba(54, 162, 235, 0.2)',
																'rgba(255, 206, 86, 0.2)',
																'rgba(75, 192, 192, 0.2)',
																'rgba(153, 102, 255, 0.2)',
																'rgba(255, 159, 64, 0.2)' ],
														borderColor : [
																'rgba(255,99,132,1)',
																'rgba(54, 162, 235, 1)',
																'rgba(255, 206, 86, 1)',
																'rgba(75, 192, 192, 1)',
																'rgba(153, 102, 255, 1)',
																'rgba(255, 159, 64, 1)' ],
														borderWidth : 1
													} ]
												},
												options : {
													maintainAspectRatio : true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
													scales : {
														yAxes : [ {
															ticks : {
																beginAtZero : true
															}
														} ]
													}
												}
											});

								},
								error : function(er) {
									console.log(er);

								}
							});

					//실적 end

					//매출 start
					var t_cid2 = '${mb.m_id}'; /* $("#testInput").val(); */
					console.log("DDDDDDDDDDDD" + t_cid2)

					$
							.ajax({
								type : "post",
								url : "charttwo",
								data : {
									t_cid : t_cid2
								},
								dataType : "json",
								success : function(result) {
									console.log("result:", result);

									var str = "";
									var name = "";
									var ctx = document.getElementById(
											"myChart2").getContext('2d');
									var arr = {};
									for (var i = 0; i < result.length; i++) {
										str += String(result[i].PS_PRICE)
												.split(9)
												+ ",";
										name += String(
												result[i].AD_CATEGORY)
												.split(7)
												+ ",";
									}
									console.log(name)
									var arr2 = (name.substring(0,
											name.length - 1)).split(",");
									var arr = (str.substring(0,
											str.length - 1)).split(",");

									var myChart2 = new Chart(
											ctx,
											{
												type : 'bar',
												data : {
													labels : arr2,
													datasets : [ {
														label : '# of Votes',
														data : arr,
														backgroundColor : [
																'rgba(255, 99, 132, 0.2)',
																'rgba(54, 162, 235, 0.2)',
																'rgba(255, 206, 86, 0.2)',
																'rgba(75, 192, 192, 0.2)',
																'rgba(153, 102, 255, 0.2)',
																'rgba(255, 159, 64, 0.2)' ],
														borderColor : [
																'rgba(255,99,132,1)',
																'rgba(54, 162, 235, 1)',
																'rgba(255, 206, 86, 1)',
																'rgba(75, 192, 192, 1)',
																'rgba(153, 102, 255, 1)',
																'rgba(255, 159, 64, 1)' ],
														borderWidth : 1
													} ]
												},
												options : {
													maintainAspectRatio : true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
													scales : {
														yAxes : [ {
															ticks : {
																beginAtZero : true
															}
														} ]
													}
												}
											});

								},
								error : function(er) {
									console.log(er);

								}
							});

				});// redey End
</script>

</html>
