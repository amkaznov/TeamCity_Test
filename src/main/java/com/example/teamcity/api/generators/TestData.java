package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.*;
import com.example.teamcity.api.requests.unchecked.AuthSettingsUnchecked;
import com.example.teamcity.api.requests.unchecked.ProjectUnchecked;
import com.example.teamcity.api.requests.unchecked.UserUnchecked;
import com.example.teamcity.api.spec.Specifications;
import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpStatus;

@Builder
@Data
public class TestData {
    private User user;
    private SuperUser superUser;
    private BuildType buildType;
    private NewProjectDescription project;

    public void delete(){
        var spec =Specifications.getSpec().superUserSpec();
        if (UserUnchecked.isCreated){
            new UserUnchecked(spec).delete(user.getUsername());
        }
        if (ProjectUnchecked.isCreated){
            new ProjectUnchecked(spec).delete(project.getId());
        }
    }
}
