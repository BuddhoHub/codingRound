package pageObjects;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightBookingPO {	

	private static WebElement element = null;
	private static List<WebElement> listElement = null;
	private static String today = getCurrentDay();
	private static WebElement date = null;
	
	public static WebElement lnk_OneWayRadio(WebDriver driver){
		element = driver.findElement(By.id("OneWay"));
		return element;
	}

	public static WebElement lnk_FromTag(WebDriver driver){
		element = driver.findElement(By.id("FromTag"));
		return element;
	}

	public static WebElement lnk_ToTag(WebDriver driver){
		element = driver.findElement(By.id("ToTag"));
		return element;
	}

	public static List<WebElement> lnk_OriginOptions(WebDriver driver){
		listElement = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		return listElement;
	}

	public static List<WebElement> lnk_DestinationOptions(WebDriver driver){
		listElement = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		return listElement;
	}

	public static WebElement lnk_DatePicker(WebDriver driver){
		WebElement dateWidgetFrom = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody"));
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
		//Select Today's Date
		for (WebElement cell: columns) {
            if (cell.getText().equals(today)) {
                date = cell;
            }
        }
		//Wait for 4 Seconds to see Today's date selected.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return date;
	}

	public static WebElement lnk_SearchBtn(WebDriver driver){
		element = driver.findElement(By.id("SearchBtn"));
		return element;
	}
	
	//Get The Current Day
    private static String getCurrentDay (){
        //Create a Calendar Object
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
 
        //Get Current Day as a number
        int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
 
        //Integer to String Conversion
        String todayStr = Integer.toString(todayInt);
 
        return todayStr;
    }
}
