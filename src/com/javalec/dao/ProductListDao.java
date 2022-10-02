package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.HelpDto;
import com.javalec.dto.ProductListDto;
import com.javalec.util.DBConnect;

public class ProductListDao {

	int product_id;
	String product_name;
	String productSelect_name;
	String product_data;
	int product_price;
	int product_stock;
	
	public ProductListDao() {
		// TODO Auto-generated constructor stub
	}

	
	
	public ProductListDao(int product_id) {
		super();
		this.product_id = product_id;
	}



	public ProductListDao(String product_name, int product_price, int product_stock) {
		super();
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_stock = product_stock;
	}
	
	
	public ProductListDao(String product_data) {
		super();
		this.product_data = product_data;
	}
	

	// 조건 검색 결과를 Table로
	public ArrayList<ProductListDto> productConditionList(){
		
		ArrayList<ProductListDto> productList = new ArrayList<ProductListDto>();
		String whereStatement = "select product_name, product_price, product_stock, product_id from product";
		String whereStatement2 = " where product_name like '%" + product_data + "%' and product_stock > 0";	
		
		try {	//error확인 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql); //database에 접근을 하겠다. (선언자, 실행 x)
			Statement stmt_mysql = conn_mysql.createStatement(); // Connection conn_mysql 인스턴스를 이용해서 Statement 객체 생성
			
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);
			
			while(rs.next()) {
				
				String product_name = rs.getString(1);
				int product_price = rs.getInt(2);
				int product_stock = rs.getInt(3);
				int product_id = rs.getInt(4);
				
				ProductListDto dto = new ProductListDto(product_name, product_price, product_stock, product_id);
				HelpDto dto2 = new HelpDto(product_id);
				productList.add(dto);
				
			}
			
			conn_mysql.close(); 		// close 해야 다른 사람의 DB도 들어올 수 있다.
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return productList;
	}
	
	
	
		
		
	
} // End Line
