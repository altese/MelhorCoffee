package com.javalec.dto;



public class CustomerListDto {

	//field
	String customer_id;
	String customer_pw;
	

	public CustomerListDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomerListDto(String customer_id) {
		super();
		this.customer_id = customer_id;
	}

	
	//constructor
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_pw() {
		return customer_pw;
	}
	public void setCustomer_pw(String customer_pw) {
		this.customer_pw = customer_pw;
	}
	
	


	
	
	
	
	
}