package servlet;

import dao.seriesDBOperation;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class remove extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        //得到username和bookID
        String username=(String)request.getSession().getAttribute("user");
        int bookid=Integer.parseInt(request.getParameter("bookid"));
        //从数据库中去除一件username的购物车中bookid的物品
        seriesDBOperation.increaseBook(username,bookid,false);

    }
}
