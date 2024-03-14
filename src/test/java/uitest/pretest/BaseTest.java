package test.java.uitest.pretest;

import main.java.com.afreecatv.utils.PropertyReader;
import org.testng.annotations.*;
import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.driver.manager.DriverManagerFactory;
import main.java.com.afreecatv.driver.type.DriverType;

public class BaseTest {

    private static String Browser;
    private static String Url;

    private void setBrowser(String browser) {
        if (browser == null) {
            browser = DriverType.CHROME.name();
        }

        browser = System.getProperty("browser", browser);

        Browser = browser;
    }

    public String getBrowser() {
        return Browser;
    }

    private void setUrl() {
        try {
            if (System.getProperty("url").contains("stable")) {
                Url = PropertyReader.getProperty("url");
            }
        } catch (NullPointerException npe) {
            Url = PropertyReader.getProperty("url");
        }

    }

    public String getUrl() {
        return Url;
    }

    @BeforeSuite()
    public void beforeSuite() {
        setUrl();
    }

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional String browser) throws Exception {
        setBrowser(browser);
        browser = getBrowser();
        DriverManager.setDriver(new DriverManagerFactory().getManager(browser));

    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }


}
