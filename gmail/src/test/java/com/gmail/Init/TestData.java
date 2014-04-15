package com.gmail.Init;

import org.testng.annotations.DataProvider;

/**
 * Define Seller Test Data
 *
 */



public class TestData
{
	@DataProvider(name="logdata")
	public static Object[][] Logindata()
	{
		Object obj[][]={{"login1","pass"}};

		return obj;
	}
}