package gridExe;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class manTest {

	 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DesiredCapabilities cp = new DesiredCapabilities("", "", Platform.ANY);
		//cp.setCapability("deviceName", "0149BCA71700D01F");
		cp.setCapability("deviceName", "BC19BA60");
		RemoteWebDriver rwd = (RemoteWebDriver) RWDFactory.getWebDriver(cp,true);
		
		Dell test = new Dell(rwd,"BC19BA60",true);
		test.execTest();
	}

}
