package smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverFactory;

import java.util.List;

public class ATagsTest {
    private WebDriver driver;

    @Test
    public void loginElementsPresentTest(){
        System.out.println("Running the test");
        boolean createAccountPresent = false;

        // We want to test the presence of A tags >> hyperlinks
        List<WebElement> aElements = driver.findElements(By.tagName("a"));

        int numberOfAElements = aElements.size();
        System.out.println("There are " + numberOfAElements + " a tags on the page");

        for(WebElement aElement:aElements){
            if(aElement.getText().equals("CREATE ACCOUNT")){
                createAccountPresent = true;
                break;
            }
        }

        Assert.assertTrue(createAccountPresent);
    }

    @BeforeMethod
    public void setUp(){
        System.out.println("Starting the test");
        String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";

        driver = DriverFactory.open("chrome");
        driver.get(webUrl);
    }

    @AfterMethod
    public void tearDown(){
        System.out.println("Closing the test");
        System.out.println("Closing the driver");
        driver.close();
    }
}
