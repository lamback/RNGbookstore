package servlet;


import dao.seriesDBOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import VO.shoppingCart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;


public class enterCart extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {
        String username=(String)request.getSession().getAttribute("user");
        request.getSession().setAttribute("user",username);
        ArrayList<shoppingCart> shoppingcar= seriesDBOperation.queryShoppingCart(username);

        JSONArray jsonArray=new JSONArray();

        for(int i=0;i<shoppingcar.size();i++)
        {
            JSONObject json=new JSONObject();
            json.put("productId",shoppingcar.get(i).getBookId());
            json.put("productName",shoppingcar.get(i).getBookName());
            json.put("productPrice",shoppingcar.get(i).getPrice());
            json.put("productQuentity",shoppingcar.get(i).getNumber());
            json.put("productImage",shoppingcar.get(i).getPhoto());
            jsonArray.add(json);
        }
        request.getSession().setAttribute("cart",jsonArray);
        //将shoppingCar传递到购物车
        response.sendRedirect("http://localhost:8080/Cart.jsp");
    }


}
