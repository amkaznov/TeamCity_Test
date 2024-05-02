package com.example.teamcity.api.requests.unchecked;

import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.requests.Request;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthSettingsUnchecked extends Request implements CrudInterface {

    public static final String AUTH_SETTINGS_ENDPOINT = "/app/rest/server/authSettings";

    public AuthSettingsUnchecked(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public Object create(Object obj) {
        return null;
    }

    @Override
    public Response get(String id) {
        return given().spec(spec)
                .get(AUTH_SETTINGS_ENDPOINT);
    }

    @Override
    public Response update(Object obj) {
        return given().spec(spec).body(obj)
                .put(AUTH_SETTINGS_ENDPOINT);
    }

    @Override
    public Object delete(String id) {
        return null;
    }
}
