import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
	private String uName;	//����� �̸�
	private String uCode;	//����� �ڵ�
	private Date checkInDate;	//üũ�� ��¥
	private Date checkOutDate;	//���� üũ�ƿ� ��¥
	private Date returnDate;	//���� üũ�ƿ� ��¥(����)
	private String rentPCode;	//���� ��ǰ �ڵ�
	
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	//���� ��ǰ �迭
	private int rentListSize = 3;
	private Product[] rentList = new Product[rentListSize];
	private int rentSize = 0;
	
	//���� ��ǰ �߰�
	public void addR(Product p)
	{
		rentList[rentSize++] = p;
	}
	
	//���� ��ǰ ����
	public void deleteR(int index)
	{
		for(int i = index; i < rentSize; i++)
		{
			rentList[i] = rentList[i+1];
		}
	}
	
	//���� ��ǰ �˻� �� index ��ȯ
	public int findRentCode(String rentPCode)
	{
		int index = -1;
		
		for(int i = 0; i < rentSize; i++)
		{
			if(rentList[i].getPCode().equals(rentPCode))
				index = i;
		}
		return index;
	}
	
	//���� ��ǰ ���� ��ȯ
	public int getRentSize()
	{
		return rentSize;
	}
	
	//���� ��ǰ ���� �ʱ�ȭ
	public void setRentSize(int i)
	{
		this.rentSize = rentSize;
	}
	
	//���� ��ǰ i��° �迭 ��ȯ
	public Product rentPAt(int i)
	{
		return rentList[i];
	}
	
	//����� ��ü
	public User() {};
	
	//��ǰ �뿩 �� ����� ����� ��ü
	public User(String uName, String uCode, Date checkInDate, Date checkOutDate)
	{
		this.uName = uName;
		this.uCode = uCode;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	public String getUName()
	{
		return uName;
	}
	
	public String getUCode()
	{
		return uCode;
	}
	
	public Date getCheckInDate()
	{
		return checkInDate;
	}
	
	public Date getCheckOutDate()
	{
		return checkOutDate;
	}
	
	public Date getReturnDate() throws ParseException
	{
		Date now = new Date();
		String formatedNow = format.format(now);
		returnDate = format.parse(formatedNow);
		
		return returnDate;
	}
	
	public String getRentPCode()
	{
		return rentPCode;
	}
	
	public void setUName(String uName)
	{
		this.uName = uName;
	}
	
	public void setUCode(String uCode)
	{
		this.uCode = uCode;
	}
	
	public void setCheckInDate(String checkInDateStr) throws ParseException
	{
		checkInDate = format.parse(checkInDateStr);
		this.checkInDate = checkInDate;
	}
	
	public void setCheckOutDate(String checkOutDateStr) throws ParseException
	{
		checkOutDate = format.parse(checkOutDateStr);
		this.checkOutDate = checkOutDate;
	}
	
	public void setRentPCode(String rentPCode)
	{
		this.rentPCode = rentPCode;
	}
	
	//�ݳ��� ���� üũ�ƿ����� ������ ������ ������ int �� ��ȯ�ϴ� �Լ�
	public int getIsLate()
	{
		int isLate = -1;
		
		if(checkOutDate.equals(returnDate))			//�����Ͽ� �ݳ�
			isLate = 0;
		else if(checkOutDate.before(returnDate))	//��ü��
			isLate = 1;
		else if(checkOutDate.after(returnDate))		//���� �ݳ�
			isLate = 2;
		
		return isLate;
	}
	
	public long getDiffDays()
	{
		long diffSec = (returnDate.getTime() - checkOutDate.getTime()) / 1000;	//�� ����
		
		return diffSec / (24*60*60);
	}
	
	public long getDaysIntended()
	{
		long diffSec = (checkOutDate.getTime() - checkInDate.getTime()) / 1000;
		
		return (diffSec / (24*60*60)) + 1;
	}
	
	/*pay ��å
	��� ��ǰ�� �Ϸ� ��Ʈ ������,
	�ڽ��� ��ǰ �뿩 �� �����ߴ� üũ�ƿ� ��¥���� �Ϸ� �ʾ��� �� ���� ���� �� 1.5�踦 �޴´�
	����, ���� üũ�ƿ� ��¥���� ���� �ݳ��ߴٸ�, ��ǰ �� �Ϸ� ��Ʈ ������ 0.9��� �����Ͽ� �޴´�.*/
	public int pay(int price)
	{
		int pay = 0;
		
		if(getIsLate() == 0)
		{
			pay = (int) (price * getDaysIntended());
		}
		else if(getIsLate() == 1)
		{
			pay = (int) ((price * getDaysIntended()) + (1.5 * price * getDiffDays()));
		}
		else if(getIsLate() ==2)
		{
			pay = (int) (0.9 * price * (getDaysIntended() + getDiffDays()));
		}
		
		return pay;
	}
}