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
  <link rel="stylesheet" href="vendors/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="vendors/base/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="css/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="images/favicon.png" />
  <style>
           .img_wrap {
            width: 300px;
            margin-top: 50px;
        }
        .img_wrap img {
            max-width: 100%;
        }
    </style>
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <a class="navbar-brand brand-logo" href="main.jsp" style="color: #71c016;">일반회원 프로필</a>
              </div>
<br>
                 <div>
            프로필사진나오는공간
    </div>
 
    <div>
        <div class="img_wrap">
            <img id="img" />
        </div>
    </div>
              <form action="normalmemberjoin" class="pt-3">
                    
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-email-outline text-primary"></i>
                      </span>
                    </div>
                    <input type="email" name="m_id" class="form-control form-control-lg border-left-0" value="gozldsos@naver.com" disabled>
                  </div>
                </div>
                
                <div class="form-group">
                

                </div>
                 
                <div class="form-group">
                    <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-account-outline text-primary"></i>
                      </span>
                    </div>
                    <input type="text"  name="m_name" class="form-control form-control-lg border-left-0"value="윤상기"  disabled>
                  </div>
                </div>
                 
                 
                <div class="form-group">
                  <div class="input-group">
                    <div class="input-group-prepend bg-transparent">
                      <span class="input-group-text bg-transparent border-right-0">
                        <i class="mdi mdi-cellphone text-primary"></i>
                      </span>
                    </div>
                    <input type="tel"  name="m_phone" class="form-control form-control-lg border-left-0" value="전화번호: 010-4189-3175">
                  </div>
                </div> 
                
                <div class="form-group">
                  <div class="input-group">
                    <input type="text"  name="m_birth" class="form-control form-control-lg border-left-0" value="생년월일 :1992년 11월 7일"  disabled>
                  </div>
                </div> 
                

                 
                
                
                <div class="mb-4">
                  <div class="form-check">
                
        
                   
                    <i class="input-helper"></i>
                  </div>
                </div>
                <div class="mt-3">
                  <a class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" href="main.jsp">닫기</a>
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
  <script src="vendors/base/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- inject:js -->
  <script src="js/off-canvas.js"></script>
  <script src="js/hoverable-collapse.js"></script>
  <script src="js/template.js"></script>
  <!-- endinject -->
</body>

</html>