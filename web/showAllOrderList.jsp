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
    <div class="ncuLogin"><img src="image/�ϲ���ѧlogin.jpg" alt="" ></div>
    <div class="address">RNG�������</div>
    <div class="manager"><img src="image/ͷ��.png" alt="ͷ��" height="18px" width="18px">
        ����Ա
    </div>
</div>
<div class="nav">
    <div class="content">
        <ul>
            <li style=" background-color: rgba(250,250,250,0.5);"><a href="managerFirstPage.html"><img src="image/��ҳ-ѡ��.png" alt="" height="40px" width="40px;"></a></li>
            <li><a href="addBooks.html">�鼮¼��</a></li>
            <li style=" background-color: rgba(250,250,250,0.5)"><a href="showAllOrderList.jsp">�鿴����</a></li>
            <li><a href="queryUsers.jsp">�û���Ϣ</a></li>
        </ul>
    </div>
</div>
<h2 class="title">---�����б�---</h2>
<%
    ArrayList p = seriesDBOperation.queryOrderList();
%>
<table border="1" cellspacing="0">
    <tr>
        <td>������</td>
        <td>ͼƬ</td>
        <td>�û���</td>
        <td>��Ʒ��</td>
        <td>�۸�</td>
        <td>��Ʒ����</td>
        <td>����״̬</td>
        <td>ȷ��</td>
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
        <td><a href="AlterOrderListSta.jsp?now_orderID=<%=pa.getOrderId()%>">ȷ�϶���</a> </td>
    </tr>
    <% } %>
</table>
</body>
</html>
