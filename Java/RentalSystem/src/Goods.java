package rentalService;

public class Goods { // ��ǰŬ���� public���� �� ������ �����Լ����� ����
	// �������
	private String goodsName; // ��ǰ�̸�
	private String goodsCode; // ��ǰ�ڵ�
	private int goodsCount; // ��ǰ����
	private int price;

	// ������ �Լ�
	public Goods(String goodsName, String goodsCode, int goodsCount, int price) {
		super();
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
		this.goodsCount = goodsCount;
		this.price = price;
	}

	// ������ �޼ҵ�
	public void addStock() { // ����߰��޼ҵ�
		goodsCount++;
	}

	public void substractStock() throws Exception {// �������޼ҵ�
		if (getGoodsCount() == 0) { // �ش� ��ǰ�� ��� ���� ��� ����ó��
			throw new Exception("��� �����ϴ�");// ������ ���� �޼��� ��� -> ����Ҿ���
		} else {
			goodsCount--;
		}
	}

	// ���ͼ��� �Լ�
	public String getGoodsName() {
		return goodsName;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public int getPrice() {
		return price;
	}

}
