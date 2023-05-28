import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class AccountPage extends BasePage {
    private final By logoutButton =  By.xpath("//a[@id='customer_logout_link']");

    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://shopmaksu.com/account");
    }

    public void logoutSubmit() {
        WebElement logoutElement = waitAndReturnElement(logoutButton);
        logoutElement.click();

        // Verify successful logout by checking the page title
        wait.until(ExpectedConditions.titleContains("shopmaksu EU"));

        String pageTitle = driver.getTitle();
        if (pageTitle.contains("shopmaksu EU")) {
            System.out.println("Logout test passed!");
        } else {
            System.out.println("Logout test failed!");
        }
}

}
