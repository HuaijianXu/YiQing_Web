package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Bean.Info;
import Dao.DeleteService;
import Dao.Get;
import Dao.Select;
import control.Paqu;


/**
 * Servlet implementation class SearchConfirmedServlet
 */
@WebServlet("/YqServlet")
public class YqServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Get get=new Get();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YqServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method.equals("getAllProvince")) {
            try {
				getAllProvince(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else if(method.equals("getAllConfirmed")) {
            getAllConfirmed(request, response);
        }
    }
	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    protected void getAllProvince(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        response.setCharacterEncoding("UTF-8");
        Select s=new Select();
        Date currentTime=new Date();
		SimpleDateFormat formatter_date = new SimpleDateFormat("yyyy-MM-dd");
		String date=formatter_date.format(currentTime);
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        Date today=formatter_date.parse(date);//将现在的date转为日期，方便比较
        Date date22=formatter_date.parse(date2);//将date2转为日期，方便比较
        if(today.before(date22)) {//如果今天日期today比查询后边的date2日期早，需要用到今天的数据
        	//不管数据库中有没有今天的数据，运行到这都需要重新爬取一遍，防止官方更新今日数据
        	boolean b=s.select(date);//查找数据库中是否存在今天的数据
            if(b) {//如果有今日数据已存在，先删除
            	DeleteService ds=new DeleteService();
            	ds.delete("myinfo", date);
            }
            Paqu pq=new Paqu();//不管数据库是否存在今日数据都会爬取；如果存在，前面已经删除过了，这里的爬取就相当于更新了
        	pq.refesh();
        }        
        List<Info> list = get.listAll(date1,date2);
        request.setAttribute("list",list);
        request.setAttribute("date1",date1);
        request.setAttribute("date2",date2);
        request.getRequestDispatcher("bar.jsp").forward(request, response);
    }
    
    protected void getAllConfirmed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");
        System.out.println(date1);
        System.out.println(date2);
        List<Info> list = get.listAll(date1,date2);
        HttpSession session = request.getSession();
        session.setAttribute("list",list);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        response.getWriter().write(json);
    }
}