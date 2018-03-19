package smoketests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

public class PageTitleJUnit {

    private WebDriver driver;
    private final String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";

    @Test
    public void pageTitleTest(){
        System.out.println("Running the test");
        driver.get(webUrl);

        String actualTitle = driver.getTitle();
        String expectedTitle = "SDET Training | Account Management";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Before
    public void setUp(){
        System.out.println("Setting up the test");
        System.out.println("Initializing the driver");
        driver = DriverFactory.open("chrome");
    }

    @After
    public void tearDown(){
        System.out.println("Closing the test");
        System.out.println("Closing the driver");
        driver.close();
    }
}
