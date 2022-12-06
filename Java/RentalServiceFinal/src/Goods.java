import java.util.ArrayList;

public class Goods implements java.io.Serializable { // 물품클래스 public으로 한 이유는 메인함수에서 사용됨
	// 멤버변수
	private String goodsName; // 물품이름
	private String goodsCode; // 물품코드
	private int goodsCount; // 물품개수
	private int price;	// 물품가격
	private String image;	// 물품 이미지 경로

	// 생성자 함수
	// 이미지 경로를 저장하기 위해 image 경로를 저장하는 image 변수 Goods 생성자의 parameter로 추가-----------------------------------------------------------
	public Goods(String goodsName, String goodsCode, int goodsCount, int price, String image) {
		super();
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
		this.goodsCount = goodsCount;
		this.price = price;
		this.image = image;
	}
	
	// 이미지 경로 get, set 함수 -----------------------------------------------------------------------------------------------------------
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	// --------------------------------------------------------------------------------------------------------------------------------
	
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
