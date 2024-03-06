package test.java.driver.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverRef) {
        driver.set(driverRef);
    }

    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

    public static void setDriver(AppiumDriver Driver) {
        appiumDriver.set(Driver);
    }

    public static AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }

}