package Cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Assignment3 {
	
	Assignment2 assignment2 = new Assignment2();

	@Test()
	public void navigatePracticeForm(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		driver.findElement(By.xpath("//*[@id='main-nav']//span[text()='HOME']")).click();
		driver.navigate().back();
		driver.quit();
	}
	
	@Test()
	public void getLinkTitle(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		System.out.println(driver.findElement(By.linkText("This is a link")).getAttribute("title"));
		driver.quit();		
	}
	
	@Test()
	public void subscribeWithEmail(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		driver.findElement(By.xpath("//*[text()='Enter your email address:']/parent::form//input[@name='email']")).clear();
		driver.findElement(By.xpath("//*[text()='Enter your email address:']/parent::form//input[@name='email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.cssSelector("[type='submit'][value='Subscribe']")).click();
		driver.quit();		
	}
	
	@Test
	public void enterContactUsDetails(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Test Name");
		driver.findElements(By.name("email")).get(1).clear();
		driver.findElements(By.name("email")).get(1).sendKeys("abc@gmail.com");
		driver.findElement(By.name("telephone")).clear();
		driver.findElement(By.name("telephone")).sendKeys("+12345678");
		driver.findElement(By.xpath("//input[@name='country']")).clear();
		driver.findElement(By.xpath("//input[@name='country']")).sendKeys("USA");
		driver.findElement(By.xpath("//input[@name='company']")).clear();
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Meta");
		driver.findElement(By.xpath("//textarea[@name='message']")).clear();
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("This is a test message");
		driver.findElement(By.partialLinkText("Subm")).click();
		driver.quit();
	}
}
