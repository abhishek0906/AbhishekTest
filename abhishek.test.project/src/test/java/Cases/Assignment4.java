package Cases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Assignment4 {
	Assignment2 assignment2 = new Assignment2();
	
	@Test()
	public void countInputBoxes(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		System.out.println("number of input boxes on the page is: " + driver.findElements(By.xpath("//input")).size());
		driver.quit();
	}
	
	@Test()
	public void getColorCodeOfChangeColorButton(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		System.out.println("color code of Change color button is: " + driver.findElement(By.xpath("//*[text()='Change Color']")).getCssValue("color"));
		driver.quit();
	}
	
	@Test()
	public void countButtons(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		System.out.println("number of buttons on the page is: " + driver.findElements(By.xpath("//button")).size());
		driver.quit();
	}
	
	@Test()
	public void getButtonsText(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		List<WebElement> buttonsList = driver.findElements(By.xpath("//button"));
		System.out.println("text of 6th Button is: " + buttonsList.get(5).getText());
		System.out.println("text of last Button is: " + buttonsList.get(buttonsList.size()-1).getText());
		driver.quit();
	}
	
	@Test()
	public void getListText() throws InterruptedException{
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://www.seleniumframework.com/Practiceform/");
		Thread.sleep(15000);
		List<WebElement> linksList = driver.findElements(By.xpath("//a"));
		System.out.println("number of links on the page is: " + linksList.size());
		int i=0;
		for(i=0;i<linksList.size();i++){
			if(linksList.get(i).getText().trim().equals("Setup First Project")){
				System.out.println("Node for Setup First project is : " + (i + 1));
				break;
			}
		}
		System.out.println(linksList.get(i+1).getText());
		driver.quit();
	}
}
