var number=0;//勾选的商品数量
var totalPrice=0;//勾选的商品的总价格
var alltotalnum=0;
var alltotalprice=0;


$(function(){
	$.post("../../cart/finds",null,function(data){
		if(data!=null){
			var str='';
				
			$.each(data,function(index,item){
			str+='<ul><li class="col01"><input type="checkbox" name="goods_input" checked onclick="check(this)"></li>';
			str+='<li class="col02"><img src="../../'+ item.pics +'"></li>';
			str+=' <li class="col03" id="'+ item.gno +'">'+ item.gname +'<br><em>'+ item.price +'元/'+ item.weight +''+ item.unit +'</em></li>';
			str+=' <li class="col04">'+ item.weight +''+ item.unit +'</li> <li class="col05">'+ item.price +'</li>';
			str+=' <li class="col06"><div class="num_add">';
			str+='<a href="javascript:void(0);" onclick="change(this,-1)" class="minus fl">-</a>';
			str+='<input type="text" value="'+ item.num +'" class="num_show fl">';
			str+='<a href="javascript:void(0);" onclick="change(this,1)" class="add fl">+</a></div></li>';
			str+='<li class="col07">'+ (item.num*item.price) +'</li>';
			str+='<li class="col08"><a href="javascript:void(0);" onclick="delGoods(this,'+ item.cno +')" id="del">删除</a></li></ul>';
			number=number+item.num;
			totalPrice+=(item.num*item.price);
		})
		alltotalnum=number;
		alltotalprice=totalPrice;
		$("#cart_list").html(str);
	}
		$("#total_num").html(data.length);//购物车的全部件数 
		$("#totalPrices").html(totalPrice);
		$("#totalNumbers").html(number);
	},"json")
})    

//删除
function delGoods(obj,cno){
	$("#del").attr("disabled","true");
	
	var flag=confirm("您确定将这件商品从你的购物车删除嘛？");
	
	if(flag){
		$.post("../../cart/del",{cno:cno},function(data){
    		data=parseInt($.trim(data));
    		
    		if(data==-2){
    			alert("说明cno为空");
    			$("#del").removeAttr("disabled");
    		}else if(data>0){
    			alert("删除成功");
    			location.reload();
    		}
    	},"text")
	}else{
		$("#del").removeAttr("disabled");
	}
}

//点击加减数量的函数
function change(obj,count){
	 	count=parseInt(count);
    	var num=0;
    	if(count==1){
    		num=parseInt($(obj).prev().val());
    		num+=count;
    		$(obj).prev().val(num);
    	}else{
    		num=parseInt($(obj).next().val());
    		if(count+num<=0){
    			$(obj).next().val(1);
    		}else{
    			num+=count;
    			$(obj).next().val(num);
    		}
    	}
    	//下面就是要去数据库修改商品的数量   首先要获取gno
    	var gno=$(obj).parent().parent().parent().find(".col03").attr("id");
    	$.post("../../cart/update",{num:num,gno:gno},function(data){
    		data=parseInt($.trim(data));
    		if(data==-1){
    			alert("说明有参数为空！！！！");
    		}else if(data>0){
    			alert("修改成功");
    			var parent=$(obj).parent().parent().parent();
    			//获取当前这个复选框状态
    			var flag=parent.find(".col01").children("input").prop("checked");
    			var priceobj=parent.find(".col07");//每一条的小计
    	    	var price=parseInt(parent.find(".col05").html());//每一条的单价
    	    	priceobj.html(price*num);//修改每一种商品的数量
    			if(flag){//如果选中才修改总的商品价格和数量
        	    	//修改总的价格和商品数量
        	    	//商品的总数量的修改，就是用之前的+count*num
        	    	number=parseInt($("#totalNumbers").html());
        	    	$("#totalNumbers").html(number+count);
        	    	number=number+count;
        	    	//商品的总价的修改
        	    	totalPrice=parseInt($("#totalPrices").html());
        	    	$("#totalPrices").html(totalPrice+count*price);
    			}
    			
    		}else{
    			alert("修改失败");
    		}
    	},"text")

}

//全选按钮的的选中的值的变化
$("#all").on("click",function(){
	if($("#all").prop("checked")){
		//改变值
		$("#totalPrices").html(alltotalprice);
		$("#totalNumbers").html(alltotalnum);
		//改变所有商品前面的复选框的勾选情况
		$("input[name='goods_input']").each(function(){
			$(this).prop("checked",true);
		})
		return;
	}
	
	if(!$("#all").prop("checked")){
		$("#totalPrices").html(0);
		$("#totalNumbers").html(0);
		$("input[name='goods_input']").each(function(){
			$(this).prop("checked",false);
		})
		return;
	}
})



//每个商品前面的复选框的选中与不选中
function check(obj){
	//获取你当前点的这个复选框的商品的数量
	var objnum=parseInt($(obj).parent().parent().find(".col06").children("div").children("input").val());
	//获取当前这个商品的总价
	var objprice=parseInt($(obj).parent().parent().find(".col07").html());
	
	//获取所有商品的价格
	var total=parseInt($("#totalPrices").html());
	
	//console.log(number,objnum);
	//console.log(total,objprice);
	//说明不是全选
	if(!$(obj).prop("checked")){
		//获取当前对象的数量
		number=number-objnum;
		$("#totalNumbers").html(number);
		$("#totalPrices").html(total-objprice);
		
		$("#all").prop("checked",false);
	}else{
		number=number+objnum;
		$("#totalNumbers").html(number);
		$("#totalPrices").html(total+objprice);
	}
	
	//循环所有的input标签来判断是否被选中
	var flag=0;
	var length=$("input[name='goods_input']").length;//商品前面的复选框的个数
	$("input[name='goods_input']:checked").each(function(){
		flag++;
	})
	//说明全选中
	if(flag==length){
		$("#all").prop("checked",true);
	}
}

//跳转结算页面
function toPay(){
	
	//判断你是否有选择商品去结算
	var flag=0;
	var length=$("input[name='goods_input']").length;//商品前面的复选框的个数
	$("input[name='goods_input']").each(function(){
		if(!$(this).prop("checked")){
			flag++;
		}
	})
	if(flag==length){
		alert("你没有选择商品去结算，请你先选择好商品");
		return;
	}
	
	//首先获取到你需要接选的商品有哪些
	var gno='';
	$("input[name='goods_input']:checked").each(function(){
		gno=gno+$(this).parent().parent().find(".col03").attr("id")+",";
	})
	gno=gno.substring(0,gno.length-1);
	location.href="pay.html#"+ gno +"";
	
}