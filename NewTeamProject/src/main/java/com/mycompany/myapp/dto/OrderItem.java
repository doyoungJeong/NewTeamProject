package dao;

public class OrderItem {
	private int orderNo;
	private int productNo;
	private String orderItemName;
	private int orderItemAmount;
	private int orderItemPrice;
	
	private String total_price ;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getOrderItemName() {
		return orderItemName;
	}
	public void setOrderItemName(String orderItemName) {
		this.orderItemName = orderItemName;
	}
	public int getOrderItemAmount() {
		return orderItemAmount;
	}
	public void setOrderItemAmount(int orderItemAmount) {
		this.orderItemAmount = orderItemAmount;
	}
	public int getOrderItemPrice() {
		return orderItemPrice;
	}
	public void setOrderItemPrice(int orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}
	
	
	public void setOrderItemtotalPrice(String orderItemtotalPrice) {
		this.total_price = orderItemtotalPrice;
	}
	public String getOrderItemtotalPrice() {
		return total_price;
	}
	
	
}
