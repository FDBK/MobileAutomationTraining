package lib.UI;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject
{

    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
            NEXT_LINK = "id:Next",
            GET_STARTED_BUTTON = "id:Get started",
            SKIP = "id:Skip";

    public WelcomePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Wait for 'Learn More...' link to appear")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Error! No 'Learn more...' element found.",
                10
        );
    }

    @Step("Wait for 'New ways to explore...' text to appear")
    public void waitForNewWaysText()
    {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE,
                "Error! No 'New ways to explore...' element found.",
                10
        );
    }

    @Step("Wait for 'Add or edit preferred languages...' text to appear")
    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES,
                "Error! No 'Add or edit preferred languages...' element found.",
                10
        );
    }

    @Step("Wait for 'Learn more about data collected...' text to appear")
    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Error! No 'Learn more about data collected...' element found.",
                10
        );
    }

    @Step("Click 'Next' button")
    public void clickNextButton()
    {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Error! No 'Next' button found.",
                10
        );
    }

    @Step("Click 'Get started' button")
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Error! No 'Get started' button found.",
                10
        );
    }

    @Step("Click 'Skip' button")
    public void clickSkip()
    {
        this.waitForElementAndClick(
                SKIP,
                "Error! No 'Skip' button found.",
                10
        );
    }

}
