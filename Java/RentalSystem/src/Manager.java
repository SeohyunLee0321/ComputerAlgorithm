import java.util.*;

public class Manager {
	
	private int arrayLength = 100;	//배열 길이
	private int index = 0;		//찾는 배열 위치
	private int size = 0;
	private int usersIndex = 0;
	
	private Product[] products = new Product[arrayLength];		//배열 생성
	private User[] users = new User[arrayLength];
	private User[] rental = new User[arrayLength];
	
	private static Manager mgr = new Manager();	
	private Manager() {}
	public static Manager getInstance() 
	{	//Manager를 mgr 이름으로 객체 생성, getInstance로 생성한 객체 받아오기
		return mgr;
	}
	
	//상품 코드로 검색
	public int search(String code) throws Exception
	{
		int searchIndex = 0;	//검색 상품 index
		
			try
			{
				for(int i = 0; i < index; i++)
				{
					if(products[i].getCode().equals(code))	//입력한 코드와 저장된 물품 코드가 같다면
					{
						System.out.println(products[i].getCode());
						System.out.println(code);
						searchIndex = i;	//searchIndex에 i 대입 후 break
						break;
					}
					else
					{
						searchIndex = -1;
					}
				}
				if(searchIndex == -1)
				{
					Exception e = new Exception("찾으시는 물품이 없습니다");
					throw e;
				}
			}
			catch(Exception e)	//입력한 코드와 같은 코드 없다면
			{
				System.out.println(e.getMessage());
			}
			
		return searchIndex;		//searchIndex 반환
	}
	
	// 배열 번호로 재고 확인
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
	
	// 오류 클래스
	class CodeEqualException extends Exception {}
	
	class StockException extends Exception {}
	
	//재고가 1개이상이면 사용자가 입력한 정보 배열에 저장하고 재고 -1, 재고 없으면 exception
	public void addUser(User u) throws Exception
	{
		try
		{
			if(products[search(u.getProductName())].getStock() > 0)
			{
				users[usersIndex] = u;
				System.out.println("사용자 정보가 등록되었습니다");
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
			System.out.println("물건의 재고가 없습니다");
		}
	}
	
	public void add(Product p)
	{
		if(products[0] == null)
		{
			products[index] = p;	//index번 째 products 배열에 상품 객체 추가 후, index++
			System.out.println("물품이 추가되었습니다");
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
					else if(!products[i].getCode().equals(p.getCode()))	//코드가 동일하지 않아야 상품 추가가능
					{
						products[index] = p;
						System.out.println("물품이 추가되었습니다");
						index++;
						
						break;
					}
				}
				catch(CodeEqualException e)
				{
					System.out.println("동일한 코드가 존재합니다");
				}
			}
		}
	}
	
	//입력 받은 상품 index를 통해 해당 상품 삭제
	public void delete(int searchIndex)
	{
		for(int i = 0; i < index; i++)
		{
			//배열에 저장된 내용 없으면 continue, i가 찾는 index와 같으면 count++
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
				//저장된 객체 한칸씩 앞으로(마지막은 null로 처리)
				if(i >= searchIndex && i < arrayLength-1)
					products[i] = products[i+1];
				else if(i == arrayLength-1)
					products[i] = null;
			}
		}
	}
	
	//배열의 길이 return
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