package steps;

import cucumber.api.java.en.And;
import pages.SearchResultsPage;

public class SearchSteps {

    @And("enters (.*) into search bar")
    public void entersProductNameIntoSearchBar(String productName) throws Throwable {
        SearchResultsPage.searchForProduct(productName);
    }

    @And("^selects (.*)$")
    public void selectsCategory(String category) throws Throwable {
        SearchResultsPage.selectCategory(category);
    }

    @And("^filters products by start rating$")
    public void filtersProductsByStartRating() throws Throwable {
        SearchResultsPage.sortByStarRating();
    }

    @And("^chooses product from search results$")
    public void choosesProductFromSearchResults() throws Throwable {
        SearchResultsPage.selectProductNameFromSearch();
    }
}
