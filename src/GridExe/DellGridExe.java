package GridExe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class DellGridExe {
	
	@Test
	public static void testIt() {
		// TODO Auto-generated method stub

		LinkedList<Thread> t_link = new LinkedList<Thread>();
		LinkedList<BasicTest> tests = new LinkedList<BasicTest>();

		// Android
		DesiredCapabilities pm_capabilitie_a = new DesiredCapabilities("", "", Platform.ANY);
		pm_capabilitie_a.setCapability("deviceName", "067C0546439C8F54");

		// iPhone
		DesiredCapabilities pm_capabilitie_i = new DesiredCapabilities("", "", Platform.ANY);
		pm_capabilitie_i.setCapability("deviceName", "DC1F1FBB3710248A2CA9D1EF1A189427E77D4D3D");


		DesiredCapabilities chrome_capabilities = new DesiredCapabilities("chrome", "", Platform.ANY);
		DesiredCapabilities fireFox_capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);

		// exec of Perfecto 

		WebDriver w = getWebDriver(pm_capabilitie_a,true);
		Dell pm = new Dell(w,"PM_Android",true);
		tests.add(pm);


		WebDriver w1 = getWebDriver(pm_capabilitie_i,true);
		Dell pm1 = new Dell(w1,"PM_iphone",true); 
		tests.add(pm1);

		//Dell chrome = new Dell(getWebDriver(chrome_capabilities),"Chrome",false);
		//tests.add(chrome);

		 
		Dell fireFox = new Dell(getWebDriver(fireFox_capabilities),"FireFox",false);
		tests.add(fireFox);


		System.out.println("***");

		for (BasicTest test : tests) {
			Thread thread = new Thread(test);
			thread.start();

			t_link.add(thread);
		}


		// wait for all the threads 
		try {
			for (Thread tt : t_link) {

				tt.join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All wait");


		// building the report 
		File f = new File("c:\\test\\rep\\new.html") ;
		FileWriter output;
		try {
			output = new FileWriter(f);
			output.write(repUtil.Pre);
			output.write(repUtil.preGrid);

			for (BasicTest test : tests) {
				LinkedList<ImageDetails> images =  test.getImagesList();

				// loop over all the images and build the grid 
				for (ImageDetails image : images) {
					output.write(repUtil.addLineGrid(image.getScreen(), image.getDevice(), image.getName()));
				}
			}
			output.write(repUtil.postGrid);
			output.write(repUtil.preSlides);

			for (BasicTest test : tests) {
				LinkedList<ImageDetails> images =  test.getImagesList();

				// loop over all the images and build the grid 
				for (ImageDetails image : images) {
					output.write(repUtil.addLineSlideshow(image.getScreen(), image.getDevice(), image.getName()));
				}
			}
			output.write(repUtil.postSlides);
			output.write(repUtil.Post);

			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

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
					String user = URLEncoder.encode("hadass@perfectomobile.com", "UTF-8");
					String password = URLEncoder.encode("123456", "UTF-8");
		              				
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