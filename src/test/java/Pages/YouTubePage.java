package Pages;

import org.openqa.selenium.WebDriver;

import driver.Driver;

public class YouTubePage {

    static WebDriver driver = Driver.webDriver;

    public static void goToYoutube() {
        driver.get(System.getenv("APP_URL"));
    }
}