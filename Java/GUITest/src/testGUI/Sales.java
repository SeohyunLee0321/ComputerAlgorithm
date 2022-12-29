package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import java.awt.Font;

public class Sales {

	private JFrame frame;
	private final JTable table = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales window = new Sales();
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
	public Sales() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uB9E4\uCD9C \uD655\uC778");
		frame.setBounds(100, 100, 200, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table.setBounds(12, 40, 155, 160);
		frame.getContentPane().add(table);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3.setText("<\uB9E4\uCD9C \uB9AC\uC2A4\uD2B8>");
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setBounds(12, 10, 107, 24);
		frame.getContentPane().add(textArea_3);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(167, 40, 17, 160);
		frame.getContentPane().add(scrollBar);
		
		JTextArea textArea_3_1 = new JTextArea();
		textArea_3_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3_1.setText("<\uCD1D \uB9E4\uCD9C>");
		textArea_3_1.setBackground(SystemColor.menu);
		textArea_3_1.setBounds(12, 215, 102, 24);
		frame.getContentPane().add(textArea_3_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(12, 245, 135, 24);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_3_1_1 = new JTextArea();
		textArea_3_1_1.setText("\uC6D0");
		textArea_3_1_1.setBackground(SystemColor.menu);
		textArea_3_1_1.setBounds(150, 245, 17, 24);
		frame.getContentPane().add(textArea_3_1_1);
	}
}
