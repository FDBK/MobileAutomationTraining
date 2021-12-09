package lib.UI;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{

    protected static String
            TITLE,
            FOOTER,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CLOSE_SYNC_DIALOG_BUTTON,
            EXISTING_LIST_BY_SUBSTRING_TPL;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* Templates */
    private static String getExistingListXpath(String substring)
    {
        return EXISTING_LIST_BY_SUBSTRING_TPL.replace("{FOLDER_NAME}", substring);
    }
    /* Templates */

    @Step("Wait for title element to appear")
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Article title is not found.", 15);
    }

    @Step("Get article title")
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("Swipe article to the footer")
    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER,
                    "Error! End of article is not found.",
                    20
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppears(
                    FOOTER,
                    "Error! End of article is not found.",
                    40
            );
        } else {
            this.scrollWebPageWhileElementIsNotVisible(
                    FOOTER,
                    "Error! End of article is not found.",
                    40
            );
        }
    }

    @Step("Add article to the new Reading List folder and call it '{folder_name}'")
    public void addArticleToMyList(String folder_name)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Error! No 'More options' button is found.",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Error! No 'Add to reading list' option is found.",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Error! No 'Got it!' button is found.",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Error! No input for list name is found.",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                folder_name,
                "Error! No input for list name is found.",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Error! No 'OK' button is found.",
                5
        );
    }

    @Step("Add article to the existing Reading List folder '{folder_name}'")
    public void addArticleToMyExistingList(String folder_name)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Error! No 'More options' button is found.",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Error! No 'Add to reading list' option is found.",
                5
        );

        String existing_list_xpath = getExistingListXpath(folder_name);

        this.waitForElementAndClick(
                existing_list_xpath,
                "Error! '" + folder_name + "' folder is not found.",
                5
        );
    }

    @Step("Add article to 'My Saved' list")
    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfAlreadyAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Error! No 'Add to reading list' option is found.",
                5
        );
    }

    @Step("Remove article from 'My Saved' list if it's already there")
    public void removeArticleFromSavedIfAlreadyAdded()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Error! 'Remove' button is not found.",
                    5
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Error! 'Add' button is not found.",
                    5
            );
        }
    }

    @Step("Close 'Sync' dialog in iOS app")
    public void closeSyncDialog()
    {
        this.waitForElementAndClick(
                CLOSE_SYNC_DIALOG_BUTTON,
                "Error! No 'X' button is found.",
                5
        );
    }

    @Step("Close article")
    public void closeArticle()
    {
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Error! No 'X' button is found.",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform '" + Platform.getInstance().getPlatformVar() + "'.");
        }
    }

}
