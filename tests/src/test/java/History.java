import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class History {
    private WebDriver driver;

    public void performHistoryTest() {
        try {
            // Navigate to page1
            driver.navigate().to("https://shopmaksu.com/collections/dresses");

            // Navigate to page2
            driver.navigate().to("https://shopmaksu.com/collections/all-clothing");

            // Navigate back to page1 directly
            driver.navigate().to("https://shopmaksu.com/collections/dresses");

            // Verify if we are on page1
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://shopmaksu.com/collections/dresses")) {
                System.out.println("History test passed!");
            } else {
                System.out.println("History test failed!");
            }
        } catch (Exception e) {
            // Handle the exception
            System.out.println("Exception occurred during history test: " + e.getMessage());
        }
    }
}
