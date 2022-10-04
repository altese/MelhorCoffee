package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.ProductListDao;
import com.javalec.dto.CustomerListDto;

//import com.javalec.dto.ProductOrderDto;
import com.javalec.base.Static_CustomerId;

import com.javalec.dto.HelpDto;

import com.javalec.dto.ProductListDto;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;


public class productSelectList extends JFrame {


	private JFrame frmDialog;
	private JLabel product_name;
	private JTextField tfShoesSelect;
	private JButton btnShoesSelect;
	private JScrollPane scrollPane;
	private JTable Inner_Table;
	private final DefaultTableModel Outer_Table = new DefaultTableModel();
	private JLabel lblCustomer_id;
	private JButton btnNewButton;
	private JButton btnMyPageUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					productSelectList window = new productSelectList();
					window.frmDialog.setVisible(true);
					window.frmDialog.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public productSelectList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmDialog = new JFrame();
		frmDialog.setTitle("제품 검색");
		frmDialog.addWindowListener(new WindowAdapter() {

			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				conditionQueryAction();
			}
		});
		frmDialog.setBounds(100, 100, 450, 300);
		frmDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDialog.getContentPane().setLayout(null);
		frmDialog.getContentPane().add(getProduct_name());
		frmDialog.getContentPane().add(getTfShoesSelect());
		frmDialog.getContentPane().add(getBtnShoesSelect());
		frmDialog.getContentPane().add(getScrollPane());
		frmDialog.getContentPane().add(getLblCustomer_id());

		frmDialog.getContentPane().add(getBtnNewButton());
	}


//		frmDialog.getContentPane().add(getBtnMyPageUpdate());
	

	private JLabel getProduct_name() {
		if (product_name == null) {
			product_name = new JLabel("신발 이름");
			product_name.setBounds(35, 46, 64, 22);
		}
		return product_name;
	}

	private JTextField getTfShoesSelect() {
		if (tfShoesSelect == null) {
			tfShoesSelect = new JTextField();
			tfShoesSelect.setBounds(103, 47, 197, 21);
			tfShoesSelect.setColumns(10);
		}
		return tfShoesSelect;
	}

	private JButton getBtnShoesSelect() {
		if (btnShoesSelect == null) {
			btnShoesSelect = new JButton("검색");
			btnShoesSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {




					
					tableInit();
					conditionQueryAction();
					

				}
			});
			btnShoesSelect.setBounds(320, 46, 91, 23);
		}
		return btnShoesSelect;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(35, 79, 377, 161);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}

	private JTable getInner_Table() {
		if (Inner_Table == null) {
			Inner_Table = new JTable();
			Inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					
					if(e.getButton() == 1) {
						tableClick();
					}
					

				}
			});
			Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			Inner_Table.setModel(Outer_Table);
		}

		
		return Inner_Table;
	}
	

	private JLabel getLblCustomer_id() {
		if (lblCustomer_id == null) {
			lblCustomer_id = new JLabel("New label");
			lblCustomer_id.setBounds(35, 21, 140, 15);
		}
		return lblCustomer_id;
	}


//	private JButton getBtnMyPageUpdate() {
//		if (btnMyPageUpdate == null) {
//			btnMyPageUpdate = new JButton("내 정보 수정하기");
//			btnMyPageUpdate.setBounds(278, 10, 133, 23);
//		}
//		return btnMyPageUpdate;
//	}
	
	// ------------------------------------------------------------------------------------------
	
	// Init the table
	public void tableInit() {

		Outer_Table.addColumn("순번");
		Outer_Table.addColumn("이름");
		Outer_Table.addColumn("가격");
		Outer_Table.addColumn("수량");


		
		Outer_Table.setColumnCount(4);
		
		int i = Outer_Table.getRowCount();
		
		for(int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}
		
		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);
		

		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
		int width = 30;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 140;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 124;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 80;
		col.setPreferredWidth(width);


	}

	// 재품 리스트 출력
	public void conditionQueryAction() {
		
		ProductListDao dao = new ProductListDao(tfShoesSelect.getText().trim());
		ArrayList<ProductListDto> dtoList = dao.productConditionList();
		
		int listCount = dtoList.size();
		
		lblCustomer_id.setText(Static_CustomerId.customer_id + "님 환영합니다.");
		for(int i = 0; i < listCount; i++) {
			String temp = Integer.toString(dtoList.get(i).getProduct_id());
			String[] qTxt = {temp, dtoList.get(i).getProduct_name(), Integer.toString(dtoList.get(i).getProduct_price()), Integer.toString(dtoList.get(i).getProduct_stock())};
			Outer_Table.addRow(qTxt);
		}
		
	}
	
	// 재품 리스트 중 원하는 재품 클릭 시 구매하기 창으로 이동
	private void tableClick() {
		
		int i = Inner_Table.getSelectedRow(); // 몇번째 줄 인지 알려줌
		String wkSequence = (String) Inner_Table.getValueAt(i, 0); // i번째 행의 0번째(Seqno) 값을 wkSequence에 넣어줌
		ProductListDao dao = new ProductListDao(Integer.parseInt(wkSequence));
		
		Help help = new Help();
		help.productOrderList(Integer.parseInt(wkSequence));
		help.setVisible(true);	// 리스트 클릭 시 다음 페이지로 넘어감
		frmDialog.setVisible(false);
		
	}

	// 주현 -- 내정보수정버튼
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("내정보수정");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

//					UpdateCustomerInfo updateCustomerInfo = new UpdateCustomerInfo();
					UpdateCustomerInfo.main(null);
//					JFrame jFrame = new JFrame();
					frmDialog.setVisible(false);
				}
			});
			btnNewButton.setBounds(305, 5, 117, 29);
		}
		return btnNewButton;
	}

	// ----주현
	

}// End Line
