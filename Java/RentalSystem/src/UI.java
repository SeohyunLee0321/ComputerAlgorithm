import java.util.*;

public class UI
{
	
	public static void main (String[] args) throws Exception
	{
		int size = 0;
		
		Manager manager = Manager.getInstance();
		Product p = new Product();
		User u = new User();
		
		boolean stopUI = false;
		
		while(stopUI == false)
		{
			Scanner scan = new Scanner(System.in);
			
			System.out.println("-----------------------*********물품 렌탈 프로그램*********------------------------");
			System.out.println("1. 관리자로 접속");
			System.out.println("2. 사용자로 접속");
			System.out.println("3. 프로그램 종료");
			System.out.println("원하시는 프로그램 번호를 입력하세요 : ");
			
			try
			{
				boolean stop = false;
				int menu1 = 0;
				
				switch(menu1 = scan.nextInt())
				{
				case 1:
					
					while(stop == false)
					{
						//관리자로 접속
						System.out.println("-----------------------*********관리자 메뉴*********------------------------");
						System.out.println("1. 상품 등록");
						System.out.println("2. 상품 삭제");
						System.out.println("3. 전체 상품 보여주기");
						System.out.println("4. 재고가 남아있는 상품 보여주기");
						System.out.println("5. 사용자가 렌트 중인 상품 보여주기");
						System.out.println("6. 관리자 메뉴에서 나가기");
						System.out.println("원하시는 프로그램 번호를 입력하세요 : ");
						
						
						try
						{
							boolean go = true;
							int menu2 = 0;
							
							switch(menu2 = scan.nextInt())
							{
							case 1:
								//상품 등록
								try
								{
									System.out.println("상품의 이름을 입력하세요 : ");
									String name = scan.next();
									
									System.out.println("상품의 코드를 입력하세요 : ");
									String code = scan.next();
									
									int price = 0;
									go = true;
									while(go == true)
									{
										try
										{
											System.out.println("상품의 하루 렌트 가격을 입력하세요 : ");
											price = scan.nextInt();
											scan.nextLine();
											go = false;
											break;
										}
										catch(InputMismatchException e)
										{
											System.out.println("삼품 가격을 숫자로 다시 입력하세요");
											scan.nextLine();
										}
									}
									
									int stock = 0;
									go = true;
									while(go == true)
									{
										try
										{
											System.out.println("상품의 재고를 입력하세요 : ");
											stock = scan.nextInt();
											scan.nextLine();
											go = false;
											
											if(stock > 0)
												size++;
											break;
										}
										catch(InputMismatchException e)
										{
											System.out.println("삼품 재고를 숫자로 다시 입력하세요");
											scan.nextLine();
										}
									}
									manager.add(new Product(name, code, price, stock));
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("더 이상 물품을 추가할 수 없습니다");
								}
								break;
								
							case 2:
								//상품 삭제
								System.out.println("삭제하고싶은 상품의 코드를 입력하세요 : ");
								String code = scan.next();
								manager.delete(manager.search(code));
								break;
								
							case 3:
								//전체 상품 조회
								for (int i = 0; i < manager.getIndex(); i++)
								{
									p = manager.productAt(i);
									
									System.out.println("물품명 : " + p.getName() +
														",\t물품코드 : " + p.getCode() +
														",\t물품가격 : " + p.getPrice() +
														",\t재고수량 : " + p.getStock());
								}
								break;
								
							case 4:
								//재고 남아있는 상품만 조회
								try
								{
									for(int j = 0; j < size; j++)
									{
										p = manager.searchStock(j);
											
										System.out.println("물품명 : " + p.getName() +
												",\t물품코드 : " + p.getCode() +
												",\t물품가격 : " + p.getPrice() +
												",\t재고수량 : " + p.getStock());
									}
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("현재 재고가 남아 있는 물품이 없습니다");
								}
								break;
								
							case 5:
								//물품을 렌트중의 사용자 조회
								for (int i = 0; i < manager.getUsersIndex(); i++)
								{
									u = manager.userAt(i);
									
									System.out.println("사용자 : " + u.getName() +
														",\t전화번호 : " + u.getPhoneNum() +
														",\t빌린물품 : " + u.getProductName());
								}
								
								break;
							case 6:
								//관리자 메뉴 종료
								System.out.println("관리자 메뉴에서 나가기\n");
								stop = true;
								break;
								
							default:
								System.out.println("실행하고자하는 메뉴를 숫자로 다시 입력하세요\n");
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("메뉴 번호로 다시 입력하세요");
							stop = true;
						}
						
					}
					break;
					
	/////////////////////////////////////////////////////////////////////////////////
					
				case 2:
					while(stop == false)
					{
						//사용자로 접속
						System.out.println("-----------------------*********사용자 메뉴*********------------------------");
						System.out.println("1. 재고가 남아있는 제품 보기");
						System.out.println("2. 물품 렌트하기");
						System.out.println("3. 사용자 메뉴에서 나가기");
						System.out.println("원하시는 프로그램 번호를 입력하세요 : ");
						
						try
						{
							stop = false;
							boolean go = true;
							int menu3 = 0;
							
							switch(menu3 = scan.nextInt())
							{
							case 1:
								//재고남아있는 물품 조회
								try
								{
									for(int j = 0; j < size; j++)
									{
										p = manager.searchStock(j);
											
										System.out.println("물품명 : " + p.getName() +
												",\t물품코드 : " + p.getCode() +
												",\t물품가격 : " + p.getPrice() +
												",\t재고수량 : " + p.getStock());
									}
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("현재 빌릴 수 있는 물품이 없습니다");		// 아무때나 오류떠 고치기
								}
								break;
								
							case 2:
								//물품 렌트하기
								System.out.println("물품을 렌트하기 위해 사용자 정보를 입력하세요");
								
								System.out.println("당신의 이름을 입력하세요");
								String userName = scan.next();
								
								int userPhone = 0;
								go = true;
								while(go == true)
								{
									try
									{
										System.out.println("당신의 전화번호를 숫자로 입력하세요 (ex. 0000)");
										userPhone = scan.nextInt();
										scan.nextLine();
										go = false;
										break;
									}
									catch(InputMismatchException e)
									{
										System.out.println("전화번호를 숫자로만 다시 입력하세요");
										scan.nextLine();
									}
								}
								
								System.out.println("렌트하려고하는 물품 코드를 입력하세요");
								String userRentProduct = scan.next();
								
								int rentDate = 0;
								go = true;
								while(go == true)
								{
									try
									{
										System.out.println("렌트하는 날짜를 입력하세요 (ex. 0000)");
										userPhone = scan.nextInt();
										scan.nextLine();
										go = false;
										break;
									}
									catch(InputMismatchException e)
									{
										System.out.println("날짜를 숫자로만 다시 입력하세요");
										scan.nextLine();
									}
								}
								
								manager.addUser(new User(userName, userPhone, userRentProduct, rentDate));
								
								break;
								
							case 3:
								System.out.println("사용자 메뉴에서 나가기\n");
								stop = true;
								break;
								
							default:
								System.out.println("실행하고자하는 메뉴를 숫자로 다시 입력하세요\n");
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("메뉴 번호로 다시 입력하세요");
							stop = true;
						}
						
					}
					break;
					
				case 3:
					//프로그램 종료
					System.out.println("프로그램에서 나가기");
					//scan.next("");
					stopUI = true;
					scan.close();
					break;
					
				default:
					System.out.println("실행하고자하는 메뉴를 숫자로 다시 입력하세요\n");
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("메뉴 번호로 다시 입력하세요");
			}
			
		}
	}
}
