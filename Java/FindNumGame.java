//1부터 100사이의 발생된 임의의 숫자를 5번안에 맞추는 게임.
//

import java.util.Scanner;
import java.util.Random;

public class FindNumGame {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Random ranGen = new Random();
		int ranNum, answer, count=1;
		ranNum=ranGen.nextInt(100)+1;
		while(count<6) {
		// 여기를 채우세요
		// 입력한 숫자와 랜덤값을 비교해서 작은가 큰가를 사용자에게 알려준다
		//  맞춘경우는  몇번만에 정답을 맞추었습니다  라고 표시 후 종료

			System.out.println(count + " 번 째 시도, 숫자를 입력하세요: ");	//몇번째 시도인지 print
			answer = scan.nextInt();		//사용자 입력

			if (answer == ranNum)		//정답이면 print후 break
			{
				System.out.println("정답입니다! " + count + " 번 만에 정답을 맞추었습니다");

				break;
			}
			else if (answer > ranNum)	//랜덤 숫자가 입력값보다 작으면
			{
				System.out.println("입력한 숫자가 답보다 큽니다");
				count++;
			}
			else	//랜덤 숫자가 입력값보다 크면
			{
				System.out.println("입력한 숫자가 답보다 작습니다");
				count++;
			}
		}
		if (count == 6)	//답 못맞추면 print(랜덤 숫자 뭐였는지 함께 출력)
			System.out.println("정답을 5번 시도에 맞추지 못했습니다.");
			System.out.println("답은 " + ranNum + " 입니다");
	}

}
