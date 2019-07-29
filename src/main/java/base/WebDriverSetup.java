package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WebDriverSetup {

    private static final ThreadLocal<WebDriver> drivers = ThreadLocal.withInitial(() -> null);

    private static long waitUntil;

    static {
        try {
            waitUntil = Long.parseLong(getProperties().getProperty("wait.until"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ChromeDriver setupDriver() {
        ChromeDriver driver;
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 0);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        System.setProperty(
                "webdriver.chrome.driver",
                "chromedriver");
        driver = new ChromeDriver(options);
        return driver;
    }

    public static WebDriver getDriver() {
        return drivers.get();
    }

    public static void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    public static JavascriptExecutor getJsExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public static void waitForPageToLoad() {
        new WebDriverWait(getDriver(), waitUntil).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void scrollTo(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        getJsExecutor().executeScript(scrollElementIntoMiddle, element);
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

    public static List<WebElement> getElements(By locator) {
        waitForPageToLoad();
        try {
            return getDriver().findElements(locator);
        } catch (WebDriverException e) {
            waitForPageToLoad();
            return getDriver().findElements(locator);
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
