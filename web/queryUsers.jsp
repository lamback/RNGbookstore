<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,java.sql.*" pageEncoding="gb2312" %>
<%@page import="dao.seriesDBOperation" %>
<%@page import="VO.user" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>�û���Ϣ��ѯ</title>
    <link rel="stylesheet" href="css/findUser.css">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<%
    ArrayList a = seriesDBOperation.queryAllUsers();
%>
<div class="top">
    <div class="ncuLogin"><img src="image/�ϲ���ѧlogin.jpg" alt="" ></div>
    <div class="address">RNG�������</div>
    <div class="manager"><img src="image/ͷ��.png" alt="ͷ��" style="height:18px; width:18px" >
        ����Ա
    </div>
</div>
<div class="nav">
    <div class="content">
        <ul>
            <li><a href="managerFirstPage.html"><img src="image/��ҳ-ѡ��.png" alt="" style="height:40px; width:40px"></a></li>
            <li><a href="addBooks.html">�鼮¼��</a></li>
            <li><a href="showAllOrderList.jsp">�鿴����</a></li>
            <li style=" background-color: rgba(250,250,250,0.5)"><a href="queryUsers.jsp">�û���Ϣ</a></li>
        </ul>
    </div>
</div>
<div class="container bar7">
    <form action="jieguo.jsp">
        <input type="text"  name="username" placeholder="�����û�����ѯ��Ϣ">
        <button type="submit"></button>
    </form>
    <br>
    <hr width="960" align="center">
    <br>
<table align="center" border="1" cellspacing="0">
<tr>
    <td width="70">�û���</td>
    <td width="50">�Ա�</td>
    <td width="120">�绰</td>
    <td width="200">��ַ</td>
    <td>����</td>
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
