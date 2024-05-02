package com.example.teamcity.api.requests.checked;

import com.example.teamcity.api.models.User;
import com.example.teamcity.api.requests.CrudInterface;
import com.example.teamcity.api.requests.Request;
import com.example.teamcity.api.requests.unchecked.UserUnchecked;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class UserChecked extends Request implements CrudInterface {


     public UserChecked(RequestSpecification spec) {
        super(spec);
    }

    @Override
    public User create(Object obj) {
        return new UserUnchecked(spec)
                .create(obj)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().as(User.class);

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
    public Object delete(String id) {
        return new UserUnchecked(spec)
                .delete(id)
                .then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
