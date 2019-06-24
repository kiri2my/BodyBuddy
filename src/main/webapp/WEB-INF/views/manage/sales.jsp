<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY_COMPANY_MANAGER</title>
<!-- plugins:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<!-- endinject -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/images/favicon.png" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/bootstrap.js" />
			<!-- plugins:js -->
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
			<!-- endinject -->
			<!-- Plugin js for this page-->
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
			<!-- End plugin js for this page-->
			<!-- inject:js -->
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/template.js"></script>
			<!-- endinject -->
			<!-- Custom js for this page-->
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css/bootstrap.css"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/css/style.css"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
			<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
			<!-- End custom js for this page-->
</head>

<body>
	<div class="tabbable">
		<!-- 왼쪽과 오른쪽 탭에만 필요 -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab">매출</a></li>
			<li><a href="#tab2" data-toggle="tab">실적</a></li>
		</ul>
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
						<tbody>
							${getSalescList}
						</tbody>
						<footer>
							<tr>
								${getSalesAllcList}
							</tr>
						</footer>
						
					</table>
															<div style="width:1500px">
    <canvas id="myChart2"></canvas>
</div>
				</div>
			</div>

			
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
						
						<tbody>
							${getSalesList}
							
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
						<tbody>
						${getSalesAllList}

						</tbody>

					</table>
					<div style="width:1500px">
    <canvas id="myChart"></canvas>
</div>
				</div>
				

				
			</div>
			
</body>
<script>
var omg="";
$(document).ready(function(){
	
	
	 var t_cid = $("#testInput").val();
	 console.log(t_cid)

		$.ajax({
			type : "post",
			url : "chart",
			data : {
				t_cid : t_cid
			},
			dataType : "json",
			success : function(result) {
			console.log("result:",result);
				var str = "";
				var name = "";
				var ctx = document.getElementById("myChart").getContext('2d');
				var arr = {};
				for(var i=0;i<result.length;i++){		
					str += String(result[i].PS_PRICE).split(9)+",";
					name += String(result[i].M_NAME).split(7)+",";
				}
					var arr2=(name.substring(0, name.length-1)).split(",");
					var arr=(str.substring(0, str.length-1)).split(",");
			
					
				var myChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: arr2,
				        datasets: [{
				            label: '# of Votes',
				            data: arr,
				            backgroundColor: [
				                'rgba(255, 99, 132, 0.2)',
				                'rgba(54, 162, 235, 0.2)',
				                'rgba(255, 206, 86, 0.2)',
				                'rgba(75, 192, 192, 0.2)',
				                'rgba(153, 102, 255, 0.2)',
				                'rgba(255, 159, 64, 0.2)'
				            ],
				            borderColor: [
				                'rgba(255,99,132,1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
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
	 var t_cid2 = $("#testInput").val();
	 console.log("DDDDDDDDDDDD"+t_cid2)

		$.ajax({
			type : "post",
			url : "charttwo",
			data : {
				t_cid : t_cid2
			},
			dataType : "json",
			success : function(result) {
			console.log("result:",result);

				var str = "";
				var name = "";
				var ctx = document.getElementById("myChart2").getContext('2d');
				var arr = {};
				for(var i=0;i<result.length;i++){		
					str += String(result[i].PS_PRICE).split(9)+",";
					name += String(result[i].AD_CATEGORY).split(7)+",";
				}
				console.log(name)
					var arr2=(name.substring(0, name.length-1)).split(",");
					var arr=(str.substring(0, str.length-1)).split(",");
			
					
				var myChart2 = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: arr2,
				        datasets: [{
				            label: '# of Votes',
				            data: arr,
				            backgroundColor: [
				                'rgba(255, 99, 132, 0.2)',
				                'rgba(54, 162, 235, 0.2)',
				                'rgba(255, 206, 86, 0.2)',
				                'rgba(75, 192, 192, 0.2)',
				                'rgba(153, 102, 255, 0.2)',
				                'rgba(255, 159, 64, 0.2)'
				            ],
				            borderColor: [
				                'rgba(255,99,132,1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)',
				                'rgba(255, 159, 64, 1)'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
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
