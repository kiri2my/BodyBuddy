<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
	<div id="bodybody" style="left: 0px; top: 15px; position: relative; display: block;">
		<div
			style="top: 0px; left: 0px; position: absolute; width: 550px; height: 300px;">

			<h3 style="text-align: center;">최근 신규 가입</h3>

			<table class="table table-hover" style="table-layout: fixed;">
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


			<h3 style="text-align: center;">최근 판매 내역</h3>

			<table class="table table-hover" style="table-layout: fixed;">
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
			<h3 style="text-align: center;">등록중인 광고</h3>
			<table class="table table-hover"  style="table-layout: fixed;">
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

							<h2>실적</h2>

							<div class="tab-pane" id="tab2">
								<div class="table-responsive">
									<table class="table">
										<thead class="thead-dark">
											<tr>
												<th>트레이너명</th>
												<th>프로그램명</th>
												<th>실적수</th>
												<th>단가</th>
												<th>금액</th>
											</tr>
										</thead>

										<tbody>${getSalesList}
										</tbody>

									</table>
								</div>
								<br>

								<table class="table">
									<thead class="thead-dark">
										<tr>
											<th>트레이너명</th>
											<th>총실적수</th>
											<th>총금액</th>
										</tr>
									</thead>
									<tbody>${getSalesAllList}
									</tbody>

								</table>
								<div style="width: 1500px">
									<canvas id="myChart"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var omg = "";

$(document)
		.ready(
				function() {
					var t_cid = '${mb.m_id}'; /* $("#testInput").val(); */
					console.log(t_cid)

						$.ajax({
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
									var ctx = document.getElementById("myChart").getContext('2d');
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

						$.ajax({
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
									var ctx = document.getElementById("myChart2").getContext('2d');
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
