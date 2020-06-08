package com.yjj.fresh.enity;

public class OrderItemInfo {
	private Integer ino;
	private String ono;//订单编号
	private Integer gno;//商品编号
	private String nums;// 购买数量
	private String price ;//购买单价
	private Integer status;
	private String odate;
	
	private GoodsInfo goodsinfo;

	@Override
	public String toString() {
		return "OrderItemInfo [ino=" + ino + ", ono=" + ono + ", gno=" + gno + ", nums=" + nums + ", price=" + price
				+ ", status=" + status + ", odate=" + odate + ", goodsinfo=" + goodsinfo + "]";
	}

	public Integer getIno() {
		return ino;
	}

	public void setIno(Integer ino) {
		this.ino = ino;
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public Integer getGno() {
		return gno;
	}

	public void setGno(Integer gno) {
		this.gno = gno;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public GoodsInfo getGoodsinfo() {
		return goodsinfo;
	}

	public void setGoodsinfo(GoodsInfo goodsinfo) {
		this.goodsinfo = goodsinfo;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public OrderItemInfo(Integer ino, String ono, Integer gno, String nums, String price, Integer status,
			GoodsInfo goodsinfo) {
		super();
		this.ino = ino;
		this.ono = ono;
		this.gno = gno;
		this.nums = nums;
		this.price = price;
		this.status = status;
		this.goodsinfo = goodsinfo;
	}

	public OrderItemInfo() {
		super();
	}
	
	
	
	
}
