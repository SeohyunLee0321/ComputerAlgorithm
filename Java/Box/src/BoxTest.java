
class BoxTest {

	public static void main(String[] args) {
		Box box1 = new Box();
		
		box1.setWidth(100);
		box1.setHeight(100);
		box1.setLength(100);
		
		System.out.println("box1 volume : " + box1.getVolume());
		System.out.println(box1);
		
		Box box2 = new Box();
		
		box2.setWidth(200);
		box2.setHeight(200);
		box2.setLength(200);
		
		box1 = box2;
		
		System.out.println("box1 width after assign : " + box1.getWidth());
		System.out.println("box1 height after assign : " + box1.getHeight());
		System.out.println("box1 length after assign : " + box1.getLength());
		System.out.println("box1 volume after assign: " + box1.getVolume());
	}

}
