package com.teamcity.ui.pages.favorites;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.teamcity.ui.elements.ProjectElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends FavoritesPage {
    private static final String FAVORITE_PROJECTS_URL = "/favorite/projects";
    private final ElementsCollection subprojects = elements(By.xpath("//*[@role='heading']"));


    public ProjectsPage open() {
        Selenide.open(FAVORITE_PROJECTS_URL);
        waitUntilFavoritePageIsLoaded();
        return this;
    }

    public List<ProjectElement> getSubprojects() {
        return generatePageElements(subprojects, ProjectElement::new);
    }

}

