<%@page pageEncoding="UTF-8" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="/public/bootstrap-3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="/public/css/index.css">
</head>
<body>
<div class="container-fluid" id="signIn">
  <%--顶部LOGON--%>
  <div class="row" id="top">
    <div class="col-md-3 h1 logo">15249 杨凡</div>
    <div class="col-md-9"></div>
  </div>

  <%--中部登录表单--%>
  <div class="row">
    <div class="col-sm-3"></div>
    <div class="col-sm-6" id="signInPanel">
      <div class="row">
        <div class="col-md-12 h3">电影租赁管理系统</div>
      </div>
      <div class="row">
        <form class="form-horizontal" id="signInForm">
          <div class="form-group has-feedback">
            <label for="signInName" class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10 input-group ">
              <input class="form-control" id="signInName" name="firstName" placeholder="Please enter your first name:">
              <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
          </div>
          <div class="form-group has-feedback">
            <label for="signInPassword" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10 input-group">
              <input type="password" class="form-control" id="signInPassword" placeholder="Useless input.">
              <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="button" class="btn btn-primary">登录</button>
            </div>
          </div>
        </form>
      </div>
    </div>
    <div class="col-sm-3"></div>
  </div>
</div>
<script src="../../public/js/jquery.js"></script>
<script src="../../public/js/index.js"></script>
<script>
    $(() => {
        movie.session.init()
    })
</script>
</body>
</html>
