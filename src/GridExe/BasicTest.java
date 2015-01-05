package GridExe;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.omg.DynamicAny._DynValueStub;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;

/*
 *
 *   Class Name  : PerfectoMobileBasicTest
 *	Author      : Uzi Eilon <uzie@perfectomobile.com>
 *	Date        : Dec 6th 2013  
 *	
 *	Description :
 *	Basic abstract perfecto mobile test - Each test need to extend this class and implement the actual test in the PerfectoMobileBasicTest
 *	This basic test handle the driver and the device
 */

public abstract class BasicTest{


	WebDriver _driver;
	boolean _status = true; 
	LinkedList<ImageDetails> _imagesList =new LinkedList();;
	int num= 1;
	boolean _isMobileDevice = false;
	String _exePlatform;

	public BasicTest (WebDriver driver,String browser, boolean isMobileDevice)
	{
		_driver = driver;
		_exePlatform = browser;
		_isMobileDevice = isMobileDevice;
 	} 


	public void run() {
		try
		{

			execTest();
		}catch (Exception e)
		{
			e.printStackTrace();

			_status = false; 
		}
 	}


	protected  void switchToContext(String context,WebDriver driver ) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}


	protected  void takeScreenShot(String name,WebDriver d)
	{
		try {
			// get screen shot 
			String screenshot ="c:\\test\\rwdTest"+name+".jpg";
			File screenshotFile = new File(screenshot);

			WebDriver augment = new Augmenter().augment(d);
			TakesScreenshot shot = (TakesScreenshot) augment;
			File file = shot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, screenshotFile);
			_imagesList.add(new ImageDetails(name,_exePlatform,screenshot) );

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public  void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}


	public   LinkedList<ImageDetails> getImagesList() {
		 return _imagesList;
	}


	public abstract void execTest() throws Exception ;
	
	public boolean isMobileDevice(){
		
		return _isMobileDevice;
	}

}
