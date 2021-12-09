package lib.UI.iOS;

import lib.UI.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject
{

    static {
        ARTICLE_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
