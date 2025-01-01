package MyLearning.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import MyLearning.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> Ordername;
	
	
	public Boolean verifyOrderToDisplay(String productName) {
		Boolean match = Ordername.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	


}
