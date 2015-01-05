package GridExe;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class manTest {

	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DesiredCapabilities cp = new DesiredCapabilities("mobileDefault", "", Platform.ANY);
		cp.setCapability("deviceName", "0149BCA71700D01F");
		RemoteWebDriver rwd = (RemoteWebDriver) RWDFactory.getWebDriver(cp,true);
		
		Dell test = new Dell(rwd,"0149BCA71700D01F",true);
		test.execTest();
	}

}
