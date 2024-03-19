package main.java.com.afreecatv.utils;

import io.appium.java_client.AppiumDriver;
import main.java.com.afreecatv.driver.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ScreenShot {
    public String getBase64Image() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    public static String getBase64Image(AppiumDriver driver) throws WebDriverException, Exception {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public byte[] elementScreenShot(WebElement element) {
        return element.getScreenshotAs(OutputType.BYTES);
    }

    public static void saveScreenshotToFile(byte[] screenshotBytes, String filePath) {
        try {
            // 바이트 배열을 파일로 저장
            Path screenshotPath = new File(new FileManager().getDownloadFilePath(filePath)).toPath();
            Files.write(screenshotPath, screenshotBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRefImage(String filePath) throws Exception {
        Path path = new File(new FileManager().getDownloadFilePath(filePath)).toPath();
        return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
    }
}
