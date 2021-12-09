package lib.UI;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject
{

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Open navigation bar")
    public void openNavigation()
    {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(
                    OPEN_NAVIGATION,
                    "Error! No 'Navigation' button is found.",
                    5
            );
        } else {
            System.out.println("Method openNavigation() does nothing for platform '" + Platform.getInstance().getPlatformVar() + "'.");
        }
    }

    @Step("Click 'My Lists' navigation button")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Error! No 'My lists' navigation button is found.",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Error! No 'My lists' navigation button is found.",
                    5
            );
        }
    }

}
