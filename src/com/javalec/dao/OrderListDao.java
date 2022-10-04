package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.OrderListDto;
import com.javalec.util.DBConnect;
import com.javalec.util.Static_CustomerId;

public class OrderListDao {
	
	//fields
	String product_name;
	int order_quantity;
	int order_price;
	
	//constructor
	public OrderListDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	//method
	public ArrayList<OrderListDto> selectList() {

		ArrayList<OrderListDto> dtoList = new ArrayList<OrderListDto>(); 


		String whereStatement = "select order_id, product_name, order_quantity, order_price ";
		String whereStatement2 = "from product p, orders o, customer c ";
		String whereStatement3 = "where c.customer_id = " + Static_CustomerId.customer_id + " and o.product_id = p.product_id and o.customer_id = c.customer_id";
		

		// 검색 포맷
		try { // error확인
			// connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			//
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2 + whereStatement3);
			// 1. 데이터값 입력
			while (rs.next()) { 

				int seqno = rs.getInt(1);
				String wkName = rs.getString(2); 
				int wkQuantity = rs.getInt(3);
				int wkPrice = rs.getInt(4);

				// 2. 입력된 데이터 DTO클래스에 저장
				OrderListDto orderListDto = new OrderListDto(seqno, wkName, wkQuantity, wkPrice); // 가져온 데이터 묶는 역할

				dtoList.add(orderListDto); // 2. 입력된 데이터 dtoList에도 저장
			}

			conn_mysql.close(); // close 해야 다른 사람의 DB도 들어올 수 있다.

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dtoList;

	}

}
