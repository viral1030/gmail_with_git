package com.gmail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends AbstractPage {

	
	@FindBy (xpath ="html/body/div[5]/div[2]/div/div[1]/div[4]/div[1]/div[2]/div[3]/div/div/div[2]")
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
