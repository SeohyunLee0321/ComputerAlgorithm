package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import java.awt.Font;

public class Goods {

	private JFrame frame;
	private JTable table;
	private JButton btnNewButton;
	private JTextArea textArea;
	private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Goods window = new Goods();
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
	public Goods() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uBB3C\uD488 \uAC80\uC0C9");
		frame.setBounds(100, 100, 450, 288);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uBB3C\uD488\uBA85", "\uBB3C\uD488 \uCF54\uB4DC ", "\uAC00\uACA9", "\uC7AC\uACE0 \uC218\uB7C9 "},
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
				"\uBB3C\uD488\uBA85", "\uC7AC\uACE0 \uC218\uB7C9", "\uAC00\uACA9", ""
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setBounds(12, 42, 405, 162);
		frame.getContentPane().add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(417, 42, 17, 162);
		frame.getContentPane().add(scrollBar);
		
		btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.setBounds(351, 214, 66, 23);
		frame.getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea.setText("\uAC80\uC0C9\uD560 \uBB3C\uD488\uC758 \uCF54\uB4DC\uB97C \uC785\uB825\uD558\uC138\uC694");
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(12, 10, 399, 24);
		frame.getContentPane().add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setText("\uBB3C\uD488 \uCF54\uB4DC");
		textArea_1.setBounds(192, 213, 150, 24);
		frame.getContentPane().add(textArea_1);
	}
}
