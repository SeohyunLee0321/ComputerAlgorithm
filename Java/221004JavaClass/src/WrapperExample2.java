
public class WrapperExample2 {
	public static void main(String args[]) {
		int total = 0;
		for(int cnt = 0; cnt < args.length; cnt++) {
			try {
				Integer obj = new Integer(args[cnt]);
				total += obj.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("숫자를 입력해야 합니다");
			}
		}
		System.out.println(total);
	}
}
