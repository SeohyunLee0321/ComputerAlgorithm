import java.util.*;

import java.io.*;
import java.text.SimpleDateFormat;

public class User implements java.io.Serializable {

	// 멤버변수
	private String userName;// 사용자이름
	private String phoneNum;// 전화번호
	// 대여 물품코드들의 배열
	private String[] rentalGoodsCode = new String[3];
	// 대여 물품 가격들의 배열
	private Integer[] payList = new Integer[3];
	private String dueDate;// 반납기한
	private String rentDate;// 빌린날짜
	private Integer rentalCount = 0;// 빌린물품개수
	Calendar getToday = Calendar.getInstance();

	// 생성자함수
	public User(String userName, String phoneNum, String rentDate, String dueDate) {
		super();
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.rentDate = rentDate;
		this.dueDate = dueDate;
	}
	
	public User() {}
	
	public User(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	// 기존의 equals 함수를 무시 (오버라이딩)
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof User))
			return false;
		User u = (User) o;
		// 유저 코드가 같으면 true 반환
		return u.getPhoneNum().equals(phoneNum);
	}

	// 배열에 빌린 상품코드와 가격 추가
	public void addCode(String goodsCode, int price) {
		rentalGoodsCode[rentalCount] = goodsCode;
		payList[rentalCount] = price;
		rentalCount++;
	}
	
	private static String[] remove(String[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        List<String> result = new ArrayList<>(Arrays.asList(arr));
        result.remove(index);
        return result.toArray(new String[0]);
    }
	
	public void subCode(User u, String goodsCode) {
		for (int i = 0; i < rentalCount; i++) {
			if (u.codeAt(i).equals(goodsCode)) {
				rentalGoodsCode = remove(rentalGoodsCode, i);
				rentalCount--;
				break;
			}
		}
	}
	
	private static Integer[] remove2 (Integer[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        List<Integer> result = new ArrayList<>(Arrays.asList(arr));
        result.remove(index);
        return result.toArray(new Integer[0]);
    }
	
	public void subPay(User u, int index) {
		payList = remove2(payList, index);
	}
	
	// 비용 계산 메소드
	Calendar today = Calendar.getInstance();

	public int payCalculate() throws Exception {
		// 비용 계산하기 (대여 물품 금액 다 더하기)
		int payment = 0;
		for (int i = 0; i < rentalCount; i++) {
			payment += payList[i];
		}

		getToday.setTime(new Date());

		Date date1 = new SimpleDateFormat("yyyyMMdd").parse(rentDate);
		Calendar rentalDate = Calendar.getInstance();
		rentalDate.setTime(date1);

		Date date2 = new SimpleDateFormat("yyyyMMdd").parse(dueDate);
		Calendar returnDate = Calendar.getInstance();
		returnDate.setTime(date2);

		// 반납예정일 - 빌린날짜 (빌리려고 한 날짜)
		int day1 = (int) ((returnDate.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
		// 실제반납일 - 빌린날짜 (실제로 빌린 날짜)
		int day2 = (int) ((getToday.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;

		if (day1 == day2) // 반납 예정 날짜에 반납
			return payment * day1; // 당일 대여 반납도 1일로 처리

		else if (day1 > day2)// 반납 예정 날짜보다 빨리 반납
			return payment * day2; // 당일 대여 반납도 1일로 처리

		else // 반납 예정 날짜보다 늦게 반납
			return (int) (payment * day1 + payment * 1.5 * (day2 - day1)); // 연체료 (하루 대여료) 추가하여서 반환
	}
	
	public int payCalculateEach(int Goodsindex) throws Exception {
		// 비용 계산하기 (대여 물품 금액 다 더하기)
		int payment = payList[Goodsindex];

		getToday.setTime(new Date());
		
		Date date1 = new SimpleDateFormat("yyyyMMdd").parse(rentDate);
		Calendar rentalDate = Calendar.getInstance();
		rentalDate.setTime(date1);
		
		Date date2 = new SimpleDateFormat("yyyyMMdd").parse(dueDate);
		Calendar returnDate = Calendar.getInstance();
		returnDate.setTime(date2);
		
		// 반납예정일 - 빌린날짜 (빌리려고 한 날짜)
		int day1 = (int) ((returnDate.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
		// 실제반납일 - 빌린날짜 (실제로 빌린 날짜)
		int day2 = (int) ((getToday.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
	
		if (day1 == day2) // 반납 예정 날짜에 반납
			return payment * day1; // 당일 대여 반납도 1일로 처리
		
		else if (day1 > day2)// 반납 예정 날짜보다 빨리 반납
			return payment * day2; // 당일 대여 반납도 1일로 처리
		
		else // 반납 예정 날짜보다 늦게 반납
			return (int) (payment * day1 + payment * 1.5 * (day2 - day1)); // 연체료 (하루 대여료) 추가하여서 반환
	}

	// i번째 대여 물품 코드를 리턴
	public String codeAt(int i) {
		return rentalGoodsCode[i];
	}

	// i번째 대여 물품의 가격 리턴
	public int payAt(int i) {
		// 대여 금액 배열 i번째 상품 객체 return
		return payList[i];
	}

	// 게터세터 함수

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String[] getRentalGoodsCode() {
		return rentalGoodsCode;
	}

	public void setRentalGoodsCode(String[] rentalGoodsCode) {
		this.rentalGoodsCode = rentalGoodsCode;
	}

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getRentalCount() {
		return rentalCount;
	}

	public void setRentalCount(int rentalCount) {
		this.rentalCount = rentalCount;
	}

}