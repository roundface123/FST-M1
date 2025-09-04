package activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity3 {
	
	AppiumDriver driver;
	WebDriverWait wait;
	
	//Setup function
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		//define the capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
//		options.setApp("path/to/application.apk");
		options.setApp("C:/Users/AshaAnnaThomas/eclipse-workspace/FST_Appium/src/test/resources/Calculator.apk");
		
		//Only for android
//		options.setAppPackage("com.google.android.calculator");
//		options.setAppActivity("com.android.calculator2.Calculator");
		options.noReset();
		
		//set the appium server url
		URL serverurl = new URI("http://localhost:4723").toURL();
		
		//initialize the driver
		driver = new AndroidDriver(serverurl, options);
	}
	
	@Test
	public void addTest() {
		driver.findElement(AppiumBy.id("clr")).click();
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(By.id("op_add")).click();
		driver.findElement(By.id("digit_9")).click();
		driver.findElement(By.id("eq")).click();
		String ans = driver.findElement(By.id("result_final")).getText();
		System.out.println(ans);
		assertEquals("14", ans);
	}
	
	@Test
	public void subTest() {
		driver.findElement(AppiumBy.id("clr")).click();
		driver.findElement(AppiumBy.id("digit_1")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(By.id("op_sub")).click();
		driver.findElement(By.id("digit_5")).click();
		driver.findElement(By.id("eq")).click();
		String ans = driver.findElement(By.id("result_final")).getText();
		System.out.println(ans);
		assertEquals("5", ans);
	}
	
	@Test
	public void multiplyTest() {
		driver.findElement(AppiumBy.id("clr")).click();
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(By.id("op_mul")).click();
		driver.findElement(By.id("digit_1")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(By.id("eq")).click();
		String ans = driver.findElement(By.id("result_final")).getText();
		System.out.println(ans);
		assertEquals("500", ans);
	}
	
	@Test
	public void divideTest() {
		driver.findElement(AppiumBy.id("clr")).click();
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.id("digit_0")).click();
		driver.findElement(By.id("op_div")).click();
		driver.findElement(By.id("digit_2")).click();
		driver.findElement(By.id("eq")).click();
		String ans = driver.findElement(By.id("result_final")).getText();
		System.out.println(ans);
		assertEquals("25", ans);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
