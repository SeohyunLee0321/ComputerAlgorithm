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
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch(IOException ioe)
		{
			System.out.println("������ ���� �� �����ϴ�");
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
