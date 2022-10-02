package com.javalec.dto;

public class HelpDto {

	int product_id;
	String product_name;
	int product_stock;
	int product_price;
	
	
	public HelpDto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public HelpDto(int product_id, String product_name, int product_price, int product_stock) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_stock = product_stock;
		this.product_price = product_price;
	}


	public HelpDto(String product_name, int product_price) {
		super();
		this.product_name = product_name;
		this.product_price = product_price;
	}


	public HelpDto(int product_id) {
		super();
		this.product_id = product_id;
	}


	public HelpDto(String product_name, int product_price, int product_stock) {
		super();
		this.product_name = product_name;
		this.product_stock = product_stock;
		this.product_price = product_price;
	}
	
	
	
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_stock() {
		return product_stock;
	}
	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	
	

	
	
	
	
}
