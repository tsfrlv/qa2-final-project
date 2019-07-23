package steps;

import base.BaseStep;
import cucumber.api.java.en.Given;
import pages.HomePage;

public class CommonSteps extends BaseStep {

    @Given("^user navigates to homepage$")
    public void user_navigates_to_homepage() throws Throwable {
        HomePage.openHomePage();
    }
}
