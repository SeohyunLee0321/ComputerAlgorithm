import java.util.Scanner;

class Employee {
	
	private String name;		// 이름
	private String address;		// 주소
	private String phoneNum;		// 전화번호
	private int salary;		// 연봉
	
	// 각 필드에 대한 get, set 메소드
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	
	public void setSalary(int salary)
	{
		this.salary = salary;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	public int getSalry()
	{
		return salary;
	}
}

public class EmployeeTest {
	
	static Scanner scan = new Scanner(System.in);		// scanner 를 클래스 필드로 선언
	static int i = 0;		// 정수 i 를 클래스 필드로 선언
	
	public static void main(String[] args) {
		
		System.out.println("직원 수를 입력하세요");		// 입력할 직원 수 입력받기
		int employeesNum = scan.nextInt();
		scan.nextLine();
		
		Employee[] employees = new Employee[employeesNum];		// 직원 수에 맞는 employees 배열 생성
		
		for(i = 0; i < employees.length; i++)		// for 문을 통해서 사용자로부터 데이터 입력받기 (input 함수)
		{
			employees[i] = new Employee();
			
			input(employees);
		}
		
		System.out.println("수정하시겠습니까?");		// 수정 여부 확인 메시지 출력
		String yes = scan.next();		// String yes 생성 및 입력
		
		if(yes.equals("y"))		// yes 가 y 라면 (즉, 수정할 것이 있다고 답했다면)
		{
			System.out.println("수정하고 싶은 직원번호를 입력하세요 : ");		// 수정할 직원 번호 입력하도록 메시지 출력
			
			i = scan.nextInt() - 1;		// i 에 직원 번호 입력받고 1을 빼서 assign
			scan.nextLine();
			
			if((i < employees.length) && (i > 0))		// 알맞은 직원 번호 입력시 input 함수 불러와서 내용 수정
				input(employees);
			else		// 직원 번호가 employees 배열의 길이보다 길면 잘못된 직원 번호임을 알림
			{
				System.out.println("잘못된 직원번호입니다");
			}
		}
		
		for(i = 0; i < employees.length; i++)		// for 문을 통해서 저장된 employees 배열 속 내용 출력
		{
			System.out.println((i+1) + "번째 직원의 이름 : " + employees[i].getName() + 		// getName 을 통해 저장된 직원 이름 가져오기
							   "    주소 : " + employees[i].getAddress() +		// getAddress 을 통해 저장된 직원 이름 가져오기
							   "    전화번호 : " + employees[i].getPhoneNum() +		// getPhoneNum 을 통해 저장된 직원 이름 가져오기
							   "    연봉 : " + employees[i].getSalry());		// getSalary 을 통해 저장된 직원 이름 가져오기
		}
	}
	
	public static void input(Employee[] employees)		// input 함수
	{
		String name;		// 이름
		String address;		// 주소
		String phoneNum;		// 전화번호
		int salary;		// 연봉
		
		// setName 을 통해 employees[i]에 직원 이름 입력받고 저장
		System.out.printf((i+1) + " 번째 직원 이름 : ");
		name = scan.nextLine();
		employees[i].setName(name);
		
		// setAddress 을 통해 employees[i]에 직원 주소 입력받고 저장
		System.out.printf((i+1) + " 번째 직원 주소 : ");
		address = scan.nextLine();
		employees[i].setAddress(address);
		
		// setPhoneNum 을 통해 employees[i]에 직원 전화번호 입력받고 저장
		System.out.printf((i+1) + " 번째 직원 전화번호 : ");
		phoneNum = scan.nextLine();
		employees[i].setPhoneNum(phoneNum);
		
		// setSalary 을 통해 employees[i]에 직원 연봉 입력받고 저장
		System.out.printf((i+1) + " 번째 직원 연봉 : ");
		salary = scan.nextInt();
		employees[i].setSalary(salary);
		scan.nextLine();
	}
}