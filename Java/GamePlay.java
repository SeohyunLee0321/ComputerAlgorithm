import java.util.Scanner;

public class GamePlay {

	public static void main (String[] args) {

		Scanner scan = new Scanner(System.in);
		int cheolsooPick = 0;
		int youngheePick = 0;

		while (cheolsooPick == 0 || youngheePick == 0)	//처음 0,0으로 선언되어있거나 오류(0) 발생 시 while 반복으로 다시 입력 받기
		{
			try
			{
				System.out.print("철수 >> ");
				String cheolsoo = scan.next();	//철수의 선택 입력받기

				if (cheolsoo.equals("가위"))
					cheolsooPick = 1;		//가위를 입력받으면 1로 초기화
				else if (cheolsoo.equals("바위"))
					cheolsooPick = 2;		//바위를 입력받으면 2로 초기화
				else if (cheolsoo.equals("보"))
					cheolsooPick = 3;		//보를 입력받으면 3으로 초기화
				else
				{
					cheolsooPick = 0;		//오류 시 0으로 초기화
					throw new Exception();		//오류 발생
				}
			}
			catch (Exception e)
			{
				System.out.println("철수의 입력을 다시 확인하세요");	//오류 메시지
			}

			try
			{
				System.out.print("영희 >> ");
				String younghee = scan.next();	//영희의 선택 입력받기

				if (younghee.equals("가위"))
					youngheePick = 1;		//가위를 입력받으면 1로 초기화
				else if (younghee.equals("바위"))
					youngheePick = 2;		//바위를 입력받으면 2로 초기화
				else if (younghee.equals("보"))
					youngheePick = 3;		//보를 입력받으면 3으로 초기화
				else
				{
					youngheePick = 0;		//오류 시 0으로 초기화
					throw new Exception();		//오류 발생
				}
			}
			catch (Exception e)
			{
				System.out.println("영희의 입력을 다시 확인하세요");	//오류 메시지
			}
			
			if (cheolsooPick == 0 || youngheePick == 0)		//영희 또는 철수의 입력에 오류가 나오면
				System.out.println("가위 바위 보 중에서 다시 입력하세요");
			else if (cheolsooPick == youngheePick)		//영희와 철수의 입력이 같다면
				System.out.println("비겼습니다");
			else if ((cheolsooPick == 1 && youngheePick == 2) ||
				(cheolsooPick == 2 && youngheePick == 3) ||
				(cheolsooPick == 3 && youngheePick == 1))	//영희가 이기는 경우
				System.out.println("영희가 이겼습니다");
			else		//철수가 이기는 경우
				System.out.println("철수가 이겼습니다");
		}
	}
}