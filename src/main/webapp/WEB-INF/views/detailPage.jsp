<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
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
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
<!-- End custom js for this page-->

<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.serializeObject.js"></script>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY</title>
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
<link type="text/css" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.png" />
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>

<body>
	
				<!-- partial -->
				<div id="wrap">
					<div id="header">
						<jsp:include page="header.jsp" />
					</div>
					<div id="main">
						${detailPageHTML}
						
						

					</div>
					<div id="footer">
						<jsp:include page="footer.jsp" />
					</div>
				</div>

			
			<!-- main-panel ends -->
		
		<!-- page-body-wrapper ends -->
	
	<!-- container-scroller -->

</body>

<script>
$("#optionSelect").change(function(){
	var op_code = $("#optionSelect").prop("selectedOptions")[0].id.replace("op",""); 
	var $op_code = "#"+op_code;
	var $op_priceValue = $($op_code).val();
		
	$("#priceShow").html("<h3><small class='text-muted'>가격: </small>"+$op_priceValue+"원</h3>")
	
	if($("#optionSelect").val()=="상세 옵션을 선택해주세요"){
		$("#purchase").prop("disabled",true);
	}else{
		$("#purchase").prop("disabled",false);
	}
	
	console.log(op_code);
	console.log($op_priceValue);
	
});

$("#purchase").click(function(){
	var ad_code = $("#ad_code").val();
	var op_code = $("#optionSelect").prop("selectedOptions")[0].id.replace("op",""); 
	var $op_code = "#"+op_code;
	var $op_priceValue = $($op_code).val();
	
	console.log(ad_code);
	console.log(op_code);
	console.log($op_priceValue);
	
	$.ajax({
		type:'post', 
		url:'purchsingle',
		data: {ps_adcode:ad_code, ps_opcode:op_code, ps_price:$op_priceValue},
		//data:$('#rFrm').serialize(), 폼 전체 데이터 전송
		//processData:true,
		dataType:'html',
		success:function(data){
			console.log(data);
			alert("결제성공");

		},
		error:function(err){
			console.log(err);

		}
	}); //ajax End
});//click End



$("#review-tab").click(function(){
		//var obj=$("#rFrm").serializeObject(); //{속성:값,속성:값}
		var ad_code = $("#ad_code").val();
		//var obj=$("form[name=detailPageInfo]").serializeObject();
		//obj.r_bnum=bnum;//r_bnum은 name에 없으니 따로 추가해준다.
		//console.log(obj);
		//js객체-->json형태로 변환(문자열)
		//var jsonStr=JSON.stringify(obj);
		//console.log(jsonStr);
		$.ajax({
			type:'get', //json으로 넘길때는 무조건 post
			url:'review',
			data:{rv_adcode:ad_code},
			//data:$('#rFrm').serialize(), 폼 전체 데이터 전송
			dataType:'json',
			contentType:"application/json",
			success:function(data,status,xhr){
				console.log(data);
				var str='';
				$('#yes').html(str);
			},
			error:function(xhr,status){
				alert("error");
				console.log(status);
				console.log(xhr);
			}
		}); //ajax End

});//review click End

$("#qa-tab").click(function(){
	var ad_code = $("#ad_code").val();
	$.ajax({
		type:'get', 
		url:'qa',
		data:{qa_adcode:ad_code},
		dataType:'json',
		contentType:"application/json",
		success:function(data,status,xhr){
			console.log(data);
			var str='';
			$('#yes').html(str);
		},
		error:function(xhr,status){
			alert("error");
			console.log(status);
			console.log(xhr);
		}
	}); //ajax End

});//qna click End
</script>

</html>
