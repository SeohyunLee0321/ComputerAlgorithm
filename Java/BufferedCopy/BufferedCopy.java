import java.io.*;

public class BufferedCopy {
	public static void main(String args[]) throws Exception {
		
		long start = 0;	// 파일을 복사하기 시작하는 시간을 저장할 변수
		long end = 0;	// 파일 복사가 끝나는 시간을 저장할 변수

		start = System.currentTimeMillis();	// 시작 시간 start 변수에 저장
		
		BufferedInputStream in = null;		// BufferedInputStream 클래스
		BufferedOutputStream out = null;	// BufferedOutputStream 클래스

		try {
				if(args.length > 2) {	// 3개 이상의 파일명 입력했을 경우 exception
					Exception e = new Exception("2개의 파일명만 입력하세요");
					throw e;
				}
				in = new BufferedInputStream(new FileInputStream(args[0]));	// args[0] 에 저장된 파일을 열기
				//out = new BufferedOutputStream(new FileOutputStream(args[1]),1024);		// 디렉토리에 args[1] 에 저장된 이름의 파일 만들어서 열기
				out = new BufferedOutputStream(new FileOutputStream(args[1]),1024);
				byte arr[] = new byte[1024];	// 1024 byte 씩 읽어오는 buffer 만들기
				
				while(true) {
					int data = in.read(arr);	// 데이터 읽기
						
					if(data < 0)	// 파일이 끝나면 break
						break;
					for(int cnt = 0; cnt < data; cnt++)
						//out.write(arr[cnt]);
						out.write(data);
				}
				end = System.currentTimeMillis();	// 끝 시간 end 변수에 저장
		}
		// exception catch
		catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		catch (IOException ioe){
			System.out.println("파일을 읽을 수 없습니다");
		}
		catch (ArrayIndexOutOfBoundsException ae) {	// 1개의 파일명만 입력했을 경우
			System.out.println("복사 대상 파일명과 복사한 파일을 저장할 파일명을 입력해주세요");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally{
			try{
				// 파일 닫기
				out.close();
				in.close();

				// 걸린 시간 출력
				System.out.println("파일 복사에 걸린 시간은 " + (end - start) + "ms 입니다.");
			}
			catch(Exception e){
			}
		}
	}
}