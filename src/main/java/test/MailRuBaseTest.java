package test;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class MailRuBaseTest {

    protected static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = Driver.getDriver();
    }

    @AfterTest
    public void cleanUp() {
        Driver.closeDriver();
    }
}
