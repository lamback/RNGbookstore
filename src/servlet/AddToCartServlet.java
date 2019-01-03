package servlet;

import dao.BooksDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToCartServlet",urlPatterns = "/servlet/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String i=request.getParameter("flag");
        HttpSession session=request.getSession(true);
        String userName=(String)session.getAttribute("user");
        if(userName!=null){
            BooksDao booksDao=new BooksDao();
            booksDao.addToCart(i,userName);
//            System.out.println(userName);
            response.getWriter().println(1);
        }
        else {
//            System.out.println("fail");
//            response.sendRedirect("/firstPage.html");        //请求无效？
//            System.out.println("1");
            response.getWriter().println(0);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
