package test.java.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import main.java.com.afreecatv.log.Logging;
import main.java.com.afreecatv.utils.BrowserInfoUtils;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import test.java.extentReports.ExtentLogging;
import test.java.extentReports.ExtentReport;

import java.util.Arrays;

public class ExtentReportListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();

        ExtentReport.createTest(methodName, description);

        BrowserInfoUtils browserInfoUtils = new BrowserInfoUtils();
        ExtentReport.addDevices(browserInfoUtils);

        ExtentLogging.info("***** " + methodName + " Start *****");
        Logging.logger.info("***** " + methodName + " Start *****");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        String logText = "<b>" + methodName + " is passed.</b>";
        Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);

        ExtentLogging.pass(markup_message);
        Logging.logger.info("[Time] " + getExecutionTime(result) + " [Method] " + methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        String exceptionMessage = result.getThrowable().getMessage();
        String exceptionDetailMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String failMsgDetail = "<details><summary><b><font color=red> Exception occured, click to see details: </font></b> </summary>"
                + exceptionDetailMessage.replaceAll(",", "<br>") + "</details> \n";

        ExtentLogging.fail(exceptionMessage);
        ExtentLogging.fail(failMsgDetail);

        String logText = "<b>" + methodName + " is failed.</b>";
        Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);

        ExtentLogging.fail(markup_message, true);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();

        String logText = "<b>" + methodName + " is skipped.</b>";
        Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        ExtentLogging.skip(markup_message);
    }

    private double getExecutionTime(ITestResult result) {
        long durationInMilliSeconds = result.getEndMillis() - result.getStartMillis();
        double durationInSeconds = durationInMilliSeconds / 1000.0;

        return durationInSeconds;
    }
}
