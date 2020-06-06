import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.List;


public class FirstChromeTest {

    WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
    }

    @Test
    public void googleSimpleTest()   {


        driver.navigate().to("http://www.google.com");
        WebElement gmail = driver.findElement(By.xpath("//a[contains(text(),'Gmail')]"));
        System.out.println("link do ikonki gmail to: " + gmail.getAttribute("href"));

        WebElement elementZTekstu = driver.findElement(By.linkText("Grafika"));
        System.out.println(elementZTekstu.getText());
        elementZTekstu.click();

    }




}
