package com.gmail.Init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

	ArrayList<String> SuiteName = new ArrayList<String>();
	ArrayList<String> TestName = new ArrayList<String>();

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {

		for (ISuite iSuite : arg1) {

			Map<String, ISuiteResult> results = iSuite.getResults();

			Set<String> keys = results.keySet();

			for (String key : keys) {

				ITestContext context = results.get(key).getTestContext();

				TestName.add(context.getName());

				SuiteName.add(context.getSuite().getName());

				System.out.println("\n Method Name->" + context.getName()

				+ "\n::Suite Name->" + context.getSuite().getName()

				+ "\n::Start Date Time for execution->"
						+ context.getStartDate()

						+ "\n::End Date Time for execution->"
						+ context.getEndDate());
				
				
				

				IResultMap resultMap = context.getFailedTests();

				Collection<ITestNGMethod> failedMethods = resultMap
						.getAllMethods();

				for (ITestNGMethod iTestNGMethod : failedMethods) {

					System.out.println("TESTCASE NAME->\n"
							+ iTestNGMethod.getMethodName());

					System.out.println("\nDescription->"
							+ iTestNGMethod.getDescription());

					System.out.println("\nPriority->"
							+ iTestNGMethod.getPriority());

					System.out.println("\n:Date->"
							+ new Date(iTestNGMethod.getDate()));

				}

				Collection<String> log = CustomLogger.myMultimap.get(context
						.getName());

				System.err.println(context.getName());

				for (int i = 0; i < log.toArray().length; i++) {

					System.err.println("log " + log.toArray()[i]);

				}

				System.err
						.println("------------------------------------------------------------------");

				System.err
						.println("*******************TEST END**********************************");

			}

			System.out.println("Multimap size : "
					+ CustomLogger.myMultimap.size());

			System.err
					.println("*******************SUITE END**********************************");

		}

	}
}
