<%@ page language="java" pageEncoding="gb2312" %>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="dao.seriesDBOperation" %>
<%@ page import="VO.order" %>
<html>
<head>
    <style>
        table{
            width: 80%;
            font-size: 30px;
            margin: 0 auto;
            position: relative;
            top: -13px;
        }
        td{
            text-align: center;
            margin-left: 3px;
            margin-right: 3px;
            /*background-color: #98d3d5;*/

        }
        .image{
            width: 150px;
            height: 150px;
        }
        .title{
            text-align: center;
            position: relative;
            font-weight: normal;
            margin-bottom: 30px;
            color: #666;
        }
        .headOFall div{
            display: inline;
            color:gold;
        }
    </style>
    <link rel="stylesheet" href="css/managerFirstPageCss.css">
</head>
<body>
<div class="top">
    <div class="ncuLogin"><img src="image/南昌大学login.jpg" alt="" ></div>
    <div class="address">RNG网上书店</div>
    <div class="manager"><img src="image/头像.png" alt="头像" height="18px" width="18px">
        管理员
    </div>
</div>
<div class="nav">
    <div class="content">
        <ul>
            <li style=" background-color: rgba(250,250,250,0.5);"><a href="managerFirstPage.html"><img src="image/首页-选中.png" alt="" height="40px" width="40px;"></a></li>
            <li><a href="addBooks.html">书籍录入</a></li>
            <li style=" background-color: rgba(250,250,250,0.5)"><a href="showAllOrderList.jsp">查看订单</a></li>
            <li><a href="queryUsers.jsp">用户信息</a></li>
        </ul>
    </div>
</div>
<h2 class="title">---订单列表---</h2>
<%
    ArrayList p = seriesDBOperation.queryOrderList();
%>
<table border="1" cellspacing="0">
    <tr>
        <td>订单号</td>
        <td>图片</td>
        <td>用户名</td>
        <td>商品名</td>
        <td>价格</td>
        <td>商品数量</td>
        <td>订单状态</td>
        <td>确认</td>
    </tr>
    <%
        for (int i = 0;i<p.size();i++){
            order pa = (order) p.get(i);
            //int nowId=pa.getId();
    %>
    <tr>
        <td><%=pa.getOrderId() %></td>
        <td><img class="image" src=<%= "photo/"+pa.getPhoto()%>></td>
        <td><%=pa.getUsername() %></td>
        <td><%=pa.getBookName() %></td>
        <td><%=pa.getPrice() %></td>
        <td><%=pa.getNumber() %></td>
        <td><%=pa.getOrderState()%></td>
        <td><a href="AlterOrderListSta.jsp?now_orderID=<%=pa.getOrderId()%>">确认订单</a> </td>
    </tr>
    <% } %>
</table>
</body>
</html>
