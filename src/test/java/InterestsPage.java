import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;

public class InterestsPage extends PageBase{
	
    private final By interest1 = By.cssSelector("div[data-name='quotes']");
    private final By interest2 = By.cssSelector("div[data-name='comics']");
    private final By interest3 = By.cssSelector("div[data-name='travel']");
    private final By interest4 = By.cssSelector("div[data-name='television']");
    private final By interest5 = By.cssSelector("div[data-name='developers & startups']");
    private final By interest6 = By.cssSelector("div[data-name='student']");
    private final By interest7 = By.cssSelector("div[data-name='anime']");
    private final By interest8 = By.cssSelector("div[data-name='gaming']");
    private final By nextBtn = By.xpath("//button[@class='onboarding-progress-button']");
	
	public InterestsPage(WebDriver driver){
		super(driver);
	}
	
	public void selectInterests(){
        WebElement interest1Element = this.waitVisibiltyAndFindElement(interest1);
		interest1Element.click();
		
        WebElement interest2Element = this.waitVisibiltyAndFindElement(interest2);
		interest2Element.click();
		
        WebElement interest3Element = this.waitVisibiltyAndFindElement(interest3);
		interest3Element.click();
		
        WebElement interest4Element = this.waitVisibiltyAndFindElement(interest4);
		interest4Element.click();
		
        WebElement interest5Element = this.waitVisibiltyAndFindElement(interest5);
		interest5Element.click();
		
        WebElement interest6Element = this.waitVisibiltyAndFindElement(interest6);
		interest6Element.click();
		
        WebElement interest7Element = this.waitVisibiltyAndFindElement(interest7);
		interest7Element.click();
		
        WebElement interest8Element = this.waitVisibiltyAndFindElement(interest8);
		interest8Element.click();
		
        WebElement nextBtnElement = this.waitVisibiltyAndFindElement(nextBtn);
		nextBtnElement.click();
		
        System.out.println("interestsPage.selectInterests() --> completed");
	}
}