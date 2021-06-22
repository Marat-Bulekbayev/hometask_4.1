package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YandexDiskFilesPage extends AbstractPage {

    private static final String PAGE_URL = "https://disk.yandex.ru/";

    @FindBy(xpath = "//div[@class='Modal-Content']/div/button")
    WebElement closeModalWindow;

    @FindBy(xpath = "//div[@value='add-to-album']")
    WebElement moveToAlbum;

    @FindBy(xpath = "//div[@class='listing-item__icon-wrapper js-prevent-mouse-selection']")
    List<WebElement> files;

    public YandexDiskFilesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public YandexDiskFilesPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public YandexDiskFilesPage dragFirstFileToTrashBin() {
        dragAndDropElement(files.get(0), files.get(files.size() - 1));
        return this;
    }

    public YandexDiskFilesPage moveFileToFavoritesAlbum() {
        openContextMenuAndClick(files.get(0), moveToAlbum);
        return this;
    }
}
