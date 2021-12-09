package lib.UI.Factories;

import lib.Platform;
import lib.UI.Android.AndroidNavigationUI;
import lib.UI.Mobile_Web.MWNavigationUI;
import lib.UI.NavigationUI;
import lib.UI.iOS.iOSNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory
{

    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }

}
