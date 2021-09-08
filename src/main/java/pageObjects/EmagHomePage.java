package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.PageObjectUtils;


public class EmagHomePage extends PageObjectUtils {

    public WebDriverWait wait;

    public EmagHomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    //Locators
    By searchBox = By.id("searchboxTrigger");
    By searchButton = By.xpath("//button[@class='btn btn-default searchbox-submit-button']");
    By acceptCookies = By.xpath("//div[contains(@class,'cookie-banner-buttons')]//button[contains(@class,'btn-primary')]");
    By closeModal = By.xpath("//button[@data-dismiss='modal']");
    By cart = By.id("my_cart");


    //Webelements
    public WebElement getSearchBox(){
        return driver.findElement(searchBox);
    }

    public WebElement getSearchButton(){
        return driver.findElement(searchButton);
    }

    public WebElement getCloseModal(){
        return driver.findElement(closeModal);
    }

    public WebElement getCart(){
        return driver.findElement(cart);
    }

    /**
     * WebElement Locator
     * @return  locator for accepting the cookies
     */
    public WebElement getButtonAcceptCookies() {
        return driver.findElement(acceptCookies);
    }

    public By addToCartByTag(String title){
        return By.xpath("//a[text()='" + title + "']//parent::*//parent::*//parent::*//button[@type='submit']");
    }

    public By addToCartByTitle(String title){
        return By.xpath("//a[@title='" + title + "']//parent::*//parent::*//parent::*//button[@type='submit']");
    }

    /**
     * WebElement Locator
     * @param title - name of your choice
     * @return webelement submit by title of the product
     */
    public WebElement getAddToCartByTitle(String title) {
        return driver.findElement(addToCartByTitle(title));
    }

    public WebElement getAddToCartByTag(String title) {
        return driver.findElement(addToCartByTag(title));
    }


    //Methods
    public void searchProduct(String text){
        this.getSearchBox().click();
        this.getSearchBox().sendKeys(text);
        this.getSearchButton().click();
    }

    public void setAcceptCookies() throws InterruptedException {
        if (getButtonAcceptCookies().isDisplayed()){
            clickForce(getButtonAcceptCookies(), 5);
        }
    }

    public void addToCartByName(String name) throws InterruptedException {


        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartByTitle(name)));
            clickForce(this.getAddToCartByTitle(name), 5);
        }catch (TimeoutException elementException){
            wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartByTag(name)));
            clickForce(this.getAddToCartByTag(name), 5);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(closeModal));
        clickForce(this.getCloseModal(), 5);
    }


    public void showCart(){
        this.getCart().click();
    }

}
