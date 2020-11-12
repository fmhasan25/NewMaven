package Test;

import ReusableClasses.Abstract_Class;
import ReusableClasses.ReusableFramework;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class test1 extends Abstract_Class {

    @Test
    public void openBrowser() throws InterruptedException {
        String cars;
        logger.log(LogStatus.INFO,"Navigating to the browser");
        driver.navigate().to("http://google.com");
        Thread.sleep(5000);
        ReusableFramework.verifyTitle(driver,"Google",logger);
        ReusableFramework.userKeysClearNType(driver,"//*[@name='q']","cars","Car",logger);

    }

}
