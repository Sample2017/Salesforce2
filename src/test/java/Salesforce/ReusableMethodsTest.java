package Salesforce;
    
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;  
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
  
public class ReusableMethodsTest {     
	public static void clickElement(WebElement obj, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.click();
			System.out.println("Pass: "+ objName + " is clicked.");
		}else{
			System.out.println("Fail: "+ objName + " is not clicked.");
		}
	}


	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			System.out.println("Pass: "+objName+" is enetered with value");
		}else{
			System.out.println("Fail: "+objName+" is not enetered with value");
		}
	}
	

	public static String[][] readSheet(String filePath, String sheetName) throws IOException{
		/*Step 1: Get the XL Path*/
		File xlFile = new File(filePath);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);


		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount =  sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();//Row count starts from '0' in excel

		System.out.println("Total row = " + iRowCount + " total col = " + iColCount);

		String[][] xlData = new String[iRowCount][iColCount];

		for(int i =0; i<iRowCount;i++){
			for(int j = 0; j <iColCount;j++){
				//xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue().trim();
				Cell cell = sheet.getRow(i).getCell(j);
				switch(cell.getCellType()){
				case Cell.CELL_TYPE_STRING:
					xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue().trim();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					xlData[i][j] = new Double(sheet.getRow(i).getCell(j).getNumericCellValue()).toString() ;
					break;
				}
			}

		}
		wb.close();
		return xlData;
	}

}