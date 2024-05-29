package com.teamcity.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.teamcity.ui.Selectors;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;

@Getter
public class StartUpPage extends Page{
    private final SelenideElement acceptLicense = element("input[id='accept']");
    private final SelenideElement proceedButton = element("input[id='proceedButton']");
    private final SelenideElement restoreFromBackupButton = element("input[id='reset']");
    private final SelenideElement backupFileUploaded = element("input[name='bakupFile']");
    private final SelenideElement header = element(Selectors.byId("header"));

    public StartUpPage open(){
        Selenide.open("/");
        return this;
    }

    public StartUpPage setupTeamCityServer(){
        var aa=1;
//        waitUntilPageIsLoadedIcon();
        proceedButton.shouldBe(Condition.visible,Duration.ofMinutes(1));
        proceedButton.click();
        System.out.println(aa+1);
//        waitUntilPageIsLoadedIcon();
        proceedButton.shouldBe(Condition.visible,Duration.ofMinutes(1));
        proceedButton.click();
        System.out.println(aa+1);
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        System.out.println(aa+1);
        clickOnSubmit();
        return this;
    }
}
