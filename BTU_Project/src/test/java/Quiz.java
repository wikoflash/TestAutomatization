import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Quiz {
    WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    public Quiz() {
        WebDriverManager.chromedriver().setup();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    @Test
    public void QuizSteps() throws InterruptedException {
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Step: login
        WebElement userName = driver.findElement(By.id("userName"));
        WebElement pwd = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.id("login"));
        userName.sendKeys("test123");
        pwd.sendKeys("Automation@123");
        login.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id=\"submit\"]"), "Log out"));

        // Step:Go To Book Store
        WebElement bookStore = driver.findElement(By.id("gotoStore"));
        bookStore.click();
        js.executeScript("window.scrollBy(0,350)", "");

        Thread.sleep(500);
        List<WebElement> books = driver.findElements(By.className("action-buttons"));
        var bookNum = books.size();
        Assert.assertEquals(bookNum, 8);

        //Step:Book Details
        WebElement gitGuide = driver.findElement(By.id("see-book-Git Pocket Guide"));
        gitGuide.click();
        WebElement title = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/label"));
        Assert.assertEquals(title.getText(), "Git Pocket Guide");

        //Step:Add to your collection
        //WebElement addCollection = driver.findElement(By.xpath("//*text()='Add To Your Collection'"));
        //List<WebElement> buttons = driver.findElements(By.xpath("//*[contains(text()='Add To Your Collection')]"));
        WebElement button1 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[2]/button"));
        button1.click();
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "Book already present in the your collection!");
        alert.accept();

        //Step:Back To Book Store
        WebElement button2 = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[2]/div[2]/div[9]/div[1]/button"));
        button2.click();
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(500);
        Assert.assertEquals(books.size(), 8);

        //Step:Log out
        WebElement logout = driver.findElement(By.id("submit"));
        logout.click();
        WebElement h2 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h2"));
        Assert.assertEquals(h2.getText(), "Welcome,");
        WebElement h5 = driver.findElement(By.xpath("//*[@id=\"userForm\"]/div[1]/h5"));
        Assert.assertEquals(h5.getText(), "Login in Book Store");

        //driver.close();
        //driver.quit();
    }
}