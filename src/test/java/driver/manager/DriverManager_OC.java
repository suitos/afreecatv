package test.java.driver.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public interface DriverManager_OC {

    WebDriver createDriver() throws Exception;

    AppiumDriver createAppiumDriver(String udid, String platformVersion);

}