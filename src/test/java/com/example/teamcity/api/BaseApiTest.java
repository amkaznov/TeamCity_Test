package com.example.teamcity.api;

import com.example.teamcity.api.generators.TestData;
import com.example.teamcity.api.generators.TestDataStorage;
import com.example.teamcity.api.requests.CheckedRequest;
import com.example.teamcity.api.requests.UncheckedRequest;
import com.example.teamcity.api.spec.Specifications;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseApiTest extends BaseTest{
    public TestData testData;
    public TestDataStorage testDataStorage;
    public CheckedRequest checkedWithSuperUser
            = new CheckedRequest(Specifications.getSpec().superUserSpec());
    public UncheckedRequest uncheckedWithSuperUser
            =new UncheckedRequest(Specifications.getSpec().superUserSpec());

    @BeforeSuite
    public void setAuthSetting(){
        testDataStorage=TestDataStorage.getStorage();
        testDataStorage.addAuthData().makeAuthSettings();
    }

    @BeforeMethod
    public void setupTest(){
        testDataStorage = TestDataStorage.getStorage();

    }

    @AfterMethod
    public void cleanTest(){
        testDataStorage.delete();
    }


}
