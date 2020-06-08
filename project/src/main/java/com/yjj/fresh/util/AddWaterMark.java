package com.yjj.fresh.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

public class AddWaterMark {
	 public static void addWaterMark(String beforImg,String afterImg) {

	        Color color = new Color(123,255,33);   // 水印颜色
	        Font font = new Font("楷体", Font.ITALIC, 25);  //水印字体 
	        String waterMarkContent="天天生鲜";   //水印内容
	        String tarImgPath = afterImg;  //存储图片的位置
	        try {
	            File file = new File(beforImg);  //原图片
	            BufferedImage buImage = ImageIO.read(file);
	            int width = buImage.getWidth(); //图片宽
	            int height = buImage.getHeight(); //图片高
	            
	            //添加水印
	            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
	            Graphics2D g = bufferedImage.createGraphics();
	            g.drawImage(buImage, 0, 0, width, height, null);
	            g.setColor(color); //水印颜色
	            g.setFont(font); //水印字体
	            
	            g.drawString(waterMarkContent, 8,20); //水印位置
	            g.dispose(); //释放资源
	            
	            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
	            ImageIO.write(bufferedImage, "jpg", outImgStream);
	            System.out.println("添加水印完成");  
	            outImgStream.flush();  
	            outImgStream.close(); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
