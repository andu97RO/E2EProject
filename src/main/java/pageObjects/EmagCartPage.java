package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.PageObjectUtils;

public class EmagCartPage extends PageObjectUtils {
    public WebDriverWait wait;


    public EmagCartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    //locator
    By cartTitle = By.xpath("//h1[@class='cart']");


    public By productInCart(String name){
        return By.xpath("//a[@title='" + name + "']");
    }

    public By deleteProduct(String name){
        return By.xpath("//div[@class='line-item line-item-main']//a[@title='" + name + "']//parent::*//parent::*//a[@class='emg-right remove-product btn-remove-product gtm_rp080219']");
    }


    //WebElements
    public WebElement showProducts(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        return driver.findElement(productInCart(name));
    }

    public WebElement getDeleteProduct(String name){
        return driver.findElement(this.deleteProduct(name));
    }

    public WebElement getCartTitle(){
        return driver.findElement(cartTitle);
    }


    //Methods
    public void deleteProductByTitle(String titleOfProduct){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        this.getDeleteProduct(titleOfProduct).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteProduct(titleOfProduct)));
    }

    public boolean productsInCart(String name){
        try {
            this.showProducts(name).isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }catch( TimeoutException timeoutException){
            timeoutException.printStackTrace();
            return false;
        }
    }

}
