package com.example.teamcity.api.requests.unchecked;

import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.requests.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BuildTypeUnchecked extends Request implements CrudInterface {

    public static final String BUILD_TYPES_ENDPOINT = "/app/rest/buildTypes";
    public static Boolean isCreated = false;
    public void setIsCreated(){
        isCreated=true;
    }

    public BuildTypeUnchecked(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Response create(Object obj) {
        setIsCreated();
        return given().spec(spec).body(obj)
                .post(BUILD_TYPES_ENDPOINT);
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public Response delete(String id) {
        return given().spec(spec)
                .delete(BUILD_TYPES_ENDPOINT+ "/id:"+id);
    }
}
