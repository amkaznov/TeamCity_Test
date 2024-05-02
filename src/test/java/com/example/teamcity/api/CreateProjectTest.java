package com.example.teamcity.api;

import com.example.teamcity.api.generators.RandomData;
import org.apache.hc.core5.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseApiTest {


    @BeforeMethod
    public void setTestData(){
        testData = testDataStorage.addTestData();
    }

    @Test
    public void systemAdminCreateProject(){
        var project = checkedWithSuperUser.getProjectRequest()
                .create(testData.getProject());
        softy.assertThat(project.getId()).isEqualTo(testData.getProject().getId());
    }
    @Test
    public void systemAdminCreatingProjectWithNullName(){
        testData.getProject().setName("");

        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

    }
    @Test
    public void systemAdminCreatingProjectWithIdStartingInt(){
        testData.getProject().setId(RandomData.getInt()+ RandomData.getString());

        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
    @Test
    public void systemAdminCreatingProjectWithNullId(){
        testData.getProject().setId("");

        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

    }
    @Test
    public void createSecondProjectWithSameId(){
        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        secondTestData.getProject().setId(firstTestData.getProject().getId());

        checkedWithSuperUser.getProjectRequest()
                .create(firstTestData.getProject());
        uncheckedWithSuperUser.getProjectRequest()
                .create(secondTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("Project ID \""+secondTestData.getProject().getId()
                        +"\" is already used by another project\n"
                        + "Project ID \""+secondTestData.getProject().getId()
                        +"\" is already used by another project"));

    }
    @Test
    public void createSecondProjectWithSameName(){
        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        secondTestData.getProject().setName(firstTestData.getProject().getName());

        checkedWithSuperUser.getProjectRequest()
                .create(firstTestData.getProject());
        uncheckedWithSuperUser.getProjectRequest()
                .create(secondTestData.getProject())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(Matchers.containsString("Project with this name already exists: "
                        + secondTestData.getProject().getName()));
    }

}
