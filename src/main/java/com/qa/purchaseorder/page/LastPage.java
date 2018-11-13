package com.qa.purchaseorder.page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.purchaseorder.base.POTestBase;

public class LastPage extends POTestBase {
	
	WebDriverWait wait;

	public LastPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}

	By thankYouText=By.xpath("//h1[@class='text-center']");
	By errorText=By.xpath("//[text(),'Error']");

	public int isDisplayedText() {
		wait= new WebDriverWait(driver, 15);
		return driver.findElements(thankYouText).size();
		
	}
	
	public void navigateBack() {
		
		driver.navigate().back();
		
	}

}
