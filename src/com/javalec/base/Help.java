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
	private JFrame frame;
	private JComboBox cbStock;
	private JLabel lblproductPrice;
	private JLabel lblProductPrice2;
	private JLabel lblProductId;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnBackPage;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help window = new Help();
					window.frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Dialog");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	/**
	 * Create the dialog.
	 */
	public Help() {
		setTitle("Help");
		setBounds(100, 100, 358, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getLblProductName());
		getContentPane().add(getBtnOrder());
		getContentPane().add(getCbStock());
		getContentPane().add(getLblproductPrice());
		getContentPane().add(getLblProductPrice2());
		getContentPane().add(getLblProductId());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getBtnBackPage());

	}

	private JLabel getLblProductName() {
		if (lblProductName == null) {
			lblProductName = new JLabel("제품명");
			lblProductName.setBounds(175, 38, 103, 15);
		}
		return lblProductName;
	}
	private JButton getBtnOrder() {
		if (btnOrder == null) {
			btnOrder = new JButton("구매하기");
			btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					productStockCheck();
					
				}
			});
			btnOrder.setBounds(220, 212, 91, 30);
		}
		return btnOrder;
	}
	
	private JLabel getLblproductPrice() {
		if (lblproductPrice == null) {
			lblproductPrice = new JLabel("가격");
			lblproductPrice.setBounds(175, 122, 103, 23);
		}
		return lblproductPrice;
	}
	
	private JLabel getLblProductPrice2() {
		if (lblProductPrice2 == null) {
			lblProductPrice2 = new JLabel("");
			lblProductPrice2.setVisible(false);
			lblProductPrice2.setBounds(261, 202, 50, 15);
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
			cbStock.setBounds(175, 77, 103, 23);
		}
		return cbStock;
	}
	
	private JLabel getLblProductId() {
		if (lblProductId == null) {
			lblProductId = new JLabel("");
			lblProductId.setBounds(261, 227, 50, 15);
			lblProductId.setVisible(false);
		}
		return lblProductId;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("제품명 :");
			lblNewLabel.setBounds(113, 38, 50, 15);
		}
		return lblNewLabel;
	}
	
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("수량 :");
			lblNewLabel_1.setBounds(113, 81, 50, 15);
		}
		return lblNewLabel_1;
	}
	
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("가격 :");
			lblNewLabel_2.setBounds(113, 126, 50, 15);
		}
		return lblNewLabel_2;
	}
	
	private JButton getBtnBackPage() {
		if (btnBackPage == null) {
			btnBackPage = new JButton("이전 페이지");
			btnBackPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					backPage();
				}
			});
			btnBackPage.setBounds(105, 212, 103, 30);
		}
		return btnBackPage;
	}
	
	// ---------------------------------------------------------------------------------------
	
	// 선택한 상품을 구매하기 위한 구매 목록 출력
	public void productOrderList(int wkSequence) {
		
		HelpDao dao = new HelpDao(wkSequence);
		HelpDto dto = dao.tableClick();
		
		lblProductId.setText(Integer.toString(dto.getProduct_id()));
		lblProductName.setText(dto.getProduct_name());
		lblProductPrice2.setText(Integer.toString(dto.getProduct_price()));
		lblproductPrice.setText(Integer.toString(dto.getProduct_price()));
		
		// productStockCheck 메소드에서 필요한 재품코드 넘겨주기
		Static_CustomerId.setProducdt_id(dto.getProduct_id());
		
	}

	// 상품의 남은 수량을 확인하고 고객이 고른 상품 수량과 비교하는 메소드
	private void productStockCheck() {
		HelpDao dao = new HelpDao(Static_CustomerId.producdt_id);
		HelpDto dto = dao.productStockCheck();
		
		if(dto.getProduct_id() < Integer.parseInt((String)cbStock.getSelectedItem())) {
			// 남은 재고가 고객이 고른 수량보다 적을 때
			JOptionPane.showMessageDialog(null, "남은 재고가 " + dto.getProduct_id() + "개 입니다. \n" + dto.getProduct_id() + "개 이하로 선택하세요.");
			
		}else {
			// 고객이 고른 수량이 구매가 가능할 때
			productOrder();
			
		}
	}
	
	// 구매하기 버튼을 누르면 orders 테이블에 insert 해주는 메소드
	private void productOrder() {
		
		int product_id = Integer.parseInt(lblProductId.getText().trim());
		int product_price = Integer.parseInt(lblproductPrice.getText().trim());
		int product_stock = Integer.parseInt((String)cbStock.getSelectedItem());
		
		HelpDao dao = new HelpDao(Static_CustomerId.customer_id, product_id, product_stock, product_price);
		int check = dao.productOrdersAction();
		
		if(check == 1) {
			JOptionPane.showMessageDialog(null, "상품 구매가 완료 되었습니다.");
			dao.productStockOut(product_id, product_stock);
			
			// 10/03 한별수정 - 상품 구매 후 신발 리스트 출력 페이지로 이동,
			// 구매 완료 후 상품 리스트로 돌아가는 문장
			productSelectList.main(null);
			boolean help = new Help() == null;
			setVisible(help);
			// ------------------------------------------
			
		} else {
			JOptionPane.showMessageDialog(null, "DB 작업중 문제가 발생했습니다. \n행정실로 문의 하세요!");
		}
	}

	
	// 구매 창에서 콤보박스에서 고른 수량 만큼 totalprice에 값 변경해주는 메소드
	public void productOrder2() {
		lblproductPrice.setText(Integer.toString(Integer.parseInt((String)lblProductPrice2.getText()) * Integer.parseInt((String)cbStock.getSelectedItem())));
		
	}
	
	public void backPage() {
		productSelectList.main(null);
		boolean help = new Help() == null;
		setVisible(help);
	}
	
} // End Line
