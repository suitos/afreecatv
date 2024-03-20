package test.java.actionFactory;

import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.log.Logging;
import org.openqa.selenium.WebDriver;
import test.java.extentReports.ExtentLogging;

import java.util.ArrayList;

public class BrowserActions {
    private WebDriver driver;

    public BrowserActions() {
        driver = DriverManager.getDriver();
    }

    public void changeTab(int tabOrder) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabOrder));

        Logging.logger.info("[Step] ChangeTabs " + (tabOrder + 1) + "번째 탭");
        ExtentLogging.info("[Step] ChangeTabs " + (tabOrder + 1) + "번째 탭");

    }
}
