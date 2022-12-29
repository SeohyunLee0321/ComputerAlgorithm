import java.util.Scanner;

public class InputArrayAverage {
	public static void main(String[] args) {

		int n;
		int[] array;
		int sum = 0;

		Scanner scan = new Scanner(System.in);
		System.out.print("input number : ");

		n = scan.nextInt();
		array = new int[n];
	
		for (int i = 0; i < n; i++)
		{
			System.out.print("Input num" + (i + 1) + " : ");
			array[i] = scan.nextInt();
			sum += array[i];
		}
		
		System.out.println("Average : " + ((double)sum / (array.length)));
	}
}