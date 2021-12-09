package lib.UI.Mobile_Web;

import lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject
{

    static {
        ARTICLE_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        ARTICLE_IN_THE_LIST_BY_LINK_TPL = "xpath://ul[contains(@class, 'watchlist')]//a[@href='{LINK_TEXT}']";
        REMOVE_FROM_SAVED_BY_LINK_TPL = "xpath://ul[contains(@class, 'watchlist')]//a[@href='{LINK_TEXT}']/../a[contains(@class, 'watched')]";
        REMOVE_FROM_SAVED_BUTTON_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watched')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
