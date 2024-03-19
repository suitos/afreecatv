package test.java.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.com.afreecatv.utils.BrowserInfoUtils;
import main.java.com.afreecatv.utils.DataFormat;
import test.java.uitest.pretest.BaseTest;

import java.util.Objects;

public final class ExtentReport {

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("./extent-reports/STABLE-AFREECATV-" + new DataFormat().getToday() + ".html");

            extent.attachReporter(spark);

            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("AfreecaTV Service Automation Test Report");
            spark.config().setReportName("AfreecaTV Service Automation Test Report");

        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.setSystemInfo("Product", "AfreecaTV Service");
            extent.setSystemInfo("Author", "Chaemun Lim");
            extent.setSystemInfo("TestUrl", BaseTest.TargetUrl);
            extent.flush();
        }
        ExtentManager.unload();

    }

    public static void createTest(String methodName, String description) {
        ExtentManager.setExtentTest(extent.createTest(methodName, description));

    }

    synchronized public static void addDevices(BrowserInfoUtils browserInfoUtils) {
        browserInfoUtils.setDriverBrowserName();
        browserInfoUtils.setBrowserVersionInfo();

        ExtentManager.getExtentTest().assignDevice(browserInfoUtils.getDriverBrowserName() + "-" + browserInfoUtils.getBrowserVersion());

    }

}
