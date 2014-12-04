package test.java;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.options.MobileDeviceProperty;

public class TestNGUnitedTest {

	String _Device;
	@BeforeMethod
	public void beforeMethod() {
		
	}

	@BeforeTest
	public void beforeTest() {

	
	}

 	public void afterTest(MobileDriver driver){
		driver.quit();
		InputStream reportStream = ((IMobileDriver) driver).downloadReport(MediaType.HTML);

		if (reportStream != null) {
			File reportFile = new File(Constants.REPORT_LIB+"TestNG_"+_Device+".HTML");
			FileUtils.write(reportStream, reportFile);
			Reporter.log( Constants.REPORT_LIB+"TestNG_"+_Device+".HTML");

			String filename =Constants.REPORT_LIB+"TestNG_"+_Device+".HTML"  ;
			//	Reporter.log("</br><b>Report:</b> <a href=" + filename +">Report</a>");

			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));

				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				Reporter.log("<DIV valign=\"top\" align=\"left\" style=\"font-family: Verdana; font-style: normal; font-variant: normal; font-weight: normal; font-size: 10pt; color: black; text-indent: 0em; letter-spacing: normal; word-spacing: normal; text-transform: none;margin-top: 0pt; margin-bottom: 20pt; height: 3.146in; width: 10.562in; white-space: normal; line-height: normal\">");

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				Reporter.log(sb.toString());

				Reporter.log("</DIV>");
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}

	 @DataProvider(name = "Devices" , parallel = true)
	    public Object[][] testSumInput() {
	        return new Object[][] { { "Android", "0149BCA71700D01F" }, 
	        			{ "Android", "A1A5438E" } ,
	        			{"iphone","39F3DA5531ADBE2A05CFF4D65E43A2C38D3D595A"}
	        };
	    }
	 
	//@Parameters({ "deviceID" })
	@Test (dataProvider="Devices")
	public void CheckFlight(String Type,String deviceID) {
		_Device = deviceID;
 		String host = Constants.PM_CLOUD;
		String user = Constants.PM_USER;
		String password = Constants.PM_PASSWORD;
		MobileDriver driver = new MobileDriver(host, user, password);
		Reporter.log("Connect to:"+host);
 		System.out.println(" **** new Drivewr  " + deviceID);

		Reporter.log("device:"+deviceID);
		IMobileDevice device = driver.getDevice(deviceID);
		Reporter.log("device MODEL :"+device.getProperty(MobileDeviceProperty.MODEL));
		Reporter.log("device OS :"+device.getProperty(MobileDeviceProperty.OS));

		PerfectoTest t = new PerfectoTest();
		String rc =  t.checkFlights(device);

		//assert rc.equals("New York/Newark, NJ (EWR)") : "Expected  New York/Newark, NJ (EWR)" + rc;
		afterTest(driver);

	}

}
