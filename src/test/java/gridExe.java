package test.java;


import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import GridExe.Dell;
import GridExe.RWDFactory;


public class gridExe {

	String _Device;
	RemoteWebDriver _RWD;
	DesiredCapabilities _CP;

	@BeforeMethod
	public void beforeMethod() {
	}

	@BeforeTest
	public void beforeTest() {

		String host = Constants.PM_CLOUD;
		Reporter.log("Connect to:"+host);
	}

	@AfterSuite
	public void afterTest(){
		System.out.println("End Test");
	}


	@DataProvider(name = "Devices" , parallel = true)
	public Object[][] testSumInput() {
		return new Object[][] { { "Device", "0149BCA71700D01F" }, 
				{ "Device", "39F3DA5531ADBE2A05CFF4D65E43A2C38D3D595A" },
				{ "Browser", "firefox" },
				{ "Browser", "chrome" }
		};
	}



	//@Parameters({ "deviceID" })
	@Test (dataProvider="Devices" )
	public void testDevices(String type,String deviceID) {
		Dell test= null;
		if (type.equals("Device"))
		{
			_CP = new DesiredCapabilities("", "", Platform.ANY);
			_CP.setCapability("deviceName", deviceID);
			_RWD = (RemoteWebDriver) RWDFactory.getWebDriver(_CP,true);
			test = new Dell(_RWD,deviceID,true);
		}
		else
		{
			_CP  = new DesiredCapabilities(deviceID, "", Platform.ANY);
			_RWD = (RemoteWebDriver) RWDFactory.getWebDriver(_CP,false);
			test = new Dell(_RWD,deviceID,false);

		}

		test.execTest();
	}

}
