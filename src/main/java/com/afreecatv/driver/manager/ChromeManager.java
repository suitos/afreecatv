package main.java.com.afreecatv.driver.manager;

import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.com.afreecatv.driver.option.DriverOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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