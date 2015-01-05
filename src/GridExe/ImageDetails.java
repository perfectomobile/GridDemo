package GridExe;

public class ImageDetails {
	
	String _image;
	String _name;
	String _device;
	
	public ImageDetails(String testName,String deviceID,String screenShot) 
	{
		_image=screenShot;
		_name=testName;
		_device=deviceID;
	}

	public String getName()
	{
		return _name;
	} 

	
	public String getDevice()
	{
		return _device;
	} 
	
	public String getScreen()
	{
		return _image;
	} 
	
}
