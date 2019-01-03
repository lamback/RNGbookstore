package servlet;

import dao.seriesDBOperation;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class alterUserOrderStateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String orderId=request.getParameter("orderId");
        int order_id=Integer.parseInt(orderId);
        seriesDBOperation.updateOrderState(order_id);

        response.sendRedirect("showUserOrderList.jsp");
    }
}
