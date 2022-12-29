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
			
			System.out.println("-----------------------*********��ǰ ��Ż ���α׷�*********------------------------");
			System.out.println("1. �����ڷ� ����");
			System.out.println("2. ����ڷ� ����");
			System.out.println("3. ���α׷� ����");
			System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է��ϼ��� : ");
			
			try
			{
				boolean stop = false;
				int menu1 = 0;
				
				switch(menu1 = scan.nextInt())
				{
				case 1:
					
					while(stop == false)
					{
						//�����ڷ� ����
						System.out.println("-----------------------*********������ �޴�*********------------------------");
						System.out.println("1. ��ǰ ���");
						System.out.println("2. ��ǰ ����");
						System.out.println("3. ��ü ��ǰ �����ֱ�");
						System.out.println("4. ��� �����ִ� ��ǰ �����ֱ�");
						System.out.println("5. ����ڰ� ��Ʈ ���� ��ǰ �����ֱ�");
						System.out.println("6. ������ �޴����� ������");
						System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է��ϼ��� : ");
						
						
						try
						{
							boolean go = true;
							int menu2 = 0;
							
							switch(menu2 = scan.nextInt())
							{
							case 1:
								//��ǰ ���
								try
								{
									System.out.println("��ǰ�� �̸��� �Է��ϼ��� : ");
									String name = scan.next();
									
									System.out.println("��ǰ�� �ڵ带 �Է��ϼ��� : ");
									String code = scan.next();
									
									int price = 0;
									go = true;
									while(go == true)
									{
										try
										{
											System.out.println("��ǰ�� �Ϸ� ��Ʈ ������ �Է��ϼ��� : ");
											price = scan.nextInt();
											scan.nextLine();
											go = false;
											break;
										}
										catch(InputMismatchException e)
										{
											System.out.println("��ǰ ������ ���ڷ� �ٽ� �Է��ϼ���");
											scan.nextLine();
										}
									}
									
									int stock = 0;
									go = true;
									while(go == true)
									{
										try
										{
											System.out.println("��ǰ�� ��� �Է��ϼ��� : ");
											stock = scan.nextInt();
											scan.nextLine();
											go = false;
											
											if(stock > 0)
												size++;
											break;
										}
										catch(InputMismatchException e)
										{
											System.out.println("��ǰ ��� ���ڷ� �ٽ� �Է��ϼ���");
											scan.nextLine();
										}
									}
									manager.add(new Product(name, code, price, stock));
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("�� �̻� ��ǰ�� �߰��� �� �����ϴ�");
								}
								break;
								
							case 2:
								//��ǰ ����
								System.out.println("�����ϰ���� ��ǰ�� �ڵ带 �Է��ϼ��� : ");
								String code = scan.next();
								manager.delete(manager.search(code));
								break;
								
							case 3:
								//��ü ��ǰ ��ȸ
								for (int i = 0; i < manager.getIndex(); i++)
								{
									p = manager.productAt(i);
									
									System.out.println("��ǰ�� : " + p.getName() +
														",\t��ǰ�ڵ� : " + p.getCode() +
														",\t��ǰ���� : " + p.getPrice() +
														",\t������ : " + p.getStock());
								}
								break;
								
							case 4:
								//��� �����ִ� ��ǰ�� ��ȸ
								try
								{
									for(int j = 0; j < size; j++)
									{
										p = manager.searchStock(j);
											
										System.out.println("��ǰ�� : " + p.getName() +
												",\t��ǰ�ڵ� : " + p.getCode() +
												",\t��ǰ���� : " + p.getPrice() +
												",\t������ : " + p.getStock());
									}
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("���� ��� ���� �ִ� ��ǰ�� �����ϴ�");
								}
								break;
								
							case 5:
								//��ǰ�� ��Ʈ���� ����� ��ȸ
								for (int i = 0; i < manager.getUsersIndex(); i++)
								{
									u = manager.userAt(i);
									
									System.out.println("����� : " + u.getName() +
														",\t��ȭ��ȣ : " + u.getPhoneNum() +
														",\t������ǰ : " + u.getProductName());
								}
								
								break;
							case 6:
								//������ �޴� ����
								System.out.println("������ �޴����� ������\n");
								stop = true;
								break;
								
							default:
								System.out.println("�����ϰ����ϴ� �޴��� ���ڷ� �ٽ� �Է��ϼ���\n");
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("�޴� ��ȣ�� �ٽ� �Է��ϼ���");
							stop = true;
						}
						
					}
					break;
					
	/////////////////////////////////////////////////////////////////////////////////
					
				case 2:
					while(stop == false)
					{
						//����ڷ� ����
						System.out.println("-----------------------*********����� �޴�*********------------------------");
						System.out.println("1. ��� �����ִ� ��ǰ ����");
						System.out.println("2. ��ǰ ��Ʈ�ϱ�");
						System.out.println("3. ����� �޴����� ������");
						System.out.println("���Ͻô� ���α׷� ��ȣ�� �Է��ϼ��� : ");
						
						try
						{
							stop = false;
							boolean go = true;
							int menu3 = 0;
							
							switch(menu3 = scan.nextInt())
							{
							case 1:
								//������ִ� ��ǰ ��ȸ
								try
								{
									for(int j = 0; j < size; j++)
									{
										p = manager.searchStock(j);
											
										System.out.println("��ǰ�� : " + p.getName() +
												",\t��ǰ�ڵ� : " + p.getCode() +
												",\t��ǰ���� : " + p.getPrice() +
												",\t������ : " + p.getStock());
									}
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									System.out.println("���� ���� �� �ִ� ��ǰ�� �����ϴ�");		// �ƹ����� ������ ��ġ��
								}
								break;
								
							case 2:
								//��ǰ ��Ʈ�ϱ�
								System.out.println("��ǰ�� ��Ʈ�ϱ� ���� ����� ������ �Է��ϼ���");
								
								System.out.println("����� �̸��� �Է��ϼ���");
								String userName = scan.next();
								
								int userPhone = 0;
								go = true;
								while(go == true)
								{
									try
									{
										System.out.println("����� ��ȭ��ȣ�� ���ڷ� �Է��ϼ��� (ex. 0000)");
										userPhone = scan.nextInt();
										scan.nextLine();
										go = false;
										break;
									}
									catch(InputMismatchException e)
									{
										System.out.println("��ȭ��ȣ�� ���ڷθ� �ٽ� �Է��ϼ���");
										scan.nextLine();
									}
								}
								
								System.out.println("��Ʈ�Ϸ����ϴ� ��ǰ �ڵ带 �Է��ϼ���");
								String userRentProduct = scan.next();
								
								int rentDate = 0;
								go = true;
								while(go == true)
								{
									try
									{
										System.out.println("��Ʈ�ϴ� ��¥�� �Է��ϼ��� (ex. 0000)");
										userPhone = scan.nextInt();
										scan.nextLine();
										go = false;
										break;
									}
									catch(InputMismatchException e)
									{
										System.out.println("��¥�� ���ڷθ� �ٽ� �Է��ϼ���");
										scan.nextLine();
									}
								}
								
								manager.addUser(new User(userName, userPhone, userRentProduct, rentDate));
								
								break;
								
							case 3:
								System.out.println("����� �޴����� ������\n");
								stop = true;
								break;
								
							default:
								System.out.println("�����ϰ����ϴ� �޴��� ���ڷ� �ٽ� �Է��ϼ���\n");
								break;
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println("�޴� ��ȣ�� �ٽ� �Է��ϼ���");
							stop = true;
						}
						
					}
					break;
					
				case 3:
					//���α׷� ����
					System.out.println("���α׷����� ������");
					//scan.next("");
					stopUI = true;
					scan.close();
					break;
					
				default:
					System.out.println("�����ϰ����ϴ� �޴��� ���ڷ� �ٽ� �Է��ϼ���\n");
					break;
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("�޴� ��ȣ�� �ٽ� �Է��ϼ���");
			}
			
		}
	}
}
