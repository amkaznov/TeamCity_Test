package com.teamcity.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import com.teamcity.ui.Selectors;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewProjectPage extends AdminPage {

    private final SelenideElement urlInput = element(Selectors.byId("url"));
    private final SelenideElement urlRepoInput = element(Selectors.byId("repositoryUrl"));
    private final SelenideElement projectNameInput = element(Selectors.byId("projectName"));
    private final SelenideElement buildTypeNameInput = element(Selectors.byId("buildTypeName"));
    private final SelenideElement buildTypeExternalIdInput = element(Selectors.byId("buildTypeExternalId"));
    private final SelenideElement useManuallyButton = element(new ByAttribute("data-hint-container-id", "create-build-configuration"));


    public CreateNewProjectPage openProjectMenu(String parentProjectId) {
        Selenide.open("/admin/createObjectMenu.html?projectId=" + parentProjectId + "&showMode=createProjectMenu");
        return this;
    }

    public CreateNewProjectPage openBuildTypeMenu(String parentProjectId) {
        Selenide.open("/admin/createObjectMenu.html?projectId=" + parentProjectId + "&showMode=createBuildTypeMenu");
        return this;
    }

    public CreateNewProjectPage createProjectOrBuildConfigByUrl(String url) {
        waitUntilPageIsLoaded();
        urlInput.sendKeys(url);
        clickOnSubmitAndWait();
        return this;
    }

    public CreateNewProjectPage createBuildConfigByManually(String buildTypeName, String buildTypeExternalId) {
        useManuallyButton.click();
        buildTypeNameInput.sendKeys(buildTypeName);
        buildTypeExternalIdInput.clear();
        buildTypeExternalIdInput.sendKeys(buildTypeExternalId);
        clickOnSubmit();
        return this;
    }

    public CreateNewProjectPage setVscRoot(String urlRepo) {
        urlRepoInput.sendKeys(urlRepo);
        clickOnSubmitAndWait();
        return this;
    }


    public void setupProject(String projectName, String buildTypeName) {
        projectNameInput.clear();
        projectNameInput.sendKeys(projectName);
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        clickOnSubmit();
        waitAdminPage();

    }

    public CreateNewProjectPage setupBuildConfig(String buildTypeName) {
        buildTypeNameInput.clear();
        buildTypeNameInput.sendKeys(buildTypeName);
        clickOnSubmit();
        return this;
    }

}
