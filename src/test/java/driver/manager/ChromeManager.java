package test.java.driver.manager;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.java.driver.option.DriverOptions;

public class ChromeManager implements DriverManager_OC {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver(new DriverOptions().chromeOptions());
        return driver;
    }

    @Override
    public AppiumDriver createAppiumDriver(String udid, String platformVersion) {
        return null;
    }

}