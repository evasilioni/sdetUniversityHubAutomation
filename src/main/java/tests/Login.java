package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.DriverFactory;


public class Login {
    // 1. Initialize driver

    WebDriver driver = DriverFactory.open("chrome");

    @Test
    public void loginTestPOM(){
        String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
        driver.get(webUrl);

        // 2. Enter login information (login page)
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName("tim@testemail.com");
        loginPage.setPassword("trpass");
        loginPage.clickSubmit();

        // 3.Get Confirmation (Dashboard page)
        DashboardPage dashboardPage = new DashboardPage(driver);
        String conf = dashboardPage.confirmationMessage();
        String title = dashboardPage.title();

        //Assertions
        Assert.assertTrue(conf.contains("success"));
        Assert.assertTrue(title.contains("Account"));

        // 4.Close the driver
        driver.quit();

    }

}
