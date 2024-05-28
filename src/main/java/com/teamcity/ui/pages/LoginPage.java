package com.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.teamcity.api.config.Config;
import com.teamcity.api.models.User;
import com.teamcity.ui.Selectors;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.element;

@Getter
public class LoginPage extends Page{
    private static final String LOGIN_PAGE_URL = "/login.html";
    private static final String LOGIN_SUPER_USER_PAGE_URL = "/login.html?super=1";
    private final SelenideElement usernameInput = element(Selectors.byId("username"));
    private final SelenideElement  passwordInput = element(Selectors.byId("password"));
    private final SelenideElement  loginAsSuperUserButton = element("span[class='greyNote']");

    public LoginPage open (){
        Selenide.open(LOGIN_PAGE_URL);
        return this;

    }

    public void login (User user)  {
        usernameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());
        clickOnSubmit();
    }

    public LoginPage openSuperUserAuthPage (){
        loginAsSuperUserButton.click();
        waitUntilPageIsLoaded();
        return this;
    }
    public LoginPage loginSuperUser ()  {
        waitUntilPageIsLoaded();
        passwordInput.sendKeys(Config.getProperty("superUserToken"));
        clickOnSubmit();
        waitUntilPageIsLoaded();
        return this;
    }




}
