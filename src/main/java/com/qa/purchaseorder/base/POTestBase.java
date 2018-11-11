package com.qa.purchaseorder.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.qa.purchaseorder.util.TestUtil;
import com.qa.purchaseorder.util.WebEventListener;

public class POTestBase {

	public static Logger log = LogManager.getLogger(POTestBase.class.getName());

	public static WebDriver driver = null;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebDriverEventListener e_listener;

	public POTestBase() throws IOException {

		String path = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				path + "\\src\\main\\java\\com\\qa\\purchaseorder\\properties\\config.properties");
		prop = new Properties();
		prop.load(fis);

	}

	public static void intialize() throws IOException {

		if (driver == null) {

			if (prop.getProperty("browser").trim().equalsIgnoreCase("chrome")) {

				String path = System.getProperty("user.dir");

				System.setProperty("webdriver.chrome.driver",
						path + "\\src\\main\\java\\com\\qa\\purchaseorder\\drivers\\chromedriver.exe");

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

				ChromeOptions options = new ChromeOptions();
				options.merge(capabilities);

				driver = new ChromeDriver();

			}

			else if (prop.getProperty("browser").equalsIgnoreCase("FF")) {

				String path = System.getProperty("user.dir");

				
				System.setProperty("webdriver.gecko.driver",
						path + "\\src\\main\\java\\com\\qa\\purchaseorder\\drivers\\geckodriver.exe");

				driver = new FirefoxDriver();

			}

			else if (prop.getProperty("browser").trim().equalsIgnoreCase("IE")) {

				log.error("********IE not supported*********");

			}

		}

//		e_driver = new EventFiringWebDriver(driver);
//		e_listener = new WebEventListener();
//		e_driver.register(e_listener);
//
//		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url").trim());

	}

}
