package demos;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    public static void main(String[] args) {

        //1. open the web browser
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\SDETuniversity\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //2. Navigate to the web application
        //http://sdettraining.com/trguitransactions/AccountManagement.aspx
        driver.get("http://sdettraining.com/trguitransactions/AccountManagement.aspx");

        //Find Elements: Locate the element, Determine the action, Pass any parameter
        //3. Enter email address
        driver.findElement(By.name("ctl00$MainContent$txtUserName")).sendKeys("tim@testemail.com");

        //4.Enter password
        driver.findElement(By.name("ctl00$MainContent$txtPassword")).sendKeys("trpass");

        //5.Click login
        driver.findElement(By.name("ctl00$MainContent$btnLogin")).click();

        //6.get confirmation
        String conf_message = driver.findElement(By.id("conf_message")).getText();
        System.out.println("CONFIRMATION: " + conf_message);

        String title = driver.getTitle();
        System.out.println("PAGE TITLE: " + title);

        //7.close the browser (close() closes one browser , quit() closes the entire session)
        driver.close();
    }
}
