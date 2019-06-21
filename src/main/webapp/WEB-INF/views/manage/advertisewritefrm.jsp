<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
 content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>BODY BUDDY</title>
<!-- plugins:css -->
<link rel="stylesheet"
 href="vendors/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<link rel="stylesheet"
 href="vendors/datatables.net-bs4/dataTables.bootstrap4.css">
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet" href="css/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png" />
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="js/bootstrap.js">
<link
 href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/css/bootstrap-datetimepicker.min.css"
 rel="stylesheet" />

<link rel="stylesheet"
 href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
 type="text/css" />
<link rel="stylesheet" href="/path/to/jquery.timeselector.css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
 integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ"
 crossorigin="anonymous">
 
</script>
<script src="/path/to/jquery.timeselector.js"></script>

<script
 src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>



<style>
body {
 text-align: center;
 
}
</style>
</head>


<body>
 <form id="frm" action="adinsert">
  <div class="container">
   <table id="recent-purchases-listing" class="table">
    <tbody>
     <tr>
      <th style="width: 200px">분류</th>

      <td style="vertical-align: middle">
      <select id="ex-select" name="ad_category" onchange="selectExercise()" class="form-control" style="width: 150px;">
        <option class="nothing" value="nothing" >선택해주세요</option> 
        <option class="fitness" value="fitness">피트니스</option>
        <option class="yoga" value="yoga">요가</option>
        <option class="pt" value="pt">개인PT</option>
        <option class="pilates" value="pilates">필라테스</option>
      </select>
      </td>
     </tr>
     <tr>
      <th>제목</th>
      <td style="vertical-align: middle"><input type="text" 
       name="ad_title" style="height: 50px;"></td>
     </tr>
     <tr>
      <th>내용</th>
      <td style="vertical-align: middle"><input type="text" class="reset"
       name="ad_content" style="height: 300px; width: 500px"></td>
     </tr>
     
     
     
     <tr id="time" >
      <th>옵션</th>
      <td style="vertcal ical-align: middle">
       <div class="radio">
       
       <!-- 1번옵션 -->
        <div id="pre_set1" class="pre_set"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table>
           		<button type="button" onclick="remove_item1(this)">초기화</button>
           		<button type="button" onclick="add_item1()">추가</button>
           
           <hr>
        </div>
        
        <!-- 2번옵션 -->
        
        <div id="pre_set2" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item2(this)">삭제</button>
           		<button type="button" onclick="add_item2()">추가</button>
           <hr>
        </div>
        
        <!-- 3번옵션 -->
        
        <div id="pre_set3" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item3(this)">삭제</button>
           		<button type="button" onclick="add_item3()">추가</button>
           <hr>
        </div>
        
        <!-- 4번옵션 -->
        
        <div id="pre_set4" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item4(this)">삭제</button>
           		<button type="button" onclick="add_item4()">추가</button>
           <hr>
        </div>
        
        <!-- 5번옵션 -->
        
        <div id="pre_set5" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item5(this)">삭제</button>
           		<button type="button" onclick="add_item5()">추가</button>
           
           <hr>
        </div>
        
        
        <!-- 6번옵션 -->
        
        <div id="pre_set6" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item6(this)">삭제</button>
           		<button type="button" onclick="add_item6()">추가</button>
           
           <hr>
        </div>
        
        <!-- 7번옵션 -->
        
        <div id="pre_set7" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item7(this)">삭제</button>
           		<button type="button" onclick="add_item7()">추가</button>
           
           <hr>
        </div>
        
        <!-- 8번옵션 -->
        
        <div id="pre_set8" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item8(this)">삭제</button>
           		<button type="button" onclick="add_item8()">추가</button>
           
           <hr>
        </div>
        
        <!-- 9번옵션 -->
        
        <div id="pre_set9" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item9(this)">삭제</button>
           		<button type="button" onclick="add_item9()">추가</button>
           
           <hr>
        </div>
        
        <!-- 10번옵션 -->
        
        <div id="pre_set10" class="pre_set" style="display:none"><!-- style="display: none" -->
         <table>
          <tr>
           <th>옵션명</th>
           <th>기간</th>
           <th>시간</th>
           <th>요일</th>
           <th>횟수</th>
           <th>인원</th>
           <th>가격</th>
           <th>담당자</th>
           
          </tr>
          <tr>
           <td><input type="text" name="op_content" value="" style="width: 100px" placeholder="옵션명" class="reset"></td>
           <td >
            <label for="from" >From</label><input type="text"  id="from" name="op_period1" class="from">
             <label for="to">to</label><input type="text" id="to" name="op_period2" class="to">
           </td>
           <td><input type="text" name="op_clock1" value="" style="width: 100px" placeholder="시작시간" class="reset"> 
           <input type="text" name="op_clock2" value="" style="width: 100px" placeholder="종료시간" class="reset"></td>
           <td><div class="checkbox" style="width: 300px; font-size: 20px" class="reset">
        <label for="foo1"> <input type="checkbox" id="foo1"
         name="day" value="월 ">월
        </label> <label for="foo2"> <input type="checkbox" id="foo2"
         name="day" value="화 ">화
        </label> <label for="foo3"> <input type="checkbox" id="foo3"
         name="day" value="수 ">수
        </label> <label for="foo4"> <input type="checkbox" id="foo4"
         name="day" value="목 ">목
        </label> <label for="foo5"> <input type="checkbox" id="foo5"
         name="day" value="금 ">금
        </label> <label for="foo6"> <input type="checkbox" id="foo6"
         name="day" value="토 ">토
        </label> <label for="foo7"> <input type="checkbox" id="foo7"
         name="day" value="일 ">일
        </label> 
        <label for="foo7"> <input type="hidden" id="foo7"
         name="day" value="@ ">
        </label>
       </div></td>
           <td><input type="text" name="op_times" value="" style="width: 50px" placeholder="횟수" class="reset"></td>
           <td><input type="text" name="op_personnel" placeholder="모집인원" class="reset">명</td>
           <td><input type="text" name="op_price" placeholder="가격(원)" class="reset"></td>
           <td>${trainerlist}</td><!-- 담당자 -->
            
           </tr>
           </table><button type="button" onclick="remove_item10(this)">삭제</button>
           		<button type="button" onclick="add_item10()">추가</button>
           
           <hr>
        </div>

        <div id="field"></div>
        <input type="hidden" value="0" name="checkNum" id="checkNum">
        

        <!-- <input name="op_content" type="text" placeholder="시작시간"
         style="width: 80px"> 
        <input name="op_content" type="text" placeholder="종료시간" style="width: 80px"> <br>
        <input name="op_content" type="text" placeholder="횟수"
         style="width: 160px"> -->
       </div>
      </td>
     </tr>

     


     <tr>
      <th>사진</th>
      <td colspan="2x">사진내용</td>
      <td><input type="file" class="btn btn-secondary" name="ap_image">업로드</td>
     </tr>
    </tbody>


   </table>
   <button id="ins" class="btn btn-secondary" type="submit">등록</button>
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
 <script
  src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment-with-locales.min.js"></script>
 <script
  src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/3.0.0/js/bootstrap-datetimepicker.min.js"></script>
</body>

<script>

 //TIMEPICKER
 var x = 1;
 //zz
 function add_item1() {$('#pre_set2').show(); }
 function remove_item1(obj) {$('#pre_set1 input').val("");}
 //zz
 function add_item2() {	$('#pre_set3').show()}
function remove_item2(obj) {
	$('#pre_set2').hide();
	$('#pre_set2 input').val("");
	 }
//zz
function add_item3() {$('#pre_set4').show()}
function remove_item3(obj) {
	$('#pre_set3').hide();
	$('#pre_set3 input').val(""); 
	}
//zz
function add_item4() {$('#pre_set5').show()}
function remove_item4(obj) {
	$('#pre_set4').hide();
	$('#pre_set4 input').val("");
	 }
//zz
function add_item5() {$('#pre_set5').show()}
function remove_item5(obj) {
	$('#pre_set5').hide();
	$('#pre_set5 input').val("");
	}
function add_item6() {$('#pre_set6').show()}
function remove_item6(obj) {
	$('#pre_set6').hide();
	$('#pre_set6 input').val("");
	}
function add_item7() {$('#pre_set7').show()}
function remove_item7(obj) {
	$('#pre_set7').hide();
	$('#pre_set7 input').val("");
	}
function add_item8() {$('#pre_set8').show()}
function remove_item8(obj) {
	$('#pre_set8').hide();
	$('#pre_set8 input').val("");
	}
function add_item9() {$('#pre_set9').show()}
function remove_item9(obj) {
	$('#pre_set9').hide();
	$('#pre_set9 input').val("");
	}
/* function add_item10() {$('#pre_set10').show()} */
function remove_item10(obj) {
	$('#pre_set10').hide();
	$('#pre_set10 input').val("");
	}
	
 
 
 function selectExercise() {
  var exSelect = document.getElementById("ex-select");

  // select element에서 선택된 option의 value가 저장된다.
  var selectValue = exSelect.options[exSelect.selectedIndex].value;

  // select element에서 선택된 option의 text가 저장된다.
  var selectText = exSelect.options[exSelect.selectedIndex].text;

  /* if (selectValue == "fitness") {
   //$("#frm")[0].reset(); 
   
   $("#closed").show();
   $("#damdang").hide();
   $("#time").hide();
   $("#day").hide();
  } else {
   //$("#frm")[0].reset();
   $("#damdang").show();
   $("#time").show();
   $("#day").show();
   $("#closed").hide();
  } */
  /* $(".pre_set").each(function() {
   
      var dateFormat = "yymmdd",
        from = $( "#from" )
          .datepicker({
            defaultDate: "+1w",
            changeMonth: true,
            numberOfMonths: 2
          })
          .on( "change", function() {
            to.datepicker( "option", "minDate", getDate( this ) );
          }),
        to = $( "#to" ).datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 2
        })
        .on( "change", function() {
          from.datepicker( "option", "maxDate", getDate( this ) );
        });
   
      function getDate( element ) {
        var date;
        try {
          date = $.datepicker.parseDate( dateFormat, element.value );
        } catch( error ) {
          date = null;
        }
   
        return date;
      }
    } */ 
    
  
  alert(selectText);
 }
 $(".pre_set").each(function() {
  
     var dateFormat = "yymmdd",
       from = $(this).find("input[id=from]")
         .datepicker({
           defaultDate: "+1w",
           changeMonth: true,
           numberOfMonths: 2
         })
         .on( "change", function() {
           to.datepicker( "option", "minDate", getDate( this ) );
         }),
       to = $(this).find("input[id=to]")
       .datepicker({
         defaultDate: "+1w",
         changeMonth: true,
         numberOfMonths: 2
       })
       .on( "change", function() {
         from.datepicker( "option", "maxDate", getDate( this ) );
       });
  
     function getDate( element ) {
       var date;
       try {
         date = $.datepicker.parseDate( dateFormat, element.value );
       } catch( error ) {
         date = null;
       }
  
       return date;
     }
   } 
);
</script>
</html>