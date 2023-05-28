import org.junit.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {
    private WebDriver driver;
    // Different pages in same website
    String[] pageUrls = {"https://shopmaksu.com/collections/dresses", "https://shopmaksu.com/collections/all-clothing"};

    @Before
    public void setup() throws MalformedURLException {
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

    @Test
    public void testLoginAndAccountPage() {
        // Login Page
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail("javace7421@favilu.com");
        loginPage.setPassword("Maga2468*");
        loginPage.clickSubmit();

        // Account page
        AccountPage accountPage = new AccountPage(driver);
        accountPage.logoutSubmit();
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}







// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
// import org.junit.*;
// import org.openqa.selenium.*;
// import org.openqa.selenium.chrome.*;
// import org.openqa.selenium.support.ui.*;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import java.net.URL;
// import java.net.MalformedURLException;

// public class SeleniumTest {
//     private WebDriver driver;

//     @Before
//     public void setup() throws MalformedURLException {
//         ChromeOptions options = new ChromeOptions();
//         driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
//         driver.manage().window().maximize();
//     }

//     @Test
//     public void testSelenium() {
//         // Home 
//         HomePage homePage = new HomePage(this.driver);
//         System.out.println(homePage.getNavbarText());
//         Assert.assertTrue(homePage.getNavbarText().contains("EVENTS & PARTY"));


//         // Login
//         LoginPage loginPage = new LoginPage(this.driver);
//         loginPage.setEmail("javace7421@favilu.com");
//         loginPage.setPassword("Maga2468*");
//         loginPage.clickSubmit();

//         // Account
//         AccountPage accountPage = new AccountPage(this.driver);
//         accountPage.logoutSubmit();

//     }

//     @After
//     public void close() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }
// }
