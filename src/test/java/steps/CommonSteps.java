package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import pages.HomePage;

public class CommonSteps {

    @Given("^user navigates to homepage$")
    public void user_navigates_to_homepage() throws Throwable {
        HomePage.openHomePage();
    }

    @And("^selects product$")
    public void selectsProduct() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
