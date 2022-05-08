import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class RegistrationSteps extends PageBase{
	
    private final By agreeBtn = By.xpath("//button[@class='components-button white-space-normal is-primary']");
    private final By signUpPopup = By.xpath("//a[@href='/register?source=login_register_header_explore_homepage_explore']");
    private final By emailField = By.xpath("//input[@name='email']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By nameField = By.xpath("//input[@name='blogName']");
    private final By ageBtn = By.xpath("//input[@name='age']");
    private final By submitBtn = By.xpath("//button[@type='submit']");
    private final By submitAgeBtn = By.xpath("//button[@class='TRX6J CxLjL qjTo7 CguuB yC5pj']");

	public RegistrationSteps(WebDriver driver){
		super(driver);
		this.driver.get("http://tumblr.com/");
	}
	
	public void acceptPrivacy(){
        System.out.println("wait 2 seconds to focus on the frames and load Agree button");
		try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);} 
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.components-modal__frame iframe")));

		WebElement agreeElement = this.waitVisibiltyAndFindElement(agreeBtn);
		agreeElement.click();
		
        System.out.println("registrationSteps.acceptPrivacy() --> completed");
	}
	
	public void signup(String email, String pass, String blogname){
		driver.switchTo().defaultContent();
		
        WebElement signUpPopupElement = this.waitVisibiltyAndFindElement(signUpPopup);
		signUpPopupElement.click();
		
        WebElement emailFieldElement = this.waitVisibiltyAndFindElement(emailField);
		emailFieldElement.sendKeys(email + " \n");
		
        WebElement passwordFieldElement = this.waitVisibiltyAndFindElement(passwordField);
		passwordFieldElement.sendKeys(pass + " \n");
		
		// blogname unique
        WebElement nameFieldElement = this.waitVisibiltyAndFindElement(nameField);
		nameFieldElement.sendKeys(blogname + " \n");
		
        WebElement submitBtnElement = this.waitVisibiltyAndFindElement(submitBtn);
		submitBtnElement.click();
		
        System.out.println("registrationSteps.signup(email, pass, blogname) --> completed");
	}
	
	public void insertAge(){
        WebElement ageBtnElement = this.waitVisibiltyAndFindElement(ageBtn);
		ageBtnElement.sendKeys("25");
		
        WebElement submitAgeBtnElement = this.waitVisibiltyAndFindElement(submitAgeBtn);
		submitAgeBtnElement.click();
		
        System.out.println("registrationSteps.insertAge() --> completed");
	}
}
