package com.example.teamcity.api.generators;

import java.util.ArrayList;
import java.util.List;

public class TestDataStorage {
private static TestDataStorage testDataStorage;
private final List<TestData> testDataList;
private final List<AuthData> authDataList;


    private TestDataStorage(){
        this.testDataList = new ArrayList<>();
        this.authDataList=new ArrayList<>();
    }

    public static TestDataStorage getStorage() {
        if (testDataStorage==null){
            testDataStorage= new TestDataStorage();
        }
        return testDataStorage;
    }

    public TestData addTestData(TestData testData){
        getStorage().testDataList.add(testData);
        return  testData;

    }

    public TestData addTestData(){
        var testData = TestDataGenerator.generate();
        addTestData(testData);
        return  testData;

    }
    public AuthData addAuthData (AuthData authData){
        getStorage().authDataList.add(authData);
        return authData;
    }

    public AuthData addAuthData(){
        var addAuthData = TestDataGenerator.generateAuthSetting();
        addAuthData(addAuthData);
        return addAuthData;
    }

    public void makeAuthSettings(){
        authDataList.forEach(AuthData::makeAuthSettings);
    }

    public void delete(){
        testDataList.forEach(TestData::delete);
    }


}
