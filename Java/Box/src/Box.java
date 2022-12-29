
class Box {
	private int width;
	private int length;
	private int height;
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setLength(int length)
	{
		this.length = length;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getVolume()
	{
		return width * height * length;
	}
	
	public String toString()
	{
		return "width : " + getWidth() + "\nlength : " + getLength() + "\nheight : " + getHeight() + "\nvolume : " + getVolume();
	}
}