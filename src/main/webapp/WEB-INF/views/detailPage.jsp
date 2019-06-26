<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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
	
</head>

<body>
<!-- Modal -->
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
<!-- MODAL END -->
	
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

<%-- <script src="${pageContext.request.contextPath}/resources/js/kirimWebSocket.js"></script> --%>
<script>
$(document).ready(function(){
	$('#carousel-adPhoto-generic').carousel({interval:0});
});
		



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



$(".text-success.showHiddenReview").click(function(){
	
	$(this).parents().find(".hiddenReview").eq(0).prop("hidden",false);
	$(this).parents().find(".text-success.foldHiddenReview").eq(0).prop("hidden",false);
	$(this).prop("hidden",true);
});
$(".text-success.foldHiddenReview").click(function(){
	$(this).parents().find(".hiddenReview").eq(0).prop("hidden",true);
	$(this).parents().find(".text-success.showHiddenReview").eq(0).prop("hidden",false);
	$(this).prop("hidden",true);
});

$(".text-success.showHiddenQna").click(function(){
	$(this).parent().eq(0).children().eq(2).eq(0).prop("hidden",false);//.hiddenQna
	$(this).parent().eq(0).children().eq(3).eq(0).prop("hidden",false);//.foldHiddenQna
	$(this).prop("hidden",true);
});
$(".text-success.foldHiddenQna").click(function(){
	$(this).parent().eq(0).children().eq(2).eq(0).prop("hidden",true);//.hiddenQna
	$(this).parent().eq(0).children().eq(1).eq(0).prop("hidden",false);//.showHiddenQna
	$(this).prop("hidden",true);
});




$("#optionSelect").change(function(){
	var op_code = $("#optionSelect").prop("selectedOptions")[0].id.replace("op",""); 
	var $op_code = "#"+op_code;
	var $op_priceValue = $($op_code).val();
	if($("#optionSelect").val()=="상세 옵션을 선택해주세요"){
		$("#purchase").prop("disabled",true);
		$("#priceShow").html("<h3><small class='text-muted'>가격: </small>0원</h3>")
	}else{
		$("#purchase").prop("disabled",false);
		$("#priceShow").html("<h3><small class='text-muted'>가격: </small>"+$op_priceValue+"원</h3>")
	}
	console.log(op_code);
	console.log($op_priceValue);
});





//메인찜복사
var delBtnSet = ${delBtnSet}
console.log(delBtnSet);

var kind="${mb.m_kind}";

console.log(kind);

if(kind!='n'){
	//구매버튼 통제
	$("#purchase").prop("disabled",true);
	$("#purchase").attr("data-toggle","tooltip");
	$("#purchase").attr("data-placement","bottom");
	$("#purchase").attr("title","일반 회원만 구매가 가능합니다");
	$("#purchase").after("<br><br><p class='text-light bg-dark pl-1 '>일반 회원만 구매가 가능합니다</p>");
}else{
	//n일때 찜버튼
	var ad_code = $("#ad_code").val();
	var addBtn = "<button id='dibsAdd" +ad_code
	+ "' type='button' class='btn btn-outline-secondary btn-rounded btn-icon'>"
	+ "<i class='mdi mdi-heart-outline text-danger'></i></button>";
	$("#purchase").before(addBtn);
	for(var i=0;i<delBtnSet.length;i++){
		if(delBtnSet[i]==ad_code){
			var targetAddBtn = "#dibsAdd"+delBtnSet[i];
			console.log("del",$(targetAddBtn));
			
			var delBtn = "<button id='dibsDelete"+delBtnSet[i]
			+ "' type='button' class='btn btn-outline-danger btn-rounded btn-icon'>"
			+ "<i class='mdi mdi-heart'></i></button>";
		
			$(targetAddBtn).prop("outerHTML",delBtn);
		}
	}
	//메인찜복사
	//n일때 문의하기 버튼 보여주기
	$(".qFrm").prop("hidden",false);
}
//문의하기 폼
$(".qFrm").click(function(){
	var qFrm = "<div class='form-group' id='form-group'>"+
    		"<textarea placeholder='광고주에게 문의할 내용을 입력해주세요' class='form-control' id='exampleTextarea1' rows='4'></textarea></div>";
    var btns = "<button id='qaWrite' type='button' class='btn btn-primary mr-2' data-dismiss='modal'>문의 등록</button>"+
    		"<button type='button' class='btn btn-default closer' data-dismiss='modal'>닫기</button>";
    
    $(".modal-footer").html(btns);
    $(".modal-body").html(qFrm);
  //문의등록    
    $("#qaWrite").click(function(){
    	var ad_name = $("#ad_name").val();
    	var ad_code = $("#ad_code").val();
    	var m_id = "${mb.m_id}";
    	console.log(ad_code);
    	var qa_wContent = $("#exampleTextarea1").val();
    	
    	console.log(qa_wContent);
    	$.ajax({
    		url:"detailqawriteinsert",
    		type:"post",
    		data:{qa_adcode:ad_code, qa_wContent:qa_wContent, qa_answer:ad_name},	
			dataType:"html",
			success:function(data){
				console.log(data);
				if(data!=null){
					//웹소켓 문의알림 보내기
					var jsonSendQnaAlarm = JSON.stringify({ad_code:ad_code,m_id:m_id});
					$("#msg").val("QA01"+jsonSendQnaAlarm);
					console.log("WebSock4", sock);
					console.log($("#msg").val());
					sock.send($("#msg").val());
					//
					$("#question").html(data);	
				}
				
			},
			error:function(err){
				console.log(err);
			}
    		
    	});//ajax END
    });//qaWrite click END
});//qFrm click END



//





$("#purchase").click(function(){
	var ad_code = $("#ad_code").val();
	var op_code = $("#optionSelect").prop("selectedOptions")[0].id.replace("op",""); 
	var $op_code = "#"+op_code;
	var $op_priceValue = $($op_code).val();
	var m_id = "${mb.m_id}";
	
	console.log(ad_code);
	console.log(op_code);
	console.log($op_priceValue);
	
	
	$.ajax({
		type:'post', 
		url:'purchsingle',
		data: {ps_adcode:ad_code, ps_opcode:op_code, ps_price:$op_priceValue},
		//data:$('#rFrm').serialize(), 폼 전체 데이터 전송
		//processData:true,
		dataType:'text',
		success:function(data){
			console.log(data);
			if(data=='success'){
				//웹소켓 구매알림 보내기
				var jsonSendPurAlarm = JSON.stringify({ad_code:ad_code,m_id:m_id});
				$("#msg").val("PS01"+jsonSendPurAlarm);
				console.log("WebSock3", sock);
				console.log($("#msg").val());
				sock.send($("#msg").val());
				//
				var sucConf = confirm("구매에 성공하셨습니다. 내 프로그램 보기로 이동하시겠습니까?");
				if(sucConf == true){
					location.href="infoprogramn?m_id=${mb.m_id}";
				}else if(sucConf == false){
					location.href="#";
				}
			}
			if(data=='full'){
				alert("해당 상품의 정원이 가득찼습니다.");
			}
			if(data=='failed'){
				alert("구매에 실패하였습니다. 다시 시도해주세요.");
			}
			if(data=='login'){
				var logConf = confirm("로그인을 해야 구매가 가능합니다. 로그인창으로 이동하시겠습니까?");
				if(logConf == true){
					location.href="login";
				}else if(logConf == false){
					location.href="#";
				}
			}
			if(data=='notn'){
				alert("일반회원만 결제 가능합니다.")
			}
			if(data=='overlap'){
				alert("이미 구매한 상품입니다.");
			}
		},
		error:function(err){
			console.log(err);
		}
	}); //ajax End
});//click End

dibsAdd();
dibsDelete();

function dibsAdd() {
	$(".btn.btn-outline-secondary.btn-rounded.btn-icon").click(function() {
		var dibsAddBtn = $(this);
		var ad_code = $(this).prop("id").replace("dibsAdd", "");
		$.ajax({
			type : 'get',
			url : 'dibsadd',
			data : {d_adcode : ad_code},
			dataType : 'html',
			success : function(data) {
				console.log(data);
				console.log(dibsAddBtn);
				dibsAddBtn.prop("outerHTML", data);
				dibsDelete();
			},
			error : function(err) {
				console.log(err);
			}
		}); //ajax End
	});//click secondary
}//end dibsAdd
function dibsDelete() {
	$(".btn.btn-outline-danger.btn-rounded.btn-icon").click(function() {
		console.log($(this));
		var dibsDeleteBtn = $(this);
		var ad_code = $(this).prop("id").replace("dibsDelete", "");
		$.ajax({
			type : 'get',
			url : 'dibsdelete',
			data : {d_adcode : ad_code},
			dataType : 'html',
			success : function(data) {
				console.log(data);
				console.log(dibsDeleteBtn);
				dibsDeleteBtn.prop("outerHTML", data);
				dibsAdd();
			},
			error : function(err) {
				console.log(err);
			}
		}); //ajax End
	});//click danger
}//end dibsDelete
/*
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
			//contentType:"application/json",
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
		//contentType:"application/json",
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
*/

</script>

</html>