package MyLearning;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import MyLearning.BaseTest.BaseTestComponents;
import MyLearning.pageObject.CartPage;
import MyLearning.pageObject.CheckoutPage;
import MyLearning.pageObject.OrderPage;
import MyLearning.pageObject.ProductCatalogue;
import MyLearning.pageObject.confirmPage;




public class SubmitOrderTest extends BaseTestComponents {
	
	String productName = "IPHONE 13 PRO";
    @Test(dataProvider = "getdata",groups= {"purchase"})
	public void SubmitOrder(HashMap<String,String> input ) throws InterruptedException, IOException {
    	
		
		String CountryName = "India";

		ProductCatalogue productCatalogue =landingPage.login(input.get("email"),input.get("password") );
		List<WebElement> products = productCatalogue.getproductList();
		productCatalogue.getProductByName(input.get("productname"));
		productCatalogue.addProductToCart(input.get("productname"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductToDisplay(input.get("productname")); 
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry(CountryName);
		confirmPage confirmpage = checkoutpage.submitOrder();
		String confirmMessage = confirmpage.getvalidateMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
    @Test(dependsOnMethods="SubmitOrder")
    public void verifyorder() {
    	
    	ProductCatalogue productCatalogue =landingPage.login("venkytest@gmail.com", "Test@123");
    	OrderPage orderpage = productCatalogue.goToOrderPage();
    	Assert.assertTrue(orderpage.verifyOrderToDisplay(productName));
    	
    	
    }
    @DataProvider
    public Object[][] getdata() throws IOException {
    	List<HashMap<String,String>> data = getjsondatatomap(System.getProperty("user.dir")+"\\src\\test\\java\\MyLearning\\data\\purchaseOrder.json");
    	
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    }
    
	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "venkytest@gmail.com"); map.put("password", "Test@123");
	 * map.put("productname", "IPHONE 13 PRO"); HashMap<String,String> map1 = new
	 * HashMap<String,String>(); map1.put("email", "venky123@gmail.com");
	 * map1.put("password", "Test@1617"); map1.put("productname",
	 * "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map},{map1}};
	 */

}
