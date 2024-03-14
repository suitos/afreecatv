package main.java.com.afreecatv.driver.manager;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.com.afreecatv.driver.option.DriverOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxManager implements DriverManager_OC {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().clearResolutionCache().clearDriverCache().setup();
        WebDriver driver = new FirefoxDriver(new DriverOptions().firefoxOptions());
        return driver;
    }

    @Override
    public AppiumDriver createAppiumDriver(String udid, String platformVersion) {
        return null;
    }
}