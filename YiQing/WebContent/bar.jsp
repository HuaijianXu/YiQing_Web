<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/css/bootstrap.min.css"  rel="stylesheet">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
</head>
<script type="text/javascript">
var dt;
function getAllConfirmed(){
    
    var date1 = "${date1}";
    var date2 = "${date2}";
$.ajax({
    url:"YqServlet?method=getAllConfirmed",
    async:false,
    type:"POST",
    data:{"date1":date1,
          "date2":date2
         },
    success:function(data){
        dt = data;
        //alert(dt);
    },
    error:function(){
        alert("请求失败");
    },
    dataType:"json"
});
    
    var myChart = echarts.init(document.getElementById('yiqingchart'));
    var xd = new Array(0)//长度为33
    var yd = new Array(0)//长度为33
    for(var i=0;i<32;i++){
        xd.push(dt[i].province);
        yd.push(dt[i].confirmed_num);
    }
        // 指定图表的配置项和数据
       var option = {
        title: {
            text: '全国各省的确诊人数'
        },
        tooltip: {
            show: true,
            trigger: 'axis'
            
        },
        legend: {
            data: ['确诊人数']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            axisLabel:{
                                        //横坐标上的文字斜着显示 文字颜色 begin 
                                             interval:0,
                                             rotate:45,
                                             margin:60,
                                             textStyle:{color:"#ec6869" }
                                        //横坐标上的文字换行显示 文字颜色end
                                             },
            data: xd
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '确诊人数',
                type: 'bar',
                stack: '总量',
                data: yd,
                barWidth:20,
                barGap:'10%'
            }
        ]
    };

       

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
}
</script>
<body>
    <button class="btn btn-default" onclick="getAllConfirmed()" style="padding-top:20px;font-size:20px">柱状图</button>
    <div id="yiqingchart" style="width:900px; height: 600px;">
        
    </div>
    <table class="table table-striped" style="font-size:20px">
        <tr>
            <td>编号</td>
			<td>时间</td>
            <td>省份</td>
            <td>新增人数</td>
            <td>确诊人数</td>
            <td>治愈人数</td>
            <td>死亡人数</td>
        </tr>
        <c:forEach items="${list}" var="info">
        <tr>
            <td>${info.id}</td>
            <td>${info.date}</td>
            <td>${info.province}</td>
            <td>${info.newconfirmed_num}</td>
            <td>${info.confirmed_num}</td>
            <td>${info.cured_num}</td>
            <td>${info.dead_num}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>