import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage extends PageBase{

	String blogname = "bejev72128";		// Please Change this value to the same value from SeleniumTest.java line 32
	private JavascriptExecutor js = (JavascriptExecutor)driver;
	private final By newPost = By.xpath("//button[@aria-label='Text']");
    private final By blogTitle = By.cssSelector("div[data-subview='postTypeForm'] div[data-name='title'] .editor-wrapper .editor-slot div[aria-label='Post title']");
    private final By blogBody = By.cssSelector("div[data-subview='postTypeForm'] div[data-name='body'] .editor-wrapper .editor-slot div[aria-label='Post body']");
    private final By postBtn = By.cssSelector(".control.right .split-button button");
    private final By profileIcon = By.xpath("//button[@aria-label='Account']");
    private final By blogPage = By.xpath("//a[@href='/blog/" + blogname + "']");
    private final By logoutBtn = By.xpath("//button[@aria-label='Log out']");
    private final By confirmLogoutBtn = By.xpath("//button[@aria-label='OK']");
    private final By agreeBtn = By.xpath("//button[@class='components-button white-space-normal is-primary']");

	public MainPage(WebDriver driver){
		super(driver);
	}

	public void postNewBlog(){		
        WebElement newPostElement = this.waitVisibiltyAndFindElement(newPost);
		newPostElement.click();

		System.out.println("waiting 5 seconds for new blog popup model to load");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Post forms']")));
			
		
        WebElement blogTitleElement = this.waitVisibiltyAndFindElement(blogTitle);
		blogTitleElement.sendKeys("Selenium Post \n");
		
        WebElement blogBodyElement = this.waitVisibiltyAndFindElement(blogBody);
		blogBodyElement.sendKeys("This blog is being generated through a selenium webdriver testing tool \nWith new lines... \nNice huh?");

        WebElement postBtnElement = this.waitVisibiltyAndFindElement(postBtn);		
		postBtnElement.click();		
		
        System.out.println("mainPage.postNewBlog() --> completed");
		
	}

	public void checkPreviouslyCreatedBlog(){		
		driver.switchTo().defaultContent();
        System.out.println("wait 5 seconds to switch back to default driver from the new blog popup iframe");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
		
        WebElement profileIconElement = this.waitVisibiltyAndFindElement(profileIcon);
		profileIconElement.click();
		
        WebElement blogPageElement = this.waitVisibiltyAndFindElement(blogPage);
		blogPageElement.click();
		
		System.out.println("waiting 5 seconds to view the previously created blog from the user's profile");
		try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);} 	
		
        System.out.println("mainPage.checkPreviouslyCreatedBlog() --> completed");
	}
	
	/*
	public String getPageTitle(){		
		JavascriptExecutor js =(JavascriptExecutor)(this.driver);
		String pageTitle = js.executeScript("return document.title;").toString();
		return pageTitle;
	}
	*/
	
	public String getPageTitle(){	
        System.out.println("mainPage.getPageTitle() =  --> completed");
		return ((JavascriptExecutor)(this.driver)).executeScript("return document.title;").toString();
	}
	
	public void logout(){	
        WebElement profileIconElement = this.waitVisibiltyAndFindElement(profileIcon);
		profileIconElement.click();
		
        WebElement logoutBtnElement = this.waitVisibiltyAndFindElement(logoutBtn);
		logoutBtnElement.click();
		
        WebElement confirmLogoutBtnElement = this.waitVisibiltyAndFindElement(confirmLogoutBtn);
		confirmLogoutBtnElement.click();
		
        System.out.println("switching driver focus to the popup iframe element after logging out");
		try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);} 	
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.components-modal__frame iframe")));
		
		WebElement agreeElement = this.waitVisibiltyAndFindElement(agreeBtn);
		agreeElement.click();
		
        System.out.println("mainPage.logout() --> completed");
	}
}
