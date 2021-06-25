package test;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "json:target/cucumber-report.json", "html:target/cucumber-report" },
                 features = "src/main/resources/features/MailRuLogin.feature",
                 glue = { "steps" })
public class MailRuCucumberTest extends MailRuCucumberBaseTest {

}
