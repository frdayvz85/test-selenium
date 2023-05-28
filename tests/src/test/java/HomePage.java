import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class HomePage extends BasePage {
    String[] pageUrls = {"https://shopmaksu.com/collections/dresses", "https://shopmaksu.com/collections/all-clothing", "https://shopmaksu.com/pages/faq"};

    private final By navbarLocator = By.xpath("//div[contains(@class, 'header__desktop__upper')]");
    private final By searchBarButton = By.xpath("//summary[contains(@class, 'navlink--search')]");
    private final By searchBarInput = By.xpath("//input[@id='SearchInput--desktop']");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver.get("https://shopmaksu.com/");
    }

    public String getNavbarText() {
        WebElement navbarElement = waitAndReturnElement(navbarLocator);
        return navbarElement.getText();
    }

     public void openSearchBar() {
        WebElement searchBarOpenButtonElement = waitAndReturnElement(searchBarButton);
        searchBarOpenButtonElement.click();
    }

    public void typeInSearchBar(String keys) {
        WebElement searchBarElement = waitAndReturnElement(searchBarInput);
        searchBarElement.sendKeys(keys);
    }

}