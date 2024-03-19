package test.java.pageFactory.pages;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import main.java.com.afreecatv.driver.manager.DriverManager;
import test.java.actionFactory.UserActions;

import static org.assertj.core.api.Fail.fail;

public class BasePage extends UserActions {

    private WebDriver driver;
    private UserActions userActions;

    public BasePage() {
        try {
            driver = DriverManager.getDriver();
            driver.manage().window().maximize();

            PageFactory.initElements(driver, this);

            userActions = new UserActions();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize page object: " + this.getClass().getSimpleName(), e);
        }
    }

    public void navigate(String url) {
        try {
            driver.get(url);
        } catch (InvalidArgumentException e) {
            fail(url + " is open fail!!");
            driver.quit();

        } catch (WebDriverException we) {
            fail("Server is not active!!");
            driver.quit();

        }
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

}
