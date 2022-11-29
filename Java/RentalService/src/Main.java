import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

// Main -> MainFrame ���� Ŭ���� �̸� ��ġ��
public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JFrame main;
	Management m = new Management();

	JFrame getMainWindow () {
		return main;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() throws Exception {
		initialize();
		// database.txt ���� �˻�
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Database.txt"));
			m = new Management(100, 100, 100, ois);
		} catch (FileNotFoundException fnfe) {
			// ���� �н� ��, ��Ȳ ����
			String[] buttons = { "���α׷� ���� ����", "���� �н�" };
			var num = JOptionPane.showOptionDialog(null, "Database.txt ������ �����ϴ�. �˸��� ��Ȳ�� ����ּ���", "Database.txt ���� ����",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, null);

			// ���α׷� ���� ���� ��ư Ŭ�� -> ���α׷��� ���� �����մϴ� -> ���� ���� / ���� ����
			if (num == 0) {
				JOptionPane.showMessageDialog(null, "���α׷��� ���� �����մϴ�");
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
			}
			// ���� �н� Ŭ�� -> ������ ã�� ���丮�� ������ �� �ٽ� �������ּ��� -> ����
			if (num == 1) {
				JOptionPane.showMessageDialog(null, "������ ã�� ���丮�� ������ �� �ٽ� �������ּ���");
				System.exit(0);
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	public void initialize() throws Exception {
		main = new JFrame();
		main.setBounds(100, 100, 473, 274);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		main.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {300};
		gbl_panel.rowHeights = new int[]{24, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("\uB80C\uD0C8 \uC2DC\uC2A4\uD15C\uC785\uB2C8\uB2E4. \uC6D0\uD558\uB294 \uBA54\uB274\uB97C \uC120\uD0DD\uD574\uC8FC\uC138\uC694");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		main.getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		// �������� �� �� �ִ� ��ư
		JButton btnNewButton_1 = new JButton("���� ���");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// ����ȭ�� �Ⱥ��̵��� setVisible(false)
					main.setVisible(false);
					// sales new�ϰ� setVisible(true)
					Sales sales = new Sales();
					sales.getSalesWindow().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		panel_1.add(btnNewButton_1, gbc_btnNewButton_1);
		
		// ������ ��� Ŭ���ϸ� ������ ����
		JButton btnNewButton = new JButton("������ ���");
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// ����ȭ�� �Ⱥ��̵��� setVisible(false)
					main.setVisible(false);
					// admin new�ϰ� setVisible(true)
					Admin admin = new Admin();
					admin.getAdmin().setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);
	}
}