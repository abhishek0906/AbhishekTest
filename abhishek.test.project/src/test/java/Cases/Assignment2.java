package Cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class Assignment2 {
	WebDriver driver = null;

	public WebDriver launchFirefox(){
		driver = new FirefoxDriver();
		return driver;
	}
	
	public WebDriver launchChrome(){
		System.setProperty(
		        "webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	public void launchIE(){
		System.setProperty(
		        "webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
}
