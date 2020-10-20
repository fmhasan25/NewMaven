import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class google_testing {

    public static void main(String[] args) {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Cars");
        driver.findElement(By.xpath("//*[@name='q']")).submit();

    }
}