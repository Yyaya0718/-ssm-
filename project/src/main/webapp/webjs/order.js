$(function(){
		$.post("../../order/findall",null,function(data){
			var str='';
			$.each(data,function(index,item){
				str+='<div class="order_title">'+ item.odate +'&nbsp;&nbsp;&nbsp;&nbsp; 订单号：'+item.ono;
				if(item.priceAll<=90){
					str+='&nbsp;&nbsp;&nbsp;&nbsp;总金额为：<label style="color:red;">'+item.priceAll+'</label>&nbsp;&nbsp;&nbsp;&nbsp;(含运费<label style="color:red;"> 10 </label>元)';
				}else{
					str+='&nbsp;&nbsp;&nbsp;&nbsp;总金额为：<label style="color:red;">'+item.priceAll+'</label>&nbsp;&nbsp;&nbsp;&nbsp;';
				}
				
				if(item.status==0){
					str+='<a href="javascript:void(0)" onclick="topay('+item.priceAll+',this)" id="'+ item.ono +'">去支付</a></div>';
				}else if(item.status==1){
					str+='<a href="">等待发货</a></div>';
				}else{
					str+='<a href="">已发货</a></div>';
				}
				$.each(item.orderItem,function(index,itm){
					str+='<ul class="goods_list_td clearfix"><li class="col01">'+ itm.goodsinfo.gno +'</li>';
					str+='<li class="col02"><img src="../../'+ itm.goodsinfo.pics +'"></li>';
					str+='<li class="col03">'+ itm.goodsinfo.gname +'</li><li class="col04">'+ itm.goodsinfo.weight +''+ itm.goodsinfo.unit +'</li>';
					str+='<li class="col04">'+ itm.goodsinfo.price +'元</li><li class="col03">'+ itm.nums +'</li>';
					str+='<li class="col04">'+ itm.goodsinfo.price*itm.nums +'元</li></ul>';
					
				})
			})
			
			$("#order_goods").append($(str));
		},"json")
	})
	
	function topay(price,that){
	var ono=$(that).attr("id");
		$.post("../../apli/pay",{ono:ono,price:price},function(data){
			$("#apli").html(data);
		},"text")
	}