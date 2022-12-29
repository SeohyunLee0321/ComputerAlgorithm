
class Circle {
	private double radius;		// 반지름
	final double PI = 3.14;		// 3.14 로 pi 를 설정하고 더 이상 바꿀 수 없도록 final
	
	public Circle(double radius)
	{
		this.radius = radius;
	}
	
	public void setRadius(double radius)		// 반지름 설정
	{
		this.radius = radius;
	}
	
	public double getRadius()		// 반지름을 return
	{
		return radius;
	}
	public double getArea()		// 넓이 return
	{
		double area;
		area = radius * radius * PI;
		return area;
	}
	
	public double getCircum()		// 원의 둘레 return
	{
		double circum;
		circum = radius * 2 * PI;
		return circum;
	}
}