package test.java.uitest.pretest;

import lombok.Getter;
import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.driver.manager.DriverManagerFactory;
import main.java.com.afreecatv.driver.type.DriverType;
import main.java.com.afreecatv.utils.PropertyReader;
import org.testng.annotations.*;
import test.java.pageFactory.pages.BasePage;

public class BaseTest {

    @Getter
    private String Browser;
    public static String TargetUrl;

    private void setBrowser(String browser) {
        if (browser == null) browser = DriverType.CHROME.name();
        browser = System.getProperty("browser", browser);

        Browser = browser;
    }

    private void setTargetUrl() {
        try {
            if (System.getProperty("url").contains("stable")) {
                TargetUrl = PropertyReader.getProperty("url");
            }
        } catch (NullPointerException npe) {
            TargetUrl = PropertyReader.getProperty("url");
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {

    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional String browser) {
        setTargetUrl();
        setBrowser(browser);
        browser = getBrowser();
        DriverManager.setDriver(new DriverManagerFactory().getManager(browser));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }

    @BeforeGroups(alwaysRun = true, groups = {"basic_test"})
    public void navigateTargetUrl() {
        BasePage basePage = new BasePage();
        basePage.navigate(TargetUrl);
    }

}
