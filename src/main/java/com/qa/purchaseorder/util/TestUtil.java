package com.qa.purchaseorder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.qa.purchaseorder.base.POTestBase;
import com.qa.purchaseorder.resources.Xls_Reader;

public class TestUtil extends POTestBase {

	public TestUtil() throws IOException {
		super();

	}

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT_TIMEOUT = 20;
	public static Xls_Reader reader;

	public static String getTwoFA() {

		String otpKeyStr ="UKMKVCOZOELMD5XZO3V72ZHW2CHPECDU";

		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now(); // <- got 2FA coed at this time!

		System.out.println(twoFactorCode);
		

		return twoFactorCode.trim();

	}

	
	public static boolean isElementPresent(WebDriver driver, By by) {  
	    
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);  
	    try {  
	        driver.findElement(by);  
	        return true;  
	    } catch (NoSuchElementException e) {  
	        return false;  
	    } finally {  
	        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);  
	    }  
	}
	
	
	
}
