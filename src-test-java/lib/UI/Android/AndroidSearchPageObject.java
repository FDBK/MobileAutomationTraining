package lib.UI.Android;

import lib.UI.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject
{

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text = 'No results found']";
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TPL = "xpath://parent::*[*[@text='{TITLE}'] and *[@text='{DESCRIPTION}']]";
        SEARCH_RESULTS_LIST = "id:org.wikipedia:id/page_list_item_container";
        SECOND_RESULT_XPATH = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@index=1]";
        SEARCH_INPUT_TEXT = "id:org.wikipedia:id/search_src_text";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
