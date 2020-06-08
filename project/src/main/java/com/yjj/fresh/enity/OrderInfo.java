package com.yjj.fresh.enity;

import java.util.List;

public class OrderInfo {
	private String ono;
	private String odate;//下单日期
	private Integer ano;//收货地址
	private String sdate; //发货日期
	private String rdate; //收货日期
	private Integer status;// 订单状态
	private double priceAll ;// 订单总额
	private Integer invoice ;//是否已开发票
	
	private List<OrderItemInfo> orderItem;

	@Override
	public String toString() {
		return "OrderInfo [ono=" + ono + ", odate=" + odate + ", ano=" + ano + ", sdate=" + sdate + ", rdate=" + rdate
				+ ", status=" + status + ", priceAll=" + priceAll + ", invoice=" + invoice + ", orderItem=" + orderItem
				+ "]";
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getPriceAll() {
		return priceAll;
	}

	public void setPriceAll(Double priceAll) {
		this.priceAll = priceAll;
	}

	public Integer getInvoice() {
		return invoice;
	}

	public void setInvoice(Integer invoice) {
		this.invoice = invoice;
	}

	public List<OrderItemInfo> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemInfo> orderItem) {
		this.orderItem = orderItem;
	}

	public OrderInfo(String ono, String odate, Integer ano, String sdate, String rdate, Integer status, Double priceAll,
			Integer invoice, List<OrderItemInfo> orderItem) {
		super();
		this.ono = ono;
		this.odate = odate;
		this.ano = ano;
		this.sdate = sdate;
		this.rdate = rdate;
		this.status = status;
		this.priceAll = priceAll;
		this.invoice = invoice;
		this.orderItem = orderItem;
	}

	public OrderInfo() {
		super();
	}
	
	
	
}
