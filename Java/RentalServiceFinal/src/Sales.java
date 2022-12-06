import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Sales extends JFrame {

	private JFrame sales;
	private JTable table_3;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTable table_4;
	private JTextField textField_12;
	// 대여 물품 선택 리스트
	ArrayList<String> rentSelectedGoods = new ArrayList<String>();
	// 반납 물품 선택 리스트
	ArrayList<String> returnSelectedGoods = new ArrayList<String>();
	private int totalPrice = 0;
	BufferedImage img = null;
	
	Management m;
	Main main = new Main();

	int row1 = -1;
	int row2 = -1;
	int row3 = -1;
	private JTextField textField;
	
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
	
	JFrame getSalesWindow () {
		return sales;
	}

	/**
	 * Create the application.
	 */
	public Sales() throws Exception {
		initialize();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Database.txt"));
		m = new Management(100, 100, 100, ois);

		// 저장된 정보가 처음부터 보이도록 함
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

			DefaultTableModel model = (DefaultTableModel) table_3.getModel();
			model.addRow(arr);
			
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_3.getModel());
			table_3.setRowSorter(sorter);
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
	private void initialize() {
		sales = new JFrame();
		sales.setTitle("Rental System");
		sales.setBounds(100, 100, 900, 500);
		sales.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sales.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		sales.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("\uC601\uC5C5 \uBAA8\uB4DC");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		panel_1.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnNewButton = new JButton("\uBA54\uC778 \uD654\uBA74");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sales.setVisible(false);
					main.getMainWindow().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_3.add(btnNewButton);
		
		// 저장 버튼
		JButton btnNewButton_1 = new JButton("저장");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 저장
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
					m.saveAll(oos);
					JOptionPane.showMessageDialog(sales, "파일을 저장했습니다");
				} catch (FileNotFoundException fnfe) { 
					JOptionPane.showMessageDialog(sales, "파일을 찾을 수 없습니다");
				} catch (IOException ioe) { 
					JOptionPane.showMessageDialog(sales, "파일 입출력 오류입니다");
				} catch (Exception a) {
					JOptionPane.showMessageDialog(sales, "파일을 저장할 수 없습니다");
				} finally {
					try {
						if(oos != null)
							oos.close();
					} catch (Exception a1) {
						JOptionPane.showMessageDialog(sales, "파일 닫기에 실패했습니다");
					}
				}
			}
		});
		panel_3.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		sales.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane, "name_990022830924500");
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\uBB3C\uD488 \uB300\uC5EC", null, panel_4, null);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_23 = new JPanel();
		panel_6.add(panel_23, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uD488\uBA85 :");
		panel_23.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_23.add(textField);
		
		JPanel panel_24 = new JPanel();
		panel_6.add(panel_24, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_22 = new JLabel("\uBB3C\uD488\uC758 \uC774\uBBF8\uC9C0\uB97C \uBCF4\uB824\uBA74 \uBB3C\uD488\uC758 \uC5F4\uC744 \uB354\uBE14\uD074\uB9AD\uD558\uC138\uC694");
		panel_24.add(lblNewLabel_22);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("\uBB3C\uD488 1 :");
		panel_10.add(lblNewLabel_2);
		
		JLabel lblNewLabel_17 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		panel_10.add(lblNewLabel_17);
		
		JLabel lblNewLabel_3 = new JLabel("\uBB3C\uD488 2 :");
		panel_10.add(lblNewLabel_3);
		
		JLabel lblNewLabel_18 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		panel_10.add(lblNewLabel_18);
		
		JLabel lblNewLabel_4 = new JLabel("\uBB3C\uD488 3 :");
		panel_10.add(lblNewLabel_4);
		
		JLabel lblNewLabel_19 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		panel_10.add(lblNewLabel_19);
		
		JPanel panel_11 = new JPanel();
		panel_7.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13, BorderLayout.EAST);
		
		// 상품 대여 버튼
		JButton btnNewButton_3 = new JButton("\uC0C1\uD488 \uB300\uC5EC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 만약 모든 입력란에 입력이 안 되었다면
				if(textField_9.getText().equals("") || textField_10.getText().equals("") || textField_11.getText().equals("")) {
					JOptionPane.showMessageDialog(sales, "이름, 전화번호, 반납예정일을 모두 입력해주세요");
				}
				
				try {
				String userName = textField_9.getText();
				String phoneNum = textField_10.getText();
				Calendar today = Calendar.getInstance();
				String rentDate = new SimpleDateFormat("yyyyMMdd").format(today.getTime());
				// 반납 예정일 입력받고 yyyyMMdd 형식이 아니면 exception
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dueDate = new SimpleDateFormat("yyyyMMdd").format(sdf.parse(textField_11.getText()));
				
					Date rentDate1 = sdf.parse(rentDate);
					Date dueData1 = sdf.parse(dueDate);
					if (rentDate1.after(dueData1)) {
						JOptionPane.showMessageDialog(sales, "반납예정일은 금일의 날짜와 같거나 뒤의 날짜로 입력해주세요");
					}
					if (rentSelectedGoods.size() == 0) {
						JOptionPane.showMessageDialog(sales, "물품을 선택해주세요");
					} else if (rentSelectedGoods.size() != 0 && !rentDate1.after(dueData1)) {
						User u = new User(userName, phoneNum, rentDate, dueDate);

						try {
							if (rentSelectedGoods.size() == 1) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());
							} else if (rentSelectedGoods.size() == 2) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());

								int index2 = m.search(lblNewLabel_18.getText());
								u.addCode(lblNewLabel_18.getText(), m.goodsAt(index2).getPrice());
							} else if (rentSelectedGoods.size() == 3) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());

								int index2 = m.search(lblNewLabel_18.getText());
								u.addCode(lblNewLabel_18.getText(), m.goodsAt(index2).getPrice());

								int index3 = m.search(lblNewLabel_19.getText());
								u.addCode(lblNewLabel_19.getText(), m.goodsAt(index3).getPrice());
							}

							m.checkIn(u);
							JOptionPane.showMessageDialog(sales, "상품 대여가 완료되었습니다");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (ParseException pe) {
					if (!textField_11.getText().equals(""))
						JOptionPane.showMessageDialog(sales, "날짜가 정확하지 않습니다\nyyyyMMdd 형식으로 입력해주세요");
				}
				// 라벨 및 텍스트필기 초기화
				lblNewLabel_17.setText("(선택 안함)");
				lblNewLabel_18.setText("(선택 안함)");
				lblNewLabel_19.setText("(선택 안함)");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				
				// 선택된 checkbox 들 해제하기
				for(int i = 0; i < table_3.getRowCount(); i++) {
					if (table_3.getValueAt(i, 4) != null && (boolean)table_3.getValueAt(i, 4) == true) {
						table_3.setValueAt(false, i, 4);
					}
				}
				
				// 테이블 내 체크박스 비활성화, 테이블 내 물품 수량 바꾸기
				if(row1 != -1) {
					table_3.setValueAt(false, row1, 4);
					table_3.setValueAt(Integer.parseInt((String) table_3.getValueAt(row1, 3)) - 1, row1, 3);
				}
				if(row2 != -1) {
					table_3.setValueAt(false, row2, 4);
					table_3.setValueAt(Integer.parseInt((String) table_3.getValueAt(row2, 3)) - 1, row2, 3);
				}
				if(row3 != -1) {
					table_3.setValueAt(false, row3, 4);
					table_3.setValueAt(Integer.parseInt((String) table_3.getValueAt(row3, 3)) - 1, row3, 3);
				}
				rentSelectedGoods.clear();
			}
		});
		panel_13.add(btnNewButton_3);
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.CENTER);
		
		JLabel lblNewLabel_5 = new JLabel("\uC774\uB984 :");
		panel_12.add(lblNewLabel_5);
		
		textField_9 = new JTextField();
		panel_12.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		panel_12.add(lblNewLabel_6);
		
		textField_10 = new JTextField();
		panel_12.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("\uBC18\uB0A9\uC608\uC815\uC77C(yyyymmdd) :");
		panel_12.add(lblNewLabel_7);
		
		textField_11 = new JTextField();
		panel_12.add(textField_11);
		textField_11.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_8.add(scrollPane, BorderLayout.CENTER);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\uBB3C\uD488\uBA85",
				"\uBB3C\uD488\uCF54\uB4DC", "\uAC00\uACA9", "\uC218\uB7C9", "\uC120\uD0DD" }) {
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			// 테이블 선택만 가능, 수정 불가
			public boolean isCellEditable(int row, int column) {
				if(column == 4)
					return true;
				else
					return false;
			}
		});
		table_3.getColumnModel().getColumn(4).setPreferredWidth(15);
		scrollPane.setViewportView(table_3);
		
		// 물품 검색
		TableRowSorter<TableModel> sorter_1 = new TableRowSorter<>(table_3.getModel());
		table_3.setRowSorter(sorter_1);
		
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 추가 기능 : 이미지 확인을 위한 코드 (대여 진행 테이블)---------------------------------------------------------------------------------------------------------------
				// 더블클릭하면
				if (e.getClickCount() == 2) {
					int row = table_3.getSelectedRow();
					try {
						String imgRoute = m.goodsAt(m.search((String) table_3.getValueAt(row, 1))).getImage();
						// goods에 이미지 경로를 저장해뒀다면 이미지 setVisible(true)
						if (imgRoute != null) {
							img = ImageIO.read(new File(imgRoute));
							getContentPane().add(new MyPanel());
							pack();
							setVisible(true);
						} else {
							JOptionPane.showMessageDialog(sales, "물품의 이미지가 등록되어있지 않습니다");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				// 물품 대여를 위해 물품 선택할 때 textArea의 글자를 물품코드로 변경
				// 3개 이상의 물품 선택 못하도록 하기
				// 재고가 0개인 물품 선택하지 못하도록 하기
				} else if (e.getClickCount() == 1) {
					try {
						int row = table_3.getSelectedRow();
						boolean selected = (boolean) table_3.getValueAt(row, 4);
						int goodsCount = Integer.parseInt((String) table_3.getValueAt(row, 3));	// 상품 재고
						
						if (selected == true && goodsCount > 0 && rentSelectedGoods.size() < 4) {
							// selectedGoods에 물품 코드 추가
							rentSelectedGoods.add((String) table_3.getValueAt(row, 1));
						} else if (selected == false) {
							// selectedGoods에서 물품 코드 삭제
							rentSelectedGoods.remove((String) table_3.getValueAt(row, 1));
						}
						if (rentSelectedGoods.size() == 0) {
							lblNewLabel_17.setText("(선택 안함)");
							lblNewLabel_18.setText("(선택 안함)");
							lblNewLabel_19.setText("(선택 안함)");
						} else if (rentSelectedGoods.size() == 1) {
							lblNewLabel_17.setText(rentSelectedGoods.get(0));
							lblNewLabel_18.setText("(선택 안함)");
							lblNewLabel_19.setText("(선택 안함)");
						} else if (rentSelectedGoods.size() == 2) {
							lblNewLabel_17.setText(rentSelectedGoods.get(0));
							lblNewLabel_18.setText(rentSelectedGoods.get(1));
							lblNewLabel_19.setText("(선택 안함)");
						} else if (rentSelectedGoods.size() == 3) {
							lblNewLabel_17.setText(rentSelectedGoods.get(0));
							lblNewLabel_18.setText(rentSelectedGoods.get(1));
							lblNewLabel_19.setText(rentSelectedGoods.get(2));
						} else if (rentSelectedGoods.size() == 4) {
							JOptionPane.showMessageDialog(sales, "물품은 3개까지 선택 가능합니다");
							table_3.setValueAt(false, row, 4);
							rentSelectedGoods.remove(3);
						}
						
						if (goodsCount == 0) {
							JOptionPane.showMessageDialog(sales, "해당 물품은 재고가 없어 빌릴 수 없습니다");
							table_3.setValueAt(false, row, 4);
						}
					} catch (NullPointerException npe) {}
				}
			}
		});
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				// textField에 입력하면 바로 sort해주기 위해 코드 추가
				table_3.setRowSorter(sorter_1);
				
				String text = textField.getText();

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
				table_3.setRowSorter(sorter_1);
				
				String text = textField.getText();

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
				throw new UnsupportedOperationException("해당 서비스는 지원되지 않습니다."); // ArrayList에 값을 추가 / 변경 / 삭제하려는 경우 익셉션 발생
			}
		});
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("\uBB3C\uD488 \uBC18\uB0A9", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_5.add(panel_14, BorderLayout.NORTH);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		panel_14.add(panel_17, BorderLayout.NORTH);
		
		JLabel lblNewLabel_8 = new JLabel("\uC804\uD654\uBC88\uD638 :");
		panel_17.add(lblNewLabel_8);
		
		textField_12 = new JTextField();
		panel_17.add(textField_12);
		textField_12.setColumns(10);
		JLabel lblNewLabel_16 = new JLabel("(\uC0AC\uC6A9\uC790\uBA85)");
		
		JLabel lblNewLabel_12 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		JLabel lblNewLabel_13 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		JLabel lblNewLabel_14 = new JLabel("(\uC120\uD0DD \uC548\uD568)");
		JLabel lblNewLabel_15 = new JLabel("(\uCD1D \uC9C0\uBD88\uAE08\uC561)");
		
		
		// 사용자 대여 기록 조회
		JButton btnNewButton_4 = new JButton("\uAC80\uC0C9");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_4.getModel();
				// 모델 초기화, 지불금액이랑 물품 선택한 textfeild 도 초기화
				model.setNumRows(0);
				lblNewLabel_12.setText("(선택 안함)");
				lblNewLabel_13.setText("(선택 안함)");
				lblNewLabel_14.setText("(선택 안함)");
				lblNewLabel_15.setText("(총 지불금액)");
				returnSelectedGoods.clear();
				totalPrice = 0;
				
				// 사용자 조회
				String userPhone = textField_12.getText();
				try {
					int userIndex = m.searchUser(userPhone);
					if (userIndex == -1) {
						JOptionPane.showMessageDialog(sales, "해당 사용자를 찾을 수 없습니다");
					} else {
						// 사용자에게 사용자명 보여주기
						lblNewLabel_16.setText(m.userAt(userIndex).getUserName());
						User u = m.userAt(userIndex);
						int rentalGoodsCount = u.getRentalCount();
						// 사용자에게 대여 기록 보여주기
						for (int i = 0; i < rentalGoodsCount; i++) {
							int rentGoodsIndex = m.search(m.userAt(userIndex).codeAt(i));
							Goods g = m.getGoodsList().get(rentGoodsIndex);
							
							String arr[] = new String[9];
							// 물품 코드
							arr[0] = g.getGoodsCode();
							// 물품명
							arr[1] = g.getGoodsName();
							// 물품 가격
							arr[2] = Integer.toString(g.getPrice());
							// 빌린 날짜
							arr[3] = u.getRentDate();
							// 대여 만료일
							arr[4] = u.getDueDate();

							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							
							// 오늘날짜 (반납날짜)
							Calendar today = Calendar.getInstance();
							
							// 빌린날짜
							Date date1 = sdf.parse(u.getRentDate());
							Calendar rentDate = Calendar.getInstance();
							rentDate.setTime(date1);
							
							// 반납예정날짜
							Date date2 = sdf.parse(u.getDueDate());
							Calendar dueDate = Calendar.getInstance();
							dueDate.setTime(date2);
							
							int daysLate;	// 연체 일수
							int rentDays;	// 정상 대여 일수
							// 만약 오늘 날짜가 반납 예정일보다 빠르면 연체일수는 0으로 만들기
							if(today.compareTo(dueDate) <= 0) {
								daysLate = 0;
								rentDays = (int)((today.getTimeInMillis() - rentDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
							}
							// 오늘 날짜가 반납 예정일보다 느리면 연체일수 정상 표시
							else {
								daysLate = (int)((today.getTimeInMillis() - dueDate.getTimeInMillis()) / (1000 * 60 * 60 * 24));
								rentDays = (int)((dueDate.getTimeInMillis() - rentDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
							}
							// 연체되지 않은 대여일수
							arr[5] = Integer.toString(rentDays);
							// 연체일수
							arr[6] = Integer.toString(daysLate);
							// 지불할 가격
							arr[7] = Integer.toString(u.payCalculateEach(i));
							
							// 테이블에 열 추가
							model.addRow(arr);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_17.add(btnNewButton_4);
		
		JPanel panel_18 = new JPanel();
		panel_14.add(panel_18, BorderLayout.CENTER);
		
		panel_18.add(lblNewLabel_16);
		
		JLabel lblNewLabel_9 = new JLabel("\uB2D8\uC758 \uB300\uC5EC \uAE30\uB85D\uC774 \uD655\uC778\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		panel_18.add(lblNewLabel_9);
		
		JPanel panel_15 = new JPanel();
		panel_5.add(panel_15, BorderLayout.SOUTH);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_19 = new JPanel();
		panel_15.add(panel_19, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uBB3C\uD488 1 :");
		panel_19.add(lblNewLabel_2_1);
		
		panel_19.add(lblNewLabel_12);
		
		JLabel lblNewLabel_3_1 = new JLabel("\uBB3C\uD488 2 :");
		panel_19.add(lblNewLabel_3_1);
		
		panel_19.add(lblNewLabel_13);
		
		JLabel lblNewLabel_4_1 = new JLabel("\uBB3C\uD488 3 :");
		panel_19.add(lblNewLabel_4_1);
		
		panel_19.add(lblNewLabel_14);
		
		JPanel panel_20 = new JPanel();
		panel_15.add(panel_20, BorderLayout.CENTER);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21, BorderLayout.EAST);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\uACB0\uC81C\uC5D0 \uB3D9\uC758\uD569\uB2C8\uB2E4");
		
		// 반납 버튼
		JButton btnNewButton_5 = new JButton("\uBB3C\uD488 \uBC18\uB0A9");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 아무 물품도 선택하지 않고 반납버튼 누르면
				if(returnSelectedGoods.size() == 0)
					JOptionPane.showMessageDialog(sales, "반납하실 물품을 선택해주세요");
				
				// 반납할 물품을 하나라도 선택하면
				else {
					// 결제에 동의한다는 체크박스에 체크하면
					if(chckbxNewCheckBox.isSelected()) {
						try {
							// 물품 반납 진행
							int uIndex = m.searchUser(textField_12.getText());
							User u = m.userAt(uIndex);
							String gCode = "";
							
							for (int i = table_4.getRowCount() - 1; i >= 0; i--) {
								if (table_4.getValueAt(i, 8) == null || (boolean) table_4.getValueAt(i, 8) == false) {
									continue;
								} else if ((boolean) table_4.getValueAt(i, 8)) {
									// 물품 코드
									gCode = (String) table_4.getValueAt(i, 0);
									// 재고 추가
									m.returnGoodsNew(uIndex, gCode);
									// 유저의 물품 대여 리스트에서 물품 삭제
									m.userAt(uIndex).subCode(u, gCode);
									// payList에서 물품 가격 삭제
									m.userAt(uIndex).subPay(u, i);
									
									// 테이블에서 해당 열 삭제
									DefaultTableModel model = (DefaultTableModel) table_4.getModel();
									model.removeRow(i);
								}
							}
							// 반납 진행한 가격 sales 에 추가
							m.addSales(totalPrice);
							
							// 결제 진행 체크박스 false 로 되돌리기
							chckbxNewCheckBox.setSelected(false);
							JOptionPane.showMessageDialog(sales, "물품을 반납하였습니다");
							
							// 라벨 및 변수 초기화
							lblNewLabel_12.setText("(선택 안함)");
							lblNewLabel_13.setText("(선택 안함)");
							lblNewLabel_14.setText("(선택 안함)");
							lblNewLabel_15.setText("(총 지불금액)");
							totalPrice = 0;
							returnSelectedGoods.clear();
							
							// 사용자의 모든 물품 반납되면 사용자를 대여자 목록에서 삭제 (체크아웃)
							if(u.getRentalCount() == 0) {
								m.checkOut(uIndex);
								JOptionPane.showMessageDialog(sales, "사용자의 물품이 모두 반납되어 자동으로 체크아웃됩니다");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					// 결제에 동의하는 체크박스에 체크하지 않으면 반납 진행 X
					} else {
						JOptionPane.showMessageDialog(sales, "결제에 동의해주세요");
					}
				}
			}
		});
		panel_21.add(btnNewButton_5);
		
		JPanel panel_22 = new JPanel();
		panel_20.add(panel_22, BorderLayout.CENTER);
		
		JLabel lblNewLabel_20 = new JLabel("\uC5F0\uCCB4\uC77C\uC218\uC5D0 \uB300\uD55C \uBB3C\uD488 \uAC00\uACA9\uC740 \uAE30\uBCF8 \uAC00\uACA9\uC758 1.5\uBC30 \uC785\uB2C8\uB2E4");
		panel_22.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("     ");
		panel_22.add(lblNewLabel_21);
		panel_22.add(lblNewLabel_15);
		
		JLabel lblNewLabel_10 = new JLabel("\uC6D0\uC744 \uC9C0\uBD88\uD574\uC8FC\uC2ED\uC2DC\uC624");
		panel_22.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("     ");
		panel_22.add(lblNewLabel_11);
		
		panel_22.add(chckbxNewCheckBox);
		
		JPanel panel_16 = new JPanel();
		panel_5.add(panel_16, BorderLayout.CENTER);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_16.add(scrollPane_1, BorderLayout.CENTER);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBB3C\uD488\uCF54\uB4DC", "\uBB3C\uD488\uBA85", "\uAC00\uACA9", "\uB300\uC5EC\uC77C", "\uBC18\uB0A9\uC608\uC815\uC77C", "\uC815\uC0C1\uB300\uC5EC\uC77C\uC218", "\uC5F0\uCCB4\uC77C\uC218", "\uC9C0\uBD88\uAE08\uC561", "\uC120\uD0DD"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			// 테이블 선택만 가능, 수정 불가
			public boolean isCellEditable(int row, int column) {
				if(column == 8)
					return true;
				else
					return false;
			}
		});
		table_4.getColumnModel().getColumn(8).setPreferredWidth(25);
		scrollPane_1.setViewportView(table_4);
		
		// table_4 (반납 진행 테이블)의 mouseListener
		table_4.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스를 클릭하면
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					try {
						int row = table_4.getSelectedRow();
						// 클릭된 셀 selected 변수에 저장
						boolean selected = (boolean) table_4.getValueAt(row, 8);
						User u;

						try {
							u = m.userAt(m.searchUser(textField_12.getText()));

							if (selected == true) {
								// selectedGoods에 물품 코드 추가
								returnSelectedGoods.add((String) table_4.getValueAt(row, 0));
								totalPrice += u.payCalculateEach(row);
							} else if (selected == false) {
								// selectedGoods에서 물품 코드 삭제
								returnSelectedGoods.remove((String) table_4.getValueAt(row, 0));
								totalPrice -= u.payCalculateEach(row);
							}
							if (returnSelectedGoods.size() == 0) {
								lblNewLabel_12.setText("(선택 안함)");
								lblNewLabel_13.setText("(선택 안함)");
								lblNewLabel_14.setText("(선택 안함)");
							} else if (returnSelectedGoods.size() == 1) {
								lblNewLabel_12.setText(returnSelectedGoods.get(0));
								lblNewLabel_13.setText("(선택 안함)");
								lblNewLabel_14.setText("(선택 안함)");
							} else if (returnSelectedGoods.size() == 2) {
								lblNewLabel_12.setText(returnSelectedGoods.get(0));
								lblNewLabel_13.setText(returnSelectedGoods.get(1));
								lblNewLabel_14.setText("(선택 안함)");
							} else if (returnSelectedGoods.size() == 3) {
								lblNewLabel_12.setText(returnSelectedGoods.get(0));
								lblNewLabel_13.setText(returnSelectedGoods.get(1));
								lblNewLabel_14.setText(returnSelectedGoods.get(2));
							}
							lblNewLabel_15.setText(Integer.toString(totalPrice));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} catch (NullPointerException npe) {
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