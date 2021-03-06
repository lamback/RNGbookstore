package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 定义数据库连接操作
 */
public class DBConnection {
    // 定义数据库连接参数
    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    public static final String URL = "jdbc:mysql://localhost:3306/rngbookstore?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "123456";

    // 注册数据库驱动
    static
    {
        try
        {
            Class.forName(DRIVER_CLASS_NAME);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("注册失败！");
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConn() throws SQLException
    {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // 关闭连接
    public static void closeConn(Connection conn)
    {
        if (null != conn)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println("关闭连接失败！");
                e.printStackTrace();
            }
        }
    }

}
