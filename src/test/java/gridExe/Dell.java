package gridExe;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sun.net.www.content.audio.basic;


public class Dell extends BasicTest{




	public Dell(WebDriver driver, String browser, boolean isMobileDevice) {
		super(driver, browser, isMobileDevice);
	}

	public void execTest()
	{
		System.out.println("***  START **** "+_exePlatform);

		_driver.get("http://dell.com");
		_driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		_driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		takeScreenShot(_exePlatform+"First Page",_driver);
		
		if (isMobileDevice()) {

			WebElement item = _driver.findElement(By.cssSelector("#openSearch"));
			item.click();
			_driver.findElement(By.xpath("(//input[@id=\"searchterms\"])[1]")).sendKeys("gift card");
			_driver.findElement(By.xpath("/html[1]/body[1]/header[1]/nav[1]/div[1]/div[5]/div[1]/form[1]/button[1]/span[1]")).click();
			_driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[3]/div[1]/div[3]/div[1]/div[1]/div[2]/h4[2]/a[1]")).click();
			
			takeScreenShot(_exePlatform+"Sarch GiftCard Page",_driver);
			
			_driver.findElement(By.cssSelector("#addToCartButton")).click();
			//_driver.findElement(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/button[1]")).click();
		}
		else {
			

			_driver.findElement(By.xpath(".//*[@id='searchinput']")).sendKeys("gift card");
			//_driver.findElement(By.xpath(".//*[@id='search-next-search-box']")).sendKeys("gift card");
			_driver.findElement(By.xpath(".//*[@id='revidHeader']/nav/div/div[3]/span[2]/form/button")).click();
			_driver.findElement(By.xpath(".//*[@id='accessories-title4']/h4")).click();
			
			takeScreenShot(_exePlatform+"Sarch GiftCard Page",_driver);
			
			_driver.findElement(By.xpath(".//*[@id='maincontentcnt']/table[2]/tbody/tr[1]/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr[1]/td[2]/table/tbody/tr[2]/td/a/img")).click();
			
		}
		
		
		takeScreenShot(_exePlatform+"Add to Cart Page",_driver);
		_driver.quit();
		 
	}



}
