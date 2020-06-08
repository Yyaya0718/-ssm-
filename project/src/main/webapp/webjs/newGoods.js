$(function(){
	$.post("../../good/findNew",null,function(data){
		
		var str='';
		
		$.each(data,function(index,item){
			str+='<ul><li><a href="details.html#'+ item.gno +'"><img src="../../'+ item.pics +'"></a>';
			str+='<h4><a href="details.html#'+ item.gno +'">'+ item.gname +'</a></h4><div class="prize">￥'+ item.price +'</div></li></ul>';
		})
		$("#new_goods").append($(str));
	},"json")
	
})
                