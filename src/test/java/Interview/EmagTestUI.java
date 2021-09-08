package Interview;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.EmagCartPage;
import pageObjects.EmagHomePage;
import resources.base;

import java.io.IOException;

public class EmagTestUI extends base {

    public WebDriver driver;
    public EmagHomePage emagHomePage;
    public EmagCartPage emagCartPage;


    String product = "apple watch";
    String completeNameProduct1 = "Apple Watch 3, GPS, Carcasa Space Grey Aluminium 38mm, Black Sport Band";
    String completeNameProduct2 = "Apple Watch 6, GPS, Carcasa Gold Aluminium 40mm, Pink Sand Sport Band";

    public static Logger log = LogManager.getLogger(EmagTestUI.class.getName());

    @BeforeSuite
    public void initialize() throws IOException {

        driver = initializeDriver();
        log.info("Driver is initialized");

        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        log.info("Navigated to url page");
    }

    @Test
    public void test_1() throws InterruptedException {
        emagHomePage = new EmagHomePage(driver);
        emagCartPage = new EmagCartPage(driver);

        emagHomePage.setAcceptCookies();
        emagHomePage.searchProduct(product);
        emagHomePage.addToCartByName(completeNameProduct1);
        emagHomePage.addToCartByName(completeNameProduct2);
        emagHomePage.showCart();

        Assert.assertTrue(emagCartPage.productsInCart(completeNameProduct1), "The product is not displayed" + completeNameProduct1);
        Assert.assertTrue(emagCartPage.productsInCart(completeNameProduct2), "The product is not displayed" + completeNameProduct2);
        log.info("Test 1 - Successfully add two products to cart and assert that the operation is successful");
    }

    @Test
    public void test_2(){
        emagHomePage = new EmagHomePage(driver);
        emagCartPage = new EmagCartPage(driver);

        emagCartPage.deleteProductByTitle(completeNameProduct2);
        Assert.assertTrue(emagCartPage.productsInCart(completeNameProduct1), "The product is not displayed" + completeNameProduct1);
        Assert.assertFalse(emagCartPage.productsInCart(completeNameProduct2), "The product is displayed" + completeNameProduct2);
        log.info("Test 2 - * Remove successfully one of them and assert that the result is correct");

    }

    @Test
    public void test_3(){
        emagHomePage = new EmagHomePage(driver);
        emagCartPage = new EmagCartPage(driver);

        emagCartPage.deleteProductByTitle(completeNameProduct1);

        Assert.assertFalse(emagCartPage.productsInCart(completeNameProduct1), "The product is displayed" + completeNameProduct1);
        Assert.assertFalse(emagCartPage.productsInCart(completeNameProduct2), "The product is displayed" + completeNameProduct2);
        log.info("Test 3 - * Remove successfully the remaining one and assert that the result is correct");
    }

    @Test
    public void test_4(){
        emagHomePage = new EmagHomePage(driver);
        emagCartPage = new EmagCartPage(driver);

        emagCartPage.deleteProductByTitle(completeNameProduct1);
        log.info("Test 4 - * Try to remove again one of the products expecting a failed test in this case");
    }

    @AfterSuite
    public void teardown() {
        driver.close();
    }


}
