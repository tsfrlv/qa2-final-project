package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage {

    private static By proceedToCheckoutButton = By.className("order-form-forward-button");
    private static By proceedToCheckoutWithoutRegistrationButton = By.className("btn-v2");
    private static By productPrice = By.className("ait-cart-price");

    public static void proceedToCheckout () {
        WebDriverSetup.getElement(proceedToCheckoutButton).click();
    }

    public static void proceedToCheckoutWithoutRegistration () {
        WebDriverSetup.getElement(proceedToCheckoutWithoutRegistrationButton).click();
    }

    public static String getProductPrice() {
        WebElement price = WebDriverSetup.getElement(productPrice);

        return price.getText();
    }
}
