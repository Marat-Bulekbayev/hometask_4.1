package test;

import driver.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

@CucumberOptions(plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
                 features = "src/main/resources/features/MailRuSearch.feature",
                 glue = { "steps" })
public class MailRuCucumberSearchTest extends AbstractTestNGCucumberTests {

    private static final WebDriver driver = Driver.getDriver();

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }
}
