import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Management { // ������ Ŭ����
	// (1) ��ǰ �迭
	private ArrayList<Goods> goodsList = new ArrayList<Goods>();
	// (2) ����� �迭
	private ArrayList<User> userList = new ArrayList<User>();
	// (3) ���� �迭
	private ArrayList<Integer> salesList = new ArrayList<Integer>();
	private Integer dailySales = 0; // �� ���� �Ѿ�

	Management () {}
	
	// �Ŵ�����Ʈ ������ �Լ� -> ��ǰ �迭, ����� �迭, ���� �迭�� ũ�� ����
	Management(int maxGoodsCount, int maxUserCount, int maxSalesCount) {
		goodsList = new ArrayList<Goods>(maxGoodsCount);// ��ǰ �迭 ũ�� ����
		userList = new ArrayList<User> (maxUserCount);// �뿩 �迭 ũ�� ����
		salesList = new ArrayList<Integer>(maxSalesCount);// ���� �迭 ũ�� ����
	}
	
	Management(int maxGoodsCount, int maxUserCount, int maxSalesCount, ObjectInputStream ois) throws Exception {
		goodsList = new ArrayList<Goods>(maxGoodsCount);// ��ǰ �迭 ũ�� ����
		userList = new ArrayList<User>(maxUserCount);// �뿩 �迭 ũ�� ����
		salesList = new ArrayList<Integer>(maxSalesCount); // ���� �迭 ũ�� ����

		// ���� ������ �о����
		try {
			// ��ǰ �迭 ä���
			int gCount = (Integer) ois.readObject();
			for (int i = 0; i < gCount; i++) {
				Goods g = (Goods) ois.readObject();
				goodsList.add(g);
			}
			// ���� �迭 ä���
			int uCount = (Integer) ois.readObject();
			for (int i = 0; i < uCount; i++) {
				User u = (User) ois.readObject();
				userList.add(u);
			}
			// ���� �迭 ä���
			int sCount = (Integer) ois.readObject();
			for (int i = 0; i < sCount; i++) {
				int sales = (Integer) ois.readObject();
				salesList.add(sales);
			}
			// ���� ���� �ҷ�����
			dailySales = (Integer) ois.readObject();
		} catch (FileNotFoundException fnfe) {
		} catch (EOFException eofe) {
		} catch (IOException ioe) {
		}

	}
	
	// ��ǰ ���� �Լ�
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
			throw new Exception ("������ ������ �� �����ϴ�");
		}
	}

	// -------------------------��ǰ ���� �޼ҵ�------------------------------

	// �ڵ� �ߺ� üũ
	public void checkCode(Goods g) throws Exception {
		if (goodsList.indexOf(g) >= 0)
			throw new Exception ("�ߺ��� �ڵ��Դϴ�.");
	}

	public void addGoods (Goods g) throws Exception{
		if(goodsList != null)
			checkCode(g);
		goodsList.add(g);
	}

	// ���� ��ǰ�� ������ �� ��ǰ�� �����ϴ� �޼ҵ�
	public void deleteGoods(String goodsCode) throws Exception {
		try {
			goodsList.remove(search(goodsCode));
		} catch (Exception e) {
			throw new Exception("�������� �ʴ� �ڵ��Դϴ�.");
		}
	}

	// ��ǰ �ڵ�� ��ü�� �˻��Ͽ� �ε��� ��ȣ�� ��ȯ
	public int search(String goodsCode) throws Exception {	//�ش� ��ǰ ã�� �� ������ UI���� catch
		return goodsList.indexOf(new Goods(goodsCode));
	}

	// i��° ��ǰ ��ü�� ��ȯ
	public Goods goodsAt(int i) {
		try {
			return goodsList.get(i);
		} catch (IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	// ��� ����
	public void subGoodsStock(int index, int n) throws Exception {	// catch in Goods subStock()
		goodsList.get(index).subStock(n);
	}
	
	// ��� ����
	public void addGoodsStock(int index, int n) {
		goodsList.get(index).addStock(n);
	}
	
	public void setImage(int index, String image) {
		goodsList.get(index).setImage(image);
	}
	
	// -------------���� ���� �޼ҵ�-------------------------

	// �ߺ��� ��ȭ��ȣ ���� Ȯ��
	public void checkPhone(User u) throws Exception {
		if(userList.indexOf(u) >= 0)
			throw new Exception ("�ߺ��� ��ȭ��ȣ�Դϴ�");
	}

	// �뿩 �� ��� ����
	public void subStock(User u) throws Exception {
		for (int i = 0; i < u.getRentalCount(); i++) {
			String code = u.codeAt(i); // �ش� ������ �뿩�� ��ǰ�� �ڵ�
			int searchIdx;
			try {
				searchIdx = search(code); // goodsList���� �ش� �ڵ��� �ε��� ���
			} catch (Exception e) {
				throw new Exception("��ġ�ϴ� �ڵ带 ã�� �� �����ϴ�.");
			}
			Goods g = goodsList.get(searchIdx); // �ش� �ε����� Goods ��ü ���
			g.subStock(1); // ��� 1�� ����
		}
	}

	// ����� �迭�� ���� ��ü �߰�
	public void addUser(User u) {
		userList.add(u);
	}

	// üũ��
	public void checkIn(User u) throws Exception {
		try {
			checkPhone(u);
			subStock(u);
			addUser(u);
		} catch (Exception e) {
			throw new Exception ("�߸��� üũ���Դϴ�");
		}
	}
	
	public User userAt(int i) {
		try {
			return userList.get(i);
		} catch (IndexOutOfBoundsException iobe) {
			return null;
		}
	}
	
	// ��ġ�ϴ� ȸ����ȣ �˻�
	public int searchUser(String phone) throws Exception {	//����� ã�� �� ������ UI���� catch
		return userList.indexOf(new User(phone));
	}

	// �ݳ��� ��ǰ ��� �ٽ� ����
	public void returnGoods(int uIdx) throws Exception {
		User u = userAt(uIdx);
		for (int i = 0; i < u.getRentalCount(); i++) { // �ش� ����ڰ� ���� ��ǰ������ŭ �ݺ�
			String code = u.codeAt(i); // �ش� User�� �뿩�� i��° ��ǰ�� �ڵ�
			try {
				int gIdx = search(code); // goodsList���� �ش� �ڵ��� �ε��� ��ȣ �˻�
				goodsAt(gIdx).addStock(1); // �ش� goods ��ü�� ��� �߰��ϱ�
			} catch (Exception e) {
				throw new Exception("��ǰ�ڵ带 ã���� �����ϴ�");
			}
		}
	}
	
	// ã�� ��ǰ�ڵ��� ������ ����
	public void returnGoodsNew (int uIdx, String gCode) throws Exception {
		User u = userAt(uIdx);
		for (int i = 0; i < u.getRentalCount(); i++) {
			String code = u.codeAt(i);
			if (code.equals(gCode)) {
				try {
					int gIdx = search(code);
					goodsAt(gIdx).addStock(1);
				} catch (Exception e) {
					throw new Exception("��ǰ�ڵ带 ã���� �����ϴ�");
				}
			}
		}
	}

	// ����� ��ü �迭���� ����
	public void deleteUser(int uIdx) {
		userList.remove(uIdx);
	}
	
	public void addSales(int money) throws Exception {
		dailySales += money; // �� ���⿡ ���ϱ�
		salesList.add(money);// �� ���� ��� �迭�� �߰�
	}

	// üũ�ƿ� �޼ҵ�
	public void checkOut(int uIdx) throws Exception {
		try {
			deleteUser(uIdx); // �ش� ������ü ����
		} catch (Exception e) {
			throw new Exception("�߸��� üũ�ƿ��Դϴ�");
		}

	}

	// ���ͼ��� �Լ�
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
