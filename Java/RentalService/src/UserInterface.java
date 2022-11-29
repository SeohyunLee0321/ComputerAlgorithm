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
			System.out.println("������ �ҷ��Խ��ϴ�");
			if(ois != null)
				ois.close();
		} catch (FileNotFoundException fnfe) {
			while (true) {
				System.out.println("Database.txt ������ ã�� �� �����ϴ�");
				System.out.println("database.txt ������ ����� ���̶�� '1'��, ���α׷� ���� �����̸� '2'�� �����ּ���");
				int answer = sc1.nextInt();
				
				if(answer == 1) {
					System.out.println("database.txt ������ �ٽ� ã�� �� ���丮�� �������ּ���");
					System.exit(0);
				}
				else if(answer == 2) {
					System.out.println("���α׷��� ���� �����մϴ�");
					m = new Management(100, 100, 100);
					break;
				}
				else
					System.out.println("1 �Ǵ� 2 �� �Է����ּ���");
			} 
		} 
		
		while (isOnLoop == true) {
			Scanner sc = new Scanner(System.in);

			// ����ڿ��� �޴� �����ֱ�
			System.out.println("-------��Ż�������α׷�-------");
			System.out.println("1. �뿩��ǰ���");
			System.out.println("2. �뿩��ǰ����");
			System.out.println("3. ��ü��ǰ��ȸ");
			System.out.println("4. ���ǰ�˻�");
			System.out.println("5. ��ǰ�뿩");
			System.out.println("6. �ݳ�");
			System.out.println("7. �뿩����Ȯ��");
			System.out.println("8. �Ѹ��� �� ������ Ȯ��");
			System.out.println("9. ����");
			System.out.println("10. ����");
			System.out.println("--------------------------");

			// ����ڿ��� �̿��� �޴� ���� �ޱ�
			System.out.println("���Ͻô� �޴��� ��ȣ�� �Է��� �ֽʽÿ�.");
			int userChoice = sc.nextInt();// ����ڰ� ������ �޴��� ��ȣ

			try {
				switch (userChoice) {

				case 1: // ��ǰ���
					System.out.println("����Ϸ��� ��ǰ�� �̸��� �Է����ֽʽÿ�.");
					String name = sc.next();
					System.out.println("����Ϸ��� ��ǰ�� ������ �Է����ֽʽÿ�.");
					int count = sc.nextInt();
					System.out.println("����Ϸ��� ��ǰ�� ��ǰ�ڵ带 �Է����ֽʽÿ�.");
					String code = sc.next();
					System.out.println("����Ϸ��� ��ǰ�� ������ �Է����ֽʽÿ�.");
					int price = sc.nextInt();
					Goods g = new Goods(name, code, count, price);// ��ǰ��ü����
					try {
						m.addGoods(g);// ��ǰ����Ʈ�� �߰�
					} catch (Exception e) {
						System.out.println("�ߺ��� Ű���Դϴ�.");
						continue;
					}
					break;// switch�� ����

				case 2: // ��ǰ����
					System.out.println("�����Ϸ��� ��ǰ�� �ڵ带 �Է����ֽʽÿ�");
					String dGoodsCode = sc.next(); // �����Ϸ��� ��ǰ�� �ڵ�
					try {
						m.deleteGoods(dGoodsCode);// ��ǰ�� ��ǰ����Ʈ���� ����
						System.out.println("�ش� ��ǰ�� �����Ǿ����ϴ�.");
					} catch (Exception e) { // ����ó��
						System.out.println("�ش� ��ǰ�� ���� �ڵ尡 �����ϴ�.");
						continue;
					}
					break;// switch�� ����

				case 3: // ��ü ��ǰ �����ֱ�
					if (m.goodsAt(0) == null) // ��ǰ�� ���� ���
						System.out.println("��ǰ�� �������� �ʽ��ϴ�.");
					else {// ��ǰ�� ������ ���
						for (int i = 0; i < m.getgCount(); i++) {
							System.out.println("��ǰ��: " + m.goodsAt(i).getGoodsName() + " |��ǰ�ڵ�: "
									+ m.goodsAt(i).getGoodsCode() + " |��ǰ����: " + m.goodsAt(i).getGoodsCount());
						}
					}
					break;// switch�� ����

				case 4: // ��� ��ǰ �˻�
					System.out.println(" ");
					System.out.println("�˻��Ϸ��� ��ǰ�� �ڵ带 �Է����ֽʽÿ�.");
					try {
						String goodsCode = sc.next(); // �˻��Ϸ��� ��ǰ�� �ڵ�
						int goodsIndex = m.search(goodsCode); // �˻��Ϸ��� ��ǰ�� �ε���
						// ��ǰ�� ���� ���
						if (m.goodsAt(0) == null)
							System.out.println("��ǰ�� �������� �ʽ��ϴ�.");
						// ��ǰ�� �������
						else {
							// ��� 0�� ���
							if (m.goodsAt(goodsIndex).getGoodsCount() == 0) {
								System.out.println("�ش� ��ǰ�� ��� �����ϴ�.");
							}
							// ��� �������
							else {
									int i = m.search(goodsCode);// �ش� ��ǰ�� �ε���
									// �ش� ��ǰ ���� ���� ���
									System.out.println("��ǰ��: " + m.goodsAt(i).getGoodsName() + " |��ǰ�ڵ�: "
											+ m.goodsAt(i).getGoodsCode() + " |��ǰ����: " + m.goodsAt(i).getGoodsCount());
							}
						}
					} catch (Exception e) { // �������� �ʴ� �ڵ带 �Է��� ��� (search error catch)
						System.out.println("�ش� ��ǰ�� ���� �ڵ尡 �����ϴ�.");
						continue;
					}

					break; // switch�� ����

				case 5: // �뿩�ϱ�(checkIn ���)

					// ����� ���� �Է¹ޱ�
					System.out.println("������ �Է����ֽʽÿ�.");
					String userName = sc.next(); // ����� �̸�
					System.out.println("��ȭ��ȣ�� �Է����ֽʽÿ�.");
					String phoneNum = sc.next(); // ����� ��ȭ��ȣ =�˻��� Ű��

					// �뿩 ���� �Է¹ޱ�
					Calendar today = Calendar.getInstance();
					String rentDate = new SimpleDateFormat("yyyyMMdd").format(today.getTime());

					// �ݳ� ���� ���� �Է¹ޱ�
					System.out.println("�ݳ� ���� ���ڸ� �����ּ���(yyyyMMdd �������� ����) : ");
					String dueDate = sc.next();

					// User ��ü ����
					User u1 = new User(userName, phoneNum, rentDate, dueDate);

					// �뿩�� ��ǰ ���� �Է� �ޱ�
					System.out.println("�뿩�� ��ǰ ������ �����ּ���(�ִ� 3��) : ");
					int rentalNum = sc.nextInt();

					// �뿩�� ��ǰ ������ 4�̻��϶� �ͼ��� �߻�
					if (rentalNum >= 4)
						throw new Exception("3�������� ���� �� �ֽ��ϴ�");

					// �뿩�� ��ǰ �ڵ� �Է� �ޱ�
					for (int i = 0; i < rentalNum; i++) {
						System.out.println("�뿩�� ��ǰ �ڵ带 �����ּ��� : ");
						String rentalCode = sc.next();
						int index = m.search(rentalCode);
						int payment = m.goodsAt(index).getPrice();
						u1.addCode(rentalCode, payment); // �뿩 ��ǰ �ڵ� �迭�� �ڵ� �߰�
					}

					// üũ���ϱ�
					m.checkIn(u1);
					System.out.println("��ǰ �뿩�� �Ϸ�Ǿ����ϴ�.");
					break;

				case 6:// üũ�ƿ��ϱ�

					System.out.println("��ȭ��ȣ�� �Է����ֽʽÿ�.");
					String phone = sc.next(); // ����� ��ȭ��ȣ �Է¹ޱ�

					// ��ġ�ϴ� ����� ã��
					try {
						int index = m.searchUser(phone);
						System.out.println("�̸�: " + m.userAt(index).getUserName() 
								+ "\n������¥: " + m.userAt(index).getRentDate()
								+ "\n�ݳ�������: " + m.userAt(index).getDueDate() + "\n������ǰ����: "
								+ m.userAt(index).getRentalCount());
						int money = m.userAt(index).payCalculate();
						System.out.println("���ұݾ�: " + money);
						
						// �� ���ҿ��� �����
						System.out.println("�����Ͻðڽ��ϱ�? ������ 1, �ƴϸ� 2�� �����ֽʽÿ� : ");
						int answer = sc.nextInt();
						
						// 1�Է� -> üũ�ƿ� ������ ���
						if (answer == 1) {
							m.checkOut(index);// üũ�ƿ�����
							System.out.println("�ݳ��� �Ϸ�Ǿ����ϴ�.");
						}
						// 2�Է�-> üũ�ƿ� ���� ���� ���
						else if (answer == 2)
							System.out.println("������ ��ҵǾ����ϴ�. �ٽ� �������ֽʽÿ�.");
						// �߸��� ��ȣ �Է����� ���
						else
							System.out.println("�߸��� ��ȣ�� �Է��߽��ϴ�.");
					} catch (Exception e) {
						System.out.println("�ش� ����ڸ� ã�� �� �����ϴ�");
					}

					break;// switch�� ����

				case 11:
					/*System.out.println("��ȭ��ȣ�� �Է����ֽʽÿ�.");
					String phone2 = sc.next(); // ����� ��ȭ��ȣ �Է¹ޱ�
					int index2 = m.searchUser(phone2);
					User u = m.userAt(index2);
					System.out.println("�̸�: " + m.userAt(index2).getUserName() 
							+ "\n������¥: " + m.userAt(index2).getRentDate()
							+ "\n�ݳ�������: " + m.userAt(index2).getDueDate() + "\n������ǰ����: "
							+ m.userAt(index2).getRentalCount());
					System.out.println("-------��Ʈ�� ��ǰ���-------");
					for (int i = 0; i < m.userAt(index2).getRentalCount(); i++) {
						String rentalCode = m.userAt(index2).codeAt(i);
						System.out.println("�뿩 ��ǰ �ڵ�" + (i + 1) + ")" + rentalCode);
					}
					System.out.println("��� ��ǰ�� �ݳ��Ͻðڽ��ϱ�?");
					int returnCount = sc.nextInt();
					for (int i = 0; i < returnCount; i++) {
						System.out.println("�ݳ��� �����ڵ带 �Է��ϼ���");
						String gCode = sc.next();
						m.returnGoodsNew(index2, gCode);
						m.userAt(index2).subCode(u, gCode);
						System.out.println(gCode + " �� �ݳ��� �Ϸ�Ǿ����ϴ�.");
					}
					if(u.getRentalCount() == 0) {
						m.checkOut(index2);
						System.out.println("����ڰ� üũ�ƿ��Ǿ����ϴ�");
					}*/
					
					break;
					
				case 7: // �뿩���� �����ֱ�
					System.out.println("�뿩���� ��ȸ�� ���� ȸ������ ��ȭ��ȣ�� �Է��� �ֽʽÿ�.");
					String phoneNumber = sc.next();// ����� ���� Ű������ ��ȭ��ȣ �Է¹���
					try {
						int index = m.searchUser(phoneNumber);
						System.out.println("�̸�: " + m.userAt(index).getUserName() + "\n��ȭ��ȣ: "
								+ m.userAt(index).getPhoneNum() + "\n������¥: " + m.userAt(index).getRentDate()
								+ "\n�ݳ�������: " + m.userAt(index).getDueDate() + "\n������ǰ����: "
								+ m.userAt(index).getRentalCount());
						System.out.println("-------��Ʈ�� ��ǰ���-------");
						for (int i = 0; i < m.userAt(index).getRentalCount(); i++) {
							String rentalCode = m.userAt(index).codeAt(i);
							System.out.println("�뿩 ��ǰ �ڵ�" + (i + 1) + ")" + rentalCode);
						}
					} catch (Exception e) {
						System.out.println("�ش� ����ڸ� ã�� �� �����ϴ�");
					}

					break;// switch�� ����
					
				case 8:
					System.out.println("------------������Ȳ------------");
					System.out.println("���� ���� �� �հ�:" + m.getDailySales());
					for (int i = 0; i < m.getsCount(); i++) {
						System.out.println((i + 1) + "]" + m.getSalesList().get(i) + "��");
					}
					break; // switch�� ����

				case 9: // ���α׷� ����
					ObjectOutputStream oos = null;
					try {
						oos = new ObjectOutputStream(new FileOutputStream("Database.txt"));
						m.saveAll(oos);
					} finally {
						try {
							if(oos != null)
								oos.close();
						} catch (Exception e) {
							System.out.println("���� �ݱ⿡ �����߽��ϴ�");
						}
					}
					
					
					System.out.println("������ �Ϸ�Ǿ����ϴ�");
					break;

				case 10: // ���α׷� ����
					System.out.println("���α׷��� �����մϴ�.");
					isOnLoop = false;
					break;
					
				/*case 12:
					System.out.println("���� ��ǰ?");
					String subGoods = sc.next();
					System.out.println("��� � ����?");
					int sub = sc.nextInt();
					m.subGoodsStock(m.search(subGoods), sub);
					break;*/

				default:// ����ڰ� �߸� �Է��� ���
					System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("�����ϰ����ϴ� �޴��� ��ȣ�� �ٽ� �Է��ϼ���");
			}
		}
	}
}