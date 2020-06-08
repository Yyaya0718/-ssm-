//发请求获取用户名
	$.post("../../member/findUname",null,function(data){
		if(data!=null){
			$("#login_noname").css("display","none");
			$("#login_uname").css("display","block");
			$("#login_nickname").text(data.nickName);
			$("#login_nickname").attr("name",data.mno);
			$("#show_count").text(data.cartNum);
		}else{
			location.href="../../login.html";
		}
	},"json")


