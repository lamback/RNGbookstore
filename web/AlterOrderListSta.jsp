<%@ page language="java" pageEncoding="gb2312" %>
<%@ page import="dao.seriesDBOperation" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String order_id=request.getParameter("now_orderID");
    int orid=Integer.parseInt(order_id);
    seriesDBOperation.updateOrderState(orid);
%>
<jsp:forward page="showAllOrderList.jsp"></jsp:forward>
</body>
</html>
