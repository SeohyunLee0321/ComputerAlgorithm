import java.io.*;

public class ObjectOutputExample2 {
	public static void main(String args[]) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("output2.dat"));
			out.writeObject(new GoodsStocks("70101", 100));
			out.writeObject(new GoodsStocks("70102", 50));
			out.writeObject(new GoodsStocks("70103", 200));
			}
			catch (IOException ioe) {
			System.out.println("파일로 출력할 수 없습니다.");
			}
			finally {
			try {
			out.close();
			}
			catch (Exception e) {
			}
			}
	}
}