package MyLearning.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import MyLearning.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productBy = By.cssSelector(".mb-3 ");
	By addToCart = By.cssSelector("button:last-Child");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getproductList() {
		waitElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		
		WebElement prod = products.stream().filter(
				product->product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitElementToAppear(toastMessage);
		waitElementToDisappear(spinner);
		
		
	}
	
	

}
