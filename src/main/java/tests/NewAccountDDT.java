package tests;

import demos.DataReaders;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CSVParser;
import utilities.DriverFactory;

import java.io.File;
import java.util.List;

@RunWith(value = Parameterized.class)
public class NewAccountDDT {
    String name, email, phone, gender, psw, country;
    boolean weeklyEmail, monthlyEmail, occasionalEmail;
    WebDriver driver;
    WebElement nameElement , emailElement, phoneNumberElement;
    WebElement passwordElement, verifyPswElement, countryElement, femaleElement;
    WebElement maleElement, weeklyCheckBox, monthlyCheckBox, occasionalCheckBox, submitBtn;

    @Test
    public void newAccountTest(){
        System.out.println("NEW RECORD: " + name + " " + email + " "
                + phone + " " + gender + " " + psw + " " +country + " " + weeklyEmail
                + " " + monthlyEmail + " " +occasionalEmail);
//        if(phone.equals("7894561230")){
//            Assert.fail();
//        }

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ctl00$MainContent$txtFirstName")));
        defineWebElements();

        // 3. Fill out the form
        nameElement.sendKeys(name);
        emailElement.sendKeys(email);
        phoneNumberElement.sendKeys(phone); //rel Xpath
//        driver.findElement(By.cssSelector("input[id='MainContent_txtPassword']")).sendKeys("mspass");
        passwordElement.sendKeys(psw);
        verifyPswElement.sendKeys(psw);
        new Select(countryElement).selectByVisibleText(country);

        if (gender.equalsIgnoreCase("Male")) {
            maleElement.click();
        } else {
            femaleElement.click();
//            driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Female']")).click();
        }

        if(weeklyEmail){
            if(!weeklyCheckBox.isSelected()){
                weeklyCheckBox.click();
            }
        } else {
            if(weeklyCheckBox.isSelected()){
                weeklyCheckBox.click();
            }
        }

        if(monthlyEmail){
            if(!monthlyCheckBox.isSelected()){
                monthlyCheckBox.click();
            }
        } else {
            if(monthlyCheckBox.isSelected()){
                monthlyCheckBox.click();
            }
        }

        if(occasionalEmail){
            if(!occasionalCheckBox.isSelected()){
                occasionalCheckBox.click();
            }
        } else {
            if(occasionalCheckBox.isSelected()){
                occasionalCheckBox.click();
            }
        }

        submitBtn.click();
    }

    @Before
    public void setUp(){
        String webUrl = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";

        driver = DriverFactory.open("chrome");
        driver.get(webUrl);

        String createAccounttxt = "Create Account";
        driver.findElement(By.linkText(createAccounttxt.toUpperCase())).click();
    }


    public void defineWebElements(){
        nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
        emailElement = driver.findElement(By.id("MainContent_txtEmail"));
        phoneNumberElement = driver.findElement(By.xpath("//*[@id='MainContent_txtHomePhone']"));
        passwordElement = driver.findElement(By.cssSelector("input[type='password'][id='MainContent_txtPassword']"));
        verifyPswElement = driver.findElement(By.name("ctl00$MainContent$txtVerifyPassword"));
        countryElement = driver.findElement(By.id("MainContent_menuCountry"));
        femaleElement = driver.findElement(By.id("MainContent_Female"));
        maleElement = driver.findElement(By.name("ctl00$MainContent$Gender"));
        weeklyCheckBox = driver.findElement(By.name("ctl00$MainContent$checkWeeklyEmail"));
        monthlyCheckBox = driver.findElement(By.name("ctl00$MainContent$checkMonthlyEmail"));
        occasionalCheckBox = driver.findElement(By.name("ctl00$MainContent$checkUpdates"));
        submitBtn = driver.findElement(By.name("ctl00$MainContent$btnSubmit"));
    }


    @After
    public void tearDown(){
        driver.quit();
    }

    //This annotated method is designed to pass parameters into the class via constructor
    @Parameters
    public static List<String[]> getData(){
        File file = new File(DataReaders.class.getResource("/UserAccounts.csv").getFile());
        return CSVParser.get(file.getPath());
    }

    // Constructor that passes parameters to the test method
    public NewAccountDDT(String name, String email, String phone, String gender,
                         String psw, String country, String weeklyEmail, String monthlyEmail,
                         String occasionalEmail){

        this.name=name;
        this.email=email;
        this.phone=phone;
        this.gender=gender;
        this.psw=psw;
        this.country=country;
        if(weeklyEmail.equals("TRUE")){
            this.weeklyEmail=true;
        }else {
            this.weeklyEmail=false;
        }

        if(monthlyEmail.equals("TRUE")){
            this.monthlyEmail=true;
        }else {
            this.monthlyEmail=false;
        }

        if(occasionalEmail.equals("TRUE")){
            this.occasionalEmail=true;
        }else {
            this.occasionalEmail=false;
        }
    }
}
