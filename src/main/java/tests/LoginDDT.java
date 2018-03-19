package tests;

import demos.DataReaders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.DriverFactory;
import utilities.ExcelParser;

import java.io.File;

public class LoginDDT {

    WebDriver driver;

    @Test(dataProvider = "getData")
    public void loginTest(String name, String email, String psw){
        System.out.println("NEW RECORD: "  + name + " " + email + " " + psw);

        driver.findElement(By.name("ctl00$MainContent$txtUserName")).sendKeys("tim@testemail.com");
        driver.findElement(By.name("ctl00$MainContent$txtPassword")).sendKeys("trpass");
        driver.findElement(By.name("ctl00$MainContent$btnLogin")).click();

        String conf_message = driver.findElement(By.id("conf_message")).getText();
        System.out.println("CONFIRMATION: " + conf_message);
    }

    @DataProvider
    public String[][] getData(){
        File file = new File(DataReaders.class.getResource("/UserLogin.xls").getFile());
        return ExcelParser.get(file.getPath());
    }

    @BeforeMethod
    public void setUp(){
        String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";

        driver = DriverFactory.open("chrome");
        driver.get(webUrl);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
