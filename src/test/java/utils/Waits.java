package test.java.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.driver.manager.DriverManager;
import test.java.pageFactory.pages.BasePage;

import java.time.Duration;

import static org.assertj.core.api.Fail.fail;

public class Waits {
    public void waitForClickabilityOf(WebElement el) {

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
            wait.ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(el));

        } catch (Exception e) {
            if (!el.isDisplayed()) {
                fail(el + " is not clickable");
            }
        }

    }
}
