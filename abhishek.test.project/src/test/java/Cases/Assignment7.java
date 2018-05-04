package Cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment7 {
	Assignment2 assignment2 = new Assignment2();
	
	@Test()
	public void verifySaerchResults(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://adam.goucher.ca/parkcalc/");
		
		
		Select lot = new Select(driver.findElement(By.id("Lot")));
		lot.selectByVisibleText("Valet Parking");
		
		driver.findElement(By.id("EntryTime")).clear();
		driver.findElement(By.id("EntryTime")).sendKeys("02:00");
		driver.findElement(By.cssSelector("[name='EntryTimeAMPM'][value='PM']")).click();
		driver.findElement(By.xpath(".//*[@name='EntryDate']/..//*[contains(@href, 'EntryDate')]")).click();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.cssSelector("[bgcolor='#FFFF33']")).click();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.id("ExitTime")).clear();
		driver.findElement(By.id("ExitTime")).sendKeys("03:00");
		driver.findElement(By.cssSelector("[name='ExitTimeAMPM'][value='PM']")).click();
		driver.findElement(By.xpath(".//*[@name='ExitDate']/..//*[contains(@href, 'ExitDate')]")).click();
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.cssSelector("[bgcolor='#FFFF33'] + td")).click();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.name("Submit")).click();
		
		float valetParkingCharges = Float.parseFloat(driver.findElement(By.xpath(".//*[text()='COST']/../../td[2]//b")).getText().replace("$ ", ""));
		
		//Economy Parking
		lot = new Select(driver.findElement(By.id("Lot")));
		lot.selectByVisibleText("Economy Parking");
		
		driver.findElement(By.id("EntryTime")).clear();
		driver.findElement(By.id("EntryTime")).sendKeys("02:00");
		driver.findElement(By.cssSelector("[name='EntryTimeAMPM'][value='PM']")).click();
		driver.findElement(By.xpath(".//*[@name='EntryDate']/..//*[contains(@href, 'EntryDate')]")).click();
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.cssSelector("[bgcolor='#FFFF33']")).click();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.id("ExitTime")).clear();
		driver.findElement(By.id("ExitTime")).sendKeys("03:00");
		driver.findElement(By.cssSelector("[name='ExitTimeAMPM'][value='PM']")).click();
		driver.findElement(By.xpath(".//*[@name='ExitDate']/..//*[contains(@href, 'ExitDate')]")).click();
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.cssSelector("[bgcolor='#FFFF33'] + td")).click();
		driver.switchTo().window(tabs.get(0));
		
		driver.findElement(By.name("Submit")).click();
		
		float economyParkingCharges = Float.parseFloat(driver.findElement(By.xpath(".//*[text()='COST']/../../td[2]//b")).getText().replace("$ ", ""));
		
		assertTrue(economyParkingCharges <= (valetParkingCharges/2));
		driver.quit();
	}
	
	@Test()
	public void verifyOnlineShopping() throws InterruptedException{
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		
		//Number of dresses on home page
		assertTrue(driver.findElements(By.cssSelector("#homefeatured .ajax_block_product")).size()<10);
		
		//Number of dresses in dress->Summer
		driver.findElement(By.linkText("DRESSES")).click();
		driver.findElement(By.linkText("Summer Dresses")).click();
		assertEquals(driver.findElements(By.cssSelector(".product_list .ajax_block_product")).size(), 3);
		
		
		//White Dress filter
		driver.findElement(By.cssSelector("input.color-option[style='background: #ffffff;']")).click();
		Thread.sleep(2000);
		assertEquals(driver.findElement(By.cssSelector(".color_to_pick_list a[id *= color]")).getAttribute("style"),("background: rgb(255, 255, 255);"));
		driver.findElement(By.cssSelector("#enabled_filters .icon-remove")).click();
		
		//Price filters
		slider(driver);
		Thread.sleep(2000);
		assertEquals(driver.findElements(By.cssSelector(".product_list .ajax_block_product")).size(), 2);
		driver.quit();
	}
	
	@Test()
	public void addItemToWishList(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		
		//Number of dresses in dress->Summer
		driver.findElement(By.linkText("DRESSES")).click();
		driver.findElement(By.linkText("Summer Dresses")).click();

		//open Quick View
	    Actions move = new Actions(driver);
	    move.moveToElement(driver.findElement(By.cssSelector(".product_list .product_img_link"))).build().perform();
		driver.findElement(By.className("quick-view")).click();
		driver.switchTo().frame(driver.findElement(By.className("fancybox-iframe")));
		driver.findElement(By.id("wishlist_button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement errrorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fancybox-error")));
		assertEquals(errrorBox.getText(), "You must be logged in to manage your wishlist.");
		driver.quit();
	}
	
	
	@Test()
	public void addItemToCart(){
		WebDriver driver = assignment2.launchChrome();
		driver.get("http://automationpractice.com/index.php");
		
		//Find element
		driver.findElement(By.id("search_query_top")).clear();
		driver.findElement(By.id("search_query_top")).sendKeys("Printed Dress");
		driver.findElement(By.name("submit_search")).click();
		WebDriverWait wait = new WebDriverWait(driver, 60);

		//Add item to cart
	    Actions move = new Actions(driver);
	    String productTitle = driver.findElements(By.cssSelector(".product_list .ajax_block_product .product-name")).get(2).getText();
	    move.moveToElement(driver.findElements(By.cssSelector(".product_list .product_img_link")).get(2)).build().perform();
		driver.findElements(By.cssSelector(".product_list .ajax_block_product .ajax_add_to_cart_button")).get(2).click();
	
		//Verify product title
		WebElement cartProductTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_title")));
		assertEquals(cartProductTitle.getText(), productTitle);
		
		//checkout product
		driver.findElement(By.cssSelector("[title='Proceed to checkout']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".step_current.first")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart_navigation [title='Proceed to checkout']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-account_form")));
		
		driver.quit();
	}
	
	// 
	//.product_list .ajax_block_product .product-name
	//#layer_cart_product_title
	//[title='Proceed to checkout']
	//#create-account_form
	public static void slider(WebDriver driver){
			WebDriverWait wait = new WebDriverWait(driver, 60);
		    WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layered_price_slider")));
		    WebElement leftSlider = driver.findElement(By.cssSelector("#layered_price_slider a"));
		    int width= slider.getSize().getWidth();
		    Actions move = new Actions(driver);
		    move.dragAndDropBy(leftSlider, ((width* 75)/100), 0);
		    move.build().perform();
		    System.out.println("Slider moved");
		}
	
}
