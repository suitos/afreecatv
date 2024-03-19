package main.java.com.afreecatv.driver.option;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverOptions {
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;

    private void chromeHeadlessOptions() {
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1400,1080");
    }

    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--lang=ko-KR");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("enable-automation");

        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-browser-side-navigation");

        chromeOptions.addArguments("force-device-scale-factor=1");
        chromeOptions.addArguments("high-dpi-support=1");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        System.setProperty("webdriver.chrome.logfile", "log4j2/driver/chrome.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        chromeOptions.setCapability("goog:chromeOptions", "{\"loggingPrefs\": {\"browser\": \"ALL\"}}");

        return chromeOptions;
    }

    public FirefoxOptions firefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("--disable-infobars");

        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        System.setProperty("webdriver.gecko.logfile", "log4j2/driver/geckodriver.log");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "log4j2/driver/firefox.log");
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.DEBUG);

        return firefoxOptions;
    }
}
