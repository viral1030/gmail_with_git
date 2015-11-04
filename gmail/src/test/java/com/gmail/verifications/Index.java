package com.gmail.verifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.Init.Common;
import com.gmail.Init.CustomLogger;
import com.gmail.Init.SeleniumInit;

public class Index extends SeleniumInit {

	CustomLogger cl = new CustomLogger();

	@Test(groups = { "functest" })
	public void passedMethod() {

		CustomLogger.kiwiLog("Sending mail Step 1 : ");
		CustomLogger.kiwiLog("Sending mail Step 2 : ");
		CustomLogger.kiwiLog("Sending mail Step 3 : ");
		CustomLogger.kiwiLog("Sending mail Step 4 : ");
		CustomLogger.kiwiLog("Sending mail Step 5 : ");
		CustomLogger.kiwiLog("Sending mail Step 6 : ");
		CustomLogger.kiwiLog("Sending mail Step 7 : ");
		CustomLogger.kiwiLog("Sending mail Step 8 : ");
		CustomLogger.kiwiLog("Sending mail Step 9 : ");
		CustomLogger.kiwiLog("Sending mail Step 10 : ");
		CustomLogger.kiwiLog("Sending mail Step 11 : ");
		CustomLogger.kiwiLog("Sending mail Step 12: ");
		CustomLogger.kiwiLog("Sending mail Step 13 : ");

		Common.pause(5);

	}

	@Test(groups = { "functest123" })
	public void failedMethod() {

		CustomLogger.kiwiLog("Cancle Mail Step 1 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 2 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 3 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 4 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 5 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 6 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 7 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 8 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 9 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 10 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 11 : ");
		CustomLogger.kiwiLog("Cancle Mail Step 12: ");
		CustomLogger.kiwiLog("Cancle Mail Step 13 : ");

		Assert.assertTrue(false);

	}

	@Test(groups = { "functest" })
	public void failedMethod1() {

		CustomLogger.kiwiLog("Resend Mail Step 1 : ");
		CustomLogger.kiwiLog("Resend Mail Step 2 : ");
		CustomLogger.kiwiLog("Resend Mail Step 3 : ");
		CustomLogger.kiwiLog("Resend Mail Step 4 : ");
		CustomLogger.kiwiLog("Resend Mail Step 5 : ");
		CustomLogger.kiwiLog("Resend Mail Step 6 : ");
		CustomLogger.kiwiLog("Resend Mail Step 7 : ");
		CustomLogger.kiwiLog("Resend Mail Step 8 : ");
		CustomLogger.kiwiLog("Resend Mail Step 9 : ");
		CustomLogger.kiwiLog("Resend Mail Step 10 : ");
		CustomLogger.kiwiLog("Resend Mail Step 11 : ");
		CustomLogger.kiwiLog("Resend Mail Step 12: ");
		CustomLogger.kiwiLog("Resend Mail Step 13 : ");
		Assert.assertTrue(false);
	}

}
