import java.util.Scanner;

class FieldExample2 {
	public static void main (String args[])
	{
		Scanner scan = new Scanner(System.in);
		Circle obj = new Circle(0.0);		// obj 이름의 circle 만들고 0.0 으로 초기화
		
		System.out.print("Enter radius : ");
		obj.setRadius(scan.nextDouble());		// 입력받은 double 을 setRadius 함수에 넣어 radius 에 넣기
		
		System.out.println("radius : " + obj.getRadius());		// radius 가져오기
		System.out.println("area : " + obj.getArea());		// area 가져오기
		System.out.println("circumference : " + obj.getCircum());		// circum 가져오기
		
		scan.close();
	}
}