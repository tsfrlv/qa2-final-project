package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomePage {

    private static By cartButton = By.id("shopping_cart_container");

    public static void openHomePage() throws IOException, InterruptedException {
        WebDriverSetup.getDriver().navigate().to(WebDriverSetup.getProperties().getProperty("app.url"));
        //TODO remove if find out why the property in ChromeDriver does not work
        Thread.sleep(1000);
    }

    public static void navigateToCart() {
        WebDriverSetup.getElement(cartButton).click();
    }
}

