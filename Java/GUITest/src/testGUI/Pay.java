package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pay {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pay window = new Pay();
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
	public Pay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\uAE08\uC561 \uC9C0\uBD88");
		frame.setBounds(100, 100, 215, 115);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("\uAE08\uC561\uC744 \uC9C0\uBD88\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(12, 10, 399, 24);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(62, 43, 75, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
