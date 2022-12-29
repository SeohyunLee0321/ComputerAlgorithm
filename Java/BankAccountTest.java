public class BankAccountTest
{
	public static void main (String[] args)
	{
		BankAccount a1 = new BankAccount();
		BankAccount a2 = new BankAccount();
		
		a1.deposit(100.45);
		a1.withdraw(60.78);

		a1.printBalance();
		System.out.println("a1의 연 이자는 : " + a1.addInterest());
		a1.withdraw(50.698);
		a1.printBalance();
		a1.withdraw(40.412);
		a1.printBalance();
		a1.withdraw(10.387);
		a1.printBalance();
	}
}