package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import model.Product;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class CartSteps {

    Product product;

    public CartSteps(Product product){
        this.product = product;
    }

    @And("^adds it to cart$")
    public void addsItToCart() throws Throwable {
        String name = ProductPage.getProductName();
        this.product.setName(name);
        ProductPage.addProductToCart();

    }

    @Then("^navigates to Cart$")
    public void navigatesToCart() throws Throwable {
        HomePage.navigateToCart();
        String price = CartPage.getProductPrice();
        this.product.setPrice(price);
    }

    @And("^proceeds to checkout without registration$")
    public void proceedsToCheckoutWithoutRegistration() throws Throwable {
        CartPage.proceedToCheckout();
        CartPage.proceedToCheckoutWithoutRegistration();
    }
}
