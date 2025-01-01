package MyLearning.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import MyLearning.AbstractComponents.AbstractComponents;

public class confirmPage extends AbstractComponents {
	
	WebDriver driver;

	public confirmPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
	}
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String getvalidateMessage()
	{
		return message.getText();
	}

}
