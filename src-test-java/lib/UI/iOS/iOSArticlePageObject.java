package lib.UI.iOS;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject
{

    static {
        TITLE = "id:Java (programming language)";
        FOOTER = "id:View article in browser']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_SYNC_DIALOG_BUTTON = "id:places auth close";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

}
