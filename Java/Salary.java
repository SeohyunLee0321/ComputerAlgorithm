


import java.util.Scanner;

public class Salary {
	public static void main(String Args[]) {
		int salary;
		int deposit;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter salary : ");
		salary = input.nextInt();

		deposit = 10 * 12 * salary;
		System.out.printf("Deposit for 10 years : %d\n", deposit);
	} //end method main

} //end class Salary