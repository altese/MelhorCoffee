package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.javalec.dao.UpdateCustomerInfoDao;
import com.javalec.util.Static_CustomerId;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UpdateCustomerInfo {

	private JFrame frame;
	private JLabel lblid;
	private JLabel lblpw;
	private JTextField tfId;
	private JTextField tfPw;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomerInfo window = new UpdateCustomerInfo();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateCustomerInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				getCustomerId();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblid());
		frame.getContentPane().add(getLblpw());
		frame.getContentPane().add(getTfId());
		frame.getContentPane().add(getTfPw());
		frame.getContentPane().add(getBtnUpdate());
	}
	private JLabel getLblid() {
		if (lblid == null) {
			lblid = new JLabel("ID:");
			lblid.setBounds(75, 52, 61, 16);
		}
		return lblid;
	}
	private JLabel getLblpw() {
		if (lblpw == null) {
			lblpw = new JLabel("PW:");
			lblpw.setBounds(75, 93, 61, 16);
		}
		return lblpw;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEditable(false);
			tfId.setBounds(195, 47, 130, 26);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfPw() {
		if (tfPw == null) {
			tfPw = new JTextField();
			tfPw.setBounds(195, 88, 130, 26);
			tfPw.setColumns(10);
		}
		return tfPw;
	}
	private JButton getBtnUpdate() {
		if (btnUpdate == null) {
			btnUpdate = new JButton("수정");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = updateFieldCheck();
					if(i_chk == 0) {
						updateAction();
					}
				}
			});
			btnUpdate.setBounds(294, 199, 117, 29);
		}
		return btnUpdate;
	}
	
	private void getCustomerId() {
		
		tfId.setText(Static_CustomerId.customer_id);
	}
	
	private void updateAction() {

		String customer_id = tfId.getText();
		String customer_pw = tfPw.getText();
//		String customer_changedate = "";

		UpdateCustomerInfoDao dao = new UpdateCustomerInfoDao(customer_id, customer_pw);

		Boolean ok = dao.updateAction();

		if (ok == true) {

			JOptionPane.showMessageDialog(null, tfId.getText() + "님의 정보가 수정되었습니다.");

			Static_CustomerId.setCustomer_id(customer_id);

			LogIn.main(null);
			frame.setVisible(false);

		} else {
			JOptionPane.showMessageDialog(null, "작업중 문제발생했습니다.");

		}

	}
	private int updateFieldCheck() {
		int i = 0;
		String message = "";

		if (tfId.getText().trim().length() == 0) {
			i++;
			message = "아이디를 ";
			tfId.requestFocus();

		} else if (tfPw.getText().trim().length() == 0) {
			i++;
			message = "비밀번호를 ";
			tfPw.requestFocus();
		}

		if (i > 0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
		}

		return i;

	}
	

	
}
