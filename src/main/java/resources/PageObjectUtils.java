package resources;

import org.openqa.selenium.*;

public class PageObjectUtils {

    public WebDriver driver;

    public PageObjectUtils(WebDriver driver){
        this.driver = driver;
    }

    public void clickForce(WebElement element , int times) throws InterruptedException {
        try {
            if (times > 0) {
                Thread.sleep(200);
                this.retryingFindClick(element, times);
            }
            else {
                throw new InterruptedException("Could not click element " + element);
            }
        } catch (ElementNotInteractableException e) {
            this.clickForce(element, times - 1);
        }
    }

    public void retryingFindClick(WebElement element, int times) throws InterruptedException {

        while(times>0) {
            try {
                if (times==1){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    break;
                }
                if(element.isDisplayed() && element.isEnabled())
                {
                    // Click on element if it is displayed and enabled
                    element.click();
                    break;
                }
                else {
                    times--;
                }
            } catch(StaleElementReferenceException e) {
                times--;
            }
        }
        if (!(times>0)) {
            throw new InterruptedException("Element " + element + " could not be clicked - tried 10 times");
        }
    }
}
