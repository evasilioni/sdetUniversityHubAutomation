package demos;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

public class NewAccount {
    public static void main(String[] args) {
        String name = "Mary Smith";
        String email = "ms@testemail.com";
        String pswd = "mspass";
        String phoneNumber = "1231231234";
        String country = "Germany";
        String gender = "Female";

        boolean weeklyEmail = true;
        boolean monthlyEmail = false;
        boolean occassionalUpdate = false;

        WebDriver driver = DriverFactory.open("chrome");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // 2. Open browser to Account Management Page  >> Click on create account
        driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");

        String createAccounttxt = "Create Account";
        driver.findElement(By.linkText(createAccounttxt.toUpperCase())).click();
//        driver.findElement(By.linkText(createAccounttxt)).click(); //for FF

        WebElement nameElement = driver.findElement(By.name("ctl00$MainContent$txtFirstName"));
        WebElement emailElement = driver.findElement(By.id("MainContent_txtEmail"));
        WebElement phoneNumberElement = driver.findElement(By.xpath("//*[@id='MainContent_txtHomePhone']"));
        WebElement passwordElement = driver.findElement(By.cssSelector("input[type='password'][id='MainContent_txtPassword']"));
        WebElement verifyPswElement = driver.findElement(By.name("ctl00$MainContent$txtVerifyPassword"));
        WebElement countryElement = driver.findElement(By.id("MainContent_menuCountry"));
        WebElement femaleElement = driver.findElement(By.id("MainContent_Female"));
        WebElement maleElement = driver.findElement(By.name("ctl00$MainContent$Gender"));
        WebElement weeklyCheckBox = driver.findElement(By.name("ctl00$MainContent$checkWeeklyEmail"));
        WebElement submitBtn = driver.findElement(By.name("ctl00$MainContent$btnSubmit"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ctl00$MainContent$txtFirstName")));
        // 3. Fill out the form
        nameElement.sendKeys(name);
        emailElement.sendKeys(email);
        phoneNumberElement.sendKeys(phoneNumber); //rel Xpath
//        driver.findElement(By.cssSelector("input[id='MainContent_txtPassword']")).sendKeys("mspass");
        passwordElement.sendKeys(pswd);
        verifyPswElement.sendKeys(pswd);
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

        submitBtn.click();
        //4. Get confirmation
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent_lblTransactionResult")));
        String conf = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
        String expected = "Customer information added successfully";
        if(conf.contains(expected)){
            System.out.println("CONFIRAMTION :" + conf);
        }else {
            System.out.println("TEST FAILED");
        }

        //Customer information added successfully
        driver.close();
    }

}
