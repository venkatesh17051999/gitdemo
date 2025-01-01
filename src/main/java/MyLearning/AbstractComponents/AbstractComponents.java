package MyLearning.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MyLearning.pageObject.CartPage;
import MyLearning.pageObject.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	WebDriverWait wait; 

	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[contains(@routerlink, 'cart')]")
	WebElement cart;
	@FindBy(xpath="//button[contains(@routerlink, 'myorders')]")
	WebElement order;
	
	public void waitElementToAppear(By FindBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
		
	}
	public void waitWebElementToAppear(WebElement FindBy) {

		wait.until(ExpectedConditions.visibilityOf(FindBy));
		
	}
	
	public void waitElementToDisappear(WebElement Ele) {
	
		wait.until(ExpectedConditions.invisibilityOf(Ele));
		
	}
	
	public CartPage goToCartPage() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		order.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}


}
