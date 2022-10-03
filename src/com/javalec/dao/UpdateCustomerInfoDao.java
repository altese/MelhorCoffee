package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.javalec.util.DBConnect;

public class UpdateCustomerInfoDao {

	String customer_id;
	String customer_pw;
//	String customer_changedate;

	public UpdateCustomerInfoDao(String customer_id, String customer_pw) {
		super();
		this.customer_id = customer_id;
		this.customer_pw = customer_pw;
	}

	public Boolean updateAction() {

		PreparedStatement ps = null;

		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 아직 실행 단계 x)

			// 쿼리 문장 만들기 (preparestatement)
			String query = "update customer set customer_pw = ?, customer_changedate = now() ";
					

			String query2 = " where customer_id = ? ";
			

			// 위의 쿼리 문장대로 순서대로 쓴다.
			ps = conn_mysql.prepareStatement(query + query2);// insert(), value()

			ps.setString(1, customer_pw);

			ps.setString(2, customer_id); // query2를 위함

			ps.executeUpdate(); // insert update method 이거 하나밖에 없다

			conn_mysql.close(); // close 해야 다른 사람의 DB도 들어올 수 있다.


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
