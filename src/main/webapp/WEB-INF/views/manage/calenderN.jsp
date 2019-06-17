<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />

<style>
/* 달력 패키지 */
.xans-attend-calendarhead { height:70px; }
.xans-attend-calendarhead .inner { position:relative; width:200px; margin:0 auto; text-align:center; }
.xans-attend-calendarhead p.prev { position:absolute; top:50%; left:0; margin-top:-11px; }
.xans-attend-calendarhead p.next { position:absolute; top:50%; right:0; margin-top:-11px; }
.xans-attend-calendarhead p.date { display:inline; color:#d9bca0; font-size:30px; letter-spacing:-1px; font-family:Lucida Sans, sans-serif;  }
.xans-attend-calendar th { border-top:1px solid #d9bca0; font-size:11px; color:#d9bca0; background:url("http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/bg_thead.gif") right 0 no-repeat; }
.xans-attend-calendar th span { display:block; padding:8px 0; }
.xans-attend-calendar th.sun span { color:#e5541e; background:url("http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/bg_thead.gif") 0 0 no-repeat; }
.xans-attend-calendar th.sat span { color:#7cbed7; }
.xans-attend-calendar td { border:1px solid #fff; background:#f7f4ec; }
.xans-attend-calendar td p { position:relative; height:90px; padding:8px 6px; border-top:1px solid #e2ddd0; border-left:1px solid #e2ddd0; color:#8f8f8f; }
.xans-attend-calendar td p img { padding:15px; }
.xans-attend-calendar #attStart { position:absolute; left:-13px; top:-40px; padding:0; }
.xans-attend-calendar #attEnd { position:absolute; left:-13px; top:-40px; padding:0; }
.xans-attend-button { text-align:right; margin:20px 0 0; }
/* 스탬프형 > 보안문자 레이어 */
.xans-attend-button .attendSecurityLayer { display:none; position:absolute; z-index:100; width:320px; color:#757575; }
.xans-attend-button .attendSecurityLayer h3 { height:35px; padding:0 35px 0 19px; color:#fff; font-size:14px; line-height:35px; background-color:#495164; }
.xans-attend-button .attendSecurityLayer .content { padding:20px; border-left:1px solid #757575; border-right:1px solid #757575; background-color:#fff; }
.xans-attend-button .attendSecurityLayer .close { position:absolute; right:14px; top:12px; }
.xans-attend-button .attendSecurityLayer .close img { cursor:pointer; }
.xans-attend-button .attendSecurityLayer .btnArea { padding:10px 0; border:1px solid #757575; border-top:1px solid #d7d5d5; text-align:center; background-color:#fbfafa; }
.xans-attend-button .attendSecurityLayer .form { padding:5px 10px; background-color:#f5f5f5; border-top:1px solid #d8d8d8; }
.xans-attend-button .attendSecurityLayer .form img,
.xans-attend-button .attendSecurityLayer .form .keyword { vertical-align:middle; }
.xans-attend-button .attendSecurityLayer .form .keyword { width:130px; height:20px; margin:0 2px; line-height:20px; border-style:solid; border-width:1px; border-color:#c5c5c5 #e9e9e9 #e9e9e9 #c5c5c5; color:#202020; }
.xans-attend-button .attendSecurityLayer .description { margin:5px 0 0; font-size:11px; color:#747474; }


</style>
</head>
<body>


    <div module="attend_calendarhead">

        <div class="inner">
            <p class="date">{$year}.{$month}</p>
            <p class="prev"><a href="{$prev_month}"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/btn_prev.gif" alt="이전 달" /></a></p>
            <p class="next"><a href="{$next_month}"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/btn_next.gif" alt="다음 달" /></a></p>
        </div>
    </div>
    <table module="attend_calendar" border="1" summary="">

        <caption>출석이벤트 달력</caption>
        <colgroup>
            <col style="width:15%;" />
            <col style="width:14%;" />
            <col style="width:14%;" />
            <col style="width:14%;" />
            <col style="width:14%;" />
            <col style="width:14%;" />
            <col style="width:15%;" />
        </colgroup>
        <thead>
            <tr>
                <th scope="col" class="sun"><span>SUN</span></th>
                <th scope="col"><span>MON</span></th>
                <th scope="col"><span>TUE</span></th>
                <th scope="col"><span>WED</span></th>
                <th scope="col"><span>THU</span></th>
                <th scope="col"><span>FRI</span></th>
                <th scope="col" class="sat"><span>SAT</span></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><p>{$sun}</p></td>
                <td><p>{$mon}</p></td>
                <td><p>{$the}</p></td>
                <td><p>{$wed}</p></td>
                <td><p>{$thu}</p></td>
                <td><p>{$fri}</p></td>
                <td><p>{$sat}</p></td>
            </tr>
            <tr>
                <td><p>{$sun}</p></td>
                <td><p>{$mon}</p></td>
                <td><p>{$the}</p></td>
                <td><p>{$wed}</p></td>
                <td><p>{$thu}</p></td>
                <td><p>{$fri}</p></td>
                <td><p>{$sat}</p></td>
            </tr>
        </tbody>
    </table>
    <div module="attend_button">
        <!--
            $login_page = /member/login.html
        -->
        <a href="#none" onclick="{$action_attend}"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/btn_attend.gif" alt="출석체크" /></a>
        <div class="{$attendSecurityLayer}" style="display:none; left:300px; top:700px; text-align:left;">
            <h3>스팸 출석체크 방지 문자 입력</h3>
            <div class="content">
                <fieldset>
                    <legend>보안문자 입력</legend>
                    <p class="form">
                        <img src="{$secure_img}" alt="보안문자" />
                        {$form.secure_text}
                    </p>
                    <p class="description"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/board/ico_warning.gif" alt="" /> 왼쪽의 문자를 공백없이 입력하세요.(대소문자구분)</p>
                </fieldset>
            </div>
            <div class="btnArea">
                <a href="#none"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/btn_security_ok.gif" alt="확인" onclick="{$action_capcha_do}"/></a>
                <a href="#none"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/calendar/btn_security_cancel.gif" alt="취소" onclick="{$action_capcha_undo}" /></a>
            </div>
            <div class="close"><a href="#none" onclick="$('.attendSecurityLayer').hide();"><img src="http://img.echosting.cafe24.com/skin/base_ko_KR/common/btn_close.png" alt="닫기" /></a></div>
        </div>
    </div>
</div>
    </body>
</html>
