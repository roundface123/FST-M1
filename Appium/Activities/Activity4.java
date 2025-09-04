package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity4 {
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	//Setup function
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		//define the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.google.android.contacts");
		options.setAppActivity("com.android.contacts.activities.PeopleActivity");
		
		options.noReset();
		
		//set the appium server url
		URL serverurl = new URI("http://localhost:4723").toURL();
		
		//initialize the driver
		driver = new AndroidDriver(serverurl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@Test
	public void addContats() {
		driver.findElement(AppiumBy.id("floating_action_button")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='First name']")).sendKeys("TestFName");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Last name']")).sendKeys("TestLName");
		driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='+1']")).sendKeys("999148292");
		driver.findElement(AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.widget.Button")).click();
		String contactName = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.google.android.contacts:id/large_title']")).getText();
		
		//driver.findElement(AppiumBy.id("large_title")).getText();
		Assert.assertEquals(contactName, "TestFName TestLName");
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
