package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final String loginForm = "//form[@id='login-form']";
    private static final String baseUrl = "https://demo.openmrs.org/openmrs/login.htm";

    private static WebDriver driver;

    /**
     * Singleton constructor
     */
    private DriverManager() {
    }

    /**
     * Initialize web driver (or) driver object creation
     * @return
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("autofill.profile_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
        }
        return driver;
    }

    /**
     * Start application
     */
    public static void startApplication(){
        if(baseUrl!=null && !baseUrl.isEmpty()){
            driver.get(baseUrl);
        } else {
            System.out.println("Web application URL is NULL");
        }
    }

    /**
     * Driver quit
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
