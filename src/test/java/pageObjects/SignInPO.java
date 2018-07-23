package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import testConfig.SeleniumHelper;

public class SignInPO extends SeleniumHelper{
	
	private static WebElement element = null;
	
	public SignInPO(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement lnk_YourTrips(){
		element = driver.findElement(By.linkText("Your trips"));
		return element;
	}
	
	public WebElement lnk_SignInLink(){
		element = driver.findElement(By.id("SignIn"));
		return element;
	}
    
	public WebElement lnk_SignInButton(){
		element = driver.findElement(By.id("signInButton"));
		return element;
	}
}
