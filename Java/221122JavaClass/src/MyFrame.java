import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("MyFrame");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		MyPanel c = new MyPanel();
		add(c);
	}
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
	}
}
