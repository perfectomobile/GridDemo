
package scripts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import test.java.Constants;

import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.api.IMobileWebDriver;
import com.perfectomobile.selenium.by.ByMobile;

public class Jenkins {


	public static void main(String[] args)
	{
		JenkinsApp();
	}

	public static void JenkinsApp()   {


		MobileDriver PMdriver = new MobileDriver();

		IMobileDevice device = PMdriver.getDevice("5CDCB21A");

		//	IMobileDevice device = PMdriver.getDevice("0149BCA71700D01F");
		WebDriver webdriver= device.getNativeDriver("JUC 2014");
		device.getNativeDriver("JUC 2014").open();

		IMobileWebDriver visual = device.getVisualDriver();
		visual.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		visual.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

		visual.findElement(ByMobile.image("PUBLIC:Jenkins_menu.png")).click();



		webdriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		webdriver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

		webdriver.findElement(By.linkText("Speakers")).click();

		webdriver.findElement(By.xpath("//*[@contentDesc=\"Search\"]")).click();
		device.getMobileKeyboard().sendKeys("uzi");


		webdriver.findElement(By.xpath("//*[text()=\"Uzi Eilon\"]")).click();
		webdriver.findElement(By.xpath("//text[text()=\"Sessions\"]")).click();
		visual.findElement(By.linkText("Plug Real Devices into Your Jenkins")).click();

		webdriver.close();
		PMdriver.quit();
 		InputStream reportStream = PMdriver.downloadReport(MediaType.HTML);

		File reportFile = new File("c:\\test\\jenkins.HTML");
		
		try {
			FileUtils.write(reportStream, reportFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
