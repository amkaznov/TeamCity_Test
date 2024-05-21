package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.AuthSettings;
import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.SuperUser;
import com.example.teamcity.api.models.User;
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


    public void makeAuthSettings(){
        var perProjectPermissionsValue = new AuthSettingsUnchecked(Specifications.getSpec().superUserSpec())
                .get(null)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getBoolean("perProjectPermissions");

        if (!perProjectPermissionsValue){
            AuthSettings authSettings = TestDataGenerator.generateAuthSetting();
            new AuthSettingsUnchecked(Specifications.getSpec().superUserSpec())
                    .update(authSettings)
                    .then().assertThat().statusCode(HttpStatus.SC_OK);
        }
    }

    public void delete(){
        var spec =Specifications.getSpec().superUserSpec();
        new ProjectUnchecked(spec).delete(project.getId());
        new UserUnchecked(spec).delete(user.getUsername());
    }
}
