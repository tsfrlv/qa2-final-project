package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage {

    private static By productNameParent = By.className("product-main-info");
    private static By productName = By.tagName("h1");
    private static By addToCartButton = By.id("add_product_to_shopping_cart_button_top");

    public static String getProductName() {
        WebElement parent = WebDriverSetup.getElement(productNameParent);
        WebElement name = WebDriverSetup.getElement(parent, productName);

        return name.getText();
    }

    public static void addProductToCart() throws InterruptedException {
        WebDriverSetup.getElement(addToCartButton).click();
    }

}
