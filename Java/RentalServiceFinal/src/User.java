import java.util.*;

import java.io.*;
import java.text.SimpleDateFormat;

public class User implements java.io.Serializable {

	// �������
	private String userName;// ������̸�
	private String phoneNum;// ��ȭ��ȣ
	// �뿩 ��ǰ�ڵ���� �迭
	private String[] rentalGoodsCode = new String[3];
	// �뿩 ��ǰ ���ݵ��� �迭
	private Integer[] payList = new Integer[3];
	private String dueDate;// �ݳ�����
	private String rentDate;// ������¥
	private Integer rentalCount = 0;// ������ǰ����
	Calendar getToday = Calendar.getInstance();

	// �������Լ�
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
	
	// ������ equals �Լ��� ���� (�������̵�)
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof User))
			return false;
		User u = (User) o;
		// ���� �ڵ尡 ������ true ��ȯ
		return u.getPhoneNum().equals(phoneNum);
	}

	// �迭�� ���� ��ǰ�ڵ�� ���� �߰�
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
	
	// ��� ��� �޼ҵ�
	Calendar today = Calendar.getInstance();

	public int payCalculate() throws Exception {
		// ��� ����ϱ� (�뿩 ��ǰ �ݾ� �� ���ϱ�)
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

		// �ݳ������� - ������¥ (�������� �� ��¥)
		int day1 = (int) ((returnDate.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
		// �����ݳ��� - ������¥ (������ ���� ��¥)
		int day2 = (int) ((getToday.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;

		if (day1 == day2) // �ݳ� ���� ��¥�� �ݳ�
			return payment * day1; // ���� �뿩 �ݳ��� 1�Ϸ� ó��

		else if (day1 > day2)// �ݳ� ���� ��¥���� ���� �ݳ�
			return payment * day2; // ���� �뿩 �ݳ��� 1�Ϸ� ó��

		else // �ݳ� ���� ��¥���� �ʰ� �ݳ�
			return (int) (payment * day1 + payment * 1.5 * (day2 - day1)); // ��ü�� (�Ϸ� �뿩��) �߰��Ͽ��� ��ȯ
	}
	
	public int payCalculateEach(int Goodsindex) throws Exception {
		// ��� ����ϱ� (�뿩 ��ǰ �ݾ� �� ���ϱ�)
		int payment = payList[Goodsindex];

		getToday.setTime(new Date());
		
		Date date1 = new SimpleDateFormat("yyyyMMdd").parse(rentDate);
		Calendar rentalDate = Calendar.getInstance();
		rentalDate.setTime(date1);
		
		Date date2 = new SimpleDateFormat("yyyyMMdd").parse(dueDate);
		Calendar returnDate = Calendar.getInstance();
		returnDate.setTime(date2);
		
		// �ݳ������� - ������¥ (�������� �� ��¥)
		int day1 = (int) ((returnDate.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
		// �����ݳ��� - ������¥ (������ ���� ��¥)
		int day2 = (int) ((getToday.getTimeInMillis() - rentalDate.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
	
		if (day1 == day2) // �ݳ� ���� ��¥�� �ݳ�
			return payment * day1; // ���� �뿩 �ݳ��� 1�Ϸ� ó��
		
		else if (day1 > day2)// �ݳ� ���� ��¥���� ���� �ݳ�
			return payment * day2; // ���� �뿩 �ݳ��� 1�Ϸ� ó��
		
		else // �ݳ� ���� ��¥���� �ʰ� �ݳ�
			return (int) (payment * day1 + payment * 1.5 * (day2 - day1)); // ��ü�� (�Ϸ� �뿩��) �߰��Ͽ��� ��ȯ
	}

	// i��° �뿩 ��ǰ �ڵ带 ����
	public String codeAt(int i) {
		return rentalGoodsCode[i];
	}

	// i��° �뿩 ��ǰ�� ���� ����
	public int payAt(int i) {
		// �뿩 �ݾ� �迭 i��° ��ǰ ��ü return
		return payList[i];
	}

	// ���ͼ��� �Լ�

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