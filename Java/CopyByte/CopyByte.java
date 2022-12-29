import java.io.*;

public class CopyByte {
	public static void main(String args[]) {

		FileInputStream inputStream = null;	// FileReader 클래스
		FileOutputStream outputStream = null;	// FileWriter 클래스
	
		long start = 0;	// 파일을 복사하기 시작하는 시간을 저장할 변수
		long end = 0;	// 파일 복사가 끝나는 시간을 저장할 변수

		start = System.currentTimeMillis();	// 시작 시간 start 변수에 저장

		try {
			inputStream = new FileInputStream(args[0]);		// args[0] 에 저장된 파일을 열기
			outputStream = new FileOutputStream(args[1]);	// 디렉토리에 args[1] 에 저장된 이름의 파일 만들어서 열기

			while(true) {
				int data = inputStream.read();	// 데이터 읽기

				if(data < 0)	// 파일이 끝나면 break
					break;
				byte b = (byte) data;	// byte 로 형변환

				outputStream.write(b);	// 데이터 쓰기
			}

			end = System.currentTimeMillis();	// 끝 시간 end 변수에 저장
		}
		// exception catch
		catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch(IOException ioe){
			System.out.println("파일을 읽을 수 없습니다");
		}
		finally{
			try{
				// 파일 닫기
				inputStream.close();
				outputStream.close();

				// 걸린 시간 출력
				System.out.println("파일 복사에 걸린 시간은 " + (end - start) + "ms 입니다.");
			}
			catch(Exception e){
			}
		}
	}
}