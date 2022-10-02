package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


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








	public int insertAction() {
		PreparedStatement ps = null;
		int check = 0;
		try { // error확인

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 아직 실행 단계 x)

			
			// 쿼리 문장 만들기 (preparestatement)
			String query = " insert into customer (customer_id, customer_pw) "; 
																							
			String query2 = " values (?,?) ";

			
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
	
	
	
//	// 삭제
//		public Boolean deleteAction() {
//			PreparedStatement ps = null; // 입력 수정 삭제
//
//			try { // error확인
//
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
//						DBConnect.pw_mysql); // database에 접근을 하겠다. (선언자, 아직 실행 단계 x)
//
//				// 쿼리 문장 만들기 (preparestatement)
//				String query = "delete from userinfo where seqno = ? "; // relation) 여기 띄어쓰기 있어야지 에러 안걸림
//
//				// 바로 위 쿼리 문장을 실행하기 위함
//				ps = conn_mysql.prepareStatement(query);// insert(), value()
//				ps.setInt(1, seqno); // trim 필요 없음, editable이 안됨,, integer.parseInt 문자열로바꿔
//				// setInt(): SQL Integer value로 바꿔줌
//
//				ps.executeUpdate(); // insert update, delete method 이거 하나밖에 없다 (셋 중에 하나 실행하는 메소드)
//
//				conn_mysql.close(); // close 해야 다른 사람의 DB도 들어올 수 있다.
//
////				JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 삭제되었습니다.");>>address class에 있어야 됨 
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				return false; /// what????
//			}
//			return true;
//		}
	
}
