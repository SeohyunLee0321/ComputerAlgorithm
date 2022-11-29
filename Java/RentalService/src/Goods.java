public class Goods implements java.io.Serializable { // ��ǰŬ���� public���� �� ������ �����Լ����� ����
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
	
	public Goods() {}
	
	public Goods(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	// ������ equals �Լ��� ���� (�������̵�)
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Goods))
			return false;
		Goods g = (Goods)o;
		// ��ǰ �ڵ尡 ������ true ��ȯ
		return g.getGoodsCode().equals(goodsCode);
	}
	
	public void addStock(int n) { // ����߰��޼ҵ�
		goodsCount += n;
	}
	
	public void subStock(int n) throws Exception {	// �������޼ҵ�
		if (getGoodsCount() < n) {	// �ش� ��ǰ�� ��� ���� ��� ����ó��
			throw new Exception("��� �����ϴ�");
		} else {
			goodsCount -= n;
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
