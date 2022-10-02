package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.javalec.dto.HelpDto;
import com.javalec.util.DBConnect;

public class HelpDao {

	int product_Id;
	String customer_id;
	int order_quantity;
	int order_price;
	
	public HelpDao() {
		// TODO Auto-generated constructor stub
	}
	
	



	public HelpDao(String customer_id, int product_Id, int order_quantity, int order_price) {
		super();
		this.product_Id = product_Id;
		this.customer_id = customer_id;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
	}



	public HelpDao(String customer_id, int order_quantity, int order_price) {
		super();
		this.customer_id = customer_id;
		this.order_quantity = order_quantity;
		this.order_price = order_price;
	}



	public HelpDao(int product_Id) {
		super();
		this.product_Id = product_Id;
	}

	public HelpDto tableClick() {
		
		HelpDto dto = null;

		String whereStatement = "select product_id, product_name, product_price, product_stock from product ";
		String whereStatement2 = "where product_id = " + product_Id;

		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 실행 x)
			Statement stmt_mysql = conn_mysql.createStatement(); // Connection conn_mysql 인스턴스를 이용해서 Statement 객체 생성

			/*
			 * ResultSet - 결과값을 저장할 수 있다 - 저장된 값을 한 행 단위로 불러올 수 있다 - 한 행에서 값을 가져올 때는 타입을 지정해
			 * 불러올 수 있다.
			 */
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) { // next를 통해 다른 행들을 선택할 수 있다.

				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int product_price = rs.getInt(3);
				int product_stock = rs.getInt(4);

				dto = new HelpDto(product_id, product_name, product_price, product_stock);

			}

			conn_mysql.close(); // close 해야 다른 사람의 DB도 들어올 수 있다.

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dto;
		
	}
	
	public HelpDto itemClick() {

		HelpDto dto = null;

		String whereStatement = "select product_name, product_price from product ";
		String whereStatement2 = "where product_id = " + product_Id;

		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 실행 x)
			Statement stmt_mysql = conn_mysql.createStatement(); // Connection conn_mysql 인스턴스를 이용해서 Statement 객체 생성

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {

				String product_name = rs.getString(1);
				int product_price = rs.getInt(2);

				dto = new HelpDto(product_name, product_price);

			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;

	}
	
	// 구매하기 버튼 클릭시 (상품 구매) orders 테이블에 데이터 입력
	public int productOrdersAction() {
		PreparedStatement ps = null;
		int check = 0;
		
		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 실행 x)
			Statement stmt_mysql = conn_mysql.createStatement(); // Connection conn_mysql 인스턴스를 이용해서 Statement 객체 생성

			// 쿼리 문장 만들기 (preparestatement)
			String query = "insert into orders (customer_id, product_id, order_date, order_quantity, order_price) "; // relation) 여기 띄어쓰기 있어야지 에러 안걸림
			String query1 = "values (?, ?, now(), ?, ?)";
			// 위의 쿼리 문장대로 순서대로 쓴다.
			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, customer_id);
			ps.setInt(2, product_Id);
			ps.setInt(3, order_quantity);
			ps.setInt(4, order_price);

			check = ps.executeUpdate(); // insert update method 이거 하나밖에 없다

			conn_mysql.close(); // close 해야 다른 사람의 DB도 들어올 수 있다.

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return check;
			
		}
		return check;
	}
	
	
	public boolean productStockOut(int product_id, int product_stock) {
		PreparedStatement ps = null;
		try {	//error확인 
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql); //database에 접근을 하겠다. (선언자, 실행 x)
			Statement stmt_mysql = conn_mysql.createStatement(); // Connection conn_mysql 인스턴스를 이용해서 Statement 객체 생성
			
			//쿼리 문장 만들기 (preparestatement)
			String query = 	"update product set product_stock = product_stock - ? ";	// 쿼리문 끝에 띄어쓰기 신경쓰기
			String query2 = "where product_id = ?";
			
			ps = conn_mysql.prepareStatement(query + query2);
			ps.setInt(1, product_stock);
			ps.setInt(2, product_id);
			
			ps.executeUpdate();			//insert update delete method 이거 하나밖에 없다
			
			conn_mysql.close(); 		// close 해야 다른 사람의 DB도 들어올 수 있다.
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
