package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.javalec.dao.HelpDao;
import com.javalec.dto.HelpDto;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Help extends JDialog {
	private JLabel lblProductName;
	private JButton btnOk;
	private JFrame frmDialog;
	private JComboBox cbStock;
	private JLabel lblproductPrice;
	private JLabel lblProductPrice2;
	

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
		getContentPane().add(getBtnOk());
		getContentPane().add(getCbStock());
		getContentPane().add(getLblproductPrice());
		getContentPane().add(getLblProductPrice2());

	}

	private JLabel getLblProductName() {
		if (lblProductName == null) {
			lblProductName = new JLabel("제품명");
			lblProductName.setBounds(161, 40, 103, 15);
		}
		return lblProductName;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("New button");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					productSelectList project = new productSelectList();
					project.setVisible(true);
					
					
				}
			});
			btnOk.setBounds(318, 230, 91, 23);
		}
		return btnOk;
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

//					HelpDto dto = new HelpDto();
//					System.out.println("getid: "+ dto.getProduct_id());
					productOrder2();
					
				}
			});
			
			cbStock.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}));
			cbStock.setBounds(161, 98, 103, 23);
		}
		return cbStock;
	}
	
	
	public void productOrder(int wkSequence) {
		System.out.println("1");
		HelpDao dao = new HelpDao(wkSequence);
		HelpDto dto = dao.tableClick();
		
		lblProductName.setText(dto.getProduct_name());
		lblProductPrice2.setText(Integer.toString(dto.getProduct_price()));
		lblproductPrice.setText(Integer.toString(dto.getProduct_price()));
		
	}

	public void productOrder2() {
		lblproductPrice.setText(Integer.toString(Integer.parseInt((String)lblProductPrice2.getText()) * Integer.parseInt((String)cbStock.getSelectedItem())));
		
	}

} // End Line
