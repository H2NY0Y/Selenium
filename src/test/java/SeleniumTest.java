import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    private WebElement waitVisibiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void seleniumTest() {
		
		// Please change the following variables according to their names
		String email = "retete6854@abincol.com";
		String pass = "i46uxK*dwm@23#I";
		String blogname = "retete6854";		// Please also change the blogname value in MainPage.java line 10 to the same value you added here
		// Using VALID fake credit card information for testing
		String creditCardNumber = "4263982640269299";
		String ExpirationDate = "02 / 23";
		String cvcNumber = "837";
		
		
		RegistrationSteps registrationSteps = new RegistrationSteps(this.driver);
		registrationSteps.acceptPrivacy();
		
        System.out.println("wait 5 seconds for to switch frames and load new page elements");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 
		
		registrationSteps.signup(email, pass, blogname);
		registrationSteps.insertAge();	
		
        System.out.println("wait 10 seconds for new page elements to load");
		try{Thread.sleep(10000);}catch(InterruptedException e){System.out.println(e);} 
		
		InterestsPage interestsPage = new InterestsPage(this.driver);		
		interestsPage.selectInterests();
		
		MainPage mainPage = new MainPage(this.driver);  
		
        System.out.println("wait 5 seconds for redirection");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}
		
		mainPage.postNewBlog();
		mainPage.checkPreviouslyCreatedBlog();
        System.out.println("Page title = " + mainPage.getPageTitle());
		
		SubscriptionSteps subscriptionSteps = new SubscriptionSteps(this.driver);
		
		subscriptionSteps.goAddFree();
		subscriptionSteps.selectSubscription();
		
        System.out.println("wait 5 seconds for the billing form to load");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 
		
		subscriptionSteps.fillBillingForm(creditCardNumber, ExpirationDate, cvcNumber);
		
        System.out.println("wait 2 seconds before logout()");
		try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);} 
		
		mainPage.logout();
    }
    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
