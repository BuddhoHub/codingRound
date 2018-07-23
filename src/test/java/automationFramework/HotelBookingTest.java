package automationFramework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

import pageObjects.HotelBookingPO;

public class HotelBookingTest {
	
	WebDriver driver;
	
	@BeforeMethod(groups = { "P0" })
	public void driver()
	{
		driver = new ChromeDriver();
	}


	@BeforeTest
	public void setup(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.cleartrip.com/");
		setDriverPath();
	}

	@Test
	public void shouldBeAbleToSearchForHotels() {
		HotelBookingPO hotelBookingPO = PageFactory.initElements(driver, pageObjects.HotelBookingPO.class);

		hotelBookingPO.clickHotelTab();

		hotelBookingPO.locationDropdownClick("Indiranagar, Bangalore, Karnataka, India");

		//to click outside the date-picker frame
		driver.findElement(By.xpath("//*[@id='Home']/section/div/div/div[1]")).click();

		hotelBookingPO.travellerSelection("1 room, 2 adults");

		hotelBookingPO.clickSearchButton();

		waitFor(5000);

		//verify that result appears for the provided hotel search
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
