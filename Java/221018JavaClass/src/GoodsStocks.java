public class GoodsStocks implements java.io.Serializable { // 재고 정보 클래스
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
			throw new Exception("재고가 부족합니다.");
		this.num -= num;
		return num;
	}
}