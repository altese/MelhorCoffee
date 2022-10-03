package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.javalec.dao.KioskLogInDao;
import com.javalec.dto.CustomerListDto;
import com.javalec.util.Static_CustomerId;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class LogIn extends JDialog {

	private JFrame frame;
	private JTextField tfIdInsert;
	private JTextField tfPwInsert;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
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
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("로그인");
		lblNewLabel.setBounds(35, 34, 78, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setBounds(67, 98, 50, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		tfIdInsert = new JTextField();
		tfIdInsert.setBounds(154, 95, 205, 21);
		frame.getContentPane().add(tfIdInsert);
		tfIdInsert.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("PW:");
		lblNewLabel_1_1.setBounds(67, 148, 50, 15);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		tfPwInsert = new JTextField();
		tfPwInsert.setColumns(10);
		tfPwInsert.setBounds(154, 145, 205, 21);
		frame.getContentPane().add(tfPwInsert);
		
		JButton btnLogIn = new JButton("LOG IN");
		// ************************** 로그인 버튼 이벤트 ********************************
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String custId = tfIdInsert.getText().trim();
				String custPw = tfPwInsert.getText().trim();
				
				KioskLogInDao kioskLogInDao = new KioskLogInDao(custId, custPw);
				
				int loginCheck = kioskLogInDao.loginCheck();
				
				if(loginCheck == 1) {
					JOptionPane.showMessageDialog(null, "로그인되었습니다.");
					
					Static_CustomerId.setCustomer_id(custId);
					
					frame.setVisible(false);
					productSelectList.main(null);
					
				}else {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요.");
					System.out.println(loginCheck);
				}
				
			}
		});
		btnLogIn.setBounds(320, 209, 91, 23);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(256, 40, 78, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("계정이 없다면...");
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setBounds(215, 40, 91, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("가입하기 ");
		//***********************회원 가입 버튼 이벤트*****************************
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Shoes_CustomerJoin shoes_CustomerJoin = new Shoes_CustomerJoin();
				shoes_CustomerJoin.main(null);
			}
		});
		btnNewButton.setBounds(320, 36, 91, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
