package test.java;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import org.testng.annotations.AfterSuite;
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

public class emptyDPTest {

	String _Device;
	MobileDriver driver;
	@BeforeMethod
	public void beforeMethod() {
	}

	@BeforeTest
	public void beforeTest() {

		String host = Constants.PM_CLOUD;
		String user = Constants.PM_USER;
		String password = Constants.PM_PASSWORD;
		driver = new MobileDriver(host, user, password);
		Reporter.log("Connect to:"+host);
	}

	@AfterSuite
	public void afterTest(){
		driver.quit();
		InputStream reportStream = ((IMobileDriver) driver).downloadReport(MediaType.HTML);

		if (reportStream != null) {
			File reportFile = new File(Constants.REPORT_LIB+"TestNG_"+_Device+".HTML");
			try {
				FileUtils.write(reportStream, reportFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Reporter.log( Constants.REPORT_LIB+"TestNG_"+_Device+".HTML");

			String filename =Constants.REPORT_LIB+"TestNG_"+_Device+".HTML"  ;
			//	Reporter.log("</br><b>Report:</b> <a href=" + filename +">Report</a>");

			try {
				BufferedReader br = new BufferedReader(new FileReader(filename));

				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				Reporter.log("<DIV>");

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
	        						{ "Android", "CD6C2ED905F210B1" },
	        						{ "Android", "A1A5438E" } };
	    }
	 


	//@Parameters({ "deviceID" })
 	@Test (dataProvider="Devices" )
  	 public void testDevices(String type,String deviceID) {
 		System.out.println(" ******************* runtest on   " + deviceID);
 	 
	}

}
