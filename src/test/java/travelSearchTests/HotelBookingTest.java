package travelSearchTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.HotelBookingPO;
import testConfig.SeleniumHelper;

public class HotelBookingTest extends SeleniumHelper{

	@Test(groups = {"regression"})
	public void shouldBeAbleToSearchForHotels() {
		HotelBookingPO hotelBookingPO = new HotelBookingPO(driver);
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
}
