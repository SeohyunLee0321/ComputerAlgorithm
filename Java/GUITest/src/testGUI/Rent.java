package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;

public class Rent {

	private JFrame frame;
	private JTable table;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JButton btnNewButton;
	private JTextArea textArea_3;
	private JTextArea textArea_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rent window = new Rent();
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
	public Rent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uBB3C\uD488 \uB300\uC5EC");
		frame.setBounds(100, 100, 450, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uBB3C\uD488\uBA85 ", "\uBB3C\uD488 \uCF54\uB4DC ", "\uAC00\uACA9 ", null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.setBounds(12, 280, 399, 97);
		frame.getContentPane().add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(412, 280, 17, 97);
		frame.getContentPane().add(scrollBar);
		
		textArea = new JTextArea();
		textArea.setText("\uC774\uB984");
		textArea.setBounds(12, 40, 75, 24);
		frame.getContentPane().add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setText("\uC804\uD654\uBC88\uD638");
		textArea_1.setBounds(99, 40, 150, 24);
		frame.getContentPane().add(textArea_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setText("\uBC18\uB0A9 \uC608\uC815\uC77C(yyyymmdd)");
		textArea_2.setBounds(261, 40, 150, 24);
		frame.getContentPane().add(textArea_2);
		
		btnNewButton = new JButton("\uB300\uC5EC");
		btnNewButton.setBounds(315, 387, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_3.setBackground(new Color(240, 240, 240));
		textArea_3.setText("\uC0AC\uC6A9\uC790\uC758 \uC815\uBCF4\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textArea_3.setBounds(12, 10, 399, 24);
		frame.getContentPane().add(textArea_3);
		
		textArea_4 = new JTextArea();
		textArea_4.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_4.setText("\uB300\uC5EC\uD560 \uBB3C\uD488\uC744 \uC120\uD0DD\uD574\uC8FC\uC138\uC694 (\uCD5C\uB300 3\uAC1C)");
		textArea_4.setBackground(SystemColor.menu);
		textArea_4.setBounds(12, 246, 399, 24);
		frame.getContentPane().add(textArea_4);
	}
}
