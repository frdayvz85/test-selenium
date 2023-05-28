import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class LoginPage extends BasePage {
    private final By emailInput = By.xpath("//input[@type='email' and @name='customer[email]']");
    private final By passwordInput = By.xpath("//input[@type='password' and @name='customer[password]']");
    private final By submitButton = By.xpath("//button[@type='submit' and contains(@class, 'btn--primary')]");


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://shopmaksu.com/account/login");
    }

    public void setEmail(String email) {
        WebElement emailElement = waitAndReturnElement(emailInput);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void setPassword(String password) {
        WebElement passwordElement = waitAndReturnElement(passwordInput);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickSubmit() {
        WebElement submitElement = waitAndReturnElement(submitButton);
        submitElement.click();
    }

    // Login End
}
