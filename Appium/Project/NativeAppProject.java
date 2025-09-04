package project;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import activities.ActionsBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class NativeAppProject {
	
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
		options.setApp("C:/Users/AshaAnnaThomas/eclipse-workspace/FST_Appium/src/test/resources/ts-todo-list-v1.apk");
		
		options.noReset();
		
		//set the appium server url
		URL serverurl = new URI("http://localhost:4723").toURL();
		
		//initialize the driver
		driver = new AndroidDriver(serverurl, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void addTask (String taskName,  String taskDescription, String priorityTab) {
		driver.findElement(AppiumBy.id("fab_new_task")).click();
	    WebElement taskNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("et_new_task_name")));
	    taskNameField.sendKeys(taskName);
//		driver.findElement(AppiumBy.id("et_new_task_name")).sendKeys(taskName);
		driver.findElement(AppiumBy.id("et_new_task_description")).sendKeys(taskDescription);
		driver.findElement(AppiumBy.id("tv_new_task_priority")).click();
		driver.findElement(AppiumBy.xpath(priorityTab)).click();
		driver.findElement(AppiumBy.id("bt_new_task_ok")).click();
	}
	
	@Test (priority = 1)
	public void createListTasks() {
		
		addTask("Activity1", "Activity 1 with priority high", "//android.widget.ListView[@resource-id='android:id/select_dialog_listview']/android.widget.LinearLayout[1]");
		addTask("Activity2", "Activity 2 with priority medium", "//android.widget.ListView[@resource-id='android:id/select_dialog_listview']/android.widget.LinearLayout[2]");
		addTask("Activity3", "Activity 3 with priority low", "//android.widget.ListView[@resource-id='android:id/select_dialog_listview']/android.widget.LinearLayout[3]");
		List<WebElement> task = driver.findElements(AppiumBy.xpath("//android.widget.ExpandableListView[@resource-id='com.app.todolist:id/exlv_tasks']//android.widget.RelativeLayout"));	
		
		Assert.assertEquals(task.size(), 3);
	}
	
	@Test(priority = 2)
	public  void editTasks() {
		
		WebElement firstTask = driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.app.todolist:id/rl_exlv_task_group_root\"])[1]"));
		
		// Define finger input
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		int centerX = firstTask.getLocation().getX() + (firstTask.getSize().getWidth() / 2);
		int centerY = firstTask.getLocation().getY() + (firstTask.getSize().getHeight() / 2);
			

		// Build the sequence
		Sequence longPress = new Sequence(finger, 1)
		        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
		        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		        .addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2))) // hold
		        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform action
		driver.perform(Arrays.asList(longPress));
//		ActionsBase.siwpe(driver, centerX, centerY, 2);
		
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")).click();
		driver.findElement(AppiumBy.id("tv_todo_list_deadline")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
		        AppiumBy.accessibilityId("07 September 2025")
		));
		driver.findElement(AppiumBy.accessibilityId("07 September 2025")).click();
		driver.findElement(AppiumBy.id("bt_deadline_ok")).click();
		driver.findElement(AppiumBy.id("bt_new_task_ok")).click();
		
		String deadlineText = driver.findElement(AppiumBy.id("tv_exlv_task_deadline")).getText();
		
		Assert.assertEquals(deadlineText, "Deadline: 07.09.2025");
			
	}
	
	@Test(priority = 3)
	public void markAsComplete() {
		
		driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id='com.app.todolist:id/cb_task_done'])[1]")).click();
		driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id='com.app.todolist:id/cb_task_done'])[1]")).click();
		
		WebElement thirdTask = driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout[@resource-id=\"com.app.todolist:id/rl_exlv_task_group_root\"])[1]"));
		
		// Define finger input
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		int centerX = thirdTask.getLocation().getX() + (thirdTask.getSize().getWidth() / 2);
		int centerY = thirdTask.getLocation().getY() + (thirdTask.getSize().getHeight() / 2);

		// Build the sequence
		Sequence longPress = new Sequence(finger, 1)
		        .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
		        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
		        .addAction(new org.openqa.selenium.interactions.Pause(finger, Duration.ofSeconds(2))) // hold
		        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform action
		driver.perform(Arrays.asList(longPress));
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Edit To-Do Task']")).click();
		
		WebElement seekbar = driver.findElement(AppiumBy.id("sb_new_task_progress"));
		
		// Get SeekBar location & size
        int startX = seekbar.getLocation().getX();
        int y = seekbar.getLocation().getY() + (seekbar.getSize().getHeight() / 2);
        int barWidth = seekbar.getSize().getWidth();
		
        int targetX = startX + (int)(barWidth * 0.5);
        
        Sequence drag = new Sequence(finger, 1)
                // Move finger to start of SeekBar
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                // Drag to target position smoothly
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), targetX, y))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform
        driver.perform(Arrays.asList(drag));
        
        driver.findElement(AppiumBy.id("bt_new_task_ok")).click();
        
        driver.findElement(AppiumBy.accessibilityId("More options")).click();
        
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.xpath("//android.widget.TextView[@resource-id='com.app.todolist:id/title' and @text='Completed tasks']"))
        );
        
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='com.app.todolist:id/title' and @text='Completed tasks']")).click();
        
        List<WebElement> task = driver.findElements(AppiumBy.xpath("//android.widget.ExpandableListView[@resource-id='com.app.todolist:id/exlv_tasks']//android.widget.RelativeLayout"));	
		
		Assert.assertEquals(task.size(), 12);
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
