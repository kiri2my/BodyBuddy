<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BODY BUDDY</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- plugin css for this page -->
    <link rel="stylesheet" href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <link rel="stylesheet" href="css/style.css">
    <!-- endinject -->
    <link rel="shortcut icon" href="images/favicon.png" />
    <link rel="stylesheet" href="css/bootstrap.css">
</head>

<body>
    <div class="container-scroller">
        <!-- partial:partials/_navbar.html -->
        <!--header-->
        <!--<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div class="navbar-brand-wrapper d-flex justify-content-center">
                <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
                    <a class="navbar-brand brand-logo" href="main.html" style="color: #71c016;">BODY BUDDY</a>
                    <a class="navbar-brand brand-logo-mini" href="main.html" style="color: #71c016;">BODY BUDDY</a>
                    <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                        <span class="mdi mdi-sort-variant"></span>
                    </button>
                </div>
            </div>
            <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
                <ul class="navbar-nav mr-lg-4 w-100">
                    <li>
                        <button type="button" class="btn btn-outline-dark btn-fw">전체보기</button>
                    </li>&nbsp;&nbsp;&nbsp;&nbsp;
                    <li>
                        <button type="button" class="btn btn-outline-success">피트니스 센터</button>
                    </li>&nbsp;&nbsp;&nbsp;&nbsp;
                    <li>
                        <button type="button" class="btn btn-outline-success">홈트레이닝</button>
                    </li>&nbsp;&nbsp;
                    <li>
                        <button type="button" class="btn btn-outline-success">필라테스</button>
                    </li>&nbsp;&nbsp;
                    <li>
                        <button type="button" class="btn btn-outline-success">요가</button>
                    </li>&nbsp;&nbsp;
                </ul>
                <ul class="navbar-nav navbar-nav-right">
                    <li class="nav-item dropdown mr-4">
                        <a class="nav-link count-indicator dropdown-toggle d-flex align-items-center justify-content-center notification-dropdown" id="notificationDropdown" href="#" data-toggle="dropdown">
                            <i class="mdi mdi-bell mx-0"></i>
                            <span class="count"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="notificationDropdown">
                            <p class="mb-0 font-weight-normal float-left dropdown-header">알림</p>
                            <a class="dropdown-item">
                                <div class="item-content">
                                    <h6 class="font-weight-normal">김X솔 회원 박X솔 트레이너의 XXX프로그램 결제함</h6>
                                    <p class="font-weight-light small-text mb-0 text-muted">
                                        방금 전
                                    </p>
                                </div>
                            </a>
                            <a class="dropdown-item">
                                <div class="item-content">
                                    <h6 class="font-weight-normal">차X헌 트레이너의 소속 승인요청 대기중</h6>
                                    <p class="font-weight-light small-text mb-0 text-muted">
                                        2 일 전
                                    </p>
                                </div>
                            </a>
                        </div>
                    </li>
                    <li class="nav-item nav-profile dropdown">
                        <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
                            <img src="images/faces/mydefault.jpg" alt="profile" />
                            <span class="nav-profile-name">사용자 이름(M_NAME)</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
                            <a class="dropdown-item">
                                <i class="mdi mdi-settings text-primary"></i>
                                마이페이지
                            </a>
                            <a class="dropdown-item">
                                <i class="mdi mdi-logout text-primary"></i>
                                로그아웃
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>-->
        <!-- partial -->

        <!--sidebar-->
        <!--<div class="container-fluid page-body-wrapper">
             partial:partials/_sidebar.html 
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
            
        <ul class="nav">
         
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="mdi mdi-account-multiple menu-icon"></i>
              <span class="menu-title">내 회원목록</span>
            </a>
                        
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="pages/tables/basic-table.html">
              <i class="mdi mdi-bulletin-board menu-icon"></i>
              <span class="menu-title">내 프로그램 광고 관리</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="index.html">
              <i class="mdi mdi-account-card-details menu-icon"></i>
              <span class="menu-title">내 프로필 관리</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="pages/charts/chartjs.html">
              <i class="mdi mdi-comment-question-outline menu-icon"></i>
              <span class="menu-title">내 문의 관리</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="pages/forms/basic_elements.html">
              <i class="mdi mdi-square-inc-cash menu-icon"></i>
              <span class="menu-title">내 판매 내역</span>
            </a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="pages/charts/chartjs.html">
              <i class="mdi mdi-walk menu-icon"></i>
              <span class="menu-title">회원 탈퇴하기</span>
            </a>
          </li>
          
          
        </ul>
      </nav>-->
        <!-- partial -->
        <!--주소검색-->
        <!--<div class="main-panel" style="width: 100%">
                <div class="content-wrapper">
                    <div class="row">
                        <div class="col-md-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body dashboard-tabs p-0">
                                    <ul class="nav nav-tabs px-4" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="overview-tab" data-toggle="tab" href="#overview" role="tab" aria-controls="overview" aria-selected="true" style="border-bottom-color : #71c016; color: #71c016">주소검색</a>
                                        </li>
                                        <li class="nav-item">
                                        </li>
                                        <li class="nav-item">
                                        </li>
                                    </ul>
                                    <div class="tab-content py-0 px-0">
                                        <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                                            <div class="d-flex flex-wrap justify-content-xl-between">

                                                <div class="d-flex border-md-right flex-grow-1 align-items-center justify-content-center p-3 item">
                                                    <li class="nav-item nav-search d-none d-lg-block w-100">
                                                        <div class="input-group">
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <span class="input-group-text" id="addrSearch">
                                                                주소찾기
                                                            </span>&nbsp;&nbsp;&nbsp;
                                                            <div class="input-group-prepend">

                                                                <input type="text" class="form-control" placeholder="시·도" />&nbsp;&nbsp;&nbsp;
                                                                <input type="text" class="form-control" placeholder="시·군·구" />&nbsp;&nbsp;&nbsp;
                                                                <input type="text" class="form-control" placeholder="상세주소" />&nbsp;&nbsp;
                                                                <span class="input-group-text" id="localSearch">
                                                                    <i class="mdi mdi-magnify"></i>
                                                                </span>
                                                            </div>

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
                    <div class="row" style="height: 700px">-->

        <!--<div class="col-md-4 stretch-card" >
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-title">지도</p>
                                    <div id="map">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>-->
		<div class="row" style="height: 700px">
		<div class="col-md-12 stretch-card">
			<!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도-->
			<div class="card">
				<div class="card-body">
					<p class="card-title">광고 관리</p>
					<div class="table-responsive">
						<table id="recent-purchases-listing" class="table">
							<c:set var="advertise" value="${adList }" />
							<c:if test="${empty advertise }">
									광고가 없습니다.
								</c:if>
							<c:if test="${!empty advertise }">
								<thead>
									<tr>
										<th >카테고리</th>
										<th>광고명</th>
										<th>등록일</th>
										<th>상태</th>
										<th>담당트레이너</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="advertise" items="${adList}">
										<tr>
											<td>${advertise.ad_category}</td>
											<td><a href="#">${advertise.ad_title}</a></td>
											<td><a href="#">${advertise.ad_date}</a></td>
											<td><a href="#">${advertise.ad_status}</a></td>
											<td>${advertise.op_trainer }</td>
											<td><input type="button" value="수정"><input type="button" value="삭제"></td>
											
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

       <!--  <div class="col-md-12 stretch-card">
            md-12면 화면에 꽉 차고 md-7리스트, md-5지도
            <div class="card">
                <div class="card-body">
                    <div class="input-group">
                        <form class="navbar-serch pull-left">
                            <input type="text" class="search-query" placeholder="검색 키워드를 입력하세요!" style="width:300px;">
                            <span class="input-group-btn">
                                <button class="btn btn-secondary" type="button">찾기</button>
                            </span>
                        </form>
                    </div>
                    <div class="table-responsive">
                        <table id="recent-purchases-listing" class="table">
                            <thead>
                                <tr>
                                    <th>분류</th>
                                    <th>제목</th>
                                    <th>요일</th>
                                    <th>시간/횟수</th>
                                    <th>가격(월)</th>
                                    <th>작성날짜</th>
                                    <th>모집기간</th>
                                    <th>담당자</th>
                                    <th>관리</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>필라테스</td>
                                    <td>필라테스 기초반입니다</td>
                                    <td>월,수,금</td>
                                    <td>9:00 ~ 10:00</td>
                                    <td>30,000</td>
                                    <td>2019/01/31</td>
                                    <td>모집마감</td>
                                    <td><a href="#" onclick="advertisemodifyfrm()">박한솔</a></td>
                                    <td>
                                        <button class="btn btn-secondary" type="button">수정</button>
                                        <button class="btn btn-secondary" type="button">삭제</button>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td>요가</td>
                                    <td>요가 상급반입니다</td>
                                    <td>화,목</td>
                                    <td>19:00 ~ 20:00</td>
                                    <td>20,000</td>
                                    <td>2019/05/25</td>
                                    <td>2019/06/05</td>
                                    <td><a href="#">이수원</a></td>
                                    <td>
                                        <button class="btn btn-secondary" type="button">수정</button>
                                        <button class="btn btn-secondary" type="button">삭제</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>PT</td>
                                    <td>올여름엔 바다로!!</td>
                                    <td>협의</td>
                                    <td>협의</td>
                                    <td>상세보기</td>
                                    <td>협의</td>
                                    <td>상시모집</td>
                                    <td><a href="#">이근상</a></td>
                                    <td>
                                        <button class="btn btn-secondary" type="button">수정</button>
                                        <button class="btn btn-secondary" type="button">삭제</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>요가</td>
                                    <td>초급요가</td>
                                    <td>토,일</td>
                                    <td>13:00~15:00</td>
                                    <td>40,000</td>
                                    <td>2019/03/28</td>
                                    <td>모집마감</td>
                                    <td><a href="#">이수원</a></td>
                                    <td>
                                        <button class="btn btn-secondary" type="button">수정</button>
                                        <button class="btn btn-secondary" type="button">삭제</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>필라테스</td>
                                    <td>필라테스 고수만!!</td>
                                    <td>화,목</td>
                                    <td>21:00 ~ 22:00</td>
                                    <td>30,000</td>
                                    <td>2019/06/03</td>
                                    <td>2019/06/10</td>
                                    <td><a href="#">고정근</a></td>
                                    <td>
                                        <button class="btn btn-secondary" type="button">수정</button>
                                        <button class="btn btn-secondary" type="button">삭제</button>
                                    </td>
                                </tr>
                                
                            </tbody>
                        </table> -->
                        
                             <button class="btn btn-secondary" onclick="advertisewritefrm()" type="button">신규 등록</button>  
                        
                    </div>
                </div>
            </div>
        </div>



    </div>
    

    <!-- plugins:js -->
    <script src="vendors/base/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page-->
    <script src="vendors/chart.js/Chart.min.js"></script>
    <script src="vendors/datatables.net/jquery.dataTables.js"></script>
    <script src="vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
    <!-- End plugin js for this page-->
    <!-- inject:js -->
    <script src="js/off-canvas.js"></script>
    <script src="js/hoverable-collapse.js"></script>
    <script src="js/template.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page-->
    <script src="js/dashboard.js"></script>
    <script src="js/data-table.js"></script>
    <script src="js/jquery.dataTables.js"></script>
    <script src="js/dataTables.bootstrap4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <!-- End custom js for this page-->
</body>

</html>
