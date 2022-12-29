import java.io.*;

public class InputStream {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader reader = null;
		try
		{
			reader = new FileReader("output.dat");
			while(true)
			{
				int data = reader.read();
				if(data < 0)
					break;
				byte b = (byte) data;
				System.out.println(b);
			}
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch(IOException ioe)
		{
			System.out.println("파일을 읽을 수 없습니다");
		}
		finally
		{
			try
			{
				reader.close();
			}
			catch(Exception e)
			{
				
			}
		}
	}
}
