import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;

public class Admin extends JFrame {

	// 하나로 합치고 관리자 모드 버튼 누르면 관리자 모드 class로 가도록 만들기
	// admin initial에 있는 database.txt 못찾는 것도 전체의 initialize 로 가도록만들기
	private JFrame admin;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	JLabel lblNewLabel_11 = new JLabel("(\uB9E4\uCD9C\uC561)");
	private JTable table_1;
	private JTable table_2;
	Management m;
	Main main = new Main();

	JFrame getAdmin() {
		return admin;
	}
	/**
	 * Launch the application.
	 */
	// 메인에서 관리자 모드 버튼을 눌렀을 때 창을 열게 만들었기 때문에 run()을 no body 함수로 만들기
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Admin() throws Exception {
		initialize_admin();

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Database.txt"));
		m = new Management(100, 100, 100, ois);

		// 저장된 정보가 처음부터 보이도록 함
		// goodsList 불러오기
		int goodsListLength = m.getGoodsList().size();

		for (int i = 0; i < goodsListLength; i++) {
			Goods g = m.getGoodsList().get(i);

			String price = Integer.toString(g.getPrice());
			String count = Integer.toString(g.getGoodsCount());

			String arr[] = new String[4];
			arr[0] = g.getGoodsName();
			arr[1] = g.getGoodsCode();
			arr[2] = price;
			arr[3] = count;

			// 테이블에 열 추가
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(arr);
			
			// 물품명을 기준으로 내림차순 정렬
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			table.setRowSorter(sorter);
			List <RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 0;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
		
		// userList 불러오기
		int userListLength = m.getUserList().size();
		
		for (int i = 0; i < userListLength; i++) {
			User u = m.getUserList().get(i);
			
			String arr[] = new String[8];
			arr[0] = u.getUserName();
			arr[1] = u.getPhoneNum();
			int rentalCount = u.getRentalCount();
			for(int j = 0; j < rentalCount; j++) {
				// goods의 이름으로 유저 리스트에 유저가 빌린 물품 보여주기
				arr[j + 2] =  m.getGoodsList().get(m.search(u.getRentalGoodsCode()[j])).getGoodsName();
			}
			arr[5] = u.getRentDate();
			arr[6] = u.getDueDate();
			arr[7] = Integer.toString(u.payCalculate());
			
			// 테이블에 열 추가
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(arr);
			
			// user 이름을 기준으로 내림차순 정렬
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_1.getModel());
			table_1.setRowSorter(sorter);
			List <RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 0;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
		
		// salesList 불러오기
		int salesListLength = m.getsCount();
		
		for (int i = 0; i < salesListLength; i++) {
			String arr[] = new String[1];
			arr[0] = Integer.toString(m.getSalesList().get(i));
			
			// 테이블에 열 추가
			DefaultTableModel model = (DefaultTableModel) table_2.getModel();
			model.addRow(arr);
		}
		// 총매출 textArea에 setText 해주기
		lblNewLabel_11.setText(Integer.toString(m.getDailySales()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize_admin() {
		admin = new JFrame();
		admin.setTitle("Rental System");
		admin.setBounds(100, 100, 904, 514);
		admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		admin.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{317, 96, 0};
		gbl_panel_3.rowHeights = new int[]{22, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_6 = new JLabel("\uAD00\uB9AC\uC790 \uBAA8\uB4DC");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 0;
		panel_3.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_4, BorderLayout.EAST);
		
		// 메인화면으로 돌아가는 버튼
		JButton btnNewButton_1 = new JButton("메인화면");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// admin은 setVisible(false)하고 main은 setVisible(true)
					admin.setVisible(false);
					main.getMainWindow().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel_4.add(btnNewButton_1);
		
		// 저장 버튼
		JButton btnNewButton = new JButton("저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 파일 저장
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
					m.saveAll(oos);
					JOptionPane.showMessageDialog(admin, "파일을 저장했습니다");
				} catch (FileNotFoundException fnfe) { 
					JOptionPane.showMessageDialog(admin, "파일을 찾을 수 없습니다");
				} catch (IOException ioe) { 
					JOptionPane.showMessageDialog(admin, "파일 입출력 오류입니다");
				} catch (Exception a) {
					JOptionPane.showMessageDialog(admin, "파일을 저장할 수 없습니다");
				} finally {
					try {
						if(oos != null)
							oos.close();
					} catch (Exception a1) {
						JOptionPane.showMessageDialog(admin, "파일 닫기에 실패했습니다");
					}
				}
			}
		});
		panel_4.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		admin.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, "name_981418641557200");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\uBB3C\uD488 \uAD00\uB9AC", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uBB3C\uD488\uBA85 : ");
		panel_5.add(lblNewLabel);
		
		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(10);
		
		// 물품명으로 물품 검색 -> 해당 열 focus
		JButton btnNewButton_2 = new JButton("검색");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = -1;
				int tableSize = table.getRowCount();
				for(int row = 0; row < tableSize; row++) {
					if(textField.getText().equals(table.getValueAt(row, 0))) {
						rowSelect = row;
						break;
					}
				}
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(admin, "찾으시려는 물품명을 입력해주세요");
				} else if(rowSelect == -1) {
					JOptionPane.showMessageDialog(admin, "입력하신 물품을 찾을 수 없습니다");
				} else {
					table.requestFocus();
					table.changeSelection(rowSelect, 0, false, false);
				}
			}
		});
		panel_5.add(btnNewButton_2);
		
		JPanel panel_6 = new JPanel();
		panel_1.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_6.add(tabbedPane_1);
		
		JPanel panel_10 = new JPanel();
		tabbedPane_1.addTab("\uBB3C\uD488 \uCD94\uAC00", null, panel_10, null);
		
		JLabel lblNewLabel_1 = new JLabel("물품명 :");
		panel_10.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("물품코드 :");
		panel_10.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_10.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("가격 :");
		panel_10.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_10.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("수량 :");
		panel_10.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_10.add(textField_4);
		textField_4.setColumns(10);
		
		// 추가 버튼 누르면 textField 에 입력한 정보들 테이블에 추가
		// 뭔가 안쓰고 추가 누르면 JOptionPane.showMessageDialog
		JButton btnNewButton_3 = new JButton("추가");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 물품명, 물품코드, 가격, 수량을 모두 입력하고 추가 버튼 누르면 정상적으로 추가 진행
				if(!textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("") && !textField_4.getText().equals("")) {
					String arr[] = new String[4];
					arr[0] = textField_1.getText();
					arr[1] = textField_2.getText();
					arr[2] = textField_3.getText();
					arr[3] = textField_4.getText();
					
					try {
						int price = Integer.parseInt(arr[2]);
						int goodsCount = Integer.parseInt(arr[3]);
						
						// Goods 객체로 만들고 goodsList에 추가
						Goods g = new Goods(arr[0], arr[1], goodsCount, price);
						m.addGoods(g);
						
						// 테이블에 추가
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(arr);
						
						// 물품명을 기준으로 내림차순 정렬
						TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter);
						List <RowSorter.SortKey> sortKeys = new ArrayList<>();
						int columnIndexToSort = 0;
						sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
						 
						sorter.setSortKeys(sortKeys);
						sorter.sort();
						
						// 정상적으로 추가한 후에는 textField 지우기
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
					} catch (NumberFormatException nfe) {
						//textfield_3,4에 숫자 아닌 것을 넣으면 catch
						JOptionPane.showMessageDialog(admin, "가격과 수량은 숫자로 입력해주세요");
					} catch (Exception a) {	// 중복된 물품코드가 이미 존재한다면
						JOptionPane.showMessageDialog(admin, "물품코드 중복입니다. 다른 물품코드를 입력해주세요");
					}
				}
				else {
					// 물품명, 물푸모드, 가격, 수량 중 하나라도 입력하지 않았다면
					JOptionPane.showMessageDialog(admin, "모든 항목을 입력해주세요");
				}
			}
		});
		panel_10.add(btnNewButton_3);
		
		JPanel panel_11 = new JPanel();
		tabbedPane_1.addTab("\uBB3C\uD488 \uC0AD\uC81C", null, panel_11, null);
		
		JLabel lblNewLabel_9 = new JLabel("물품코드 :");
		panel_11.add(lblNewLabel_9);
		
		// 삭제할 물품명 입력하는 textField
		textField_5 = new JTextField();
		panel_11.add(textField_5);
		textField_5.setColumns(10);
		
		// 열 선택하고 삭제 버튼 누르면 열 삭제, 물품코드 입력하고 삭제 버튼 누르면 삭제
		// 열 선택하고 입력하면 입력한 물품을 삭제하기
		// 열 선택도 안하고 물품코드도 입력안하면 JOptionPane.showMessageDialog
		JButton btnNewButton_4 = new JButton("삭제");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String gCode = textField_5.getText();
				if(!gCode.equals("")) {
					try {
						int row = m.search(gCode);
						model.removeRow(row);
						textField_5.setText("");
						m.deleteGoods(gCode);
					} catch (Exception e1) {
						textField_5.requestFocus();
						JOptionPane.showMessageDialog(admin, "해당 물품코드를 가진 물품이 없습니다");
					}
				} else if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String gCodeSelected = table.getValueAt(row, 1).toString();
					model.removeRow(row);
					try {
						m.deleteGoods(gCodeSelected);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					textField_5.requestFocus();
					JOptionPane.showMessageDialog(admin, "삭제하고자하는 열을 선택하거나 물품명을 입력하세요!");
				}
			}
		});
		panel_11.add(btnNewButton_4);
		
		JPanel panel_12 = new JPanel();
		tabbedPane_1.addTab("\uC7AC\uACE0 \uAD00\uB9AC", null, panel_12, null);
		
		JLabel lblNewLabel_10 = new JLabel("수량 :");
		panel_12.add(lblNewLabel_10);
		
		textField_6 = new JTextField();
		panel_12.add(textField_6);
		textField_6.setColumns(10);
		
		// 재고 추가
		JButton btnNewButton_5 = new JButton("추가");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int addNum = Integer.parseInt(textField_6.getText());
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(admin, "재고를 변경하고자하는 열을 선택하세요!");
					} else {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						try {
							// 실제 goods의 수량 변경
							m.addGoodsStock(m.search(gCodeSelected), addNum);
							// 테이블의 수량 변경
							table.setValueAt(Integer.parseInt(table.getValueAt(row, 3).toString()) + addNum, row, 3);
							JOptionPane.showMessageDialog(admin, "재고를 추가했습니다");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(admin, "수량을 입력하세요");
				}
			}
		});
		panel_12.add(btnNewButton_5);
		
		// 재고 감소
		JButton btnNewButton_6 = new JButton("감소");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int subNum = Integer.parseInt(textField_6.getText());
					int row = table.getSelectedRow();

					if (row == -1) {
						JOptionPane.showMessageDialog(admin, "재고를 변경하고자하는 열을 선택하세요!");
					} else {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						try {
							// 실제 goods의 수량 변경
							m.subGoodsStock(m.search(gCodeSelected), subNum);
							// 테이블의 수량 변경
							table.setValueAt(Integer.parseInt(table.getValueAt(row, 3).toString()) - subNum, row, 3);
							JOptionPane.showMessageDialog(admin, "재고를 감소시켰습니다");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(admin, "재고가 감소하려는 수량보다 부족합니다!\n다시 입력해주세요");
							textField_6.setText("");
							textField_6.requestFocus();
						}
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(admin, "수량을 입력하세요");
				}
			}
		});
		panel_12.add(btnNewButton_6);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_9.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"물품명", "물품코드", "가격", "수량"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("\uC720\uC800 \uAD00\uB9AC", null, panel_7, null);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_7.add(panel_13, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		panel_13.add(lblNewLabel_5);
		
		textField_7 = new JTextField();
		panel_13.add(textField_7);
		textField_7.setColumns(10);
		
		// 대여자 전화번호로 검색-----------------------------------------------------------------------------------------------------------안찾아
		JButton btnNewButton_7 = new JButton("검색");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = m.searchUser(textField_7.getText());
					table_1.requestFocus();
					table_1.changeSelection(row, 0, false, false);
					
					if(textField_7.getText().equals("")) {
						JOptionPane.showMessageDialog(admin, "전화번호를 입력해주세요");
					} else if (row == -1) {
						JOptionPane.showMessageDialog(admin, "입력하신 전화번호를 가진 사용자를 찾을 수 없습니다");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_13.add(btnNewButton_7);
		
		JPanel panel_14 = new JPanel();
		panel_7.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_14.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uB300\uC5EC\uBB3C\uD488 1", "\uB300\uC5EC\uBB3C\uD488 2", "\uB300\uC5EC\uBB3C\uD488 3", "\uB300\uC5EC \uC2DC\uC791\uC77C", "\uB300\uC5EC \uB9CC\uB8CC\uC77C", "\uC694\uAE08"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("\uB9E4\uCD9C \uD655\uC778", null, panel_8, null);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_8.add(panel_15, BorderLayout.EAST);
		
		JLabel lblNewLabel_7 = new JLabel("\uCD1D \uB9E4\uCD9C\uC561 :");
		panel_15.add(lblNewLabel_7);
		
		panel_15.add(lblNewLabel_11);
		
		JLabel lblNewLabel_8 = new JLabel("\uC6D0");
		panel_15.add(lblNewLabel_8);
		
		JPanel panel_16 = new JPanel();
		panel_8.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_16.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uB9E4\uCD9C\uC561"
			}
		));
		scrollPane_2.setViewportView(table_2);
	}
}