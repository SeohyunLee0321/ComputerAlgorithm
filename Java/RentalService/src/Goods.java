public class Goods implements java.io.Serializable { // 물품클래스 public으로 한 이유는 메인함수에서 사용됨
	// 멤버변수
	private String goodsName; // 물품이름
	private String goodsCode; // 물품코드
	private int goodsCount; // 물품개수
	private int price;

	// 생성자 함수
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
	
	// 기존의 equals 함수를 무시 (오버라이딩)
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Goods))
			return false;
		Goods g = (Goods)o;
		// 물품 코드가 같으면 true 반환
		return g.getGoodsCode().equals(goodsCode);
	}
	
	public void addStock(int n) { // 재고추가메소드
		goodsCount += n;
	}
	
	public void subStock(int n) throws Exception {	// 재고삭제메소드
		if (getGoodsCount() < n) {	// 해당 물품의 재고가 없는 경우 예외처리
			throw new Exception("재고가 없습니다");
		} else {
			goodsCount -= n;
		}
	}

	// 게터세터 함수
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
