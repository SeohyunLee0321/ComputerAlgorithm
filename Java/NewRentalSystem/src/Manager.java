public class Manager {
	
	//물품 배열
	private int pListSize = 100;
	private Product[] products = new Product[pListSize];
	private int pSize = 0;
	
	//사용자 배열
	private int uListSize = 100;
	private User[] users = new User[uListSize];
	private int uSize = 0;
	
	User u = new User();
	
	private int totalPay = 0;
	
	//물품 추가
	public void add(Product p)
	{
		products[pSize++] = p;
	}
	
	//물품 삭제
	public void delete(int index)
	{
		for(int i = index; i < pSize; i++)
		{
			products[i] = products[i+1];
		}
		pSize--;
	}
	
	//사용자 삭제
	public void deleteU(int index)
	{
		for(int i = index; i < uSize; i++)
		{
			users[i] = users[i+1];
		}
		uSize--;
		u.setRentSize(0);
	}
	
	//물품 코드로 배열 검색 후 index 반환
	public int findPCode(String pCode)
	{
		int index = -1;
		
		for(int i = 0; i < pSize; i++)
		{
			if(products[i].getPCode().equals(pCode))
				index = i;
		}
		return index;	//동일한 코드가 있으면 해당 index return, 동일한 코드 없으면 -1 return
	}
	
	//사용자 추가
	public void addU(User u)
	{
		users[uSize++] = u;
	}
	
	//사용자 코드로 배열 검색 후 index 반환
	public int findUCode(String uCode)
	{
		int index = -1;
		
		for(int i = 0; i < uSize; i++)
		{
			if(users[i].getUCode().equals(uCode))
				index = i;
		}
		return index;
	}
	
	//i 번째 배열 반환
	public Product productAt(int i)
	{
		return products[i];
	}
	
	public User userAt(int i)
	{
		return users[i];
	}
	
	public int getPSize() 
	{
		return pSize;
	}
	
	public int getUSize()
	{
		return uSize;
	}
	
	public int getTotalPay()
	{
		return totalPay;
	}
	
	public void setTotalPay(int pay)
	{
		totalPay += pay;
	}
	
	public void resetPay()
	{
		totalPay = 0;
	}
}