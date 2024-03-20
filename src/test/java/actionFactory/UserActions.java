package test.java.actionFactory;

import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

    }

    public void waitForGetAttribute(WebElement element, String attribute, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }

    public void waitForVisibilityAll(List<WebElement> elementList, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    public void waitForVisibility(WebElement el, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOf(el));

    }

    public void waitForClickabilityOf(WebElement el, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(el));
    }

    public void waitForWindowsToBe(int wishTabOrder, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.numberOfWindowsToBe(wishTabOrder));

    }

    public void sleep(long time) throws Exception {
        Thread.sleep(time);
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
}

