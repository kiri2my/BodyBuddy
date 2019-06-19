<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<!-- plugins:js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendors/base/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendors/chart.js/Chart.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendors/datatables.net/jquery.dataTables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
<!-- End plugin js for this page-->
<!-- inject:js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/off-canvas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/hoverable-collapse.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/template.js"></script>
<!-- endinject -->
<!-- Custom js for this page-->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/data-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap4.js"></script>
<!-- End custom js for this page-->

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY(지도없음,로그인함)</title>
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
<link type="text/css" rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.png" />
<style>
.scroll {
	max-height: 97%;
	overflow-y: auto;
}
</style>

</head>

<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->

		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<!-- partial -->
			<div class="main-panel" style="width: 100%">
				<div class="content-wrapper">
					<div class="row">
						<div class="col-md-12 grid-margin stretch-card">
							<div class="card">
								<div class="card-body dashboard-tabs p-0">
									<ul class="nav nav-tabs px-4" role="tablist">
										<li class="nav-item"><a class="nav-link active"
											id="overview-tab" data-toggle="tab" href="#overview"
											role="tab" aria-controls="overview" aria-selected="true"
											style="border-bottom-color: #71c016; color: #71c016">주소검색</a>
										</li>
										<li class="nav-item"></li>
										<li class="nav-item"></li>
									</ul>
									<div class="tab-content py-0 px-0">
										<div class="tab-pane fade show active" id="overview"
											role="tabpanel" aria-labelledby="overview-tab">
											<div class="d-flex flex-wrap justify-content-xl-between">

												<div class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
													<li class="nav-item nav-search d-none d-lg-block w-100">
														<div class="input-group">
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
															<button class="input-group-text"   onclick="sample5_execDaumPostcode()">주소선택</button>&nbsp;&nbsp;&nbsp;
															<form id="addrInput" name="addrInput" action="${pageContext.request.contextPath}/"><%-- action="${pageContext.request.contextPath}/" --%>
															<div class="input-group-prepend">
																<input type="text" id="sample5_address" name="sido" class="form-control" placeholder="시·도" required="required"/>&nbsp;&nbsp;&nbsp;
																<input type="text" id="sample6_address" name="sigungu" class="form-control" placeholder="시·군·구" required="required"/>&nbsp;&nbsp;&nbsp;
																<input type="text" id="sample7_address" name="extra" class="form-control" placeholder="도로명·지번" required="required"/>&nbsp;&nbsp;
																<span class="input-group-text" id="localSearch">
																	<i class="mdi mdi-magnify"></i>
																</span>
															</div>
															</form>
														</div>
													</li>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div> -->
					<div class="row" style="height: 60em;">
						<div id="mapCard" class="col-md-4 stretch-card" hidden="true">
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-title">지도</p>
                                    <div id="map">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
						${mainListHTML}
					</div>
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->

</body>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script	type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
<script	type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/additional-methods.js"></script>
<script type="text/javascript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f321e26e149ff9c8fec71aba7e8aa47c&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">

//헤더 메뉴 카테고리버튼 /categoryselect id=cateAll cateFitness cateHomeTraining catePilates cateYoga
$(".cate").click(function(){
	var sido = $("#sample5_address").val();
	var sigungu = $("#sample6_address").val();
	var extra = $("#sample7_address").val();
	var cate = $(this).prop("id");
	console.log(cate);

	if(sido!="" && sigungu!="" && extra!=""){
		location.href="${pageContext.request.contextPath}/?sido="+sido+"&sigungu="+sigungu+"&extra="+extra+"&cate="+cate;
	}else{
		location.href="${pageContext.request.contextPath}/?cate="+cate;	
	}
	
});

//지도
var mapContainer = document.getElementById('mapCard'); // 지도를 표시할 div
	mapOption = {center: new daum.maps.LatLng(37.537187, 127.005476), level: 5};//(지도의 중심좌표,지도의 확대 레벨)
var map = new daum.maps.Map(mapContainer, mapOption);//지도를 미리 생성
var geocoder = new daum.maps.services.Geocoder();//주소-좌표 변환 객체를 생성
function sample5_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {// 주소 정보를 해당 필드에 넣는다.
    		var sido = data.sido;
    		var sigungu = data.sigungu;
    		var bname = data.bname;
    		var roadname = data.roadname;
    		document.getElementById("sample5_address").value = sido;
    		document.getElementById("sample6_address").value = sigungu;
    		document.getElementById("sample7_address").value = bname+"/"+roadname; 
		}//oncomplete: function(data) { END
	}).open();//new daum.Postcode END
}//sample5_execDaumPostcode END
/* $(document).ready(function(){ 	
$("#addrInput").validate();//end Validate
}); */

$("#localSearch").click(function(){//주소검색버튼 클릭
var sido = $("#sample5_address").val();
var sigungu = $("#sample6_address").val();
var extra = $("#sample7_address").val(); 
   
console.log( "addr",sido, sigungu, extra);
//location.href="${pageContext.request.contextPath}/?sido="+sido+"&sigungu="+sigungu+"&extra1="+extra1+"&extra2="+extra2;
if(sido!="" && sigungu!="" && extra!="") addrInput.submit();
else alert("주소별 검색어를 모두 입력하고 검색해주세요.");
});//end Click localSearch

//화면에 출력된 광고들의 주소리스트 뽑기
var jsonMainList = ${jsonMainList}

var addrList = new Array();
for(var i=0;i<jsonMainList.length; i++){
	//var addrRecord = jsonMainList[i]["M_ADDR"]+" "+jsonMainList[i]["M_EXADDR"]
	var addrRecord = jsonMainList[i]["M_ADDR"]
	addrList[i] += addrRecord.replace("undefined","");
}
for(var i=0;i<addrList.length; i++){
	addrList[i]=addrList[i].replace("undefined","");
}
console.log(addrList);
//리스트에 따른 좌표 마커 미리 생성
for(var i=0; i< addrList.length; i++){
	geocoder.addressSearch(addrList[i], function(results, status) {
		if (status === daum.maps.services.Status.OK) {//검색결과가 있다면
			var result = results[0]; //첫번째 결과의 값을 활용
	   	 	// 마커가 표시될 위치입니다 
	    	var markerPosition  = new daum.maps.LatLng(result.y, result.x); // 해당 주소에 대한 좌표를 받아서
	    	// 마커를 생성합니다
	    	var marker = new daum.maps.Marker({
	        	position: markerPosition
	    	});
	    	// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			// 지도 중심을 변경한다.
    		map.setCenter(markerPosition);
		}//if END
	});//geoCoder END
}//for END
//지도를 보여준다.
var showMap = ${showMap}
if(showMap){
	$("#listCard").prop("className","col-md-8 card scroll");//col-md-12 card scroll
	$("#mapCard").prop("hidden",false);
	mapContainer.style.display = "block";
	map.relayout();
}
//인풋창에 검색내용 보여준다.
var sido = "${sido}";
var sigungu = "${sigungu}";
var extra = "${extra}";
var extra1 = "${extra1}";
var extra2 = "${extra2}";
$("#sample5_address").val(sido);
$("#sample6_address").val(sigungu);
if(extra1=="") $("#sample7_address").val(extra); 
if(extra=="") $("#sample7_address").val(extra1+extra2);




//찜

console.log(jsonMainList);
var delBtnSet = ${delBtnSet}
console.log(delBtnSet);

var kind="${mb.m_kind}";
if(kind=='n'){
for(var i=0;i<jsonMainList.length; i++){
	var addBtnBefATag = "#showdetail"+jsonMainList[i]["AD_CODE"];
	//console.log("add",$(addBtnBefATag));
	
	var addBtn = "<button id='dibsAdd" +jsonMainList[i]["AD_CODE"]
	+ "' type='button' class='btn btn-outline-secondary btn-rounded btn-icon'>"
	+ "<i class='mdi mdi-heart-outline text-danger'></i></button>";
	$(addBtnBefATag).after(addBtn);
}


for(var i=0;i<delBtnSet.length;i++){
	var targetAddBtn = "#dibsAdd"+delBtnSet[i];
	console.log("del",$(targetAddBtn));
	
	var delBtn = "<button id='dibsDelete"+delBtnSet[i]
	+ "' type='button' class='btn btn-outline-danger btn-rounded btn-icon'>"
	+ "<i class='mdi mdi-heart'></i></button>";
	$(targetAddBtn).prop("outerHTML",delBtn);
}
}


	dibsAdd();
	dibsDelete();

	function dibsAdd() {
		$(".btn.btn-outline-secondary.btn-rounded.btn-icon").click(function() {
			var dibsAddBtn = $(this);
			var ad_code = $(this).prop("id").replace("dibsAdd", "");
			$.ajax({
				type : 'get',
				url : 'dibsadd',
				data : {
					d_adcode : ad_code
				},
				dataType : 'html',
				success : function(data) {
					console.log("dibsadd",data);
					//console.log(dibsAddBtn);
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
				data : {
					d_adcode : ad_code
				},
				dataType : 'html',
				success : function(data) {
					console.log("dibsdelete",data);
					//console.log(dibsDeleteBtn);
					dibsDeleteBtn.prop("outerHTML", data);
					dibsAdd();
				},
				error : function(err) {
					console.log(err);
				}
			}); //ajax End
		});//click danger
	}//end dibsDelete
</script >

</html>