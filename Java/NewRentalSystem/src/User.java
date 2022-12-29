import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class User {
	private String uName;	//사용자 이름
	private String uCode;	//사용자 코드
	private Date checkInDate;	//체크인 날짜
	private Date checkOutDate;	//예정 체크아웃 날짜
	private Date returnDate;	//실제 체크아웃 날짜(오늘)
	private String rentPCode;	//빌린 물품 코드
	
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	
	//빌린 물품 배열
	private int rentListSize = 3;
	private Product[] rentList = new Product[rentListSize];
	private int rentSize = 0;
	
	//빌린 물품 추가
	public void addR(Product p)
	{
		rentList[rentSize++] = p;
	}
	
	//빌린 물품 삭제
	public void deleteR(int index)
	{
		for(int i = index; i < rentSize; i++)
		{
			rentList[i] = rentList[i+1];
		}
	}
	
	//빌린 물품 검색 후 index 반환
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
	
	//빌린 물품 개수 반환
	public int getRentSize()
	{
		return rentSize;
	}
	
	//빌린 물품 개수 초기화
	public void setRentSize(int i)
	{
		this.rentSize = rentSize;
	}
	
	//빌린 물품 i번째 배열 반환
	public Product rentPAt(int i)
	{
		return rentList[i];
	}
	
	//사용자 객체
	public User() {};
	
	//물품 대여 시 사용할 사용자 객체
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
	
	//반납이 예정 체크아웃보다 빠른지 느린지 같은지 int 로 반환하는 함수
	public int getIsLate()
	{
		int isLate = -1;
		
		if(checkOutDate.equals(returnDate))			//예정일에 반납
			isLate = 0;
		else if(checkOutDate.before(returnDate))	//연체됨
			isLate = 1;
		else if(checkOutDate.after(returnDate))		//빨리 반납
			isLate = 2;
		
		return isLate;
	}
	
	public long getDiffDays()
	{
		long diffSec = (returnDate.getTime() - checkOutDate.getTime()) / 1000;	//초 차이
		
		return diffSec / (24*60*60);
	}
	
	public long getDaysIntended()
	{
		long diffSec = (checkOutDate.getTime() - checkInDate.getTime()) / 1000;
		
		return (diffSec / (24*60*60)) + 1;
	}
	
	/*pay 정책
	모든 물품의 하루 렌트 가격은,
	자신이 물품 대여 시 설정했던 체크아웃 날짜보다 하루 늦어질 때 마다 물건 당 1.5배를 받는다
	만약, 예정 체크아웃 날짜보다 일찍 반납했다면, 물품 당 하루 렌트 가격의 0.9배로 할인하여 받는다.*/
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