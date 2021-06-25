package test;

import driver.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class MailRuCucumberBaseTest extends AbstractTestNGCucumberTests {

    protected static final WebDriver driver = Driver.getDriver();

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }
}
