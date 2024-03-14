package test.java.actionFactory;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import main.java.com.afreecatv.driver.manager.DriverManager;

import java.time.Duration;

public class UserActions {
    private WebDriver driver;

    public UserActions() {
        driver = DriverManager.getDriver();
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

    public void sleep(long time) throws Exception {
        Thread.sleep(time);
    }

    public void click(WebElement el, String elname) throws Exception {
        waitForClickabilityOf(el, 3);
        el.click();

    }

    public void sendKeys(WebElement el, String text, String elname) throws Exception {
        waitForClickabilityOf(el, 3);
        el.clear();
        sleep(200);
        el.sendKeys(text);

    }

    public String getText(WebElement el) throws Exception {
        waitForClickabilityOf(el, 3);
        return el.getText();
    }
}
