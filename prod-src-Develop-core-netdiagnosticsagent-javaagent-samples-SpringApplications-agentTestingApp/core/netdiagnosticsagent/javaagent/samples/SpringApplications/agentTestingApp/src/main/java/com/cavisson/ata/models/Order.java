package com.cavisson.ata.models;

//Completable future
public class Order {

	int orderId;
	String orderAdd;
	int orderItemQty;
	float orderPrice;

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Order(int orderId, String orderAdd, int orderItemQty) {
		super();
		this.orderId = orderId;
		this.orderAdd = orderAdd;
		this.orderItemQty = orderItemQty;
	}

	public String getOrderAdd() {
		return orderAdd;
	}

	public void setOrderAdd(String orderAdd) {
		this.orderAdd = orderAdd;
	}

	public int getOrderItemQty() {
		return orderItemQty;
	}

	public void setOrderItemQty(int orderItemQty) {
		this.orderItemQty = orderItemQty;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Order id : " + this.orderId + " , Order QTY : " + this.orderItemQty + " , Order Price : "
				+ this.orderPrice + " , Order Address : " + this.orderAdd;
	}
}