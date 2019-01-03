var pd;
function dynamicTable(result) {
    var data = result;//取到一个json数据
    if (data == null) {
        return;
    }
    var datavue = [];
    for (var i = 0; i < data.length; i++) {//json类似一个数组，所以通过循环输出里面
        var objproject = {
            "productId" : result[i].productId,//这个是赋值到一个数组对象里面去，开发的时候就是取到里面的值进行一个逻辑判断，要干嘛干嘛的。这个也加上他的下标
            "productName" : result[i].productName,
            "productPrice" : result[i].productPrice,
            "productQuentity" : result[i].productQuentity,
            "productImage" : result[i].productImage
        }
        datavue.push(objproject);
    }
    pd=datavue;
}

var vm=new Vue({
	el:"#app",
	data:{
		totalMoney:0,
		productList:[],
		checkAllFlag:false
	},
	filters:{
		formatMoney: function (value) {
			return "￥ "+value.toFixed(2);
		}
	},
	mounted:function(){
		this.cartView();
	},
	methods:{
		cartView:function () {
			var _this=this;
            _this.productList=pd;
			//获取商品数据信息

		},
		//点击增加或减少商品数量按钮后修改对应金额
		changeMoney:function (product,way) {
			if (way>0) {
				product.productQuentity++;
                $.ajax({
                    type: "GET",//方法类型
                    dataType: "json",//预期服务器返回的数据类型
                    url: "/servlet/add" ,//url
                    data: $('#'+product.productId).serialize()
                })
			}else{
				product.productQuentity--;
                $.ajax({
                    type: "GET",//方法类型
                    dataType: "json",//预期服务器返回的数据类型
                    url: "/servlet/remove" ,//url
                    data: $('#'+product.productId).serialize()
                })
				if (product.productQuentity<1) {
					product.productQuentity=1;
				}
			}
			this.getTotalMoney();
		},
		//单选框设置
		checkBox:function (item){
			var _this=this;
			var num=0;
			if (typeof item.check == "undefined") {
				this.$set(item,"check",true);
			}else{
				item.check = !item.check;
			}
			this.productList.forEach(function (item,value) {
				if (item.check) {
					num++;
				}
			})
			if (num==_this.productList.length) {
				this.checkAllFlag=true;
			}else{
				this.checkAllFlag=false;
			}
			this.getTotalMoney();
		},
		//全选按钮设置
		checkAll:function (){
			var _this=this;
			this.checkAllFlag = !this.checkAllFlag;
			this.productList.forEach(function(item,index){
				if (typeof item.check == "undefined") {
					_this.$set(item,"check",_this.checkAllFlag);
				}else{
					item.check = _this.checkAllFlag;
				}
			})
			this.getTotalMoney();
		},
		//总金额设置
		getTotalMoney:function () {
			var _this=this;
			this.totalMoney = 0;
			this.productList.forEach(function (item,index) {
				if (item.check) {
					_this.totalMoney += item.productQuentity*item.productPrice;
				}
			})
		},
		//删除商品
		del: function (item) {
			var index=this.productList.indexOf(item);
			this.productList.splice(index,1);
			this.getTotalMoney();
            $.ajax({
                type: "GET",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/servlet/delete" ,//url
                data: $('#'+item.productId).serialize()
            })
		},
		//增加订单
		addorder: function (item) {
			var num=0;
            this.productList.forEach(function (item,index) { //所选商品
                if (item.check) {
                	num++;
                    $.ajax({
                        type: "GET",//方法类型
                        dataType: "json",//预期服务器返回的数据类型
                        url: "/servlet/enterOrder" ,//url
                        data: $('#'+item.productId).serialize(),
                        success: function (result) {
                            console.log(result);//打印服务端返回的数据(调试用)
                        }
                    })
                }
            });
            if(num==0) {alert("您尚未选择商品")}
            else{
                alert("下单成功！您共选择"+num+"件商品");
                window.location.href="http://localhost:8080/showUserOrderList.jsp";
			}


        }
	}
})

