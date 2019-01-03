function showBooks(category) {
    var xmlHttp=new XMLHttpRequest();     //适用于非IE浏览器
    xmlHttp.open("POST","servlet/ShowBooksByCategoryServlet?category="+category,true);
    xmlHttp.onreadystatechange=function () {
        if((xmlHttp.readyState==4)){
            var json=eval("("+xmlHttp.responseText+")");
            var length=json.length;
            addBook(length,json);
        }
    }
    xmlHttp.send();
}

function addBook(length,json) {
    var showBookArea=document.getElementsByClassName("showBookArea");
    showBookArea[0].innerHTML=null;
    for(var i=0;i<length;i++){
        var book=document.createElement("div");
        book.className="book";
        book.innerHTML=" <img src=\""+json[i].photo+"\" >\n" +
            "            <div class=\"message\">\n" +
            "              <strong>"+json[i].bookName+"</strong>\n" +
            "                <br>\n" +
            "                作者："+json[i].author+"\n" +
            "                <br>\n" +
            "                ¥"+json[i].price+"\n" +
            "                <br>\n" +
            "                <a href='javascrip:void(0)' onclick='addToCart()'  id="+json[i].bookName+">加入购物车</a>\n" +
            "            </div>";
        // console.log(showBookArea);
        showBookArea[0].appendChild(book);
        // var img=document.createElement("img");
        // img.src=json[i].photo;
        // book.appendChild(img);
        // var message=document.createElement("div");
    }
}

function addToCart(event) {
    var test = window.event;
    // console.log(test.target.id);
    // console.log('gsag');
    var xmlHttp=new XMLHttpRequest();     //适用于非IE浏览器e
    xmlHttp.open("POST","servlet/AddToCartServlet?flag="+test.target.id,true);
    xmlHttp.onreadystatechange=function () {
        if(xmlHttp.readyState==4){
            var a=xmlHttp.responseText;
            // alert(typeof a);
            if(a==0)
                window.location.href="/login.html";
            else
            alert("添加成功！");
        }
    }
    xmlHttp.send();
}
function isLogin() {
    var xmlHttp=new XMLHttpRequest();     //适用于非IE浏览器
    xmlHttp.open("POST","servlet/IsLoginServlet",true);
    xmlHttp.onreadystatechange=function () {
        if(xmlHttp.readyState==4){
            var username=xmlHttp.responseText;
            var str="null";
            // console.log(typeof username);
            // console.log(typeof str);
            // console.log(str);
            // console.log(username.length);
            // for(var i=0;i<username.length;i++){
            //     console.log(username.charAt(i))
            // }
            var tempusername=username.substring(0,4);     //返回的null在其后面带有两个空格
            // console.log(tempusername==str);
            if(!(tempusername==str)){        //js中无equals方法
                var father=document.getElementById("father");
                var son1=document.getElementById("son1");
                var son2=document.getElementById("son2");
                father.removeChild(son1);
                father.removeChild(son2);
                // console.log(1);
                var father2=document.getElementById("father2");
                father2.innerHTML=null;
                father2.innerHTML="<div class=\"user\">"+username+",您好！</div>\n" +
                    "        <a href=\"#\">客服</a>&nbsp;&nbsp;\n" +
                    "        <a href=\"#\">帮助</a>&nbsp;&nbsp;\n" +
                    "        <a href=\"#\">个人中心</a>&nbsp;&nbsp;\n" +
                    "        <a href=\"#\">手机版</a>"
            }
        }
    }
    xmlHttp.send();
}

function cancel() {
    var xmlHttp=new XMLHttpRequest();     //适用于非IE浏览器
    xmlHttp.open("POST","servlet/CancelServlet",true);         //get会有缓存
    xmlHttp.onreadystatechange=function () {
        if(xmlHttp.readyState==4){
            window.location.href="/firstPage.html";
        }
    }
    xmlHttp.send();
}