package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Waiter {

    private static final int MAX_TIME_OUT = 20;
    private static final int MIN_POLLING_TIME = 2;

    private Waiter(){}

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        Wait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIME_OUT))
                .pollingEvery(Duration.ofSeconds(MIN_POLLING_TIME))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForRightURL(WebDriver driver, String url) {
        Wait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIME_OUT))
                .pollingEvery(Duration.ofSeconds(MIN_POLLING_TIME))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void waitImplicitly(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void waitForTimeInterval(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
