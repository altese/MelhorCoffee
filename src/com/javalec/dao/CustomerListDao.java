package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.util.DBConnect;

public class CustomerListDao {

	public void Dao() {
		// TODO Auto-generated method stub

	}

	String customer_id;
	String customer_pw;

	public CustomerListDao(String customer_id, String customer_pw) {
		super();
		this.customer_id = customer_id;
		this.customer_pw = customer_pw;

	}

	public CustomerListDao(String customer_id) {
		super();
		this.customer_id = customer_id;
	}

	public int insertAction() {
		PreparedStatement ps = null;
		int check = 0;
		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 아직 실행 단계 x)

			// 쿼리 문장 만들기 (preparestatement)

			String query = " insert into customer (customer_id, customer_pw, customer_date) "; 
																							
			String query2 = " values (?,?,now()) ";


			// 위의 쿼리 문장대로 순서대로 쓴다.
			ps = conn_mysql.prepareStatement(query + query2);

			ps.setString(1, customer_id);
			ps.setString(2, customer_pw);

			check = ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return check;
		}
		return check;
	}

	
	// 아이디 중복 체크
	public int customer_idCheck(String customer_id) {
		int check = 0;
		String query1 = "select count(*) from customer ";
		String query2 = "where customer_id = '" + customer_id + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);

			while (rs.next()) {
				check = rs.getInt(1);
				}
			

			

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return check;
		}
		return check;
	}

}
