
class Circle {
	private double radius;		// ������
	final double PI = 3.14;		// 3.14 �� pi �� �����ϰ� �� �̻� �ٲ� �� ������ final
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	public void setRadius(double radius)		// ������ ����
	{
		this.radius = radius;
	}
	
	public double getRadius()		// �������� return
	{
		return radius;
	}
	public double getArea()		// ���� return
	{
		double area;
		area = radius * radius * PI;
		return area;
	}
	
	public double getCircum()		// ���� �ѷ� return
	{
		double circum;
		circum = radius * 2 * PI;
		return circum;
	}
}