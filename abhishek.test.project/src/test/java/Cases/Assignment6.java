package Cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment6 {
	Assignment2 assignment2 = new Assignment2();
	
	@Test()
	public void notEqual(){
		assertNotEquals(3, 5);
	}
	
	@Test()
	public void greaterThan(){
		assertFalse(3 > 5);;
	}
	
	@Test
	public void isPallindrome() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter a number: ");
		int number = reader.nextInt(); 
		reader.close();
		int r, sum = 0, temp;
		temp = number;
		
		while (number > 0) {
			r = number % 10;
			sum = (sum * 10) + r;
			number = number / 10;
		}
		if (temp == sum)
			System.out.println("Entered number is a palindrome.");
		else
			System.out.println("Entered number is not a palindrome");
	}
	
	@Test()
	public void navigateToHomePage(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.cssSelector(".logo.img-responsive")).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homepage-slider")));
		driver.quit();
	}
	
	@Test()
	public void navigateToContactUsPage(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.linkText("Contact us")).click();
		assertEquals(driver.findElement(By.cssSelector(".page-heading.bottom-indent")).getText(), "CUSTOMER SERVICE - CONTACT US");
		driver.quit();
	}
	
	@Test()
	public void verifySaerchResults(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.id("search_query_top")).clear();
		driver.findElement(By.id("search_query_top")).sendKeys("ABCDEFGH");
		driver.findElement(By.name("submit_search")).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-warning")));
		assertEquals(errorMessage.getText(), "No results were found for your search \"ABCDEFGH\"");
		driver.quit();
	}
}
