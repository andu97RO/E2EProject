package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.base;

public class PrestoHome {
    public WebDriver driver;
    public static Logger log = LogManager.getLogger(base.class.getName());

    public PrestoHome(WebDriver driver) {
        this.driver = driver;
    }


    //Selectors
    private By suntDeAcordButton = By.xpath("//button[@class='terms-button']");
    private By loginButton = By.xpath("//a[@class='login']");
    private By username = By.id("username");
    private By password = By.xpath("//input[@id='password']");
    private By submit = By.xpath("//button[@type='submit']");
    private By continueButton = By.xpath("//button[@class='primary-button']");
    private By userText = By.xpath("//a[@class='username active-customer']");



    public By title = By.id("sdgBod");


    //WebElements
    public WebElement getSuntDeAcord(){
        return this.driver.findElement(suntDeAcordButton);
    }

    public WebElement getLogin(){
        return this.driver.findElement(loginButton);
    }

    public WebElement getUsername(){
        return this.driver.findElement(username);
    }

    public WebElement getPassword(){
        return this.driver.findElement(password);
    }

    public WebElement getSubmit(){
        return this.driver.findElement(submit);
    }

    public WebElement getContinue(){
        return this.driver.findElement(continueButton);
    }

    public WebElement getUserText(){
        return this.driver.findElement(userText);
    }


    //Methods
    public void clickCache(){
        this.getSuntDeAcord().click();
    }

    public void clickLogin(){
        this.getLogin().click();
    }

    public void fillUsername(String username){
        this.getUsername().sendKeys(username);
    }

    public void fillPassword(String password){
        this.getPassword().sendKeys(password);
    }

    public void clickSubmit(){
        this.getSubmit().click();
    }

    public void clickContinue(){
        this.getContinue().click();
    }

    public String getUserTextFromCard(){
        return this.getUserText().getText();
    }




}
