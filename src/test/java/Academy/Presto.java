package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.PrestoHome;
import resources.base;

import java.io.IOException;

public class Presto extends base {

    public WebDriver driver;
    public PrestoHome prestoHome;
    public static Logger log = LogManager.getLogger(base.class.getName());

    @BeforeTest
    public void initialize() throws IOException {

        driver = initializeDriver();
        log.info("Driver is initialized");

        driver.get(prop.getProperty("url"));
        log.info("Navigated to Home page");

        driver.manage().window().maximize();
    }

    @Test
    public void TestPresto() throws InterruptedException {

        prestoHome = new PrestoHome(driver);

        Thread.sleep(3000);

        prestoHome.clickCache();
        log.info("Clicked on the cookie pop-up");

        prestoHome.clickLogin();
        log.info("Clicked on login button");

        prestoHome.fillUsername("");
        log.info("Filled the username");

        prestoHome.fillPassword("");
        log.info("Filed the password");

        prestoHome.clickSubmit();
        log.info("Clicked on submit");

        Thread.sleep(2000);
        prestoHome.clickContinue();

        Thread.sleep(2000);
        prestoHome.clickContinue();

        Thread.sleep(2000);
        Assert.assertEquals(prestoHome.getUserTextFromCard(), "Andrei");

    }

    @AfterTest
    public void teardown() {

//        driver.close();
    }


}
