package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.Factories.SearchPageObjectFactory;
import lib.UI.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Search function")
public class SearchTests extends CoreTestCase
{

    @Test
    @Feature(value = "Search")
    @DisplayName("Check search results by titles and descriptions")
    @Description("Search for 'Java' and wait for three correct search results to appear")
    @Step("Start the 'testSearchByTitleAndDescription' test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearchByTitleAndDescription()
    {
        String[] Titles = new String[]{"Java", "JavaScript", "Java (programming language)"};
        String[] Descriptions = new String[]{"Island of Indonesia", "Programming language", "Object-oriented programming language"};

        if (Platform.getInstance().isMW()) {
            Descriptions[0] = "Indonesian island";
            Descriptions[1] = "High-level programming language";
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        for (int i = 0; i < 3; i++) {
            SearchPageObject.waitForElementByTitleAndDescription(Titles[i], Descriptions[i]);
        }
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check search results")
    @Description("Search for 'Java' and wait for the 'Object-oriented...' search result to appear")
    @Step("Start the 'testSearch' test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Test 'Cancel Search' button")
    @Description("Initialize 'Search' input field, check that the 'Cancel Search' button is present and works fine")
    @Step("Start the 'testCancelSearch' test")
    @Severity(value = SeverityLevel.MINOR)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check search results (not empty)")
    @Description("Search for 'Linkin Park Discography' and check that there are some search results")
    @Step("Start the 'testAmountOfNotEmptySearch' test")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("Too few results found.", amount_of_search_results > 0);
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check search results (empty)")
    @Description("Search for 'xkfdkfirkf' and check that there are no search results")
    @Step("Start the 'testAmountOfEmptySearch' test")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "xkfdkfirkf";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Search, clear 'Search' input field and check")
    @Description("Search for 'Java', check for multiple results, clear the input and check that results disappear")
    @Step("Start the 'testSearchClearNCheck' test")
    @Severity(value = SeverityLevel.MINOR)
    public void testSearchClearNCheck()
    {
        String search_line = "Java";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.checkForMultipleResults();
        SearchPageObject.clearSearchInput();
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())) {
            SearchPageObject.checkThatResultsDisappear();
        }
    }

}
