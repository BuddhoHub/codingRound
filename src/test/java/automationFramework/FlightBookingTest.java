package automationFramework;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.FlightBookingPO;
import testConfig.SeleniumHelper;



public class FlightBookingTest extends SeleniumHelper{
	
	@Test(groups = {"regression"})
	public void testThatResultsAppearForAOneWayJourney() {
		FlightBookingPO flightBookingPO = new FlightBookingPO(driver);
		waitFor(2000);
		flightBookingPO.lnk_OneWayRadio().click();

		flightBookingPO.lnk_FromTag().clear();
		flightBookingPO.lnk_FromTag().sendKeys("Bangalore");

		//wait for the auto complete options to appear for the origin
		waitFor(2000);

		flightBookingPO.lnk_OriginOptions().get(0).click();

		flightBookingPO.lnk_ToTag().clear();
		flightBookingPO.lnk_ToTag().sendKeys("Delhi");

		//wait for the auto complete options to appear for the destination
		waitFor(2000);

		//select the first item from the destination auto complete list
		flightBookingPO.lnk_DestinationOptions().get(0).click();

		flightBookingPO.lnk_DatePicker().click();

		//all fields filled in. Now click on search
		flightBookingPO.lnk_SearchBtn().click();

		waitFor(5000);
		//verify that result appears for the provided journey search
		Assert.assertTrue(isElementPresent(By.className("searchSummary")));
	}

	@AfterTest
	public void navigateToHomePage(){
		driver.findElement(By.xpath("//*[@id='GlobalNav']/div/div[1]/a/span")).click();
	}


	
}
