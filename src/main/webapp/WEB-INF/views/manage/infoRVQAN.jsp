<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY</title>
<script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
<!-- plugins:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/mdi/css/materialdesignicons.min.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<!-- endinject -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/images/favicon.png">
<style type="text/css">/* Chart.js */
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


#header {
	position: relative;
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

.modal {
	text-align: center;
}

.modal:before {
	display: inline-block;
	vertical-align: middle;
	content: " ";
	height: 100%;
}

.modal-dialog {
	display: inline-block;
	text-align: left;
	vertical-align: middle;
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
						<p class="card-title">후기목록</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">

								<div class="row">
									<div class="col-sm-12">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" 
														aria-label="Name: activate to sort column descending"
														style="width: 200px;">광고명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 200px;">옵션명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 800px;">내용</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 260px;">올린시간</th>	
				
												</tr>
											</thead>
											<tbody>
								
											${reviewListN}

											</tbody>
										</table>
										
									</div>
								</div>
	
							</div>
						</div>
					</div>
					<br/>
					
					
					
					
	
					
					
					
					
					<br/>
					<div id="se" class="card-body">
						<p class="card-title">문의 목록</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
								<div class="row">
									<div class="col-sm-5 col-md-12"></div>
									<div class="col-sm-5 col-md-12"></div>
								</div>
								
								<div class="row">
									<div class="col-sm-12">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" aria-sort="ascending"
														aria-label="Name: activate to sort column descending"
														style="width: 300px;">광고명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 200px;">내용보기</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 500px;">올린시간</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 470px;">답변시간</th>	
											</thead>
											<tbody>
											${questionListN}
											

											</tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-5"></div>
									<div class="col-sm-12 col-md-7"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
		<div id="nav">
			<jsp:include page="sidebarNormal.jsp" />
		</div>
	</div>



<!-- 모달 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content" style="width: 800px; height: 650px;">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">내 문의 보기</h4>
					
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
				문의 내용 : 
				<input id="qa_num" type="hidden"/>
					<div id="qa_wcontent" style="text-align: center;margin: 50px;"></div>
					<%-- $ --%>
				</div>

				<div class="modal-body">
					<input type="text" id="qa_acontent" name="qa_acontent"
						placeholder="아직 답변이 없습니다."
						disabled="disabled"
						style="text-align: center; width: 750px; height: 150px;"
						 />
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>



</body>
<script>
${alert}



$(".abtn").each(function() {
	$(this).click(function() {
		var num=$(this).parent().children().eq(1).val();
		console.log(num);

		$.ajax({
			url : "qnacheck",
			type : "post",
			data : {
				"qa_num" : num
			},
			dataType : "json",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				qajax = data;
				qajaxnum = data[0].qa_num;
				qajaxwcon = data[0].qa_wcontent;
				qajaxacon = data[0].qa_acontent;
				console.log("1 = ", qajaxnum)
				console.log("2 = ", qajaxwcon)
				console.log("3 = ", qajaxacon)
				$('#qa_wcontent').html(qajaxwcon);
				$('#qa_num').val(qajaxnum);
				$('#qa_acontent').prop("disabled", false);

			},
			error : function(error) {
				console.log(error);
				alert(" 실패 ");

			}
		})
	});//end ajax

});//end click

$(".cbtn").each(function() {
	$(this).click(function() {
		var num=$(this).parent().children().eq(1).val();
		console.log(num);

		$.ajax({
			url : "qnacheck",
			type : "post",
			data : {
				"qa_num" : num
			},
			dataType : "json",
			/*data:{m_id : $('#m_id').val(), sdf:"sdfsdfdfsdf"},*/
			success : function(data) {
				qajax = data;
				qajaxnum = data[0].qa_num;
				qajaxwcon = data[0].qa_wcontent;
				qajaxacon = data[0].qa_acontent;
				console.log("1-1 = ", qajaxnum)
				console.log("2-2 = ", qajaxwcon)
				console.log("3-3 = ", qajaxacon)
				$('#qa_wcontent').html(qajaxwcon);
				$('#qa_acontent').val(qajaxacon);
				$('#qa_num').val(qajaxnum);
				$('#qa_acontent').prop("disabled", true);

			},
			error : function(error) {
				console.log(error);
				alert(" 실패 ");

			}
		})
	});//end ajax

});//end click
</script>
</html>