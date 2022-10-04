package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.dao.OrderListDao;
import com.javalec.dto.OrderListDto;

import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderList extends JFrame {

	private JFrame frame;
	private JTable Inner_Table;
	private JButton btnNewButton;
	//***************************Outer_Table*********************
	private final DefaultTableModel Outer_Table = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderList window = new OrderList();
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
	public OrderList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//*************************윈도우 이벤트*******************
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl = new JLabel("주문 내역");
		lbl.setBounds(24, 24, 79, 15);
		frame.getContentPane().add(lbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 49, 381, 162);
		frame.getContentPane().add(scrollPane);
		//*********************테이블 세팅*******************************
		Inner_Table = new JTable();
		scrollPane.setViewportView(Inner_Table);
		
		Inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Inner_Table.setModel(Outer_Table);
		
		btnNewButton = new JButton("뒤로 가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backPage();
			}
		});
		btnNewButton.setBounds(314, 221, 91, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	//==============================메소드============================
	
	//TableInit()
	private void tableInit() {
		Outer_Table.addColumn("주문 번호");
		Outer_Table.addColumn("상품명");
		Outer_Table.addColumn("수량");
		Outer_Table.addColumn("가격");

		Outer_Table.setColumnCount(4);

		int i = Outer_Table.getRowCount();
		for (int j = 0; j < i; j++) {
			Outer_Table.removeRow(0);
		}

		Inner_Table.setAutoResizeMode(Inner_Table.AUTO_RESIZE_OFF);

		int vColIndex = 0;
		TableColumn col = Inner_Table.getColumnModel().getColumn(vColIndex);
		int width = 100;
		col.setPreferredWidth(width);

		vColIndex = 1;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);

		vColIndex = 2;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 90;
		col.setPreferredWidth(width);

		vColIndex = 3;
		col = Inner_Table.getColumnModel().getColumn(vColIndex);
		width = 100;
		col.setPreferredWidth(width);

	}
	
	//searchAction()
	private void searchAction() {

		OrderListDao orderListDao = new OrderListDao(); 
		ArrayList<OrderListDto> dtoList = orderListDao.selectList(); // 타입 = 실행 메소드 4. arraylist 형식으로 가져와서, 해당 Dao method 실행

		int listCount = dtoList.size(); // 데이터의 열의 수를 나타냄

		// 위의 데이터 행의 수만큼 정보 출력
		for (int index = 0; index < listCount; index++) {
//			String temp = Integer.toString(Static_CustomerId.producdt_id); // temp: seqno 할당
			String[] qTxt = { Integer.toString(dtoList.get(index).getOrder_id()), dtoList.get(index).getProduct_name(), Integer.toString(dtoList.get(index).getOrder_quantity()),
					Integer.toString(dtoList.get(index).getOrder_price()) }; // 1행의 박스 할당
			Outer_Table.addRow(qTxt); // 출력
		}

	}
	
	//backpage()
	public void backPage() {
		productSelectList.main(null);
//		boolean OrderList = new OrderList() == null;
		setVisible(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// END
