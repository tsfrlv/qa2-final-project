package model;

public class Customer {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private Boolean consentNewsletter;
    private Boolean consentData;
    private String deliveryType;
    private String deliveryAddress;
    private String deliveryDate;
    private String paymentMethod;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getConsentNewsletter() {
        return consentNewsletter;
    }

    public void setConsentNewsletter(Boolean consentNewsletter) {
        this.consentNewsletter = consentNewsletter;
    }

    public Boolean getConsentData() {
        return consentData;
    }

    public void setConsentData(Boolean consentData) {
        this.consentData = consentData;
    }

    public String getDelivery() {
        return deliveryType;
    }

    public void setDelivery(String delivery) {
        this.deliveryType = delivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}
