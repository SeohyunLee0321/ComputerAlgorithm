package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class Menu {

	private JFrame frmMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("Menu");
		frmMenu.setBounds(100, 100, 450, 190);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textArea_3_1 = new JTextArea();
		textArea_3_1.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3_1.setText("\uBB3C\uD488");
		textArea_3_1.setBackground(SystemColor.menu);
		textArea_3_1.setBounds(54, 10, 36, 24);
		panel.add(textArea_3_1);
		
		JButton btnNewButton = new JButton("\uBB3C\uD488 \uB4F1\uB85D");
		btnNewButton.setBounds(24, 44, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uBB3C\uD488 \uC0AD\uC81C");
		btnNewButton_1.setBounds(24, 77, 97, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\uBB3C\uD488 \uAC80\uC0C9");
		btnNewButton_1_1.setBounds(24, 110, 97, 23);
		panel.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		frmMenu.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3.setText("\uB300\uC5EC / \uBC18\uB0A9");
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setBounds(26, 10, 91, 24);
		panel_1.add(textArea_3);
		
		JButton btnNewButton_2 = new JButton("\uBB3C\uD488 \uB300\uC5EC");
		btnNewButton_2.setBounds(26, 44, 97, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("\uBB3C\uD488 \uBC18\uB0A9");
		btnNewButton_2_1.setBounds(26, 77, 97, 23);
		panel_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("\uB300\uC5EC \uD655\uC778");
		btnNewButton_2_1_1.setBounds(26, 110, 97, 23);
		panel_1.add(btnNewButton_2_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		frmMenu.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextArea textArea_3_2 = new JTextArea();
		textArea_3_2.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3_2.setText("\uB9E4\uCD9C / \uC800\uC7A5");
		textArea_3_2.setBackground(SystemColor.menu);
		textArea_3_2.setBounds(26, 10, 91, 24);
		panel_2.add(textArea_3_2);
		
		JButton btnNewButton_2_2 = new JButton("\uB9E4\uCD9C \uD655\uC778");
		btnNewButton_2_2.setBounds(26, 44, 97, 23);
		panel_2.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_2_1 = new JButton("\uC800\uC7A5");
		btnNewButton_2_2_1.setBounds(26, 77, 97, 23);
		panel_2.add(btnNewButton_2_2_1);
	}
}
