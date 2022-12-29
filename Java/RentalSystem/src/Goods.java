package rentalService;

public class Goods { // 물품클래스 public으로 한 이유는 메인함수에서 사용됨
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

	// 재고관리 메소드
	public void addStock() { // 재고추가메소드
		goodsCount++;
	}

	public void substractStock() throws Exception {// 재고삭제메소드
		if (getGoodsCount() == 0) { // 해당 물품의 재고가 없는 경우 예외처리
			throw new Exception("재고가 없습니다");// 다음과 같은 메세지 출력 -> 재고감소안함
		} else {
			goodsCount--;
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
