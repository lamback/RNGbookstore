<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.sql.*" pageEncoding="gb2312" %>
<%@page import="dao.seriesDBOperation" %>
<%@page import="VO.user" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户信息查询</title>
    <link rel="stylesheet" href="css/findUser.css">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<%
    ArrayList a = seriesDBOperation.queryAllUsers();
%>
<div class="top">
    <div class="ncuLogin"><img src="image/南昌大学login.jpg" alt="" ></div>
    <div class="address">RNG网上书店</div>
    <div class="manager"><img src="image/头像.png" alt="头像" style="height:18px; width:18px" >
        管理员
    </div>
</div>
<div class="nav">
    <div class="content">
        <ul>
            <li><a href="managerFirstPage.html"><img src="image/首页-选中.png" alt="" style="height:40px; width:40px"></a></li>
            <li><a href="addBooks.html">书籍录入</a></li>
            <li><a href="showAllOrderList.jsp">查看订单</a></li>
            <li style=" background-color: rgba(250,250,250,0.5)"><a href="queryUsers.jsp">用户信息</a></li>
        </ul>
    </div>
</div>
<div class="container bar7">
    <form action="jieguo.jsp">
        <input type="text"  name="username" placeholder="输入用户名查询信息">
        <button type="submit"></button>
    </form>
    <br>
    <hr width="960" align="center">
    <br>
<table align="center" border="1" cellspacing="0">
<tr>
    <td width="70">用户名</td>
    <td width="50">性别</td>
    <td width="120">电话</td>
    <td width="200">地址</td>
    <td>邮箱</td>
    </tr>
<%
    int i = 0;
    while (i< a.size()) {
        user d = (user)a.get(i);
%>
<tr>
    <td height="30"><%=d.getUsername()%></td>
    <td><%=d.getGender()%></td>
    <td><%=d.getTelephone()%></td>
    <td><%=d.getAddress()%></td>
    <td><%=d.getEmail()%></td>
</tr>
<%
        i++;
    }
%>
</table>
</div>
</body>
</html>
