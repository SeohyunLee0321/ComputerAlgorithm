import java.util.Scanner;

class CountLetter
{
	public static void main(String args[])
	{
		Scanner scan  = new Scanner(System.in);	//scan에 사용자 입력 값 할당

		//26개의 정수 저장 가능한 배열 count 선언 및 생성
		int[] countSmall = new int[26];		//소문자 배열
		int[] countBig = new int[26];		//대문자 배열

		System.out.println("문자열을 입력하시오 : ");
		String buffer = scan.nextLine();		//buffer에 scan으로 입력한 문자열 할당

		// 각 문자가 등장하는 횟수를 계산
		for (int i = 0; i < buffer.length(); i++)
		{
			char ch = buffer.charAt(i);

			if (ch >= 'a' && ch <= 'z')	//ch가 a~z 사이에 있어야 countSmall++
				countSmall[ch - 'a']++;
			else if (ch >= 'A' && ch <= 'Z')		//ch가 A~Z 사이에 있어야 countBig++
				countBig[ch - 'A']++;
		}
	
		//배열에 저장된 횟수 출력하는 반복루프
		for (int i = 0; i < 26; i++)
		{
			if (countBig[i] != 0)		//대문자 알파벳이 쓰인 횟수가 0이 아니라면
				System.out.println((char)('A' + i) + " : " + countBig[i]);

			if (countSmall[i] != 0)	//소문자 알파벳이 쓰인 횟수가 0이 아니라면
				System.out.println((char)('a' + i) + " : " + countSmall[i]);
		}
	}
}