package com.gmail.Init;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class CustomLogger implements ITestListener {
	static ArrayList<String> list = new ArrayList<String>();;

	static Multimap<String, String> myMultimap = ArrayListMultimap.create();

	public void onFinish(ITestContext arg0) {

		for (String a : list) {

			System.err.println("Method Name : " + arg0.getName() + "Step : "
					+ a);

			myMultimap.put(arg0.getName(), a);

		}

		list.clear();
	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult arg0) {

	}

	public void onTestSkipped(ITestResult arg0) {

	}

	public void onTestStart(ITestResult arg0) {

	}

	public void onTestSuccess(ITestResult arg0) {

	}

	public static void kiwiLog(String log) {

		list.add(log);

	}
}
