package test.java.actionFactory;

import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.extentReports.ExtentLogging;

import java.time.Duration;
import java.util.List;

public class UserActions {
    private WebDriver driver;

    public UserActions() {
        driver = DriverManager.getDriver();
    }

    public void waitForPageLoad(long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until((ExpectedCondition<Boolean>) driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));

    }

    public void waitForGetAttributeToBeNotEmpty(WebElement element, String attribute, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public void waitForVisibilityAll(List<WebElement> elementList, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    public void waitForMoreElementsThan(By elementList, int elementNum, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(elementList, elementNum));
    }

    public void waitForVisibility(WebElement el, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(el));

    }

    public void waitForPresenceOfElementLocated(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(by));

    }

    public void waitForClickabilityOf(WebElement el, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(el));
    }

    public void waitForWindowsToBe(int wishTabNum, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.numberOfWindowsToBe(wishTabNum));

    }

    public void sleep(long time) throws Exception {
        Thread.sleep(time);
    }

    public void scrollElement(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", el);

    }

    public void scrollElementIntoMiddle(WebElement el) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, el);
    }

    public void click(WebElement el, String elname) throws Exception {
        waitForClickabilityOf(el, 3);
        el.click();

        Logging.logger.info("[Step] Click " + elname);
        ExtentLogging.info("[Step] Click " + elname);

    }

    public void sendKeys(WebElement el, String text, String elname) throws Exception {
        waitForClickabilityOf(el, 3);
        el.clear();
        sleep(200);
        el.sendKeys(text);

        Logging.logger.info("[Step] SendKeys " + elname + " [Text] " + text);
        ExtentLogging.info("[Step] SendKeys " + elname + " [Text] " + text);

    }

    public String getText(WebElement el) throws Exception {
        waitForVisibility(el, 3);
        return el.getText();
    }

    public String getAttribute(By by, String attribute) throws Exception {
        waitForPresenceOfElementLocated(by, 3);
        return driver.findElement(by).getAttribute(attribute);
    }

    public String getAttribute(WebElement el, String attribute) throws Exception {
        //waitForVisibility(el, 3);
        return el.getAttribute(attribute);
    }
}

