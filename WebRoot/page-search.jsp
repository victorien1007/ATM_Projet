
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
    <!-- DataTables-->
    <link rel="stylesheet" href="assets/plugins/dataTables/css/dataTables.css">
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
                    <div class="col-md-3">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">查询余额</h3>
                            </div>
                            <div class="panel-body">
                                <div class="dashboard-tile detail tile-turquoise">
                                    <div class="content">
                                        <p>当前账户余额为:</p>
                                        <h1 id="balance" class="text-left timer" data-from="0" data-to="0" data-speed="2000"></h1>
                                    </div>
                                    <div class="icon"><i class="fa fa-users"></i>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>


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
    <script src="assets/plugins/countTo/jquery.countTo.js"></script>
    <script src="assets/plugins/weather/js/skycons.js"></script>
    <!-- FlotCharts  -->
    <script src="assets/plugins/flot/js/jquery.flot.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.resize.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.canvas.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.image.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.categories.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.crosshair.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.errorbars.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.fillbetween.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.navigate.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.pie.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.selection.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.stack.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.symbol.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.threshold.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.colorhelpers.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.time.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.example.js"></script>
    <!-- Morris  -->
    <script src="assets/plugins/morris/js/morris.min.js"></script>
    <script src="assets/plugins/morris/js/raphael.2.1.0.min.js"></script>
    <!-- Vector Map  -->
    <script src="assets/plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="assets/plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"></script>
    <!-- ToDo List  -->
    <script src="assets/plugins/todo/js/todos.js"></script>
    <script>
    $(document).ready(function() {
        //$("#balance").attr("data-to","2000");
        getbalanceData();
        app.timer();
    });
    function getbalanceData(){
    
   		var id = "${userId}";
        var requestURL = "atm/balance/"+id;
        $.ajax({
            type : "GET",
            url : requestURL,
            async : false,
            cache : false,
            success : function(result) {
                if(result.code == 1)
                {
                    //通过
                    $("#balance").attr("data-to",result.balance);
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
