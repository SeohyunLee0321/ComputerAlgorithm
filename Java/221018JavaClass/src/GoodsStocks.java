public class GoodsStocks implements java.io.Serializable { // ��� ���� Ŭ����
	String code;
	int num;

	GoodsStocks(String code, int num) {
		this.code = code;
		this.num = num;
	}

	void addStock(int num) {
		this.num += num;
	}

	int subtractStock(int num) throws Exception {
		if (this.num < num)
			throw new Exception("��� �����մϴ�.");
		this.num -= num;
		return num;
	}
}