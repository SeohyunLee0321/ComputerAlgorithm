import java.util.Scanner;

class FieldExample2 {
	public static void main (String args[])
	{
		Scanner scan = new Scanner(System.in);
		Circle obj = new Circle(0.0);		// obj �̸��� circle ����� 0.0 ���� �ʱ�ȭ
		
		System.out.print("Enter radius : ");
		obj.setRadius(scan.nextDouble());		// �Է¹��� double �� setRadius �Լ��� �־� radius �� �ֱ�
		
		System.out.println("radius : " + obj.getRadius());		// radius ��������
		System.out.println("area : " + obj.getArea());		// area ��������
		System.out.println("circumference : " + obj.getCircum());		// circum ��������
		
		scan.close();
	}
}