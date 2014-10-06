package upload;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Driver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.impl.StaticLoggerBinder;

import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.options.touch.MobileTouchOptions;
import com.sun.xml.internal.ws.api.pipe.Tube;


public class uploadandinstall {

	static MobileDriver d ;
	static String repKey;
	static String appName;
	static String buildLocation ="";
	static String[] devices;
	public static void main(String[] args) {


		System.out.println("*** args111:"+args.length);

		// override the device as runtime parameter
		if (args.length == 1) {
			devices = args[0].split(",");
			System.out.println("Device id :"+devices.length ); 

		} else if (args.length == 2) 
		{

			devices = args[0].split(",");

			System.out.println("Device id :"+devices.length ); 

			buildLocation = args[1] ;
			System.out.println("Device and build location been added :"+ buildLocation );
		} else			
		{
			System.out.println("** using default device ID");
		} 

		try {
			setupParam();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("**an not read the file set up the parameters form the default value");

			// can not read the file set up the parameters form the default value
		}

		try {
			System.out.println("** Uploading app to Perfecto Mobile repository ***");
			 	uploadfilewithPM(d);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("** App uploaded successfully ***");

		try {

			for(int i =0; i < devices.length; i++)
			{
				System.out.println("** Installing app on real device / devices ***"+devices[i]);

				installAppOnDevice(d,devices[i]);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("** app installed on the device / devices ***");



		d.quit();

	}



	private static void setupParam() throws IOException {


		System.out.println("** OPEN FILE ****");

		String intLib = "";
		if (buildLocation.equals(""))
		{
			intLib="C:\\teamCity\\Perfecto";
		}else
		{
			intLib=buildLocation;
		}
		System.out.println("** OPEN FILE ****"+intLib+"\\buildParam.txt");

		File f = new File(intLib+"\\buildParam.txt");
		FileReader reader = new FileReader(f);
		BufferedReader in = new BufferedReader(reader);

		String cloud = null;
		String password = null;
		String user = null;

		while( in.ready()== true)
		{

			String line =  in.readLine();

			if (line.contains("="))
			{
				line.trim();
				line.replace(" ","");
				String key = line.substring(0,line.indexOf("="));
				String val = line.substring(line.indexOf("=")+1);

				switch (key.toUpperCase()) {
				case "DEVICE":
					if (devices.length ==0) 
					{
						devices = val.split(",");
					}
					break;
				case "APP_NAME":
					appName=val;			
					break;
				case "PM_USER":
					user=val;			
					break;
				case "PM_PASSWORD":
					password=val;			
					break;
				case "PM_CLOUD":
					cloud=val;			
					break;
				case "REPOSITORY_KEY":
					repKey=val;			
					break;
				case "BUILD_LOCATION":
					if (buildLocation.equals(""))
					{
						buildLocation=val;
					}					 		
					break;
				default:
					break;
				}

			}
		}

		in.close();
		reader.close();


		try {
			d = new MobileDriver(cloud,user,password);
		} catch (Exception e) {
			System.out.println("** ERROR :  can not connect to the cloud check the network or paramaters file");
		}
	}


	private static void uploadfilewithPM(MobileDriver driver ) {

		//	File app = new File(Constants.WORK_LIB+Constants.APP_NAME);
		//	driver.uploadMedia(Constants.REPOSITORY_KEY, app);

		File app = new File(buildLocation+"\\"+appName);
		if (app.exists())
		{
			driver.uploadMedia(repKey+appName, app);
		}
		else
		{		
			System.out.println("** ERROR :  can not find the latest build under : "+buildLocation+"\\"+appName);
		}
	}
	private static void installAppOnDevice(MobileDriver driver,String DeviceId ) {
		IMobileDevice device = driver.getDevice(DeviceId);
		device.open();

		device.installApplication(repKey+appName,true); 
		device.close();

	}



}
