package servlet;

import dao.BooksDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowBooksByCategoryServlet",urlPatterns = "/servlet/ShowBooksByCategoryServlet")
public class ShowBooksByCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksDao booksDao=new BooksDao();
//        String string=booksDao.showBooksByCategory("art");
        String string=booksDao.showBooksByCategory(request.getParameter("category"));
//        System.out.println(string);
        response.getWriter().println(string);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
