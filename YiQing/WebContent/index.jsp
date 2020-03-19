<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"  rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<style type="text/css">
    .skyblue{
        background:skyblue;
    }
    .pink{
        background:pink;
    }
    *{
        margin:0px;
        padding:0px;
    }
    a{
        font-size:15px;
    }
    
</style>
</head>
<body>
    <div class="container">
        <form action="YqServlet?method=getAllProvince" method="post">
            <div class="row" style="padding-top: 20px">
                <div class="col-xs-4">
                    <h4>起始时间：</h4>
                    <input type="text" class="form-control" name="date1">
                </div>
                <div class="col-xs-4">
                    <h4>终止时间：</h4>
                    <input type="text" class="form-control" name="date2">
                </div>
                <div class="col-xs-2">
                    <input type="submit" class="btn btn-default" value="查询">
                </div>
            </div>
        </form>

    </div>
</body>
</html>