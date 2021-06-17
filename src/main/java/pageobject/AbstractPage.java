package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.Waiter;

public abstract class AbstractPage {

    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void dragAndDropElement(WebElement element, WebElement target) {
        new Actions(driver).dragAndDrop(element, target).build().perform();
    }

    protected void openContextMenuAndClick(WebElement element, WebElement target) {
        Waiter.waitForElementToBeClickable(driver, element);
        new Actions(driver).contextClick(element).build().perform();
        Waiter.waitForElementToBeClickable(driver, target);
        new Actions(driver).click(target).build().perform();
    }

    public void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    public void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }
}
