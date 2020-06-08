//获取商品的 
var gnos=location.hash.replace("#", "");
//页面一加载就显示已有的收货地址和商品信息
$(function(){
	$.post("../../order/finds",{gnos:gnos},function(data){
		var addrStr='<dt>寄送到：</dt>';
		var goodsStr='';
		var num=0;
		var totalPrice=0;
		
		
		$.each(data.addrInfo,function(index,item){
			if(item.flag==1){
				addrStr+='<dd class="selectedAddr" onclick="defaultAddr(this)" id="'+ item.ano +'">';
			}else{
				addrStr+='<dd onclick="defaultAddr(this)" id="'+ item.ano +'">';
			}
			addrStr+='<span>邮寄到 </span>'+ item.province +' '+ item.city +' '+ item.area +' '+ item.addr +' （'+ item.name +' 收） '+ item.tel +' </dd>';
		})
		$("#addr_list").append($(addrStr));
		
		$.each(data.goodsInfo,function(index,item){
			goodsStr+='<ul class="goods_list_td clearfix">';
			goodsStr+='<li class="col01" name="cart_cno" id="'+ item.cno +'">'+ item.gno +'</li>';
			goodsStr+='<li class="col02"><img src="../../'+ item.pics +'"></li>';
			goodsStr+='<li class="col03">'+ item.gname +'</li>';
			goodsStr+='<li class="col04">'+ item.weight +''+ item.unit +'</li>';
			goodsStr+='<li class="col05">'+ item.price +'元</li>';
			goodsStr+='<li class="col06">'+ item.num +'</li>';
			goodsStr+='<li class="col07" id="'+ item.tno +'">'+ item.num*item.price +'元</li></ul>';
			num=num+parseInt(item.num);
			totalPrice+=parseInt(item.num*item.price);
		})
		$("#order_list").append($(goodsStr));
		$("#total_count").html(num);
		$("#total_price").html(totalPrice);
		
		if(totalPrice>=100){
			$("#yf").html(0);
		}
		$("#real_price").html(parseInt(totalPrice)+parseInt($("#yf").html()));
	},"json")
	
	
	$("#addr_list>dd").click(function(){
		$("#addr_list>dd").removeClass("selectedAddr");
		$(this).addClass("selectedAddr");
	});
})

//编辑收货地址弹框
function showAddrDiv() {
	$("#myform")[0].reset();
	$("#addr_div").css("display", "block");
}

//收货地址按钮的关闭
function hiddenDiv(){
	$("#addr_div").css("display", "none");
}

//设置为默认的收货地址
function defaultAddr(obj){
	var ano=$(obj).attr("id");
	//将这个设置成默认地址
	$.post("../../addr/defalut",{ano:ano},function(data){
		data=parseInt($.trim(data));
		if(data==-2){
			alert("说明ano为空");
		}else if(data>0){
			location.reload();
			$(obj).css("background","url('../../images/location.png') no-repeat 60px center");
		}
	},"text")
}

//添加收货地址按钮
function addAddr() {
	$("#add_addr").attr("disabled","true");
	var name=$("#addr_name").val();
	var tel=$("#addr_tel").val();
	var province=$("#province option:selected").html();
	var city=$("#city option:selected").html();
	var area=$("#area option:selected").html();
	var addr=$("#addr_addr").val();
	
	//console.log(name,tel,province,city,area,addr);
	if(name=='' || tel=='' || province=='' || city=='' || area=='' || addr==''){
		alert("您填写的信息有为空的");
		return;
	}
	
	$.post("../../addr/add",{name:name,tel:tel,province:province,city:city,area:area,addr:addr},function(data){
		data=parseInt($.trim(data));
		if(data==-2){
			alert("后台接受到的数据有为空的");
		}else if(data==-3){
			alert("说明mno为空");
		}else if(data>0){
			alert("添加成功");
			location.reload();
		}else{
			alert("添加失败");
		}
	})
	
}

//提交订单按钮
$('#order_btn').click(function() {
	var mno=$("#login_nickname").attr("name");
	var cno=[];
	$(".col01[name='cart_cno']").each(function(index,item){
		cno[cno.length]=$(this).attr("id");
	})
	var gno=[];
	var num=[];
	var price=[];
	$("#order_list .col01").each(function(index,item){
		var obj=$(this).parent().find(".col07");
		gno[gno.length]=$(this).html();
		num[num.length]=$(this).parent().find(".col06").html();
		price[price.length]=obj.html().replace("元","")+"-"+obj.attr("id");
	})
	
	var totalPrice=$("#real_price").html();
	var ano=$(".selectedAddr").attr("id");
	
	$.post("../../order/pay",{
		cno:cno.join("-"),
		gno:gno.join(","),
		num:num.join(","),
		price:price.join(","),
		totalPrice:totalPrice,
		ano:ano,
		mno:mno
		},function(data){
			data=parseInt($.trim(data));
			if(data==-2){
				alert("说明后台取值有为空的");
			}else if(data>0){
				$('.popup_con').fadeIn('fast', function() {
					setTimeout(function(){
						$('.popup_con').fadeOut('fast',function(){
							window.location.href = 'order.html';
						});	
					},3000)
				});
			}else{
				alert("订单提交失败");
			}
		},"text")
});