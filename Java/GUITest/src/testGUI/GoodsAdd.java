package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Font;

public class GoodsAdd {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodsAdd window = new GoodsAdd();
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
	public GoodsAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uBB3C\uD488 \uB4F1\uB85D");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"\uBB3C\uD488\uBA85 ", "\uBB3C\uD488 \uCF54\uB4DC", "\uAC00\uACA9 ", "\uC7AC\uACE0 \uC218\uB7C9 "},
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
		));
		table.setBounds(12, 10, 405, 161);
		frame.getContentPane().add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(417, 10, 17, 160);
		frame.getContentPane().add(scrollBar);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("\uBB3C\uD488\uBA85");
		textArea.setBounds(12, 220, 75, 24);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\uCD94\uAC00");
		btnNewButton.setBounds(356, 221, 66, 23);
		frame.getContentPane().add(btnNewButton);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText("\uBB3C\uD488 \uCF54\uB4DC");
		textArea_1.setBounds(99, 220, 75, 24);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_1_1 = new JTextArea();
		textArea_1_1.setText("\uAC00\uACA9");
		textArea_1_1.setBounds(186, 220, 75, 24);
		frame.getContentPane().add(textArea_1_1);
		
		JTextArea textArea_1_1_1 = new JTextArea();
		textArea_1_1_1.setText("\uC218\uB7C9");
		textArea_1_1_1.setBounds(273, 220, 75, 24);
		frame.getContentPane().add(textArea_1_1_1);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea_3.setText("\uB4F1\uB85D\uD560 \uBB3C\uD488\uC758 \uC815\uBCF4\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setBounds(12, 186, 399, 24);
		frame.getContentPane().add(textArea_3);
	}

}
