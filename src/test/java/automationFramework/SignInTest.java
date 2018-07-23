package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

import pageObjects.SignInPO;

public class SignInTest {
	
	WebDriver driver;
	
	@BeforeMethod(groups = { "P0" })
	public void driver()
	{
		driver = new ChromeDriver();
	}
	
	public void setup(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/");
		setDriverPath();
	}

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        SignInPO.lnk_YourTrips(driver).click();
        SignInPO.lnk_SignInLink(driver).click();
        
        driver.switchTo().frame("modal_window");
        SignInPO.lnk_SignInButton(driver).click();
        
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
    }
    
    @AfterTest
    public void closeWindow(){
    	driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}
