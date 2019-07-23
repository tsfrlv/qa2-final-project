package runners;

import base.WebDriverSetup;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = {"steps"}
//        tags = {"@"}
)
public class TestRunner {
    @BeforeClass
    public static void init() {
        if (WebDriverSetup.getDriver() != null) {
            try {
                WebDriverSetup.getDriver().getPageSource();
            } catch (Exception WebDriverException) {
                WebDriverSetup.setDriver(null);
            }
        }

        if (WebDriverSetup.getDriver() == null) {
            WebDriverSetup.setDriver(WebDriverSetup.setupDriver());
        }
        WebDriverSetup.getDriver().manage().window().maximize();
    }

    @AfterClass
    public static void teardown() {
        WebDriverSetup.getDriver().manage().deleteAllCookies();
        WebDriverSetup.getDriver().quit();
    }
}
