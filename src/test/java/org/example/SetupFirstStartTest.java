package org.example;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;


public class SetupFirstStartTest  {
    @Test
    public void startUpTest(){
        new StartUpPage()
                .open()
                .setupTeamCityServer();
    }
}