package ReusableClasses;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import jdk.javadoc.doclet.Reporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class ReusableFramework {
static WebDriver driver;
static int timeout =15;

    public static WebDriver setDriver(String browser){

        if(browser=="chrome"){
        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver86");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
         driver = new ChromeDriver(options);

        }
        else if(browser=="firefox"){
            System.setProperty("webdriver.gecko.driver","src/main/resources/driver/geckodriver");
            FirefoxOptions options= new FirefoxOptions();
            options.addArguments("incognito");
            driver = new FirefoxDriver(options);
        }
        else if(browser=="edge"){
            EdgeDriverManager.edgedriver().setup();
            driver=new EdgeDriver();

        }
        return driver;
    }


    public static void verifyTitle(WebDriver driver, String expectedTitle, ExtentTest logger){
        String actualTitle = driver.getTitle();
        if(actualTitle.equals(expectedTitle)){
            System.out.println("Expected title matches with Actual Title. Expected title was "+expectedTitle);
        }else {
            System.out.println("Expected title doesn't doesn't match with expected. The actual title is "+ actualTitle);
        }
    }//end of the verifyTitle Method

    public static void userKeysClearNType(WebDriver driver, String locator, String userInput,String elementName, ExtentTest logger){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        try{
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            element.clear();
            element.sendKeys(userInput);
            logger.log(LogStatus.INFO,"Entering a value in the box. Name of the input is "+ elementName);
            System.out.println("Entering a value in the box. The element Name is "+ elementName);
        }catch (Exception e){
            System.out.println("Unable to enter element "+ elementName+" "+ e);
            logger.log(LogStatus.FAIL,"Unable to enter the element");
            getScreenShot(driver,logger,elementName);
        }
    }//end of the userKeyClearNType method

    public static void getScreenShot(WebDriver driver,ExtentTest logger,String imageName) {
        try {
            String fileName = imageName + ".png";
            String directory ="src/main/java/HTML_Report/Screenshots/";
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, new File(directory + fileName));
            //String imgPath = directory + fileName;
            String image = logger.addScreenCapture("Screenshots//" + fileName);
            logger.log(LogStatus.INFO, "ScreenShot", image);

        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Error occured while taking SCREENSHOT!!!");
            e.printStackTrace();
        }//end of if else condition
    }//end of screenshot method



}//end of the java class
