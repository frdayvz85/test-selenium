import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {
    private WebDriver driver;
    private ConfigReader configReader;
    // Different pages in same website
    String[] pageUrls = {"https://shopmaksu.com/collections/dresses", "https://shopmaksu.com/collections/all-clothing"};

    @Before
    public void setup() throws MalformedURLException {
        configReader = new ConfigReader();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking"); //Disable popup blocking
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testHomePage() {
         // Home Page
        HomePage homePage = new HomePage(driver);
        System.out.println(homePage.getNavbarText());
        Assert.assertTrue(homePage.getNavbarText().contains("EVENTS & PARTY"));
    }

    @Test
    public void testLoginAndAccountPage() {
        // Login Page
        // Read username and password from the configuration file
        String email = configReader.getProperty("email");
        String password = configReader.getProperty("password");

        // Check if email is null, provide a value 
        if (email == null) {
            email = "javace7421@favilu.com";
        }

        // Check if password is null, provide a value
        if (password == null) {
            password = "Maga2468*";
        }
        


        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickSubmit();

        // Account page
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logoutSubmit();
    }


    @Test
    public void testContactPage() {
        // Contact Page
        ContactPage contactPage = new ContactPage(driver);
        contactPage.setEmail("testusertestse41144@gmail.com");
        contactPage.clickSubmit();

        //Wait for the subscription confirmation or error message to appear
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By successMessageLocator = By.xpath("//p[@class='newsletter__message newsletter__message--success']");
        By errorMessageLocator = By.xpath("//div[@class='newsletter__message newsletter__message--error errors']");
        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(successMessageLocator));
        WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageLocator));

        // Check if subscription was successful or not
        if (successMessage.isDisplayed()) {
            System.out.println("Test Passed: Subscription Successful");
        } else if (errorMessage.isDisplayed()) {
            System.out.println("Test Failed: Subscription Failed");
        } else {
            System.out.println("Test Failed: Unknown Error");
        }
    }

    @Test
    public void testHistoryPage() {
         // History class
        History history = new History();
        history.performHistoryTest();
    }



    @Test
    public void testMultiplePages() {
        // Test Multiple Pages
        for (String pageUrl : pageUrls) {
            try {
                System.out.println("pageUrl: " + pageUrl);
                driver.get(pageUrl);
                // Example: Assert page title contains specific text
                String pageTitle = driver.getTitle();
                 System.out.println("pageTitle: " + pageTitle);
                if (pageUrl.equals("https://shopmaksu.com/collections/dresses")) {
                    Assert.assertTrue(pageTitle.contains("Dresses"));
                } else if (pageUrl.equals("https://shopmaksu.com/collections/all-clothing")) {
                    Assert.assertTrue(pageTitle.contains("All clothing"));
                }else {
                    // Default assertions/tests if the page URL doesn't match any specific condition
                    Assert.fail("Invalid page URL: " + pageUrl);
                }
            }catch (Exception e) {
                // Handle the exception here (e.g., log the error)
                System.out.println("Error occurred while navigating to page: " + pageUrl);
                e.printStackTrace();
            }
        }
    }

    


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    
}

