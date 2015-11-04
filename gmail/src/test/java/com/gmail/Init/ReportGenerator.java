package com.gmail.Init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class ReportGenerator implements IReporter {

	ArrayList<String> suiteName = new ArrayList<String>();
	ArrayList<String> testName = new ArrayList<String>();
	ArrayList<String> passedMethod = new ArrayList<String>();
	ArrayList<String> failedMethod = new ArrayList<String>();

	Map<String, Long> totalTestTime = new HashMap<String, Long>();
	Map<String, Date> testStartTime = new HashMap<String, Date>();
	Map<String, Date> testEndTime = new HashMap<String, Date>();

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {

		for (ISuite iSuite : arg1) {

			Map<String, ISuiteResult> results = iSuite.getResults();

			Set<String> keys = results.keySet();

			for (String key : keys) {

				ITestContext context = results.get(key).getTestContext();

				String groupName[] = context.getAllTestMethods()[0].getGroups();

				System.out.println("Group Name " + groupName[0].toString());

				
				System.out.println("Group Name " +context);
				
				
				
				
				testName.add(context.getName());

				suiteName.add(context.getSuite().getName());

				long diff = context.getEndDate().getTime()

				- context.getStartDate().getTime();

				long diffSeconds = diff / 1000 % 60;

				testStartTime.put(context.getName(), context.getStartDate());

				testEndTime.put(context.getName(), context.getStartDate());

				totalTestTime.put(context.getName(), diffSeconds);

				IResultMap resultMap = context.getFailedTests();

				IResultMap passedresultMap = context.getPassedTests();

				Collection<ITestNGMethod> passedMethods = passedresultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : passedMethods) {
					passedMethod.add(iTestNGMethod.getMethodName());

				}

				Collection<ITestNGMethod> failedMethods = resultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : failedMethods) {

					failedMethod.add(iTestNGMethod.getMethodName());

				}

				/*
				 * Collection<String> log = CustomLogger.myMultimap.get(context
				 * .getName());
				 * 
				 * System.err.println("Context name  " + context.getName());
				 * 
				 * for (int i = 0; i < log.toArray().length; i++) {
				 * 
				 * System.out.println("log " + log.toArray()[i]);
				 * 
				 * }
				 */
			}

			/*
			 * System.out.println("Multimap size : " +
			 * CustomLogger.myMultimap.size());
			 */

		}

		System.out
				.println("============================TEST RUN RESULT============================");

		System.out.println("TOTAl PASSED METHOD " + passedMethod.size());

		for (String a : passedMethod) {

			System.out.println("\n Passed Method : " + a);

		}

		System.out.println("TOTAl FAILED METHOD " + failedMethod.size());

		for (String a : failedMethod) {
			System.out.println("\n Failed  Method : " + a + "  Size  "
					+ failedMethod.size());
		}

		System.out
				.println("============================REPORTER OUTPUT============================");

		for (String a : testName) {

			System.out.println("\n TEST NAME  :" + a);

			System.out.println("\n Total time taken :" + totalTestTime.get(a));

			System.out.println("Test Start Time : " + testStartTime.get(a));

			System.out.println("Test End Time : " + testEndTime.get(a));

			Collection<String> log = CustomLogger.myMultimap.get(a);

			for (int i = 0; i < log.toArray().length; i++) {

				System.out.println("log " + log.toArray()[i]);

			}

		}

	}
}
