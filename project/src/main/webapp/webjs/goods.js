var page = 0;
var pages = 0;
var tno=0;
$(function() {
	//获取地址栏的参数
	tno=location.href.split("?")[1].split("&")[0].split("=")[1];
	page = location.href.split("?")[1].split("&")[1].split("=")[1];//当前是第几页

	//根据类型查询所有的商品信息
	$.post("../../good/findByPage",{tno : tno,page : page},function(data) {
		if (data == null) {
			alert("此类型暂时没有商品");
			return;
		}
				
		$("#type_name").html(data.goodsInfo[0].tname);
		var str = '';
		//循环数据进行拼接
		$.each(data.goodsInfo,function(index, item) {
			str += '<li><a href="details.html#'+ item.gno +'"><img src="../../'+ item.pics +'"></a>';
			str += '<h4><a href="details.html#'+ item.gno +'" id="findGno">'+ item.gname+ '</a></h4><div class="operate">';
			str += '<span class="prize">￥'+ item.price+ '.00</span><span class="unit">'+ item.price+ '.00/'+ item.weight +''+ item.unit +'</span>';
			str += '<a href="javascript:void(0);" class="add_goods" title="加入购物车" id="add_cart" onclick="addCart(this)"></a></div></li>';
		})
		$("#goods_show").append($(str));
		//进行分页显示
		//开始做分页处理
		var pageStr = '<ul><li><a href="javascript:void(0)" onclick="changePage(-1)">上一页</a></li>';
		pages = data.pages;
		for (var i = 0; i < pages; i++) {
			if ((page - 1) == i) {
				pageStr += '<li><a href="javascript:void(0)" onclick="toPage('+ (i + 1) +')" class="active">'+ (i + 1) + '</a></li>';
			}else{
				pageStr += '<li><a href="javascript:void(0)" onclick="toPage('+ (i + 1) +')">' + (i + 1)+ '</a></li>';
				}
			}
			pageStr += '<li><a href="javascript:void(0)" onclick="changePage(1)">下一页></a></li></ul>';
			if(pages!=1){
				$("#pageContent").append($(pageStr));
			}
			
	}, "json")
})

function changePage(num) {
	num = parseInt(num);
	page = parseInt(page);
	if (page + num <= 0) {
		page = 1;
	} else if (page + num > pages) {
		page = pages;
	} else {
		page = page + num;
	}
	location.href = "goods.html?tno=" + tno + "&page=" + page;
}
		
function toPage(num){
	location.href = "goods.html?tno=" + tno + "&page=" + num;
}
		
//加入购物车
function addCart(obj){
	$("#add_cart").attr("disabled","true");
	//首先判断是否登录
	if($("#login_nickname").text()=='yc'){
		//说明还没有登录
		location.href="../../login.html";
		return;
	}
	//获取这个商品的gno还有数量
	var num=1;
	var gno=$(obj).parent().prev().find("#findGno").attr("href").split("#")[1];
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
}