package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPO{

	WebDriver driver;
	
	private static WebElement element = null;
	
	public static WebElement lnk_YourTrips(WebDriver driver){
		element = driver.findElement(By.linkText("Your trips"));
		return element;
	}
	
	public static WebElement lnk_SignInLink(WebDriver driver){
		element = driver.findElement(By.id("SignIn"));
		return element;
	}
    
	public static WebElement lnk_SignInButton(WebDriver driver){
		element = driver.findElement(By.id("signInButton"));
		return element;
	}
}
