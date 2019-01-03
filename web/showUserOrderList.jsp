<%@ page import="dao.seriesDBOperation" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="VO.order" %><%--
  Created by IntelliJ IDEA.
  User: Alpha_Lin
  Date: 2018/12/2
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

        <style>
            table{
                width: 80%;
                font-size: 25px;
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
            td{
                background-color: rgb(242,241,242);
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
        <%--<link rel="stylesheet" href="css/managerFirstPageCss.css">--%>
    <link href="css/firstPage.css" rel="stylesheet"/>
    <script src="js/firstPage.js"></script>
    <script src="js/commons.js"></script>
    </head>

<body >
<div class="headOFall" id="father2">
    <!--<div class="user">user,您好！</div>-->
    <a href="#">客服</a>&nbsp;&nbsp;
    <a href="#">帮助</a>&nbsp;&nbsp;
    <a href="#">个人中心</a>&nbsp;&nbsp;
    <a href="#">手机版</a>
</div>

<div class="top">RNG网上书店
</div>

<div class="all">
    <div class="nav">
        <ul id="father">
            <li><a href="/firstPage.html">首页</a></li>
            <li><a href="/servlet/enterCart">我的购物车</a></li>
            <li><a href="showUserOrderList.jsp">订单查询</a></li>
            <li><a href="#" onclick="cancel(),location.reload()">注销</a></li>
        </ul>
    </div>
</div>
<h2 class="title"><%=request.getSession().getAttribute("user")%>--订单列表</h2>
<%
    String username=(String)session.getAttribute("user");
    ArrayList p = seriesDBOperation.queryUserOrderList(username);
%>
<table >
    <tr>
        <td>订单号</td>
        <td>图片</td>
        <td>商品ID</td>
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
        <td><%=pa.getBookId() %></td>
        <td><%=pa.getBookName() %></td>
        <td>¥<%=pa.getPrice() %></td>
        <td><%=pa.getNumber() %></td>
        <td><%=pa.getOrderState()%></td>
        <td><a href="alterUserOrderStateServlet?orderId=<%=pa.getOrderId()%>">确认收货</a> </td>
    </tr>
    <% } %>
</table>
</body>
</html>
