package lib.UI.Mobile_Web;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://li[@title='{TITLE}']//div[contains(text(), '{DESCRIPTION}')]";
        SECOND_RESULT_XPATH = "xpath://li[contains(@class, 'page-summary')][2]";
        SEARCH_INPUT_TEXT = "css:form>input[type='search']";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
