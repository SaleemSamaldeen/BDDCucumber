package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Hooks {

    public static ExtentTest test;
    public ExtentTest suiteTest;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;

    //@Before
    public void createExtentReport() {
        htmlReporter = new ExtentSparkReporter("./reports/result.html");
        htmlReporter.config().setDocumentTitle("Page Object Model Automation");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        suiteTest = extent.createTest("Rest API", "Customer wants to sell his car");
        suiteTest.assignAuthor("Saleem Samaldeen");
        suiteTest.assignCategory("Rest webservices");
        test = suiteTest.createNode("Rest API Task");
    }

    public static void reportStep(String desc, String status) {
        if (status.equalsIgnoreCase("TRUE")) {
            test.pass(desc);
        } else if (status.equalsIgnoreCase("FALSE")) {
            test.fail(desc);
        } else if (status.equalsIgnoreCase("WARNING")) {
            test.warning(desc);
        } else if (status.equalsIgnoreCase("INFO")) {
            test.info(desc);
        }
    }

    //@After
    public void endReport() {
        extent.flush();
    }
}
