import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Management { // 관리자 클래스
	// (1) 물품 배열
	private ArrayList<Goods> goodsList = new ArrayList<Goods>();
	// (2) 사용자 배열
	private ArrayList<User> userList = new ArrayList<User>();
	// (3) 매출 배열
	private ArrayList<Integer> salesList = new ArrayList<Integer>();
	private Integer dailySales = 0; // 일 매출 총액

	Management () {}
	
	// 매니지먼트 생성자 함수 -> 상품 배열, 사용자 배열, 매출 배열의 크기 설정
	Management(int maxGoodsCount, int maxUserCount, int maxSalesCount) {
		goodsList = new ArrayList<Goods>(maxGoodsCount);// 상품 배열 크기 설정
		userList = new ArrayList<User> (maxUserCount);// 대여 배열 크기 설정
		salesList = new ArrayList<Integer>(maxSalesCount);// 매출 배열 크기 설정
	}
	
	Management(int maxGoodsCount, int maxUserCount, int maxSalesCount, ObjectInputStream ois) throws Exception {
		goodsList = new ArrayList<Goods>(maxGoodsCount);// 상품 배열 크기 설정
		userList = new ArrayList<User>(maxUserCount);// 대여 배열 크기 설정
		salesList = new ArrayList<Integer>(maxSalesCount); // 매출 배열 크기 설정

		// 파일 데이터 읽어오기
		try {
			// 상품 배열 채우기
			int gCount = (Integer) ois.readObject();
			for (int i = 0; i < gCount; i++) {
				Goods g = (Goods) ois.readObject();
				goodsList.add(g);
			}
			// 유저 배열 채우기
			int uCount = (Integer) ois.readObject();
			for (int i = 0; i < uCount; i++) {
				User u = (User) ois.readObject();
				userList.add(u);
			}
			// 매출 배열 채우기
			int sCount = (Integer) ois.readObject();
			for (int i = 0; i < sCount; i++) {
				int sales = (Integer) ois.readObject();
				salesList.add(sales);
			}
			// 매출 총합 불러오기
			dailySales = (Integer) ois.readObject();
		} catch (FileNotFoundException fnfe) {
		} catch (EOFException eofe) {
		} catch (IOException ioe) {
		}

	}
	
	// 물품 저장 함수
	public void saveAll(ObjectOutputStream oos) throws Exception {
		try {
			// goods save
			Integer gCount = goodsList.size();
			oos.writeObject(gCount);

			for (int i = 0; i < gCount; i++) {
				oos.writeObject(goodsList.get(i));
			}

			// user save
			Integer uCount = userList.size();
			oos.writeObject(uCount);

			for (int i = 0; i < uCount; i++) {
				oos.writeObject(userList.get(i));
			}

			// sales save
			Integer sCount = salesList.size();
			oos.writeObject((Integer) sCount);

			for (int i = 0; i < sCount; i++) {
				oos.writeObject(salesList.get(i));
			}

			oos.writeObject(dailySales);
		} catch (IOException ioe) {
			throw new Exception ("파일을 저장할 수 없습니다");
		}
	}

	// -------------------------물품 관리 메소드------------------------------

	// 코드 중복 체크
	public void checkCode(Goods g) throws Exception {
		if (goodsList.indexOf(g) >= 0)
			throw new Exception ("중복된 코드입니다.");
	}

	public void addGoods (Goods g) throws Exception{
		if(goodsList != null)
			checkCode(g);
		goodsList.add(g);
	}

	// 없는 물품을 제외한 뒤 물품을 삭제하는 메소드
	public void deleteGoods(String goodsCode) throws Exception {
		try {
			goodsList.remove(search(goodsCode));
		} catch (Exception e) {
			throw new Exception("존재하지 않는 코드입니다.");
		}
	}

	// 물품 코드로 객체를 검색하여 인덱스 번호를 반환
	public int search(String goodsCode) throws Exception {	//해당 물품 찾을 수 없으면 UI에서 catch
		return goodsList.indexOf(new Goods(goodsCode));
	}

	// i번째 물품 객체를 반환
	public Goods goodsAt(int i) {
		try {
			return goodsList.get(i);
		} catch (IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	// 재고 감소
	public void subGoodsStock(int index, int n) throws Exception {	// catch in Goods subStock()
		goodsList.get(index).subStock(n);
	}
	
	// 재고 증가
	public void addGoodsStock(int index, int n) {
		goodsList.get(index).addStock(n);
	}
	
	public void setImage(int index, String image) {
		goodsList.get(index).setImage(image);
	}
	
	// -------------유저 관리 메소드-------------------------

	// 중복된 전화번호 인지 확인
	public void checkPhone(User u) throws Exception {
		if(userList.indexOf(u) >= 0)
			throw new Exception ("중복된 전화번호입니다");
	}

	// 대여 후 재고 감소
	public void subStock(User u) throws Exception {
		for (int i = 0; i < u.getRentalCount(); i++) {
			String code = u.codeAt(i); // 해당 유저가 대여한 물품의 코드
			int searchIdx;
			try {
				searchIdx = search(code); // goodsList에서 해당 코드의 인덱스 얻기
			} catch (Exception e) {
				throw new Exception("일치하는 코드를 찾을 수 없습니다.");
			}
			Goods g = goodsList.get(searchIdx); // 해당 인덱스의 Goods 객체 얻기
			g.subStock(1); // 재고 1개 삭제
		}
	}

	// 사용자 배열에 유저 객체 추가
	public void addUser(User u) {
		userList.add(u);
	}

	// 체크인
	public void checkIn(User u) throws Exception {
		try {
			checkPhone(u);
			subStock(u);
			addUser(u);
		} catch (Exception e) {
			throw new Exception ("잘못된 체크인입니다");
		}
	}
	
	public User userAt(int i) {
		try {
			return userList.get(i);
		} catch (IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	// 일치하는 회원번호 검색
	public int searchUser(String phone) throws Exception {	//사용자 찾을 수 없으면 UI에서 catch
		return userList.indexOf(new User(phone));
	}

	// 반납후 상품 재고 다시 증가
	public void returnGoods(int uIdx) throws Exception {
		User u = userAt(uIdx);
		for (int i = 0; i < u.getRentalCount(); i++) { // 해당 사용자가 빌린 물품개수만큼 반복
			String code = u.codeAt(i); // 해당 User가 대여한 i번째 물품의 코드
			try {
				int gIdx = search(code); // goodsList에서 해당 코드의 인덱스 번호 검색
				goodsAt(gIdx).addStock(1); // 해당 goods 객체의 재고를 추가하기
			} catch (Exception e) {
				throw new Exception("물품코드를 찾을수 없습니다");
			}
		}
	}
	
	// 찾은 물품코드의 재고수만 감소
	public void returnGoodsNew (int uIdx, String gCode) throws Exception {
		User u = userAt(uIdx);
		for (int i = 0; i < u.getRentalCount(); i++) {
			String code = u.codeAt(i);
			if (code.equals(gCode)) {
				try {
					int gIdx = search(code);
					goodsAt(gIdx).addStock(1);
				} catch (Exception e) {
					throw new Exception("물품코드를 찾을수 없습니다");
				}
			}
		}
	}

	// 사용자 객체 배열에서 삭제
	public void deleteUser(int uIdx) {
		userList.remove(uIdx);
	}
	
	public void addSales(int money) throws Exception {
		dailySales += money; // 일 매출에 더하기
		salesList.add(money);// 일 매출 목록 배열에 추가
	}

	// 체크아웃 메소드
	public void checkOut(int uIdx) throws Exception {
		try {
			deleteUser(uIdx); // 해당 유저객체 삭제
		} catch (Exception e) {
			throw new Exception("잘못된 체크아웃입니다");
		}

	}

	// 게터세터 함수
	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}

	public int getgCount() {
		return goodsList.size();
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public int getuCount() {
		return userList.size();
	}

	public ArrayList<Integer> getSalesList() {
		return salesList;
	}

	public int getsCount() {
		return salesList.size();
	}

	public int getDailySales() {
		return dailySales;
	}

}
