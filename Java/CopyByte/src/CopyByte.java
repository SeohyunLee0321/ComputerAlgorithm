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
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch(IOException ioe){
			System.out.println("파일을 읽을 수 없습니다");
		}
		finally{
			try{
				reader.close();
				writer.close();

				System.out.println("파일 복사에 걸린 시간은 " + (end - start) + "ms 입니다.");
			}
			catch(Exception e){
			}
		}
	}
}