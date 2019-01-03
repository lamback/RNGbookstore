package servlet;


import beans.BookBean;
import dao.BooksDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddBooksServlet",urlPatterns = "/servlet/AddBooksServlet")
public class AddBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();

        /*
             用于Base64格式文件转为二进制文档
         */
//            byte[] myPhoto=Base64.decodeBase64(photo);
//        for (byte byte1 : myPhoto) {
//            System.out.print(byte1);
//        }
//        String path = "D:\\IDEA\\IdeaProjects\\RNGmarket\\web\\image\\QQ截图.jpg";
//        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
//        fileOutputStream.write(myPhoto);
//        fileOutputStream.close();
//        byte[] myPhoto =Base64.decodeBase64(photo);
//        BASE64Decoder decoder =new BASE64Decoder();
//        byte[] imageByte=decoder.decodeBuffer(photo);
        String category=request.getParameter("category");
        String photo=request.getParameter("photo");
        String bookName=request.getParameter("bookName");
        String author=request.getParameter("author");
        String information=request.getParameter("information");
        float price=Float.parseFloat(request.getParameter("price"));

        /*
            将图片转为二进制文件，准备存入数据库
         */
        String imagePath= "D:\\IDEA\\IdeaProjects\\RNGmarket\\web\\image\\"+photo;
        byte[] bytes = null;
        //转为二进制数组
        FileInputStream fileInputStream = new FileInputStream(new File(imagePath));
        bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();

        /*
            测试用，看二进制文档能否转出为图片
         */
//        String path = "D:\\IDEA\\IdeaProjects\\RNGmarket\\web\\image\\QQ截图.png";
//        FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
//        fileOutputStream.write(bytes);
//        fileOutputStream.close();

        BookBean bookBean=new BookBean();
        bookBean.setPhoto(bytes);
        bookBean.setCategory(category);
        bookBean.setBookName(bookName);
        bookBean.setAuthor(author);
        bookBean.setInformation(information);
        bookBean.setPrice(price);

        BooksDao booksDao=new BooksDao();
        booksDao.addBook(bookBean);

        /*ServletContext context=getServletContext();
        RequestDispatcher rd=context.getRequestDispatcher("/addBooks.html");     //相对于上下文根路径
        rd.include(request,response);*/
        response.sendRedirect("/addBooks.html");
//        out.println(category+","+photo+","+bookName+","+author+","+information+","+price);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
