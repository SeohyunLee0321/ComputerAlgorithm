class BankAccount
{
	double balance;

	public void deposit(double amount)
	{
		balance += amount;
		System.out.println(balance + " 원이 예금되었습니다.");
	}

	public void withdraw(double amount)
	{
		if (balance == 0)
			System.out.println("잔고가 없습니다. 인출 실패!!");

		if (balance - amount < 0)
			System.out.println("잔액이 부족합니다. 인출 실패!!");
		else
		{
			balance -= amount;
			System.out.println(amount + " 원이 인출되었습니다");
		}
	}

	public double getBalance()
	{
		return balance;
	}
	
	public void printBalance()
	{
		System.out.println("현재 잔액 : " + balance);
	}

	public double addInterest()
	{
		return balance * 0.075;
	}
}