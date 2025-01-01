package MyLearning.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MyLearning.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//Section[contains(@class,'ta-results')]//button[2]")
     WebElement resultCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By countryList = By.xpath("//Section[contains(@class,'ta-results')]");
	
	
	
	public void selectCountry(String CountryName) throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.sendKeys(country,CountryName).build().perform();
		waitElementToAppear(countryList);
		resultCountry.click();
		 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			scroll.executeScript("window.scrollBy(0,1200)");
			Thread.sleep(200);
		
	}
	
	public confirmPage submitOrder() {
		submit.click();
		return new confirmPage(driver);
	}

}
