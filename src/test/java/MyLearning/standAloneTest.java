package MyLearning;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("venkytest@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3 ")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3 "));
		WebElement prod = products.stream().filter(
				product->product.findElement(By.tagName("b")).getText().equalsIgnoreCase("IPHONE 13 PRO")).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-Child")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("//button[contains(@routerlink, 'cart')]")).click();
		
		List<WebElement> prodname = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		Boolean match = prodname.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Section[contains(@class,'ta-results')]")));
		 driver.findElement(By.xpath("//Section[contains(@class,'ta-results')]//button[2]")).click();
		 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			scroll.executeScript("window.scrollBy(0,1200)");
			Thread.sleep(200);
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
	}

}
