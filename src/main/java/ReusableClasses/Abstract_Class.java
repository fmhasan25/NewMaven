package ReusableClasses;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class Abstract_Class {
    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;

    @BeforeSuite
    public void defineDriver() {
        //define driver with choosing the browser
        driver = ReusableFramework.setDriver("chrome");

        //define the report path
        reports = new ExtentReports("src/main/java/HTML_Report/TestReport.html", true);

    }//end of the @BeforeSuite

    @BeforeMethod
    public void getMethodName(Method methodName) {
        logger = reports.startTest(methodName.getName());
    }//end of the @BeforeMethod


    @AfterMethod
    public void endTest() {
        reports.endTest(logger);
    }//end of the @AfterMethod

    @AfterSuite
    public void closeInfo() {
        //terminate the page
        driver.quit();

        //finishing the reporting
        reports.flush();
    }//end of the @AfterSuite


}//end of the java class

