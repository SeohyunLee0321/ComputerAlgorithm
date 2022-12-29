import java.io.*;

public class ObjectInputExample2 {
	public static void main(String args[]) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("output2.dat"));
			while (true) {
				GoodsStocks obj = (GoodsStocks) in.readObject();
				System.out.println("��ǰ�ڵ�:" + obj.code + "\t��ǰ����:" + obj.num);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		} catch (EOFException eofe) {
			System.out.println("��");
		} catch (IOException ioe) {
			System.out.println("������ ���� �� �����ϴ�.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
}
