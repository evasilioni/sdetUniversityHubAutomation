package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Expedia {
    String browserType = "Chrome";
    WebDriver driver;
    String city= "New York, New York";
    String checkIn = "04/04/2018";
    String checkOut = "04/14/2018";
    String numOfGuests="1";
    String starRating = "star4";
    int searchResult = 3;

    @Test
    public void hotelReservation(){
        //1. Search
        driver.findElement(By.id("tab-hotel-tab-hp")).click();
        driver.findElement(By.id("hotel-destination-hp-hotel")).clear();
        driver.findElement(By.id("hotel-destination-hp-hotel")).sendKeys(city);
        driver.findElement(By.id("hotel-checkin-hp-hotel")).clear();
        driver.findElement(By.id("hotel-checkin-hp-hotel")).sendKeys(checkIn);
        driver.findElement(By.id("hotel-checkout-hp-hotel")).clear();
        driver.findElement(By.id("hotel-checkout-hp-hotel")).sendKeys(checkOut);
        new Select(driver
                .findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[4]/div[4]/label/select")))
                .selectByValue(numOfGuests);
        driver.findElement(By.xpath("//*[@id=\"gcw-hotel-form-hp-hotel\"]/div[9]/label/button")).click();

        //Print the name of the city
        String actualCity = driver.findElement(By.xpath("//*[@id=\"hotelResultTitle\"]/h1")).getText();
        System.out.println(actualCity);

        //2. Modify the search results page, give criteria
        driver.findElement(By.cssSelector("input[name='star'][id='"+ starRating + "']")).click();;

        //3. Analyze the results and make our selection
        driver.findElement(By.xpath("//*[@id=\"resultsContainer\"]/section/article["+ searchResult +"]/div[2]/div/a")).click();

        //Switch the window to the pop
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        String hotelName = driver.findElement(By.id("hotel-name")).getText();
        System.out.println("HOTEL:" + hotelName);

        //4. Book reservation
        driver.findElement(By.xpath("//*[@id=\"rooms-and-rates\"]/div/article/table/tbody[1]/tr[1]/td[4]/div/form/div[1]/button")).click();

        //5.Fill out contact / billing

        //6. Get confirmation
    }

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.open(browserType);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
