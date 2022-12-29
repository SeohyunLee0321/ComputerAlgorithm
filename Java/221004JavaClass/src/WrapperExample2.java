
public class WrapperExample2 {
	public static void main(String args[]) {
		int total = 0;
		for(int cnt = 0; cnt < args.length; cnt++) {
			try {
				Integer obj = new Integer(args[cnt]);
				total += obj.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("���ڸ� �Է��ؾ� �մϴ�");
			}
		}
		System.out.println(total);
	}
}
