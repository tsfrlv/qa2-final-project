package steps;

import cucumber.api.java.en.Given;
import pages.HomePage;

public class CommonSteps {

    @Given("^user navigates to homepage$")
    public void user_navigates_to_homepage() throws Throwable {
        HomePage.openHomePage();
    }


}
