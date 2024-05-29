package com.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.teamcity.ui.Selectors;

import static com.codeborne.selenide.Selenide.element;

public class ProjectPage extends Page {

    private final SelenideElement overviewHeader = element(Selectors.byDataTest("overview-header"));
    private final SelenideElement dropdownButton = overviewHeader.$(Selectors.byDataTest("ring-dropdown"));
    private final SelenideElement newBuildConfigButton = element(Selectors.byDataTest("ring-link ring-list-link ring-list-item"));

    public ProjectPage open(String projectName) {
        Selenide.open("/project/" + projectName + "?mode=builds");
        return this;
    }

    public void openNewBuildConfigMenu() {
        waitUntilPageIsLoaded();
        dropdownButton.click();
        newBuildConfigButton.click();
    }
}
