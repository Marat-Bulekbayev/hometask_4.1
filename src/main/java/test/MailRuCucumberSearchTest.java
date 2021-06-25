package test;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
                 features = "src/main/resources/features/MailRuSearch.feature",
                 glue = { "steps" })
public class MailRuCucumberSearchTest extends MailRuCucumberBaseTest {

}
