package dao;

import beans.BookBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

/*
    针对本表的操作有：
    1.往数据库中添加书籍
    2.从数据库中根据书籍种类取出书籍信息在首页展示
 */

public class BooksDao {
    Connection conn=null;
    ResultSet resultSet=null;
    /*
    往数据库中添加书籍
     */
    public void addBook(BookBean bookBean){
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");

            String sql="insert into books(bookName,category,photo,author,information,price) values(?,?,?,?,?,?)";
            state=conn.prepareStatement(sql);
            System.out.println(bookBean.getBookName()+","+bookBean.getCategory()+","+bookBean.getAuthor()+","+bookBean.getInformation()+","+bookBean.getPrice());
            state.setString(1,bookBean.getBookName());
//            System.out.println(bookBean.getCategory());
            state.setString(2,bookBean.getCategory());
//            System.out.println(bookBean.getInformation());
            state.setBytes(3,bookBean.getPhoto());
//            System.out.println(bookBean.getPhoto());
            state.setString(4,bookBean.getAuthor());
//            System.out.println(bookBean.getAutor());
            state.setString(5,bookBean.getInformation());
//            System.out.println(bookBean.getPrice());
            state.setFloat(6,bookBean.getPrice());

            int flag=state.executeUpdate();
            if(flag==0){
                System.out.println("插入失败！");
            }

            state.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
        从数据库中根据书籍种类取出书籍信息在首页展示
     */
    public String showBooksByCategory(String s){
        StringBuilder str=new StringBuilder();
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");

            String sql="select * from rngbookstore.books where category=?";
            state=conn.prepareStatement(sql);
            state.setString(1,s);

            resultSet=state.executeQuery();

            str.append("[");
            while(resultSet.next()){
                str.append("{\"bookName\":"+"\""+resultSet.getString(2)+"\",");
                str.append("\"category\":"+"\""+resultSet.getString(3)+"\",");
                byte[] bytes=resultSet.getBytes(4);
                String tempPath = "D:/IDEA/IdeaProjects/RNGmarket/web/image/"+resultSet
                        .getString(2)+".png";   //随机生成文件名
                String relativePath = tempPath.substring(tempPath.indexOf("/image"));
                //将二进制文件在指定路径，生成图片
                FileOutputStream fileOutputStream = new FileOutputStream(new File(tempPath));
                try {
                    fileOutputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileOutputStream.close();

                str.append("\"photo\":"+"\""+relativePath+"\",");
                str.append("\"author\":"+"\""+resultSet.getString(5)+"\",");
                str.append("\"information\":"+"\""+resultSet.getString(6)+"\",");
                str.append("\"price\":"+"\""+resultSet.getFloat(7)+"\"},");
            }
            int endIndex=str.length()-1;              //删除多余的，号
            str.deleteCharAt(endIndex);
            str.append("]");
            state.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    /*
        查询books表，将数据插入购物车表
     */
    public void addToCart(String name,String userName){
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");

            String sql="select * from rngbookstore.books where bookName=?";
            state=conn.prepareStatement(sql);
            state.setString(1,name);

            resultSet=state.executeQuery();

            while(resultSet.next()) {
                int bookId = resultSet.getInt(1);
//                System.out.println(bookId);
                addToCart2(bookId,userName);
            }
            state.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToCart2(int bookId,String userName){
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");
            if(addToCart3(bookId)==0){
                String sql="insert into rngbookstore.shoppingcart(bookId,username,number)VALUES (?,?,?)";
                state=conn.prepareStatement(sql);
                state.setInt(1,bookId);
                state.setString(2,userName);
                state.setInt(3,1);

                int success=state.executeUpdate();
            }
            state.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int  addToCart3(int bookId){
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");

            String sql="select * from rngbookstore.shoppingcart where bookId=?";
            state=conn.prepareStatement(sql);
            state.setInt(1,bookId);

            resultSet=state.executeQuery();
            if(resultSet.next())
            {
                System.out.println(1);
                return 1;
            }
            state.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(0);
        return 0;
    }
}