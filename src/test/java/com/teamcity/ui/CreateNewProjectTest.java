package com.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.teamcity.api.models.BuildType;
import com.teamcity.api.models.NewProjectDescription;
import com.teamcity.api.models.User;
import com.teamcity.ui.pages.ProjectPage;
import com.teamcity.ui.pages.admin.CreateNewProjectPage;
import com.teamcity.ui.pages.favorites.ProjectsPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.teamcity.api.enums.Endpoint.*;

public class CreateNewProjectTest extends BaseUiTest {
    @Test
    public void authorizedUserShouldBeAbleCreateNewProjectByGitHub() {
        loginAsUser((User) testData.get(USERS));
        var url = "https://github.com/amkaznov/testTC.git";

        new CreateNewProjectPage()
                .openProjectMenu(((NewProjectDescription) testData.get(PROJECTS)).getParentProject().getLocator())
                .createProjectOrBuildConfigByUrl(url)
                .setupProject(((NewProjectDescription) testData.get(PROJECTS)).getName(), ((BuildType) testData.get(BUILD_TYPES)).getName());
        new ProjectsPage()
                .open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(((NewProjectDescription) testData.get(PROJECTS)).getName()));

    }

    @Test
    public void authorizedUserShouldBeAbleCreateNewBuildConfigurationByGitHub() {
        var url = "https://github.com/amkaznov/testTC.git";
        makeProjectAndLogin((NewProjectDescription) testData.get(PROJECTS), (User) testData.get(USERS));

        new ProjectsPage()
                .open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(((NewProjectDescription) testData.get(PROJECTS)).getName()))
                .find(By.cssSelector("h2")).click();
        new ProjectPage()
                .openNewBuildConfigMenu();
        new CreateNewProjectPage()
                .createProjectOrBuildConfigByUrl(url)
                .setupBuildConfig(((BuildType) testData.get(BUILD_TYPES)).getName())
                .checkPageTitle(((BuildType) testData.get(BUILD_TYPES)).getName());
    }

    @Test
    public void authorizedUserShouldBeAbleCreateNewBuildConfigurationByManually() {
        var url = "https://github.com/amkaznov/testTC.git";
        makeProjectAndLogin((NewProjectDescription) testData.get(PROJECTS), (User) testData.get(USERS));
        var projectsPage = new ProjectsPage();

        projectsPage
                .open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(((NewProjectDescription) testData.get(PROJECTS)).getName()))
                .find(By.cssSelector("h2")).click();
        new ProjectPage()
                .openNewBuildConfigMenu();
        new CreateNewProjectPage()
                .createBuildConfigByManually(((BuildType) testData.get(BUILD_TYPES)).getName(), ((BuildType) testData.get(BUILD_TYPES)).getId())
                .setVscRoot(url)
                .checkPageTitle(((BuildType) testData.get(BUILD_TYPES)).getName());
    }
}
