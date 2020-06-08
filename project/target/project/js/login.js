$(function(){
	//判断地址栏有没有用户名
	var uname=location.hash.replace("#", "");
	$('#nickname').val(uname);

	getCode();
	
	//取cookie	LoginInfo=yjj0718-888888
	var str=document.cookie;
	
	var pwd=str.split("-")[1];
	var pwd = decodeURIComponent($.base64.atob(pwd));//解密
	
	$("#nickname").val(str.split("=")[1].split("-")[0]);
	$("#pwd").val(pwd);
})
/**
 * 登录的方法
 */

	var code;//存正确的验证码
	var err_code=false;
    var err_name = false;
    var err_pwd = false;

    var username = $('#nickname');
    var password = $('#pwd');
    var err_uname = $('.user_error');//用户名的错误信息
    var err_password=$(".pwd_error");//密码错误信息
    var err_yzm=$(".code_error");//验证码错误信息

    username.blur(function () {
        check_username();
    });

    username.focus(function () {
    	//隐藏
    	err_uname.hide();
    });

    password.blur(function () {
        check_password();
    });

    password.focus(function () {
    	err_password.hide();
    });

    function check_username() {
        var re = /^\w{6,15}(\@[a-z0-9]+(\.[0-9a-z]+){1,2})?$/;
        var val = username.val();
        if (val == '') {
        	err_uname.html("用户名不能为空");
        	err_uname.show();
            err_name = true;
            return false;
        }

        if (re.test(val)) {
        	err_uname.hide();
            err_name = false;
        }else {
        	err_uname.html('用户名必须是6到十五位 或者是 邮箱');
        	err_uname.show();
            err_name = true;
            return false;
        }
    }

    function check_password() {
        var re = /^[\w@!#$%&^*]{6,15}$/;
        var val = password.val();

        if (val == '') {
            err_password.html('密码不能为空');
            err_password.show();
            err_pwd = true;
            return false;
        }

        if (re.test(val)) {
            err_pwd = false;
            err_password.hide();
        }
        else{
            err_password.html('密码是6到15位字母、数字');
            err_password.show();
            err_pwd = true;
            return false;
        }
    }

  //获取验证码
	function getCode(){
		code='';  /*默认code为空*/
        var codes=[0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];  /*声明一个数组,用来放所有数字字母*/
        for(var i=0;i<4;i++){  /*获取长度为4的验证码*/
        	var j=Math.floor(Math.random()*62);/*从codes数组下标随机获取数字0-61,存入arr*/
        	code+=codes[j];
      	}
       $("#code").val(code); /*把得到的验证码赋值给显示按钮*/
	}	
	
	$("#yzm").blur(function(){
		validate();
	})
	$("#yzm").focus(function(){
		err_code=false;
		err_yzm.hide();
	})
	
	//验证码的格式和正确性验证
	 function validate(){
		//获取验证码
		var yzm = $("#yzm").val();
		
		if(yzm==""){
			err_yzm.html("验证码为空").show();
			return true;
		}
		
        var reg=/^[0-9a-zA-Z]{4}$/;
        if(reg.test(yzm)){   /*对输入的验证码的格式进行验证*/
            if(yzm.toLowerCase()==code.toLowerCase()){  /*把验证码都转为小写在比较*/
            	err_code=false;
                return false;
            }else{
            	err_yzm.html("验证码错误,请重新输入").show();
                err_code=true;
            }
        }else{
        	err_yzm.html("验证码的格式错误,请重新输入").show();
            err_code=true;
        }

    }
  
	//模拟登陆
	function login(	){
		$(".submit_input").attr("disabled","true");
		//获取用户名
		var nickname = $("#nickname").val();
		//获取密码
		var pwd = $("#pwd").val();
		//获取验证码
		var yzm = $("#yzm").val();
		
		var md5pwd=$.base64.btoa(encodeURIComponent(pwd));//传过去一个MD5加密过的密码  便于存cookie
		
		var flag=0;//判断是否要使用记住密码和用户名
		if($("#remember").prop("checked") ){
			flag=1;
		}
		
		if(err_name){
			$(".submit_input").removeAttr("disabled");
			alert("用户名输入有误");
			return;
		}
		
		if(err_pwd){
			$(".submit_input").removeAttr("disabled");
			alert("密码输入有误");
			return;
		}
		
		if(yzm=='' || err_code){
			$(".submit_input").removeAttr("disabled");
			alert("验证码输入有误");
			return;
		}
		
		//发送请求 登录
		$.post("member/login",{nickName:nickname,pwd:pwd,flag:flag,md:md5pwd},function(data){
			data=parseInt($.trim(data));
			if(data>0){
				//跳转转网页
				location.href = "member/index";	
			}else{
				$(".submit_input").removeAttr("disabled");
				alert("用户名或密码错误");
				location.href = "login.html";
			}
		},"text")
		
	}	
