package MyLearning.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



import MyLearning.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	
	}
	
	@FindBy(xpath="//div[@class='cartSection']//h3")
	List<WebElement> prodname;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductToDisplay(String productName) {
		Boolean match = prodname.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}

}
