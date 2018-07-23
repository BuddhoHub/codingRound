package automationFramework;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.SignInPO;
import testConfig.SeleniumHelper;

public class SignInTest extends SeleniumHelper{

    @Test(groups = {"regression"})
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	SignInPO SignInPO = new SignInPO(driver);
        SignInPO.lnk_YourTrips().click();
        SignInPO.lnk_SignInLink().click();
        
        driver.switchTo().frame("modal_window");
        SignInPO.lnk_SignInButton().click();
        
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
    }
    
    @AfterTest
	public void navigateToHomePage(){
		driver.findElement(By.xpath("//*[@id='GlobalNav']/div/div[1]/a/span")).click();
	}

}
