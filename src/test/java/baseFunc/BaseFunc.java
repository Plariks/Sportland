package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseFunc {

    private static WebDriver driver;
    private WebDriverWait wait;
    Actions action;
    WebElement target;
//    WebDriverWait jsWait;
//    JavascriptExecutor jsExec;


    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/694039/Desktop/pycharm/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(getAllElements(locator).isEmpty(), "this element is not found!");
        return driver.findElement(locator);
    }

    public List<WebElement> getAllElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "this element is not found!");
        return driver.findElements(locator);
    }


    public void moveOverElement(By locator) {
        action = new Actions(driver);
        target = driver.findElement(locator);
        action.moveToElement(target).build().perform();
    }


    public void waitePageLoader() {
        driver.manage().timeouts().pageLoadTimeout(5, SECONDS);
    }

    public void scriptsTimeOut() {
        driver.manage().timeouts().setScriptTimeout(10, SECONDS);
    }

    public void waitCondition(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void justWaitSeconds() {
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    public void waitJsExecution(By locator) {
        String javaScript = "(function watcher(ms){var start=new Date().getTime();var end = start;while(end<start+ms){end=new Date().getTime();};return 'complete';})(5000);return 'success';";
        wait.until(ExpectedConditions.jsReturnsValue(javaScript));
        waitForElement(locator);
    }

    public void waitForJsAgain() {
        ExpectedCondition<Boolean> jsLoader = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        wait.until(jsLoader);
    }

}
