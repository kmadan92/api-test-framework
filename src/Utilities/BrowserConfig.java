package Utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.HashMap;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

//import com.Compass.WebUI.MobileEngine;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;

public class BrowserConfig extends Global_Utilities {

	@SuppressWarnings("deprecation")
	public static WebDriver getBrowser(String BrowserType) throws MalformedURLException {
		WebDriver BrowserDriver = null;

		System.out.println("Browser selected: " + BrowserType);
		System.out.println("Infrastructure selected: " + System.getProperty("Infrastructure"));

		if (System.getProperty("Infrastructure").equalsIgnoreCase("VM")) {
			System.out.println("--Starting tests on VM--");

			if (System.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				
				WebDriverManager.chromedriver().setup();

				if (BrowserType.equalsIgnoreCase("Chrome Incognito Mode")) {
					/*System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\Browser\\Chrome\\chromedriver.exe");*/

					DesiredCapabilities chromecapabilities = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();

					options.addArguments("--incognito");
					options.addArguments("start-maximized");

					chromecapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					chromecapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
							UnexpectedAlertBehaviour.ACCEPT);

					BrowserDriver = new ChromeDriver(chromecapabilities);
				}

				else if (BrowserType.equalsIgnoreCase("Chrome With Download Preference")) {
					String downloadFilepath = System.getProperty("user.dir") + "\\Comparison\\" + FolderLocation.get();

					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);

					/*System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\Browser\\Chrome\\chromedriver.exe");*/

					ChromeOptions options1 = new ChromeOptions();
					options1.setExperimentalOption("prefs", chromePrefs);
					options1.addArguments("--incognito");
					options1.addArguments("start-maximized");

					DesiredCapabilities cap = DesiredCapabilities.chrome();
					cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options1);

					BrowserDriver = new ChromeDriver(cap);
				}

				else if (BrowserType.equalsIgnoreCase("Chrome Normal Mode")) {
					/*System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + "\\Browser\\Chrome\\chromedriver.exe");*/

					ChromeOptions options2 = new ChromeOptions();
					options2.addArguments("start-maximized");

					DesiredCapabilities chromecapabilities2 = DesiredCapabilities.chrome();

					chromecapabilities2.setCapability(ChromeOptions.CAPABILITY, options2);
					chromecapabilities2.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
							UnexpectedAlertBehaviour.ACCEPT);

					BrowserDriver = new ChromeDriver(chromecapabilities2);
				}
				else {
					throw new InvalidParameterException("You must choose one of the defined Browser Types");
				}
			}

			else if (BrowserType.equalsIgnoreCase("Firefox")) {
				// updated to work with selenium 3.0.1 jars (these are triggered through Gecko
				// driver, post setting the capabilities)
				// System.setProperty("webdriver.firefox.marionette","C:\\NewFramework_ISD-SIT_Project\\Browser\\GeckoDriver\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver",
						"C:\\NewFramework_ISD-SIT_Project\\Browser\\GeckoDriver\\geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
//			
				// FirefoxOptions options = new FirefoxOptions();
				// options.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				// //Location where Firefox is installed

				// DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				// capabilities.setCapability("moz:firefoxOptions", options);

//			//FirefoxProfile f1 = new FirefoxProfile();
//	        //boolean acceptUntrustedSs = false;
//	        //f1.setAssumeUntrustedCertificateIssuer(acceptUntrustedSs);
//			//return new FirefoxDriver(f1);
				// return new FirefoxDriver();
				BrowserDriver = new FirefoxDriver(capabilities);
//			FirefoxOptions firefoxOptions = new FirefoxOptions();
//			firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
//			firefoxOptions.setCapability("marionette", true);
//			return new FirefoxDriver(firefoxOptions); 
//			
				System.out.println("Browser type not enabled in test framework");
			}

			else if (BrowserType.equalsIgnoreCase("Android Browser")) {
//			DesiredCapabilities mobilecapabilities = new DesiredCapabilities();
//			mobilecapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
//			mobilecapabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
//			mobilecapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus5T");
//			mobilecapabilities.setCapability(MobileCapabilityType.VERSION, MobileEngine.version);
//			mobilecapabilities.setCapability(MobileCapabilityType.UDID, MobileEngine.deviceid);
//			mobilecapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//			
//		 BrowserDriver = new AndroidDriver(new URL("http://127.0.0.1:"+MobileEngine.port+"/wd/hub"), mobilecapabilities);
//		 return driver;
				
				System.out.println("Browser type not enabled in test framework");
			}

			else {
				throw new InvalidParameterException("You must choose one of the defined Browser");
			}

		}

		if (System.getProperty("Infrastructure").equalsIgnoreCase("Docker")) {
			System.out.println("--Starting tests in docker container--");

			if (System.getProperty("Browser").equalsIgnoreCase("Chrome")) {
				DesiredCapabilities chromecapabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();

				if (BrowserType.equalsIgnoreCase("Chrome Incognito Mode")) {
					options.addArguments("--incognito");
					options.addArguments("start-maximized");
					chromecapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
					chromecapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					chromecapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
							UnexpectedAlertBehaviour.ACCEPT);
				} else if (BrowserType.equalsIgnoreCase("Chrome With Download Preference")) {
					String downloadFilepath = System.getProperty("user.dir") + "\\Comparison\\" + FolderLocation.get();
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", downloadFilepath);
					options.setExperimentalOption("prefs", chromePrefs);
					options.addArguments("--incognito");
					options.addArguments("start-maximized");
					chromecapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					chromecapabilities.setCapability(ChromeOptions.CAPABILITY, options);
				} else if (BrowserType.equalsIgnoreCase("Chrome Normal Mode")) {
					options.addArguments("start-maximized");
					chromecapabilities.setCapability(ChromeOptions.CAPABILITY, options);
					chromecapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
							UnexpectedAlertBehaviour.ACCEPT);
				}
				else {
					throw new InvalidParameterException("You must choose one of the defined Browser Types");
				}

				BrowserDriver = new RemoteWebDriver(new URL("http://10.112.187.230:4444/wd/hub"), chromecapabilities);
				
			}
			else {
				throw new InvalidParameterException("You must choose one of the defined Browser");
			}

		}

		return BrowserDriver;
	}

}
