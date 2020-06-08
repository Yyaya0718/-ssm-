
var error=$("#error").css("color","red");

var err_tno=false;
var err_gname=false;
var err_price=false;
var err_balance=false;
var err_intro=false;

//类型
$("#goodsType").blur(function(){
	var tno = $.trim($("#goodsType").val());
	if(tno==""){
		error.html("请选择商品的类型");
		err_tno=true;
		return;
	}
	err_tno=false;
})
$("#goodsType").focus(function(){
	error.html("");
	err_tno=false;
})

//名称
$("#gname").blur(function(){
	var gname = $.trim($("#gname").val());
	if(gname==""){
		error.html("请填写商品的名称");
		err_gname=true;
		return;
	}
	err_gname=false;
})
$("#gname").focus(function(){
	error.html("");
	err_gname=false;
})

//单价
$("#price").blur(function(){
	var price = $.trim($("#price").val());
	if(price==""){
		error.html("请填写商品的单价");
		err_price=true;
		return;
	}
	if(parseInt(balance)<=0){
		error.html("商品的单价不能为负数");
		err_price=true;
		return;
	}
	err_price=false;
})
$("#price").focus(function(){
	error.html("");
	err_price=false;
})

//数量
$("#balance").blur(function(){
	var balance = $.trim($("#balance").val());
	if(balance==""){
		error.html("请填写商品的数量");
		err_balance=true;
		return;
	}
	if(parseInt(balance)<=0){
		error.html("商品的数量不能为负数");
		err_balance=true;
		return;
	}
	err_balance=false;
})
$("#balance").focus(function(){
	error.html("");
	err_balance=false;
})

//描述
$("#intro").blur(function(){
	var intro = $.trim($("#intro").val());
	if(intro==""){
		error.html("请填写商品的描述");
		err_intro=true;
		return;
	}
	err_intro=false;
})
$("#intro").focus(function(){
	error.html("");
	err_intro=false;
})

