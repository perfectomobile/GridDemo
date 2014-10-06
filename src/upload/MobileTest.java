package upload;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.perfectomobile.selenium.MobileDriver;


public class MobileTest {


	public static void main(String[] args) {
		String device = Constants.DEVICE;

		if (args.length == 1) {
            device = args[0] ;  
            System.out.println("Device id :"+device);

             
        } else {
           System.out.println("** using default device ID");
        } 
 		try {
			System.out.println("** Uploading app to Perfecto Mobile repository ***");
			//uploadfile();
			uploadfilewithPM();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("** App uploaded successfully ***");
		
		try {
			System.out.println("** Installing app on real device / devices ***");

		 	installApp(device);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("** app installed on the device / devices ***");

 	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

	private static void uploadfilewithPM() throws Exception {

		String host = Constants.PM_CLOUD;
		String user = Constants.PM_USER;
		String password = Constants.PM_PASSWORD;
		MobileDriver d = new MobileDriver(host, user, password);
		File app = new File(Constants.WORK_LIB+Constants.APP_NAME);
		d.uploadMedia(Constants.REPOSITORY_KEY, app);
	}
	private static void uploadfile() throws Exception {

		String USER_AGENT = "Mozilla/5.0";
		String url = "https://"+Constants.PM_CLOUD+"/services/repositories/media/"+Constants.REPOSITORY_KEY+"?operation=upload&user="+Constants.PM_USER+"&password="+Constants.PM_PASSWORD+"&overwrite=true";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		String urlParameters = "";

		//	String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		File file = new File(Constants.WORK_LIB+Constants.APP_NAME); 
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] buffer = new byte[1024*1024];
		int bytesRead = 0;

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());

		while((bytesRead = fileInputStream.read(buffer))>0)
		{
			wr.write(buffer,0,bytesRead);
		}

		fileInputStream.close();



		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			response.append("\n");

		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	public static void installApp(String device) throws Exception 
	{


		String openurl = "https://"+Constants.PM_CLOUD+"/services/executions?user="+Constants.PM_USER+"&password="+Constants.PM_PASSWORD+"&operation=start";

		String res = htmlcall (openurl);
		int  start = res.indexOf("\":\"")+3; 
		int  end =  res.indexOf("\",\""); ; 
		System.out.println(res);

		String execID = res.substring(start, end);
		System.out.println(execID);



		res = htmlcall (getCMD(execID,"handset","open","",device));
		System.out.println(res);

		res = htmlcall (getCMD(execID,"application","install","&param.file="+Constants.REPOSITORY_KEY,device));

		// close scripts
		String closeURL ="https://"+Constants.PM_CLOUD+"/services/executions/"+execID+"?user="+Constants.PM_USER+"&password="+Constants.PM_PASSWORD+"&operation=end";

 		res = htmlcall (closeURL);
	}


	public static String getCMD(String execID,String cmd,String sub_cmd,String parmas,String deviceID)  
	{
		return  "https://"+Constants.PM_CLOUD+"/services/executions/"+execID+"?user="+Constants.PM_USER+"&password="+Constants.PM_PASSWORD+"&operation=command&command="+cmd+"&subcommand="+sub_cmd+"&param.handsetId="+deviceID+parmas;

	}

	public static String htmlcall(String url) throws Exception 
	{
		String USER_AGENT = "Mozilla/5.0";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			response.append("\n");

		}

		in.close();

		//print result
		return response.toString();


	}

}
