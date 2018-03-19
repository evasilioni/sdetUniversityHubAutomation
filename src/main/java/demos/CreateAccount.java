package demos;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccount {
    public static void main(String[] args) throws InterruptedException {
        // 1 . Create WebDriver
        System.setProperty("webdriver.gecko.driver", "C:\\Software\\SDETuniversity\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        // 2. Open browser to Account Management Page  >> Click on create account
        driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");
        driver.findElement(By.linkText("Create Account")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ctl00$MainContent$txtFirstName")));
        // 3. Fill out the form
        driver.findElement(By.name("ctl00$MainContent$txtFirstName")).sendKeys("Mary Smith");
        driver.findElement(By.id("MainContent_txtEmail")).sendKeys("ms@testemail.com");

        driver.findElement(By.xpath("//*[@id='MainContent_txtHomePhone']")).sendKeys("1231231234"); //rel Xpath

//        driver.findElement(By.cssSelector("input[id='MainContent_txtPassword']")).sendKeys("mspass");
        driver.findElement(By.cssSelector("input[type='password'][id='MainContent_txtPassword']")).sendKeys("mspass");
        driver.findElement(By.name("ctl00$MainContent$txtVerifyPassword")).sendKeys("mspass");

//        driver.findElement(By.id("MainContent_Female")).click();
        driver.findElement(By.cssSelector("input[name='ctl00$MainContent$Gender'][value='Female']")).click();
        new Select(driver.findElement(By.id("MainContent_menuCountry"))).selectByVisibleText("Canada");
        driver.findElement(By.name("ctl00$MainContent$checkMonthlyEmail")).click();

        driver.findElement(By.name("ctl00$MainContent$btnSubmit")).click();

        //4. Get confirmation

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent_lblTransactionResult")));
        String conf = driver.findElement(By.id("MainContent_lblTransactionResult")).getText();
        System.out.println(conf);


        // 5. Close the browser
        driver.close();
    }
}
