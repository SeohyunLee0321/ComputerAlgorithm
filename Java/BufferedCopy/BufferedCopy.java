import java.io.*;

public class BufferedCopy {
	public static void main(String args[]) throws Exception {
		
		long start = 0;	// ������ �����ϱ� �����ϴ� �ð��� ������ ����
		long end = 0;	// ���� ���簡 ������ �ð��� ������ ����

		start = System.currentTimeMillis();	// ���� �ð� start ������ ����
		
		BufferedInputStream in = null;		// BufferedInputStream Ŭ����
		BufferedOutputStream out = null;	// BufferedOutputStream Ŭ����

		try {
				if(args.length > 2) {	// 3�� �̻��� ���ϸ� �Է����� ��� exception
					Exception e = new Exception("2���� ���ϸ� �Է��ϼ���");
					throw e;
				}
				in = new BufferedInputStream(new FileInputStream(args[0]));	// args[0] �� ����� ������ ����
				//out = new BufferedOutputStream(new FileOutputStream(args[1]),1024);		// ���丮�� args[1] �� ����� �̸��� ���� ���� ����
				out = new BufferedOutputStream(new FileOutputStream(args[1]),1024);
				byte arr[] = new byte[1024];	// 1024 byte �� �о���� buffer �����
				
				while(true) {
					int data = in.read(arr);	// ������ �б�
						
					if(data < 0)	// ������ ������ break
						break;
					for(int cnt = 0; cnt < data; cnt++)
						//out.write(arr[cnt]);
						out.write(data);
				}
				end = System.currentTimeMillis();	// �� �ð� end ������ ����
		}
		// exception catch
		catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		
		catch (IOException ioe){
			System.out.println("������ ���� �� �����ϴ�");
		}
		catch (ArrayIndexOutOfBoundsException ae) {	// 1���� ���ϸ� �Է����� ���
			System.out.println("���� ��� ���ϸ�� ������ ������ ������ ���ϸ��� �Է����ּ���");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			try{
				// ���� �ݱ�
				out.close();
				in.close();

				// �ɸ� �ð� ���
				System.out.println("���� ���翡 �ɸ� �ð��� " + (end - start) + "ms �Դϴ�.");
			}
			catch(Exception e){
			}
		}
	}
}