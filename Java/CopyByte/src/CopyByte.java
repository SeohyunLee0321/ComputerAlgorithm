import java.io.*;

public class CopyByte {
	public static void main(String args[]) {

		FileInputStream reader = null;
		FileOutputStream writer = null;
	
		long start = 0;	
		long end = 0;

		try {
			reader = new FileInputStream(args[0]);
			writer = new FileOutputStream(args[1]);
			
			start = System.currentTimeMillis();

			while(true) {
				int data = reader.read();

				if(data < 0)
					break;
				byte b = (byte) data;

				writer.write(b);
			}

			end = System.currentTimeMillis();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch(IOException ioe){
			System.out.println("������ ���� �� �����ϴ�");
		}
		finally{
			try{
				reader.close();
				writer.close();

				System.out.println("���� ���翡 �ɸ� �ð��� " + (end - start) + "ms �Դϴ�.");
			}
			catch(Exception e){
			}
		}
	}
}