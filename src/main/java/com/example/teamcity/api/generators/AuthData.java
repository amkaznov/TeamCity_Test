package com.example.teamcity.api.generators;

import com.example.teamcity.api.models.AuthSettings;
import com.example.teamcity.api.requests.unchecked.AuthSettingsUnchecked;
import com.example.teamcity.api.spec.Specifications;
import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpStatus;

@Builder
@Data
public class AuthData {
    private AuthSettings authSettings;
    public void makeAuthSettings(){
        var perProjectPermissionsValue = new AuthSettingsUnchecked(Specifications.getSpec().superUserSpec())
                .get(null)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getBoolean("perProjectPermissions");

        if (!perProjectPermissionsValue){
            new AuthSettingsUnchecked(Specifications.getSpec().superUserSpec())
                    .update(authSettings)
                    .then().assertThat().statusCode(HttpStatus.SC_OK);
        }
    }
}
