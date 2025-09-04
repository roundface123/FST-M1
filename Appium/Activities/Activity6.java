package activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	//Setup function
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		//define the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.noReset();
		
		//set the appium server url
		URL serverurl = new URI("http://localhost:4723").toURL();
		
		//initialize the driver
		driver = new AndroidDriver(serverurl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get("https://training-support.net/webelements/sliders");
	}
	
	@Test
	public void slider75Test() {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
		
		Dimension dims = driver.manage().window().getSize();
		
		Point start = new Point((int)(0.5*dims.getWidth()), (int)(0.72*dims.getHeight()));
		Point end = new Point((int)(0.67*dims.getWidth()), (int)(0.72*dims.getHeight()));
		
		ActionsBase.siwpe(driver, start, end, 2000);
		String volumeText = driver.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]")).getText();
		Assert.assertEquals(volumeText, "75%");
	}
	
	@Test
	public void slider25Test() {
		
		
		wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.SeekBar")));
		
		Dimension dims = driver.manage().window().getSize();
		
		Point start = new Point((int)(0.5*dims.getWidth()), (int)(0.72*dims.getHeight()));
		Point end = new Point((int)(0.34*dims.getWidth()), (int)(0.72*dims.getHeight()));
		
		ActionsBase.siwpe(driver, start, end, 2000);
		String volumeText = driver.findElement(AppiumBy.xpath("//android.view.View/android.widget.TextView[contains(@text, '%')]")).getText();
		Assert.assertEquals(volumeText, "25%");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
