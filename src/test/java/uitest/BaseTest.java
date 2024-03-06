package test.java.uitest;

import org.testng.annotations.*;
import test.java.driver.manager.DriverManager;
import test.java.driver.manager.DriverManagerFactory;
import test.java.driver.type.DriverType;
import test.java.pageFactory.pages.BasePage;

public class BaseTest {
    private static String BROWSER;

    private String setBrowserValue(String browser) {

        if (browser == null) {
            browser = DriverType.CHROME.name();
        }

        browser = System.getProperty("browser", browser);
        BROWSER = browser;
        return browser;
    }

    @BeforeSuite(enabled = false)
    public void beforeSuite() {

    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional String browser) throws Exception {
        browser = setBrowserValue(browser);
        DriverManager.setDriver(new DriverManagerFactory().getManager(browser));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }


}
