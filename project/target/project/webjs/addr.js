var docXml;
var code=0;
var cityCode=0;
$(function(){
	$.get("../../xml/areas.xml",function(xml){
		docXml=xml;
		var provinceNodes=$(docXml).find("province");//获取所有的province节点
		//开始循环所有节点 取出省份的值
		var provinceStr='<option value="0">--请选择省份--</option>';
		$.each(provinceNodes,function(index,item){
			province=$(item).attr("name");
			code=$(item).attr("code").substring(0,3);
			provinceStr+='<option value="'+ code +'">'+ province +'</option>';
		})
		$("#province").html(provinceStr);
	})
})

//当省份一旦发生改变的时候，就去修改市的
$("#province").on("change",function(){
	//首先取出你选择的省份是哪一个   获取到的是省份编号的前三位110,130
	var provinceCode=$("#province option:selected").val();
	
	//获取所有的城市节点
	var cityNodes=$(docXml).find("city");
	
	//循环所有的城市节点   ，根据code去找对应城市
	var cityStr='<option value="0">--请选择城市--</option>';
	$(cityNodes).each(function(index,item){
		cityCode=$(item).attr("code");
		//取出code存到value里面
		code=$(item).attr("code").substring(0,4);
		if(cityCode.startsWith(provinceCode)){//首先取出当前这个节点的code看是否是以provinceCode开头
			cityStr+="<option value='"+ code +"'>"+ $(item).attr("name") +"</option>";
		}
	})
	$("#city").html(cityStr);
	
})

//当城市一旦发生改变的时候，就去修改区的
$("#city").on("change",function(){
	//首先取出你选择的城市是哪一个   获取到的是城市编号的前4位1101,1102
	cityCode=$("#city option:selected").val();
	
	//获取所有的区节点
	var areaNodes=$(docXml).find("area");
	
	//循环所有的area节点   ，根据code去找对应区
	var areaCode=0;
	var areaStr='<option value="0">--请选择城市--</option>';
	$(areaNodes).each(function(index,item){
		//首先取出当前这个节点的code看是否是以cityCode开头
		areaCode=$(item).attr("code");
		//取出code存到value里面
		//code=$(item).attr("code").substring(0,4);
		if(areaCode.startsWith(cityCode)){
			areaStr+="<option value='0'>"+ $(item).attr("name") +"</option>";
		}
	})
	$("#area").html(areaStr);
	
})
