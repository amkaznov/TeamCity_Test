package com.example.teamcity.api;

import com.example.teamcity.api.generators.RandomData;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateBuildConfigurationTest extends BaseApiTest{
    @BeforeMethod
    public void setTestData(){
        testData = testDataStorage.addTestData();
        uncheckedWithSuperUser.getProjectRequest()
                .create(testData.getProject());
    }
    @Test
    public void systemAdminCreateBuildConfiguration(){
        var buildType= checkedWithSuperUser.getBuildTypeRequest()
                .create(testData.getBuildType());
        softy.assertThat(buildType.getId()).isEqualTo (testData.getBuildType().getId());
    }
    @Test
    public void systemAdminCreateBuildConfigurationWithNullName(){
        testData.getBuildType().setName("");

        uncheckedWithSuperUser.getBuildTypeRequest()
                .create(testData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void systemAdminCreateBuildConfigurationWithIdStartingInt(){
        testData.getBuildType().setId(RandomData.getInt()+ RandomData.getString());

        uncheckedWithSuperUser.getBuildTypeRequest()
                .create(testData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
    @Test
    public void systemAdminCreateBuildConfigurationWithNullId(){
        testData.getBuildType().setId("");

        uncheckedWithSuperUser.getBuildTypeRequest()
                .create(testData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
    @Test
    public void systemAdminCreateBuildConfigurationWithSameId(){
        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        firstTestData.getBuildType().setProject(testData.getBuildType().getProject());
        secondTestData.getBuildType().setProject(testData.getBuildType().getProject());

        secondTestData.getBuildType().setId(firstTestData.getBuildType().getId());

        checkedWithSuperUser.getBuildTypeRequest()
                .create(firstTestData.getBuildType());
        uncheckedWithSuperUser.getBuildTypeRequest()
                .create(secondTestData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
    @Test
    public void systemAdminCreateBuildConfigurationWithSameName(){
        var firstTestData = testDataStorage.addTestData();
        var secondTestData = testDataStorage.addTestData();

        firstTestData.getBuildType().setProject(testData.getBuildType().getProject());
        secondTestData.getBuildType().setProject(testData.getBuildType().getProject());

        secondTestData.getBuildType().setName(firstTestData.getBuildType().getName());

        checkedWithSuperUser.getBuildTypeRequest()
                .create(firstTestData.getBuildType());
        uncheckedWithSuperUser.getBuildTypeRequest()
                .create(secondTestData.getBuildType())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
