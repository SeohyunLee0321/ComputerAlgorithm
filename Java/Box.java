import java.util.Scanner;	//스캐너 클래스 사용

public class Box {
	public static void main(String[] args) {
		double width;	//밑변
		double height;	//높이
		double area;		//넓이
		double perimeter;	//둘레

		Scanner input = new Scanner(System.in);	//Scanner 객체 선언

		System.out.println("밑변은?");
		width = input.nextDouble();

		System.out.println("높이는?");
		height = input.nextDouble();

		area = width * height;
		perimeter = (width + height) * 2;

		System.out.println("사각형의 넓이 : " + area);
		System.out.println("사각형의 둘레 : " + perimeter);
	}
}