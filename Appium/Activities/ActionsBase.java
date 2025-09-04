package activities;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class ActionsBase {
	
	//set the pointer input type
	static PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
	
	//create the sequence of the swipe 
	public static void siwpe(AppiumDriver driver, Point start, Point end, int duration) {
		Sequence swipe = new Sequence (finger, 1).
		//need 4 actions 
		addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY())).
		addAction(finger.createPointerDown(LEFT.asArg())). //left click
		addAction(finger.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY())).
		addAction(finger.createPointerUp(LEFT.asArg()));
		
		//Perform the sequence
		driver.perform(Arrays.asList(swipe));
	}
}
