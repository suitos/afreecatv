package test.java.pageFactory.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.driver.manager.DriverManager;

import java.time.Duration;

import static org.assertj.core.api.Fail.fail;

public class BasePage {

    public WebDriver driver;

    public BasePage() {
        try {
            driver = DriverManager.getDriver();
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize page object: " + this.getClass().getSimpleName(), e);
        }
    }

    public void waitForClickabilityOf(WebElement el) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(el));

        } catch (Exception e) {
            if (!el.isDisplayed()) {
                fail(el + " is not clickable");
            }
        }

    }

    public BasePage open(String url) {
        try {
            driver.get(url);

        } catch (InvalidArgumentException e) {
            fail(url + " is open fail!!");
            driver.quit();

        } catch (WebDriverException we) {
            fail("Server is not active!!");
            driver.quit();

        }
        return this;
    }

    public BasePage sleep(long time) throws Exception {
        Thread.sleep(time);
        return this;
    }

    public BasePage click(WebElement el, String elname) throws Exception {
        waitForClickabilityOf(el);

        el.click();

        return this;
    }

    public BasePage sendKeys(WebElement el, String text, String elname) throws Exception {
        waitForClickabilityOf(el);
        el.clear();
        sleep(200);
        el.sendKeys(text);

        return this;
    }

    public String getText(WebElement el) throws Exception {
        waitForClickabilityOf(el);
        return el.getText();
    }
}
