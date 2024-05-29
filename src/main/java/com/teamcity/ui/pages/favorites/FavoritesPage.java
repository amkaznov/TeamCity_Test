package com.teamcity.ui.pages.favorites;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.teamcity.ui.pages.Page;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;

public class FavoritesPage extends Page {
    private final SelenideElement header = element(By.xpath("//*[contains(@class, 'ProjectPageHeader__title')]"));

    public void waitUntilFavoritePageIsLoaded() {
        waitUntilPageIsLoaded();
        header.shouldBe(Condition.visible, Duration.ofSeconds(10));

    }

}
