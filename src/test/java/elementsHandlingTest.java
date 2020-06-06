import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class  elementsHandlingTest {


    // Testuje rozne elementy na stronie Guru99 i Rahul Shetty academy

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        driver.quit();
    }

    @Test
    public void dropdownUsage() throws InterruptedException {

        String url = "http://www.rahulshettyacademy.com/AutomationPractice/";
        driver.navigate().to("http://www.rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.findElement(By.xpath("//td[contains(text(), 'REST API')]")).getText());

        Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdown-class-example']")));
        Assertions.assertEquals(4, dropdown.getOptions().size());


        dropdown.selectByVisibleText("Option3");
        Assertions.assertEquals("Option3", dropdown.getFirstSelectedOption().getText());
        dropdown.selectByIndex(0);
        Thread.sleep(500);
        dropdown.selectByValue("option2");

    }

    @Test
    public void checkBox() {

        driver.navigate().to("http://www.rahulshettyacademy.com/AutomationPractice/");
        WebElement checkBox = driver.findElement(By.xpath("//input[@id='checkBoxOption2']"));

        checkBox.click();
        if (checkBox.isSelected()) {
            System.out.println("Checkbox zaznaczony");
        }

        Assertions.assertTrue(checkBox.isSelected());
        if (checkBox.isSelected()) {
            checkBox.click();
        }
        System.out.println("Odznaczono");
        Assertions.assertFalse(checkBox.isSelected());

    }

    @Test
    public void displayingRowInfo() {

        driver.navigate().to("http://www.rahulshettyacademy.com/AutomationPractice/");
        WebElement table = driver.findElement(By.xpath("//table[@id='product']"));

        List<WebElement> rows = driver.findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void rightClickContextMentu() throws InterruptedException {

        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement button = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        WebElement edit = driver.findElement(By.xpath("//span[contains(text(),'Edit')]"));


        Actions builder = new Actions(driver);
        builder.contextClick(button).moveToElement(edit).click().perform();

        Thread.sleep(5000);
    }

    @Test
    public void takeAScreenshot() throws IOException {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("target/main_page.png"));
    }


    @Test
    public void alertAcceptanceTest() {
        driver.get("http://www.rahulshettyacademy.com/AutomationPractice/");
        WebElement alertButton = driver.findElement(By.xpath("//input[@id='alertbtn']"));

        alertButton.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();

        System.out.println(textOnAlert);

        Assertions.assertEquals("Hello , share this practice page and share your knowledge", textOnAlert);
        alert.accept();
    }

    @Test
    public void alertDiscardTest() throws InterruptedException {
        driver.get("http://www.rahulshettyacademy.com/AutomationPractice/");

        WebElement confirmButton = driver.findElement(By.xpath("//input[@id='confirmbtn']"));
        WebElement nameField = driver.findElement(By.xpath("//input[@id='name']"));

        String name = "John";
        nameField.sendKeys(name);

        confirmButton.click();
        Thread.sleep(2000);

        new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();

        System.out.println(textOnAlert);

        Assertions.assertEquals("Hello " + name + ", Are you sure you want to confirm?", textOnAlert);
        alert.dismiss();

    }

    @Test
    public void iFrame(){
        driver.get("http://demo.guru99.com/test/guru99home/");

        driver.manage().window().maximize();
        driver.switchTo().frame("a077aa5e"); //switching the frame by ID

        System.out.println("********We are switch to the iframe*******");
        driver.findElement(By.xpath("html/body/a/img")).click();
        //Clicks the iframe

        System.out.println("*********We are done***************");
    }

    }

