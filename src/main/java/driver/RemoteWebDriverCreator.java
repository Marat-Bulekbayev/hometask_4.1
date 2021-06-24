package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverCreator extends WebDriverCreator {

    @Override
    public WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
