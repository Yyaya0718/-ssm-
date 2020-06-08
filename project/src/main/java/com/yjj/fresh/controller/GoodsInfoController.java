package com.yjj.fresh.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yjj.fresh.biz.IGoodsInfoBiz;
import com.yjj.fresh.enity.GoodsInfo;
import com.yjj.fresh.util.AddWaterMark;

@RestController
@RequestMapping("/good")
public class GoodsInfoController {

	@Autowired
	private IGoodsInfoBiz goodInfoBiz;

	@RequestMapping("/findGoods")
	public Map<String,Object> findGood(){
		return goodInfoBiz.findAll();
	}

	/**
	 * 根据tid分页查询商品信息
	 * @param tno
	 * @return
	 */
	@RequestMapping("/findByPage")
	public Map<String,Object> findByPage(@RequestParam Map<String,Object> map){
		if(goodInfoBiz.findByPage(map)==null){
			return null;
		}else{
			return goodInfoBiz.findByPage(map);
		}
	}

	@RequestMapping("/findByGno")
	public GoodsInfo findByGno(String gno){
		return goodInfoBiz.fingByGno(gno);
	}


	//管理员端

	//管理员端的商品信息
	@RequestMapping("/addGood")
	public int add(@RequestParam Map<String,Object> map,MultipartFile pic, HttpServletRequest request){
		int result =-1;

		if(pic.isEmpty()){
			result=-2;//说明没有图片需要上传
		}
		String savePath="";
		try {
			String path= request.getServletContext().getRealPath("");

			savePath="../goods/"+pic.getOriginalFilename();

			File dest = new File(path, savePath);
			//将图片存到服务器的指定文件
			pic.transferTo(dest);
			
			String picPath="F:/yc70/Tomcat/apache-tomcat-8.5.45/webapps/goods/"+pic.getOriginalFilename();
			
			//保存到服务器之后。取出他的地址进行加水印  加完水印还是保存在这里
			AddWaterMark.addWaterMark(picPath, picPath);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		map.put("pics", savePath);
		result=goodInfoBiz.add(map);
		return result;
	}

	@RequestMapping("/upload")
	public Map<String,Object> upload(@RequestParam("upload") MultipartFile pic,HttpServletRequest request){
		Map<String,Object> result=new HashMap<String,Object>();

		if(pic.isEmpty()){
			return null;
		}

		try {
			String savePath="goods/"+pic.getOriginalFilename();

			String path=request.getServletContext().getRealPath("");

			File dest=new File(new File(path).getParentFile(),savePath);
			pic.transferTo(dest);

			result.put("fileName", pic.getOriginalFilename());
			result.put("uploaded", 1);
			result.put("url", "../../../"+savePath);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return result;
	}

	@RequestMapping("/finds")
	public Map<String,Object> findByPage(Integer page,Integer rows){
		return goodInfoBiz.findByPage(page, rows);
	}

	@RequestMapping("/del")
	public int delete(Integer gno){
		return goodInfoBiz.delete(gno);
	}

	@RequestMapping("/update")
	public int update(MultipartFile pic,@RequestParam Map<String,Object> map, HttpServletRequest request){
		String path="";
		if(pic==null || pic.isEmpty()){
			map.put("pics","");
			return goodInfoBiz.update(map);
		}else{
			try {
				String savePath=request.getServletContext().getRealPath("");
				path="../goods/"+pic.getOriginalFilename();

				File f=new File(savePath,path);
				pic.transferTo(f);
				
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put("pics",path);
			return goodInfoBiz.update(map);
		}
	}
	
	
	@RequestMapping("/findNew")
	public List<GoodsInfo> findNew(){
		return goodInfoBiz.findNew();
	}
	
	@RequestMapping("/Tno")
	public List<GoodsInfo> Tno(String tno){
		return goodInfoBiz.findTno(tno);
	}
}
