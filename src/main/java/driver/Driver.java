package driver;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final int WAIT_TIMEOUT_SECONDS = 15;
    private static WebDriver driver;

    private Driver(){}

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", RESOURCES_PATH + "chromedriver.exe");
            ChromeDriverCreator creator = new ChromeDriverCreator();
            driver = creator.createWebDriver();
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
