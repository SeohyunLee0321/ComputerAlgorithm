import java.io.*;

public class ObjectExample4 {
	public static void main(String args[]) {
		GoodsStock obj = new GoodsStock("73527", 200);
		System.out.println(obj);
	}
}

class GoodsStock {
	String goodsCode;
	int stockNum;
	GoodsStock(String goodsCode, int stockNum) {
		this.goodsCode = goodsCode;
		this.stockNum = stockNum;
	}
	public String toString() {
		String str = "��ǰ�ڵ� : " + goodsCode + "\t������ : " + stockNum;
		return str;
	}
}
