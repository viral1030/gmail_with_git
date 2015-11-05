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
	ArrayList<String> skippedMethod = new ArrayList<String>();

	Map<String, Long> totalTestTime = new HashMap<String, Long>();
	Map<String, Date> testStartTime = new HashMap<String, Date>();
	Map<String, Date> testEndTime = new HashMap<String, Date>();
	Map<String, String> testWithGroup = new HashMap<String, String>();

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {

		for (ISuite iSuite : arg1) {

			Map<String, ISuiteResult> results = iSuite.getResults();

			Set<String> keys = results.keySet();
			/*
			 * System.out.println("Suite" + iSuite.getName());
			 */
			suiteName.add(iSuite.getName());

			for (String key : keys) {

				ITestContext context = results.get(key).getTestContext();

				String groupName[] = context.getAllTestMethods()[0].getGroups();

				// ----------------------------------Group
				// Name-----------------------------------------------------

				/* System.out.println("Group Name " + groupName[0].toString()); */

				/* System.out.println("Group Name " +context); */

				testWithGroup.put(context.getName(), groupName[0].toString());

				// ---------------------------------- Total Test Configuration
				// ---------------------------------------------

				testName.add(context.getName());

				// -------------------------------------Suite
				// Details------------------------------------------

				/* suiteName.add(context.getSuite().getName()); */

				// ---------------------------------------Test execution
				// Time----------------------------------------------------

				long diff = context.getEndDate().getTime()

				- context.getStartDate().getTime();

				long diffSeconds = diff / 1000 % 60;

				testStartTime.put(context.getName(), context.getStartDate());

				testEndTime.put(context.getName(), context.getStartDate());

				totalTestTime.put(context.getName(), diffSeconds);

				// -----------------------------Total Passed
				// Method---------------------------------------------

				IResultMap resultMap = context.getFailedTests();

				IResultMap passedresultMap = context.getPassedTests();

				Collection<ITestNGMethod> passedMethods = passedresultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : passedMethods) {
					passedMethod.add(iTestNGMethod.getMethodName());

				}

				// ------------------------------Total Failed
				// Method-------------------------------------------------
				Collection<ITestNGMethod> failedMethods = resultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : failedMethods) {

					failedMethod.add(iTestNGMethod.getMethodName());

				}

				// -------------------------------Total Skipped
				// Method------------------------------------------------
				IResultMap skippedResultMap = context.getSkippedTests();

				Collection<ITestNGMethod> skippedmethod = skippedResultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : skippedmethod) {

					skippedMethod.add(iTestNGMethod.getMethodName());

				}

				// -------------------------------------------------------------------------------
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

		// -------------------------------------------------------------------------------

		System.out
				.println("============================Over All Summary============================");

		System.out.println("TOTAL   : "
				+ (passedMethod.size() + failedMethod.size() + skippedMethod
						.size()));

		System.out.println("PASSED  : " + passedMethod.size());

		System.out.println("FAILED  : " + failedMethod.size());

		System.out.println("SKIPPED : " + skippedMethod.size());

		System.out
				.println("\n============================Passed Methods============================");

		for (String a : passedMethod) {

			System.out.println("\n Passed Method : " + a);

		}

		System.out
				.println("\n============================Failed Methods============================");

		for (String a : failedMethod) {
			System.out.println("\n Failed  Method : " + a);
		}

		System.out
				.println("\n============================Skipped Methods============================");

		for (String a : skippedMethod) {
			System.out.println("\n Skipped  Method : " + a);
		}

		System.out
				.println("\n============================Suite Summary============================");

		for (String a : suiteName) {

			/* System.out.println("Total Test :" + testName.size()); */

			System.out.println("Suite Name " + a);

		}

		System.out
				.println("\n============================Group Details============================");

		for (String a : testName) {

			/* System.out.println("Total Test :" + testName.size()); */

			System.out.println("Group Name " + testWithGroup.get(a)
					+ " Method Name " + a);

		}

		System.out
				.println("============================REPORTER OUTPUT============================");

		for (String a : testName) {

			System.out.println("\n TEST NAME  :" + a);

			System.out.println("\n Total time taken :" + totalTestTime.get(a));

			System.out.println("Test Start Time : " + testStartTime.get(a));

			System.out.println("Test End Time : " + testEndTime.get(a));

			Collection<String> log = CustomLogger.myMultimap.get(a);

			for (int i = 0; i < log.size(); i++) {

				System.out.println("log " + log.toArray()[i]);

			}

		}

	}
}
