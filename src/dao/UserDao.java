package dao;


import beans.User;

import java.sql.*;
import java.util.HashMap;

public class UserDao {
    Connection conn = null;
    ResultSet resultSet = null;

    /*
        1.从数据库中读出用户信息，与用户输入的用户名和密码进行验证
        2.注册页面，加入用户
     */
    public boolean login(String username,String password) {
        HashMap<String,String> userMap=new HashMap<>();
        try {
            Statement state = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn = DriverManager.getConnection(url, "root", "123456");

            String sql = "select * from rngbookstore.user";
            state = conn.createStatement();
            resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                userMap.put(resultSet.getString(1),resultSet.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //验证
        if(userMap.containsKey(username)){
            if(userMap.get(username).equals(password))
            return true;
            return false;
        }
        return false;
    }

    /*
        注册页面，加入用户
     */
    public void addUser(User user){
        try {
            PreparedStatement state=null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
            conn= DriverManager.getConnection(url,"root","123456");

            String sql="insert into user (username,password,gender,telephone,address,email) values(?,?,?,?,?,?)";
            state=conn.prepareStatement(sql);
            state.setString(1,user.getUsername());
//            System.out.println(bookBean.getCategory());
            state.setString(2,user.getPassword());
//            System.out.println(bookBean.getInformation());
            state.setString(3,user.getGender());
//            System.out.println(bookBean.getPhoto());
            state.setString(4,user.getTelephone());
//            System.out.println(bookBean.getAutor());
            state.setString(5,user.getAddress());
//            System.out.println(bookBean.getPrice());
            state.setString(6,user.getEmail());

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
}
