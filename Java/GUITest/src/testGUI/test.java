package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

public class test {

	private JFrame frmDatabseFileNot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frmDatabseFileNot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatabseFileNot = new JFrame();
		frmDatabseFileNot.setTitle("Database File Not Found");
		frmDatabseFileNot.setBounds(100, 100, 445, 89);
		frmDatabseFileNot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabseFileNot.getContentPane().setLayout(null);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setText("database.txt \uD30C\uC77C\uC744 \uCC3E\uACE0 \uD604 \uB514\uB809\uD1A0\uB9AC\uC5D0 \uC800\uC7A5\uD574\uC8FC\uC138\uC694");
		textArea_3.setFont(new Font("Monospaced", Font.BOLD, 15));
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setBounds(12, 10, 410, 24);
		frmDatabseFileNot.getContentPane().add(textArea_3);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
