<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>ATM</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/favicon.ico" type="image/x-icon">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Fonts from Font Awsome -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!-- CSS Animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- Custom styles for this theme -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- iCheck-->
    <link rel="stylesheet" href="assets/plugins/icheck/css/_all.css">
    <!-- Fonts -->
    <!-- <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,900,300italic,400italic,600italic,700italic,900italic' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'> -->
    <!-- Feature detection -->
    <script src="assets/js/modernizr-2.6.2.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <section id="container">
        <header id="header">
            <!--logo start-->
            <div class="brand">
                <a href="#" class="logo">
                    <span>ATM</span></a>
            </div>
            <!--logo end-->
            <div class="toggle-navigation toggle-left">
                <button type="button" class="btn btn-default" id="toggle-left" data-toggle="tooltip" data-placement="right" title="Toggle Navigation">
                    <i class="fa fa-bars"></i>
                </button>
            </div>
            <div class="user-nav">
                <ul>
                    <li class="dropdown settings">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                      ${userId} </a>
                    </li>
                    <li>
                        <a href="pages-login.html"><i class="fa fa-power-off"></i> Logout</a>
                    </li>
                </ul>
            </div>
        </header>
        <!--sidebar start-->
        <aside class="sidebar">
            <div id="leftside-navigation" class="nano">
                <ul class="nano-content">
                    <li>
                        <a href="page-save.jsp"><i class="fa fa fa-tasks"></i>存款</a>
                    </li>
                    <li>
                        <a href="page-draw-money.jsp"><i class="fa fa fa-tasks"></i>取款</a>
                    </li>
                    <li>
                        <a href="page-transfer.jsp"><i class="fa fa fa-tasks"></i>转账</a>
                    </li>
                    <li>
                        <a href="tables-data.jsp"><i class="fa fa-table"></i>消费记录</a>
                    </li>
                    <li>
                        <a href="page-search.jsp"><i class="fa fa-table"></i>查询余额</a>
                    </li>
                </ul>
            </div>

        </aside>
        <!--sidebar end-->
        <!--main content start-->
        <section class="main-content-wrapper">
            <section id="main-content">

                <div class="row">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Transfer Account</h3>
                            </div>
                            <div class="panel-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="opposite_account" class="col-sm-2 control-label">对方账户</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="opposite_account" placeholder="0000 0000 0000 010">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="opposite_name" class="col-sm-2 control-label">账户姓名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="opposite_name" placeholder="please input full name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="transfer_number" class="col-sm-2 control-label">转账金额</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="transfer_number" placeholder="number">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <button id="transfer_btn" type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>


            </section>
        </section>
        <!--main content end-->
        <!--sidebar right start-->

        <!--sidebar right end-->
    </section>
    <!--Global JS-->
    <script src="assets/js/jquery-1.10.2.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/waypoints/waypoints.min.js"></script>
    <script src="assets/js/application.js"></script>
    <!--Page Level JS-->
    <script src="assets/plugins/icheck/js/icheck.min.js"></script>
    <script>

  	var id = "${userId}";
  
    $(document).ready(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-grey',
            radioClass: 'iradio_flat-grey'
        });
      
        $("#transfer_btn").click(function() {
        transfer();
    });

    });

    

    function transfer(){
    	
        var account = $("#opposite_account").val();
        var name = $("#opposite_name").val();
        var num = $("#transfer_number").val();
        var requestURL = "atm/transfer/"+id+"/"+account+"/"+name+"/"+num;
            $.ajax({
                type : "GET",
                url : requestURL,
                async : false,
                cache : false,
                success : function(result) {
                    if(result.code == 1){
                        alert("转账成功");
                    }
                    else
                    {
                     alert("操作失败");
                    }
                }
            });
    }
    </script>

</body>

</html>
