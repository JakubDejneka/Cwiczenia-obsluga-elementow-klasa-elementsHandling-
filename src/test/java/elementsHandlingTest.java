import net.bytebuddy.TypeCache;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

public class  elementsHandlingTest {


    // Testuje rozne elementy na stronie Guru99 i Rahul Shetty academy

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_v83/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
    public void iFrame() {
        driver.get("http://demo.guru99.com/test/guru99home/");

        driver.manage().window().maximize();
        driver.switchTo().frame("a077aa5e"); //switching the frame by ID

        System.out.println("********We are switch to the iframe*******");
        driver.findElement(By.xpath("html/body/a/img")).click();
        //Clicks the iframe

        System.out.println("*********We are done***************");
    }


    @Test
    public void zabawyNaSpiceJet() throws InterruptedException {

        driver.get("https://www.spicejet.com/");
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.xpath("//div[@id='Div1']")).getAttribute("style").toString());
        if (driver.findElement(By.xpath("//div[@id='Div1']")).getAttribute("style").contains("0.5")) {
            System.out.println("nieaktywne");
            Assertions.assertTrue(true);
        } else
            System.out.println("Aktywne ?");

        driver.findElement(By.xpath("//input[@id='ctl00_mainContent_rbtnl_Trip_1']")).click();
        System.out.println("Klikniete");

        if (driver.findElement(By.xpath("//div[@id='Div1']")).getAttribute("style").contains("1")) {
            System.out.println("aktywne");
            Assertions.assertTrue(true);
        }

        System.out.println("Before clicking checkbox");
        System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        Assertions.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

        int ilosc_checkboxow = driver.findElements(By.xpath("//input[@type='checkbox']")).size();
        System.out.println(ilosc_checkboxow);
        Assertions.assertEquals(ilosc_checkboxow, 6);

        WebElement elementToClick = driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']"));
        elementToClick.click();

        System.out.println("After clicking checkbox");
        System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());

        driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();
        Thread.sleep(2000);


    }


    @Test
    public void addingToCardItemsFromList() throws InterruptedException {


        driver.get("https://rahulshettyacademy.com/seleniumPractise/");

        List<WebElement> elementy = driver.findElements(By.xpath("//h4[@class='product-name']"));
        String[] itemsNeeded = {"Brocolli", "Cucumber"};

        for (int i = 0; i < elementy.size(); i++) {
            String[] name = elementy.get(i).getText().split("-");
            String trimmedName = name[0].trim();

            List itemsNeededList = Arrays.asList(itemsNeeded);

            if (itemsNeededList.contains(trimmedName)) {
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
            }
        }
        driver.findElement(By.cssSelector("img[alt='Cart']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
        WebDriverWait w = new WebDriverWait(driver, 6);


        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();


        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));
        WebElement promoCode = driver.findElement(By.xpath("//span[@class='promoInfo']"));
        Assertions.assertEquals(promoCode.getText(), "Code applied ..!");


        Thread.sleep(3000);
    }

    @Test
    public void actionsOnAmazon() throws InterruptedException {

        driver.get("https://www.amazon.com");
        Actions a = new Actions(driver);
        WebElement signIn = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
        //a.moveToElement(driver.findElement(By.xpath("//a[@id='nav-link-accountList']"))).build().perform();

        WebElement element = driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
        a.moveToElement(element)
                .click()
                .keyDown(Keys.SHIFT)
                .sendKeys("buddha")
                .doubleClick()
                .build()
                .perform();

        Thread.sleep(1000);
        a.moveToElement(signIn).contextClick(signIn).build().perform();
        Thread.sleep(1000);


    }

    @Test
    public void windowHandles() {

        driver.get("https://accounts.google.com/signup/v2/webcreateaccount?hl=en&flowName=GlifWebSignIn&flowEntry=SignUp");
        WebElement help = driver.findElement(By.xpath("//a[contains(text(),'Help')]"));
        help.click();
        System.out.println("Before switching");
        System.out.println(driver.getTitle());

        Set<String> ids = driver.getWindowHandles();
        Iterator<String> it = ids.iterator();
        String parent = it.next();
        String child = it.next();

        driver.switchTo().window(parent);

        System.out.println("After switching");
        driver.switchTo().window(child);
        System.out.println(driver.getTitle());


    }

    @Test
    public void dragAndDropInFrames() throws InterruptedException {

        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));

        Actions a = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        a.dragAndDrop(source, target).build().perform();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();
    }

    @Test
    public void limitingWebDriverScope() {

        driver.get("http://qaclickacademy.com/practice.php");

        // creating "mini driver" or driver with limited scope:
        WebElement footerdriver = driver.findElement(By.cssSelector("div#gf-BIG"));
        System.out.println(footerdriver.findElements(By.tagName("a")).size());

    }

    @Test
    public void openLinksInNewCards() {

        driver.get("http://qaclickacademy.com/practice.php");
        WebElement footerdriver = driver.findElement(By.cssSelector("div#gf-BIG"));
        WebElement columnDriver = footerdriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));

        for (int i = 1; i < columnDriver.findElements(By.tagName("a")).size(); i++) {

            String newTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
            columnDriver.findElements(By.tagName("a")).get(i).sendKeys(newTab);
        }
        Set<String> idPage = driver.getWindowHandles();
        Iterator<String> it = idPage.iterator();

        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
        }


    }

    @Test
    public void calendarHandling() throws InterruptedException {

        driver.get("https://www.path2usa.com/travel-companions");
        Thread.sleep(7000);
        //April 23
        driver.findElement(By.xpath(".//*[@id='travel_date']")).click();


        while (!driver.findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText().contains("May")) {
            driver.findElement(By.cssSelector("[class='datepicker-days'] th[class='next']")).click();
        }
        Thread.sleep(1000);

        List<WebElement> dates = driver.findElements(By.className("day"));

        //Grab common attribute//Put into list and iterate
        int count = driver.findElements(By.className("day")).size();
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            String text = driver.findElements(By.className("day")).get(i).getText();
            System.out.println(text);
            if (text.equalsIgnoreCase("21")) {
                driver.findElements(By.className("day")).get(i).click();
                break;
            }

        }
        Thread.sleep(3000);
    }

    @Test
    public void handlingTableGrids() {

        //link jest z losowej tabeli wynikow- moze sie zmieniac
        driver.get("https://www.cricbuzz.com/live-cricket-scorecard/28287/thai-vs-sin-1st-match-acc-eastern-region-t20-2020");
        WebElement table = driver.findElement(By.xpath("//div[@class='cb-col cb-col-67 cb-scrd-lft-col html-refresh ng-isolate-scope']"));
        int count = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();
        int suma = 0;

        for (int i = 0; i < count - 20; i++) {
            String text = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText();
            int y = Integer.parseInt(text);
            suma = suma + y;

        }

        System.out.println("policzone z petli : " + suma);

        System.out.println("-----------------------");
        String extras = driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText();
        System.out.println(extras);
        System.out.println(driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText());
    }

    @Test
    public void autoSuggestiveDropdowns() throws InterruptedException {

        driver.get("https://www.makemytrip.com/");
        Thread.sleep(2000);


        WebElement popup = driver.findElement(By.cssSelector(".autopop__wrap.makeFlex.column.defaultCursor"));
        WebElement account = driver.findElement(By.xpath("//li[@data-cy='account']"));
        if (popup.isDisplayed()) {
            account.click();
        }

        driver.findElement(By.xpath("//input[@id='fromCity']")).click();

        WebElement source = driver.findElement(By.xpath("//input[@placeholder='From']"));
        Thread.sleep(3000);
        source.sendKeys("Warsaw");
        Thread.sleep(3000);

        source.sendKeys(Keys.ARROW_DOWN);
        source.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        WebElement destination = driver.findElement(By.xpath("//input[@placeholder='To']"));

        destination.sendKeys("Bengaluru");
        Thread.sleep(2000);
        destination.sendKeys(Keys.ARROW_DOWN);
        destination.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    @Test
    public void jsExecutor() throws InterruptedException {

        driver.get("https://www.ksrtc.in");
        driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys("BENG");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return document.getElementById(\"fromPlaceName\").value;";
        String text = (String) js.executeScript(script);
        int i = 0;

        while (!text.equalsIgnoreCase("BENGA2LURU AIRPORT")) {
            i++;
            driver.findElement(By.xpath("//input[@id='fromPlaceName']")).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(1000);
            text = (String) js.executeScript(script);
            if (i > 9) {
                break;
            }

        }
        if (i > 9) {
            System.out.println("element not found");
        } else System.out.println("Element found");

        System.out.println(text);

    }

    @Test
    public void jsExe2() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement input = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        input.clear();
        input.sendKeys("unit");
        Thread.sleep(2000);
        input.sendKeys(Keys.ARROW_DOWN);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return document.getElementById(\"autocomplete\").value;";
        String txt = (String) js.executeScript(script);


        while (!txt.equalsIgnoreCase("United States (USA)")) {
            input.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            txt = (String) js.executeScript(script);
            System.out.println(txt);
        }

    }

    @Test
    public void jsExe3() throws InterruptedException {

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement input = driver.findElement(By.xpath("//input[@id='autocomplete']"));
        input.clear();
        input.sendKeys("unit");
        Thread.sleep(2000);

        for (int i = 0; i < 5; i++) {
            input.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
        }
        System.out.println(driver.findElement(By.xpath("//input[@id='autocomplete']")).getAttribute("value"));

    }

    @Test
    public void screenShootCapture() throws IOException {

        driver.get("https://www.google.com");
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\Jacob\\Desktop\\screenshoot.png"));

    }

    @Test
    public void sortingTables(){

        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        List<WebElement> lista = driver.findElements(By.xpath("//tr/td[2]"));

        ArrayList <String> originalList = new ArrayList<>();
        for (int i = 0; i<lista.size(); i++){
            originalList.add(driver.findElements(By.xpath("//tr/td[2]")).get(i).getText());
        }

        for (String e : originalList){
            System.out.println(e);
        }

        System.out.println("----------------copied list: -----------");
        ArrayList <String> copiedList = new ArrayList<>();
        for (int i = 0; i <originalList.size(); i++){
            copiedList.add(originalList.get(i));
            System.out.println(copiedList.get(i));
        }

        System.out.println("sorting copied list: ");
        Collections.sort(copiedList);
        System.out.println(copiedList);

        Assertions.assertFalse(copiedList.equals(originalList));
        System.out.println("reverse copied list and display:  ");
        Collections.reverse(copiedList);
        System.out.println(copiedList);



    }

    
}

