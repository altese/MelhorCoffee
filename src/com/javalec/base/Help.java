package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.javalec.dao.HelpDao;
import com.javalec.dto.HelpDto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Help extends JDialog {
	private JLabel lblProductName;
	private JButton btnOrder;
	private JFrame frmDialog;
	private JComboBox cbStock;
	private JLabel lblproductPrice;
	private JLabel lblProductPrice2;
	private JLabel lblProductId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help dialog = new Help();
					dialog.frmDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.frmDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frmDialog = new JFrame();
		frmDialog.setTitle("Dialog");
		frmDialog.setBounds(100, 100, 450, 300);
		frmDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDialog.getContentPane().setLayout(null);
	}
	
	/**
	 * Create the dialog.
	 */
	public Help() {
		setTitle("Help");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getLblProductName());
		getContentPane().add(getBtnOrder());
		getContentPane().add(getCbStock());
		getContentPane().add(getLblproductPrice());
		getContentPane().add(getLblProductPrice2());
		getContentPane().add(getLblProductId());

	}

	private JLabel getLblProductName() {
		if (lblProductName == null) {
			lblProductName = new JLabel("제품명");
			lblProductName.setBounds(161, 70, 103, 15);
		}
		return lblProductName;
	}
	private JButton getBtnOrder() {
		if (btnOrder == null) {
			btnOrder = new JButton("구매하기");
			btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					productOrder();
					
				}
			});
			btnOrder.setBounds(318, 230, 91, 23);
		}
		return btnOrder;
	}
	
	private JLabel getLblproductPrice() {
		if (lblproductPrice == null) {
			lblproductPrice = new JLabel("가격");
			lblproductPrice.setBounds(161, 147, 103, 23);
		}
		return lblproductPrice;
	}
	
	private JLabel getLblProductPrice2() {
		if (lblProductPrice2 == null) {
			lblProductPrice2 = new JLabel("New label");
			lblProductPrice2.setVisible(false);
			lblProductPrice2.setBounds(326, 70, 50, 15);
		}
		return lblProductPrice2;
	}
	
	
	public JComboBox getCbStock() {
		if (cbStock == null) {
			cbStock = new JComboBox();
			cbStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					productOrder2();
					
				}
			});
			
			cbStock.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
			cbStock.setBounds(161, 98, 103, 23);
		}
		return cbStock;
	}
	
	private JLabel getLblProductId() {
		if (lblProductId == null) {
			lblProductId = new JLabel("New label");
			lblProductId.setBounds(326, 116, 50, 15);
			lblProductId.setVisible(false);
		}
		return lblProductId;
	}
	
	
	public void productOrderList(int wkSequence) {
		
		HelpDao dao = new HelpDao(wkSequence);
		HelpDto dto = dao.tableClick();
		
		lblProductId.setText(Integer.toString(dto.getProduct_id()));
		lblProductName.setText(dto.getProduct_name());
		lblProductPrice2.setText(Integer.toString(dto.getProduct_price()));
		lblproductPrice.setText(Integer.toString(dto.getProduct_price()));
		
	}

	private void productOrder() {
		
		int product_id = Integer.parseInt(lblProductId.getText().trim());
		int product_price = Integer.parseInt(lblproductPrice.getText().trim());
		int product_stock = Integer.parseInt((String)cbStock.getSelectedItem());
		
		HelpDao dao = new HelpDao(Static_CustomerId.customer_id, product_id, product_stock, product_price);
		int check = dao.productOrdersAction();
		
		if(check == 1) {
			JOptionPane.showMessageDialog(null, "상품 구매가 완료 되었습니다.");
			dao.productStockOut(product_id, product_stock);
		} else {
			JOptionPane.showMessageDialog(null, "DB 작업중 문제가 발생했습니다. \n행정실로 문의 하세요!");
		}
	}

	public void productOrder2() {
		lblproductPrice.setText(Integer.toString(Integer.parseInt((String)lblProductPrice2.getText()) * Integer.parseInt((String)cbStock.getSelectedItem())));
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // End Line
