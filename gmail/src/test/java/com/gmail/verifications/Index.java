package com.gmail.verifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.Init.Common;
import com.gmail.Init.SeleniumInit;
import com.gmail.pages.DashboardPage;

public class Index extends SeleniumInit 
{
	
	
	/*helllooo*/
	Common common = new Common(driver);

	/*****hello again*****/
	@Test
	public void Gmail_login() //facebook login
	{
        /* log here ..*/   
		common.log("<br></br>Step 1:open Google.com");
		common.log("<br></br>Step 2:clcik on Gmail link '");
		common.log("<br></br>Step 3:Enter the valid detail into form field" +
				"   <br></br> Email: krazzyvir@gmail.com" +
				"   <br></br> Password : 8518krazzyvir1933");
		common.log("<br></br> Step 4 : click on compose Button");
		common.log("<br></br> Step 5 : Enter The Detail for sending mail" +
				"   <br>TO : viral103patel@gmail.com " +
				"   </br>subject: automation " +
				"   </br>Body :  this mail is send by automation script");
		common.log("<br></br>Step 6: Click on send Button");
		
		 DashboardPage db = indexpage.login();
		/*comment removed*/
		if (db.verify_gmail_send())
		{
			Assert.assertTrue(true);
			

		} else {
			Assert.assertTrue(false);
			log("<br></br>FAIL");

		}
	}
	
	
	
	
	@Test
	public void Gmail_login_1() //facebook login
	{
        /* log here ..*/   
		common.log("<br></br>Step 1:open Google.com");
		common.log("<br></br>Step 2:clcik on Gmail link '");
		common.log("<br></br>Step 3:Enter the valid detail into form field" +
				"   <br></br> Email: krazzyvir@gmail.com" +
				"   <br></br> Password : 8518krazzyvir1933");
		common.log("<br></br> Step 4 : click on compose Button");
		common.log("<br></br> Step 5 : Enter The Detail for sending mail" +
				"   <br>TO : viral103patel@gmail.com " +
				"   </br>subject: automation " +
				"   </br>Body :  this mail is send by automation script");
		common.log("<br></br>Step 6: Click on send Button");
		
		 DashboardPage db = indexpage.login();
		/*comment removed*/
		if (db.verify_gmail_send())
		{
			Assert.assertTrue(true);
			

		} else {
			Assert.assertTrue(false);
			log("<br></br>FAIL");

		}
	}
	
	
	
		
		
	}
	
	
	
			
		
	
	

