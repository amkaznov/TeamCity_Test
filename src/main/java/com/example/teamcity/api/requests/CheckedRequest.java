package com.example.teamcity.api.requests;

import com.example.teamcity.api.requests.checked.BuildTypeChecked;
import com.example.teamcity.api.requests.checked.ProjectChecked;
import com.example.teamcity.api.requests.checked.UserChecked;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class CheckedRequest {
    private final UserChecked userRequest;
    private final ProjectChecked projectRequest;
    private final BuildTypeChecked buildTypeRequest;
    public CheckedRequest(RequestSpecification spec){
        this.userRequest=new UserChecked(spec);
        this.projectRequest=new ProjectChecked(spec);
        this.buildTypeRequest=new BuildTypeChecked(spec);

    }
}
