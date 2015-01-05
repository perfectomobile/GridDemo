package GridExe;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RWDFactory {

	 
	public static WebDriver getWebDriver(DesiredCapabilities cp)
	{
		return getWebDriver(cp,false);
		
	}

	public static WebDriver getWebDriver(DesiredCapabilities cp,boolean device) 
	{
		System.out.println("*get web driver*");


		URL serverURL= null;
		try {
			// Grid 
			if (device)
			{
				//standalon
				//serverURL = new URL("http://54.187.19.29:4444/wd/hub"); 
				
				String host;
				try {
					host = URLEncoder.encode("demo.perfectomobile.com", "UTF-8");
					String user = URLEncoder.encode("uzie@perfectomobile.com", "UTF-8");
					String password = URLEncoder.encode("Perfecto1", "UTF-8");
		              				
					serverURL = new URL("https://" + user + ":" + password + "@" + host + "/nexperience/wd/hub");	
					
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			else
			{
				// Grid
				serverURL = new URL("http://54.68.7.220:4444/wd/hub");

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebDriver _driver = new RemoteWebDriver(serverURL, cp);
		System.out.println("*end web driver*");

		return _driver;

	}

}
