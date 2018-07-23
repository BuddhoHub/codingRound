package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

import pageObjects.FlightBookingPO;



public class FlightBookingTest {
	
	public static WebDriver driver = new ChromeDriver();

	/*WebDriver driver;
	
	@BeforeMethod(groups = { "P0" })
	public void driver()
	{
		public static WebDriver driver = new ChromeDriver();
	}*/
	@BeforeTest
	public void setup(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/");
		setDriverPath();
	}

	@Test(groups = { "P0", "regression"})
	public void testThatResultsAppearForAOneWayJourney() {

		waitFor(2000);
		FlightBookingPO.lnk_OneWayRadio(driver).click();

		FlightBookingPO.lnk_FromTag(driver).clear();
		FlightBookingPO.lnk_FromTag(driver).sendKeys("Bangalore");

		//wait for the auto complete options to appear for the origin
		waitFor(2000);

		FlightBookingPO.lnk_OriginOptions(driver).get(0).click();

		FlightBookingPO.lnk_ToTag(driver).clear();
		FlightBookingPO.lnk_ToTag(driver).sendKeys("Delhi");

		//wait for the auto complete options to appear for the destination
		waitFor(2000);

		//select the first item from the destination auto complete list
		FlightBookingPO.lnk_DestinationOptions(driver).get(0).click();

		FlightBookingPO.lnk_DatePicker(driver).click();

		//all fields filled in. Now click on search
		FlightBookingPO.lnk_SearchBtn(driver).click();

		waitFor(5000);
		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));
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


	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
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
