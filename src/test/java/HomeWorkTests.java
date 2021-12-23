import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkTests {
    //Задание 1
    /*
    1. Есть ли разница между $("h1 div"); и $("h1").$("div");
    - может ли привести к тому что, поиск найдёт разные элементы?
    Если может - приведите пример, когда.

    Ответ:
    $("h1 div") - это аналог XPath //h1//div
    то есть, он найдет первый div внутри h1 любого уровня, может быть он найдет вложенный элемент

    $("h1").$("div") - это аналог  XPath //h1/div
    то есть, он найдет первый div внутри h1 первого уровня, непосредственного потомка

    например,
    <h1>
        <a>
            <div>
            "content 1"
            </div>
        </a>
        <div>
            "content 2"
        </div>
    </h1>

    $("h1 div") - вернет <div> "content 1" </div>
    $("h1").$("div") - вернет <div> "content 2" </div>
    */

    //Задание 2
    @Test
        void searchOnGithubTests() {
        //открываем github
        open("https://github.com/");
        //ищем "Selenide" через поле поиска
        $("input.header-search-input").setValue("Selenide").pressEnter();
        //переходим по ссылке "selenide/selenide"
        $("ul.repo-list a[href='/selenide/selenide']").click();
        //на открывшейся странице переходим в раздел wiki
        $("#repository-container-header #wiki-tab").click();
        //проверяем, что в разделе "Chapters" есть ссылка на "Soft assertions"
        $$x("//h2[text()='Chapters']//following-sibling::ul/li/a")
                .findBy(text("Soft assertions"))
                .shouldHave(attribute("href"))
                .shouldBe(visible)
                .click();
        //проверяем, что в разделе "Example" есть пример с "JUnit5"
        $$x("//h3[text()='Example:']//following-sibling::ol/li")
                .findBy(text("JUnit5"))
                .scrollTo()
                .shouldBe(visible);
    }

    //Задание 3
    @Test
    void dragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $$("#columns > div").get(0).shouldHave(text("A"));
        $$("#columns > div").get(1).shouldHave(text("B"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $$("#columns > div").get(0).shouldHave(text("B"));
        $$("#columns > div").get(1).shouldHave(text("A"));
    }
}
