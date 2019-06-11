<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="js/bootstrap.js">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
    <style>
        body{
            text-align: center;
        }
    </style>
</head>


<body>
	<form>
    <div class="container">
        <table id="recent-purchases-listing" class="table">
            <thead>
                <tr>
                    <th>분류</th>
                    <th>제목</th>
                    <th>요일</th>
                    <th>시간/횟수</th>
                    <th>가격(월)</th>
                    <th>모집기간</th>
                    <th>담당자</th>
                </tr>
            </thead>
            <tbody>
                <tr >
                    <td style="vertical-align: middle">
                        <select class="form-control" style="width: 100px;">
                            <option>피트니스</option>
                            <option>요가</option>
                            <option>개인PT</option>
                            <option>필라테스</option>
                        </select>
                    </td>
                    <td style="vertical-align: middle">
                        <input type="text" style="height: 50px;">
                    </td>
                    <td >
                        <div class="checkbox">
                            <label for="foo">
                                <input type="checkbox" id="foo" name="mon">월
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="tue">화
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="wed">수
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="thr">목
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="fir">금
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="sat">토
                            </label>
                            <label for="foo">
                                <input type="checkbox" id="foo" name="sun">일
                            </label>
                        </div>
                    </td>
                    <td style="vertical-align: middle">
                        <div class="radio">
                            <!--<label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 오전
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 오후
                        </label>-->
                            <input type="text" placeholder="시작시간" style="width:80px">
                            <input type="text" placeholder="종료시간" style="width:80px">
                            <input type="text" placeholder="횟수" style="width:160px">
                        </div>
                    </td>
                    <td style="vertical-align: middle"><input type="text" placeholder="월 가격" style="width:80px;" nowrap></td>
                    <td style="vertical-align: middle">
                        javascript 달력 예제 사용
                    </td>
                    <td style="vertical-align: middle">
                        <select class="form-control" style="width: 100px">
                            <option>트레이너1</option>
                            <option>트레이너2</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>사진</td>
                    <td colspan="5">사진내용</td>
                    <td><button class="btn btn-secondary">업로드</button></td>
                </tr>
            </tbody>
        </table>
        <button id="ins" class="btn btn-secondary" type="button">등록</button>
        <button id="back" class="btn btn-secondary" type="button">돌아가기</button>
        
    </div>
	</form>









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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment-with-locales.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js"></script>
</body>

</html>
