public class MultithreadExample1 {
	public static void main(String args[]) {
		Thread thread = new DigitThread();	// ������ ����
		thread.start();	// ������ ����
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.print(ch);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

class DigitThread extends Thread {
	public void run() {
		for (int i = 0; i < 100; i++)
			for (int cnt = 0; cnt < 10; cnt++)
				System.out.print(cnt);
	}
}

// ��� ��� : A0123456789012345678901234567890123456789012345678901234BCDEFGHI5678901234567890123456789012345678901234567JKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW
// ���ĺ��� ���ڰ� ������ ��µ�