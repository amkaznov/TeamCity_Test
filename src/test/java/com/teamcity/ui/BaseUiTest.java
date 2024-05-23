package com.teamcity.ui;

import com.codeborne.selenide.Configuration;
import com.teamcity.BaseTest;
import com.teamcity.api.config.Config;
import com.teamcity.api.models.NewProjectDescription;
import com.teamcity.api.models.Project;
import com.teamcity.api.models.User;
import com.teamcity.api.requests.checked.CheckedBase;
import com.teamcity.api.spec.Specifications;
import com.teamcity.ui.pages.LoginPage;
import org.testng.annotations.BeforeSuite;

import static com.teamcity.api.enums.Endpoint.PROJECTS;
import static com.teamcity.api.enums.Endpoint.USERS;

public class BaseUiTest extends BaseTest {
    @BeforeSuite
    public void setupUiTests() {

        Configuration.baseUrl = "http://" + Config.getProperty("host");
        Configuration.remote = Config.getProperty("remote");
        Configuration.pageLoadTimeout = 30000;
        Configuration.reportsFolder = "target/surefire-reports";
        Configuration.downloadsFolder = "target/downloads";

        BrowserSetting.setup(Config.getProperty("browser"));
    }

    public void loginAsUser(User user) {
        checkedSuperUser.getRequest(USERS).create(user);
        new LoginPage().open().login(user);
    }

    public void makeProjectAndLogin(NewProjectDescription newProjectDescription, User user) {
        checkedSuperUser.getRequest(USERS).create(user);
        var checkedProjectRequest = new CheckedBase(Specifications.getSpec()
                .authSpec(user), PROJECTS);
        var project = (Project) checkedProjectRequest.create(newProjectDescription);

        new LoginPage().open().login(user);
    }
}
