package steps;

import businessobject.User;
import driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobject.MailRuMainPage;
import util.UserCreator;

public class MailRuSteps {

    private static final WebDriver driver = Driver.getDriver();
    private User mailRuUser = UserCreator.createUser();

    @Given("^user opens mail.ru main page$")
    public void open_mail_ru_main_page() {
        new MailRuMainPage(driver).openPage();
    }

    @When("^user performs authorization$")
    public void perform_authorization() {
        new MailRuMainPage(driver).loginToMailbox(mailRuUser);
    }

    @Then("^write new mail button is displayed$")
    public void verify_login_is_successful() {
        new MailRuMainPage(driver).isUserLoggedInMailbox();
    }

    @When("^user enters \"([^\"]*)\" and press search$")
    public void perform_search(String searchQuery) {
        new MailRuMainPage(driver).search(searchQuery);
    }

    @Then("^first search result contains \"([^\"]*)\"$")
    public void verify_first_search_result(String searchQuery) {
        new MailRuMainPage(driver).isSearchResultDisplayed(searchQuery);
    }
}
