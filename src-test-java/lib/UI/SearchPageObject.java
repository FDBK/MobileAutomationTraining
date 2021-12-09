package lib.UI;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject
{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL,
            SEARCH_RESULTS_LIST,
            SECOND_RESULT_XPATH,
            SEARCH_INPUT_TEXT;

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* Templates */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultByTitleAndDescription(String title, String description)
    {
        String add_title = SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL.replace("{TITLE}", title);
        return add_title.replace("{DESCRIPTION}", description);
    }
    /* Templates */

    @Step("Initialize 'Search' input field")
    public void initSearchInput()
    {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Error! Search Init Element is not found."
        );
        this.waitForElementAndClick(
                SEARCH_INIT_ELEMENT,
                "Error! Search Init Element is not found, unable to click on it.",
                5
        );
    }

    @Step("Wait for 'Close' button to appear")
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(
                SEARCH_CANCEL_BUTTON,
                "Error! 'Close' button is not found.",
                5
        );
    }

    @Step("Wait for 'Close' button to disappear")
    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Error! 'Close' button is still visible.",
                5
        );
    }

    @Step("Click 'Close' button")
    public void clickCancelButton()
    {
        this.waitForElementAndClick(
                SEARCH_CANCEL_BUTTON,
                "Error! 'Close' button is not found.",
                5
        );
    }

    @Step("Type '{search_line}' into the 'Search' input field")
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(
                SEARCH_INPUT,
                search_line,
                "Error! Search input is not found",
                10
        );
    }

    @Step("Wait for '{substring}' search result to appear")
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Error! '" + substring + "' string is not found in search results."
        );
    }

    @Step("Click article with the '{substring}' title")
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                search_result_xpath,
                "Error! '" + substring + "' string is not found in search results.",
                10
        );
    }

    @Step("Get element name/text attribute for the '{substring}' article and click that article")
    public String getElementNameBySubstringAndClick(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        WebElement element = this.waitForElementPresent(
                search_result_xpath,
                "Error! '" + substring + "' string is not found in search results.",
                10
        );
        String attribute = "name";
        if (Platform.getInstance().isAndroid()) {
            attribute = "text";
        }
        String element_name = "";
        if (Platform.getInstance().isMW()) {
            element_name = element.getText();
        } else {
            element_name = element.getAttribute(attribute);
        }
        element.click();
        return element_name.toLowerCase();
    }

    @Step("Get amount of found articles")
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Error! No results found.",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    @Step("Wait for 'Empty Results' label to appear")
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Error! Found some results.",
                15
        );
    }

    @Step("Assert there are no search results (without waiting)")
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "Error! Found some results."
        );
    }

    @Step("Wait for article with the '{title}' title and the '{description}' description to appear")
    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultByTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Error! There is no '" + title + "' result with the '" + description + "' description on the page!",
                10
        );
        System.out.println("OK: '" + title + "' result with the '" + description + "' description is present on the page.");
    }

    @Step("Check if there are multiple search results")
    public void checkForMultipleResults()
    {
        this.waitForElementPresent(
                SECOND_RESULT_XPATH,
                "Error! No multiple search results.",
                10
        );
        System.out.println("OK: There are multiple search results.");
    }

    @Step("Clear 'Search' input field")
    public void clearSearchInput()
    {
        this.waitForElementAndClear(
                SEARCH_INPUT_TEXT,
                "Error! Search input is not found.",
                5
        );
    }

    @Step("Check that search results disappear")
    public void checkThatResultsDisappear()
    {
        this.waitForElementNotPresent(
                SEARCH_RESULTS_LIST,
                "Error! Search results are still visible.",
                10
        );
        System.out.println("OK: There are no visible search results on the page anymore.");
    }

}
