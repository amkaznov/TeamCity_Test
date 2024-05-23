package com.teamcity.ui.pages.admin;

import com.codeborne.selenide.SelenideElement;
import com.teamcity.ui.Selectors;
import com.teamcity.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;

public class AdminPage extends Page {
    private final SelenideElement goToBuildButton = element(Selectors.byId("homeLink"));
}
