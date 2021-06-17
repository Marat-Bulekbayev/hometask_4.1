package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final int WAIT_TIMEOUT_SECONDS = 15;
    private static WebDriver driver;

    private Driver(){}

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            try {
                driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
