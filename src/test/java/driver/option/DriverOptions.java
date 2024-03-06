package test.java.driver.option;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverOptions {
    private final ChromeOptions chromeOptions = new ChromeOptions();

    private void headlessOptions() {
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1400,1080");
    }

    public ChromeOptions chromeOptions() {

        chromeOptions.addArguments("--lang=ko-KR");
        chromeOptions.addArguments("disable-gpu");
        chromeOptions.addArguments("enable-automation");

        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-browser-side-navigation");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        chromeOptions.addArguments("--use-fake-device-for-media-stream");

        chromeOptions.addArguments("force-device-scale-factor=1");
        chromeOptions.addArguments("high-dpi-support=1");
        chromeOptions.addArguments("--remote-allow-origins=*");

        return chromeOptions;
    }

}
