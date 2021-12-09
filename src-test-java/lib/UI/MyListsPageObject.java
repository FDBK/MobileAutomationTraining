package lib.UI;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_TITLE_TPL,
            ARTICLE_IN_THE_LIST_BY_LINK_TPL,
            REMOVE_FROM_SAVED_BY_LINK_TPL,
            REMOVE_FROM_SAVED_BUTTON_TPL;

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* Templates */
    private static String getFolderXpathByName(String folder_name)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getSavedMWArticleXpathByLink(String link_text)
    {
        return ARTICLE_IN_THE_LIST_BY_LINK_TPL.replace("{LINK_TEXT}", link_text);
    }

    private static String getRemoveButtonByLink(String link_text)
    {
        return REMOVE_FROM_SAVED_BY_LINK_TPL.replace("{LINK_TEXT}", link_text);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON_TPL.replace("{TITLE}", article_title);
    }
    /* Templates */

    @Step("Open Reading List folder by name '{folder_name}'")
    public void openFolderByName(String folder_name)
    {
        String folder_name_xpath = getFolderXpathByName(folder_name);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Error! '" + folder_name + "' folder is not found.",
                5
        );
    }

    @Step("Wait for article to appear by title '{article_title}'")
    public void waitForArticleToAppearByTitle(String article_title)
    {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                saved_article_xpath,
                "Error! '" + article_title + "' article is not found.",
                15
        );
    }

    @Step("Wait for article to disappear by title '{article_title}'")
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                saved_article_xpath,
                "Error! '" + article_title + "' article was not deleted and is still in the List.",
                15
        );
    }

    @Step("Wait for article to appear by link '{link_text}'")
    public void waitForArticleToAppearByLink(String link_text)
    {
        String saved_article_xpath = getSavedMWArticleXpathByLink(link_text);
        this.waitForElementPresent(
                saved_article_xpath,
                "Error! '" + link_text + "' article is not found.",
                15
        );
    }
    @Step("Wait for article to disappear by link '{link_text}'")
    public void waitForArticleToDisappearByLink(String link_text)
    {
        String saved_article_xpath = getSavedMWArticleXpathByLink(link_text);
        this.waitForElementNotPresent(
                saved_article_xpath,
                "Error! '" + link_text + "' article was not deleted and is still in the List.",
                15
        );
    }

    @Step("Swipe '{article_title}' article to remove it from the Reading List")
    public void swipeArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String saved_article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    saved_article_xpath,
                    "Error! No '" + article_title + "' article is found."
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Error! 'Remove' button is not found.",
                    5
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(
                    saved_article_xpath,
                    "Error! No '" + article_title + "' article is found."
            );
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Unstar article to remove it from the Watchlist by link '{link_text}'")
    public void unStarArticleToDeleteByLink(String link_text)
    {
        String remove_locator = getRemoveButtonByLink(link_text);
        this.waitForElementAndClick(
                    remove_locator,
                    "Error! 'Remove' button is not found.",
                    5
            );

        driver.navigate().refresh();
    }

}
