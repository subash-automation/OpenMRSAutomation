package utility;

import enums.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitUtil {

    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForVisibility(WebDriver driver, By locator, Timeout seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds.getSeconds());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForVisibilityOfAll(WebDriver driver, By locator, Timeout seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds.getSeconds());
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static WebElement waitForClickability(WebDriver driver, By locator, Timeout seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds.getSeconds());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForInvisibility(WebDriver driver, By locator, Timeout seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds.getSeconds());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForTextPresent(WebDriver driver, By locator, String text, Timeout seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds.getSeconds());
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Implicit wait for driver
     * @param driver
     * @param seconds
     */
    public static void waitUntil(WebDriver driver, int seconds){
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }
}
