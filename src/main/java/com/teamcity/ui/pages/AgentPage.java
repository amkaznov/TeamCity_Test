package com.teamcity.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;

public class AgentPage extends  Page{
    private final SelenideElement authorizeButton = element("button[data-test-authorize-agent='true']");

    public AgentPage openFirstUnAuthAgent(){
        System.out.println("openFirstUnAuthAgent");
        Selenide.open("/agent/1#all-pools");
        waitUntilPageIsLoaded();
        System.out.println("openFirstUnAuthAgent");
        return this;
    }
    public void authorizeFirstAgent(){
        System.out.println("authorizeFirstAgent");
        authorizeButton.shouldBe(Condition.visible, Duration.ofSeconds(30));
        authorizeButton.click();
        clickOnSubmitButton();
        authorizeButton.shouldBe(Condition.text("Unauthorize"));
        System.out.println("authorizeFirstAgent");
    }
}
