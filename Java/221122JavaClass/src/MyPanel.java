import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("�ȳ��ϼ���? �ڹ� ���α׷��� ������!", 10, 10);
		g.drawLine(10, 20, 110, 20);
		g.drawRect(10, 30, 100, 100);
	}
}
