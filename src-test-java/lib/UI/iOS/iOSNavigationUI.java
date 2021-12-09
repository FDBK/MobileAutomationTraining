package lib.UI.iOS;

import lib.UI.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI
{

    static {
        MY_LISTS_LINK = "id:Saved";
    }

    public iOSNavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

}
