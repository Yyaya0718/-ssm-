package com.yjj.fresh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjj.fresh.biz.ITypeInfoBiz;
import com.yjj.fresh.enity.TypeInfo;

@RestController
@RequestMapping("/type")
public class TypeInfoController {
	
	@Autowired
	private ITypeInfoBiz typeInfoBiz;

	@RequestMapping("/finds")
	public Map<String,Object> findAll(@RequestParam Map<String,Object> map){
		return typeInfoBiz.findAll(map);
	}
	
	@RequestMapping("/findAll")
	public List<TypeInfo> finds(){
		return typeInfoBiz.finds();
	}
}
