package com.qa.purchaseorder.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.aerogear.security.otp.Totp;

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

		String otpKeyStr = "UKMKVCOZOELMD5XZO3V72ZHW2CHPECDU"; // <- this 2FA secret key.

		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now(); // <- got 2FA coed at this time!

		System.out.println(twoFactorCode);
		

		return twoFactorCode.trim();

	}

}
