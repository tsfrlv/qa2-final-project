package steps;

import base.WebDriverSetup;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import model.Customer;
import model.Product;
import org.junit.Assert;
import pages.CartPage;
import pages.OrderPage;

import java.util.Map;

import static testHelpers.TestProperties.*;

public class OrderSteps {
    Product product;
    Customer customer;

    public OrderSteps(Product product, Customer customer) {
        this.customer = customer;
        this.product = product;
    }

    @And("^picks (.*)$")
    public void selectsDeliveryMethod(String deliveryMethod) throws Throwable {
        switch (deliveryMethod) {
            case URIEKSTE:
                this.customer.setDeliveryAddress(URIEKSTE_ADDRESS + " (" + URIEKSTE + ")");
                break;
            case MUKUSALA:
                this.customer.setDeliveryAddress(MUKUSALA_ADDRESS + " (" + MUKUSALA + ")");
                break;
            default:
                this.customer.setDeliveryAddress(URIEKSTE_ADDRESS + " (" + URIEKSTE + ")");
                break;
        }
        OrderPage.selectDeliveryMethod(deliveryMethod);
    }

    @Then("^enters user data$")
    public void entersUserData(Map<String, String> userData) throws Throwable {
        String name = userData.get("name");
        this.customer.setName(name);
        String surname = userData.get("surname");
        this.customer.setSurname(surname);
        String email = userData.get("email");
        this.customer.setEmail(email);
        String phone = userData.get("phone");
        this.customer.setPhone(phone);
        OrderPage.enterUserData(name, surname, email, phone);

    }

    @And("^checks (.*)$")
    public void selectsPaymentMethod(String desiredPaymentMethod) throws Throwable {
        String paymentMethod;
        String contactType = "E-pastā";
        switch (desiredPaymentMethod) {
            case TRANSFER_INDIVIDUAL_PERSON:
                this.customer.setPaymentMethod(TRANSFER_INDIVIDUAL_PERSON);
                paymentMethod = "TRANSFER_INDIVIDUAL_PERSON";
                break;
            case ONLINE_CARD_INDIVIDUAL_PERSON:
                this.customer.setPaymentMethod(ONLINE_CARD_INDIVIDUAL_PERSON);
                paymentMethod = "ONLINE_CARD_INDIVIDUAL_PERSON";
                break;
            default:
                this.customer.setPaymentMethod(TRANSFER_INDIVIDUAL_PERSON);
                paymentMethod = "ONLINE_CARD_INDIVIDUAL_PERSON";
                break;
        }
        OrderPage.selectPaymentMethod(paymentMethod, contactType);
        CartPage.proceedToCheckout();
    }

    @Then("^completes the order$")
    public void completesTheOrder() throws Throwable {
        //TODO make it soft
        Assert.assertEquals((OrderPage.finalProductName().getText()).toLowerCase(), (this.product.getName()).toLowerCase());
        Assert.assertEquals(OrderPage.finalPrice().getText(), this.product.getPrice());
        Assert.assertEquals(OrderPage.finalUserData().getText(), this.customer.getUserData());
        Assert.assertTrue((OrderPage.finalSelectedPaymentMethod().getText()).contains((this.customer.getPaymentMethod()).toUpperCase()));
        Assert.assertEquals(OrderPage.finalDeliveryAddress().getText(), this.customer.getDeliveryAddress());
        WebDriverSetup.getDriver().manage().deleteAllCookies();
    }

    @And("^agrees with terms and proceeds to payment method$")
    public void agreesWithTerms() throws Throwable {
        OrderPage.agreeWithTermsAndProceed();

    }

    @And("^sets (.*)$")
    public void setsNewsletterSettings(String newsletterSettings) throws Throwable {
        OrderPage.setNewsletterSettings(newsletterSettings);
    }
}
