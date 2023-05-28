import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class ContactPage extends BasePage {
    private final By conactInput =  By.xpath("//input[@id='email-input-newsletter-1']");
    private final By submitButton =  By.xpath("/button[@id='subscribe-button-newsletter-1']");

    public ContactPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://shopmaksu.com/pages/contact-us"); 
    }

    public void setEmail(String email) {
        WebElement emailElement = waitAndReturnElement(conactInput);
        emailElement.click();
        emailElement.sendKeys(email);
    }


    public void clickSubmit() {
        WebElement submitElement = waitAndReturnElement(submitButton);
        submitElement.click();
    }

}
