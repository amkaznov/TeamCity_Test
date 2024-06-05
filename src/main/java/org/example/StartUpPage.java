
package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;

@Getter
public class StartUpPage {
    private final SelenideElement acceptLicense = element("input[id='accept']");
    private final SelenideElement proceedButton = element("input[id='proceedButton']");
    private final SelenideElement submitButton = element("button[type='submit']");
    private final SelenideElement header = element("*[id='header']");

    public StartUpPage open(){
        Selenide.open("/");
        return this;
    }

    public StartUpPage setupTeamCityServer(){

        proceedButton.shouldBe(Condition.visible,Duration.ofMinutes(1));
        proceedButton.click();
        proceedButton.shouldBe(Condition.visible,Duration.ofMinutes(1));
        proceedButton.click();
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        clickOnSubmit();
        return this;
    }
    public void clickOnSubmit() {
        submitButton.click();
    }
}
