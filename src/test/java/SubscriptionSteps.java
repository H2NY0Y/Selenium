import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class SubscriptionSteps extends PageBase{
	
	private final By profileIcon = By.xpath("//button[@aria-label='Account']");
    private final By goAddFreeBtn = By.xpath("//a[@href='/settings/ad-free-browsing?source=settings']");
    private final By btn39 = By.xpath("//button[@aria-label='$39.99/year']");
    private final By lastName = By.xpath("//input[@name='billing_last_name']");
    private final By firstName = By.xpath("//input[@name='billing_first_name']");
    private final By countrySelect = By.id("billing_country");
    private final By postCode = By.xpath("//input[@name='billing_postcode']");
    private final By stateSelect = By.id("billing_state");
    private final By cardNumber = By.xpath("//input[@name='cardnumber']");
    private final By expDate = By.xpath("//input[@name='exp-date']");
    private final By cardCVC = By.xpath("//input[@name='cvc']");
    private final By payBtn = By.xpath("//button[@type='submit']");
    private final By errorMsg = By.cssSelector("ul[role='alert'] li");	
	
	public SubscriptionSteps(WebDriver driver){
		super(driver);
	}
	
	public void goAddFree(){
        WebElement profileIconElement = this.waitVisibiltyAndFindElement(profileIcon);
		profileIconElement.click();

        WebElement goAddFreeBtnElement = this.waitVisibiltyAndFindElement(goAddFreeBtn);
		goAddFreeBtnElement.click();

        System.out.println("subscriptionSteps.goAddFree() --> completed");
	}
	
	public void selectSubscription(){
        WebElement btn39Element = this.waitVisibiltyAndFindElement(btn39);
		btn39Element.click();

        System.out.println("subscriptionSteps.selectSubscription() --> completed");
	}	
	
	public void fillBillingForm(String creditCardNumber, String ExpirationDate, String cvcNumber){
        WebElement lastNameElement = this.waitVisibiltyAndFindElement(lastName);
		lastNameElement.sendKeys("Doh \n");
		
        WebElement firstNameElement = this.waitVisibiltyAndFindElement(firstName);
		firstNameElement.sendKeys("John \n");
		
		Select countrySelectElement = new Select(this.driver.findElement(countrySelect));
		countrySelectElement.selectByVisibleText("Hungary");
		
		WebElement postCodeElement = this.waitVisibiltyAndFindElement(postCode);
		postCodeElement.sendKeys("1068 \n");
		
		Select stateSelectElement = new Select(this.driver.findElement(stateSelect));
		stateSelectElement.selectByVisibleText("Budapest");
        
        System.out.println("switching selenium focus to the iframe card detail information");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@allow='payment *']")));
		
		WebElement cardNumberElement = this.waitVisibiltyAndFindElement(cardNumber);
		cardNumberElement.sendKeys(creditCardNumber + " \n");
		
        WebElement expDateElement = this.waitVisibiltyAndFindElement(expDate);
		expDateElement.sendKeys(ExpirationDate + " \n");
		
        WebElement cardCVCElement = this.waitVisibiltyAndFindElement(cardCVC);
		cardCVCElement.sendKeys(cvcNumber);		
		
		driver.switchTo().defaultContent();
		System.out.println("waiting 5 seconds for card details to be verified");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
			
        WebElement payBtnElement = this.waitVisibiltyAndFindElement(payBtn);
		payBtnElement.click();
        
        System.out.println("waiting 5 seconds to recieve the respons from the bank's iframe submitted info");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
		
		WebElement errorMsgElement = waitVisibiltyAndFindElement(errorMsg);
		Assert.assertTrue(errorMsgElement.getText().contains("Error: Your card was declined."));
		
        System.out.println("-----" + errorMsgElement.getText() + "-----");
		
        System.out.println("going back to browser's previous page");
		driver.navigate().back();
		
        System.out.println("subscriptionSteps.fillBillingForm(creditCardNumber, ExpirationDate, cvcNumber) --> completed");
	}
}
