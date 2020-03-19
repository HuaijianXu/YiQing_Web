package Servlet;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import Dao.DeleteService;
import control.Paqu;
import utils.DBUtil;

@WebServlet("/PaquServlet")
public class PaquServlet extends HttpServlet {


    
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
        Date currentTime=new Date();
        SimpleDateFormat formatter_date = new SimpleDateFormat("yyyy-MM-dd");
        String date=formatter_date.format(currentTime);
        DeleteService ds=new DeleteService();
        ds.delete("myinfo", date);
        Paqu pq=new Paqu();
        pq.refesh();
    }
    

}
