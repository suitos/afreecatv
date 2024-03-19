package main.java.com.afreecatv.utils;

import lombok.Getter;
import main.java.com.afreecatv.driver.manager.DriverManager;
import main.java.com.afreecatv.driver.type.DriverType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


@Getter
public final class BrowserInfoUtils {

    private String DriverBrowserName;
    private String BrowserVersion;

    public void setDriverBrowserName() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        DriverBrowserName = capabilities.getBrowserName().toUpperCase();
        if (DriverBrowserName.contains(DriverType.EDGE.name())) {
            DriverBrowserName = "MSEDGE";
        }
    }

    public void setBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        BrowserVersion = capabilities.getBrowserVersion();
    }

}
