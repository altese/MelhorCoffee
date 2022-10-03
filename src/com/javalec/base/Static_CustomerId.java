package com.javalec.base;

public class Static_CustomerId {

	public static String customer_id;
	public static int producdt_id;
	
	public Static_CustomerId() {
		// TODO Auto-generated constructor stub
	}

	
	
	public static int getProducdt_id() {
		return producdt_id;
	}
	public static void setProducdt_id(int producdt_id) {
		Static_CustomerId.producdt_id = producdt_id;
	}

	public static String getCustomer_id() {
		return customer_id;
	}
	public static void setCustomer_id(String customer_id) {
		Static_CustomerId.customer_id = customer_id;
	}
	
	
	
}
