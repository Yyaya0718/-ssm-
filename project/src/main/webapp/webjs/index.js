 let findsGood=new Vue({
	 el:"#app",
	 data:{
		 loginflag:false,
		 loginbtn:true,
		 login_uname:'',
		 cartnum:0,
		 goods:{},
		 types:{}
	 },
	 methods:{
		 
	 },
	 mounted:function(){//数据挂载之前
		 axios.all( [ checkName(), findAllGoods() ] ).then( axios.spread( ( d1,d2 )=>{
			 
			 if(d1.data!=null && d1.data!=""){
				 this.loginflag=true;
				 this.loginbtn=false;
				 this.login_uname=d1.data.nickName;
				 this.cartnum=d1.data.cartNum;
			 }else{
				 this.loginflag=false;
				 this.loginbtn=true;
			 }
			 
			 if(d2.data!=null){
				 this.goods=d2.data.goods;
				 this.types=d2.data.types;
			 }
			 
		  }) );
	 }
 })
 
 function checkName(){
	return axios({
		url:"../member/findUname",
		method:"post"
	});
}
 
 function findAllGoods(){
	 return axios({
		 url:"../good/findGoods",
		 method:"post"
	 });
 }