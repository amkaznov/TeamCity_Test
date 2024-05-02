package com.example.teamcity.api.generators;

import com.example.teamcity.api.config.Config;
import com.example.teamcity.api.models.AuthSettings;
import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.models.Module;
import com.example.teamcity.api.models.Modules;
import com.example.teamcity.api.models.NewProjectDescription;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.models.Properties;
import com.example.teamcity.api.models.Property;
import com.example.teamcity.api.models.Role;
import com.example.teamcity.api.models.Roles;
import com.example.teamcity.api.models.SuperUser;
import com.example.teamcity.api.models.User;

import java.util.Arrays;
import java.util.List;

public class TestDataGenerator {
    public static TestData generate(){
        var superUser = SuperUser.builder()
                .password(Config.getProperty("superUser_token"))
                .build();

        var user = User.builder()
                .username(RandomData.getString())
                .password(RandomData.getString())
                .email(RandomData.getString() + "@gmail.com")
//                .roles(Roles.builder()
//                        .role(Arrays.asList(Role.builder()
//                                        .roleId("SYSTEM_ADMIN")
//                                        .scope("g")
//                                .build()))
//                        .build())
                .build();

        var project = NewProjectDescription
                .builder()
                .parentProject(Project.builder()
                        .locator("_Root")
                        .build())
                .name("Project_Name_"+RandomData.getString())
                .id("Project_ID_"+RandomData.getString())
                .copyAllAssociatedSettings(true)
                .build();

        var buildType = BuildType.builder()
                .id("Build_Type_ID_"+RandomData.getString())
                .name("Build_Type_Name_"+RandomData.getString())
                .project(project)
                .build();


        return TestData.builder()
                .user(user)
                .project(project)
                .superUser(superUser)
                .buildType(buildType)
                .build();
    }

    public  static Roles generateRoles (com.example.teamcity.api.enums.Role role,String scope){
        return Roles.builder().role(Arrays.asList(Role.builder()
                .roleId(role.getText())
                .scope(scope)
                .build())).build();

    }

    public static AuthSettings generateAuthSetting(){
        return AuthSettings.builder()
                .allowGuest(false)
                .guestUsername("guest")
                .welcomeText("Hi dear friend")
                .collapseLoginForm(false)
                .perProjectPermissions(true)
                .emailVerification(false)
                .modules(new Modules(List.of(
                        new Module("Default", new Properties(List.of(
                                new Property("usersCanResetOwnPasswords", "true"),
                                new Property("usersCanChangeOwnPasswords", "true"),
                                new Property("freeRegistrationAllowed", "false")
                        ), 3)),
                        new Module("Token-Auth", new Properties(List.of(), 0)),
                        new Module("HTTP-Basic", new Properties(List.of(), 0))
                )))
                .build();
    }

}
