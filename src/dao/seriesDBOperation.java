package dao;

import VO.books;
import VO.order;
import VO.shoppingCart;
import VO.user;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * 定义一系列数据库操作
 * 1、将书籍信息存入数据库<></>
 * 2、用户的登入与注册<></>
 * 3、查询所有用户的用户信息<></>
 * 4、查看用户购物车<></>
 * 5、将商品添加到该用户的购物车中<></>
 * 6、加减某一商品的数量<></>
 * 7、查看用户订单列表，所有用户订单列表<
 * 8、添加订单到该用户的订单列表中<
 * 9、修改订单状态<></>
 * 10、根据书籍类别查找书籍<
 */
public class seriesDBOperation {

    //1、书籍信息录入
    public static void entry(books book){
        String sql="insert into books (bookName,category,photo,author,information,price) values(?,?,?,?,?,?);";
        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,book.getBookName());
            pre.setString(2,book.getCategory());
            pre.setBinaryStream(3,book.getFileInputStream(),book.getFileInputStream().available());
            pre.setString(4,book.getAuthor());
            pre.setString(5,book.getInformation());
            pre.setFloat(6,(float)book.getPrice());
            pre.execute();
            DBConnection.closeConn(con);
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }
    }

    //2、登入验证，数据库中有该用户则返回true 否则为false
    public static Boolean login(user user){
        String sql="select * from user where username=?";
        String pswd=" ";
        try {
            Connection con = DBConnection.getConn();
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user.getUsername());
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                pswd = rs.getString("password");
            }
            pre.close();
            con.close();
        }catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        if(pswd.equals(user.getPassword()))
            return true;
        return false;
    }

    //2、用户注册 如果注册成功，返回true 否则，返回false
    public static Boolean registered(user user){
        String sql="insert into user values(?,?,?,?,?,?);";
        int executeSuccess=0;

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,user.getUsername());
            pre.setString(2,user.getPassword());
            pre.setString(3,user.getGender());
            pre.setString(4,user.getTelephone());
            pre.setString(5,user.getAddress());
            pre.setString(6,user.getEmail());
            executeSuccess=pre.executeUpdate();
            pre.close();
            con.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        if(executeSuccess>0)
            return true;
        else
            return false;
    }

    //3、查询所有用户信息
    public static ArrayList<user> queryAllUsers(){
        ArrayList<user> list=new ArrayList<>();
        String sql="select * from user;";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                user user=new user();
                user.setUsername(rs.getString("username"));
                user.setGender(rs.getString("gender"));
                user.setTelephone(rs.getString("telephone"));
                user.setAddress(rs.getString("address"));
                user.setEmail(rs.getString("email"));
                list.add(user);
            }
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    //根据用户名获取该用户的购物车
    public static ArrayList<shoppingCart> queryShoppingCart(String username){
        ArrayList<shoppingCart> list=new ArrayList<>();
        String sql="select bookName,price,number,books.bookId,photo from shoppingcart,books " +
                "where shoppingcart.username=? and shoppingcart.bookId=books.bookId";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,username);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                shoppingCart cart=new shoppingCart();
                cart.setBookName(rs.getString("bookName"));
                cart.setPrice(rs.getFloat("price"));
                cart.setNumber(rs.getInt("number"));
                cart.setBookId(rs.getInt("bookId"));
                cart.setPhoto(rs.getBinaryStream("photo"));
                list.add(cart);
            }
            rs.close();
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    //从购物车中删除
    public static void removeFromShoppingCart(int bookId,String username){
        String sql="delete from shoppingcart where bookId=? and username=?;";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setInt(1,bookId);
            pre.setString(2,username);
            pre.executeUpdate();
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    //将某一用户的购物车中的某商品的数量加减一
    public static void increaseBook(String username ,int bookId,Boolean isAdd){
        String sql1="select number from shoppingcart where username=? and bookId=?";
        int number=0;
        String sql2="update shoppingcart set number=? where username=? and bookId=?";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql1);
            pre.setString(1,username);
            pre.setInt(2,bookId);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                number=rs.getInt("number");
            }
            rs.close();
            pre=con.prepareStatement(sql2);
            if(isAdd)
                pre.setInt(1,number+1);
            else
                pre.setInt(1,number-1);
            pre.setString(2,username);
            pre.setInt(3,bookId);
            pre.executeUpdate();
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    //查询所有用户的订单
    public static ArrayList<order> queryOrderList(){
        ArrayList<order> list=new ArrayList<>();
        String sql="select books.bookId,bookName,photo,price,orderState,number,orderlist.Date,orderId,username " +
                "from orderlist,books where orderlist.bookId=books.bookId order by username;";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                order order=new order();
                order.setBookId(rs.getInt(1));
                order.setBookName(rs.getString(2));
                order.setPhoto(rs.getBinaryStream(3));
                order.setPrice(rs.getFloat(4));
                if(rs.getInt(5)>0){
                    order.setOrderState("订单已完成！");
                }else{
                    order.setOrderState("订单未完成！");
                }
                order.setNumber(rs.getInt(6));
                order.setDate(rs.getDate(7).toString());
                order.setOrderId(rs.getInt(8));
                order.setUsername(rs.getString(9));
                list.add(order);
            }
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    //查询单个用户的订单列表
    public static ArrayList<order> queryUserOrderList(String username){
        ArrayList<order> list=new ArrayList<>();
        String sql="select books.bookId,bookName,photo,price,orderState,number,orderlist.Date,orderId " +
                "from orderlist,books where orderlist.bookId=books.bookId and username=?;";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,username);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                order order=new order();
                order.setBookId(rs.getInt(1));
                order.setBookName(rs.getString(2));
                order.setPhoto(rs.getBinaryStream(3));
                order.setPrice(rs.getFloat(4));
                if(rs.getInt(5)==1){
                    order.setOrderState("已收货！");
                }else{
                    order.setOrderState("未收货！");
                }
                order.setNumber(rs.getInt(6));
                order.setDate(rs.getDate(7).toString());
                order.setOrderId(rs.getInt(8));
                list.add(order);
            }
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    //更改订单状态
    public static void updateOrderState(int orderId){
        String sql="update orderlist set orderState=1 where orderId="+orderId;

        try{
            Connection con=DBConnection.getConn();
            Statement st=con.createStatement();
            st.executeUpdate(sql);
            DBConnection.closeConn(con);
        }catch(SQLException ex){

        }
    }

    //将商品添加至该用户的购物车中
    public static void add2Shoppingcart(String username,int bookId){
        String sql="insert into shoppingcart (bookId,username,number) values(?,?,?);";
        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setInt(1,bookId);
            pre.setString(2,username);
            pre.setInt(3,1);
            pre.executeUpdate();
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    //将订单添加到订单列表中
    public static void add2OrderList(String username,int bookId){
        order order=new order();
        order.setUsername(username);
        order.setBookId(bookId);
        String sqlque="select number from shoppingcart where bookId="+bookId;
        String sqlins="insert into orderlist (bookId,username,orderState,number,Date) values(?,?,?,?,?)";
        String sqldel="delete from shoppingcart where bookId=? and username=?;";
        try{
            Connection con=DBConnection.getConn();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sqlque);
            int count=0;
            while(rs.next())
                count=rs.getInt("number");
            PreparedStatement preins=con.prepareStatement(sqlins);
            preins.setInt(1,order.getBookId());
            preins.setString(2,order.getUsername());
            preins.setInt(3,0);
            preins.setInt(4,count);
            preins.setString(5,order.getDate());
            preins.executeUpdate();
            PreparedStatement predel=con.prepareStatement(sqldel);
            predel.setInt(1,order.getBookId());
            predel.setString(2,username);
            predel.executeUpdate();
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    //根据书籍类别查询书籍信息
    public static ArrayList<books> queryBooksByCategory(String category){
        ArrayList<books> list=new ArrayList<>();
        String sql="select * from books where category=?";

        try{
            Connection con=DBConnection.getConn();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,category);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                books book=new books();
                book.setBookId(rs.getInt("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setAuthor(rs.getString("author"));
                book.setInformation(rs.getString("information"));
                book.setPrice(rs.getFloat("price"));
                book.setInputStream(rs.getBinaryStream("photo"));
                list.add(book);
            }
            DBConnection.closeConn(con);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
