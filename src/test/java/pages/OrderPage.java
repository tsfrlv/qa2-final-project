package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private static By availableDeliveryGrid = By.id("available_delivery_types_container");
    private static By orderNameForm = By.id("order_main_data_name");
    private static By orderSurnameForm = By.id("order_main_data_surname");
    private static By orderEmailForm = By.id("order_main_data_email");
    private static By orderPhoneForm = By.id("order_main_data_contact_phone_number");
    private static By orderConsentCheckbox = By.id("accept_purchase_agreement");
    private static By orderNewsletterFormHeader = By.xpath("//label[contains(text(),'jaunumus')]");
    private static By orderNewsletterFormInput = By.tagName("input");
    private static By paymentMethods = By.id("payment-tabs");
    private static By orderContactOnlineCardSelect = By.id("order_payment_type_individual_person_online_card_contact_type");
    private static By orderContactTransferSelect = By.id("order_payment_type_individual_person_transfer_contact_type");

    private static By priceLocator = By.id("shopping-cart-total-amount");
    private static By productNameLocator = By.className("ait-cart-item-info");
    private static By userDataLocator = By.xpath("//h3[contains(text(),'Klients')]/following-sibling::p[1]");
    private static By deliveryAddressLocator = By.xpath("//h3[contains(text(),'Piegādes adrese')]/following-sibling::p[1]");
    private static By selectedPaymentMethodLocator = By.xpath("//*[@id='main']/div/div/form/div[3]/h2");

    public static WebElement finalPrice () {
        return WebDriverSetup.getElement(priceLocator);
    }
    public static WebElement finalProductName () {
        return WebDriverSetup.getElement(productNameLocator);
    }
    public static WebElement finalUserData () {
        return WebDriverSetup.getElement(userDataLocator);
    }
    public static WebElement finalDeliveryAddress () {
        return WebDriverSetup.getElement(deliveryAddressLocator);
    }
    public static WebElement finalSelectedPaymentMethod () {
        return WebDriverSetup.getElement(selectedPaymentMethodLocator);
    }

    public static void selectDeliveryMethod(String deliveryMethod) {
        WebElement selectedDeliveryMethod = WebDriverSetup.getElement(availableDeliveryGrid).findElement(By.xpath("//strong[contains(text(),'" + deliveryMethod + "')]"));

        WebDriverSetup.scrollTo(selectedDeliveryMethod);
        selectedDeliveryMethod.click();
    }

    public static void enterUserData(String name, String surname, String email, String phone) {
        WebDriverSetup.getElement(orderNameForm).sendKeys(name);
        WebDriverSetup.getElement(orderSurnameForm).sendKeys(surname);
        WebDriverSetup.getElement(orderEmailForm).sendKeys(email);
        WebDriverSetup.getElement(orderPhoneForm).sendKeys(phone);

    }

    public static void agreeWithTermsAndProceed() {
        WebElement checkbox = WebDriverSetup.getElement(orderConsentCheckbox);
        WebDriverSetup.scrollTo(checkbox);
        checkbox.click();
        checkbox.submit();
    }

    public static void setNewsletterSettings(String newsletterSettings) {

        WebElement inputParent = WebDriverSetup.getElement(orderNewsletterFormHeader).findElement(By.xpath("./.."));
        WebElement input = WebDriverSetup.getElement(inputParent, orderNewsletterFormInput);
        //hardcoded way; got tired fighting with windmills
        input.click();
        input.sendKeys(newsletterSettings);
    }

    public static void selectPaymentMethod(String desiredPaymentMethod, String contactType) throws InterruptedException {
        WebElement selectedPaymentMethod = WebDriverSetup.getElement(paymentMethods).findElement(By.xpath("//a[@payment_type='" + desiredPaymentMethod + "']"));

        selectedPaymentMethod.click();

        //no time, this will be here
        By paymentType = null;
        switch (desiredPaymentMethod) {
            case "TRANSFER_INDIVIDUAL_PERSON":
                paymentType = orderContactTransferSelect;
                break;
            case "ONLINE_CARD_INDIVIDUAL_PERSON":
                paymentType = orderContactOnlineCardSelect;
                break;
        }



        WebElement inputParent = WebDriverSetup.getElement(paymentType).findElement(By.xpath("./.."));
        WebElement input = WebDriverSetup.getElement(inputParent, orderNewsletterFormInput);

        WebDriverSetup.scrollTo(input);
        //yet another windmills
        new WebDriverWait(WebDriverSetup.getDriver(), 2).until(ExpectedConditions.elementToBeClickable(input)).click();
        input.sendKeys(contactType);
    }
}
