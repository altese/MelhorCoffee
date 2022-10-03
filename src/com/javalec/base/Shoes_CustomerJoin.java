package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.javalec.dao.CustomerListDao;
//import com.javalec.dao.Dao;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;


public class Shoes_CustomerJoin {

public class Shoes_CustomerJoin extends JFrame {


	private JFrame frame;
	private JLabel lblid;
	private JLabel lblpw;
	private JTextField tfidinsert;
	private JTextField tfpwinsert;
	private JButton btnjoin;

	private JButton btnBackPage;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shoes_CustomerJoin window = new Shoes_CustomerJoin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Shoes_CustomerJoin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("불사조 회원가입");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblid());
		frame.getContentPane().add(getLblpw());
		frame.getContentPane().add(getTfidinsert());
		frame.getContentPane().add(getTfpwinsert());
		frame.getContentPane().add(getBtnjoin());

		frame.getContentPane().add(getBtnBackPage());

	}

	private JLabel getLblid() {
		if (lblid == null) {
			lblid = new JLabel("ID:");
			lblid.setBounds(80, 50, 61, 16);
		}
		return lblid;
	}
	private JLabel getLblpw() {
		if (lblpw == null) {
			lblpw = new JLabel("PW:");
			lblpw.setBounds(80, 103, 61, 16);
		}
		return lblpw;
	}
	private JTextField getTfidinsert() {
		if (tfidinsert == null) {
			tfidinsert = new JTextField();
			tfidinsert.setBounds(167, 45, 130, 26);
			tfidinsert.setColumns(10);
		}
		return tfidinsert;
	}
	private JTextField getTfpwinsert() {
		if (tfpwinsert == null) {
			tfpwinsert = new JTextField();
			tfpwinsert.setBounds(167, 98, 130, 26);
			tfpwinsert.setColumns(10);
		}
		return tfpwinsert;
	}
	private JButton getBtnjoin() {
		if (btnjoin == null) {
			btnjoin = new JButton("가입");
			btnjoin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int check = insertFieldCheck();
					if(check == 0) {
						insertAction();
					}
				}
			});
			btnjoin.setBounds(305, 185, 117, 29);
		}
		return btnjoin;
	}
	

	private JButton getBtnBackPage() {
		if (btnBackPage == null) {
			btnBackPage = new JButton("이전 페이지");
			btnBackPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					backPage();
				}
			});
			btnBackPage.setBounds(167, 188, 117, 29);
		}
		return btnBackPage;
	}

	
	
	
	private void insertAction() {
		
		String customer_id = tfidinsert.getText();
		String customer_pw = tfpwinsert.getText();
		
		
		CustomerListDao dao = new CustomerListDao(customer_id, customer_pw);
		
		int insert = dao.insertAction();
		
		
		if(insert == 1) {	
			JOptionPane.showMessageDialog(null, "가입을 환영합니다.");

			frame.setVisible(false);
			LogIn.main(null);

		}
	
	}
	
	
	private int insertFieldCheck() {
		int i = 0;
		String message = "";
		
		if(tfidinsert.getText().trim().length() == 0) {
			i++;
			message = "아이디를 ";
			tfidinsert.requestFocus();
			
		}else if(tfpwinsert.getText().trim().length() == 0) {
			i++;
			message = "비밀번호를 ";
			tfpwinsert.requestFocus();
		}
		
		if(i > 0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
		}
		
		return i;
		
	}
	

//	private void deleteAction() {
//		
//		//sequence 넘버 정수로 바꾸기 
//		String customer_id = tfidinsert.getText();
//		
//		Dao dao = new Dao(customer_id);					//연결 
//		
//		Boolean ok =  dao.deleteAction();			//리턴값 	
//		
//		if(ok == true) {
//			JOptionPane.showMessageDialog(null, tfidinsert.getText() + "님의 정보가 삭제되었습니다.");
//		}else {
//			JOptionPane.showMessageDialog(null, "Db 작업중 문제가 발생했습니다. \n행정실로 문의 하세요!");
//		}
//		
//	}
	
	
	

	private void backPage() {
		LogIn.main(null);
		frame.setVisible(false);
	}

	
}
