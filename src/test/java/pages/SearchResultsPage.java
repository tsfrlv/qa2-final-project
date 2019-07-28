package pages;

import base.WebDriverSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SearchResultsPage {

    private static By searchBox = By.id("search_products_text_field");
    private static By searchResultsByCategoriesGrid = By.id("search-category-image-grid");
    private static By searchResultsByProductGrid = By.className("product");
    private static By sortOrderSelector = By.className("select-sort-products");
    private static By sortByStarRatingSelection = By.xpath("//span[contains(text(),'Visvairāk')]");
    private static By sortOrderDropDown = By.className("drop-product-sort-list");
    private static By productTitleParent = By.className("p-content");
    private static By productTitle = By.tagName("a");

    public static void searchForProduct(String productName) {
        WebDriverSetup.getElement(searchBox).clear();
        WebDriverSetup.getElement(searchBox).sendKeys(productName);
        WebDriverSetup.getElement(searchBox).submit();
    }

    public static void selectCategory(String category) {
        WebDriverSetup.getElement(searchResultsByCategoriesGrid).findElement(By.xpath("//strong[contains(text(),'" + category + "')]")).click();
    }

    public static void sortByStarRating() {
        WebDriverSetup.getElement(sortOrderSelector).click();
        WebDriverSetup.getElement(sortOrderDropDown).findElement(sortByStarRatingSelection).click();
    }

    public static void selectProductNameFromSearch() throws InterruptedException {
        WebElement parent = checkFoundItemsAndSelectRandomProduct().findElement(productTitleParent);
        WebElement product = WebDriverSetup.getElement(parent, productTitle);

        WebDriverSetup.scrollTo(product);
        product.click();

    }

    private static WebElement checkFoundItemsAndSelectRandomProduct() throws InterruptedException {

        WebDriverSetup.waitForPageToLoad();
        //TODO remove when some waitUntilElement method implemented
        Thread.sleep(1000);
        List<WebElement> results = WebDriverSetup.getElements(searchResultsByProductGrid);


        int min = 1;
        int max = results.size();

        Random r = new Random();
        int i = r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
        return results.get(i);
    }


}
