package com.gmail.page.Indexpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.gmail.Init.Common;
import com.gmail.pages.AbstractPage;
import com.gmail.pages.DashboardPage;

public class IndexPage extends AbstractPage {

	public IndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

      Common common=new Common(driver);
	
	@FindBy(xpath=".//*[@id='gb']/div[1]/div[1]/div[1]/div[2]/a")
	private WebElement gmail;
	
	@FindBy(xpath=".//*[@id='Email']")
	private WebElement enter_email;
	
	@FindBy(xpath=".//*[@id='Passwd']")
	private WebElement enter_pass;
	
	@FindBy(xpath=".//*[@id='signIn']")
	private WebElement click_submit;
	
	@FindBy(xpath=".//div[@class='z0']/div[contains(.,'COMPOSE')]")
	private WebElement click_compose;
	
	@FindBy(xpath=".//*[@name='to']")
	private WebElement mail_sendTo;
	
	@FindBy(xpath=".//*[@name='subjectbox']")
	private WebElement mail_subject;
	
	@FindBy(xpath=".//*[@class='Am Al editable']/iframe")
	private WebElement mail_body;
	
	@FindBy(xpath="//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")
	private WebElement mail_send;
	

	
	
	/**/
	public DashboardPage login() {

		gmail.click();
		common.pause(3);
		
		enter_email.sendKeys("krazzyvir@gmail.com");
		common.pause(3);
		
		enter_pass.sendKeys("8518krazzyvir1933");
		common.pause(3);
		
		click_submit.click();
		common.pause(20);
		click_compose.click();
		
		common.pause(20);
		common.waitForElement(".//*[@name='to']");
		mail_sendTo.sendKeys("viral103patel@gmail.com");
		
		common.pause(3);
	    
		mail_subject.sendKeys("automation");
		
		common.pause(4);
		mail_body.sendKeys("this mail is send by automation script");
	 common.pause(5);
	 
	 mail_send.click();
	 common.pause(4);
		return new DashboardPage(driver);

	}
	
	
	
	

}
