package pageObjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import testConfig.SeleniumHelper;

public class HotelBookingPO extends SeleniumHelper{
	
	@FindBy(xpath="//*[@id='Home']/div/div/ul/li/div/div[2]/aside[1]/nav/ul[1]/li[2]/a[1]/span")
	private WebElement hotelLink;
	
	@FindBy(id = "Tags")
    private WebElement localityTextBox;
	
	@FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
	
	@FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;
	
	@FindBy(xpath="//*[@id='ui-id-1']")
	private WebElement locationDropdown;
	
	public HotelBookingPO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickHotelTab(){
		hotelLink.click();
	}
	
	public void travellerSelection(String keys) {
		new Select(travellerSelection).selectByVisibleText(keys);
	}
	
	public void clickSearchButton(){
		searchButton.click();
	}
	
	public void locationDropdownClick(String keys){
		localityTextBox.sendKeys(keys);
		waitFor(5000);
		locationDropdown.click();
		List<WebElement> options = locationDropdown.findElements(By.className("list"));
    	for (WebElement option : options)
    	{
    	    if (option.getText().contentEquals(keys))
    	    {
    	        option.click(); // click the desired option
    	        break;
    	    }
    	}
	}

}
