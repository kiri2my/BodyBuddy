<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Majestic Admin</title>
  <!-- plugins:css -->
  <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
  <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
  <!-- endinject -->
  <link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/images/favicon.png">
<style type="text/css">/* Chart.js */
@-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}
    
@
-webkit-keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
@
keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	-webkit-animation: chartjs-render-animation 0.001s;
	animation: chartjs-render-animation 0.001s;
}

.content-wrapper {
	text-align: center;
}

.sorting {
	text-align: center;
}

.odd {
	text-align: center;
}
.even {
	text-align: center;
}

#nav {
	position: fixed;
	top: 50px;
	height: 90%;
}

#main {
	position: absolute;
	left: 240px;
	top: 25px;
	width: 105%;
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
.btn.btn-dark.btn-lg.btn-block{
	size: 50%;
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



		<div class="row">
			<div class="col-md-12 grid-margin"></div>
		</div>
		<div class="row">
			<div class="col-md-12 stretch-card">
				<div class="card">
					<div class="card-body">
						<p class="card-title">내 찜목록</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">

								<div class="row">
									<div class="col-sm-10">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" 
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">광고명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">삭제</th>


											</thead>
											<tbody>

										${dibs}

											</tbody>
										</table>
									</div>
								</div>
	
							</div>
						</div>
					</div>
</div>
</div>
</div>
</div>
</div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
   		<div id="nav">
			<jsp:include page="sidebarNormal.jsp" />
		</div>
	</div>     
   


</body>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<script>
$(".btn.btn-dark").click(function(){
	var adnum=$(".btn.btn-dark").parent().children().eq(1).val();
	console.log(adnum);
	$.ajax({
		type:'get', 
		url:'dibsdelete',
		data:{d_adcode:adnum},
		dataType:'html',
		success:function(data){
			console.log(data);
			if(data.includes("dibsAdd")){
				alert("선택된 찜 삭제 완료");
				location.href="dibsn?m_id=${mb.m_id}";
			}else if(data.includes("dibsDelete")){
				alert("선택된 찜 삭제 실패");
			}
		},
		error:function(error){
			console.log(error);
		}
	
	});   
});
</script>
</html>