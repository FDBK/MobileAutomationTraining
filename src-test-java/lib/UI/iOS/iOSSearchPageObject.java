package lib.UI.iOS;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject
{

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeCell/XCUIElementTypeLink[contains(@name, '{TITLE}')]/../XCUIElementTypeLink[contains(@name, '{DESCRIPTION}')]/..";
        SEARCH_RESULTS_LIST = "xpath://XCUIElementTypeLink";
        SECOND_RESULT_XPATH = "xpath://XCUIElementTypeCell[2]";
        SEARCH_INPUT_TEXT = "xpath://XCUIElementTypeSearchField";
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
