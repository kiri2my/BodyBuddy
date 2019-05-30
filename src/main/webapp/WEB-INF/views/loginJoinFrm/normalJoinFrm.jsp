<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Majestic Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="../../vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="../../vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="../../css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="../../images/favicon.png" />
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <a class="navbar-brand brand-logo" href="main.jsp" style="color: #71c016;">BODY BUDDY</a>
              </div>
              <h5>아이콘이 있으면 필수내용입니다</h5>
              <form action="normalmemberjoin" class="pt-3">
                    
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-email-outline text-primary"></i>
                      </span>
                    </div>
                    <input type="email" name="m_id" class="form-control form-control-lg border-left-0" placeholder="이메일을 입력해주세요"><button type="button" class="btn btn-outline-secondary btn-md">중복 확인</button>
                  </div>
                </div>
                
                <div class="form-group">
                
                <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-lock-outline text-primary"></i>
                      </span>
                    </div>
                    <input type="password" name="m_pw" class="form-control form-control-lg border-left-0" id="exampleInputPassword" placeholder="패스워드를 입력해주세요">                        
                </div>
                </div>
                 
                <div class="form-group">
                    <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-account-outline text-primary"></i>
                      </span>
                    </div>
                    <input type="text"  name="m_name" class="form-control form-control-lg border-left-0" placeholder="이름을 입력해주세요">
                  </div>
                </div>
                 
                 
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-cellphone text-primary"></i>
                      </span>
                    </div>
                    <input type="tel"  name="m_phone" class="form-control form-control-lg border-left-0" placeholder="전화번호를 입력해주세요">
                  </div>
                </div> 
                
                <div class="form-group">
                  <div class="input-group">
                    <input type="text"  name="m_birth" class="form-control form-control-lg border-left-0" placeholder="생년월일 8자리를 입력해주세요 ex)19900601">
                  </div>
                </div> 
                
                <div class="form-group">
                  <div class="input-group">
                    <input type="text"  name="m_addr" class="form-control form-control-lg border-left-0" placeholder="시/도-군/구">
                    <button type="button" class="btn btn-outline-secondary btn-md">주소 검색</button>
                    <input type="text"  name="m_addr" class="form-control form-control-lg border-left-0" placeholder="상세주소 입력">
                  </div>
                </div> 
                
                 
                
                
                <div class="mb-4">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      모든 회원 약관에 동의합니다.
                    <i class="input-helper"></i></label>
                  </div>
                </div>
                <div class="mt-3">
                  <a class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" href="../../main.jsp">회원가입</a>
                </div>
                <div class="text-center mt-4 font-weight-light">
                   이미 회원가입 하셨나요?<a href="login.html" class="text-primary"> 로그인</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="../../vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="../../js/off-canvas.js"></script>
  <script src="../../js/hoverable-collapse.js"></script>
  <script src="../../js/template.js"></script>
  <!-- endinject -->
</body>

</html>
