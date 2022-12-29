package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class UserSearch {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSearch window = new UserSearch();
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
	public UserSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uB300\uC5EC \uC815\uBCF4 \uD655\uC778");
		frame.setBounds(100, 100, 450, 265);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_3.setText("\uC870\uD68C\uD560 \uC0AC\uC6A9\uC790\uC758 \uC804\uD654\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setBounds(12, 10, 399, 24);
		frame.getContentPane().add(textArea_3);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText("\uC804\uD654\uBC88\uD638");
		textArea_1.setBounds(12, 44, 150, 24);
		frame.getContentPane().add(textArea_1);
		
		JButton btnNewButton = new JButton("\uC870\uD68C");
		btnNewButton.setBounds(174, 45, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uC774\uB984 ", "\uB300\uC5EC \uB0A0\uC9DC ", "\uBC18\uB0A9 \uC608\uC815 \uB0A0\uC9DC"},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBounds(12, 103, 361, 16);
		frame.getContentPane().add(table);
		
		JTextArea textArea_3_1_1 = new JTextArea();
		textArea_3_1_1.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_3_1_1.setText("<\uC0AC\uC6A9\uC790 \uC815\uBCF4>");
		textArea_3_1_1.setBackground(SystemColor.menu);
		textArea_3_1_1.setBounds(12, 78, 399, 24);
		frame.getContentPane().add(textArea_3_1_1);
		
		JTextArea textArea_3_1_2 = new JTextArea();
		textArea_3_1_2.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_3_1_2.setText("<\uB300\uC5EC \uBB3C\uD488>");
		textArea_3_1_2.setBackground(SystemColor.menu);
		textArea_3_1_2.setBounds(12, 131, 399, 24);
		frame.getContentPane().add(textArea_3_1_2);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uBB3C\uD488\uBA85 ", "\uBB3C\uD488 \uCF54\uB4DC ", null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.setBounds(12, 156, 361, 48);
		frame.getContentPane().add(table_1);
	}

}
