import java.io.*;

class Copychar {
	public static void main(String args[]) {
		FileReader reader = null;		// FileReader 클래스
		FileWriter writer = null;		// FileWriter 클래스
		char arr[] = new char[100];	// 파일로부터 읽은 문자를 담을 char 배열
		
		try {
			reader = new FileReader(args[0]);	// args[0] 에 저장된 파일명의 파일을 열기
			
			// 파일 읽고 처리
			while(true) {
				int data = reader.read(arr);	// 데이터를 읽고 arr 배열에 파일로부터 읽은 문자 담기
				if(data == -1)	// 문장이 끝나면 break
					break;
			}
		}
		catch (FileNotFoundException fnfe) {		// FileReader 생성자가 발생하는 익셉션 처리
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch (IOException ioe) {		// FileReader 의 read, close 메소드가 발생하는 익셉션 처리
			System.out.println("파일을 읽을 수 없습니다.");
		}
		finally {
			try {
				reader.close();	// 파일 닫기
			}
			catch (Exception e) {
			}
		}
		
		try {
			writer = new FileWriter(args[1]);		// 현재 디렉토리에 args[1]에 입력한 파일명의 파일을 새로 만들어 열기

			writer.write(arr);		// arr 배열에 있는 모든 문자들을 파일로 출력
		}
		catch(IOException ioe) {		// FileWriter 의 read, close 메소드가 발생하는 익셉션 처리
			System.out.println("파일로 출력할 수 없습니다");
		}
		finally {
			try {
				writer.close();	// 파일 닫기
			}
			catch (Exception e) {
			}
		}
	}
}