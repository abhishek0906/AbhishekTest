/**
 * 
 */
package DataDrivenAssignments;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author admin
 *
 */
public class base {
	
	/**
	 * using Webdriver Manager to handle Chrome updation automatically
	 * and launching browser with desired capabilities
	 * @return
	 */
	public WebDriver getDriver()
	{
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = null;
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
	    chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
	    options.setExperimentalOption("prefs", chromePrefs);
		
		options.addArguments(new String[] { "--test-type" });
		options.addArguments(new String[] { "--no-sandbox"});
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		return driver;
	}
	
	public WebElement findElement(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
			return null;
		}
	}

	public void clickElement(WebDriver driver, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}
	
	public void enterText(WebDriver driver, By locator, String text) {
		try {
			WebElement element = findElement(driver, locator);
			element.click();
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
	}


	/**
	 * @param driver
	 * This method waits for complete page load and refreshes if not loaded
	 */
	public void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
        	Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }
}
