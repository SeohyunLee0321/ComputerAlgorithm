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
			int totalPay = 0;
			for(int j = 0; j < rentalCount; j++) {
				// goods�� �̸����� ���� ����Ʈ�� ������ ���� ��ǰ �����ֱ�
				arr[j + 2] =  m.getGoodsList().get(m.search(u.getRentalGoodsCode()[j])).getGoodsName();
				totalPay += u.payCalculateEach(j);
			}
			arr[5] = u.getRentDate();
			arr[6] = u.getDueDate();
			arr[7] = Integer.toString(totalPay);
			
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
		
		// �Ѹ��� textArea�� setText ���ֱ�
		lblNewLabel_13.setText(Integer.toString(m.getDailySales()));
		
		// �߰���� : �̹��� ����� ���� �ڵ� ------------------------------------------------------------------------------------------------
		// �̹��� ����� ���� ��ǰ��� ��ǰ�ڵ�, �̹��� ��� �ҷ�����
		for (int i = 0; i < goodsListLength; i++) {
			Goods g = m.getGoodsList().get(i);
			
			String arr[] = new String[3];
			arr[0] = g.getGoodsName();
			arr[1] = g.getGoodsCode();
			arr[2] = g.getImage();
			
			// ���̺� �� �߰�
			DefaultTableModel model = (DefaultTableModel) table_2.getModel();
			model.addRow(arr);
			
			// ��ǰ���� �������� �������� ����
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
		lblNewLabel_6.setFont(new Font("����", Font.PLAIN, 15));
		panel_3.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel panel_17 = new JPanel();
		panel_3.add(panel_17, BorderLayout.EAST);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_12 = new JLabel("\uCD1D \uB9E4\uCD9C : ");
		lblNewLabel_12.setFont(new Font("����", Font.BOLD, 12));
		panel_17.add(lblNewLabel_12, BorderLayout.WEST);
		
		panel_17.add(lblNewLabel_13, BorderLayout.CENTER);
		
		JLabel lblNewLabel_14 = new JLabel(" \uC6D0");
		lblNewLabel_14.setFont(new Font("����", Font.BOLD, 12));
		panel_17.add(lblNewLabel_14, BorderLayout.EAST);
		
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
						Goods g = new Goods(arr[0], arr[1], goodsCount, price, null);
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
		
		// ������ ��ǰ�ڵ� �Է��ϴ� textField
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
						JOptionPane.showMessageDialog(admin, "��ǰ�� �����߽��ϴ�");
					} catch (Exception e1) {
						textField_5.requestFocus();
						JOptionPane.showMessageDialog(admin, "�ش� ��ǰ�ڵ带 ���� ��ǰ�� �����ϴ�");
					}
				} else if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String gCodeSelected = table.getValueAt(row, 1).toString();
					try {
						m.deleteGoods(gCodeSelected);
						model.removeRow(row);
						JOptionPane.showMessageDialog(admin, "��ǰ�� �����߽��ϴ�");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					textField_5.requestFocus();
					JOptionPane.showMessageDialog(admin, "�����ϰ����ϴ� ���� �����ϰų� ��ǰ�ڵ带 �Է��ϼ���!");
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
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "��ǰ��", "��ǰ�ڵ�", "����", "����" }) {
			// ���̺� ���ø� ����, ���� �Ұ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(table);
		
		// ��ǰ������ �˻�
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		sorter.setRowFilter(RowFilter.regexFilter(textField.getText().trim()));
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				String text = textField.getText();
				
				// textField�� �Է��ϸ� �ٷ� sort
				table.setRowSorter(sorter);

				if (text.trim().length() == 0) { // ���ڿ� ����
					sorter.setRowFilter(null);
				} else {
					// ��ǰ�� �˻��� �� �ֵ��� ����
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // ��ҹ��� ��� ���� �˻�
				}
				// rowchange �������� ȣ��
				// ȣ������ ������ IndexOutOfBoundsException �߻�
				sorter.allRowsChanged();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				// textField�� �Է��ϸ� �ٷ� sort
				table.setRowSorter(sorter);
				
				String text = textField.getText();

				if (text.trim().length() == 0) {
					// row�� sort, filter ���ÿ� ����
					sorter.setRowFilter(null);
				} else {
					// ��ǰ�� �˻��� �� �ֵ��� ����
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // ��ҹ��� ��� ���� �˻�
				}
				// rowchange �������� ȣ��
				// ȣ������ ������ IndexOutOfBoundsException �߻�
				sorter.allRowsChanged();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("�ش� ���񽺴� �������� �ʽ��ϴ�."); // ArrayList�� ���� �߰� / ���� / �����Ϸ��� ��� �ͼ��� �߻�
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
			// ���̺� ���ø� ����, ���� �Ұ�
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		// �̸� / ��ȭ��ȣ�� �뿩�� �˻�
		TableRowSorter<TableModel> sorter_1 = new TableRowSorter<>(table_1.getModel());
		table_1.setRowSorter(sorter_1);
		
		textField_7.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				// textField�� �Է��ϸ� �ٷ� sort���ֱ� ���� �ڵ� �߰�
				table_1.setRowSorter(sorter_1);
				
				String text = textField_7.getText();

				if (text.trim().length() == 0) { // ���ڿ� ����
					sorter_1.setRowFilter(null);
				} else {
					// ��ȭ��ȣ�� �̸� �࿡���� sort�ϵ��� ����
					sorter_1.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1)); // ��ҹ��� ��� ���� �˻�
				}
				// rowchange �������� ȣ��
				// ȣ������ ������ IndexOutOfBoundsException �߻�
				sorter_1.allRowsChanged();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// textField�� �Է��ϸ� �ٷ� sort���ֱ� ���� �ڵ� �߰�
				table_1.setRowSorter(sorter_1);
				
				String text = textField_7.getText();

				if (text.trim().length() == 0) {
					sorter_1.setRowFilter(null); // row�� sort, filter ���ÿ� ����
				} else {
					// ��ȭ��ȣ�� �̸� �࿡���� sort�ϵ��� ����
					sorter_1.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0, 1)); // ��ҹ��� ��� ���� �˻�
				}
				// rowchange �������� ȣ��
				// ȣ������ ������ IndexOutOfBoundsException �߻�
				sorter_1.allRowsChanged();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// ArrayList�� ���� �߰� / ���� / �����Ϸ��� ��� �ͼ��� �߻�
				throw new UnsupportedOperationException("�ش� ���񽺴� �������� �ʽ��ϴ�.");
			}
		});

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("�̹��� ���", null, panel_8, null);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_8.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_15.add(scrollPane_2, BorderLayout.CENTER);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\uBB3C\uD488\uBA85", "\uBB3C\uD488 \uCF54\uB4DC", "\uC774\uBBF8\uC9C0 \uACBD\uB85C" }) {
			// ���̺� ���ø� ����, ���� �Ұ�
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
		
		// �߰� ��� : �̹����� ����� ���� �ڵ� ---------------------------------------------------------------------------------------------
		JButton btnNewButton_8 = new JButton("\uC774\uBBF8\uC9C0 \uB4F1\uB85D");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���õ� �� row ������ ����
				int row = table_2.getSelectedRow();
				// ���� �������� �ʾҴٸ� �޽��� ���
				if (row == -1) {
					JOptionPane.showMessageDialog(admin, "�̹����� ����ϰ����ϴ� ���� �����ϼ���!");
				} else {	// ���� �����ߴٸ�
					// ������ ���� ��ǰ �ڵ� gCodeSelected �� ����
					String gCodeSelected = table.getValueAt(row, 1).toString();
					// �̹��� ��θ� file1 ������ ����
					// pics ���� �ȿ� �̹������� �����ؾ��ϴ� ������ �Ŵ��� ������� ������ ���ϸ� �տ� pics/ ����
					File file1 = new File("pics/" + textField_8.getText());
					
					// �̹��� ��θ� �Է����� ������
					if(textField_8.getText().equals("")) {
						JOptionPane.showMessageDialog(admin, "�̹��� ��θ� �Է����ּ���");
					}
					// ������ pics ���� �ȿ� �ְ� �̹��� ��θ� �Է�������
					else if(!textField_8.getText().equals("") && file1.exists())
						try {
							
							// goods�� image�� ���� ��� set
							m.setImage(m.search(gCodeSelected), "pics/" + textField_8.getText());
							// ���̺� ���� ����
							table_2.setValueAt("pics/" + textField_8.getText(), row, 2);
							JOptionPane.showMessageDialog(admin, "�̹����� ��ϵǾ����ϴ�");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					// ������ pics ���� �ȿ� ������
					else
						JOptionPane.showMessageDialog(admin, "�̹����� ���丮�� ���� �������ּ���");
				}
			}
		});
		panel_16.add(btnNewButton_8);
		
		// �߰� ��� : ��ϵ� �̹����� �����ϱ� ���� �ڵ� -------------------------------------------------------------------------------------------
		JButton btnNewButton_9 = new JButton("\uC774\uBBF8\uC9C0 \uC0AD\uC81C");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_2.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(admin, "�̹����� �����ϰ����ϴ� ���� �����ϼ���!");
				} else {
					try {
						String gCodeSelected = table.getValueAt(row, 1).toString();
						// ��ǰ�� �̹��� ��ΰ� ��ϵǾ����� ������ �޽��� ���
						if (m.goodsAt(m.search(gCodeSelected)).getImage() == null) {
							JOptionPane.showMessageDialog(admin, "�� ��ǰ�� �̹����� ��ϵǾ����� �ʽ��ϴ�");
						} else {
							// goods�� image�� ���� ��� set
							m.setImage(m.search(gCodeSelected), null);
							// ���̺� ���� ����
							table_2.setValueAt(null, row, 2);
							JOptionPane.showMessageDialog(admin, "�̹����� �����Ǿ����ϴ�");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel_16.add(btnNewButton_9);
		
		// �߰� ��� : �̹����� ���� ���� �ڵ� (��ǰ ���� ���̺�) -------------------------------------------------------------------------------------------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ��ǰ�� ���� ����Ŭ���ϸ�
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					try {
						// �̹��� setVisible(true);
						String imgRoute = m.goodsAt(m.search((String) table.getValueAt(row, 1))).getImage();
						// goods �� �̹��� ��ΰ� ��ϵǾ��ִٸ�
						if (imgRoute != null) {
							img = ImageIO.read(new File(imgRoute));
							getContentPane().add(new MyPanel());
							pack();
							setVisible(true);
						} else {
							JOptionPane.showMessageDialog(admin, "��ǰ�� �̹����� ��ϵǾ����� �ʽ��ϴ�");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		// �߰� ��� : �̹����� ���� ���� �ڵ� (�̹��� ��� ���̺�) -------------------------------------------------------------------------------------------
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ��ǰ�� ���� ����Ŭ���ϸ�
				if (e.getClickCount() == 2) {
					int row = table_2.getSelectedRow();
					try {
						// �̹��� setVisible(true);
						String imgRoute = m.goodsAt(m.search((String) table_2.getValueAt(row, 1))).getImage();
						// goods �� �̹��� ��ΰ� ��ϵǾ��ִٸ�
						if (imgRoute != null) {
							img = ImageIO.read(new File(imgRoute));
							getContentPane().add(new MyPanel());
							pack();
							setVisible(true);
						} else {
							JOptionPane.showMessageDialog(admin, "��ǰ�� �̹����� ��ϵǾ����� �ʽ��ϴ�");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	// �߰� ��� : ��ǰ ����Ŭ�� �� �̹��� �������� MyPanel Ŭ����------------------------------------------------------------------------------------
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