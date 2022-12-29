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
		
		System.out.println("---------*********��ǰ ��Ż ���α׷�*********---------");
		
		while(stopUI == false)
		{
			Scanner scan = new Scanner(System.in);
			
			System.out.println("\n���Ͻô� �޴� ��ȣ�� �Է��ϼ���");
			System.out.println("1. ��ǰ ���");
			System.out.println("2. ��ǰ ����");
			System.out.println("3. ��ü ��ǰ ���");
			System.out.println("4. ��� �ִ� ��ǰ�� ���");
			System.out.println("5. ����� ��� �� ��ǰ �뿩");
			System.out.println("6. �뿩�� ��ü ���");
			System.out.println("7. �뿩�� �ڵ�� �뿩�� ���� �˻�");
			System.out.println("8. üũ�ƿ� �� ���ҿ��");
			System.out.println("9. �Ϸ� ���� Ȯ��");
			System.out.println("10. ���α׷� ����");
			
			try
			{
				switch(scan.nextInt())
				{
				case 1:
					//��ǰ ���
					Product p1 = new Product();
					System.out.println("��ǰ �ڵ带 �Է��ϼ��� : ");
					String pCode = scan.next();
					//�Է¹��� pCode �� ����Ǿ��ִ� pCode �� ���� �� ���� ���
					if(mgr.findPCode(pCode) == -1)
						p1.setPCode(pCode);
					//�Է¹��� pCode �� ����Ⱦ��ִ� pCode �� ���� ���
					else
					{
						System.out.println("���� ��ǰ�� �ߺ��� �ڵ��Դϴ�. �ٸ� �ڵ�� �Է��ϼ���");
						System.out.println("----------------------------------------------");
						p = mgr.productAt(mgr.findPCode(pCode));
						
						System.out.println("��ǰ �̸� : " + p.getPName() + "\t��ǰ �ڵ� : " + p.getPCode() + "\t��ǰ ���� : " + p.getPStock());
						System.out.println("----------------------------------------------");
						break;
					}
					System.out.println("��ǰ �̸��� �Է��ϼ��� : ");
					String pName = scan.next();
					p1.setPName(pName);
					System.out.println("��ǰ�� ������ �Է��ϼ��� : ");
					int price = scan.nextInt();
					p1.setPrice(price);
					System.out.println("��ǰ�� ��� �Է��ϼ��� : ");
					int pStock = scan.nextInt();
					p1.setPStock(pStock);
					
					mgr.add(p1);
					break;
					
				case 2:
					//��ǰ ����
					System.out.println("������ ��ǰ�� �ڵ带 �Է��ϼ��� : ");
					pCode = scan.next();
					//�Է¹��� pCode �� ������ pCode �� ���� ��ǰ�� ���� ���
					if(mgr.findPCode(pCode) == -1)
						System.out.println("�Է��Ͻ� ��ǰ �ڵ带 ã�� ���߽��ϴ�");
					//�ش� ��ǰ�� ã�� ���
					else
					{
						mgr.delete(mgr.findPCode(pCode));
						System.out.println("�ش� ��ǰ�� �����߽��ϴ�");
					}
					break;
					
				case 3:
					//��ü ��ǰ ���
					//���� ��ϵ� ��ǰ�� ���� ���
					if(mgr.getPSize() == 0)
						System.out.println("���� ��ϵ� ��ǰ�� �����ϴ�");
					//��ϵ� ��ǰ�� �ִ� ���
					else
						for(int i = 0; i < mgr.getPSize(); i++)
						{
							p = mgr.productAt(i);
							System.out.println("��ǰ �ڵ� : " + p.getPCode() + "\t��ǰ �̸� : " + p.getPName() + "\t��ǰ ���� : " + p.getPrice() + "\t��ǰ ���� : " + p.getPStock());
						}
					break;
					
				case 4:
					//��� 1 �̻��� ��ǰ�� ���
					int count = 0;
					//���� ��ϵ� ��ǰ�� ���� ���
					if(mgr.getPSize() == 0)
						System.out.println("���� ��ϵ� ��ǰ�� �����ϴ�");
					//��ϵ� ��ǰ�� �����ϴ� ���
					else
					{
						for(int i = 0; i < mgr.getPSize(); i++)
						{
							p = mgr.productAt(i);
							if(p.getPStock() > 0)
							{
								System.out.println("��ǰ �ڵ� : " + p.getPCode() + "\t��ǰ �̸� : " + p.getPName() + "\t��ǰ ���� : " + p.getPrice() + "\t��ǰ ���� : " + p.getPStock());
								count++;
							}
						}
						//��� �����ִ� ��ǰ�� ���� ���
						if(count == 0)
							System.out.println("��� �����ִ� ��ǰ�� �����ϴ�");
					}
					break;
					
				case 5:
					//�뿩�� ��� �� ��ǰ �뿩
					if(mgr.getPSize() == 0)
					{
						System.out.println("���� ��ϵ� ��ǰ�� ���� �뿩�� �Ұ����մϴ�");
						break;
					}
					User u1 = new User();
					
					System.out.println("������� �̸��� �Է��ϼ��� : ");
					String uName = scan.next();
					u1.setUName(uName);
					System.out.println("������� �ڵ带 �Է��ϼ��� : ");
					String uCode = scan.next();
					//�ߺ��� ����� �ڵ尡 �ƴ� ���
					if(mgr.findUCode(uCode) == -1)
						u1.setUCode(uCode);
					//�Է¹��� ����� �ڵ尡 �ߺ��� ���
					else
					{
						System.out.println("���� ����ڿ� �ߺ��� �ڵ��Դϴ�. �ٸ� �ڵ�� �Է��ϼ���");
						System.out.println("----------------------------------------------");
						u = mgr.userAt(mgr.findUCode(uCode));
						
						System.out.println("�뿩�� �̸� : " + u.getUName() + 
											"\t�뿩�� �ڵ� : " + u.getUCode());
						System.out.println("----------------------------------------------");
						break;
					}
					//üũ�� ��¥�� date �� �Է¹ޱ�
					System.out.println("üũ�� ��¥�� �Է��ϼ���(yyyyMMdd) : ");
					String checkInDateStr = scan.next();
					try
					{
						u1.setCheckInDate(checkInDateStr);
					}
					catch(ParseException e)
					{
						System.out.println("��¥�� �߸� �Է��ϼ̽��ϴ� yyyyMMdd �������� �Է����ּ���");
						break;
					}
					//���� üũ�ƿ� ��¥�� date �� �Է¹ޱ�
					System.out.println("üũ�ƿ��Ͻ� ��¥�� �Է��ϼ���(yyyyMMdd) : ");
					String checkOutDateStr = scan.next();
					try
					{
						u1.setCheckOutDate(checkOutDateStr);
					}
					catch(ParseException e)
					{
						System.out.println("��¥�� �߸� �Է��ϼ̽��ϴ� yyyyMMdd �������� �Է����ּ���");
						break;
					}
					//üũ�ƿ� ���� ��¥�� üũ�� ��¥���� �̸� ���
					if(u1.getCheckOutDate().before(u1.getCheckInDate()) == true)
					{
						System.out.println("üũ�ƿ� ���� ��¥�� üũ�� ��¥���� �̸��ϴ�. �ٽ� �Է����ּ���");
						break;
					}
					//üũ�ƿ� ���� ��¥�� üũ�� ��¥���� �ʰų� ���� ���
					else
					{
						System.out.println("��Ʈ�ϰ����ϴ� ��ǰ ������ ���� 3�� ���Ϸ� �Է��ϼ��� : ");
						int rentPNum = scan.nextInt();
						//4�� �̻� �������� �ϴ� ���
						if(rentPNum > 3)
						{
							System.out.println("��ǰ�� �ִ� 3�������� ��Ʈ�� �� �ֽ��ϴ�");
							break;
						}
						//��ϵ� ��ǰ �������� ���� �������� �ϴ� ���
						else if(rentPNum > mgr.getPSize())
						{
							System.out.println("���� ��ϵǾ��ִ� ��ǰ���� �������� ��ǰ ���� �� �����ϴ�");
							break;
						}
						else
						{
							System.out.println("��ǰ�� " + rentPNum + "�� ��Ʈ�մϴ�");
							for(int i = 0; i < rentPNum; i++)
							{
								System.out.println((i+1) + " ��°�� ��Ʈ�� ��ǰ�� �ڵ带 �Է��ϼ��� : ");
								String rentPCode = scan.next();
								//�ڽ��� �̹� ���� ��ǰ�� ���
								if(u.findRentCode(rentPCode) != -1)
								{
									System.out.println("�̹� ��Ʈ�� ��ǰ�Դϴ�");
									break;
								}
								else
								{
									//��ǰ ��� �ִ� ��� ���������� ��ǰ ��Ʈ
									if(mgr.productAt(mgr.findPCode(rentPCode)).getPStock() > 0)
									{
										Product p2 = new Product();
										p2.setPCode(rentPCode);
										p2.setPName(mgr.productAt(mgr.findPCode(rentPCode)).getPName());
										p2.setPrice(mgr.productAt(mgr.findPCode(rentPCode)).getPrice());
										u1.addR(p2);
										
										mgr.productAt(mgr.findPCode(rentPCode)).subtractStock();
										System.out.println("�ش� ��ǰ�� ��Ʈ�߽��ϴ�");
									}
									//�������� �ϴ� ��ǰ�� ��� ���� ���
									else
									{
										System.out.println("�ش� ��ǰ�� ��� ���� ���� �� �����ϴ�");
										break;
									}
								}
							}
						}
						mgr.addU(u1);
					}
					break;
					
				case 6:
					//�뿩 ���� ����� ���� ��ü ���
					if(mgr.getUSize() == 0)
					{
						System.out.println("�뿩���� ����ڰ� �����ϴ�");
						break;
					}
					
					for(int i = 0; i < mgr.getUSize(); i++)
					{
						u = mgr.userAt(i);
						System.out.println("�뿩�� �̸� : " + u.getUName() + "\t�뿩�� �ڵ� : " + u.getUCode() + "\tüũ�� ��¥ : " + u.getCheckInDate() + "\t���� üũ�ƿ� ��¥ : " + u.getCheckOutDate());
						try
						{
							for(int j = 0; j < u.getRentSize(); j++)
							{
								r = u.rentPAt(j);
								System.out.println("�뿩�� ��ǰ �ڵ� : " + r.getPCode() + "\t�뿩�� ��ǰ �̸� : " + r.getPName());
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
					//�뿩�� �ڵ�� �뿩�� ���� �˻�
					System.out.println("�뿩���� �ڵ带 �Է��ϼ��� : ");
					String rentUCode = scan.next();
					//�뿩�� �ڵ尡 ��ϵǾ� ���� ���� ���
					if(mgr.findUCode(rentUCode) == -1)
					{
						System.out.println("�ش� �뿩�ڴ� �������� �ʽ��ϴ�");
						break;
					}
					else
					{
						u = mgr.userAt(mgr.findUCode(rentUCode));
						System.out.println("�뿩�� �̸� : " + u.getUName() + "\t�뿩�� �ڵ� : " + u.getUCode() + "\tüũ�� ��¥ : " + u.getCheckInDate() + "\tüũ�ƿ� ��¥ : " + u.getCheckOutDate());
						for(int j = 0; j < u.getRentSize(); j++)
						{
							r = u.rentPAt(j);
							System.out.println("�뿩�� ��ǰ �ڵ� : " + r.getPCode() + "\t�뿩�� ��ǰ �̸� : " + r.getPName() + "\t�뿩�� ��ǰ�� �Ϸ� ���� : " + r.getPrice());
						}
					}
					break;
					
				case 8:
					//�뿩�� �ڵ�� üũ�ƿ� �� �����ؾ� �� ��� ���
					if(mgr.getUSize() == 0)
					{
						System.out.println("���� üũ�� �� �뿩�ڰ� �����ϴ�");
						break;
					}
					else
					{
						System.out.println("�뿩���� �ڵ带 �Է��ϼ��� : ");
						String checkOutUCode = scan.next();
						if(mgr.findUCode(checkOutUCode) == -1)
						{
							System.out.println("�ش� �뿩�ڴ� �������� �ʽ��ϴ�");
							break;
						}
						else
						{
							u = mgr.userAt(mgr.findUCode(checkOutUCode));
							System.out.println("�뿩�� �̸� : " + u.getUName() + "\t�뿩�� �ڵ� : " + u.getUCode() + "\nüũ�� ��¥ : " + u.getCheckInDate() + "\t���� üũ�ƿ� ��¥ : " + u.getCheckOutDate() + "\n���� üũ�ƿ� ��¥ : " + u.getReturnDate());
							for(int j = 0; j < u.getRentSize(); j++)
							{
								r = u.rentPAt(j);
						
								System.out.println("�뿩�� ��ǰ �ڵ� : " + r.getPCode() + "\t�뿩�� ��ǰ �̸� : " + r.getPName() + "\t�뿩�� ��ǰ�� �Ϸ� ���� : " + r.getPrice());
								
								mgr.productAt(mgr.findPCode(r.getPCode())).addStock();
								r.setPCode("");
								r.setPName("");
							}
							int tempTotal = 0;
							//pay ���� �� ���
							if(u.getIsLate() == 0)
							{
								System.out.println("\nüũ�ƿ� �����Ͽ� �ݳ��ϼ̽��ϴ�");
								System.out.println(u.getRentSize() + " ���� ��ǰ�� �ݳ��ϼ̽��ϴ�\n" + u.getDaysIntended() + " �� ���� ��Ʈ�ϼ̽��ϴ�");
							}
							else if(u.getIsLate() == 1)
							{
								System.out.println("\nüũ�ƿ� �����Ϻ��� �ʰ� �ݳ��ϼ̽��ϴ�");
								System.out.println(u.getRentSize() + " ���� ��ǰ�� �ݳ��ϼ̽��ϴ�\n��ü�� ��¥�� ���� ����� �Ϸ� ��Ʈ ����� 1.5���� ����Դϴ�.\n" +
										u.getDaysIntended() + " �� ���� ���������� ��Ʈ�ϼ̰�, " + u.getDiffDays() + " �� �ʰ� �ݳ��ϼ̽��ϴ�");
							}
							else if(u.getIsLate() == 2)
							{
								System.out.println("\nüũ�ƿ� �����Ϻ��� ���� �ݳ��ϼ̽��ϴ�");
								System.out.println(u.getRentSize() + " ���� ��ǰ�� �ݳ��ϼ̽��ϴ�\n�Ϸ� ��Ʈ ����� 0.9��� �����մϴ�.\n" +
										(u.getDaysIntended() + u.getDiffDays()) + " �� ���� ��Ʈ�ϼ̰�, " + (-u.getDiffDays()) + " �� ���� �ݳ��ϼ̽��ϴ�");
							}
							for(int i = 0; i < u.getRentSize(); i++)
							{
								r = u.rentPAt(i);
								//pay �Լ��� ������ �ݾ� 
								tempTotal += u.pay(r.getPrice());
							}
							System.out.println("�� �ݾ��� " + tempTotal + "�� �Դϴ�");
							mgr.setTotalPay(tempTotal);
							mgr.deleteU(mgr.findUCode(checkOutUCode));
						}
					}
					break;
					
				case 9:
					//�Ϸ� ���� ���
					System.out.println("������ ������ �� " + mgr.getTotalPay() + "�� �Դϴ�");
					break;
					
				case 10:
					//���α׷� ����
					System.out.println("���α׷��� �����մϴ�");
					stopUI = true;
					scan.close();
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("�����ϰ����ϴ� �޴��� ��ȣ�� �ٽ� �Է��ϼ���");
			}
		}
	}
}
