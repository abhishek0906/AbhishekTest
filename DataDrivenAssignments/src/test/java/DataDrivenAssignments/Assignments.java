/**
 * 
 */
package DataDrivenAssignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


/**
 * @author admin
 *
 */
public class Assignments extends base{
	ExcelManager excelManager = new ExcelManager();
	String testResultsWorkbookPath = System.getProperty("user.dir")+"/DataDrivenAssignment1.xls";
	
	By searchBox		= By.cssSelector(".search_query");
	By searchBtn		= By.cssSelector("[name='submit_search']");
	By searchResultText	= By.cssSelector(".heading-counter");
	
	WebDriver driver = null;
	
	@Test
	public void searchResults() {
		
		//open the browser and navigate to the site
		driver = getDriver();
		driver.get("http://automationpractice.com/index.php");
		
		excelManager.openExcelFile(testResultsWorkbookPath, 1);
		int numberOfRows = excelManager.getTotalNumberOfRows();
		for (int i = 1; i < numberOfRows; i++) {
			
			//read search text from excel and enter into the search box
			enterText(driver, searchBox, excelManager.getCellValue(i, 0));
			clickElement(driver, searchBtn);
			waitForPageLoaded(driver);
			
			//get the searched result and update it in excel
			excelManager.addCellData(i, 2, findElement(driver, searchResultText).getText().trim());
			excelManager.saveChangesInExcel();
			
			//Assert the results
			String result = String.valueOf((excelManager.getCellValue(i, 1).equals(excelManager.getCellValue(i, 2))));
			
			//update the result in the excel sheet.
			excelManager.addCellData(i, 3, result);
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
