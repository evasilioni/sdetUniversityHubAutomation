package stepImplementations;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.DriverFactory;

public class BDDLoginTest {

    WebDriver driver;

    /*To Run this we need a Runner
    * here is the glue code */

    //Preconditions
    @Given("^user is on the login page$")
    public void user_is_on_the_login_page(){
        System.out.println("User is on the login page");
        //open the driver
        driver = DriverFactory.open("chrome");
        //navigate to login page
        driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");
    }

    //Actions
    @When("^user enters correct username and correct password$")
    public void userEntersCorrectUsernameAndCorrectPassword() throws Throwable {
        System.out.println("user enters correct username and password");
        driver.findElement(By.id("MainContent_txtUserName")).sendKeys("tim@testemail.com");
        driver.findElement(By.id("MainContent_txtPassword")).sendKeys("trpass");
        driver.findElement(By.id("MainContent_btnLogin")).click();
    }

    //Assertions
    @Then("^user gets confirmation$")
    public void userGetsConfirmation() throws Throwable {
        System.out.println("user gets confirmation");
        Assert.assertTrue(driver.findElement(By.id("conf_message")).getText().contains("success"));
    }

    @When("^user enters email (.*)$")
    public void userEntersUsername(String username) throws Throwable {
        System.out.println("TESTING: " + username);
        driver.findElement(By.id("MainContent_txtUserName")).sendKeys(username);
    }

    @And("^user enters password (.*)$")
    public void userEntersPassword(String password) throws Throwable {
        System.out.println("TESTING: " + password);
        driver.findElement(By.id("MainContent_txtPassword")).sendKeys(password);
    }

    @And("^user clicks login button$")
    public void clicks_login() throws Throwable {
        driver.findElement(By.id("MainContent_btnLogin")).click();

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
