var gno=location.hash.replace("#", "");
var num=0;
var price=0;
$(function(){
	$.post("../../good/findByGno",{gno:gno},function(data){
		if(data!=null){
			$("#type_name").html(data.tname);
				
			$("#good_img").attr("src",'../../'+ data.pics +'');
			$("#desc").html(data.descr);
				
			var str='<h3>'+ data.gname +'</h3><p>'+ data.intro +'</p>';
			str+='<div class="price_bar">';
			str+='<span class="show_price">￥<em>'+ data.price +'</em>元</span>';
			str+='<span class="show_unit">单位：<em>'+ data.weight +''+ data.unit +'</em></span></div>';
			$("#goods_list").html(str);
				
			num=parseInt($("#num").val());//数量
			price=parseInt($(".show_price em").html());//单价
				
			$("#price").html(num*price);
		}
	},"json")
})
	
	
function changeNum(count){
	var count=parseInt(count);
	num=parseInt($("#num").val());//数量
		//console.log(count,num,price);
	if(count+num <=0){
		$("#num").val(1);
		$("#price").html(price);
	}else{
		$("#num").val(count+num);
		$("#price").html(price*(count+num));
	}
}

//立即购买
$("#buy_btn").click(function(){
	//首先应该先将这个信息添加到购物车，添加成功了才能进行跳转
	$.post("../../cart/addCart",{
		num:1,
		gno:gno
	},function(data){
		data=parseInt($.trim(data));
		if(data>0){
			location.href = "pay.html#"+gno;
		}else{
			alert("立即购买失败，请稍后再试....");
		}
	})
	
});
		
//加入购物车
$("#add_cart").click(function(){
	$("#add_cart").attr("disabled","true");
	//首先判断是否登录
	if($("#login_nickname").text()=='yc'){
		//说明还没有登录
		location.href="../../login.html";
		return;
	}
			
	//获取这个商品的gno还有数量
	num=parseInt($("#num").val());
	$.post("../../cart/addCart",{num:num,gno:gno},function(data){
		$("#add_cart").removeAttr("disabled");
		data=parseInt($.trim(data));
		if(data==-2){
			alert("有属性为空");
				
		}else if(data==-3){
			alert("说明Mno为空");
		}else if(data>0){
			//说明加入购物车成功
			alert("加入购物车成功");
			location.reload();
		}else{
			alert("添加失败，请稍后再试...");
		}
	},"text")
});