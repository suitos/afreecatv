package test.java.extentReports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import main.java.com.afreecatv.utils.ScreenShot;

public final class ExtentLogging {

    private ExtentLogging() {
    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    public static void pass(Markup message) {
        try {
            ExtentManager.getExtentTest().pass(message);
        } catch (NullPointerException ignored) {

        }
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);

    }

    public static void fail(Markup message) {
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void skip(Markup message) {
        ExtentManager.getExtentTest().skip(message);
    }

    public static void info(Markup message) {
        ExtentManager.getExtentTest().info(message);
    }

    public static void info(String message) {
        try {
            ExtentManager.getExtentTest().info(message);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void fail(String message, boolean isScreeshotNeeded) {
        if (isScreeshotNeeded) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(new ScreenShot().getBase64Image()).build());
        } else {
            fail(message);
        }
    }

    public static void fail(Markup message, boolean isScreeshotNeeded) {
        if (isScreeshotNeeded) {
            ExtentManager.getExtentTest().fail(
                    MediaEntityBuilder.createScreenCaptureFromBase64String(new ScreenShot().getBase64Image()).build());
            ExtentManager.getExtentTest().fail(message);
        } else {
            fail(message);
        }
    }

}
