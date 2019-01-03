package test;

import dao.DBConnection;
import dao.ImageUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcImageTest {

    // 将图片插入数据库
    public static void readImage2DB()
    {

        String path = "F:\\java代码\\midWeb\\src\\test\\03.jpg";
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;
        try
        {
            in = ImageUtil.readImage(path);
            conn = DBConnection.getConn();
            String sql = "insert into photo (id,name,photo)values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            ps.setString(2, "charles");
            //setBinaryStream(parameterIndex,FileInputStream,length);
            ps.setBinaryStream(3, in, in.available());
            int count = ps.executeUpdate();
            if (count > 0)
            {
                System.out.println("插入成功！");
            }
            else
            {
                System.out.println("插入失败！");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBConnection.closeConn(conn);
            if (null != ps)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    // 读取数据库中图片
    public static void readDB2Image()
    {
        String targetPath = "F:\\java代码\\midWeb\\src\\test\\04.jpg";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            conn = DBConnection.getConn();
            String sql = "select * from photo where id =?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            rs = ps.executeQuery();
            while (rs.next())
            {
                InputStream in = rs.getBinaryStream("photo");
                ImageUtil.readBin2Image(in, targetPath);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBConnection.closeConn(conn);
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

    //测试
    public static void main(String[] args)
    {
        //readImage2DB();
       readDB2Image();
    }



}
