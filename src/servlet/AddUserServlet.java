package servlet;

import beans.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddUserServlet",urlPatterns = "/servlet/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String gender=request.getParameter("gender");
        String telephone=request.getParameter("telephone");
        String address=request.getParameter("address");
        String email=request.getParameter("email");

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setTelephone(telephone);
        user.setAddress(address);
        user.setEmail(email);

        UserDao userDao=new UserDao();
        userDao.addUser(user);

        HttpSession session=request.getSession();
        session.setAttribute("user",username);
        response.sendRedirect("/firstPage.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
