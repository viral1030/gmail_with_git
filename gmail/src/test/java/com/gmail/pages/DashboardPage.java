package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage {

	
	@FindBy (xpath ="//div[@class='vh']")
	private WebElement sent_gmail;
	
	@FindBy (xpath =".//*[@id=':39']/div/div[1]/span/a")
	private WebElement google;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
	public boolean verify_gmail_send()
	{
		
		if(sent_gmail.isDisplayed())
			     return true;
			else
				 return false;
	}
	
	
	public boolean verify_google()
	{
		
		if(google.isDisplayed())
			     return true;
			else
				 return false;
	}
	
	public boolean verifyfacebook_post()
	{
		return true;
	}
	
	
}
