package Cases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Assignment5 {
	Assignment2 assignment2 = new Assignment2();
	
	@Test()
	public void clickElement8Link(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Element8")));
		link.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(), 'Agile Testing and ATDD Automation')]")));
		driver.quit();
	}
	
	@Test()
	public void fillForm(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
		driver.findElement(By.name("firstname")).clear();
		driver.findElement(By.name("firstname")).sendKeys("Abhishek");
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys("Gupta");
		driver.findElement(By.id("sex-0")).click();
		driver.findElement(By.id("exp-3")).click();
		driver.findElement(By.id("datepicker")).clear();
		driver.findElement(By.id("datepicker")).sendKeys(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
		driver.findElement(By.id("profession-1")).click();
		driver.findElement(By.id("tool-2")).click();
		Select continent = new Select(driver.findElement(By.id("continents")));
		continent.selectByVisibleText("South America");
		Select seleniumCommands = new Select(driver.findElement(By.id("selenium_commands")));
		seleniumCommands.selectByVisibleText("Switch Commands");
		driver.findElement(By.id("submit")).click();
		driver.quit();
	}
	
	@Test()
	public void getTop10Gainers(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("https://money.rediff.com/losers/bse/daily");
		driver.findElement(By.cssSelector("[href='//money.rediff.com/gainers/bse']")).click();
		List<WebElement> gainersList = driver.findElements(By.cssSelector("table.dataTable tr td:nth-child(1) a"));
		System.out.println("List of Top 10 gainers is: ");
		for (int i = 0; i < 10; i++){
			System.out.println(gainersList.get(i).getText());
		}
		driver.quit();
	}
	
	@Test()
	public void getTop10Loosers(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("https://money.rediff.com/losers/bse/daily");
		driver.findElement(By.cssSelector("[href='//money.rediff.com/losers/bse/']")).click();
		List<WebElement> gainersList = driver.findElements(By.cssSelector("table.dataTable tr td:nth-child(1) a"));
		System.out.println("List of Top 10 Loosers is: ");
		for (int i = 0; i < 10; i++){
			System.out.println(gainersList.get(i).getText());
		}
		driver.quit();
	}
}
