var docXml;	//获取到的xml文件信息
var code=0;	//省的编码
var cityCode=0;	//城市的编码
$(function(){
	//发get请求得到areas.xml 这里的地址是你放areas.xml的地址
	$.get("../../xml/areas.xml",function(xml){
		docXml=xml;
		
		//获取所有的省份节点
		var provinceNodes=$(docXml).find("province");
		
		var provinceStr='<option value="0">--请选择省份--</option>';
		
		//开始循环所有节点 取出省份的值
		$.each(provinceNodes,function(index,item){
			
			//获取当前这个省份的name属性的值，即省份的名称
			province=$(item).attr("name");
			
			//获取这个省份的编码值，只取前三位，因为这个省有哪些市，这些市的编码前三位和省的前三位相同
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
	
	var cityStr='<option value="0">--请选择城市--</option>';
	
	//循环所有的城市节点   ，根据code去找对应城市
	$(cityNodes).each(function(index,item){
		cityCode=$(item).attr("code");
		//取出code存到value里面
		code=$(item).attr("code").substring(0,4);
		
		//首先取出当前这个节点的code看是否是以provinceCode开头
		if(cityCode.startsWith(provinceCode)){
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
	
	var areaCode=0;
	var areaStr='<option value="0">--请选择城市--</option>';
	
	//循环所有的area节点   ，根据code去找对应区
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
