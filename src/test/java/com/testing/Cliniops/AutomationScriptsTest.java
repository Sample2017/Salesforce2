package com.testing.Cliniops;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
  
  
public class AutomationScriptsTest extends ReusableMethodsTest{
	static WebDriver dr;

	public void selectBrowser(String browser)throws IOException{
		if(browser.equalsIgnoreCase("firefox")){
			dr=new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("chrome")){
			dr=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("IE")){
			dr=new InternetExplorerDriver();
		}
		dr.manage().window().maximize();
	}

		public static void salesforceForgetPasswordLink() throws IOException, InterruptedException{
			 dr.get("https://developer.salesforce.com/lightning");
			dr.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			dr.manage().window().maximize();
			WebElement login=dr.findElement(By.xpath(".//*[@id='login-button']"));
			ReusableMethodsTest.clickElement(login, "Login");
			dr.manage().timeouts().implicitlyWait(90,TimeUnit.SECONDS);
			WebElement userName=dr.findElement(By.id("username"));
			ReusableMethodsTest.enterText(userName, "nuthisiri", "user name");
			WebElement forgetPasswordLink=dr.findElement(By.xpath(".//*[@id='forgot_password_link']"));
			ReusableMethodsTest.clickElement(forgetPasswordLink, "forgetPasswordLink");
			Thread.sleep(4000);
		    System.out.println("We are in :"+dr.getTitle());
			}
	public void closeBrowser(){
			dr.quit();
	}


}




