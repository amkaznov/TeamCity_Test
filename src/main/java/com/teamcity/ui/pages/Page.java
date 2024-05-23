package com.teamcity.ui.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.teamcity.ui.Selectors;
import com.teamcity.ui.elements.PageElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.element;

public class Page {
    private final SelenideElement submitButton = element(Selectors.byType("submit"));
    private final SelenideElement savingWaitingMarker = element(Selectors.byId("saving"));
    private final SelenideElement pageWaitingMarker = element(Selectors.byDataTest("ring-loader"));
    private final SelenideElement pageTitle = element(Selectors.byId("restPageTitle"));

    public void waitUntilPageIsLoaded() {
//        pageWaitingMarker.shouldBe(Condition.visible, Duration.ofMinutes(1));
        pageWaitingMarker.shouldNotBe(Condition.visible, Duration.ofMinutes(1));
    }
    public void waitUntilDataIsSavedIcon() {
        savingWaitingMarker.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
    public void waitUntilDataIsSaved() {
        savingWaitingMarker.shouldNotBe(Condition.visible, Duration.ofSeconds(30));
    }
    public void clickOnSubmit() {
        submitButton.click();
        waitUntilDataIsSaved();
    }
    public void clickOnSubmitAndWait() {
        submitButton.click();
        waitUntilDataIsSavedIcon();
        waitUntilDataIsSaved();
    }

    public void checkPageTitle (String value ){
        pageTitle.shouldHave(Condition.text(value));
    }


    public  <T extends PageElement> List<T> generatePageElements(
            ElementsCollection collection,
            Function<SelenideElement, T> creator) {
        var elements = new ArrayList<T>();
        collection.forEach(webElement -> elements.add(creator.apply(webElement)));
        return elements;
    }

}
