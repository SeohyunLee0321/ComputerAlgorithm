import java.util.*;
import java.text.ParseException;

public class UI {

	public static void main (String[] args) throws Exception
	{
		Product p;
		User u = new User();
		Product r = new Product();
		Manager mgr = new Manager();
		
		boolean stopUI = false;
		
		System.out.println("---------*********물품 렌탈 프로그램*********---------");
		
		while(stopUI == false)
		{
			Scanner scan = new Scanner(System.in);
			
			System.out.println("\n원하시는 메뉴 번호를 입력하세요");
			System.out.println("1. 물품 등록");
			System.out.println("2. 물품 삭제");
			System.out.println("3. 전체 물품 출력");
			System.out.println("4. 재고가 있는 물품만 출력");
			System.out.println("5. 사용자 등록 및 물품 대여");
			System.out.println("6. 대여자 전체 출력");
			System.out.println("7. 대여자 코드로 대여자 정보 검색");
			System.out.println("8. 체크아웃 및 지불요금");
			System.out.println("9. 하루 매출 확인");
			System.out.println("10. 프로그램 종료");
			
			try
			{
				switch(scan.nextInt())
				{
				case 1:
					//물품 등록
					Product p1 = new Product();
					System.out.println("물품 코드를 입력하세요 : ");
					String pCode = scan.next();
					//입력받은 pCode 가 저장되어있는 pCode 와 같은 게 없는 경우
					if(mgr.findPCode(pCode) == -1)
						p1.setPCode(pCode);
					//입력받은 pCode 가 저장된어있는 pCode 와 같은 경우
					else
					{
						System.out.println("다음 물품과 중복된 코드입니다. 다른 코드로 입력하세요");
						System.out.println("----------------------------------------------");
						p = mgr.productAt(mgr.findPCode(pCode));
						
						System.out.println("물품 이름 : " + p.getPName() + "\t물품 코드 : " + p.getPCode() + "\t물품 개수 : " + p.getPStock());
						System.out.println("----------------------------------------------");
						break;
					}
					System.out.println("물품 이름을 입력하세요 : ");
					String pName = scan.next();
					p1.setPName(pName);
					System.out.println("물품의 가격을 입력하세요 : ");
					int price = scan.nextInt();
					p1.setPrice(price);
					System.out.println("물품의 재고를 입력하세요 : ");
					int pStock = scan.nextInt();
					p1.setPStock(pStock);
					
					mgr.add(p1);
					break;
					
				case 2:
					//물품 삭제
					System.out.println("삭제할 물품의 코드를 입력하세요 : ");
					pCode = scan.next();
					//입력받은 pCode 와 동일한 pCode 를 가진 물품이 없는 경우
					if(mgr.findPCode(pCode) == -1)
						System.out.println("입력하신 물품 코드를 찾지 못했습니다");
					//해당 물품을 찾은 경우
					else
					{
						mgr.delete(mgr.findPCode(pCode));
						System.out.println("해당 상품을 삭제했습니다");
					}
					break;
					
				case 3:
					//전체 물품 출력
					//아직 등록된 물품이 없는 경우
					if(mgr.getPSize() == 0)
						System.out.println("아직 등록된 물품이 없습니다");
					//등록된 물품이 있는 경우
					else
						for(int i = 0; i < mgr.getPSize(); i++)
						{
							p = mgr.productAt(i);
							System.out.println("물품 코드 : " + p.getPCode() + "\t물품 이름 : " + p.getPName() + "\t물품 가격 : " + p.getPrice() + "\t물품 개수 : " + p.getPStock());
						}
					break;
					
				case 4:
					//재고가 1 이상인 물품만 출력
					int count = 0;
					//아직 등록된 물품이 없는 경우
					if(mgr.getPSize() == 0)
						System.out.println("아직 등록된 물품이 없습니다");
					//등록된 물품이 존재하는 경우
					else
					{
						for(int i = 0; i < mgr.getPSize(); i++)
						{
							p = mgr.productAt(i);
							if(p.getPStock() > 0)
							{
								System.out.println("물품 코드 : " + p.getPCode() + "\t물품 이름 : " + p.getPName() + "\t물품 가격 : " + p.getPrice() + "\t물품 개수 : " + p.getPStock());
								count++;
							}
						}
						//재고가 남아있는 물품이 없는 경우
						if(count == 0)
							System.out.println("재고가 남아있는 물품이 없습니다");
					}
					break;
					
				case 5:
					//대여자 등록 및 물품 대여
					if(mgr.getPSize() == 0)
					{
						System.out.println("아직 등록된 상품이 없어 대여가 불가능합니다");
						break;
					}
					User u1 = new User();
					
					System.out.println("사용자의 이름을 입력하세요 : ");
					String uName = scan.next();
					u1.setUName(uName);
					System.out.println("사용자의 코드를 입력하세요 : ");
					String uCode = scan.next();
					//중복된 사용자 코드가 아닌 경우
					if(mgr.findUCode(uCode) == -1)
						u1.setUCode(uCode);
					//입력받은 사용자 코드가 중복인 경우
					else
					{
						System.out.println("다음 사용자와 중복된 코드입니다. 다른 코드로 입력하세요");
						System.out.println("----------------------------------------------");
						u = mgr.userAt(mgr.findUCode(uCode));
						
						System.out.println("대여자 이름 : " + u.getUName() + 
											"\t대여자 코드 : " + u.getUCode());
						System.out.println("----------------------------------------------");
						break;
					}
					//체크인 날짜를 date 로 입력받기
					System.out.println("체크인 날짜를 입력하세요(yyyyMMdd) : ");
					String checkInDateStr = scan.next();
					try
					{
						u1.setCheckInDate(checkInDateStr);
					}
					catch(ParseException e)
					{
						System.out.println("날짜를 잘못 입력하셨습니다 yyyyMMdd 형식으로 입력해주세요");
						break;
					}
					//예정 체크아웃 날짜를 date 로 입력받기
					System.out.println("체크아웃하실 날짜를 입력하세요(yyyyMMdd) : ");
					String checkOutDateStr = scan.next();
					try
					{
						u1.setCheckOutDate(checkOutDateStr);
					}
					catch(ParseException e)
					{
						System.out.println("날짜를 잘못 입력하셨습니다 yyyyMMdd 형식으로 입력해주세요");
						break;
					}
					//체크아웃 예정 날짜가 체크인 날짜보다 이른 경우
					if(u1.getCheckOutDate().before(u1.getCheckInDate()) == true)
					{
						System.out.println("체크아웃 예정 날짜가 체크인 날짜보다 이릅니다. 다시 입력해주세요");
						break;
					}
					//체크아웃 예정 날짜가 체크인 날짜보다 늦거나 같은 경우
					else
					{
						System.out.println("렌트하고자하는 물품 종류의 수를 3개 이하로 입력하세요 : ");
						int rentPNum = scan.nextInt();
						//4개 이상 빌리려고 하는 경우
						if(rentPNum > 3)
						{
							System.out.println("물품을 최대 3개까지만 렌트할 수 있습니다");
							break;
						}
						//등록된 물품 개수보다 많이 빌리려고 하는 경우
						else if(rentPNum > mgr.getPSize())
						{
							System.out.println("현재 등록되어있는 물품보다 빌리려는 물품 수가 더 많습니다");
							break;
						}
						else
						{
							System.out.println("물품을 " + rentPNum + "개 렌트합니다");
							for(int i = 0; i < rentPNum; i++)
							{
								System.out.println((i+1) + " 번째로 렌트할 물품의 코드를 입력하세요 : ");
								String rentPCode = scan.next();
								//자신이 이미 빌린 물품일 경우
								if(u.findRentCode(rentPCode) != -1)
								{
									System.out.println("이미 렌트한 물품입니다");
									break;
								}
								else
								{
									//물품 재고가 있는 경우 정상적으로 물품 렌트
									if(mgr.productAt(mgr.findPCode(rentPCode)).getPStock() > 0)
									{
										Product p2 = new Product();
										p2.setPCode(rentPCode);
										p2.setPName(mgr.productAt(mgr.findPCode(rentPCode)).getPName());
										p2.setPrice(mgr.productAt(mgr.findPCode(rentPCode)).getPrice());
										u1.addR(p2);
										
										mgr.productAt(mgr.findPCode(rentPCode)).subtractStock();
										System.out.println("해당 물품을 렌트했습니다");
									}
									//빌리려고 하는 물품의 재고가 없는 경우
									else
									{
										System.out.println("해당 물품은 재고가 없어 빌릴 수 없습니다");
										break;
									}
								}
							}
						}
						mgr.addU(u1);
					}
					break;
					
				case 6:
					//대여 중인 사용자 정보 전체 출력
					if(mgr.getUSize() == 0)
					{
						System.out.println("대여중인 사용자가 없습니다");
						break;
					}
					
					for(int i = 0; i < mgr.getUSize(); i++)
					{
						u = mgr.userAt(i);
						System.out.println("대여자 이름 : " + u.getUName() + "\t대여자 코드 : " + u.getUCode() + "\t체크인 날짜 : " + u.getCheckInDate() + "\t예상 체크아웃 날짜 : " + u.getCheckOutDate());
						try
						{
							for(int j = 0; j < u.getRentSize(); j++)
							{
								r = u.rentPAt(j);
								System.out.println("대여한 물품 코드 : " + r.getPCode() + "\t대여한 물품 이름 : " + r.getPName());
							}
						}
						catch(NullPointerException e)
						{
							System.out.println("---------------------------------------------");
							continue;
						}
						System.out.println("---------------------------------------------");
					}
					break;
					
				case 7:
					//대여자 코드로 대여자 정보 검색
					System.out.println("대여자의 코드를 입력하세요 : ");
					String rentUCode = scan.next();
					//대여자 코드가 등록되어 있지 않은 경우
					if(mgr.findUCode(rentUCode) == -1)
					{
						System.out.println("해당 대여자는 존재하지 않습니다");
						break;
					}
					else
					{
						u = mgr.userAt(mgr.findUCode(rentUCode));
						System.out.println("대여자 이름 : " + u.getUName() + "\t대여자 코드 : " + u.getUCode() + "\t체크인 날짜 : " + u.getCheckInDate() + "\t체크아웃 날짜 : " + u.getCheckOutDate());
						for(int j = 0; j < u.getRentSize(); j++)
						{
							r = u.rentPAt(j);
							System.out.println("대여한 물품 코드 : " + r.getPCode() + "\t대여한 물품 이름 : " + r.getPName() + "\t대여한 물품의 하루 가격 : " + r.getPrice());
						}
					}
					break;
					
				case 8:
					//대여자 코드로 체크아웃 및 지불해야 할 요금 출력
					if(mgr.getUSize() == 0)
					{
						System.out.println("아직 체크인 한 대여자가 없습니다");
						break;
					}
					else
					{
						System.out.println("대여자의 코드를 입력하세요 : ");
						String checkOutUCode = scan.next();
						if(mgr.findUCode(checkOutUCode) == -1)
						{
							System.out.println("해당 대여자는 존재하지 않습니다");
							break;
						}
						else
						{
							u = mgr.userAt(mgr.findUCode(checkOutUCode));
							System.out.println("대여자 이름 : " + u.getUName() + "\t대여자 코드 : " + u.getUCode() + "\n체크인 날짜 : " + u.getCheckInDate() + "\t예정 체크아웃 날짜 : " + u.getCheckOutDate() + "\n실제 체크아웃 날짜 : " + u.getReturnDate());
							for(int j = 0; j < u.getRentSize(); j++)
							{
								r = u.rentPAt(j);
						
								System.out.println("대여한 물품 코드 : " + r.getPCode() + "\t대여한 물품 이름 : " + r.getPName() + "\t대여한 물품의 하루 가격 : " + r.getPrice());
								
								mgr.productAt(mgr.findPCode(r.getPCode())).addStock();
								r.setPCode("");
								r.setPName("");
							}
							int tempTotal = 0;
							//pay 설명 글 출력
							if(u.getIsLate() == 0)
							{
								System.out.println("\n체크아웃 예정일에 반납하셨습니다");
								System.out.println(u.getRentSize() + " 개의 물품을 반납하셨습니다\n" + u.getDaysIntended() + " 일 동안 렌트하셨습니다");
							}
							else if(u.getIsLate() == 1)
							{
								System.out.println("\n체크아웃 예정일보다 늦게 반납하셨습니다");
								System.out.println(u.getRentSize() + " 개의 물품을 반납하셨습니다\n연체된 날짜에 대한 비용은 하루 렌트 비용의 1.5배의 비용입니다.\n" +
										u.getDaysIntended() + " 일 동안 정상적으로 렌트하셨고, " + u.getDiffDays() + " 일 늦게 반납하셨습니다");
							}
							else if(u.getIsLate() == 2)
							{
								System.out.println("\n체크아웃 예정일보다 일찍 반납하셨습니다");
								System.out.println(u.getRentSize() + " 개의 물품을 반납하셨습니다\n하루 렌트 비용은 0.9배로 할인합니다.\n" +
										(u.getDaysIntended() + u.getDiffDays()) + " 일 동안 렌트하셨고, " + (-u.getDiffDays()) + " 일 일찍 반납하셨습니다");
							}
							for(int i = 0; i < u.getRentSize(); i++)
							{
								r = u.rentPAt(i);
								//pay 함수로 내야할 금액 
								tempTotal += u.pay(r.getPrice());
							}
							System.out.println("총 금액은 " + tempTotal + "원 입니다");
							mgr.setTotalPay(tempTotal);
							mgr.deleteU(mgr.findUCode(checkOutUCode));
						}
					}
					break;
					
				case 9:
					//하루 매출 출력
					System.out.println("오늘의 매출은 총 " + mgr.getTotalPay() + "원 입니다");
					break;
					
				case 10:
					//프로그램 종료
					System.out.println("프로그램을 종료합니다");
					stopUI = true;
					scan.close();
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("실행하고자하는 메뉴를 번호로 다시 입력하세요");
			}
		}
	}
}
