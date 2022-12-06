import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.RowFilter.Entry;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
	JLabel lblNewLabel_13 = new JLabel("New label");
	private JTable table_1;
	Management m;
	Main main = new Main();
	private JTable table_2;
	private JTextField textField_8;
	BufferedImage img = null;
	TableRowSorter<TableModel> sorter;

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
			int totalPay = 0;
			for(int j = 0; j < rentalCount; j++) {
				// goods의 이름으로 유저 리스트에 유저가 빌린 물품 보여주기
				arr[j + 2] =  m.getGoodsList().get(m.search(u.getRentalGoodsCode()[j])).getGoodsName();
				totalPay += u.payCalculateEach(j);
			}
			arr[5] = u.getRentDate();
			arr[6] = u.getDueDate();
			arr[7] = Integer.toString(totalPay);
			
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
		
		// 총매출 textArea에 setText 해주기
		lblNewLabel_13.setText(Integer.toString(m.getDailySales()));
		
		// 추가기능 : 이미지 등록을 위한 코드 ------------------------------------------------------------------------------------------------
		// 이미지 등록을 위한 물품명과 물품코드, 이미지 경로 불러오기
		for (int i = 0; i < goodsListLength; i++) {
			Goods g = m.getGoodsList().get(i);
			
			String arr[] = new String[3];
			arr[0] = g.getGoodsName();
			arr[1] = g.getGoodsCode();
			arr[2] = g.getImage();
			
			// 테이블에 열 추가
			DefaultTableModel model = (DefaultTableModel) table_2.getModel();
			model.addRow(arr);
			
			// 물품명을 기준으로 오름차순 정렬
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_2.getModel());
			table_2.setRowSorter(sorter);
			List <RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 0;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
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
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("\uAD00\uB9AC\uC790 \uBAA8\uB4DC");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel panel_17 = new JPanel();
		panel_3.add(panel_17, BorderLayout.EAST);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_12 = new JLabel("\uCD1D \uB9E4\uCD9C : ");
		lblNewLabel_12.setFont(new Font("굴림", Font.BOLD, 12));
		panel_17.add(lblNewLabel_12, BorderLayout.WEST);
		
		panel_17.add(lblNewLabel_13, BorderLayout.CENTER);
		
		JLabel lblNewLabel_14 = new JLabel(" \uC6D0");
		lblNewLabel_14.setFont(new Font("굴림", Font.BOLD, 12));
		panel_17.add(lblNewLabel_14, BorderLayout.EAST);
		
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
						Goods g = new Goods(arr[0], arr[1], goodsCount, price, null);
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
		
		// 삭제할 물품코드 입력하는 textField
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
						JOptionPane.showMessageDialog(admin, "물품을 삭제했습니다");
					} catch (Exception e1) {
						textField_5.requestFocus();
						JOptionPane.showMessageDialog(admin, "해당 물품코드를 가진 물품이 없습니다");
					}
				} else if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String gCodeSelected = table.getValueAt(row, 1).toString();
					try {
						m.deleteGoods(gCodeSelected);
						model.removeRow(row);
						JOptionPane.showMessageDialog(admin, "물품을 삭제했습니다");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					textField_5.requestFocus();
					JOptionPane.showMessageDialog(admin, "삭제하고자하는 열을 선택하거나 물품코드를 입력하세요!");
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
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "물품명", "물품코드", "가격", "수량" }) {
			// 테이블 선택만 가능, 수정 불가
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		
		// 물품명으로 검색
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(textField.getText().trim()));
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				String text = textField.getText();
				
				// textField에 입력하면 바로 sort
				table.setRowSorter(sorter);

				if (text.trim().length() == 0) { // 문자열 공백
					sorter.setRowFilter(null);
				} else {
					// 물품명만 검색할 수 있도록 설정
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // 대소문자 상관 없이 검색
				}
				// rowchange 끝나고나서 호출
				// 호출하지 않으면 IndexOutOfBoundsException 발생
				sorter.allRowsChanged();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				// textField에 입력하면 바로 sort
				table.setRowSorter(sorter);
				
				String text = textField.getText();

				if (text.trim().length() == 0) {
					// row의 sort, filter 동시에 진행
					sorter.setRowFilter(null);
				} else {
					// 물품명만 검색할 수 있도록 설정
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // 대소문자 상관 없이 검색
				}
				// rowchange 끝나고나서 호출
				// 호출하지 않으면 IndexOutOfBoundsException 발생
				sorter.allRowsChanged();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("해당 서비스는 지원되지 않습니다."); // ArrayList에 값을 추가 / 변경 / 삭제하려는 경우 익셉션 발생
			}
		});
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("\uC720\uC800 \uAD00\uB9AC", null, panel_7, null);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_7.add(panel_13, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("\uC774\uB984 / \uC804\uD654\uBC88\uD638 :");
		panel_13.add(lblNewLabel_5);
		
		textField_7 = new JTextField();
		panel_13.add(textField_7);
		textField_7.setColumns(10);
		
		JPanel panel_14 = new JPanel();
		panel_7.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_14.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\uC774\uB984", "\uC804\uD654\uBC88\uD638", "\uB300\uC5EC\uBB3C\uD488 1",
						"\uB300\uC5EC\uBB3C\uD488 2", "\uB300\uC5EC\uBB3C\uD488 3", "\uB300\uC5EC \uC2DC\uC791\uC77C",
						"\uB300\uC5EC \uB9CC\uB8CC\uC77C", "\uC694\uAE08" }) {
			// 테이블 선택만 가능, 수정 불가
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		// 이름 / 전화번호로 대여자 검색
		TableRowSorter<TableModel> sorter_1 = new TableRowSorter<>(table_1.getModel());
		table_1.setRowSorter(sorter_1);
		
		textField_7.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				// textField에 입력하면 바로 sort해주기 위해 코드 추가
				table_1.setRowSorter(sorter_1);
				
				String text = textField_7.getText();

				if (text.trim().length() == 0) { // 문자열 공백
					sorter_1.setRowFilter(null);
				} else {
					// 전화번호와 이름 행에서만 sort하도록 설정
					sorter_1.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1)); // 대소문자 상관 없이 검색
				}
				// rowchange 끝나고나서 호출
				// 호출하지 않으면 IndexOutOfBoundsException 발생
				sorter_1.allRowsChanged();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// textField에 입력하면 바로 sort해주기 위해 코드 추가
				table_1.setRowSorter(sorter_1);
				
				String text = textField_7.getText();

				if (text.trim().length() == 0) {
					sorter_1.setRowFilter(null); // row의 sort, filter 동시에 진행
				} else {
					// 전화번호와 이름 행에서만 sort하도록 설정
					sorter_1.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1)); // 대소문자 상관 없이 검색
				}
				// rowchange 끝나고나서 호출
				// 호출하지 않으면 IndexOutOfBoundsException 발생
				sorter_1.allRowsChanged();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// ArrayList에 값을 추가 / 변경 / 삭제하려는 경우 익셉션 발생
				throw new UnsupportedOperationException("해당 서비스는 지원되지 않습니다.");
			}
		});

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("이미지 등록", null, panel_8, null);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_8.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_15.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\uBB3C\uD488\uBA85", "\uBB3C\uD488 \uCF54\uB4DC", "\uC774\uBBF8\uC9C0 \uACBD\uB85C" }) {
			// 테이블 선택만 가능, 수정 불가
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		JPanel panel_16 = new JPanel();
		panel_15.add(panel_16, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_7 = new JLabel("\uC774\uBBF8\uC9C0 \uACBD\uB85C : ");
		panel_16.add(lblNewLabel_7);
		
		textField_8 = new JTextField();
		panel_16.add(textField_8);
		textField_8.setColumns(10);
		
		// 추가 기능 : 이미지를 등록을 위한 코드 ---------------------------------------------------------------------------------------------
		JButton btnNewButton_8 = new JButton("\uC774\uBBF8\uC9C0 \uB4F1\uB85D");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 선택된 열 row 변수에 저장
				int row = table_2.getSelectedRow();
				// 열을 선택하지 않았다면 메시지 출력
				if (row == -1) {
					JOptionPane.showMessageDialog(admin, "이미지를 등록하고자하는 열을 선택하세요!");
				} else {	// 열을 선택했다면
					// 선택한 열의 물품 코드 gCodeSelected 에 저장
					String gCodeSelected = table.getValueAt(row, 1).toString();
					// 이미지 경로를 file1 변수에 저장
					// pics 폴더 안에 이미지들을 저장해야하는 것으로 매뉴얼 만들었기 때문에 파일명 앞에 pics/ 붙임
					File file1 = new File("pics/" + textField_8.getText());
					
					// 이미지 경로를 입력하지 않으면
					if(textField_8.getText().equals("")) {
						JOptionPane.showMessageDialog(admin, "이미지 경로를 입력해주세요");
					}
					// 파일이 pics 폴더 안에 있고 이미지 경로를 입력했으면
					else if(!textField_8.getText().equals("") && file1.exists())
						try {
							
							// goods의 image에 파일 경로 set
							m.setImage(m.search(gCodeSelected), "pics/" + textField_8.getText());
							// 테이블 내용 변경
							table_2.setValueAt("pics/" + textField_8.getText(), row, 2);
							JOptionPane.showMessageDialog(admin, "이미지가 등록되었습니다");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					// 파일이 pics 폴더 안에 없으면
					else
						JOptionPane.showMessageDialog(admin, "이미지를 디렉토리에 먼저 저장해주세요");
				}
			}
		});
		panel_16.add(btnNewButton_8);
		
		// 추가 기능 : 등록된 이미지를 삭제하기 위한 코드 -------------------------------------------------------------------------------------------
		JButton btnNewButton_9 = new JButton("\uC774\uBBF8\uC9C0 \uC0AD\uC81C");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_2.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(admin, "이미지를 삭제하고자하는 열을 선택하세요!");
				} else {
					try {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						// 물품에 이미지 경로가 등록되어있지 않으면 메시지 출력
						if (m.goodsAt(m.search(gCodeSelected)).getImage() == null) {
							JOptionPane.showMessageDialog(admin, "이 상품은 이미지가 등록되어있지 않습니다");
						} else {
							// goods의 image에 파일 경로 set
							m.setImage(m.search(gCodeSelected), null);
							// 테이블 내용 변경
							table_2.setValueAt(null, row, 2);
							JOptionPane.showMessageDialog(admin, "이미지가 삭제되었습니다");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_16.add(btnNewButton_9);
		
		// 추가 기능 : 이미지를 보기 위한 코드 (물품 관리 테이블) -------------------------------------------------------------------------------------------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 물품의 열을 더블클릭하면
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					try {
						// 이미지 setVisible(true);
						String imgRoute = m.goodsAt(m.search((String) table.getValueAt(row, 1))).getImage();
						// goods 에 이미지 경로가 등록되어있다면
						if (imgRoute != null) {
							img = ImageIO.read(new File(imgRoute));
							getContentPane().add(new MyPanel());
							pack();
							setVisible(true);
						} else {
							JOptionPane.showMessageDialog(admin, "물품의 이미지가 등록되어있지 않습니다");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		// 추가 기능 : 이미지를 보기 위한 코드 (이미지 등록 테이블) -------------------------------------------------------------------------------------------
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 물품의 열을 더블클릭하면
				if (e.getClickCount() == 2) {
					int row = table_2.getSelectedRow();
					try {
						// 이미지 setVisible(true);
						String imgRoute = m.goodsAt(m.search((String) table_2.getValueAt(row, 1))).getImage();
						// goods 에 이미지 경로가 등록되어있다면
						if (imgRoute != null) {
							img = ImageIO.read(new File(imgRoute));
							getContentPane().add(new MyPanel());
							pack();
							setVisible(true);
						} else {
							JOptionPane.showMessageDialog(admin, "물품의 이미지가 등록되어있지 않습니다");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	// 추가 기능 : 물품 더블클릭 시 이미지 띄우기위한 MyPanel 클래스------------------------------------------------------------------------------------
	class MyPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

		public Dimension getPreferredSize() {
			if (img == null) {
				return new Dimension(100, 100);
			} else {
				return new Dimension(img.getWidth(null), img.getHeight(null));
			}
		}

	}
}