package MyLearning.StepDefinitionImpl;

public class StepDefinition extends BaseTestComponents{
	
	@Given ("I landed on Ecommerce Page")
	public class I_landed_on_Ecommerce_Page() {
		launchPage();
	}
	
	
}