package test.java.pageFactory.pages;

import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import test.java.actionFactory.BrowserActions;
import test.java.actionFactory.UserActions;
import test.java.extentReports.ExtentLogging;

import static org.assertj.core.api.Fail.fail;

public class BasePage extends UserActions {

    private WebDriver driver;
    private UserActions userActions;
    private BrowserActions browserActions;

    public BasePage() {
        try {
            driver = DriverManager.getDriver();
            driver.manage().window().maximize();

            PageFactory.initElements(driver, this);

            userActions = new UserActions();
            browserActions = new BrowserActions();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize page object: " + this.getClass().getSimpleName(), e);
        }
    }

    public void navigate(String url) {
        try {
            driver.get(url);

            Logging.logger.info("[Step] Navigate " + url);
            ExtentLogging.info("[Step] Naviage " + url);

        } catch (InvalidArgumentException e) {
            fail(url + " is open fail!!");
        } catch (WebDriverException we) {
            fail("Server is not active!!");
        }
    }

    public void scrollElement(WebElement el) {
        userActions.scrollElement(el);
    }

    public void click(WebElement el, String elname) throws Exception {
        userActions.click(el, elname);
    }

    public void sendKeys(WebElement el, String text, String elname) throws Exception {
        userActions.sendKeys(el, text, elname);
    }

    public String getText(WebElement el) throws Exception {
        return userActions.getText(el);

    }

    public String getTextByXPath(WebElement el, String xpath) throws Exception {
        return userActions.getText(el.findElement(By.xpath(xpath)));

    }

    public int getTabSize() {
        return browserActions.getTabSize();
    }

    public void changeTab(int wishTab) {
        browserActions.changeTab(wishTab);
    }

    public void changeAndCloseTab(int wishTab) {
        browserActions.changeAndCloseTab(wishTab);
    }

    public boolean isElementDisplayed(WebElement el) {
        try {
            waitForVisibility(el, 3);
            waitForClickabilityOf(el, 3);
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
