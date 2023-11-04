package Flows;

import java.net.MalformedURLException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import Utilities.Global_Utilities;

public class CMSUI_CreateObject extends Global_Utilities {
	
	@BeforeSuite
	public void InitializeWebUISuite() throws MalformedURLException, InterruptedException
	{
		System.out.println("--Initializing Test Suite--");
		thisClass.set(new Object(){}.getClass());
		
		
		
		
		
		
		
	}
	
	@AfterSuite
	public void EndWebUISuite() throws MalformedURLException, InterruptedException
	{
		thisClass.set(new Object(){}.getClass());
		
		System.out.println("--Ending Test Suite--");
		thisClass.set(new Object(){}.getClass());
		
		setThreadDataSheetName("CMSUIEntity.xlsx");
		
	}
	
	
	@BeforeMethod
	public void InitializeCMSUI_CreateObjectMethod() throws MalformedURLException
	{
		
		
		className = this.getClass().getName();
	}
	
	@AfterMethod
	public void EndCMSUI_CreateObjectMethod(ITestResult result)
	{
		
		if(!result.isSuccess())
		{
			driver.get().quit();
		}
	}
	
	
	@Test
	public void CreateTaskTopic() throws InterruptedException
	{
		thisClass.set(new Object(){}.getClass());
		setThreadDataSheetName("CMSUIEntity.xlsx");
		URL.set(System.getProperty("CMSURL")); 
		
		
	}
	
	

}
