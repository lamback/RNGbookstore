package servlet;


import dao.seriesDBOperation;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class enterOrder extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        //得到username和bookID
        String username=(String)request.getSession().getAttribute("user");
        int bookid=Integer.parseInt(request.getParameter("bookid"));
        //相关物品加入订单，再从数据库删除
        seriesDBOperation.add2OrderList(username,bookid);
        //seriesDBOperation.removeFromShoppingCart(bookid,username);
        PrintWriter out=response.getWriter();
        out.println(bookid);
    }

}
