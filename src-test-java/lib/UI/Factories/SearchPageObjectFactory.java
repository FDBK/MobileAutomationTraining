package lib.UI.Factories;

import lib.Platform;
import lib.UI.Android.AndroidSearchPageObject;
import lib.UI.Mobile_Web.MWSearchPageObject;
import lib.UI.SearchPageObject;
import lib.UI.iOS.iOSSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory
{

    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new iOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }

}
