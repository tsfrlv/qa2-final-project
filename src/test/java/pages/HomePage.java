package pages;

import base.WebDriverSetup;

import java.io.IOException;

import static base.WebDriverSetup.getDriver;

public class HomePage {

    public static void openHomePage() throws IOException {
        getDriver().navigate().to(WebDriverSetup.getProperties().getProperty("app.url"));
    }
}
