public class MultithreadExample1 {
	public static void main(String args[]) {
		Thread thread = new DigitThread();	// 스레드 생성
		thread.start();	// 스레드 시작
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

// 출력 결과 : A0123456789012345678901234567890123456789012345678901234BCDEFGHI5678901234567890123456789012345678901234567JKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVW
// 알파벳과 숫자가 섞여서 출력됨