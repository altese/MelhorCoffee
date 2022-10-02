package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.javalec.dto.HelpDto;
import com.javalec.util.DBConnect;

public class HelpDao {

	int seqno;

	public HelpDao() {
		// TODO Auto-generated constructor stub
	}
	
	public HelpDao(int seqno) {
		super();
		this.seqno = seqno;
	}

	public HelpDto tableClick() {
		
		HelpDto dto = null;

		String whereStatement = "select product_name, product_price, product_stock from product ";
		String whereStatement2 = "where product_id = " + seqno;

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

				String product_name = rs.getString(1);
				int product_price = rs.getInt(2);
				int product_stock = rs.getInt(3);

				dto = new HelpDto(product_name, product_price, product_stock);

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
		String whereStatement2 = "where product_id = " + seqno;

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
	
}
