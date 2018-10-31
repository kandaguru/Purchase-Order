package com.qa.purchaseorder.page;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.purchaseorder.base.POTestBase;
import com.qa.purchaseorder.util.TestUtil;

public class LoginPage extends POTestBase {

	public LoginPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id = "username")
	@CacheLookup
	WebElement userName;

	@FindBy(id = "password")
	@CacheLookup
	WebElement password;

	@FindBy(id = "_eventId_proceed")
	WebElement signIn;

	@FindBy(id = "token")
	@CacheLookup
	WebElement enterTokenTxtBox;

	public void login() throws IOException {

		userName.clear();
		userName.sendKeys(prop.getProperty("username").trim());

		password.clear();
		password.sendKeys(prop.getProperty("password").trim());

		signIn.click();

	}

	public HomePage enter2FA() throws IOException {

		String token = TestUtil.getTwoFA();
		enterTokenTxtBox.sendKeys(token);
		signIn.click();

		return new HomePage();
	}

}
