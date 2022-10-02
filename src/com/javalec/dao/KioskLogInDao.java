package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.dto.CustomerListDto;
import com.javalec.util.DBConnect;

public class KioskLogInDao {

	// ----------------------fields-----------------------------
	String custId;
	String custPw;

	// -----------------------constructor------------------------
	public KioskLogInDao() {
		// TODO Auto-generated constructor stub
	}

	public KioskLogInDao(String customer_id, String customer_pw) {
		super();
		this.custId = customer_id;
		this.custPw = customer_pw;
	}

	// --------------------------method--------------------------

	public int loginCheck() {
		int check = 0;
		String query1 = "select count(*) from customer ";
		String query2 = "where customer_id = '" + custId + "'  and customer_pw = '" + custPw + "'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(query1 + query2);

			while (rs.next()) {
				if(Integer.parseInt(rs.getString(1)) == 1) {
					check++;
					
				}
				
			}
			
		
			
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return check;
		}
		return check;

	}

}// .
