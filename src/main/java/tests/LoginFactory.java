package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPageFactory;
import utilities.DriverFactory;

public class LoginFactory {

    String username = "tim@testemail.com";
    String password = "trpass";

    @Test
    public void loginTestPageFactory(){
        WebDriver driver = DriverFactory.open("chrome");
        String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
        driver.get(webUrl);

        // 2. Enter login
        LoginPageFactory loginPage = new LoginPageFactory(driver);
        loginPage.login(username, password);

        // 4.Close the driver
        driver.quit();
    }
}
