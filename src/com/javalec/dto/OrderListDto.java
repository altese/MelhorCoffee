package com.javalec.dto;

public class OrderListDto {

	// fields
	int order_id;
	String product_name;
	int order_quantity;
	int order_price;

	// constructor
	public OrderListDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderListDto(int order_id, String product_name, int order_quantity, int order_price) {
		super();
		this.order_id = order_id;
		this.product_name = product_name;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
	}

	// getter&setter
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

}
