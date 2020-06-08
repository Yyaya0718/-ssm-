var code=0;//存验证码
var name='';
var email='';

var err_name=false;
var err_email=false;
var err_yzm=false;
var err_pwd = false;

//用户名的失焦判断
$("#nickname").blur (function(){
	name=$("#nickname").val();
	
	//判断用户名的格式
	if(name == '' ){
		//给当前对象的下一个标签设值
		$(this).next().html("用户名不能为空");
		//未设值前该标签是隐藏的  此时就必须显示来
		$(this).next().show();
		err_name=true;
	}
})

$("#nickname").focus(function(){
	//隐藏
	$(this).next().hide();
	err_name=false;
})

//邮箱的判断
$("#email").blur(function(){
	email=$("#email").val();
	name=$("#nickname").val();
	
	if(email==''){
		$("#email").next().next().html("邮箱不能为空");
		$("#email").next().next().show();
		err_email=true;
		return;
	}
	
	var reg=/^[1-9]\d{4,10}@qq\.com$/i;
	if(!reg.test(email)){
		$("#email").next().next().html("邮箱格式错误");
		$("#email").next().next().show();
		err_email=true;
		return;
	}
	
	//已经获取到了用户名和邮箱，要去数据库里面查询这个人是否存在
	$.post("member/findByEmail",{nickName:name,email:email},function(data){
		data=parseInt($.trim(data));
		if(data==-2){
			alert("参数有为空");
			err_email=true;
		}else if(data>0){
			$(this).next().next().hide();
			err_email=false;
		}else{
			$("#email").next().next().html("此用户还没有注册过或邮箱与用户名不匹配");
			$("#email").next().next().show();
			err_email=true;
		}
	},"text")
	
})

$("#email").focus(function(){
	//隐藏
	$(this).next().next().hide();
	err_email=false;
})

//获取验证码的点击事件
$("#getCode").click(function(){
	$("#getCode").attr("disabled","true");//设置按钮不能再点击
	
	//获取邮箱
	email = $("#email").val();
	uname=$("#nickname").val();
	//非空判断
	if( email== '' || uname==''){
		$("#errmsg").html("邮箱和用户名不能为空").show();
		return;
	}
	
	if(err_name || err_email){
		$("#errmsg").html("邮箱和用户名不能为空").show();
		return;
	}
	
	$("#nickname").attr("readonly","true");
	$("#email").attr("readonly","true");
	
	
	//请求方式  地址  参数  回调函数
	$.post("member/sendCode",{uname:uname,email:email},function(data){
		data=parseInt($.trim(data));
		if(data==-1){
			$("#getCode").removeAttr("disabled").val("重新发送");
			alert("获取验证码失败");
		}else if(data==-2){
			$("#getCode").removeAttr("disabled").val("重新发送");
			alert("邮箱和用户名不能为空");
		}else if(data>0){
			code=data;
			alert("验证码发送成功，请在三分钟之内使用");
			time=180;
			var timer=setInterval(function(){
				if(time>0){
					time--;
					$("#getCode").val(time);
				}else{
					$("#getCode").removeAttr("disabled").val("重新发送");
					$("#nickname").removeAttr("readonly");
					$("#email").removeAttr("readonly");
					code=0;
					//将定时器清除
					clearInterval(timer);
				}
			}, 1000);
			
		}else if(data==-3){
			$("#getCode").removeAttr("disabled").val("重新发送");
			alert("验证码获取失败");
		}
	},"text");
})

//验证码的失焦判断事件
$("#yzm").blur(function(){
	var yzm=$("#yzm").val();
	
	if(yzm=='' || yzm==null){
		$(this).next().html("验证码不能为空").show();
		err_yzm=true;
		return;
	}
	
	if(yzm!=code){
		$(this).next().html("验证码输入错误").show();
		err_yzm=true;
		return;
	}
})

$("#yzm").focus(function(){
	$(this).next().hide();
	err_yzm=false;
})

//确认按钮
$("#reg").click(function(){
	
	if(err_name || err_email || err_yzm){
		$("#errmsg").html("信息填写不完整").show();
		return;
	}
	
	$("#one_div").css("display","none");
	
	$("#two_div").css("display","block");
	
})

//密码验证	
//失焦事件
$("#pwd").blur(function(){
	check_pwd();	
});

//聚焦事件
$("#pwd").focus(function(){
	$(this).next().hide();
	err_pwd=false;
});
	
function check_pwd(){
	//获取密码
	var pwd = $("#pwd").val();
	
	if(pwd=='' || pwd==null){
		$("#pwd").next().html("密码不能为空");
		$("#pwd").next().show();
		err_pwd=true;
		return;
	}
	
	//规则
	var reg = /^[\w@!#$%^&*~]{6,15}$/;
	//判断
	if(!reg.test(pwd)){
		$("#pwd").next().html("密码不符合规范");
		$("#pwd").next().show();
		err_pwd=true;
		return;
	}
	
}

//确认密码验证
//失焦事件
$("#cpwd").blur(function(){
	check_cpwd();
});

//聚焦事件
$("#cpwd").focus(function(){
	$(this).next().hide();
	err_pwd=false;
});
	
function check_cpwd(){
	//获取原密码
	var pwd = $("#pwd").val();
	//获取再次输入的密码
	var cpwd = $("#cpwd").val();
	
	//判断
	if(cpwd == ''){
		$("#cpwd").next().html("确认密码不能为空");
		$("#cpwd").next().show();
		err_pwd=true;
		return;
	}
	if(cpwd != pwd){
		$("#cpwd").next().html("两次输入的密码不一致");
		$("#cpwd").next().show();
		err_pwd=true;
		return;
	}
}

//确认修改密码
function getPwd(){
	name=$("#nickname").val();
	email=$("#email").val();
	
	var pwd=$("#pwd").val();
	$.post("member/updatePwd",{nickName:name,pwd:pwd,email:email},function(data){
		data=parseInt($.trim(data));
		
		if(data==-2){
			alert("说明后台取值有为空的");
		}else if(data>0){
			alert("成功找回密码....");
			location.href="login.html#"+name;
		}else{
			alert("找回密码失败，稍后再试....");
		}
		
	},"text")
}