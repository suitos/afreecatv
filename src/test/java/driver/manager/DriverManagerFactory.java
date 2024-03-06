package test.java.driver.manager;

import org.openqa.selenium.WebDriver;

public class DriverManagerFactory {

    public WebDriver getManager(String driverType) {
        WebDriver driver;
        switch (driverType) {

            case "CHROME": {
                driver = new ChromeManager().createDriver();
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid Driver: " + driverType);
        }

        return driver;

    }

}