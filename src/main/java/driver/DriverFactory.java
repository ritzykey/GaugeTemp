package driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

    private static WebDriver driver;

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser. The required
    // browser can be set as an environment variable.
    // Refer
    // http://getgauge.io/documentation/user/current/managing_environments/README.html
    static WebDriver getDriver() {

        String browser = System.getenv("BROWSER");
        System.out.println("Browser=" + browser);
        browser = (browser == null) ? "CHROME" : browser;

        switch (browser) {
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                }
                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);
                break;
        }
        int impWait = Integer.parseInt(System.getenv("implicityWait"));
        int pageWait = Integer.parseInt(System.getenv("pageLoadTimeout"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(impWait));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(pageWait));

        return driver;

    }
}
