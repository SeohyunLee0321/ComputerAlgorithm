import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.awt.event.*;

public class Sales extends JFrame {

	private JFrame sales;
	private JTextField textField_8;
	private JTable table_3;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTable table_4;
	private JTextField textField_12;
	private int selectCnt = 0;
	private int selectCnt2 = 0;
	private int totalPrice = 0;
	
	Management m;
	Main main = new Main();

	int row1 = -1;
	int row2 = -1;
	int row3 = -1;
	
	/**
	 * Launch the application.
	 */
	// ���ο��� ������ ��� ��ư�� ������ �� â�� ���� ������� ������ run()�� no body �Լ��� �����
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

		// ����� ������ ó������ ���̵��� ��
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
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{274, 61, 0};
		gbl_panel_1.rowHeights = new int[]{18, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("\uC601\uC5C5 \uBAA8\uB4DC");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
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
		
		// ���� ��ư
		JButton btnNewButton_1 = new JButton("����");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
					m.saveAll(oos);
					JOptionPane.showMessageDialog(sales, "������ �����߽��ϴ�");
				} catch (FileNotFoundException fnfe) { 
					JOptionPane.showMessageDialog(sales, "������ ã�� �� �����ϴ�");
				} catch (IOException ioe) { 
					JOptionPane.showMessageDialog(sales, "���� ����� �����Դϴ�");
				} catch (Exception a) {
					JOptionPane.showMessageDialog(sales, "������ ������ �� �����ϴ�");
				} finally {
					try {
						if(oos != null)
							oos.close();
					} catch (Exception a1) {
						JOptionPane.showMessageDialog(sales, "���� �ݱ⿡ �����߽��ϴ�");
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
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uD488\uBA85 :");
		panel_6.add(lblNewLabel_1);
		
		textField_8 = new JTextField();
		panel_6.add(textField_8);
		textField_8.setColumns(10);
		
		// ��ǰ������ �뿩�� ��ǰ �˻�
		JButton btnNewButton_2 = new JButton("�˻�");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSelect = -1;
				int tableSize = table_3.getRowCount();
				for(int row = 0; row < tableSize; row++) {
					if(textField_8.getText().equals(table_3.getValueAt(row, 0))) {
						rowSelect = row;
						break;
					}
				}
				if (textField_8.getText().equals("")) {
					JOptionPane.showMessageDialog(sales, "ã���÷��� ��ǰ���� �Է����ּ���");
				} else if(rowSelect == -1) {
					JOptionPane.showMessageDialog(sales, "�Է��Ͻ� ��ǰ�� ã�� �� �����ϴ�");
				} else {
					table_3.requestFocus();
					table_3.changeSelection(rowSelect, 0, false, false);
				}
			}
		});
		panel_6.add(btnNewButton_2);
		
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
		
		// ��ǰ �뿩 ��ư
		JButton btnNewButton_3 = new JButton("\uC0C1\uD488 \uB300\uC5EC");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ��� �Է¶��� �Է��� �� �Ǿ��ٸ�
				if(textField_9.getText().equals("") || textField_10.getText().equals("") || textField_11.getText().equals("")) {
					JOptionPane.showMessageDialog(sales, "�̸�, ��ȭ��ȣ, �ݳ��������� ��� �Է����ּ���");
				}
				
				try {
				String userName = textField_9.getText();
				String phoneNum = textField_10.getText();
				Calendar today = Calendar.getInstance();
				String rentDate = new SimpleDateFormat("yyyyMMdd").format(today.getTime());
				// �ݳ� ������ �Է¹ް� yyyyMMdd ������ �ƴϸ� exception
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String dueDate = new SimpleDateFormat("yyyyMMdd").format(sdf.parse(textField_11.getText()));
				
					Date rentDate1 = sdf.parse(rentDate);
					Date dueData1 = sdf.parse(dueDate);
					if (rentDate1.after(dueData1)) {
						JOptionPane.showMessageDialog(sales, "�ݳ��������� ������ ��¥�� ���ų� ���� ��¥�� �Է����ּ���");
					}
					if (selectCnt == 0) {
						JOptionPane.showMessageDialog(sales, "��ǰ�� �������ּ���");
					} else if (selectCnt != 0 && !rentDate1.after(dueData1)) {
						User u = new User(userName, phoneNum, rentDate, dueDate);

						try {
							if (selectCnt == 1) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());
							} else if (selectCnt == 2) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());

								int index2 = m.search(lblNewLabel_18.getText());
								u.addCode(lblNewLabel_18.getText(), m.goodsAt(index2).getPrice());
							} else if (selectCnt == 3) {
								int index1 = m.search(lblNewLabel_17.getText());
								u.addCode(lblNewLabel_17.getText(), m.goodsAt(index1).getPrice());

								int index2 = m.search(lblNewLabel_18.getText());
								u.addCode(lblNewLabel_18.getText(), m.goodsAt(index2).getPrice());

								int index3 = m.search(lblNewLabel_19.getText());
								u.addCode(lblNewLabel_19.getText(), m.goodsAt(index3).getPrice());
							}

							m.checkIn(u);
							JOptionPane.showMessageDialog(sales, "��ǰ �뿩�� �Ϸ�Ǿ����ϴ�");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (ParseException pe) {
					if (!textField_11.getText().equals(""))
						JOptionPane.showMessageDialog(sales, "��¥�� ��Ȯ���� �ʽ��ϴ�\nyyyyMMdd �������� �Է����ּ���");
				}
				// �� �� �ؽ�Ʈ�ʱ� �ʱ�ȭ
				lblNewLabel_17.setText("(���� ����)");
				lblNewLabel_18.setText("(���� ����)");
				lblNewLabel_19.setText("(���� ����)");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				
				// ���̺� �� üũ�ڽ� ��Ȱ��ȭ, ���̺� �� ��ǰ ���� �ٲٱ�
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
				
				selectCnt = 0;
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
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBB3C\uD488\uBA85", "\uBB3C\uD488\uCF54\uB4DC", "\uAC00\uACA9", "\uC218\uB7C9", "\uC120\uD0DD"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_3.getColumnModel().getColumn(4).setPreferredWidth(15);
		scrollPane.setViewportView(table_3);
		
		// ��ǰ �뿩�� ���� ��ǰ ������ �� textArea�� ���ڸ� ��ǰ�ڵ�� ����
		// 3�� �̻��� ��ǰ ���� ���ϵ��� �ϱ�
		// ��� 0���� ��ǰ �������� ���ϵ��� �ϱ�
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_3.getSelectedRow();
				int col = table_3.getSelectedColumn();
				boolean selected = (boolean) table_3.getValueAt(row, col);
				int goodsCount = Integer.parseInt((String) table_3.getValueAt(row, 3));	// ��ǰ ���
				
				if (selectCnt < 3 && goodsCount > 0) {
					if (col == 4 && selected == true) {
						selectCnt++;
						if (selectCnt == 1) {
							lblNewLabel_17.setText((String) table_3.getValueAt(row, 1));
							row1 = row;
						} else if (selectCnt == 2) {
							lblNewLabel_18.setText((String) table_3.getValueAt(row, 1));
							row2 = row;
						} else if (selectCnt == 3) {
							lblNewLabel_19.setText((String) table_3.getValueAt(row, 1));
							row3 = row;
						}
					} else if (col == 4 && selected == false) {
						selectCnt--;
						if (selectCnt == 0) {
							lblNewLabel_17.setText("(���� ����)");
							row1 = -1;
						} else if (selectCnt == 1) {
							if(table_3.getValueAt(row, 1).equals(lblNewLabel_17.getText())) {
								lblNewLabel_17.setText(lblNewLabel_18.getText());
								row1 = row2;
								lblNewLabel_18.setText("(���� ����)");
								row2 = -1;
							} else if(table_3.getValueAt(row, 1).equals(lblNewLabel_18.getText())) {
								lblNewLabel_18.setText("(���� ����)");
								row2 = -1;
							}
						} else if (selectCnt == 2) {
							if(table_3.getValueAt(row, 1).equals(lblNewLabel_17.getText())) {
								lblNewLabel_17.setText(lblNewLabel_18.getText());
								row1 = row2;
								lblNewLabel_18.setText(lblNewLabel_19.getText());
								row2 = row3;
								lblNewLabel_19.setText("(���� ����)");
								row3 = -1;
							} else if(table_3.getValueAt(row, 1).equals(lblNewLabel_18.getText())) {
								lblNewLabel_18.setText(lblNewLabel_19.getText());
								row2 = row3;
								lblNewLabel_19.setText("(���� ����)");
								row3 = -1;
							} else if(table_3.getValueAt(row, 1).equals(lblNewLabel_19.getText())) {
								lblNewLabel_19.setText("(���� ����)");
								row3 = -1;
							}
						}
					}
				} else if (selectCnt == 3 && selected == false) {
					selectCnt--;
					if(table_3.getValueAt(row, 1).equals(lblNewLabel_17.getText())) {
						lblNewLabel_17.setText(lblNewLabel_18.getText());
						row1 = row2;
						lblNewLabel_18.setText(lblNewLabel_19.getText());
						row2 = row3;
						lblNewLabel_19.setText("(���� ����)");
						row3 = -1;
					} else if(table_3.getValueAt(row, 1).equals(lblNewLabel_18.getText())) {
						lblNewLabel_18.setText(lblNewLabel_19.getText());
						row2 = row3;
						lblNewLabel_19.setText("(���� ����)");
						row3 = -1;
					} else if(table_3.getValueAt(row, 1).equals(lblNewLabel_19.getText())) {
						lblNewLabel_19.setText("(���� ����)");
						row3 = -1;
					}
				} else if (goodsCount == 0) {
					JOptionPane.showMessageDialog(sales, "�ش� ��ǰ�� ��� ���� ���� �� �����ϴ�");
					table_3.setValueAt(false, row, 4);
				} else {
					JOptionPane.showMessageDialog(sales, "��ǰ�� 3������ ���� �����մϴ�");
					table_3.setValueAt(false, row, 4);
				}
				String g1 = lblNewLabel_17.getText();
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
		
		
		// ����� �뿩 ��� ��ȸ
		JButton btnNewButton_4 = new JButton("\uAC80\uC0C9");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_4.getModel();
				// �� �ʱ�ȭ, ���ұݾ��̶� ��ǰ ������ textfeild �� �ʱ�ȭ
				model.setNumRows(0);
				lblNewLabel_12.setText("(���� ����)");
				lblNewLabel_13.setText("(���� ����)");
				lblNewLabel_14.setText("(���� ����)");
				lblNewLabel_15.setText("(�� ���ұݾ�)");
				selectCnt2 = 0;
				totalPrice = 0;
				
				// ����� ��ȸ
				String userPhone = textField_12.getText();
				try {
					int userIndex = m.searchUser(userPhone);
					if (userIndex == -1) {
						JOptionPane.showMessageDialog(sales, "�ش� ����ڸ� ã�� �� �����ϴ�");
					} else {
						// ����ڿ��� ����ڸ� �����ֱ�
						lblNewLabel_16.setText(m.userAt(userIndex).getUserName());
						User u = m.userAt(userIndex);
						int rentalGoodsCount = u.getRentalCount();
						// ����ڿ��� �뿩 ��� �����ֱ�
						for (int i = 0; i < rentalGoodsCount; i++) {
							int rentGoodsIndex = m.search(m.userAt(userIndex).codeAt(i));
							Goods g = m.getGoodsList().get(rentGoodsIndex);
							
							String arr[] = new String[9];
							// ��ǰ �ڵ�
							arr[0] = g.getGoodsCode();
							// ��ǰ��
							arr[1] = g.getGoodsName();
							// ��ǰ ����
							arr[2] = Integer.toString(g.getPrice());
							// ���� ��¥
							arr[3] = u.getRentDate();
							// �뿩 ������
							arr[4] = u.getDueDate();
							Calendar today = Calendar.getInstance();
							String returnDate = new SimpleDateFormat("yyyyMMdd").format(today.getTime());
							int todayInt = Integer.parseInt(returnDate);
							int dueDate = Integer.parseInt(u.getDueDate());
							int daysLate;	// ��ü �ϼ�
							// ���� ���� ��¥�� �ݳ� �����Ϻ��� ������ ��ü�ϼ��� 0���� �����
							if(todayInt <= dueDate)
								daysLate = 0;
							// ���� ��¥�� �ݳ� �����Ϻ��� ������ ��ü�ϼ� ���� ǥ��
							else
								daysLate = todayInt - dueDate;
							String rentDays = Integer.toString(todayInt - Integer.parseInt(u.getRentDate()) + 1 - daysLate);
							// ��ü���� ���� �뿩�ϼ�
							arr[5] = rentDays;
							// ��ü�ϼ�
							arr[6] = Integer.toString(daysLate);
							// ���� ����
							arr[7] = Integer.toString(g.getPrice() * Integer.parseInt(rentDays));
							// ��ü��
							int lateFee = (int) (daysLate * g.getPrice() * 1.5);
							arr[8] = Integer.toString(lateFee);
							
							// ���̺� �� �߰�
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
		
		JLabel lblNewLabel_11 = new JLabel("              ");
		panel_19.add(lblNewLabel_11);
		
		panel_19.add(lblNewLabel_15);
		
		JLabel lblNewLabel_10 = new JLabel("\uC6D0\uC744 \uC9C0\uBD88\uD574\uC8FC\uC2ED\uC2DC\uC624");
		panel_19.add(lblNewLabel_10);
		
		JPanel panel_20 = new JPanel();
		panel_15.add(panel_20, BorderLayout.CENTER);
		panel_20.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_21 = new JPanel();
		panel_20.add(panel_21, BorderLayout.EAST);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\uACB0\uC81C\uC5D0 \uB3D9\uC758\uD569\uB2C8\uB2E4");
		
		// �ݳ� ��ư
		JButton btnNewButton_5 = new JButton("\uBB3C\uD488 \uBC18\uB0A9");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ƹ� ��ǰ�� �������� �ʰ� �ݳ���ư ������
				boolean selected1 = false;
				boolean selected = false;
				
				for(int j = table_4.getRowCount() - 1; j >= 0; j--) {
					if(table_4.getValueAt(j, 9) == null || (boolean) table_4.getValueAt(j, 9) == false)
						selected1 = false;
					else if ((boolean) table_4.getValueAt(j, 9) == true)
						selected1 = true;
					selected = selected || selected1;
				}
				
				if(selected == false)
					JOptionPane.showMessageDialog(sales, "�ݳ��Ͻ� ��ǰ�� �������ּ���");
				
				// �ݳ��� ��ǰ�� �ϳ��� �����ϸ�
				else {
					// ������ �����Ѵٴ� üũ�ڽ��� üũ�ϸ�
					if(chckbxNewCheckBox.isSelected()) {
						try {
							// ��ǰ �ݳ� ����
							int uIndex = m.searchUser(textField_12.getText());
							User u = m.userAt(uIndex);
							String gCode = "";
							
							for (int i = table_4.getRowCount() - 1; i >= 0; i--) {
								if (table_4.getValueAt(i, 9) == null || (boolean) table_4.getValueAt(i, 9) == false) {
									continue;
								} else if ((boolean) table_4.getValueAt(i, 9)) {
									// ��ǰ �ڵ�
									gCode = (String) table_4.getValueAt(i, 0);
									// ��� �߰�
									m.returnGoodsNew(uIndex, gCode);
									// ������ ��ǰ �뿩 ����Ʈ���� ��ǰ ����
									m.userAt(uIndex).subCode(u, gCode);
									// payList���� ��ǰ ���� ����
									m.userAt(uIndex).subPay(u, i);
									
									// ���̺��� �ش� �� ����
									DefaultTableModel model = (DefaultTableModel) table_4.getModel();
									model.removeRow(i);
								}
							}
							// �ݳ� ������ ���� sales �� �߰�
							m.addSales(totalPrice);
							
							// ���� ���� üũ�ڽ� false �� �ǵ�����
							chckbxNewCheckBox.setSelected(false);
							JOptionPane.showMessageDialog(sales, "��ǰ�� �ݳ��Ͽ����ϴ�");
							
							// �� �� ���� �ʱ�ȭ
							lblNewLabel_12.setText("(���� ����)");
							lblNewLabel_13.setText("(���� ����)");
							lblNewLabel_14.setText("(���� ����)");
							lblNewLabel_15.setText("(�� ���ұݾ�)");
							totalPrice = 0;
							selectCnt2 = 0;
							
							// ������� ��� ��ǰ �ݳ��Ǹ� ����ڸ� �뿩�� ��Ͽ��� ���� (üũ�ƿ�)
							if(u.getRentalCount() == 0) {
								m.checkOut(uIndex);
								JOptionPane.showMessageDialog(sales, "������� ��ǰ�� ��� �ݳ��Ǿ� �ڵ����� üũ�ƿ��˴ϴ�");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					// ������ �����ϴ� üũ�ڽ��� üũ���� ������ �ݳ� ���� X
					} else {
						JOptionPane.showMessageDialog(sales, "������ �������ּ���");
					}
				}
			}
		});
		panel_21.add(btnNewButton_5);
		
		JPanel panel_22 = new JPanel();
		panel_20.add(panel_22, BorderLayout.CENTER);
		
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
				"\uBB3C\uD488\uCF54\uB4DC", "\uBB3C\uD488\uBA85", "\uAC00\uACA9", "\uB300\uC5EC\uC77C", "\uBC18\uB0A9\uC608\uC815\uC77C", "\uC815\uC0C1\uB300\uC5EC\uC77C\uC218", "\uC5F0\uCCB4\uC77C\uC218", "\uC815\uC0C1\uB300\uC5EC\uB8CC", "\uC5F0\uCCB4\uB8CC", "\uC120\uD0DD"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_4.getColumnModel().getColumn(9).setPreferredWidth(25);
		scrollPane_1.setViewportView(table_4);
		
		// table_4 (�ݳ� ���� ���̺�)�� mouseListener
		table_4.addMouseListener(new MouseAdapter() {
			@Override
			// ���콺�� Ŭ���ϸ�
			public void mouseClicked(MouseEvent e) {
				// Ŭ���� �� selected ������ ����
				int row = table_4.getSelectedRow();
				int col = table_4.getSelectedColumn();
				boolean selected = (boolean) table_4.getValueAt(row, col);
				User u;
				try {
					// user ��ü
					u = m.userAt(m.searchUser(textField_12.getText()));
					
					// ��ǰ 1, 2, 3 �󺧿� ������ ������� ���� �ؽ�Ʈ �ٲٱ� ���� �ڵ�
					if (selectCnt2 < 3) {
						if (col == 9 && selected == true) {
							selectCnt2++;
							if (selectCnt2 == 1) {
								lblNewLabel_12.setText((String) table_4.getValueAt(row, 0));
							} else if (selectCnt2 == 2) {
								lblNewLabel_13.setText((String) table_4.getValueAt(row, 0));
							} else if (selectCnt2 == 3) {
								lblNewLabel_14.setText((String) table_4.getValueAt(row, 0));
							}
							try {
								// ��ǰ �ݳ� ������ ���� ���� totalPrice �� ���ϱ�
								totalPrice += u.payCalculateEach(row);
								lblNewLabel_15.setText(Integer.toString(totalPrice));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else if (col == 9 && selected == false) {
							selectCnt2--;
							if (selectCnt2 == 0) {
								lblNewLabel_12.setText("(���� ����)");
							} else if (selectCnt2 == 1) {
								if (table_4.getValueAt(row, 0).equals(lblNewLabel_12.getText())) {
									lblNewLabel_12.setText(lblNewLabel_13.getText());
									lblNewLabel_13.setText("(���� ����)");
								} else if (table_4.getValueAt(row, 0).equals(lblNewLabel_13.getText())) {
									lblNewLabel_13.setText("(���� ����)");
								}
							} else if (selectCnt2 == 2) {
								if (table_4.getValueAt(row, 0).equals(lblNewLabel_12.getText())) {
									lblNewLabel_12.setText(lblNewLabel_13.getText());
									lblNewLabel_13.setText(lblNewLabel_14.getText());
									lblNewLabel_14.setText("(���� ����)");
								} else if (table_4.getValueAt(row, 0).equals(lblNewLabel_13.getText())) {
									lblNewLabel_13.setText(lblNewLabel_19.getText());
									lblNewLabel_14.setText("(���� ����)");
								} else if (table_4.getValueAt(row, 0).equals(lblNewLabel_14.getText())) {
									lblNewLabel_14.setText("(���� ����)");
								}
							}
							try {
								// ��ǰ �ݳ� ��Ҹ� ���� ���� totalPrice ���� ����
								totalPrice -= u.payCalculateEach(row);
								lblNewLabel_15.setText(Integer.toString(totalPrice));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					} else if (selectCnt2 == 3 && selected == false) {
						selectCnt2--;
						if (table_4.getValueAt(row, 0).equals(lblNewLabel_12.getText())) {
							lblNewLabel_12.setText(lblNewLabel_18.getText());
							lblNewLabel_13.setText(lblNewLabel_19.getText());
							lblNewLabel_14.setText("(���� ����)");
						} else if (table_4.getValueAt(row, 0).equals(lblNewLabel_13.getText())) {
							lblNewLabel_13.setText(lblNewLabel_19.getText());
							lblNewLabel_14.setText("(���� ����)");
						} else if (table_4.getValueAt(row, 0).equals(lblNewLabel_14.getText())) {
							lblNewLabel_14.setText("(���� ����)");
						}
						try {
							// ��ǰ �ݳ� ��Ҹ� ���� ���� totalPrice ���� ����
							totalPrice -= u.payCalculateEach(row);
							lblNewLabel_15.setText(Integer.toString(totalPrice));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(sales, "��ǰ�� 3������ ���� �����մϴ�");
						table_4.setValueAt(false, row, 9);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

}