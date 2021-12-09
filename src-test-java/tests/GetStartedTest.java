package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for 'Get started' screen")
public class GetStartedTest extends CoreTestCase
{

    @Test
    @Feature(value = "Welcome screen (iOS)")
    @DisplayName("Pass through iOS welcome screen")
    @Description("Click all the buttons needed to pass the welcome screen in iOS app")
    @Step("Start the 'testPassThroughWelcome' test")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testPassThroughWelcome()
    {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }

}
