public class Product {
	
	private String pCode;
	private String pName;
	private int pStock;
	private int price;
	
	public Product() {};
	
	//�뿩 list �� ���
	public Product(String pCode, String pName)
	{
		this.pCode = pCode;
		this.pName = pName;
	}
	
	//��ǰ ��� �� ���
	public Product(String pCode, String pName, int price, int pStock)
	{
		this.pCode = pCode;
		this.pName = pName;
		this.price = price;
		this.pStock = pStock;
	}
	
	public void setPrice(int price)
	{
		this.price = price;
	}
	
	public void setPCode(String pCode)
	{
		this.pCode = pCode;
	}
	
	public void setPName(String pName)
	{
		this.pName = pName;
	}
	
	public void setPStock(int pStock)
	{
		this.pStock = pStock;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public String getPCode()
	{
		return pCode;
	}
	
	public String getPName()
	{
		return pName;
	}
	
	public int getPStock()
	{
		return pStock;
	}
	
	public void addStock()
	{
		pStock++;
	}
	
	public void subtractStock() throws Exception
	{
		if(pStock < 1)
		{
			throw new Exception("Error : ��� ���ڶ��ϴ�");
		}
		else
			pStock--;
	}
}