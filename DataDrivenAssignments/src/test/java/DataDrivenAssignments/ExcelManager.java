/**
 * 
 */
package DataDrivenAssignments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.testng.util.Strings;

/**
 * @author admin
 *
 */
public class ExcelManager {
	HSSFWorkbook workbook = null;
    HSSFSheet sheet = null;
    File file1 = null;
    DataFormatter formatter = new DataFormatter();

    public void openExcelFile(String path, int sheetNumber){
		try{
			file1 = new File(path);
	        FileInputStream file = new FileInputStream(new File(path)); 
	        workbook = new HSSFWorkbook(file);
	        sheet = workbook.getSheetAt(sheetNumber-1);	
		}catch(Exception e){
			System.out.println("Not able to open Excel File due to error message" + e.getMessage());
		}
	 }
    
    public void addCellData(int rownum, int col, String value){
        Row row = sheet.getRow(rownum);
        row.createCell(col).setCellValue(value);
        saveChangesInExcel();
     } 
    
    public int getLastRow(){
    	return sheet.getLastRowNum();
    }
    
    public String getCellValue(int rownum, int colnum){
    	Row row = sheet.getRow(rownum);
		String rowValue = formatter.formatCellValue(row.getCell(colnum));
		return rowValue;
    }
    
    public void saveChangesInExcel(){
    	try{
    		FileOutputStream out = new FileOutputStream(file1);
            workbook.write(out);
            out.flush();
            out.close();
    	}catch(Exception e){
    		System.out.println("Not able to save changes in Excel File due to error message" + e.getMessage());
    	} 
    }

	public int getTotalNumberOfRows() {

		int totalNonEmptyCells = 0;
		for (Row row : sheet) {
			for (Cell cell : row) {
				String text = formatter.formatCellValue(cell);
				if (Strings.isNotNullAndNotEmpty(text)) {
					totalNonEmptyCells++;
					break;
				}
			}
		}
		return totalNonEmptyCells;
	}
}
