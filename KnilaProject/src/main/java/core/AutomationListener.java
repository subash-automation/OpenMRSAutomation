package core;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static core.DriverManager.getDriver;

public class AutomationListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Started Test: "+iTestResult.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\nTEST PASSED\n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("\nTEST FAILED\n");

        if(getDriver()!=null){
            if(iTestResult.getThrowable() instanceof Exception ){
                System.out.println("Failure reason: "+iTestResult.getThrowable().getLocalizedMessage()+"\n");
            }
            System.out.println("Failure URL: " + getDriver().getCurrentUrl());
            System.out.println("URL: "+getDriver().getCurrentUrl()+"\n");
            takeScreenshot(iTestResult);
        }
    }

    private String takeScreenshot(ITestResult result) {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String fileName = result.getMethod().getMethodName() + "_" + dateFormat.format(new Date()) + ".png";
        String destFile = "testresult/screenshots/" + fileName;
        result.setAttribute("screenshot", fileName);
        takeScreenshot(destFile);
        final String dir = System.getProperty("user.dir");
        final Path baseDir = Paths.get(dir, destFile);
        File screenshot = baseDir.toFile();
        System.out.println("Is Screenshot exists : " + screenshot.exists());
        if (screenshot.exists()) {
            System.out.println("Screenshot Absolute Path : " + screenshot.getAbsolutePath());
        }
        System.out.println("Screenshot URL : " + destFile);
        return fileName;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    /**
     * Take screenshot.
     *
     * @param fileName the file name
     */
    private static void takeScreenshot(String fileName) {

        try {
            final Path screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE).toPath();
            final Path screenShotDir = getTargetScreenshotPath(fileName);
            Files.copy(screenshot, screenShotDir, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error in taking screenshot :" + ex.getMessage());
        }
    }

    private static Path getTargetScreenshotPath(final String fileName) throws IOException {

        final String dir = System.getProperty("user.dir");
        final Path baseDir = Paths.get(dir, fileName);
        Files.createDirectories(baseDir);
        return baseDir;
    }
}
