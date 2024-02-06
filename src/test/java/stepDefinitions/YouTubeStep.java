package stepDefinitions;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.gauge.Step;

import Pages.YouTubePage;
import driver.Driver;

public class YouTubeStep {

	WebDriver driver = Driver.webDriver;

	@Step("Go to YouTube")
	public void goToYoutube(){
		YouTubePage.goToYoutube();
	}
}
