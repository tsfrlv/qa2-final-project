package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverSetup {

    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);

    //only CHROME, add setup for different drivers
    public static WebDriver setupDriver() {
        RemoteWebDriver driver;
        System.setProperty(
                "webdriver.chrome.driver",
                "chromedriver");
        driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    private static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) (getJsExecutor()).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };
    }

    public static WebElement getElement(By locator) {
        try {
            return getDriver().findElement(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return getDriver().findElement(locator);
        }
    }

    public static WebElement getElement(WebElement parent, By locator) {
        try {
            return parent.findElement(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return parent.findElement(locator);
        }
    }


    public static Properties getProperties() throws IOException {
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("test.properties");
        prop.load(stream);
        return prop;
    }

}
