import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ExampleTests {

    @Test
    public void firstTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/");
        //driver.manage().window().maximize();
        //WebElement button = driver.findElement(By.xpath("//ul/li/a"));
        //button.click();
        //WebElement text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3"));

        //driver.navigate().to("http://the-internet.herokuapp.com/");
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[11]/a"));
        dropdown.click();

        WebElement selectedOptionText = driver.findElement(By.xpath(("//*[@id=\"dropdown\"]/option[1]")));
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText(selectedOptionText.getText());
        select.selectByIndex(1);
    }


    @Test
    public void locatorTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        driver.manage().window().maximize();
        //*[@id="content"]/div/div[2]/h1 - XPath
        //#content > div > div.col-sm-4 > h1 - css
        List<WebElement> anchors = driver.findElements(By.className("product-thumb"));
        Assert.assertEquals(anchors.size(),2);
         WebElement button=driver.findElement(By.partialLinkText("Canon EOS 5D"));
        button.click();
    }
    @Test
    public void browserCommands(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        // Get the title of the page
        String title = driver.getTitle();
        System.out.println("Title " + title);
        // Get the current URL
        String url = driver.getCurrentUrl();
        System.out.println("URL " + url);
        // Get the current page HTML source
        String html = driver.getPageSource();
        System.out.println("HTML " + html);
    }
}
