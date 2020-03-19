package Servlet;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import Bean.Info;
import Dao.DeleteService;
import Dao.Get;
import Dao.Select;
import control.Paqu;
import utils.DBUtil;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("request--->"+request.getRequestURL()+"===="+request.getParameterMap().toString());
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        String date1 = request.getParameter("username"); // 获取客户端传过来的参数
        String date2 = request.getParameter("password");
//        method = "country";
//        date1="2020-03-19";
//        date2="2020-03-20";
        Get get=new Get();
        List<Info> list=null;
        if(method.equals("province")) {//查询中国省份疫情数据
            list = get.listAll(date1,date2); 
        }else
            if(method.equals("country")) {//查询海外疫情数据
                list = get.listAll2(date1, date2);
            }             
        request.setAttribute("list",list);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        try {
            response.getWriter().println(json);
            // 将json数据传给客户端
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().close(); // 关闭这个流，不然会发生错误的
        }
    }
    

}
