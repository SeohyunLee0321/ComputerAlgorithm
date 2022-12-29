import java.util.*;

public class Manager {
	
	private int arrayLength = 100;	//�迭 ����
	private int index = 0;		//ã�� �迭 ��ġ
	private int size = 0;
	private int usersIndex = 0;
	
	private Product[] products = new Product[arrayLength];		//�迭 ����
	private User[] users = new User[arrayLength];
	private User[] rental = new User[arrayLength];
	
	private static Manager mgr = new Manager();	
	private Manager() {}
	public static Manager getInstance() 
	{	//Manager�� mgr �̸����� ��ü ����, getInstance�� ������ ��ü �޾ƿ���
		return mgr;
	}
	
	//��ǰ �ڵ�� �˻�
	public int search(String code) throws Exception
	{
		int searchIndex = 0;	//�˻� ��ǰ index
		
			try
			{
				for(int i = 0; i < index; i++)
				{
					if(products[i].getCode().equals(code))	//�Է��� �ڵ�� ����� ��ǰ �ڵ尡 ���ٸ�
					{
						System.out.println(products[i].getCode());
						System.out.println(code);
						searchIndex = i;	//searchIndex�� i ���� �� break
						break;
					}
					else
					{
						searchIndex = -1;
					}
				}
				if(searchIndex == -1)
				{
					Exception e = new Exception("ã���ô� ��ǰ�� �����ϴ�");
					throw e;
				}
			}
			catch(Exception e)	//�Է��� �ڵ�� ���� �ڵ� ���ٸ�
			{
				System.out.println(e.getMessage());
			}
			
		return searchIndex;		//searchIndex ��ȯ
	}
	
	// �迭 ��ȣ�� ��� Ȯ��
	public Product searchStock(int j)
	{
		int temp = 0;
		
		for(int i = 0; i < index; i++)
		{
			if(products[i].getStock() == 0)
				continue;
			else if(products[i].getStock() > 0)
			{
				size++;
				temp++;
			}
		}
		 
		Product[] ps = new Product[temp];
		
		for(int i = 0; i < index; i++)
		{
			if(products[i].getStock() == 0)
				continue;
			else if(products[i].getStock() > 0)
				ps[--temp] = products[i];
		}
		return ps[j];
	}
	
	public int getSize()
	{
		return size;
	}
	
	public Product productAt(int i)
	{
		return products[i];
	}
	
	public User userAt(int i)
	{
		return users[i];
	}
	
	// ���� Ŭ����
	class CodeEqualException extends Exception {}
	
	class StockException extends Exception {}
	
	//��� 1���̻��̸� ����ڰ� �Է��� ���� �迭�� �����ϰ� ��� -1, ��� ������ exception
	public void addUser(User u) throws Exception
	{
		try
		{
			if(products[search(u.getProductName())].getStock() > 0)
			{
				users[usersIndex] = u;
				System.out.println("����� ������ ��ϵǾ����ϴ�");
				usersIndex++;
				products[search(u.getProductName())].setStock(products[search(u.getProductName())].getStock()-1);
			}
			else
			{
				throw new StockException();
			}
		}
		catch(StockException e)
		{
			System.out.println("������ ��� �����ϴ�");
		}
	}
	
	public void add(Product p)
	{
		if(products[0] == null)
		{
			products[index] = p;	//index�� ° products �迭�� ��ǰ ��ü �߰� ��, index++
			System.out.println("��ǰ�� �߰��Ǿ����ϴ�");
			index++;
		}
		else if(products[0] != null)
		{
			for(int i = 0; i < index; i++)
			{
				try
				{
					if(products[i].getCode().equals(p.getCode()))
					{
						throw new CodeEqualException();
					}
					else if(!products[i].getCode().equals(p.getCode()))	//�ڵ尡 �������� �ʾƾ� ��ǰ �߰�����
					{
						products[index] = p;
						System.out.println("��ǰ�� �߰��Ǿ����ϴ�");
						index++;
						
						break;
					}
				}
				catch(CodeEqualException e)
				{
					System.out.println("������ �ڵ尡 �����մϴ�");
				}
			}
		}
	}
	
	//�Է� ���� ��ǰ index�� ���� �ش� ��ǰ ����
	public void delete(int searchIndex)
	{
		for(int i = 0; i < index; i++)
		{
			//�迭�� ����� ���� ������ continue, i�� ã�� index�� ������ count++
			if(products[i] == null)
				continue;
			else if(i == searchIndex)
			{
				index--;
			}
		}
		
		if(searchIndex != -1)
		{
			for(int i = 0; i < index; i++)
			{
				//����� ��ü ��ĭ�� ������(�������� null�� ó��)
				if(i >= searchIndex && i < arrayLength-1)
					products[i] = products[i+1];
				else if(i == arrayLength-1)
					products[i] = null;
			}
		}
	}
	
	//�迭�� ���� return
	public int getLength()
	{
		return arrayLength;
	}
	
	//index return
	public int getIndex()
	{
		return index;
	}
	
	public int getUsersIndex()
	{
		return usersIndex;
	}
}