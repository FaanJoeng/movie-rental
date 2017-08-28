<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>客户管理</title>
  <link rel="stylesheet" type="text/css" href="/public/bootstrap-3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="/public/css/index.css">
</head>
<body>
<div class="container-fluid">
  <div class="row" id="indexHeader">
    <div class="col-md-3 logo">15249 杨凡</div>
    <div class="col-md-9">
      <div class="row">
        <div class="col-md-offset-10">
          <span class="glyphicon glyphicon-user"></span>
          <span id="user"><%=request.getSession().getAttribute("user")%></span>
          <a class="btn" id="signOutBtn">注销</a>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-2" id="panel">
      <div class="row">
        <a class="btn">Customer 管理</a><span class="glyphicon glyphicon-chevron-right"></span>
      </div>
      <div class="row">
        <a class="btn">Film 设置</a><span class="glyphicon glyphicon-chevron-right"></span>
      </div>
    </div>
    <div class="col-md-10">
      <div class="row">客户管理</div>
      <div class="row">客户列表
        <button type="button" class="btn btn-primary btn-sm" id="addCustomerBtn">新增</button>
        <button class="btn btn-danger btn-sm" id="deleteAllBtn">删除</button>
      </div>
      <div class="row">
        <table class="table">
          <thead>
          <th style="width: 30px">
            <input type="checkbox" id="check_all">
          </th>
          <th>ID</th>
          <th>FirstName</th>
          <th>LastName</th>
          <th>Email</th>
          <th>Address</th>
          <th>LastUpdate</th>
          <th>Operation</th>
          </thead>
          <tbody id="customer_table">
          </tbody>
        </table>
      </div>
      <div class="row">
        <div class="col-md-4" id="pagination_info">
        </div>
        <div class="col-md-8">
          <nav aria-label="Page navigation">
            <ul class="pagination" id="pagination">
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</div>

<%--新增客户模态框--%>
<div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
          aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增客户</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="addCustomerForm">
          <div class="form-group">
            <label for="addCustomerFirstNameInput" class="col-sm-2 control-label">FirstName<span class="required">*</span></label>
            <div class="col-sm-10">
              <input class="form-control" name="firstName" id="addCustomerFirstNameInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label for="addCustomerLastNameInput" class="col-sm-2 control-label">LastName<span class="required">*</span></label>
            <div class="col-sm-10">
              <input class="form-control" name="lastName" id="addCustomerLastNameInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label for="addCustomerEmailInput" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
              <input type="email" class="form-control" name="email" id="addCustomerEmailInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">Address<span class="required">*</span></label>
            <div class="col-sm-10">
              <select class="form-control" name="addId">

              </select>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="addCustomerSaveBtn">保存</button>
      </div>
    </div>
  </div>
</div>

<%--修改客户模态框--%>
<div class="modal fade" id="updateCustomerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
          aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改客户</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="updateCustomerForm">
          <div class="form-group">
            <label for="updateCustomerFirstNameInput" class="col-sm-2 control-label">FirstName</label>
            <div class="col-sm-10">
              <input class="form-control" name="firstName" id="updateCustomerFirstNameInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label for="updateCustomerLastNameInput" class="col-sm-2 control-label">LastName</label>
            <div class="col-sm-10">
              <input class="form-control" name="lastName" id="updateCustomerLastNameInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label for="updateCustomerEmailInput" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
              <input class="form-control" name="email" id="updateCustomerEmailInput">
              <span class="help-block"></span>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">Address</label>
            <div class="col-sm-10">
              <select class="form-control" name="addId">

              </select>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="updateCustomerSaveBtn">保存</button>
      </div>
    </div>
  </div>
</div>

<%--用户列表模板--%>
<script id="customer_table_template" type="text/html">
  <? for(let i = 0; i < list.length; i++){ ?>
  <? var item = list[i] ?>
  <tr>
    <td>
      <input type="checkbox" class="check_item">
    </td>
    <td><?= item.customerId?></td>
    <td><?= item.firstName?></td>
    <td><?= item.lastName?></td>
    <td>
      <? if(item.email == null){?><?}else{?>
      <?= item.email ?>
      <?}?>
    </td>
    <td><?= item.address.address?></td>
    <td><?= item.lastUpdate ?></td>
    <td>
      <button class="btn customerEditBtn" data-customer-id="<?= item.customerId ?>">
        编辑
      </button>
      |
      <button class="btn customerDeleteBtn" data-customer-id="<?= item.customerId ?>">
        删除
      </button>
    </td>
  </tr>
  <?}?>
</script>

<%--分页信息模板--%>
<script id="pagination_info_template" type="text/html">
  当前<?=pageNum?>页，共<?=pages?>页，共<?=total?>条记录
</script>

<%--分页链接模板--%>
<script id="pagination_template" type="text/html">
  <li><a href="#" id="first_page">首页</a></li>
  <? if(hasPreviousPage){ ?>
  <li>
    <a href="#" aria-label="Previous" id="pre_page">
      <span aria-hidden="true">&laquo;</span>
    </a>
  </li>
  <?}?>
  <? for(var i = 0; i < navigatepageNums.length; i++) {?>
  <? var item = navigatepageNums[i] ?>
  <? if(pageNum == item){?>
  <li class="active"><a href="#" class="page target_page"><?= item?></a></li>
  <?} else { ?>
  <li style='width:40px'><a href="#" class="target_page"><?= item ?></a></li>
  <? }}?>
  <? if(hasNextPage){ ?>
  <li>
    <a href="#" aria-label="Next" id="next_page">
      <span aria-hidden="true">&raquo;</span>
    </a>
  </li>
  <?}?>
  <li><a href="#" id="last_page">末页</a></li>
</script>
<script src="public/js/jquery.js"></script>
<script src="public/bootstrap-3.3.7/js/bootstrap.js"></script>
<script src="public/js/template-web.js"></script>
<script src="public/js/index.js"></script>
<script>
    $(() => {
        movie.customer.init()
    })
</script>
</body>
</html>
