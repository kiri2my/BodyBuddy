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
                    <div class="row" style="height: 700px">
                        <!--지도<div class="col-md-4 stretch-card" >
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-title">지도</p>
                                    <div id="map">
                                        
                                    </div>
                                </div>
                            </div>
                        </div>-->
                           
                           
                        <div class="col-md-12 stretch-card"> <!--md-12면 화면에 꽉 차고 md-7리스트, md-5지도끝-->
                            <div class="card">
                                <div class="card-body">
                                    <p class="card-title">총 _ 건의 결과가 있습니다.</p>
                                    <div class="table-responsive">
                                        <table id="recent-purchases-listing" class="table">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Status report</th>
                                                    <th>Office</th>
                                                    <th>Price</th>
                                                    <th>Date</th>
                                                    <th>Gross amount</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Jeremy Ortega</td>
                                                    <td>Levelled up</td>
                                                    <td>Catalinaborough</td>
                                                    <td>$790</td>
                                                    <td>06 Jan 2018</td>
                                                    <td>$2274253</td>
                                                </tr>
                                                <tr>
                                                    <td>Alvin Fisher</td>
                                                    <td>Ui design completed</td>
                                                    <td>East Mayra</td>
                                                    <td>$23230</td>
                                                    <td>18 Jul 2018</td>
                                                    <td>$83127</td>
                                                </tr>
                                                <tr>
                                                    <td>Emily Cunningham</td>
                                                    <td>support</td>
                                                    <td>Makennaton</td>
                                                    <td>$939</td>
                                                    <td>16 Jul 2018</td>
                                                    <td>$29177</td>
                                                </tr>
                                                <tr>
                                                    <td>Minnie Farmer</td>
                                                    <td>support</td>
                                                    <td>Agustinaborough</td>
                                                    <td>$30</td>
                                                    <td>30 Apr 2018</td>
                                                    <td>$44617</td>
                                                </tr>
                                                <tr>
                                                    <td>Betty Hunt</td>
                                                    <td>Ui design not completed</td>
                                                    <td>Lake Sandrafort</td>
                                                    <td>$571</td>
                                                    <td>25 Jun 2018</td>
                                                    <td>$78952</td>
                                                </tr>
                                                <tr>
                                                    <td>Myrtie Lambert</td>
                                                    <td>Ui design completed</td>
                                                    <td>Cassinbury</td>
                                                    <td>$36</td>
                                                    <td>05 Nov 2018</td>
                                                    <td>$36422</td>
                                                </tr>
                                                <tr>
                                                    <td>Jacob Kennedy</td>
                                                    <td>New project</td>
                                                    <td>Cletaborough</td>
                                                    <td>$314</td>
                                                    <td>12 Jul 2018</td>
                                                    <td>$34167</td>
                                                </tr>
                                                <tr>
                                                    <td>Ernest Wade</td>
                                                    <td>Levelled up</td>
                                                    <td>West Fidelmouth</td>
                                                    <td>$484</td>
                                                    <td>08 Sep 2018</td>
                                                    <td>$50862</td>
                                                </tr>
                                                <tr>
                                                    <td>Ernest Wade</td>
                                                    <td>Levelled up</td>
                                                    <td>West Fidelmouth</td>
                                                    <td>$484</td>
                                                    <td>08 Sep 2018</td>
                                                    <td>$50862</td>
                                                </tr>
                                                <tr>
                                                    <td>Ernest Wade</td>
                                                    <td>Levelled up</td>
                                                    <td>West Fidelmouth</td>
                                                    <td>$484</td>
                                                    <td>08 Sep 2018</td>
                                                    <td>$50862</td>
                                                </tr>                                                
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                        
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

</html>
	