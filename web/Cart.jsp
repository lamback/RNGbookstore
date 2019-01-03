<%@ page import="net.sf.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/22
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/vue.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/vue-resource.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link href="css/firstPage.css" rel="stylesheet"/>
    <script src="js/firstPage.js"></script>
    <script src="js/commons.js"></script>
</head>

<%
    JSONArray car=(JSONArray)request.getSession().getAttribute("cart");
    Object cca=car;
%>
<script>
    dynamicTable(<%=cca%>);
    function refresh() {
        window.location.href="http://localhost:8080/servlet/enterCart";
    }
</script>
<style type="text/css">
    .headOFall div{
        display: inline;
        color:gold;
    }
</style>
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



<div id="app" class="container">
    <h2 class="title">购物车</h2>
    <table class="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr>
            <th colspan="2">商品信息</th>
            <th style="width: 14%;">商品金额</th>
            <th style="width: 14%;">商品数量</th>
            <th style="width: 14%;">总金额</th>
            <th>编辑</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in productList">
            <td style="width: 5%;"><input type="checkbox" :checked="item.check" @click="checkBox(item)"/></td>
            <td class="goods">
                <img :src="item.productImage" class="goods-left"/>
                <div class="goods-right">
                    <p>{{item.productName}}</p>
                </div>
            </td>
            <td>{{item.productPrice | formatMoney}}</td>
            <td class="num">
                <a href="javascript:;" @click="changeMoney(item,-1)">-</a>&nbsp;&nbsp;
                <input type="text" v-model="item.productQuentity"  disabled/>
                <input type="hidden" :id="item.productId" name="bookid" v-model="item.productId" style="display: none">&nbsp;&nbsp;
                <a href="javascript:;" @click="changeMoney(item,1)">+</a>
            </td>
            <td class="redcolor">{{item.productPrice*item.productQuentity | formatMoney}}</td>
            <td class="del" @click="del(item);">删除</td>
        </tr>
        </tbody>
        <tfoot>
        <tr class="footer">
            <td><input type="checkbox" :checked="checkAllFlag" @click="checkAll"/></td>
            <td>
                <span class="redcolor">全选</span>&nbsp;&nbsp;
            </td>
            <td colspan="4">
                <button type="button" style="background-color: #8da7ff;color: #fff;padding: 10px 40px;margin-right: 20px;border: 0;" onclick="refresh()">刷新当前页面</button>
                总计：<span>{{totalMoney | formatMoney}}</span>元
                <button type="button" class="btn" @click="addorder">结账</button>
            </td>
        </tr>
        </tfoot>
    </table>
</div>

</body>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</html>
