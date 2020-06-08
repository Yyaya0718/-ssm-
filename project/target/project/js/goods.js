//添加购物车
function addcar(gtitle){
	var commodityName = gtitle;//类型名称
	var num = $('.num_show').val();
	$.post("../Fresh/jugement",{op:"addCar",commodityName: commodityName,num:1,type:"id"},function(data){
		if(data == 0){
			//加入失败
			alert("加入购物车失败!!!!")
		}else{
			//加入成功
			alert("加入购物车成功!!!!")
		}

	},"json")
}

//热门查询动态样式
//给我们热门类型查询做点击事件
$(".type").on("click",function(){
	//默认查询方式 
	var type = "id";
	//移除原来的active的样式
	$(".type").removeClass("active");
	//获取此时点击的对象的文本是什么
	var check = $(this).html();
	//判断
	if(check == "默认"){
		$("#normal").addClass("active");
	}
	if(check == "价格"){
		$("#price").addClass("active");
		type = "gprice"
	}
	if(check == "人气"){
		$("#manUse").addClass("active");
		type = "gsales";
	}
})