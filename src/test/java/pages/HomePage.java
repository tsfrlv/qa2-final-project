package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;

import java.io.IOException;

public class HomePage {

    private static By cartButton = By.id("shopping_cart_container");

    public static void openHomePage() throws IOException, InterruptedException {
        WebDriverSetup.getDriver().navigate().to(WebDriverSetup.getProperties().getProperty("app.url"));
    }

    public static void navigateToCart() {
        WebDriverSetup.getElement(cartButton).click();
    }
}

