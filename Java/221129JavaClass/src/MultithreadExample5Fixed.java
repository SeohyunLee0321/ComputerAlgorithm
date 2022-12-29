
public class MultithreadExample5Fixed {
	public static void main(String args[]) {
		SharedArea area = new SharedArea();
		area.account1 = new Account("111-111-1111", "ÀÌ¸ù·æ", 20000000);
		area.account2 = new Account("222-222-2222", "ÀÌ¸ù·æ", 10000000);
		TransferThread thread1 = new TransferThread(area);
		PrintThread thread2 = new PrintThread(area);
		thread1.start();
		thread2.start();
	}
}

class SharedArea {
	Account account1;
	Account account2;
}

class Account {
	String accountNo; // °èÁÂ¹øÈ£
	String ownerName; // ¿¹±ÝÁÖ ÀÌ¸§
	int balance; // ÀÜ¾×

	Account(String accountNo, String ownerName, int balance) {
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
	}

	void deposit(int amount) {
		balance += amount;
	}

	int withdraw(int amount) {
		if (balance < amount)
			return 0;
		balance -= amount;
		return amount;
	}
}

class TransferThread extends Thread {
	SharedArea sharedArea;

	TransferThread(SharedArea area) { // »ý¼ºÀÚ
		sharedArea = area;
	}

	public void run() {
		for (int cnt = 0; cnt < 12; cnt++) {
			synchronized(sharedArea) {
				sharedArea.account1.withdraw(1000000);
				System.out.print("ÀÌ¸ù·æ °èÁÂ: 100¸¸¿ø ÀÎÃâ,");
				sharedArea.account2.deposit(1000000);
				System.out.println("¼ºÃáÇâ °èÁÂ: 100¸¸¿ø ÀÔ±Ý");
			}
			/*
			sharedArea.account1.withdraw(1000000);
			System.out.print("ÀÌ¸ù·æ °èÁÂ: 100¸¸¿ø ÀÎÃâ,");
			sharedArea.account2.deposit(1000000);
			System.out.println("¼ºÃáÇâ °èÁÂ: 100¸¸¿ø ÀÔ±Ý");
			*/
		}
	}
}

class PrintThread extends Thread {
	SharedArea sharedArea;

	PrintThread(SharedArea area) { // »ý¼ºÀÚ
		sharedArea = area;
	}

	public void run() {
		for (int cnt = 0; cnt < 3; cnt++) {
			synchronized(sharedArea) {
				int sum = sharedArea.account1.balance + sharedArea.account2.balance;
				System.out.println("°èÁÂ ÀÜ¾× ÇÕ°è: " + sum);
			}
			try {
				Thread.sleep((long) 1);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			/*int sum = sharedArea.account1.balance + sharedArea.account2.balance;
			System.out.println("°èÁÂ ÀÜ¾× ÇÕ°è: " + sum);
			try {
				Thread.sleep((long) 1);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}*/
		}
	}
}