<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Majestic Admin</title>
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
#articleView_layer {

	display: none;
	position: fixed;	
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

#articleView_layer.open {
	display: block;
	color: red
}

#articleView_layer #bg_layer {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #000;
	opacity: .5;
	filter: alpha(opacity = 50);
	z-index: 100;
}

 #contents_layer {
	
	position: absolute;
	top: 40%;
	left: 40%;
	width: 700px;
	height: 700px;
	margin: -150px 0 0 -194px;
	padding: 28px 28px 0 28px;
	border: 2px solid #555;
	background: silver;
	font-size: 12px;
	z-index: 200;
	color: #767676;
	line-height: normal;
	white-space: normal;
	overflow: scroll
} 
</style>
</head>
<body>

	<div class="container-scroller">



		<div class="row">
			<div class="col-md-12 grid-margin"></div>
		</div>
		<div class="row">
			<div class="col-md-12 stretch-card">
				<div class="card">
					<div class="card-body">
						<p class="card-title">내 프로그램</p>
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
														style="width: 80px;">트레이너</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 140px;">옵션</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 140px;">이용기간</th>	
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 70px;">카테고리</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 70px;">이용상태</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">출결현황</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">후기</th>
												</tr>
											</thead>
											<tbody>

										${programListN}

											</tbody>
										</table>
									</div>
								</div>
	
							</div>
						</div>
					</div>
					<br>
					<br>
					<br>
					<div id="articleView_layer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div id="bg_layer"></div>
					<div id="contents_layer" style="width: 60em"></div>
					</div>
					<br>
					<br>
					<br>
					<div id="se" class="card-body">
						<p class="card-title">일반 피트니스</p>
						<div class="table-responsive">
							<div id="recent-purchases-listing_wrapper"
								class="dataTables_wrapper container-fluid dt-bootstrap4 no-footer">
								<div class="row">
									<div class="col-sm-6 col-md-5"></div>
									<div class="col-sm-6 col-md-5"></div>
								</div>
								
								<div class="row">
									<div class="col-sm-10">
										<table id="recent-purchases-listing"
											class="table dataTable no-footer" role="grid">
											<thead>
												<tr role="row">
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1" aria-sort="ascending"
														aria-label="Name: activate to sort column descending"
														style="width: 80px;">광고명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 80px;">업체명</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Status report: activate to sort column ascending"
														style="width: 80px;">이용기간</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Office: activate to sort column ascending"
														style="width: 80px;">출결확인</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Price: activate to sort column ascending"
														style="width: 80px;">이용상태</th>
													<th class="sorting" tabindex="0"
														aria-controls="recent-purchases-listing" rowspan="1"
														colspan="1"
														aria-label="Date: activate to sort column ascending"
														style="width: 80px;">후기쓰기</th>
											</thead>
											<tbody>
										
			${getnormalListN}

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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="margin-left: 20%;">
    <div class="modal-content" style="width: 60em">
      <div class="modal-header">
      <h4 class="modal-title" id="myModalLabel"></h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default closer" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>





</body>
<script>
${alert}

$(".btn.btn-dark.btn-lg.btn-block").click(function(){
	
	var list = new Array();
	
	/* var adnum1=$("#op_code").val(); */
	
	var adnum=$(this).parent().children().eq(1).val();
	var mid=$(this).parent().children().eq(2).val();
	
/* 	$(document).ready(function() {
		   var list = new Array();
		   $("input[name=testInput]").each(function(index, item){
			   list.push($(item).val());
		   });
		   alert(list);
		}); */
	
	console.log(adnum);
	console.log(mid);
 	$('#articleView_layer').addClass('open');
	$.ajax({
		type:'post', 
		url:'counsellistn',
		data:{cs_opcode:adnum,cs_mid:mid},
		dataType:'html',
		success:function(data){
			$('#contents_layer').html(data);
		},
		error:function(error){
			console.log(error);
		}
	
	});   
	//ajax End 

});


function yyyyyy(bnum) {
	
	var list = new Array();
	/* var adnum1=$("#op_code").val(); */
	
/* 	$(document).ready(function() {
		   var list = new Array();
		   $("input[name=testInput]").each(function(index, item){
			   list.push($(item).val());
		   });
		   alert(list);
		}); */
	var bbb=bnum;
	console.log(bbb);
	console.log(bbb);
 	$('#articleView_layer').addClass('open');
	$.ajax({
		type:'get', 
		url:'calenderN',
		data:{ps_code:bbb},
		dataType:'html',
		success:function(data){
			$('#contents_layer').html(data);
		},
		error:function(error){
			console.log(error);
		}
	
	});   
	//ajax End 

}
	

	//ModalBox 해제
	var $layerWindow=$('#articleView_layer');
	$layerWindow.find('#bg_layer').on('mousedown',function(event){
		console.log(event);
		$layerWindow.removeClass('open');
		
	})//function End
	
	$(".profilePage").click(function(){
		var m_id = $(this).prop("id").replace("profilePage","");
		console.log(m_id);
		$.ajax({
			url: "profilepage",
			type: "get",
			data:{m_id:m_id},
			dataType: "html",
			success:(function(data){
				console.log(data);
				$(".modal-body").html(data);
			   
				
			}),
			error:(function(err){
				console.log(err);
			})
		});
	});
	
	
</script>
</html>