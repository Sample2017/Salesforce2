package com.testing.Cliniops;
  
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.tools.ant.taskdefs.UpToDate;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
      
public class DriverScriptTest {           

     
	@Test
	public static void Driver() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, ClassNotFoundException, InstantiationException{

		AutomationScriptsTest casObject = new AutomationScriptsTest();
		String testDataPath =".\\AutomationScripts_Execution.xls";
		String reportsPath=".\\test-output\\suite\\";
		String testDataSheet="Sheet1";
		String[][] recData = ReusableMethodsTest.readSheet(testDataPath, testDataSheet);
		String tc = null;
		//looping through the rows
		for(int i=0; i<recData.length; i++){

			if (recData[i][1].equalsIgnoreCase("y")){
				tc = recData[i][2];
				System.out.println("Testcase to run: "+recData[i][2]);

				//we are getting handle to the method for invoking...
				Method testcase = AutomationScriptsTest.class.getMethod(tc);
				
				//invoke---executes the method
				if(recData[i][3]!=null && recData[i][3].equalsIgnoreCase("Y"))
				{
								casObject.selectBrowser("firefox");
					testcase.invoke(casObject);
					casObject.closeBrowser();
				}
				if(recData[i][4]!=null && recData[i][4].equalsIgnoreCase("Y"))
				{
					
					casObject.selectBrowser("chrome");
					testcase.invoke(casObject);
					casObject.closeBrowser();
				}
				if(recData[i][5]!=null && recData[i][5].equalsIgnoreCase("Y"))
				{
				
					casObject.selectBrowser("IE");
					testcase.invoke(casObject);
					casObject.closeBrowser();
				}						
								
			}
			else if(recData[i][1].equalsIgnoreCase("n")){
				System.out.println(recData[i][2] + "in line number "+i+ "skipped from execution");
			}
		}	
		
	}
}

