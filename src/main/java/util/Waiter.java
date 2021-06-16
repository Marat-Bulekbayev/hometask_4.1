package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class Waiter {

    private static final int MAX_TIME_OUT = 20;
    private static final int MIN_POLLING_TIME = 2;

    private Waiter(){}

    public static void waitForElementToBeVisible(WebDriver driver, By by) {
        Wait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIME_OUT))
                .pollingEvery(Duration.ofSeconds(MIN_POLLING_TIME))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForRightURL(WebDriver driver, String url) {
        Wait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_TIME_OUT))
                .pollingEvery(Duration.ofSeconds(MIN_POLLING_TIME))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.urlToBe(url));
    }
}
