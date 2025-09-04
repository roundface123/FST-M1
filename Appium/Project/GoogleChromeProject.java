package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
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

import activities.ActionsBase;

public class GoogleChromeProject {
	
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
		driver.get("https://v1.training-support.net/selenium");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@Test (priority = 1)
	public void createTaskTest() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Selenium']")));
		
		Dimension dims = driver.manage().window().getSize();
		
		Point start = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.9));
        Point end   = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.2));
        
        ActionsBase.swipe(driver, start, end, 700);       
        
        boolean found = false;
        int swipes = 0;
        
        while (!found && swipes < 3 ) {
        	try {
        		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("To-Do List  Elements get added at run time"))).click();
        		found = true;
        	} catch(Exception e ) {
        		ActionsBase.swipe(driver, start, end, 700);  // perform swipe
                swipes++;
        	}
        }
        
        String[] tasks = {"Add tasks to list", "Get number of tasks", "Clear the list" };
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")));
        for (String task : tasks) {
        	driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys(task);
        	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Add Task']")).click();
        	
        }
        
        List<WebElement> taskList = driver.findElements(AppiumBy.xpath("//android.view.View[@resource-id='tasksList']//android.widget.TextView"));
        System.out.println(taskList.size());
        
        for (WebElement task : taskList) {
        	task.click();
        }
        
        Assert.assertEquals(taskList.size(), 4);
	}
	
	@Test (priority = 2)
	public void simpleLoginTest() {
		//going back to initial page
//		driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='']")).click();
		
		driver.get("https://v1.training-support.net/selenium");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Selenium']")));
		
		Dimension dims = driver.manage().window().getSize();
		boolean found = false;
        int swipes = 0;
        
		Point start = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.9));
        Point end   = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.5));
          
//        ActionsBase.swipe(driver, start, end, 700);
        
        while (!found && swipes < 3 ) {
        	try {
                wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Login Form  Please sign in."))).click();
        		found = true;
        	} catch(Exception e ) {
        		ActionsBase.swipe(driver, start, end, 700);  // perform swipe
                swipes++;
        	}
        }
       
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Log in']")).click();
        
//        String welcomeMessage = driver.findElement(AppiumBy.xpath("//android.view.View[@text='Sign in! Welcome Back!']")).getText();
        
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.view.View[@text='Welcome Back, admin']")));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")));
        String welcomeMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")).getText();
        
        Assert.assertEquals(welcomeMessage, "Welcome Back, admin");
        
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).clear();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).clear();
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Log in']")).click();
        
        String inCorrectMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")).getText();
        
        Assert.assertEquals(inCorrectMessage, "Invalid Credentials");
                
        
	}
	
	@Test (priority = 3)
	public void popUpCardTest() {
		driver.get("https://v1.training-support.net/selenium");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Selenium']")));
		
		Dimension dims = driver.manage().window().getSize();
		boolean found = false;
        int swipes = 0;
        
		Point start = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.9));
        Point end   = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.2));

        
        while (!found && swipes < 4 ) {
        	try {
                wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@content-desc='Popups  Tooltips and Modals']"))).click();
        		found = true;
        	} catch(Exception e ) {
        		ActionsBase.swipe(driver, start, end, 700);  // perform swipe
                swipes++;
        	}
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.Button[@text='Sign In']")));       
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Sign In']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("password");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Log in']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")));
        String text = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")).getText();
        Assert.assertEquals(text, "Welcome Back, admin");
        
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Sign In']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")));
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys("admin");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Log in']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")));
        String textIncorrect = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='action-confirmation']")).getText();
        Assert.assertEquals(textIncorrect, "Invalid Credentials");
        
        
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
