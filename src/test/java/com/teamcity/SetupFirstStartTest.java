package com.teamcity;

import com.codeborne.selenide.Condition;
import com.teamcity.ui.BaseUiTest;
import com.teamcity.ui.pages.AgentPage;
import com.teamcity.ui.pages.LoginPage;
import com.teamcity.ui.pages.StartUpPage;
import org.testng.annotations.Test;


public class SetupFirstStartTest extends BaseUiTest {
    @Test
    public void startUpTest(){
        new StartUpPage()
                .open()
                .setupTeamCityServer()
                .getHeader().shouldHave(Condition.text("Create Administrator Account"));
    }

    @Test
    public void setupTeamCityAgentTest() {
        new LoginPage()
                .openSuperUserAuthPage()
                .loginSuperUser();
        new AgentPage()
                .openFirstUnAuthAgent()
                .authorizeFirstAgent();
    }
}

