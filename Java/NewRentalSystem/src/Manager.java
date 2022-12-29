public class Manager {
	
	//��ǰ �迭
	private int pListSize = 100;
	private Product[] products = new Product[pListSize];
	private int pSize = 0;
	
	//����� �迭
	private int uListSize = 100;
	private User[] users = new User[uListSize];
	private int uSize = 0;
	
	User u = new User();
	
	private int totalPay = 0;
	
	//��ǰ �߰�
	public void add(Product p)
	{
		products[pSize++] = p;
	}
	
	//��ǰ ����
	public void delete(int index)
	{
		for(int i = index; i < pSize; i++)
		{
			products[i] = products[i+1];
		}
		pSize--;
	}
	
	//����� ����
	public void deleteU(int index)
	{
		for(int i = index; i < uSize; i++)
		{
			users[i] = users[i+1];
		}
		uSize--;
		u.setRentSize(0);
	}
	
	//��ǰ �ڵ�� �迭 �˻� �� index ��ȯ
	public int findPCode(String pCode)
	{
		int index = -1;
		
		for(int i = 0; i < pSize; i++)
		{
			if(products[i].getPCode().equals(pCode))
				index = i;
		}
		return index;	//������ �ڵ尡 ������ �ش� index return, ������ �ڵ� ������ -1 return
	}
	
	//����� �߰�
	public void addU(User u)
	{
		users[uSize++] = u;
	}
	
	//����� �ڵ�� �迭 �˻� �� index ��ȯ
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
	
	//i ��° �迭 ��ȯ
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