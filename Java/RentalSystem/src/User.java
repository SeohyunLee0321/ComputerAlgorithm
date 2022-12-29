public class User {
	private String name;
	private String productRent;
	private int phoneNum;
	private int rentDate;
	private int checkInDate;
	
	public User() {}
	
	public User(String name, int phoneNum, String productRent, int rentDate/*, int checkInDate*/)
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.productRent = productRent;
		//this.rentDate = rentDate;
		//this.checkInDate = checkInDate;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setProductName(String productName)
	{
		this.productRent = productRent;
	}
	
	public String getProductName()
	{
		return productRent;
	}
	
	public void setPhoneNum(int phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	
	public int getPhoneNum()
	{
		return phoneNum;
	}
	
	public void setRentDate(int rentDate)
	{
		this.rentDate = rentDate;
	}
	
	public int getRentDate()
	{
		return rentDate;
	}
	
	public void setCheckInDate(int checkInDate)
	{
		this.checkInDate = checkInDate;
	}
	
	public int getCheckInDateDate()
	{
		return checkInDate;
	}
}
