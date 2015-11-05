package com.gmail.verifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.gmail.Init.Common;
import com.gmail.Init.CustomLogger;
import com.gmail.Init.SeleniumInit;

public class Index extends SeleniumInit {

	CustomLogger cl = new CustomLogger();

	@Test(groups = { "login" })
	public void loginMethod() {

		CustomLogger.kiwiLog("login Step 1 : ");
		CustomLogger.kiwiLog("login Step 2 : ");
		CustomLogger.kiwiLog("login Step 3 : ");
		CustomLogger.kiwiLog("login Step 4 : ");
		CustomLogger.kiwiLog("login Step 5 : ");
		CustomLogger.kiwiLog("login Step 6 : ");
		CustomLogger.kiwiLog("login Step 7 : ");
		CustomLogger.kiwiLog("login Step 8 : ");
		CustomLogger.kiwiLog("login Step 9 : ");
		CustomLogger.kiwiLog("login Step 10 : ");
		CustomLogger.kiwiLog("login Step 11 : ");
		CustomLogger.kiwiLog("login Step 12: ");
		CustomLogger.kiwiLog("login Step 13 : ");

		Common.pause(5);

	}

	@Test(groups = { "signup" })
	public void signupMethod() {

		CustomLogger.kiwiLog("signup Step 1 : ");
		CustomLogger.kiwiLog("signup Step 2 : ");
		CustomLogger.kiwiLog("signup Step 3 : ");
		CustomLogger.kiwiLog("signup Step 4 : ");
		CustomLogger.kiwiLog("signup Step 5 : ");
		CustomLogger.kiwiLog("signup Step 6 : ");
		CustomLogger.kiwiLog("signup Step 7 : ");
		CustomLogger.kiwiLog("signup Step 8 : ");
		CustomLogger.kiwiLog("signup Step 9 : ");
		CustomLogger.kiwiLog("signup Step 10 : ");
		CustomLogger.kiwiLog("signup Step 11 : ");
		CustomLogger.kiwiLog("signup Step 12: ");
		CustomLogger.kiwiLog("signup Step 13 : ");

		Assert.assertTrue(false);

	}

	@Test(groups = { "login" })
	public void forgotPasswordMethod() {

		CustomLogger.kiwiLog("forgot password Step 1 : ");
		CustomLogger.kiwiLog("forgot password Step 2 : ");
		CustomLogger.kiwiLog("forgot password Step 3 : ");
		CustomLogger.kiwiLog("forgot password Step 4 : ");
		CustomLogger.kiwiLog("forgot password Step 5 : ");
		CustomLogger.kiwiLog("forgot password Step 6 : ");
		CustomLogger.kiwiLog("forgot password Step 7 : ");
		CustomLogger.kiwiLog("forgot password Step 8 : ");
		CustomLogger.kiwiLog("forgot password Step 9 : ");
		CustomLogger.kiwiLog("forgot password Step 10 : ");
		CustomLogger.kiwiLog("forgot password Step 11 : ");
		CustomLogger.kiwiLog("forgot password Step 12: ");
		CustomLogger.kiwiLog("forgot password Step 13 : ");
	/*	Assert.assertTrue(false);*/
	}

}
