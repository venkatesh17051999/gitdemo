package MyLearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import MyLearning.BaseTest.BaseTestComponents;
import MyLearning.pageObject.CartPage;
import MyLearning.pageObject.ProductCatalogue;




public class ErrorValidationsTest extends BaseTestComponents {
    @Test(groups= {"errorhandling"})
	public void LoginErrorValidation() throws InterruptedException, IOException {
    	
		
		ProductCatalogue productCatalogue =landingPage.login("venkytest@gmail.com", "Test@12");
		Assert.assertEquals("Incorrect email or password.",landingPage.errorMessage());	
		//Assert.assertEquals("Incorrect email  password.",landingPage.errorMessage());	
		
	}
    @Test
   	public void ProductNameValidation() throws InterruptedException, IOException {
       	
   		String productName = "IPHONE 13 PRO";
   		ProductCatalogue productCatalogue =landingPage.login("venky123@gmail.com", "Test@1617");
   		List<WebElement> products = productCatalogue.getproductList();
   		productCatalogue.getProductByName(productName);
   		productCatalogue.addProductToCart(productName);
   		CartPage cartPage = productCatalogue.goToCartPage();
   		Boolean match = cartPage.verifyProductToDisplay(productName); 
   		Assert.assertTrue(match);
   		
   		
   	}


}
