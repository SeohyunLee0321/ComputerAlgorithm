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
	
	//상품 추가 시 사용
	public Product(String name, String code, int price, int stock)
	{
		this.name = name;
		this.code = code;
		this.price = price;
		this.stock = stock;
	}
	
	//상품 이름 set
	public void setName(String name)
	{
		this.name = name;
	}
	
	//상품 이름 get
	public String getName()
	{
		return name;
	}
	
	//상품 코드 set
	public void setCode(String code)
	{
		this.code = code;
	}
	
	//상품 코드 get
	public String getCode()
	{
		return code;
	}
	
	//상품 가격 set
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	//상품 가격 get
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
