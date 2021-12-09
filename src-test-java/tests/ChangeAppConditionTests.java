package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.Factories.ArticlePageObjectFactory;
import lib.UI.Factories.SearchPageObjectFactory;
import lib.UI.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for changing app conditions")
public class ChangeAppConditionTests extends CoreTestCase
{

    @Test
    @Feature(value = "Screen Orientation")
    @DisplayName("Test how changing screen orientation affects search results")
    @Description("Search for article and check that article title is still the same after some screen orientation changes")
    @Step("Start the 'testChangeScreenOrientationOnSearchResults' test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testChangeScreenOrientationOnSearchResults()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed.",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed.",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Feature(value = "Background")
    @DisplayName("Test how switching app to background affects search results")
    @Description("Search for article and check that search results don't crash after running app in background")
    @Step("Start the 'testCheckSearchArticleInBackground' test")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground()
    {
        if (Platform.getInstance().isMW()) {
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(2);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

}
