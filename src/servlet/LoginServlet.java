package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        //判断是否为管理员
        if(username.equals("manager")&&password.equals("123456"))
            response.sendRedirect("/managerFirstPage.html");
        else{
            UserDao userDao=new UserDao();
            if(userDao.login(username,password))
            {
                HttpSession session=request.getSession();
                session.setAttribute("user",username);
//                response.getWriter().println(username);
                response.sendRedirect("/firstPage.html");
            }
            else
                response.sendRedirect("/login.html");
        }
//        response.getWriter().println("1");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
