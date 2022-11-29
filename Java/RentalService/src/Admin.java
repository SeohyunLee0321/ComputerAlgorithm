import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.event.*;

public class Admin extends JFrame {

	// �ϳ��� ��ġ�� ������ ��� ��ư ������ ������ ��� class�� ������ �����
	// admin initial�� �ִ� database.txt ��ã�� �͵� ��ü�� initialize �� �����ϸ����
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
	// ���ο��� ������ ��� ��ư�� ������ �� â�� ���� ������� ������ run()�� no body �Լ��� �����
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

		// ����� ������ ó������ ���̵��� ��
		// goodsList �ҷ�����
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

			// ���̺� �� �߰�
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(arr);
			
			// ��ǰ���� �������� �������� ����
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			table.setRowSorter(sorter);
			List <RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 0;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
		
		// userList �ҷ�����
		int userListLength = m.getUserList().size();
		
		for (int i = 0; i < userListLength; i++) {
			User u = m.getUserList().get(i);
			
			String arr[] = new String[8];
			arr[0] = u.getUserName();
			arr[1] = u.getPhoneNum();
			int rentalCount = u.getRentalCount();
			for(int j = 0; j < rentalCount; j++) {
				// goods�� �̸����� ���� ����Ʈ�� ������ ���� ��ǰ �����ֱ�
				arr[j + 2] =  m.getGoodsList().get(m.search(u.getRentalGoodsCode()[j])).getGoodsName();
			}
			arr[5] = u.getRentDate();
			arr[6] = u.getDueDate();
			arr[7] = Integer.toString(u.payCalculate());
			
			// ���̺� �� �߰�
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(arr);
			
			// user �̸��� �������� �������� ����
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(table_1.getModel());
			table_1.setRowSorter(sorter);
			List <RowSorter.SortKey> sortKeys = new ArrayList<>();
			int columnIndexToSort = 0;
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			 
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
		
		// salesList �ҷ�����
		int salesListLength = m.getsCount();
		
		for (int i = 0; i < salesListLength; i++) {
			String arr[] = new String[1];
			arr[0] = Integer.toString(m.getSalesList().get(i));
			
			// ���̺� �� �߰�
			DefaultTableModel model = (DefaultTableModel) table_2.getModel();
			model.addRow(arr);
		}
		// �Ѹ��� textArea�� setText ���ֱ�
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
		lblNewLabel_6.setFont(new Font("����", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 0;
		panel_3.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_4, BorderLayout.EAST);
		
		// ����ȭ������ ���ư��� ��ư
		JButton btnNewButton_1 = new JButton("����ȭ��");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// admin�� setVisible(false)�ϰ� main�� setVisible(true)
					admin.setVisible(false);
					main.getMainWindow().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel_4.add(btnNewButton_1);
		
		// ���� ��ư
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ����
				ObjectOutputStream oos = null;
				try {
					oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
					m.saveAll(oos);
					JOptionPane.showMessageDialog(admin, "������ �����߽��ϴ�");
				} catch (FileNotFoundException fnfe) { 
					JOptionPane.showMessageDialog(admin, "������ ã�� �� �����ϴ�");
				} catch (IOException ioe) { 
					JOptionPane.showMessageDialog(admin, "���� ����� �����Դϴ�");
				} catch (Exception a) {
					JOptionPane.showMessageDialog(admin, "������ ������ �� �����ϴ�");
				} finally {
					try {
						if(oos != null)
							oos.close();
					} catch (Exception a1) {
						JOptionPane.showMessageDialog(admin, "���� �ݱ⿡ �����߽��ϴ�");
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
		
		// ��ǰ������ ��ǰ �˻� -> �ش� �� focus
		JButton btnNewButton_2 = new JButton("�˻�");
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
					JOptionPane.showMessageDialog(admin, "ã���÷��� ��ǰ���� �Է����ּ���");
				} else if(rowSelect == -1) {
					JOptionPane.showMessageDialog(admin, "�Է��Ͻ� ��ǰ�� ã�� �� �����ϴ�");
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
		
		JLabel lblNewLabel_1 = new JLabel("��ǰ�� :");
		panel_10.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_10.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("��ǰ�ڵ� :");
		panel_10.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_10.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("���� :");
		panel_10.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_10.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("���� :");
		panel_10.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_10.add(textField_4);
		textField_4.setColumns(10);
		
		// �߰� ��ư ������ textField �� �Է��� ������ ���̺� �߰�
		// ���� �Ⱦ��� �߰� ������ JOptionPane.showMessageDialog
		JButton btnNewButton_3 = new JButton("�߰�");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ǰ��, ��ǰ�ڵ�, ����, ������ ��� �Է��ϰ� �߰� ��ư ������ ���������� �߰� ����
				if(!textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("") && !textField_4.getText().equals("")) {
					String arr[] = new String[4];
					arr[0] = textField_1.getText();
					arr[1] = textField_2.getText();
					arr[2] = textField_3.getText();
					arr[3] = textField_4.getText();
					
					try {
						int price = Integer.parseInt(arr[2]);
						int goodsCount = Integer.parseInt(arr[3]);
						
						// Goods ��ü�� ����� goodsList�� �߰�
						Goods g = new Goods(arr[0], arr[1], goodsCount, price);
						m.addGoods(g);
						
						// ���̺� �߰�
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(arr);
						
						// ��ǰ���� �������� �������� ����
						TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
						table.setRowSorter(sorter);
						List <RowSorter.SortKey> sortKeys = new ArrayList<>();
						int columnIndexToSort = 0;
						sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
						 
						sorter.setSortKeys(sortKeys);
						sorter.sort();
						
						// ���������� �߰��� �Ŀ��� textField �����
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
						textField_4.setText("");
					} catch (NumberFormatException nfe) {
						//textfield_3,4�� ���� �ƴ� ���� ������ catch
						JOptionPane.showMessageDialog(admin, "���ݰ� ������ ���ڷ� �Է����ּ���");
					} catch (Exception a) {	// �ߺ��� ��ǰ�ڵ尡 �̹� �����Ѵٸ�
						JOptionPane.showMessageDialog(admin, "��ǰ�ڵ� �ߺ��Դϴ�. �ٸ� ��ǰ�ڵ带 �Է����ּ���");
					}
				}
				else {
					// ��ǰ��, ��Ǫ���, ����, ���� �� �ϳ��� �Է����� �ʾҴٸ�
					JOptionPane.showMessageDialog(admin, "��� �׸��� �Է����ּ���");
				}
			}
		});
		panel_10.add(btnNewButton_3);
		
		JPanel panel_11 = new JPanel();
		tabbedPane_1.addTab("\uBB3C\uD488 \uC0AD\uC81C", null, panel_11, null);
		
		JLabel lblNewLabel_9 = new JLabel("��ǰ�ڵ� :");
		panel_11.add(lblNewLabel_9);
		
		// ������ ��ǰ�� �Է��ϴ� textField
		textField_5 = new JTextField();
		panel_11.add(textField_5);
		textField_5.setColumns(10);
		
		// �� �����ϰ� ���� ��ư ������ �� ����, ��ǰ�ڵ� �Է��ϰ� ���� ��ư ������ ����
		// �� �����ϰ� �Է��ϸ� �Է��� ��ǰ�� �����ϱ�
		// �� ���õ� ���ϰ� ��ǰ�ڵ嵵 �Է¾��ϸ� JOptionPane.showMessageDialog
		JButton btnNewButton_4 = new JButton("����");
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
						JOptionPane.showMessageDialog(admin, "�ش� ��ǰ�ڵ带 ���� ��ǰ�� �����ϴ�");
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
					JOptionPane.showMessageDialog(admin, "�����ϰ����ϴ� ���� �����ϰų� ��ǰ���� �Է��ϼ���!");
				}
			}
		});
		panel_11.add(btnNewButton_4);
		
		JPanel panel_12 = new JPanel();
		tabbedPane_1.addTab("\uC7AC\uACE0 \uAD00\uB9AC", null, panel_12, null);
		
		JLabel lblNewLabel_10 = new JLabel("���� :");
		panel_12.add(lblNewLabel_10);
		
		textField_6 = new JTextField();
		panel_12.add(textField_6);
		textField_6.setColumns(10);
		
		// ��� �߰�
		JButton btnNewButton_5 = new JButton("�߰�");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int addNum = Integer.parseInt(textField_6.getText());
					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(admin, "��� �����ϰ����ϴ� ���� �����ϼ���!");
					} else {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						try {
							// ���� goods�� ���� ����
							m.addGoodsStock(m.search(gCodeSelected), addNum);
							// ���̺��� ���� ����
							table.setValueAt(Integer.parseInt(table.getValueAt(row, 3).toString()) + addNum, row, 3);
							JOptionPane.showMessageDialog(admin, "��� �߰��߽��ϴ�");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(admin, "������ �Է��ϼ���");
				}
			}
		});
		panel_12.add(btnNewButton_5);
		
		// ��� ����
		JButton btnNewButton_6 = new JButton("����");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int subNum = Integer.parseInt(textField_6.getText());
					int row = table.getSelectedRow();

					if (row == -1) {
						JOptionPane.showMessageDialog(admin, "��� �����ϰ����ϴ� ���� �����ϼ���!");
					} else {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						try {
							// ���� goods�� ���� ����
							m.subGoodsStock(m.search(gCodeSelected), subNum);
							// ���̺��� ���� ����
							table.setValueAt(Integer.parseInt(table.getValueAt(row, 3).toString()) - subNum, row, 3);
							JOptionPane.showMessageDialog(admin, "��� ���ҽ��׽��ϴ�");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(admin, "��� �����Ϸ��� �������� �����մϴ�!\n�ٽ� �Է����ּ���");
							textField_6.setText("");
							textField_6.requestFocus();
						}
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(admin, "������ �Է��ϼ���");
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
				"��ǰ��", "��ǰ�ڵ�", "����", "����"
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
		
		// �뿩�� ��ȭ��ȣ�� �˻�-----------------------------------------------------------------------------------------------------------��ã��
		JButton btnNewButton_7 = new JButton("�˻�");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = m.searchUser(textField_7.getText());
					table_1.requestFocus();
					table_1.changeSelection(row, 0, false, false);
					
					if(textField_7.getText().equals("")) {
						JOptionPane.showMessageDialog(admin, "��ȭ��ȣ�� �Է����ּ���");
					} else if (row == -1) {
						JOptionPane.showMessageDialog(admin, "�Է��Ͻ� ��ȭ��ȣ�� ���� ����ڸ� ã�� �� �����ϴ�");
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