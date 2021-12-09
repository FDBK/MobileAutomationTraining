package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.UI.*;
import lib.UI.Factories.ArticlePageObjectFactory;
import lib.UI.Factories.MyListsPageObjectFactory;
import lib.UI.Factories.NavigationUIFactory;
import lib.UI.Factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for Watchlist / Reading List / My List")
public class MyListsTests extends CoreTestCase
{

    private static String folder_name = "Learning programming";
    private static final String
            login = "FDBK",
            password = "t.1396.WC";

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Reading List")})
    @DisplayName("Save first article to the Reading List, check and remove it")
    @Description("Open an article, add it to the Reading List, check the List and remove the article from it")
    @Step("Start the 'testSaveFirstArticleToMyList' test")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String article_full_title = SearchPageObject.getElementNameBySubstringAndClick("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String article_title = "";
        if (Platform.getInstance().isMW()) {
            ArticlePageObject.waitForTitleElement();
            article_title = ArticlePageObject.getArticleTitle();
        }

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            if (Platform.getInstance().isIOS()) {
                ArticlePageObject.closeSyncDialog();
            }
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals(
                    "Error! We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(folder_name);
        }

        if (Platform.getInstance().isMW()) {
            MyListsPageObject.swipeArticleToDelete(article_title);
        } else {
            MyListsPageObject.swipeArticleToDelete(article_full_title);
        }
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Reading List")})
    @DisplayName("Add two articles to the Reading List, check and remove one of them")
    @Description("Open and add two articles to the Reading List, check the List and remove one of the articles from it")
    @Step("Start the 'testArticleList' test")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testArticleList()
    {
        // Присвоение значений переменным
        String search_text = "Java";
        String search_result_description_1 = "Object-oriented programming language";
        String search_result_description_2 = "Island of Indonesia";
        String list_item_1 = "object-oriented programming language";
        String list_item_2 = "island of Indonesia";
        String mw_link_1 = "/wiki/Java_(programming_language)";
        String mw_link_2 = "/wiki/Java";
        if (Platform.getInstance().isIOS()) {
            list_item_1 = "Object-oriented programming language";
            list_item_2 = "Indonesian island";
        }
        if (Platform.getInstance().isMW()) {
            search_result_description_2 = "Indonesian island";
        }
        String folder_name = "Must read!";

        // Инициализация необходимых библиотек
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        // Поиск и открытие первой статьи, запись её текущего названия/описания в переменную
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_text);
        String initial_article_full_title = SearchPageObject.getElementNameBySubstringAndClick(search_result_description_1);

        // Добавление первой статьи в список и её закрытие (+ авторизация при необходимости)
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            if (Platform.getInstance().isIOS()) {
                ArticlePageObject.closeSyncDialog();
            }
        }
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
        }

        // Поиск и открытие второй статьи
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.clickByArticleWithSubstring(search_result_description_2);

        // Добавление второй статьи в список и её закрытие
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyExistingList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        // Открытие списка с сохранёнными статьями
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(folder_name);
        }

        // Удаление второй статьи из списка и проверка её исчезновения
        if (Platform.getInstance().isMW()) {
            MyListsPageObject.unStarArticleToDeleteByLink(mw_link_2);
            MyListsPageObject.waitForArticleToDisappearByLink(mw_link_2);
        } else {
            MyListsPageObject.swipeArticleToDelete(list_item_2);
            MyListsPageObject.waitForArticleToDisappearByTitle(list_item_2);
        }

        // Открытие первой статьи, запись её текущего названия/описания в переменную
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isIOS())) {
            String current_article_full_title = SearchPageObject.getElementNameBySubstringAndClick(list_item_1);

        // Проверка, не изменилось ли название/описание статьи после всех произведённых действий
            Assert.assertEquals(
                    "Error! Unexpected article title: '" + current_article_full_title + "' instead of '" + initial_article_full_title + "'.",
                    current_article_full_title,
                    initial_article_full_title);
        } else {
            MyListsPageObject.waitForArticleToAppearByLink(mw_link_1);
        }

        // Вывод сообщения об успешном проведении теста
        System.out.println("OK! Everything went fine!");
    }

}
