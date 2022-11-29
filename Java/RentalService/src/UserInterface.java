import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;

public class UserInterface {
	public static void main(String[] args) throws Exception {

		boolean isOnLoop = true;
		ObjectInputStream ois = null;
		Scanner sc1 = new Scanner(System.in);
		
		Management m;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("database.txt"));
			m = new Management(100, 100, 100, ois);
			System.out.println("파일을 불러왔습니다");
			if(ois != null)
				ois.close();
		} catch (FileNotFoundException fnfe) {
			while (true) {
				System.out.println("Database.txt 파일을 찾을 수 없습니다");
				System.out.println("database.txt 파일이 사라진 것이라면 '1'을, 프로그램 최초 실행이면 '2'를 눌러주세요");
				int answer = sc1.nextInt();
				
				if(answer == 1) {
					System.out.println("database.txt 파일을 다시 찾고 현 디렉토리에 저장해주세요");
					System.exit(0);
				}
				else if(answer == 2) {
					System.out.println("프로그램을 최초 실행합니다");
					m = new Management(100, 100, 100);
					break;
				}
				else
					System.out.println("1 또는 2 를 입력해주세요");
			} 
		} 
		
		while (isOnLoop == true) {
			Scanner sc = new Scanner(System.in);

			// 사용자에게 메뉴 보여주기
			System.out.println("-------렌탈서비스프로그램-------");
			System.out.println("1. 대여물품등록");
			System.out.println("2. 대여물품삭제");
			System.out.println("3. 전체물품조회");
			System.out.println("4. 재고물품검색");
			System.out.println("5. 물품대여");
			System.out.println("6. 반납");
			System.out.println("7. 대여정보확인");
			System.out.println("8. 총매출 및 매출목록 확인");
			System.out.println("9. 저장");
			System.out.println("10. 종료");
			System.out.println("--------------------------");

			// 사용자에게 이용할 메뉴 선택 받기
			System.out.println("원하시는 메뉴의 번호를 입력해 주십시오.");
			int userChoice = sc.nextInt();// 사용자가 선택한 메뉴의 번호

			try {
				switch (userChoice) {

				case 1: // 물품등록
					System.out.println("등록하려는 물품의 이름을 입력해주십시오.");
					String name = sc.next();
					System.out.println("등록하려는 물품의 개수를 입력해주십시오.");
					int count = sc.nextInt();
					System.out.println("등록하려는 물품의 상품코드를 입력해주십시오.");
					String code = sc.next();
					System.out.println("등록하려는 물품의 가격을 입력해주십시오.");
					int price = sc.nextInt();
					Goods g = new Goods(name, code, count, price);// 물품객체생성
					try {
						m.addGoods(g);// 물품리스트에 추가
					} catch (Exception e) {
						System.out.println("중복된 키값입니다.");
						continue;
					}
					break;// switch문 종료

				case 2: // 물품삭제
					System.out.println("삭제하려는 물품의 코드를 입력해주십시오");
					String dGoodsCode = sc.next(); // 삭제하려는 물품의 코드
					try {
						m.deleteGoods(dGoodsCode);// 물품을 물품리스트에서 삭제
						System.out.println("해당 상품이 삭제되었습니다.");
					} catch (Exception e) { // 예외처리
						System.out.println("해당 물품에 대한 코드가 없습니다.");
						continue;
					}
					break;// switch문 종료

				case 3: // 전체 물품 보여주기
					if (m.goodsAt(0) == null) // 물품이 없을 경우
						System.out.println("상품이 존재하지 않습니다.");
					else {// 물품이 존재할 경우
						for (int i = 0; i < m.getgCount(); i++) {
							System.out.println("물품명: " + m.goodsAt(i).getGoodsName() + " |물품코드: "
									+ m.goodsAt(i).getGoodsCode() + " |물품개수: " + m.goodsAt(i).getGoodsCount());
						}
					}
					break;// switch문 종료

				case 4: // 재고 물품 검색
					System.out.println(" ");
					System.out.println("검색하려는 물품의 코드를 입력해주십시오.");
					try {
						String goodsCode = sc.next(); // 검색하려는 물품의 코드
						int goodsIndex = m.search(goodsCode); // 검색하려는 물품의 인덱스
						// 상품이 없을 경우
						if (m.goodsAt(0) == null)
							System.out.println("상품이 존재하지 않습니다.");
						// 상품이 있을경우
						else {
							// 재고가 0일 경우
							if (m.goodsAt(goodsIndex).getGoodsCount() == 0) {
								System.out.println("해당 상품은 재고가 없습니다.");
							}
							// 재고가 있을경우
							else {
									int i = m.search(goodsCode);// 해당 물품의 인덱스
									// 해당 물품 관련 정보 출력
									System.out.println("물품명: " + m.goodsAt(i).getGoodsName() + " |물품코드: "
											+ m.goodsAt(i).getGoodsCode() + " |물품개수: " + m.goodsAt(i).getGoodsCount());
							}
						}
					} catch (Exception e) { // 존재하지 않는 코드를 입력한 경우 (search error catch)
						System.out.println("해당 물품에 대한 코드가 없습니다.");
						continue;
					}

					break; // switch문 종료

				case 5: // 대여하기(checkIn 기능)

					// 사용자 정보 입력받기
					System.out.println("성함을 입력해주십시오.");
					String userName = sc.next(); // 사용자 이름
					System.out.println("전화번호를 입력해주십시오.");
					String phoneNum = sc.next(); // 사용자 전화번호 =검색시 키값

					// 대여 일자 입력받기
					Calendar today = Calendar.getInstance();
					String rentDate = new SimpleDateFormat("yyyyMMdd").format(today.getTime());

					// 반납 예정 일자 입력받기
					System.out.println("반납 예정 일자를 적어주세요(yyyyMMdd 형식으로 기재) : ");
					String dueDate = sc.next();

					// User 객체 생성
					User u1 = new User(userName, phoneNum, rentDate, dueDate);

					// 대여할 상품 개수 입력 받기
					System.out.println("대여할 상품 개수를 적어주세요(최대 3개) : ");
					int rentalNum = sc.nextInt();

					// 대여할 상품 개수가 4이상일때 익셉션 발생
					if (rentalNum >= 4)
						throw new Exception("3개까지만 빌릴 수 있습니다");

					// 대여할 상품 코드 입력 받기
					for (int i = 0; i < rentalNum; i++) {
						System.out.println("대여할 상품 코드를 적어주세요 : ");
						String rentalCode = sc.next();
						int index = m.search(rentalCode);
						int payment = m.goodsAt(index).getPrice();
						u1.addCode(rentalCode, payment); // 대여 물품 코드 배열에 코드 추가
					}

					// 체크인하기
					m.checkIn(u1);
					System.out.println("물품 대여가 완료되었습니다.");
					break;

				case 6:// 체크아웃하기

					System.out.println("전화번호를 입력해주십시오.");
					String phone = sc.next(); // 사용자 전화번호 입력받기

					// 일치하는 사용자 찾기
					try {
						int index = m.searchUser(phone);
						System.out.println("이름: " + m.userAt(index).getUserName() 
								+ "\n빌린날짜: " + m.userAt(index).getRentDate()
								+ "\n반납예정일: " + m.userAt(index).getDueDate() + "\n빌린물품개수: "
								+ m.userAt(index).getRentalCount());
						int money = m.userAt(index).payCalculate();
						System.out.println("지불금액: " + money);
						
						// 돈 지불여부 물어보기
						System.out.println("결제하시겠습니까? 맞으면 1, 아니면 2를 눌러주십시오 : ");
						int answer = sc.nextInt();
						
						// 1입력 -> 체크아웃 진행할 경우
						if (answer == 1) {
							m.checkOut(index);// 체크아웃진행
							System.out.println("반납이 완료되었습니다.");
						}
						// 2입력-> 체크아웃 진행 안할 경우
						else if (answer == 2)
							System.out.println("결제가 취소되었습니다. 다시 진행해주십시오.");
						// 잘못된 번호 입력했을 경우
						else
							System.out.println("잘못된 번호을 입력했습니다.");
					} catch (Exception e) {
						System.out.println("해당 사용자를 찾을 수 없습니다");
					}

					break;// switch문 종료

				case 11:
					/*System.out.println("전화번호를 입력해주십시오.");
					String phone2 = sc.next(); // 사용자 전화번호 입력받기
					int index2 = m.searchUser(phone2);
					User u = m.userAt(index2);
					System.out.println("이름: " + m.userAt(index2).getUserName() 
							+ "\n빌린날짜: " + m.userAt(index2).getRentDate()
							+ "\n반납예정일: " + m.userAt(index2).getDueDate() + "\n빌린물품개수: "
							+ m.userAt(index2).getRentalCount());
					System.out.println("-------렌트한 물품목록-------");
					for (int i = 0; i < m.userAt(index2).getRentalCount(); i++) {
						String rentalCode = m.userAt(index2).codeAt(i);
						System.out.println("대여 물품 코드" + (i + 1) + ")" + rentalCode);
					}
					System.out.println("몇개의 물품을 반납하시겠습니까?");
					int returnCount = sc.nextInt();
					for (int i = 0; i < returnCount; i++) {
						System.out.println("반납할 물풐코드를 입력하세요");
						String gCode = sc.next();
						m.returnGoodsNew(index2, gCode);
						m.userAt(index2).subCode(u, gCode);
						System.out.println(gCode + " 의 반납이 완료되었습니다.");
					}
					if(u.getRentalCount() == 0) {
						m.checkOut(index2);
						System.out.println("사용자가 체크아웃되었습니다");
					}*/
					
					break;
					
				case 7: // 대여정보 보여주기
					System.out.println("대여정보 조회를 위해 회원님의 전화번호를 입력해 주십시오.");
					String phoneNumber = sc.next();// 사용자 구별 키값으로 전화번호 입력받음
					try {
						int index = m.searchUser(phoneNumber);
						System.out.println("이름: " + m.userAt(index).getUserName() + "\n전화번호: "
								+ m.userAt(index).getPhoneNum() + "\n빌린날짜: " + m.userAt(index).getRentDate()
								+ "\n반납예정일: " + m.userAt(index).getDueDate() + "\n빌린물품개수: "
								+ m.userAt(index).getRentalCount());
						System.out.println("-------렌트한 물품목록-------");
						for (int i = 0; i < m.userAt(index).getRentalCount(); i++) {
							String rentalCode = m.userAt(index).codeAt(i);
							System.out.println("대여 물품 코드" + (i + 1) + ")" + rentalCode);
						}
					} catch (Exception e) {
						System.out.println("해당 사용자를 찾을 수 없습니다");
					}

					break;// switch문 종료
					
				case 8:
					System.out.println("------------매출현황------------");
					System.out.println("일일 매출 총 합계:" + m.getDailySales());
					for (int i = 0; i < m.getsCount(); i++) {
						System.out.println((i + 1) + "]" + m.getSalesList().get(i) + "원");
					}
					break; // switch문 종료

				case 9: // 프로그램 저장
					ObjectOutputStream oos = null;
					try {
						oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
						m.saveAll(oos);
					} finally {
						try {
							if(oos != null)
								oos.close();
						} catch (Exception e) {
							System.out.println("파일 닫기에 실패했습니다");
						}
					}
					
					
					System.out.println("저장이 완료되었습니다");
					break;

				case 10: // 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					isOnLoop = false;
					break;
					
				/*case 12:
					System.out.println("무슨 물품?");
					String subGoods = sc.next();
					System.out.println("재고 몇개 감소?");
					int sub = sc.nextInt();
					m.subGoodsStock(m.search(subGoods), sub);
					break;*/

				default:// 사용자가 잘못 입력한 경우
					System.out.println("잘못된 번호를 입력하셨습니다. 다시 입력해주세요.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("실행하고자하는 메뉴를 번호로 다시 입력하세요");
			}
		}
	}
}