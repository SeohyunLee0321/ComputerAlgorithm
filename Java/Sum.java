class Sum {
	public static void main(String args[])
	{
		int sum = 0;
	
		for (int i = 0; i < args.length; i++) 	//for(String str : args)
			sum += Integer.parseInt(args[i]);

		System.out.println("sum : " + sum);
	}
}