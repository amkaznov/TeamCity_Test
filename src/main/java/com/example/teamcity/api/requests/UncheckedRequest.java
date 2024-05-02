package com.example.teamcity.api.requests;

import com.example.teamcity.api.requests.unchecked.BuildTypeUnchecked;
import com.example.teamcity.api.requests.unchecked.ProjectUnchecked;
import com.example.teamcity.api.requests.unchecked.UserUnchecked;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class UncheckedRequest {

    private final UserUnchecked userRequest;
    private final ProjectUnchecked projectRequest;
    private final BuildTypeUnchecked buildTypeRequest;
    public UncheckedRequest(RequestSpecification spec){
        this.userRequest=new UserUnchecked(spec);
        this.projectRequest=new ProjectUnchecked(spec);
        this.buildTypeRequest=new BuildTypeUnchecked(spec);

    }
}
