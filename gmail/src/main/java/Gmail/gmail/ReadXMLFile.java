package Gmail.gmail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ViGo_Reports.ViGo_Reports.PathFetcher;

public class ReadXMLFile {

	ArrayList<String> SuiteNames = new ArrayList<String>();

	public static void main(String[] args) throws NullPointerException {

		System.err.println("Read XML Class Execution Started:");
		ReadXMLFile rxf = new ReadXMLFile();

		try {

			System.out.println("=============> Start to Generate report.");
			String current = new java.io.File(".").getCanonicalPath();

			File file = new File(current + "/Kiwi-Reports");
			if (!file.exists()) {
				if (file.mkdir()) {
					System.err.println("Directory is created!");
				} else {
					System.err.println("Failed to create directory!");
				}

			}// report file writing

			File reportsFile = new File(current + "/Kiwi-Reports/Reports");
			if (!reportsFile.exists()) {
				if (reportsFile.mkdir()) {
					System.err.println("Report Directory is created!");
				} else {
					System.err.println("Failed to create Report directory!");
				}

			}

			String path = "";
			File reportFile = new File(current + "/Kiwi-Reports/Reports");
			File firstReport = new File(current + "/Kiwi-Reports/Reports"
					+ "/1");
			File srcTesOutput = new File(current + "/test-output");

			if (reportFile.isDirectory()) {
				String[] subNote = reportFile.list();

				if (subNote.length == 0) {
					firstReport.mkdir();
					FileUtils.copyDirectory(srcTesOutput, firstReport);
					path = firstReport.toString();
					System.err.println("First Folder Created.");
				} else {
					int newFolder = subNote.length + 1;
					path = current + "/Kiwi-Reports/Reports" + "/"
							+ (newFolder);
					File reportFile1 = new File(path);
					reportFile1.mkdirs();
					FileUtils.copyDirectory(srcTesOutput, reportFile1);

				}
			}

			System.err.println("Copy file is done");
			File folderNumber = new File(current + "/Kiwi-Reports/Reports");

			String[] subNote = folderNumber.list();

			File fXmlFile = new File(current + "/Kiwi-Reports/Reports/"
					+ subNote.length + "/testng-results.xml");

			System.out.println("=========> xml file found:" + "Build No:"
					+ subNote.length);
			/*
			 * File fXmlFile = new File("D:\\REPORTS\\testng-results.xml");
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			/*
			 * System.out.println("Root element :" +
			 * doc.getDocumentElement().getNodeName());
			 */

			rxf.createReportsFile(current);

			File reportfile = new File(current + "/Kiwi-Reports/index.html");

			if (!reportfile.exists()) {
				reportfile.createNewFile();
				System.err.println("=========> Index.html created.");
			}

			FileWriter fw = new FileWriter(reportfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			rxf.suiteDetails(doc);
			rxf.totalTestResult(doc, bw);

			bw.close();

			System.err.println("============>Report Generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void totalTestResult(Document doc, BufferedWriter bw) {
		NodeList totalSuiteResult = doc.getElementsByTagName("testng-results");

		Node reporternode = totalSuiteResult.item(0);

		try {

			bw.write("<!DOCTYPE html>\n" + "<html>\n");

			bw.write("<head> \n"
					+ "  <meta charset=UTF-8>\n"
					+ " <title>KIWIQA</title>\n"
					+ "<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>\n"
					+ " <link href='bootstrap.min.css' rel='stylesheet' type='text/css' />\n"
					+ "<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css' rel=stylesheet type=text/css />\n"
					+ " <link href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css' rel=stylesheet type=text/css />\n"
					+ " <link href='AdminLTE.min.css' rel=stylesheet type=text/css />\n"
					+ " <link href='_all-skins.min.css' rel=stylesheet type=text/css /> \n"
					+ " <link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css' rel=stylesheet type=text/css /> \n"

					+ " <link href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css' rel=stylesheet type=text/css />  \n"
					+ " </head>\n"

			);
			String current = new java.io.File(".").getCanonicalPath();

			bw.write(" <body class='skin-blue sidebar-mini'>\n"
					+ "<div class=wrapper>\n"
					+ "	<header class=main-header> "
					+ "	<a href=' "

					+ "index.html' class='logo'> <span class='logo-mini'><b></b></span>"
					+ "	<span class='logo-lg'><b>KIWIQA </b>REPORTS</span>"
					+ "		</a>  "
					+ "	<nav class='navbar navbar-static-top' > "

					+ "		<h4> "
					+ "	<a href='index.html' "
					+ " style='font-size:25px   ; color: white; margin-left: 70px ;'> Dashboard</a> "

					+ "<a href='"

					+ "data.html' style='font-size:25px   ; color: white; margin-left: 70px ;'> Suites Details</a>"

					+ "	</h4> " + "		</nav>" + "	</header>" + "");

			if (reporternode.getNodeType() == Node.ELEMENT_NODE) {

				Element reportElement = (Element) reporternode;

				bw.write("  <div class=content-wrapper>\n"
						+ "  <section class=content>\n"
						+ "    <div class=row>\n"
						+ "<div class='col-lg-3 col-xs-6'>\n"
						+ "   <div class='small-box bg-aqua'>\n"
						+ " <div class='inner'>\n" + "  <h3>\n"
						+ reportElement.getAttribute("total")
						+ "</h3>\n"
						+ " <p>TOTAL</p>\n"
						+ " </div>\n"
						+ " <div class='icon'> \n"
						+ "    \n"
						+ "    </div>\n"
						+ ""
						+ "</div>\n"
						+ "</div>\n"
						+ "    <div class='col-lg-3 col-xs-6'>\n"
						+ "   <div class='small-box bg-green'>\n"
						+ " <div class='inner'>\n"
						+ "  <h3>\n"
						+ reportElement.getAttribute("passed")
						+ "</h3>\n"
						+ " <p>PASSED</p>\n"
						+ " </div>\n"
						+ "  <div class='icon'>\n"
						+ "  \n"
						+ "  </div>\n"
						+ "</div>\n"
						+ "</div>\n"
						+ "    <div class='col-lg-3 col-xs-6'>\n"
						+ "  <div class='small-box bg-yellow'>\n"
						+ " <div class='inner'>\n"
						+ "       <h3>\n"
						+ reportElement.getAttribute("skipped")
						+ "</h3>\n"
						+ "      <p>SKIPPED</p> \n "
						+ "     </div>\n"
						+ "     <div class='icon'>\n"
						+ "         \n"
						+ "      </div>\n"

						+ "      </div>\n"
						+ " </div>\n"
						+ "<div class='col-lg-3 col-xs-6'>  "

						+ "       <div class='small-box bg-red'>"
						+ "         <div class='inner'>"
						+ "           <h3>"
						+ reportElement.getAttribute("failed")
						+ "</h3>"
						+ "             <p>FAILED</p>"
						+ "           </div>"
						+ "           <div class='icon'>"
						+ "             </i>"
						+ "            </div>\n"

						+ "           </div>\n"
						+ "          </div><!-- ./col -->\n"
						+ "          </div><!-- /.row -->\n"
						+ ""
						+ ""
						+ "				<div class=row>\n "

						+ "		<section class='content'>\n"
						+ "	<div class='row'>\n"
						+ "		 <div class='col-md-6'>\n"
						+ "		      <div class='box box-info'>\n"
						+ "			     <div class='box-header with-border'>"
						+ "		              <h3 class='box-title'>Test Summary Chart</h3>\n"
						+ "		                 <div class='box-tools pull-right'>\n"

						+ "	                     </div>\n"
						+ "	             </div>\n"
						+ "			    <div class='box-body' style='width: 500' >\n"
						+ "					<canvas id='pieChart' height=250 ></canvas>\n"
						+ "	            </div>\n"
						+ "	      </div>\n"

						+ "	</div>\n"

						+ "		 <div class='col-md-6'>\n"
						+ "		      <div class='box box-info'>\n"
						+ "			     <div class='box-header with-border'>"
						+ "		              <h3 class='box-title'>Suite Summary</h3>\n"
						+ "		                 <div class='box-tools pull-right'>\n"

						+ "	                     </div>\n"
						+ "	             </div>\n"
						+ "			     <div class='box-body' style='width: 500' >\n"
						+ "				"
						+ ""
						+ "<div class='box-body'> "
						+ "<table id='exampsle2' class='table table-bordered table-hover'> "
						+ "<thead> "
						+ "<tr>"
						+ "<th>Index</th>"
						+ "<th>Suite Name</th>"
						+ ""
						+ ""
						+ "</tr>"
						+ "</thead>" + "<tbody>");

				for (int i = 0; i < SuiteNames.size(); i++) {

					bw.write(

					"	<tr> " + "	<td>" + (i + 1) + "</td>" + "	<td>"
							+ SuiteNames.get(i) + "</td> " + "	</tr>	"

					);
				}

				bw.write("</tbody>"
						+ "	</table>"
						+ "	</div>"
						+ "</div>"
						+ "	             </div>\n"
						+ "	          </div>\n"

						+ "	      </div>\n"
						+ "	<div class='row'>\n"
						+ "		 <div style='margin: 15px'>\n"
						+ "		      <div class='box box-info'>\n"
						+ "			     <div class='box-header with-border'>"
						+ "		              <h3 class='box-title'>Build Summary</h3>\n"
						+ "		                 <div class='box-tools pull-right'>\n"

						+ "	                     </div>\n"
						+ "	             </div>\n"
						+ "			    <div class='box-body' style='width: 500' >\n"
						+ "					<canvas id='barChart' height=250 ></canvas>\n"
						+ "	            </div>\n"
						+ "	      </div>\n"

						+ "	</div>\n"
						+ "</div>\n"

						+ "				</div>\n"

						+ "  </section>\n"
						+ "			</div>\n"

						+ "	</div> \n"

						+ ""
						+ ""
						+ "<footer class='main-footer'>\n"
						+ "	<div class='pull-right hidden-xs'>\n"
						+ "		<b>Version</b> 2.0\n"
						+ "	</div>\n"
						+ "	<strong>Copyright &copy; 2014-2015 <a href=>KIWIQA</a>.\n"
						+ "	</strong> All rights reserved.\n"
						+ "	</footer>\n"
						+ ""
						+ ""
						+ ""
						+ "<script src='http://code.jquery.com/ui/1.11.2/jquery-ui.min.js'\n"
						+ "	type=text/javascript></script>\n"
						+ "<script>\n"
						+ "   $.widget.bridge('uibutton', $.ui.button);\n"
						+ " </script>\n"

						+ "	<script src='jQuery-2.1.4.min.js'></script>\n"

						+ "	<script src='bootstrap.min.js'\n"
						+ "		type=text/javascript></script>\n"

						+ "	<script src='Chart.min.js' type=text/javascript></script>"

						+ "	<script src='app.min.js' type=text/javascript></script>\n"

						+ "	<script src='demo.js' type=text/javascript></script>\n"
						+ ""

						+ ""

						+ ""

						+ ""
						+ "<script> \n"

						+ "      $(function () {\n"

						+ "  var pieChartCanvas = $('#pieChart').get(0).getContext('2d');\n"
						+ "       var pieChart = new Chart(pieChartCanvas);\n"
						+ "        var PieData = [\n" + "         {\n"
						+ "            value: "
						+ reportElement.getAttribute("failed")
						+ ",\n"
						+ "            color: '#f56954',\n"
						+ "            highlight: '#f56954',\n"
						+ "          label: 'FAILED'\n"
						+ "       },\n"
						+ "         {\n"
						+ "           value:"
						+ reportElement.getAttribute("passed")
						+ ",\n"
						+ "        color: '#00a65a',\n"
						+ "       highlight: '#00a65a',\n"
						+ "      label: 'PASSED'\n"
						+ "    },\n"
						+ "  {\n"
						+ "    value: "
						+ reportElement.getAttribute("skipped")
						+ ",\n"
						+ " color: '#f39c12',\n"
						+ "     highlight: '#f39c12',\n"
						+ "     label: 'SKIPPED'\n"
						+ "     },\n"

						+ "   ];\n"
						+ "     var pieOptions = {\n"
						// Boolean - Whether we should show a stroke on each
						// segment
						+ "    segmentShowStroke: true,\n"
						// String - The colour of each segment stroke
						+ "     segmentStrokeColor: '#fff',\n"
						// Number - The width of each segment stroke
						+ "          segmentStrokeWidth: 2,\n"
						// Number - The percentage of the chart that we cut out
						// of the middle
						+ "      percentageInnerCutout: 50,\n "
						// Number - Amount of animation steps
						+ "      animationSteps: 100,\n"
						// String - Animation easing effect
						+ "     animationEasing: 'easeOutBounce', \n"
						// Boolean - Whether we animate the rotation of the
						// Doughnut
						+ "      animateRotate: true,\n"
						// Boolean - Whether we animate scaling the Doughnut
						// from the centre
						+ "     animateScale: false,\n"
						// Boolean - whether to make the chart responsive to
						// window resizing
						+ "     responsive: true, \n"
						// Boolean - whether to maintain the starting aspect
						// ratio or not when responsive, if set to false, will
						// take up entire container
						+ "      maintainAspectRatio: false, \n"
						// String - A legend template
						+ "       legendTemplate: '<ul class=\"<%=name.toLowerCase()%>-legend\">"
						+ "<% for (var i=0; i<segments.length; i++){%>"
						+ "<li><span style=\"background-color:<%=segments[i].fillColor%>\">"
						+ "</span><%if(segments[i].label){%><%=segments[i].label%><%}%>"
						+ "</li><%}%></ul> ' \n" + "  }; "
						// Create pie or douhnut chart
						// You can switch between pie and douhnut using the
						// method below.
						+ "     pieChart.Doughnut(PieData, pieOptions); \n"

				);

				// --------

				String currentDir = System.getProperty("user.dir")
						+ "/Kiwi-Reports/Reports/";

				File folderNumber = new File(currentDir);

				List<Integer> folderName = new ArrayList<Integer>();

				String[] subNote = folderNumber.list();

				System.out.println(subNote.length);

				for (String filename : subNote) {

					if (filename.equalsIgnoreCase(".DS_Store")) {
					} else {
						int i = Integer.parseInt(filename);

						folderName.add(i);
					}
				}

				System.out.println(folderName);

				Collections.sort(folderName);

				System.out.println(folderName);

				bw.write("  	" + "		 var data = { "

				+ "   labels: [  ");

				try {

					if (folderName.size() <= 5) {

						for (int i = 0; i < folderName.size(); i++) {

							bw.write(" 'Build :");
							bw.write(folderName.get(i) + "'");
							if (i == folderName.size()) {

							} else {

								bw.write(",");
							}

						}

					} else {

						System.err.println(folderName.size() - 5);

						System.err.println(folderName.get(6));

						bw.write("' Build :"
								+ folderName.get(folderName.size() - 5)
								+ " ', ' Build :"
								+ folderName.get(folderName.size() - 4)
								+ " ', ' Build :"
								+ folderName.get(folderName.size() - 3)
								+ " ', ' Build :"
								+ folderName.get(folderName.size() - 2)
								+ " ', ' Build :"
								+ folderName.get(folderName.size() - 1) + "'");

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				bw.write(" ],"

				+ "   datasets: [" + "          {"
						+ "              label: 'PASSED', "
						+ "      fillColor: '#44ae44', "
						+ "      strokeColor: '#0d7a0d', "
						+ "      highlightFill: '#53ea53',"
						+ "     highlightStroke: '#0d7a0d',");

				List<String> passed = new ArrayList<String>();

				List<String> failed = new ArrayList<String>();

				List<String> skipped = new ArrayList<String>();

				for (int i = 0; i < folderName.size(); i++) {

					File fXmlFile = new File(currentDir + folderName.get(i)
							+ "/testng-results.xml");

					DocumentBuilderFactory dbFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc1 = dBuilder.parse(fXmlFile);

					doc1.getDocumentElement().normalize();

					NodeList suiteResult = doc1
							.getElementsByTagName("testng-results");

					Node reportNode = suiteResult.item(0);

					if (reportNode.getNodeType() == Node.ELEMENT_NODE) {

						Element reportEle = (Element) reportNode;

						passed.add(reportEle.getAttribute("passed"));

						failed.add(reportEle.getAttribute("failed"));

						skipped.add(reportEle.getAttribute("skipped"));
					}

				}

				bw.write("     data: [ ");

				try {

					if (passed.size() <= 5) {
						for (int i = 0; i < passed.size(); i++) {

							bw.write(passed.get(i));
							bw.write(",");

						}
					} else {

						bw.write(passed.get(passed.size() - 5) + ","
								+ passed.get(passed.size() - 4) + ","
								+ passed.get(passed.size() - 3) + ","
								+ passed.get(passed.size() - 2) + ","
								+ passed.get(passed.size() - 1));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				bw.write("]");

				bw.write("  }, " + "  { " + "   label:'FAILED', "
						+ "    fillColor: '#f56954',"
						+ "  strokeColor: '#d82b0c', "
						+ "   highlightFill: '#fb4221', "
						+ "  highlightStroke: '#d82b0c', ");

				bw.write("     data: [ ");

				try {

					if (failed.size() <= 5) {
						for (int i = 0; i < failed.size(); i++) {

							bw.write(failed.get(i));
							bw.write(",");

						}

					} else {

						bw.write(failed.get(failed.size() - 5) + ","
								+ failed.get(failed.size() - 4) + ","
								+ failed.get(failed.size() - 3) + ","
								+ failed.get(failed.size() - 2) + ","
								+ failed.get(failed.size() - 1));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				bw.write("]");

				bw.write("},"

				+ "    {   " + "  label: 'Skipped', "
						+ "  fillColor: '#f9b040', "
						+ "  strokeColor: '#ff8101', "
						+ "   highlightFill: '#f29505', "
						+ "    highlightStroke: '#ffa801', ");

				bw.write("     data: [ ");

				try {
					if (skipped.size() <= 5) {

						for (int i = 0; i < skipped.size(); i++) {

							String skipnumber = "" + skipped.get(i);
							if (skipped.get(i).equals(skipnumber)) {

							} else {
								bw.write(skipped.get(i));
								bw.write(",");

							}

						}
					} else {

						String skipnumber = "" + 0;

						if (!skipped.get(failed.size() - 5).equals(skipnumber)) {
							bw.write(skipped.get(failed.size() - 5) + ",");
						}
						if (!skipped.get(failed.size() - 4).equals(skipnumber)) {
							bw.write(skipped.get(failed.size() - 4) + ",");
						}
						if (!skipped.get(failed.size() - 3).equals(skipnumber)) {
							bw.write(skipped.get(failed.size() - 3) + ",");
						}
						if (!skipped.get(failed.size() - 2).equals(skipnumber)) {
							bw.write(skipped.get(failed.size() - 2) + ",");
						}
						if (!skipped.get(failed.size() - 1).equals(skipnumber)) {
							bw.write(skipped.get(failed.size() - 1));
						}

					}

				} catch (Exception e) {
					e.printStackTrace();

				}
			}

			bw.write("]");

			bw.write("  "
					+ "  } "
					+ "  ] "
					+ "  }; "

					+ "   var barChartOptions = { "
					// Boolean - Whether the scale should start at zero, or
					// an order of magnitude down from the lowest value
					+ "       scaleBeginAtZero: true, "
					// Boolean - Whether grid lines are shown across the
					// chart
					+ "    scaleShowGridLines: false, "
					// String - Colour of the grid lines
					+ "   scaleGridLineColor: 'rgba(0,0,0,.05)', "
					// Number - Width of the grid lines
					+ "   scaleGridLineWidth: 1 , "
					// Boolean - Whether to show horizontal lines (except X
					// axis)
					+ "  scaleShowHorizontalLines: true, "
					// Boolean - Whether to show vertical lines (except Y
					// axis)
					+ "  scaleShowVerticalLines: true,"
					// Boolean - If there is a stroke on each bar
					+ "  barShowStroke: true,"
					// Number - Pixel width of the bar stroke
					+ "  barStrokeWidth: 2,"
					// Number - Spacing between each of the X value sets
					+ "   barValueSpacing: 50,"
					// Number - Spacing between data sets within X values
					+ "  barDatasetSpacing: 10,"
					// String - A legend template
					+ "   legendTemplate: '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>', "
					// Boolean - whether to make the chart responsive
					+ "   responsive: true, "
					+ "  maintainAspectRatio: false "
					+ "   }; "

					+ "   var ctx = document.getElementById('barChart').getContext('2d'); "
					+ "   var myBarChart = new Chart(ctx).Bar(data, barChartOptions);"

					// ---------

					+ "  });\n" + " </script>\n"

					+ "</body> \n" + "</html>" + "" + "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void suiteDetails(Document doc) throws IOException {

		String current = new java.io.File(".").getCanonicalPath();

		File reportfile = new File(current + "/Kiwi-Reports/data.html");

		if (!reportfile.exists()) {
			reportfile.createNewFile();
		}

		FileWriter fw = new FileWriter(reportfile.getAbsoluteFile());
		BufferedWriter aw = new BufferedWriter(fw);

		aw.write("    <!DOCTYPE html>\n"
				+ "  <html>\n"
				+ "<head>\n"
				+ "<meta charset=UTF-8>\n"
				+ "<title>KIWIQA</title>\n"
				+ "<meta\n"
				+ "content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'\n"
				+ "	name='viewport'>\n"
				+ "<link href='bootstrap.min.css' rel='stylesheet'\n"
				+ "	type='text/css' />\n"
				+ "<link\nn"
				+ "	href=https:'//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'\n"
				+ "	rel=stylesheet type=text/css />\n"
				+ "<link\n"
				+ "	href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'\n"
				+ "	rel=stylesheet type=text/css />\n"
				+ "<link href='AdminLTE.min.css' rel=stylesheet type=text/css />\n"
				+ "<link href='_all-skins.min.css' rel=stylesheet\n"
				+ "	type=text/css />\n"
				+ "<link\n"
				+ "	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'\n"
				+ "	rel=stylesheet type=text/css />\n"
				+ "<link\n"
				+ "	href='https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css'\n"
				+ "	rel=stylesheet type=text/css />\n"
				+ "</head> \n   "
				+ ""
				+ ""
				+ "<body class='skin-blue sidebar-mini'  style='background-color: white;'>\n "
				+ "<div class='wrapper'> \n"

				+ "	<header class=main-header> "
				+ "	<a href='"

				+ "index.html' class='logo'> <span class='logo-mini'><b></b></span>"
				+ "	<span class='logo-lg'><b>KIWIQA </b>REPORTS</span>"
				+ "		</a>  "
				+ "	<nav class='navbar navbar-static-top' > "

				+ "		<h4> "
				+ "	<a href='"

				+ "index.html' style='font-size:25px   ; color: white; margin-left: 70px ;'> Dashboard</a> "

				+ "<a href='"
				+ "data.html' style='font-size:25px   ; color: white; margin-left: 70px ;'> Suites Details</a>"

				+ "	</h4> " + "		</nav>" + "	</header>"

				+ "" + ""

		);

		NodeList suite = doc.getElementsByTagName("suite");

		StringBuilder message = new StringBuilder();
		StringBuilder fullstacktrace = new StringBuilder();

		aw.write("</br></br>");

		for (int temp = 0; temp < suite.getLength(); temp++) {
			;

			Node suitedetails = suite.item(temp);

			Element suite1 = (Element) suitedetails;

			float Duration = Float.parseFloat(suite1
					.getAttribute("duration-ms")) / 1000;

			SuiteNames.add(suite1.getAttribute("name"));

			aw.write("	<div class='box box-info'> "
					+ "			<div class='box-header with-border'>"
					+ "			<h3 class='box-title'>"
					+ "			Suite Name :  "
					+ suite1.getAttribute("name")
					+ " "
					+ "</h3>"
					+ "				<div class='box-tools pull-right'>"
					+ "					<button class='btn btn-box-tool' data-widget='collapse'>"
					+ "						<i class='fa fa-minus'></i>" + "					</button>"

					+ "				</div>" + "			</div>"
					+ "			<div class='box-body' style='width: 500'>");

			aw.write(" <div class='content-wrapper'>");

			aw.write("" + "" + "" + "" + "<section class='content-header'>\n"

			+ "		<div class=row>\n" + "			<div class='col-lg-3 col-xs-6'>\n"
					+ "				<div class='small-box bg-aqua'>\n"
					+ "					<div class='inner'>\n"
					+ "<h3>DURATION</h3>\n						" + "<p>"
					+ Duration
					+ "</p>\n"

					+ "					</div>\n"
					+ "					<div class='icon'></div>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "			<div class='col-lg-3 col-xs-6'>\n"
					+ "				<div class='small-box bg-green'>\n"
					+ "					<div class='inner'>\n"

					+ "						<h3>STARTED-AT</h3>\n"
					+ "						<p>"
					+ suite1.getAttribute("started-at")
					+ "</p>\n"

					+ "					</div>\n"
					+ "					<div class='icon'></div>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "			<div class='col-lg-3 col-xs-6'>\n"
					+ "				<div class='small-box bg-yellow'>\n"
					+ "					<div class='inner'>\n"
					+ "						<h3>FINISHED-AT</h3>\n"

					+ "						<p>"
					+ suite1.getAttribute("finished-at")
					+ "</p>\n"

					+ "					</div>\n"
					+ "					<div class='icon'></div>\n"
					+ "				</div>\n"
					+ "			</div>\n"
					+ "			<div class='col-lg-3 col-xs-6'></div>\n"
					+ "		<!-- ./col --> \n" + "	</div>\n"

					+ "	</section>");

			aw.write("<section class='content'> \n" + "	<div class='row'> \n"
					+ "		<div class='col-xs-12'>\n");

			if (suite1.hasChildNodes()) {

				NodeList suiteChild = suite1.getChildNodes();

				for (int b = 0; b < suiteChild.getLength(); b++) {

					Node suiteNode = suiteChild.item(b);

					if (suiteNode.getNodeName().equals("groups")) {

						NodeList grouplist = suiteNode.getChildNodes();

						for (int k = 0; k < grouplist.getLength(); k++) {

							Node groupNode = grouplist.item(k);

							try {
								if (groupNode.hasChildNodes()) {

									Element grpElement = (Element) groupNode;

									aw.write("<div class='box collapsed-box'> ");

									aw.write("			<div class='box-header with-border'>"
											+ "			<h3 class='box-title'>"
											+ "		Groups Name : <b> <font color=#1bd01b>"
											+ grpElement.getAttribute("name")
											+ "</b></font> "
											+ "</h3>"
											+ "				<div class='box-tools pull-right'>"
											+ "					<button class='btn btn-box-tool' data-widget='collapse'>"
											+ "						<i class='fa fa-plus'></i>"
											+ "					</button>"

											+ "				</div>"
											+ "			</div>"
											+ "			<div class='box-body' style='width: 500'>"

									);

									aw.write("" + "		\n");

									NodeList methodlist = groupNode
											.getChildNodes();

									aw.write(""
											+ ""

											+ "\n"
											+ "	<table id='example2' class='table table-bordered table-hover'>\n"
											+ "<thead>\n" + "<tr>\n"
											+ "<th>Index</th>\n"
											+ "<th>Signature</th>\n"
											+ "<th>Name</th>\n"
											+ "<th>Class</th>\n"

											+ "</tr>\n" + "</thead>\n"
											+ "<tbody>\n" + "");
									int count = 1;
									for (int i = 0; i < methodlist.getLength(); i++) {
										Node methodnode = methodlist.item(i);

										if (methodnode.getNodeName().equals(
												"method")) {
											Element methodElement = (Element) methodnode;

											aw.write("	<tr> \n"
													+ "	<td>"
													+ count
													+ "</td> \n"
													+ "	<td>"
													+ methodElement
															.getAttribute("signature")
													+ "</td>\n"
													+ "	<td>"
													+ methodElement
															.getAttribute("name")
													+ "</td>\n"
													+ "	<td>"
													+ methodElement
															.getAttribute("class")
													+ "</td>\n"

													+ "		</tr>");

											count++;

										}

									}

									aw.write("" + "</tbody>\n"

									+ "	</table>\n" + "	</div>\n" + "	</div>\n"
											+ "" + "");

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

					if (suiteNode.getNodeName().equals("test")) {

						String testStatus = "";

						try {
							if (suiteNode.hasChildNodes()) {

								NodeList testlist = suiteNode.getChildNodes();

								for (int k = 0; k < testlist.getLength(); k++) {
									Node testnode = testlist.item(k);

									if (testnode.getNodeName().equals("class")) {

										NodeList testmethodlist = testnode
												.getChildNodes();

										for (int v = 0; v < testmethodlist
												.getLength(); v++) {

											Node methodsNode = testmethodlist
													.item(v);

											if (methodsNode.getNodeName()
													.equals("test-method")) {

												Element e = (Element) methodsNode;

												if (e.getAttribute("signature")
														.contains("setUp")
														|| e.getAttribute(
																"signature")
																.contains(
																		"fetchSuiteConfiguration")
														|| e.getAttribute(
																"signature")
																.contains(
																		"tearDown")
														|| e.getAttribute(
																"signature")
																.contains(
																		"ganeratezip()"))

												{

												} else {

													testStatus = e
															.getAttribute("status");

													if (testStatus
															.equalsIgnoreCase("fail")) {

														NodeList exception = methodsNode
																.getChildNodes();
														try {
															for (int i = 0; i <= exception
																	.getLength(); i++) {

																Node ex = exception
																		.item(i);

																if (ex.getNodeName()
																		.equals("exception"))

																{

																	if (ex.hasChildNodes()) {
																		NodeList a = ex
																				.getChildNodes();

																		for (int j = 0; j < a
																				.getLength(); j++) {

																			Node errmessage = a
																					.item(j);

																			/*
																			 * System
																			 * .
																			 * out
																			 * .
																			 * println
																			 * (
																			 * "ooooooooooooooooooooooo"
																			 * +
																			 * errmessage
																			 * .
																			 * getNodeName
																			 * (
																			 * )
																			 * )
																			 * ;
																			 */if (errmessage
																					.getNodeName()
																					.equals("message")) {
																				message.append(errmessage
																						.getTextContent());
																			}

																			if (errmessage
																					.getNodeName()
																					.equals("full-stacktrace")) {
																				fullstacktrace
																						.append(errmessage
																								.getTextContent());
																			}

																		}

																	}

																}

															}

														} catch (Exception er) {
															er.printStackTrace();
														}

													}

												}

											}
										}

									}

								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						Element testelElement = (Element) suiteNode;
						long timeDuration = Long.parseLong(testelElement
								.getAttribute("duration-ms")) / 1000;

						// box-success green
						// box-info blue
						// box-danger pass

						if (testStatus.equalsIgnoreCase("pass")) {

							aw.write("<div class='box box-success collapsed-box'> ");

							aw.write("			<div class='box-header with-border'>"
									+ "			<h3 class='box-title'>"
									+ "		TEST NAME :<b> <font color=#1bd01b>"
									+ testelElement.getAttribute("name")
									+ "</b></font> "
									+ "</h3>"
									+ "				<div class='box-tools pull-right'>"
									+ "					<button class='btn btn-box-tool' data-widget='collapse'>"
									+ "						<i class='fa fa-plus'></i>"
									+ "					</button>"

									+ "				</div>"
									+ "			</div>"
									+ "			<div class='box-body' style='width: 500'>"

							);

						} else if (testStatus.equalsIgnoreCase("fail")) {

							aw.write("<div class='box box-danger collapsed-box'> ");

							aw.write("	<div class='box-header  with-border'>"
									+ "<h3 class='box-title'>TEST NAME : <b> <font color='red'>"
									+ testelElement.getAttribute("name")
									+ "</B></font> </h3> "
									+ ""
									+ ""
									+ "	<div class='box-tools pull-right'>"
									+ "					<button class='btn btn-box-tool' data-widget='collapse'>"
									+ "						<i class='fa fa-plus'></i>"
									+ "					</button>"

									+ "				</div>"
									+ "	</div> "
									+ "			<div class='box-body' style='width: 500'>");

						} else {
							aw.write("<div class='box collapsed-box'> ");
							aw.write("	<div class='box-header with-border'>"
									+ "<h3 class='box-title'>TEST NAME : <b><font color='orange'>"
									+ testelElement.getAttribute("name")
									+ "</b></font></h3> "
									+ "	"

									+ "	<div class='box-tools pull-right'>"
									+ "					<button class='btn btn-box-tool' data-widget='collapse'>"
									+ "						<i class='fa fa-plus'></i>"
									+ "					</button>"

									+ "				</div>"
									+ "	</div> "
									+ "			<div class='box-body' style='width: 500'> ");

						}

						aw.write(""

								+ "	<div class='box-body'> "

								+ "	<table id='example1' class='table table-bordered table-striped'> "
								+ "	<thead>" + "	" + "<tr>"
								+ "  <th>Status</th>" + "			<th>Duration</th>"
								+ "			<th>Started-at</th>"
								+ "			<th>Finished-at</th>" + "" + "</tr>"
								+ "	</thead>"

								+ "	<tbody>   ");

						if (testStatus.equalsIgnoreCase("pass")) {
							aw.write("	<tr>"
									+ "<td >   <font color='green'><b>   "
									+ testStatus + " </b></font></td>");
						} else if (testStatus.equalsIgnoreCase("fail")) {

							aw.write("	<tr>"
									+ "<td >   <font color='red'><b>   "
									+ testStatus + " </b></font></td>");

						} else {
							aw.write("	<tr>"
									+ "<td >   <font color='yello'><b>   "
									+ testStatus + " </b></font></td>");

						}

						aw.write("		    <td>    " + timeDuration + "</td>"
								+ "	" + "	        <td>"
								+ testelElement.getAttribute("started-at")
								+ "</td>" + "          <td>"
								+ testelElement.getAttribute("finished-at")
								+ "</td>" + "" + "	</tr>"

								+ "</tbody>"

								+ "</table> "

								+ "</div>");

						if (testStatus.equalsIgnoreCase("fail")) {
							aw.write(""

									+ "	<div class='box-body'> "

									+ "	<table id='example1' class='table table-bordered table-striped'> "
									+ "	<thead>" + "	" + "<tr>"
									+ "  <th>Message</th>"

									+ "  <th>Exception</th>"

									+ "</tr>" + "	</thead>"

									+ "	<tbody>   "

									+ "	<tr>" + "	" + "	    <td>" + message
									+ "</td>" + "	    <td>" + fullstacktrace
									+ "</td>" + "	" + "	</tr>"

									+ "</tbody>"

									+ "</table> "

									+ "</div>");

						}

						aw.write("<div class='box-body'> "
								+ "	<table id='example1' class='table table-bordered table-striped'>"
								+ "		<thead>"

								+ "			<tr>" + "" + "<th>STEP NO.</th>"
								+ "			<th>LOG STEP</th>"

								+ "			</tr>  </thead>  	"

								+ "	<tbody>");

						try {

							if (suiteNode.hasChildNodes()) {

								NodeList testlist = suiteNode.getChildNodes();

								for (int k = 0; k < testlist.getLength(); k++) {
									Node testnode = testlist.item(k);

									if (testnode.getNodeName().equals("class")) {

										NodeList testmethodlist = testnode
												.getChildNodes();

										for (int v = 0; v < testmethodlist
												.getLength(); v++) {

											Node methodsNode = testmethodlist
													.item(v);

											if (methodsNode.getNodeName()
													.equals("test-method")) {

												Element e = (Element) methodsNode;

												List<String> methodName = new ArrayList<String>();

												methodName.add(e
														.getAttribute("name"));

												NodeList reporterOutputlist = methodsNode
														.getChildNodes();

												for (int a = 0; a < methodName
														.size(); a++) {

													if (e.getAttribute(
															"signature")
															.contains("setUp")
															|| e.getAttribute(
																	"signature")
																	.contains(
																			"fetchSuiteConfiguration")
															|| e.getAttribute(
																	"signature")
																	.contains(
																			"tearDown")
															|| e.getAttribute(
																	"signature")
																	.contains(
																			"ganeratezip()"))

													{

													} else {

														aw.write("<tr> <td> <b>METHOD NAME :  "
																+ methodName
																		.get(a)
																+ " </b>  </td> </tr>");
													}

												}

												for (int f = 0; f < reporterOutputlist
														.getLength(); f++) {

													Node reporterNode = reporterOutputlist
															.item(f);

													if (reporterNode
															.getNodeName()
															.equals("reporter-output")) {

														if (reporterNode
																.hasChildNodes()) {

															NodeList lognodelist = reporterNode
																	.getChildNodes();
															int count = 1;
															for (int l = 0; l < lognodelist
																	.getLength(); l++) {
																Node lognode = lognodelist
																		.item(l);

																if (lognode
																		.getNodeName()
																		.equals("line"))

																{

																	aw.write("	<tr> "
																			+ "						<td>"
																			+ count
																			+ "</td>"
																			+ "						<td>"
																			+ ""
																			+ ""
																			+ lognode
																					.getTextContent()
																			+ " "
																			+ "</td>"

																			+ "					</tr> ");
																	count++;
																}

															}

														}

													}

												}

											}
										}

									}

								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

						aw.write("</tbody>" + "	</table>" + "</div> " +

						"</div> " +

						// following box sucess div
								"</div>");

					}

				}

			}
			aw.write("</div> " // col xs 12

					// //box body

					+ "</section>"

					+ "</div>" + "</div>" + "</div>");

		}
		 aw.write(" " + " <footer style='background:#FFFFFF;width:100%;height:40px;position:fixed;bottom:0;left:0;'> "
				    + " <div style='padding-top:10px;padding-right:10px;float:right;'> "
				    + "  <b>Version</b> 2.0" + " </div>"
				    + " <div style='padding-top:10px;padding-left:10px'>"+"<strong >Copyright &copy; 2014-2015 <a>KIWIQA</a>. </strong> All rights reserved."
				    + " <div> " + "</footer>");

	/*	aw.write(" " + "	<footer class='main-footer'> "
				+ "	<div class='pull-right hidden-xs'> "
				+ "		<b>Version</b> 2.0" + "	</div>"
				+ "	<strong>Copyright &copy; 2014-2015 <a href=>KIWIQA</a>."
				+ "	</strong> All rights reserved." + "</footer>");*/

		aw.write("    " + "	<div class='control-sidebar-bg'></div>" + "	</div>"

		+ "<script src='jQuery-2.1.4.min.js'></script>"

		+ "	<script src='bootstrap.min.js'"
				+ "		type='text/javascript'></script>"

				+ "	<script src='jquery.dataTables.min.js'"
				+ "		type='text/javascript'></script>"
				+ "	<script src='dataTables.bootstrap.min.js'"
				+ "		type='text/javascript'></script>"

				+ "	<script src='jquery.slimscroll.min.js'"
				+ "	type='text/javascript'></script>"

				+ "	<script src='fastclick.min.js'></script>"

				+ "	<script src='app.min.js' type='text/javascript'></script> "

				+ "	<script src='demo.js' type='text/javascript'></script>"

				+ "	<script type='text/javascript'>" + "      $(function () {"
				+ "       $('#example1').dataTable();"
				+ "        $('#example2').dataTable({"
				+ "         'bPaginate': true,"
				+ "          'bLengthChange': false,"
				+ "         'bFilter': false," + "         'bSort': true,"
				+ "         'bInfo': true," + "         'bAutoWidth': false"
				+ "       });" + "     });" + " </script>" + "" + "</body> "
				+ "</html>");

		aw.close();
	}

	public void getBuildResult() {

		String currentDir = System.getProperty("user.dir")
				+ "//Kiwi-Reports//Reports//";

		File folderNumber = new File(currentDir + "1");
		List<String> folderName = new ArrayList<String>();

		String[] subNote = folderNumber.list();

		for (String filename : subNote) {

			folderName.add(filename);

		}

	}

	public void createReportsFile(String current) {

		PathFetcher path = new PathFetcher();

		try {

			/*
			 * createDirectory(current + "\\Kiwi-Reports\\bootstrap");
			 * createDirectory(current + "\\Kiwi-Reports\\bootstrap\\css");
			 * createDirectory(current + "\\Kiwi-Reports\\bootstrap\\fonts");
			 * createDirectory(current + "\\Kiwi-Reports\\bootstrap\\js");
			 * 
			 * createDirectory(current + "\\Kiwi-Reports\\dist");
			 * createDirectory(current + "\\Kiwi-Reports\\dist\\css");
			 * createDirectory(current + "\\Kiwi-Reports\\dist\\css\\skins");
			 * createDirectory(current + "\\Kiwi-Reports\\dist\\js");
			 * createDirectory(current + "\\Kiwi-Reports\\dist\\js\\pages");
			 * 
			 * createDirectory(current + "\\Kiwi-Reports\\pages");
			 * createDirectory(current + "\\Kiwi-Reports\\pages\\charts");
			 * createDirectory(current + "\\Kiwi-Reports\\pages\\tables");
			 * 
			 * createDirectory(current + "\\Kiwi-Reports\\plugins");
			 * createDirectory(current + "\\Kiwi-Reports\\plugins\\chartjs");
			 * createDirectory(current + "\\Kiwi-Reports\\plugins\\jQuery");
			 */
			// --------------------------

			readWriteFile(path.readFile("/bootstrap/css/bootstrap.css"),
					current + "/Kiwi-Reports/bootstrap.css");
			// --------------------------

			readWriteFile(path.readFile("/bootstrap/css/bootstrap.css.map"),
					current + "/Kiwi-Reports/bootstrap.css.map");

			// --------------------------

			readWriteFile(path.readFile("/bootstrap/css/bootstrap.min.css"),
					current + "/Kiwi-Reports/bootstrap.min.css");

			// --------------------------

			// --------------------------

			readWriteFile(path.readFile("/bootstrap/js/bootstrap.js"), current
					+ "/Kiwi-Reports/bootstrap.js");

			// --------------------------

			readWriteFile(path.readFile("/bootstrap/js/bootstrap.min.js"),
					current + "/Kiwi-Reports/bootstrap.min.js");

			readWriteFile(path.readFile("/bootstrap/js/npm.js"), current
					+ "/Kiwi-Reports/npm.js");

			readWriteFile(path.readFile("/dist/css/skins/_all-skins.css"),
					current + "/Kiwi-Reports/_all-skins.css");

			readWriteFile(path.readFile("/dist/css/skins/_all-skins.min.css"),
					current + "/Kiwi-Reports/_all-skins.min.css");

			readWriteFile(path.readFile("/dist/css/AdminLTE.css"), current
					+ "/Kiwi-Reports/AdminLTE.css");
			readWriteFile(path.readFile("/dist/css/AdminLTE.min.css"), current
					+ "/Kiwi-Reports/AdminLTE.min.css");

			readWriteFile(path.readFile("/dist/js/app.js"), current
					+ "/Kiwi-Reports/app.js");
			readWriteFile(path.readFile("/dist/js/app.min.js"), current
					+ "/Kiwi-Reports/app.min.js");
			readWriteFile(path.readFile("/dist/js/demo.js"), current
					+ "/Kiwi-Reports/demo.js");

			readWriteFile(path.readFile("/dist/js/pages/dashboard.js"), current
					+ "/Kiwi-Reports/dashboard.js");

			readWriteFile(path.readFile("/dist/js/pages/dashboard2.js"),
					current + "/Kiwi-Reports/dashboard2.js");

			// --------------------------

			readWriteFile(path.readFile("/plugins/chartjs/Chart - Copy.js"),
					current + "/Kiwi-Reports/Chart - Copy.js");

			readWriteFile(path.readFile("/plugins/chartjs/Chart.min.js"),
					current + "/Kiwi-Reports/Chart.min.js");

			readWriteFile(path.readFile("/plugins/jQuery/jQuery-2.1.4.min.js"),
					current + "/Kiwi-Reports/jQuery-2.1.4.min.js");

		} catch (Exception e) {
			System.err.println("problem in  creating directory");
			e.printStackTrace();
		}

	}

	public void readWriteFile(StringBuilder sb, String FileForWrite) {

		try {

			/*
			 * System.out.println(sb.toString());
			 */
			File bootstrapJs = new File(FileForWrite);

			if (!bootstrapJs.exists()) {
				bootstrapJs.createNewFile();
			}

			FileWriter bootstrapJsFileWrite = new FileWriter(
					bootstrapJs.getAbsoluteFile());

			BufferedWriter bootstrapJsWrite = new BufferedWriter(
					bootstrapJsFileWrite);

			bootstrapJsFileWrite.write(sb.toString());

			bootstrapJsWrite.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createDirectory(String path) {

		File jsdir = new File(path);

		if (jsdir.mkdir()) {

			System.out.println("directory " + path + "  is created");
		} else {
			System.out.println("directory " + path + "  is not created");

		}

	}

	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {

	}

}