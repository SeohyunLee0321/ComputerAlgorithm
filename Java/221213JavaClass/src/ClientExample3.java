import java.net.*;

// Ŭ���̾�Ʈ ���α׷�
class ClientExample3 {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("###.###.###.###", 9001);
			Thread thread1 = new SenderThread(socket);
			Thread thread2 = new ReceiverThread(socket);
			thread1.start();
			thread2.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
