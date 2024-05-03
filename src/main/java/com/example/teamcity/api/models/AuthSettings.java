package com.example.teamcity.api.models;

import com.example.teamcity.api.generators.TestDataGenerator;
import com.example.teamcity.api.requests.unchecked.AuthSettingsUnchecked;
import com.example.teamcity.api.spec.Specifications;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthSettings {
    private boolean allowGuest;
    private String guestUsername;
    private String welcomeText;
    private boolean collapseLoginForm;
    private boolean perProjectPermissions;
    private boolean emailVerification;
    private Modules modules;
}
