import java.util.*;

public class Product {
	
	private String name;
	private String code;
	private int price;
	private int stock;
	/*private int checkoutDate;
	private int checkinDate;
	private int peroid;*/
	
	public Product(){}
	
	//��ǰ �߰� �� ���
	public Product(String name, String code, int price, int stock)
	{
		this.name = name;
		this.code = code;
		this.price = price;
		this.stock = stock;
	}
	
	//��ǰ �̸� set
	public void setName(String name)
	{
		this.name = name;
	}
	
	//��ǰ �̸� get
	public String getName()
	{
		return name;
	}
	
	//��ǰ �ڵ� set
	public void setCode(String code)
	{
		this.code = code;
	}
	
	//��ǰ �ڵ� get
	public String getCode()
	{
		return code;
	}
	
	//��ǰ ���� set
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	//��ǰ ���� get
	public int getPrice()
	{
		return price;
	}
	
	public void setStock(int stock)
	{
		this.stock = stock;
	}
	
	public int getStock()
	{
		return stock;
	}
}
